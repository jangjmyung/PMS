/**
 * 
 */
package pmsc;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;
import java.net.*;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import pmsc.perf.PerformanceInterface;
import pmsc.rcs.Catalog;

//http://119.205.196.124:11023/perfs/MRS_SK/SKT-SMS-1


/**
 * @author jjm
 *
 */
public class PMSCollection implements Runnable{
	
	public PMSCollection(String category, PerformanceInterface perfIF){
		this.category = category;
		this.perfIF = perfIF;
	}
	
	private Boolean cont = true;
	private String category = "";
	private PerformanceInterface perfIF =null;
	private Set<Catalog> catalogSet = new HashSet<Catalog>();
	
	private LinkedBlockingQueue<CollectionEntity> entityQueue = new LinkedBlockingQueue<CollectionEntity>();
	public static String oracleUrl = "jdbc:oracle:thin:@210.105.195.187:1521:PDB1";
	
	private static HashMap<String, PMSCollection> collectionMap = new HashMap<String, PMSCollection>();
	private static int port = 11023;
	private static Logger logger = Logger.getLogger(PMSCollection.class);
	
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		logger.debug("PMSCollection_Load oracle driver");
		
		while(true){
			Calendar startCal = Calendar.getInstance();
			PMSProperties prop = PMSProperties.loadProperies();
			if(prop.exit){
				logger.debug("PMSCollection_Terminate process");
				stopWorker(prop);
				break;
			}
			
			startWorker(prop);
			
			Queue<CollectionEntity> dbEntityQueue = CollectionEntity.getDBEntity();
			while(!dbEntityQueue.isEmpty()){
				CollectionEntity entity = dbEntityQueue.poll();
				if(entity == null) continue;
				if(entity.category == null || entity.category.length() == 0) continue;
				if(!collectionMap.containsKey(entity.category)) continue;
				
				PMSCollection pms = collectionMap.get(entity.category);
				if(pms == null || !pms.putEntity(entity)){
					logger.fatal("PMSCollection_Failed put entity");				
				}
			}
			
			try {
				long interval = Calendar.getInstance().getTimeInMillis() - startCal.getTimeInMillis();
				interval = prop.interval - interval;
				if(interval > 0) Thread.sleep(interval);
			} catch (InterruptedException e) {
				logger.fatal("exception", e);
			}
		}
	}
	
	public static void stopWorker(PMSProperties prop){
		Iterator<PMSCollection> it = collectionMap.values().iterator();
		while(it.hasNext()){
			PMSCollection pms = (PMSCollection)it.next();
			if(pms == null) continue;
			
			synchronized(pms.cont){
				pms.cont = false;
			}
		}
	}
	
	public static void startWorker(PMSProperties prop){
		logger.debug("PMSCollection_startWorker");
		
		Iterator<String> it = prop.categorySet.iterator();
		while(it.hasNext()){
			String category = (String)it.next();
			if(category == null || category.length() == 0){
				logger.debug("PMSCollection_category is empty");
				continue;
			}
			
			if(!collectionMap.containsKey(category)){
				String className = prop.classMap.get(category);
				if(className == null || className.isEmpty()){
					logger.fatal("Performance class name is null(" + category +  ")");
					continue;
				}
				try {
					Class<?> perfClass = Class.forName(className);
					Object perfInstance = perfClass.newInstance();;
					PMSCollection pms = new PMSCollection(category, (PerformanceInterface)perfInstance);
					
					collectionMap.put(category, pms);
					new Thread(pms).start();
					
					logger.debug(String.format("PMSCollection_categori(%s) is start", category));
				} catch (InstantiationException e) {
					logger.fatal("exception", e);
				} catch (IllegalAccessException e) {
					logger.fatal("exception", e);
				} catch (ClassNotFoundException e){
					logger.fatal("exception", e);
				}
			}
			else{
				logger.debug(String.format("PMSCollection_categori(%s) is running", category));
			}
		}
		
		Iterator<Entry<String, PMSCollection>> it2 = collectionMap.entrySet().iterator();
		while(it2.hasNext()){
			PMSCollection pms = it2.next().getValue();
			if(pms != null){
				pms.loadRcsPerf(prop.rcsIPSet, prop.rcsPort);
			}
		}
	}
	
	public boolean putEntity(CollectionEntity entity){
		boolean ret = true;
		try{
			entityQueue.put(entity);
			
			logger.debug("PMSCollection_putEntity(" + entity.toString() + ")");
			
		}catch(InterruptedException e){
			logger.fatal("exception", e);
			ret = false;
		}
		return ret;
	}
