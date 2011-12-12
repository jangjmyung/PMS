package pms.beans.perf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import pms.db.DBStatement;

public class RcsBean implements DBStatement {
	
	private int seqNo;
	private String category = "";
	private String serverName = "";
	private String ip = "";
	
	private int provConCnt = 0;		//PROVIDER_CONNECTION_COUNT
	private int provCont = 0;			//PROVIDER_COUNTER
	private int consConCnt = 0;		//CONSUMER_CONNECTION_COUNT
	private int consCnt = 0;			//CONSUMER_COUNTER
	private int reqPerSec = 0;			//REQUEST_PER_SECOND
	private Date UPDATE_TIME;
	
	private Vector<RcsBean> rcsBeansVec = new Vector<RcsBean>();

	@Override
	public void excute(ResultSet rs) throws SQLException {
		if(!getRcsBeansVec().isEmpty()){
			getRcsBeansVec().clear();
		}
		
		while(rs.next()){
			RcsBean bean = new RcsBean();
			
			bean.setSeqNo(rs.getInt("SEQNO"));
			bean.setCategory(rs.getString("CATEGORY"));
			bean.setServerName(rs.getString("SERVERNAME"));
			bean.setIp(rs.getString("IP"));
			
			bean.setProvConCnt(rs.getInt("PROVIDER_CONNECTION_COUNT"));		
			bean.setProvCont(rs.getInt("PROVIDER_COUNTER"));
			bean.setConsConCnt(rs.getInt("CONSUMER_CONNECTION_COUNT"));
			bean.setConsCnt(rs.getInt("CONSUMER_COUNTER"));
			bean.setReqPerSec(rs.getInt("REQUEST_PER_SECOND"));
			bean.setUPDATE_TIME(rs.getDate("UPDATE_TIME"));
			
			getRcsBeansVec().add(bean);
		}
	}

	@Override
	public String getQuery() {
		return "SELECT * from P_PERF_RCS where SEQNO in (SELECT max(SEQNO) FROM P_PERF_RCS group by IP) order by IP";
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

	public int getProvConCnt() {
		return provConCnt;
	}

	public void setProvConCnt(int provConCnt) {
		this.provConCnt = provConCnt;
	}

	public int getProvCont() {
		return provCont;
	}

	public void setProvCont(int provCont) {
		this.provCont = provCont;
	}

	public int getConsConCnt() {
		return consConCnt;
	}

	public void setConsConCnt(int consConCnt) {
		this.consConCnt = consConCnt;
	}

	public int getConsCnt() {
		return consCnt;
	}

	public void setConsCnt(int consCnt) {
		this.consCnt = consCnt;
	}

	public int getReqPerSec() {
		return reqPerSec;
	}

	public void setReqPerSec(int reqPerSec) {
		this.reqPerSec = reqPerSec;
	}

	public Date getUPDATE_TIME() {
		return UPDATE_TIME;
	}

	public void setUPDATE_TIME(Date uPDATE_TIME) {
		UPDATE_TIME = uPDATE_TIME;
	}

	public Vector<RcsBean> getRcsBeansVec() {
		return rcsBeansVec;
	}
}
