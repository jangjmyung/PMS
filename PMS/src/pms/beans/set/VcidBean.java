package pms.beans.set;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pms.db.DBPreparedStatement;
import java.util.*;

public class VcidBean implements DBPreparedStatement {

	//속성
	private int vcid =  0;
	private String vcidName = "";
	private int messageType = 0;
	private int messageSubType = 0;
	private int telcoinfo = 0;
	private String spID = "";
	private int status = 0;
	
	private int totalPerSec = 0;
	private long standBy = 0;
	private int availableCid = 0;
	private int noneCid = 0;
	
	private String messageTypeName ="";
	private String telcoinfoName = "";
	
	private Vector<VcidBean> vcidBeansVec = new Vector<VcidBean>();
	
	@Override
	public void excute(ResultSet rs) throws SQLException {
		if(!vcidBeansVec.isEmpty()){
			vcidBeansVec.clear();
		}
		
		while(rs.next()){
			VcidBean bean = new VcidBean();
			
			bean.vcid = rs.getInt("VCID");
			bean.vcidName = rs.getString("VCID_NAME");
			bean.messageType = rs.getInt("MSG_TYPE");
			bean.messageSubType = rs.getInt("MSG_SUB_TYPE");
			bean.telcoinfo = rs.getInt("TELCOINFO");
			bean.spID = rs.getString("SP_ID");
			bean.status = rs.getInt("STATUS");
			bean.setMessageTypeName(bean.messageType);
			bean.setTelcoInfoName(bean.telcoinfo);
			
			vcidBeansVec.add(bean);
		}
	}
	
	private void setMessageTypeName(int messageType){
		switch(messageType){
		case 1:
			messageTypeName = "SMS";
			break;
		case 2:
			messageTypeName ="VMS";
			break;
		case 3:
			messageTypeName = "FMS";
			break;
		case 4:
			messageTypeName = "MMS";
			break;
		default:
				break;
		}
	}
	
	private void setTelcoInfoName(int telcoInfo){
		switch(telcoInfo){
		case 1:
			telcoinfoName = "SKT";
			break;
		case 2:
			telcoinfoName = "KT";
			break;
		case 3:
			telcoinfoName = "LGT";
			break;
		case 4:
			telcoinfoName = "ISMC";
			break;
		case 5:
			telcoinfoName = "DACOM";
			break;
		default:
				break;
		}
	}

	@Override
	public String getQuery() {
		String query = "select * from M_VCID_INFO where MSG_TYPE < 5 and MSG_SUB_TYPE < 10";
		
		if(messageType != 0){
			query += " and MSG_TYPE = ?";
		}
		
		if(telcoinfo != 0){
			query += " and TELCOINFO = ?";
		}
		
		if(!vcidName.equals("")){
			query += " and VCID_NAME = ?";			
		}
		
		query += " order by VCID";

		return query;
	}

	@Override
	public boolean prepare(PreparedStatement pstmt) throws SQLException {
		
		int index = 1;
		if(messageType != 0){
			pstmt.setInt(index, messageType);
			index++;
		}
		
		if(telcoinfo != 0){
			pstmt.setInt(index, telcoinfo);
			index++;
		}
		
		if(!vcidName.equals("")){
			pstmt.setString(index, vcidName);
			index++;
		}
		
		return true;
	}

	public int getVcid() {
		return vcid;
	}

	public void setVcid(int vcid) {
		this.vcid = vcid;
	}

	public String getVcidName() {
		return vcidName;
	}

	public void setVcidName(String vcidName) {
		this.vcidName = vcidName;
	}

	public int getTotalPerSec() {
		return totalPerSec;
	}

	public void setTotalPerSec(int totalPerSec) {
		this.totalPerSec = totalPerSec;
	}

	public long getStandBy() {
		return standBy;
	}

	public void setStandBy(long standBy) {
		this.standBy = standBy;
	}

	public int getAvailableCid() {
		return availableCid;
	}

	public void setAvailableCid(int availableCid) {
		this.availableCid = availableCid;
	}

	public int getNoneCid() {
		return noneCid;
	}

	public void setNoneCid(int noneCid) {
		this.noneCid = noneCid;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public int getMessageSubType() {
		return messageSubType;
	}

	public void setMessageSubType(int messageSubType) {
		this.messageSubType = messageSubType;
	}

	public int getTelcoinfo() {
		return telcoinfo;
	}

	public void setTelcoinfo(int telcoinfo) {
		this.telcoinfo = telcoinfo;
	}

	public Vector<VcidBean> getVcidBeansVec() {
		return vcidBeansVec;
	}

	public void setVcidBeansVec(Vector<VcidBean> vcidBeansVec) {
		this.vcidBeansVec = vcidBeansVec;
	}

	public String getSpID() {
		return spID;
	}

	public void setSpID(String spID) {
		this.spID = spID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessageTypeName() {
		return messageTypeName;
	}

	public void setMessageTypeName(String messageTypeName) {
		this.messageTypeName = messageTypeName;
	}

	public String getTelcoinfoName() {
		return telcoinfoName;
	}

	public void setTelcoinfoName(String telcoinfoName) {
		this.telcoinfoName = telcoinfoName;
	}

}
