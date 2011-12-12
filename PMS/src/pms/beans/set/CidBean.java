package pms.beans.set;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pms.db.DBPreparedStatement;
import java.util.*;

public class CidBean implements DBPreparedStatement {
	
	private int vcid = 0;
	private String vcidName = "";
	private String cidName = "";
	private String cidSection = "";
	private int msgType = 0;
	private int msgSubType = 0;
	private int smppType = 0;
	private String mrsServer = "";
	private int mrsPort = 0;
	private String vaspID = "";
	private String vasID = "";
	private String billID = "";
	private String bindID = "";
	private String bindPWD = "";
	private String npdbSID = "";
	private String svrIP = "";
	private int linePort = 0;
	private int outTrafficMax = 0;
	private int reportPort = 0;
	private int status = 0;
	
	private long priority1 = Long.parseLong("4294967295");			//10 : 실시간우선대기
	private long priority2 = Long.parseLong("4294967295");			//100 : 실시간일반대기
	private long priority3 = Long.parseLong("4294967295");			//1000 : 광고우선대기
	private long priority4 = Long.parseLong("4294967295");			//2000 : 광고일반대기
	
	private Vector<CidBean> cidBeansVec = new Vector<CidBean>();
	
	//합계
	private int sumOutTrafficMax = 0;	

	@Override
	public void excute(ResultSet rs) throws SQLException {
		if(!getCidBeansVec().isEmpty()){
			getCidBeansVec().clear();
		}
		
		while(rs.next()){
			CidBean bean = new CidBean();
			bean.vcid = this.vcid;
			bean.cidName = rs.getString("CID_NAME");
			bean.cidSection = rs.getString("CID_SECTION");
			bean.msgType = rs.getInt("MSG_TYPE");
			bean.msgSubType = rs.getInt("MSG_SUB_TYPE");
			bean.smppType = rs.getInt("SMPP_TYPE");
			bean.mrsServer = rs.getString("MRS_SERVER");
			bean.mrsPort = rs.getInt("LISTENING_PORT");
			bean.vaspID = rs.getString("VASP_ID");
			bean.vasID = rs.getString("VAS_ID");
			bean.billID = rs.getString("BILL_ID");
			bean.bindID = rs.getString("BIND_ID");
			bean.bindPWD = rs.getString("BIND_PW");
			bean.npdbSID = rs.getString("NPDB_SID");
			bean.svrIP = rs.getString("SVR_IP");
			bean.linePort = rs.getInt("LINE_PORT");
			bean.outTrafficMax = rs.getInt("OUT_TRAFFIC_MAX");
			bean.reportPort = rs.getInt("REPORT_PORT");
			
			setSumOutTrafficMax(getSumOutTrafficMax() + bean.outTrafficMax); 
			
			getCidBeansVec().add(bean);
		}
	}

	@Override
	public String getQuery() {
		return "select b.*  from M_VCID_CID a, M_CID_INFO b where a.CID_NAME = b.CID_NAME and a.VCID = ? order by CID_NAME";
	}

	@Override
	public boolean prepare(PreparedStatement pstmt) throws SQLException {
		pstmt.setInt(1, vcid);

		return true;
	}

	public int getVcid() {
		return vcid;
	}

	public void setVcid(int vcid) {
		this.vcid = vcid;
	}

	public String getCidName() {
		return cidName;
	}

	public void setCidName(String cidName) {
		this.cidName = cidName;
	}

	public String getCidSection() {
		return cidSection;
	}

	public void setCidSection(String cidSection) {
		this.cidSection = cidSection;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public int getMsgSubType() {
		return msgSubType;
	}

	public void setMsgSubType(int msgSubType) {
		this.msgSubType = msgSubType;
	}

	public int getSmppType() {
		return smppType;
	}

	public void setSmppType(int smppType) {
		this.smppType = smppType;
	}

	public String getMrsServer() {
		return mrsServer;
	}

	public void setMrsServer(String mrsServer) {
		this.mrsServer = mrsServer;
	}

	public int getMrsPort() {
		return mrsPort;
	}

	public void setMrsPort(int mrsPort) {
		this.mrsPort = mrsPort;
	}

	public String getVaspID() {
		return vaspID;
	}

	public void setVaspID(String vaspID) {
		this.vaspID = vaspID;
	}

	public String getVasID() {
		return vasID;
	}

	public void setVasID(String vasID) {
		this.vasID = vasID;
	}

	public String getBillID() {
		return billID;
	}

	public void setBillID(String billID) {
		this.billID = billID;
	}

	public String getBindID() {
		return bindID;
	}

	public void setBindID(String bindID) {
		this.bindID = bindID;
	}

	public String getBindPWD() {
		return bindPWD;
	}

	public void setBindPWD(String bindPWD) {
		this.bindPWD = bindPWD;
	}

	public String getNpdbSID() {
		return npdbSID;
	}

	public void setNpdbSID(String npdbSID) {
		this.npdbSID = npdbSID;
	}

	public String getSvrIP() {
		return svrIP;
	}

	public void setSvrIP(String svrIP) {
		this.svrIP = svrIP;
	}

	public int getLinePort() {
		return linePort;
	}

	public void setLinePort(int linePort) {
		this.linePort = linePort;
	}

	public int getOutTrafficMax() {
		return outTrafficMax;
	}

	public void setOutTrafficMax(int outTrafficMax) {
		this.outTrafficMax = outTrafficMax;
	}

	public int getReportPort() {
		return reportPort;
	}

	public void setReportPort(int reportPort) {
		this.reportPort = reportPort;
	}

	public int getSumOutTrafficMax() {
		return sumOutTrafficMax;
	}

	public void setSumOutTrafficMax(int sumOutTrafficMax) {
		this.sumOutTrafficMax = sumOutTrafficMax;
	}

	public String getVcidName() {
		return vcidName;
	}

	public void setVcidName(String vcidName) {
		this.vcidName = vcidName;
	}

	public long getPriority1() {
		return priority1;
	}

	public void setPriority1(long priority1) {
		this.priority1 = priority1;
	}

	public long getPriority2() {
		return priority2;
	}

	public void setPriority2(long priority2) {
		this.priority2 = priority2;
	}

	public long getPriority3() {
		return priority3;
	}

	public void setPriority3(long priority3) {
		this.priority3 = priority3;
	}

	public long getPriority4() {
		return priority4;
	}

	public void setPriority4(long priority4) {
		this.priority4 = priority4;
	}

	public Vector<CidBean> getCidBeansVec() {
		return cidBeansVec;
	}

	public void setCidBeansVec(Vector<CidBean> cidBeansVec) {
		this.cidBeansVec = cidBeansVec;
	}

}
