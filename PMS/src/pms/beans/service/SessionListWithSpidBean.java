package pms.beans.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import pms.db.DBPreparedStatement;

public class SessionListWithSpidBean implements DBPreparedStatement {
	
	private String spID = "";
	private Vector<SessionBean> sessionVec = new Vector<SessionBean>();

	@Override
	public void excute(ResultSet rs) throws SQLException {
		if(!sessionVec.isEmpty()){
			sessionVec.clear();
		}
		
		while(rs.next()){
			SessionBean session = new SessionBean();
			session.setSessionID(rs.getLong("SESSION_ID"));
			session.setSpID(rs.getString("SP_ID"));
			session.setEuID(rs.getString("EU_ID"));
			session.setClientIP(rs.getString("CLIENT_IP"));
			session.setMasID(rs.getString("MAS_ID"));
			session.setConTime(rs.getDate("CON_TIME"));
			session.setMasIP(rs.getString("MAS_IP"));
			session.setClientVer(rs.getString("CLIENT_VERSION"));
			session.setCsdkVer(rs.getString("CSDK_VERSION"));
			
			sessionVec.add(session);
		}
	}

	@Override
	public String getQuery() {
		// TODO Auto-generated method stub
		return "select * from M_SESSION2 where sp_id=?";
	}

	@Override
	public boolean prepare(PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, spID);		
		return true;
	}
	
	public Vector<SessionBean> getSessionVec() {
		return sessionVec;
	}

	public void setSessionVec(Vector<SessionBean> sessionVec) {
		this.sessionVec = sessionVec;
	}

	public String getSpID() {
		return spID;
	}

	public void setSpID(String spID) {
		this.spID = spID;
	}
}
