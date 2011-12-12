package pmsc.perf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

import org.apache.log4j.Logger;

import pmsc.CollectionEntity;
import pmsc.PMSCollection;
import pmsc.rcs.Catalog;

public class Rcs implements PerformanceInterface {
	
	private static Logger logger = Logger.getLogger(Rcs.class);

	@Override
	public boolean storePerformance(CollectionEntity entity, HashMap<String, String> perfMap, Set<Catalog> catalogSet) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(PMSCollection.oracleUrl, "PMS", "wkfgkwk$250");
			
			con.setAutoCommit(true);
			String query = "insert into P_PERF_RCS (SEQNO, CATEGORY, SERVERNAME, IP,  PROVIDER_CONNECTION_COUNT, PROVIDER_COUNTER, CONSUMER_CONNECTION_COUNT, CONSUMER_COUNTER) " +
					"values (seq_p_perf_rcs.nextval, ?, ?, ?, ?, ?, ?, ?)";
			if(con != null){
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, entity.category);
				pstmt.setString(2, entity.serverName);
				pstmt.setString(3, entity.IP);
				pstmt.setInt(4, Integer.parseInt(perfMap.get("Provider Connection Count")));
				pstmt.setInt(5, Integer.parseInt(perfMap.get("Provider Count")));
				pstmt.setInt(6, Integer.parseInt(perfMap.get("Consumer Connection Count")));
				pstmt.setInt(7, Integer.parseInt(perfMap.get("Consumer Count")));
				
				rs = pstmt.executeQuery();
				
				logger.debug("PMSCollection_storePerf(RCS) succeed insert query");
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
	public String getCatalog() {
		// TODO Auto-generated method stub
		return "";
	}
}
