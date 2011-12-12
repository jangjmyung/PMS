package pms.beans.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import pms.db.DBPreparedStatement;
import java.util.*;

public class StatusBean implements DBPreparedStatement {
	
	private String spID = "";
	private String messageType = "";
	private int totalMsg = 0;
	private int completeMsg = 0;
	private int standByMsg = 0;
	private int successRate = 0;
	private int statusZero = 0;
	private int statusOne = 0;
	private int statusTwo = 0;
	private int statusThree = 0;
	private int statusFour = 0;
	private int successCount = 0;
	
	private Calendar calendar;

	@Override
	public void excute(ResultSet rs) throws SQLException {
		while(rs.next()){
			int status = rs.getInt(1);
			int succ = rs.getInt(2);
			int fail = rs.getInt(3);
			int receiver = rs.getInt(4);
			
			totalMsg += receiver;
			
			switch(status){
			case 0 :
				statusZero += receiver;
				standByMsg += receiver;
				break;
			case 1 :
				statusOne += receiver;
				completeMsg += receiver; 
				break;
			case 2 :
				statusTwo += receiver;
				successCount += succ;
				completeMsg += receiver;
				break;
			case 3 :
				statusThree += receiver;
				successCount += succ;
				completeMsg += receiver;
				break;
			case 4 :
				statusFour += receiver;
				successCount += succ;
				completeMsg += receiver;
				break;
			default:
				break;
			}
		}
		
		if(successCount > 0 && totalMsg > 0){
			successRate = (int)((double)successCount / (double)totalMsg * 100);			
		}
	}

	@Override
	public String getQuery() {
		String query = "";
		if(messageType.equals("MMS"))
			query = String.format("SELECT status, sum(succ_count), sum(fail_count), sum(receiver_count) FROM M_HISTORY PARTITION (M_HISTORY_PT_%d%02d%02d) WHERE receiver_count = 1 and msg_type=? and MSG_SUB_TYPE <> 1 and sp_id=? and submit_time >= ? and submit_time <= ? group by status", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
		else if(messageType.equals("LMS"))
			query = String.format("SELECT status, sum(succ_count), sum(fail_count), sum(receiver_count) FROM M_HISTORY PARTITION (M_HISTORY_PT_%d%02d%02d) WHERE receiver_count = 1 and msg_type=? and MSG_SUB_TYPE = 1 and sp_id=? and submit_time >= ? and submit_time <= ? group by status", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
		else
			query = String.format("SELECT status, sum(succ_count), sum(fail_count), sum(receiver_count) FROM M_HISTORY PARTITION (M_HISTORY_PT_%d%02d%02d) WHERE receiver_count = 1 and msg_type=? and sp_id=? and submit_time >= ? and submit_time <= ? group by status", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
		return query;
	}

	@Override
	public boolean prepare(PreparedStatement pstmt) throws SQLException {
		int msgType = 1;
		
		if(messageType.equals("SMS")) msgType = 1;
		else if(messageType.equals("LMS")) msgType = 4;
		else if(messageType.equals("MMS")) msgType = 4;
		else if(messageType.equals("VMS")) msgType = 2;
		else if(messageType.equals("FMS")) msgType = 3;
		
		Date date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String startSubmitTime = sdf.format(date);
		
		calendar.add(Calendar.MINUTE, 5);
		date = calendar.getTime();
		String endSubmitTime = sdf.format(date);
		
		pstmt.setInt(1, msgType);
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

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public int getTotalMsg() {
		return totalMsg;
	}

	public void setTotalMsg(int totalMsg) {
		this.totalMsg = totalMsg;
	}

	public int getCompleteMsg() {
		return completeMsg;
	}

	public void setCompleteMsg(int completeMsg) {
		this.completeMsg = completeMsg;
	}

	public int getStandByMsg() {
		return standByMsg;
	}

	public void setStandByMsg(int standByMsg) {
		this.standByMsg = standByMsg;
	}

	public int getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(int successRate) {
		this.successRate = successRate;
	}

	public int getStatusZero() {
		return statusZero;
	}

	public void setStatusZero(int statusZero) {
		this.statusZero = statusZero;
	}

	public int getStatusOne() {
		return statusOne;
	}

	public void setStatusOne(int statusOne) {
		this.statusOne = statusOne;
	}

	public int getStatusTwo() {
		return statusTwo;
	}

	public void setStatusTwo(int statusTwo) {
		this.statusTwo = statusTwo;
	}

	public int getStatusThree() {
		return statusThree;
	}

	public void setStatusThree(int statusThree) {
		this.statusThree = statusThree;
	}

	public int getStatusFour() {
		return statusFour;
	}

	public void setStatusFour(int statusFour) {
		this.statusFour = statusFour;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}

}