/*	
	public void storePerf(CollectionEntity entity, HashMap<String, String> perfMap){
		logger.debug(String.format("PMSCollection_storePerf(%s)", entity.toString()));
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(PMSCollection.oracleUrl, "PMS", "wkfgkwk$250");
			con.setAutoCommit(true);
			String query = "insert into P_PERF_MAS (SEQNO,SERVERNAME, IP, AVR_RMPS, CUR_PM, CUR_MQ, AVR_TMP, CUR_WRM, AVR_TMR, CUR_RQ, CUR_AJ, CUR_NQ, CUR_PP, AVR_RVPS, CUR_CR, AVR_RSPS, AVR_FPPS, AVR_RMMPS, AVR_TSM, AVR_TMQ, SESSION_CNT) " +
					"values (seq_p_perf_mas.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			if(con != null){
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, entity.serverName);
				pstmt.setString(2, entity.IP);
				pstmt.setInt(3, Integer.parseInt(perfMap.get("AverageRequestMessagesPerSec")));
				pstmt.setInt(4, Integer.parseInt(perfMap.get("CurrentProcessingMessages")));
				pstmt.setInt(5, Integer.parseInt(perfMap.get("CurrentMessageQueue")));
				pstmt.setInt(6, Integer.parseInt(perfMap.get("AverageTimeMessageProcessing")));
				pstmt.setInt(7, Integer.parseInt(perfMap.get("CurrentWaitingReponseMessage")));
				pstmt.setInt(8, Integer.parseInt(perfMap.get("AverageTimeMessageResponse")));
				pstmt.setInt(9, Integer.parseInt(perfMap.get("CurrentReportQueue")));
				pstmt.setInt(10, Integer.parseInt(perfMap.get("CurrentAllocatingJobID")));
				pstmt.setInt(11, Integer.parseInt(perfMap.get("CurrentNPDBQueries")));
				pstmt.setInt(12, Integer.parseInt(perfMap.get("CurrentProcessingReport")));
				pstmt.setInt(13, Integer.parseInt(perfMap.get("AverageRequestVMSsPerSec")));
				pstmt.setInt(14, Integer.parseInt(perfMap.get("CurrentConvertRequests")));
				pstmt.setInt(15, Integer.parseInt(perfMap.get("AverageRequestSMSsPerSec")));
				pstmt.setInt(16, Integer.parseInt(perfMap.get("AverageRequestFMSsPerSec")));
				pstmt.setInt(17, Integer.parseInt(perfMap.get("AverageRequestMMSsPerSec")));
				pstmt.setInt(18, Integer.parseInt(perfMap.get("AverageTimeSendMessage")));
				pstmt.setInt(19, Integer.parseInt(perfMap.get("AverageTimeMessageQueue")));
				pstmt.setLong(20, Long.parseLong(entity.prop.mas.getSessionCnt(String.format("%s_%s:%d", entity.category, entity.IP, entity.listeningPort ))));
				
				rs = pstmt.executeQuery();
				
				logger.debug("PMSCollection_storePerf succeed insert query");
			}
		} catch (SQLException e) {
			logger.fatal("exception", e);
		} catch (Exception e){
			logger.fatal("exception", e);
		}
		finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {logger.fatal("exception", e);};
			if(pstmt != null) try {pstmt.close();} catch (SQLException e) {logger.fatal("exception", e);};
			if(con != null) try { con.close(); } catch (SQLException e) {logger.fatal("exception", e);};
		}
	}
*/	
	private static String xmlToString(Node node) {
		try {
				Source source = new DOMSource(node);
				StringWriter stringWriter = new StringWriter();
				Result result = new StreamResult(stringWriter);
				TransformerFactory factory = TransformerFactory.newInstance();
				Transformer transformer = factory.newTransformer();
				transformer.transform(source, result);
				return stringWriter.getBuffer().toString();
			} catch (TransformerConfigurationException e) {
				logger.fatal("exception", e);
			} catch (TransformerException e) {
				logger.fatal("exception", e);
			}
			return null;
		}

	
	private static HashMap<String, String> loadPerf(CollectionEntity entity){
		BufferedInputStream in = null;
		HttpURLConnection con = null;
		
		HashMap<String, String>perfMap =  new HashMap<String, String>();
		
		try {
			String query = entity.makeQueryString();
			URL url = new URL("http", entity.IP, PMSCollection.port, query);
			con = (HttpURLConnection)url.openConnection();
			in = new BufferedInputStream(con.getInputStream());

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.parse(in);
			doc.getDocumentElement().normalize();
			
			logger.debug("PMSCollection_XML\r\n" + xmlToString(doc.getDocumentElement()));
			
			NodeList nList = doc.getElementsByTagName("Counter");
	
			for(int i = 0; i<nList.getLength(); i++){
				Node nNode = nList.item(i);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String attr = eElement.getAttribute("name");
					String content = eElement.getTextContent();
					
					if(attr == null || attr.isEmpty()) continue;
					if(content == null || content.isEmpty()) continue;
					
					perfMap.put(attr, content);
				}
			}
		} catch (ParserConfigurationException e) {
			logger.fatal("exception", e);
		}catch (MalformedURLException e) {
			logger.fatal("exception", e);
		} catch (IOException e) {
			logger.fatal("exception", e);
		} catch (Exception e){
			logger.fatal("exception", e);
		}finally {
			if( in != null) try{in.close();}catch(Exception e){logger.fatal("exception", e);}
			if( con  != null) con.disconnect();
		}
		
		return perfMap;
	}
	
	public void run(){
		logger.debug(String.format("PMSCollection_thread(%s) start", category));
		
		for(;;){
			Boolean cont = true;
			synchronized(this.cont){
				if(entityQueue.isEmpty() && !this.cont) cont = false; 
			}
			
			if(!cont) break;

			try {
				CollectionEntity entity = entityQueue.poll(1, TimeUnit.SECONDS);
				if(entity == null) continue;
				
				HashMap<String, String> perfMap = PMSCollection.loadPerf(entity);
				if(perfMap == null) continue;
				if(perfMap.isEmpty()) continue;
				if(perfIF == null) continue;
				
				perfIF.storePerformance(entity, perfMap, catalogSet);
				
			} catch (InterruptedException e) {
				logger.fatal("exception", e);
			}
		}
	}
	
	private boolean loadRcsPerf(Set<String> rcsIPSet, int rcsPort){
		if(perfIF == null) return false;
		if(perfIF.getCatalog() == null || perfIF.getCatalog().isEmpty()) return false;
		
		synchronized(catalogSet){
			catalogSet.clear();
			
			Iterator<String> it = rcsIPSet.iterator();
			while(it.hasNext()){
				String rcsIP = it.next();
				BufferedInputStream in = null;
				HttpURLConnection con = null;
				
				try {
					String query = String.format("/catalogs/%s", perfIF.getCatalog());
					URL url = new URL("http", rcsIP, rcsPort, query);
					con = (HttpURLConnection)url.openConnection();
					in = new BufferedInputStream(con.getInputStream());
		
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
					Document doc = dbBuilder.parse(in);
					doc.getDocumentElement().normalize();
					
					logger.debug("MAS_XML\r\n" + PMSCollection.xmlToString(doc.getDocumentElement()));
					
					NodeList catalogNodeList = doc.getElementsByTagName("Catalog");
					
					for(int i = 0; i<catalogNodeList.getLength(); i++){
						Node catalogNode = catalogNodeList.item(i);
						if(catalogNode.getNodeType() == Node.ELEMENT_NODE){
							Catalog catalog = new Catalog();
							if(catalog.loadElement((Element)catalogNode)){
								catalogSet.add(catalog);								
							}
						}
					}
				} catch (ParserConfigurationException e) {
					logger.fatal("exception", e);
				}catch (MalformedURLException e) {
					logger.fatal("exception", e);
				} catch (IOException e) {
					logger.fatal("exception", e);
				} catch (Exception e){
					logger.fatal("exception", e);
				}finally {
					if( in != null) try{in.close();}catch(Exception e){logger.fatal("exception", e);}
					if( con  != null) con.disconnect();
				}
			}
		}
			return true;
	}
}
