package pms.beans.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import pms.db.DBStatement;

public class SessionListBean implements DBStatement {
	
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
		return "select * from M_SESSION2 order by SP_ID";
	}
	
	public Vector<SessionBean> getSessionVec() {
		return sessionVec;
	}

	public void setSessionVec(Vector<SessionBean> sessionVec) {
		this.sessionVec = sessionVec;
	}
}
