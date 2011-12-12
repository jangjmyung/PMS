package pms.beans.perf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import pms.db.DBStatement;

public class ScsBean implements DBStatement {
	
	private int seqNo;
	private String category = "";
	private String serverName = "";
	private String ip = "";
	
	private int curRequests = 0;			//CURRENT_REQUESTS
	private int queuedReports = 0;		//QUEUED_REPORTS
	private int reqPerSec = 0;				//REQUEST_PER_SECOND
	private int reportPerSec = 0; 		//REPORT_PER_SECOND
	
	private Date UPDATE_TIME;
	private Vector<ScsBean> scsBeansVec = new Vector<ScsBean>();

	@Override
	public void excute(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		
		if(!getScsBeansVec().isEmpty()){
			getScsBeansVec().clear();
		}
		
		while(rs.next()){
			ScsBean bean = new ScsBean();
			
			bean.setSeqNo(rs.getInt("SEQNO"));
			bean.setCategory(rs.getString("CATEGORY"));
			bean.setServerName(rs.getString("SERVERNAME"));
			bean.setIp(rs.getString("IP"));
			
			bean.setCurRequests(rs.getInt("CURRENT_REQUESTS"));		
			bean.setQueuedReports(rs.getInt("QUEUED_REPORTS"));
			bean.setReqPerSec(rs.getInt("REQUEST_PER_SECOND"));
			bean.setReportPerSec(rs.getInt("REPORT_PER_SECOND"));
			bean.setUPDATE_TIME(rs.getDate("UPDATE_TIME"));
			
			getScsBeansVec().add(bean);
		}
	}

	@Override
	public String getQuery() {
		// TODO Auto-generated method stub
		return "SELECT * from P_PERF_SCS where SEQNO in (SELECT max(SEQNO) FROM P_PERF_SCS group by IP) order by IP";
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

	public int getCurRequests() {
		return curRequests;
	}

	public void setCurRequests(int curRequests) {
		this.curRequests = curRequests;
	}

	public int getQueuedReports() {
		return queuedReports;
	}

	public void setQueuedReports(int queuedReports) {
		this.queuedReports = queuedReports;
	}

	public int getReqPerSec() {
		return reqPerSec;
	}

	public void setReqPerSec(int reqPerSec) {
		this.reqPerSec = reqPerSec;
	}

	public int getReportPerSec() {
		return reportPerSec;
	}

	public void setReportPerSec(int reportPerSec) {
		this.reportPerSec = reportPerSec;
	}

	public Date getUPDATE_TIME() {
		return UPDATE_TIME;
	}

	public void setUPDATE_TIME(Date uPDATE_TIME) {
		UPDATE_TIME = uPDATE_TIME;
	}

	public Vector<ScsBean> getScsBeansVec() {
		return scsBeansVec;
	}
}
