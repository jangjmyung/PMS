package pms.beans.perf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import pms.db.DBStatement;

public class FusBean implements DBStatement {

	private int seqNo;
	private String category = "";
	private String serverName = "";
	private String ip = "";
	private Date UPDATE_TIME;
	
	
	
	private Vector<FusBean> fusBeansVec = new Vector<FusBean>();
	private int downloadPerSec = 0;
	private int curDownRequest = 0;
	private int curUploadRequest = 0;
	private int uploadPerSec = 0;

	@Override
	public void excute(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		
		if(!getFusBeansVec().isEmpty()){
			getFusBeansVec().clear();
		}
		
		while(rs.next()){
			FusBean bean = new FusBean();
			
			bean.setSeqNo(rs.getInt("SEQNO"));
			bean.setCategory(rs.getString("CATEGORY"));
			bean.setServerName(rs.getString("SERVERNAME"));
			bean.setIp(rs.getString("IP"));
			bean.setDownloadPerSec(rs.getInt("DOWNLOAD_PER_SECOND"));
			bean.setCurDownRequest(rs.getInt("CURRENT_DOWNLOAD_REQUESTS"));
			bean.setCurUploadRequest(rs.getInt("CURRENT_UPLOAD_REQUESTS"));
			bean.setUploadPerSec(rs.getInt("UPLOAD_PER_SECOND"));
						
			getFusBeansVec().add(bean);
		}
	}

	@Override
	public String getQuery() {
		// TODO Auto-generated method stub
		return "SELECT * from P_PERF_FUS where SEQNO in (SELECT max(SEQNO) FROM P_PERF_FUS group by IP) order by IP";
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

	public Date getUPDATE_TIME() {
		return UPDATE_TIME;
	}

	public void setUPDATE_TIME(Date uPDATE_TIME) {
		UPDATE_TIME = uPDATE_TIME;
	}

	public Vector<FusBean> getFusBeansVec() {
		return fusBeansVec;
	}

	public int getDownloadPerSec() {
		return downloadPerSec;
	}

	public void setDownloadPerSec(int downloadPerSec) {
		this.downloadPerSec = downloadPerSec;
	}

	public int getCurDownRequest() {
		return curDownRequest;
	}

	public void setCurDownRequest(int curDownRequest) {
		this.curDownRequest = curDownRequest;
	}

	public int getCurUploadRequest() {
		return curUploadRequest;
	}

	public void setCurUploadRequest(int curUploadRequest) {
		this.curUploadRequest = curUploadRequest;
	}

	public int getUploadPerSec() {
		return uploadPerSec;
	}

	public void setUploadPerSec(int uploadPerSec) {
		this.uploadPerSec = uploadPerSec;
	}
}
