package pms.beans.set;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import pms.db.DBPreparedStatement;

public class ServerBean implements DBPreparedStatement  {
	
	protected int seqNo = -1;
	protected String name = "";
	protected String type = "";
	protected String ip = "";
	protected String os = "";
	protected Date date;
	protected String content = "";
	private String dateText = "";
	protected Vector<ServerBean> serverVec = new Vector<ServerBean>();
	
	@Override
	public void excute(ResultSet rs) throws SQLException {
		if(!serverVec.isEmpty()){
			serverVec.clear();
		}
		
		SimpleDateFormat SDF_SECOND = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		while(rs.next()){
			ServerBean bean = new ServerBean();
			bean.seqNo = rs.getInt("SEQNO");
			bean.name = rs.getString("NAME");
			bean.type = rs.getString("TYPE");
			bean.ip = rs.getString("IP");
			bean.os = rs.getString("OS");
			bean.date = rs.getTimestamp("UPDATE_TIME");
			bean.content = rs.getString("CONTENT");
			bean.setDateText(SDF_SECOND.format(bean.date));
			
			serverVec.add(bean);
		}
	}

	@Override
	public String getQuery() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public boolean prepare(PreparedStatement pstmt) throws SQLException {
		// TODO Auto-generated method stub
		return true;
	}
	

	
	public int getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Vector<ServerBean> getServerVec() {
		return serverVec;
	}
	public void setServerVec(Vector<ServerBean> serverVec) {
		this.serverVec = serverVec;
	}

	public String getDateText() {
		return dateText;
	}

	public void setDateText(String dateText) {
		this.dateText = dateText;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
