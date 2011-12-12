package pms.beans.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import pms.db.DBPreparedStatement;

import java.util.*;

public class FailureBean implements DBPreparedStatement {
	
	//DB column
	private int seq = 0;
	private String failureType = "";
	private String failureDesc = "";
	private Calendar failureCal = Calendar.getInstance();
	private Calendar recoveryCal = Calendar.getInstance();
	private String id = "";
	private String description = "";
	private String resolution = "";
	private int checked = 0;			// 0: 해결전, 1: 해결후
	private Vector<FailureBean> failureBeansVec = new Vector<FailureBean>();
	
	private String failureTime = "";
	private String recoveryTime = "";
	
	//검색 조건
	private Calendar startCal;
	private Calendar endCal;
	private String clientID;
	
	@Override
	public void excute(ResultSet rs) throws SQLException {
		SimpleDateFormat SDF_SECOND = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if(!failureBeansVec.isEmpty()){
			failureBeansVec.clear();
		}
		
		while(rs.next()){
			FailureBean bean = new FailureBean();
			
			bean.seq = rs.getInt("SEQNO");
			bean.failureType = rs.getString("FAILURE_TYPE");
			bean.failureDesc = rs.getString("FAILURE_DESC");
			
			java.util.Date failureDate = rs.getTimestamp("FAILURE_TIME");
			java.util.Date recoveryDate = rs.getTimestamp("RECOVERY_TIME");
			
			if(failureDate != null){
				failureCal.setTime(failureDate);
				bean.setFailureTime(SDF_SECOND.format(failureDate));
			}
			
			if(recoveryDate != null){
				recoveryCal.setTime(recoveryDate);
				bean.setRecoveryTime(SDF_SECOND.format(recoveryDate));
			}
			
			bean.id = rs.getString("ID");
			bean.description = rs.getString("DESCRIPTION");
			bean.resolution = rs.getString("RESOLUTION");
			bean.checked = rs.getInt("CHECKED");
			
			failureBeansVec.add(bean);
		}
	}

	@Override
	public String getQuery() {
		String query = "select * from s_failure where to_char(FAILURE_TIME, \'yyyyMMdd\') >= ? and to_char(FAILURE_TIME, \'yyyyMMdd\') <= ? ";
		
		if(!failureType.equals("all")){
			query += " and FAILURE_TYPE = ?";			
		}
		
		if(!clientID.equals("")){
			query += "and ID = ? ";
		}
		
		query += "order by seqno desc";

		return query;
	}

	@Override
	public boolean prepare(PreparedStatement pstmt) throws SQLException {
		SimpleDateFormat SDF_DAY = new SimpleDateFormat("yyyyMMdd");
		pstmt.setString(1, SDF_DAY.format(startCal.getTime()));
		pstmt.setString(2, SDF_DAY.format(endCal.getTime()));
		
		int index = 3;
		if(!failureType.equals("all")){
			pstmt.setString(index, failureType);			
			index++;
		}
		
		if(!clientID.equals("")){
			pstmt.setString(index, clientID);
		}
		
		return true;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getFailureType() {
		return failureType;
	}

	public void setFailureType(String failureType) {
		this.failureType = failureType;
	}

	public String getFailureDesc() {
		return failureDesc;
	}

	public void setFailureDesc(String failureDesc) {
		this.failureDesc = failureDesc;
	}

	public Calendar getFailureCal() {
		return failureCal;
	}

	public void setFailureTime(Calendar failureCal) {
		this.failureCal = failureCal;
	}

	public Calendar getRecoveryCal() {
		return recoveryCal;
	}

	public void setRecoveryCal(Calendar recoveryCal) {
		this.recoveryCal = recoveryCal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	public Calendar getStartCal() {
		return startCal;
	}

	public void setStartCal(Calendar startCal) {
		this.startCal = startCal;
	}

	public Calendar getEndCal() {
		return endCal;
	}

	public void setEndCal(Calendar endCal) {
		this.endCal = endCal;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public Vector<FailureBean> getFailureBeansVec() {
		return failureBeansVec;
	}

	public void setFailureBeansVec(Vector<FailureBean> failureBeansVec) {
		this.failureBeansVec = failureBeansVec;
	}

	public String getFailureTime() {
		return failureTime;
	}

	public void setFailureTime(String failureTime) {
		this.failureTime = failureTime;
	}

	public String getRecoveryTime() {
		return recoveryTime;
	}

	public void setRecoveryTime(String recoveryTime) {
		this.recoveryTime = recoveryTime;
	}
}
