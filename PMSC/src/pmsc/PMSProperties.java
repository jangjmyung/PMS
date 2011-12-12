package pmsc;

import java.io.IOException;
import java.util.*;

import org.apache.log4j.Logger;

//import pmsc.rcs.MAS;

public class PMSProperties {
	PMSProperties(){}
	
	protected boolean exit = false;
	protected int interval = 10000;
	protected int rcsPort = 80;
	protected Set<String>	categorySet = new HashSet<String>();
	protected Set<String> rcsIPSet = new HashSet<String>();
	protected HashMap<String, String> classMap = new HashMap<String, String>();
//	protected MAS mas = null;
	
	private static Logger logger = Logger.getLogger(PMSProperties.class);
	
/*	
	public void loadRCS(){
		if(categorySet.contains(MAS.category)){
			mas = new MAS();
			if(!mas.load(rcsIPSet, rcsPort)){
				logger.info("PMSProperties_Failed load rcs infomation(MAS)");				
			}
		}
	}
*/	
	
	static public PMSProperties loadProperies(){
		PMSProperties PMSProp = new PMSProperties();
		try {
			Properties prop = new Properties();
			prop.load(PMSProperties.class.getClassLoader().getResourceAsStream("pms.properties"));
			
			String exit = prop.getProperty("EXIT");
			if(exit != null && !exit.isEmpty()){
				if(exit.compareTo("true") == 0){
					PMSProp.exit = true;
				}
			}else{
				logger.info("PMSProperties_EXIT is null");
			}
			
			String interval = prop.getProperty("INTERVAL");
			if(interval != null && !interval.isEmpty()){
				PMSProp.interval = Integer.parseInt(interval);
			}else{
				logger.info("PMSProperties_INTERVAL is null");
			}
			
			String category = prop.getProperty("CATEGORY");
			if(category != null && !category.isEmpty()){
				String categoryArr[] = category.split(",");
				for(int i=0; i<categoryArr.length; i++){
					PMSProp.categorySet.add(categoryArr[i]);
				}
			}else{
				logger.info("PMSProperties_CATEGORY is null");
			}
			
			String rcs = prop.getProperty("RCS");
			if(rcs != null && !rcs.isEmpty()){
				String rcsArr[] = rcs.split(",");
				for(int i=0; i<rcsArr.length ; i++){
					PMSProp.rcsIPSet.add(rcsArr[i]);
				}
			}else{
				logger.info("PMSProperties_RCS is null");
			}
			
			String rcsPort = prop.getProperty("RCS_PORT");
			if(rcsPort != null && !rcsPort.isEmpty()){
				PMSProp.rcsPort = Integer.parseInt(rcsPort);
			}else{
				logger.info("PMSProperties_RCS PORT is null");
			}
			
			logger.debug(String.format("PMSProperties(EXIT:%s, INTERVAL:%s, CATEGORY:%s, RCS:%s, RCS_PORT:%s)", exit, interval, category, rcs, rcsPort));
			
			Properties prop2 = new Properties();
			prop2.load(PMSProperties.class.getClassLoader().getResourceAsStream("pms_class.properties"));
			Iterator<?> it = prop2.keySet().iterator();
			while(it.hasNext()){
				String category2 = (String)it.next();
				String className = prop2.getProperty(category2);
				
				if(category2 != null && !category2.isEmpty() && className != null && !className.isEmpty()){
					PMSProp.classMap.put(category2, className);
				}
			}
			
		} catch (IOException e) {
			logger.fatal("exception", e);
		}
		
		return PMSProp;
	}
}