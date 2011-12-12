package pms.beans.perf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import pms.db.DBStatement;

public class ApiBean implements DBStatement {
	
	private int seqNo;
	private String category = "";
	private String serverName = "";
	private String ip = "";
	
	private Vector<ApiBean> apiBeansVec = new Vector<ApiBean>();
	
	private int curConvertDateReq = 0;
	private int curDeleteMessageReq = 0;
	private int curFinishUploadReq = 0;
	private int curSendmsgReq = 0;
	private int curSessions = 0;
	
	private Date UPDATE_TIME;

	@Override
	public void excute(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		
		if(!getApiBeansVec().isEmpty()){
			getApiBeansVec().clear();
		}
		
		while(rs.next()){
			ApiBean bean = new ApiBean();
			
			bean.setSeqNo(rs.getInt("SEQNO"));
			bean.setCategory(rs.getString("CATEGORY"));
			bean.setServerName(rs.getString("SERVERNAME"));
			bean.setIp(rs.getString("IP"));
			
			bean.setCurConvertDateReq(rs.getInt("CURRENT_CONVERTDATA_REQUESTS"));		
			bean.setCurDeleteMessageReq(rs.getInt("CURRENT_DELETE_MESSAGE_REQUEST"));
			bean.setCurFinishUploadReq(rs.getInt("CURRENT_FINISHUPLOAD_REQUEST"));
			bean.setCurSendmsgReq(rs.getInt("CURRENT_SENDMSG_REQUEST"));
			bean.setCurSessions(rs.getInt("CURRENT_SESSIONS"));

			bean.setUPDATE_TIME(rs.getDate("UPDATE_TIME"));
			
			getApiBeansVec().add(bean);
		}
	}

	@Override
	public String getQuery() {
		// TODO Auto-generated method stub
		return "SELECT * from P_PERF_API where SEQNO in (SELECT max(SEQNO) FROM P_PERF_API group by IP) order by IP";
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

	public Vector<ApiBean> getApiBeansVec() {
		return apiBeansVec;
	}

	public void setApiBeansVec(Vector<ApiBean> apiBeansVec) {
		this.apiBeansVec = apiBeansVec;
	}

	public int getCurConvertDateReq() {
		return curConvertDateReq;
	}

	public void setCurConvertDateReq(int curConvertDateReq) {
		this.curConvertDateReq = curConvertDateReq;
	}

	public int getCurDeleteMessageReq() {
		return curDeleteMessageReq;
	}

	public void setCurDeleteMessageReq(int curDeleteMessageReq) {
		this.curDeleteMessageReq = curDeleteMessageReq;
	}

	public int getCurFinishUploadReq() {
		return curFinishUploadReq;
	}

	public void setCurFinishUploadReq(int curFinishUploadReq) {
		this.curFinishUploadReq = curFinishUploadReq;
	}

	public int getCurSendmsgReq() {
		return curSendmsgReq;
	}

	public void setCurSendmsgReq(int curSendmsgReq) {
		this.curSendmsgReq = curSendmsgReq;
	}

	public int getCurSessions() {
		return curSessions;
	}

	public void setCurSessions(int curSessions) {
		this.curSessions = curSessions;
	}

	public Date getUPDATE_TIME() {
		return UPDATE_TIME;
	}

	public void setUPDATE_TIME(Date uPDATE_TIME) {
		UPDATE_TIME = uPDATE_TIME;
	}


}
