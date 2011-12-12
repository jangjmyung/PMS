package pms.beans.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import pms.db.DBPreparedStatement;

public class CodeBean implements DBPreparedStatement {
	private String spID = "";
	private Calendar calendar;
	private int messageType = 1;
	private int errorCode = 0;
	private int count = 0;
	
	private Vector<CodeBean> codeBeanVec = new Vector<CodeBean>();
	
	@Override
	public void excute(ResultSet rs) throws SQLException {
		while(rs.next()){
			CodeBean bean = new CodeBean();
			bean.setSpID(spID);
			bean.setMessageType(messageType);
			bean.setErrorCode(rs.getInt(1));
			bean.setCount(rs.getInt(2));
			
			getCodeBeanVec().add(bean);
		}
	}

	@Override
	public String getQuery() {
		String query = String.format("SELECT result, count(*) FROM M_HISTORY PARTITION (M_HISTORY_PT_%d%02d%02d) WHERE status > 1 and receiver_count = 1 and msg_type=? and sp_id=? and submit_time >= ? and submit_time <= ? group by result", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
		return query;
	}

	@Override
	public boolean prepare(PreparedStatement pstmt) throws SQLException {
		Date date = getCalendar().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String startSubmitTime = sdf.format(date);
		
		getCalendar().add(Calendar.MINUTE, 5);
		date = getCalendar().getTime();
		String endSubmitTime = sdf.format(date);
		
		pstmt.setInt(1, messageType);
		pstmt.setString(2, spID);
		pstmt.setString(3, startSubmitTime);
		pstmt.setString(4, endSubmitTime);
		
		return true;
	}

	public String getSpID() {
		return spID;
	}

	public void setSpID(String spID) {
		this.spID = spID;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Vector<CodeBean> getCodeBeanVec() {
		return codeBeanVec;
	}

	public void setCodeBeanVec(Vector<CodeBean> codeBeanVec) {
		this.codeBeanVec = codeBeanVec;
	}

}
