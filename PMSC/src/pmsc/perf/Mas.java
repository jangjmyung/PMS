package pmsc.perf;

import java.util.*;
import java.sql.*;

import org.apache.log4j.Logger;
import pmsc.CollectionEntity;
import pmsc.PMSCollection;
import pmsc.rcs.Catalog;
import pmsc.rcs.QueueInformation;

public class Mas implements PerformanceInterface {
	private static Logger logger = Logger.getLogger(Mas.class);

	@Override
	public boolean storePerformance(CollectionEntity entity, HashMap<String, String>perfMap,  Set<Catalog>catalogSet) {
		// TODO Auto-generated method stub
		logger.debug(String.format("PMSCollection_storePerf(%s)", entity.toString()));
		
		QueueInformation queueInfo = null;
		synchronized(catalogSet){
			Iterator<Catalog> it = catalogSet.iterator();
			while(it.hasNext()){
				queueInfo = it.next().getQueueInformation(String.format("%s_%s:%d", entity.category, entity.IP, entity.listeningPort ), "0");
				if(queueInfo != null) break;
			}
		}
		
		if(queueInfo == null){
			logger.fatal("Not found QueueInformation");
			return false;
		}
		
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
				pstmt.setLong(20, Long.parseLong(queueInfo.queueDelayTime));
				
				rs = pstmt.executeQuery();
				
				logger.debug("PMSCollection_storePerf(MAS) succeed insert query");
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
		
		return true;
	}

	@Override
	public String getCatalog(){
		// TODO Auto-generated method stub
		return "MAS";
	}
}
