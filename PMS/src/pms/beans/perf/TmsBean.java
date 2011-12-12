package pms.beans.perf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import pms.db.DBStatement;

public class TmsBean implements DBStatement {
	
	private int seqNo;
	private String category = "";
	private String serverName = "";
	private String ip = "";
	
	private int vmsTotalCall = 0;
	private int vmsProcessingCall = 0;
	private int vmsQueue = 0;
	private int fmsTotalCall = 0;
	private int fmsProcessingCall = 0;
	private int fmsQueue = 0;
	
	private Date UPDATE_TIME;
	private Vector<TmsBean> tmsBeansVec = new Vector<TmsBean>();

	@Override
	public void excute(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		if(!getTmsBeansVec().isEmpty()){
			getTmsBeansVec().clear();
		}
		
		while(rs.next()){
			TmsBean bean = new TmsBean();
			
			bean.setSeqNo(rs.getInt("SEQNO"));
			bean.setCategory(rs.getString("CATEGORY"));
			bean.setServerName(rs.getString("SERVERNAME"));
			bean.setIp(rs.getString("IP"));
			
			bean.setVmsTotalCall(rs.getInt("VMS_TOTAL_CALL"));
			bean.setVmsProcessingCall(rs.getInt("VMS_PROCESSING_CALL"));
			bean.setVmsQueue(rs.getInt("VMS_QUEUE"));
			bean.setFmsTotalCall(rs.getInt("FMS_TOTAL_CALL"));
			bean.setFmsProcessingCall(rs.getInt("FMS_PROCESSING_CALL"));
			bean.setFmsQueue(rs.getInt("FMS_QUEUE"));
			
			getTmsBeansVec().add(bean);
		}
	}

	@Override
	public String getQuery() {
		// TODO Auto-generated method stub
		return "SELECT * from P_PERF_TMS where SEQNO in (SELECT max(SEQNO) FROM P_PERF_TMS group by IP) order by IP";
	}
	
	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getVmsTotalCall() {
		return vmsTotalCall;
	}

	public void setVmsTotalCall(int vmsTotalCall) {
		this.vmsTotalCall = vmsTotalCall;
	}

	public int getVmsProcessingCall() {
		return vmsProcessingCall;
	}

	public void setVmsProcessingCall(int vmsProcessingCall) {
		this.vmsProcessingCall = vmsProcessingCall;
	}

	public int getVmsQueue() {
		return vmsQueue;
	}

	public void setVmsQueue(int vmsQueue) {
		this.vmsQueue = vmsQueue;
	}

	public int getFmsTotalCall() {
		return fmsTotalCall;
	}

	public void setFmsTotalCall(int fmsTotalCall) {
		this.fmsTotalCall = fmsTotalCall;
	}

	public int getFmsProcessingCall() {
		return fmsProcessingCall;
	}

	public void setFmsProcessingCall(int fmsProcessingCall) {
		this.fmsProcessingCall = fmsProcessingCall;
	}

	public int getFmsQueue() {
		return fmsQueue;
	}

	public void setFmsQueue(int fmsQueue) {
		this.fmsQueue = fmsQueue;
	}

	public Date getUPDATE_TIME() {
		return UPDATE_TIME;
	}

	public void setUPDATE_TIME(Date uPDATE_TIME) {
		UPDATE_TIME = uPDATE_TIME;
	}

	public Vector<TmsBean> getTmsBeansVec() {
		return tmsBeansVec;
	}

	public void setTmsBeansVec(Vector<TmsBean> tmsBeansVec) {
		this.tmsBeansVec = tmsBeansVec;
	}

}
