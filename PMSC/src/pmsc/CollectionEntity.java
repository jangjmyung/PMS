package pmsc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

public class CollectionEntity {
	private static Logger logger = Logger.getLogger(CollectionEntity.class);
	public String category = "";
	public String IP = "";
	public String performance = "";
	public String instance = "";
	public String serverName ="";
	public String CID = "";
	public int msgType = 0;
	public int smtpType = 0;
	public int listeningPort = 0;
	public int systemType = 0;
	
	public CollectionEntity(){}
	public CollectionEntity(String category, String IP, String performance, String instance, 
			int msgType, int smtpType, String serverName, String CID, int listeningPort, int systemType){
		this.category = category;
		this.IP = IP;
		this.performance = performance;
		this.instance = instance;
		this.msgType = msgType;
		this.smtpType = smtpType;
		this.serverName = serverName;
		this.CID = CID;
		this.listeningPort = listeningPort;
		this.systemType = systemType;
		
		logger.debug(this.toString());
	}
	
	public String makeQueryString(){
		String query = "/perfs";
		try {
			if(performance != null && !performance.isEmpty() && performance.length() > 0) query = query + "/" + URLEncoder.encode(performance, "euc-kr").replace("+", "%20");
			if(instance != null && !instance.isEmpty() && instance.length() > 0) query = query + "/" + URLEncoder.encode(instance, "euc-kr").replace("+", "%20");
		} catch (UnsupportedEncodingException e) {
			logger.fatal("exception", e);
		}
				
		logger.debug(String.format("CollectionEntity(query string:%s)", query));
		
		return query;
	}
	
	public String toString(){
		return String.format("CollectionEntity(category:%s, IP:%s, performance:%s, instance:%s, msgType:%d, smtpType:%d, CID:%s, listeningPort:%d, systemType:%d)" 
				, category
				, IP
				, performance
				, instance
				, msgType
				, smtpType
				, CID
				, listeningPort
				, systemType);
	}
	
	public static Queue<CollectionEntity> getDBEntity(){
		Queue<CollectionEntity> queue = new LinkedList<CollectionEntity>();
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(PMSCollection.oracleUrl, "PMS", "wkfgkwk$250");
			
			logger.debug("PMSCollection_getDBEntity(Connected DB)");
			
			con.setAutoCommit(true);
			String query = "select * from P_ENTITY";
			if(con != null){
				stm = con.createStatement();
				rs = stm.executeQuery(query);
												
				while(rs.next()){
					String category = rs.getString(1);
					String serverName = rs.getString(2);
					String IP = rs.getString(3);
					String performance = rs.getString(4);
					String instance = rs.getString(5);
					String CID = rs.getString(6);
					int msgType = rs.getInt(7);
					int smtpType = rs.getInt(8);
					int listeningPort = rs.getInt(9);
					int systemType = rs.getInt(10);
					
					logger.debug(String.format("PMSCollection_getDBEntity(category:%s, serverName;%s, IP:%s, performance:%s, instance:%s, CID:%s, msgType:%d, smtpType:%d, listeningPort:%d, systemType:%d)",
							category,
							serverName,
							IP,
							performance,
							instance,
							CID,
							msgType,
							smtpType,
							listeningPort,
							systemType));
					
					queue.add(new CollectionEntity(category, IP, performance, instance, msgType, smtpType, serverName, CID, listeningPort, systemType));
				}
			}
		} catch (SQLException e) {
			logger.fatal("exception", e);
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException e) {logger.fatal("exception", e);};
			if(stm != null) try {stm.close();} catch (SQLException e) {logger.fatal("exception", e);};
			if(con != null) try { con.close(); } catch (SQLException e) {logger.fatal("exception", e);};
		}
		
		return queue;
	}
}