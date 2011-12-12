/**
 * 
 */
package pms.beans.perf;

import java.sql.*;
import pms.db.DBStatement;
import java.util.*;
import java.util.Date;

/**
 * @author jjm
 *
 */
public class MasBean implements DBStatement {
	private Vector<MasBean> masBeansVec = new Vector<MasBean>();
	
	private int seqNo;
	private String serverName = "";
	private String ip = "";
	private int AVR_RMPS		= 0;		//AverageRequestMessagesPerSec(초당 메시지처리 건수)
	private int CUR_PM		= 0;		//CurrentProcessingMessages(현재 발송 건수)
	private int CUR_MQ		= 0;		//CurrentMessageQueue(현재 발송 대기 건수)
	private int AVR_TMP		= 0;		//AverageTimeMessageProcessing(평균 발송 처리 시간)
	private int CUR_WRM		= 0;		//CurrentWaitingReponseMessage(현재 응답 대기건수)
	private int AVR_TMR		= 0;		//AverageTimeMessageResponse(평균 요청 응답시간)
	private int CUR_RQ			= 0;		//CurrentReportQueue(리포트 대기건수)
	private int CUR_AJ			= 0;		//CurrentAllocatingJobID(JobID 할당건수)
	private int CUR_NQ		= 0; 		//CurrentNPDBQueries(NPDB 조회수)
	private int CUR_PP			= 0;		//CurrentProcessingReport
	private int AVR_RVPS		= 0; 		//AverageRequestVMSsPerSec
	private int CUR_CR			= 0;		//CurrentConvertRequests
	private int AVR_RSPS		= 0;		//AverageRequestSMSsPerSec
	private int AVR_FPPS		= 0;		//AverageRequestFMSsPerSec
	private int AVR_RMMPS	= 0;		//AverageRequestMMSsPerSec
	private int AVR_TSM		= 0;		//AverageTimeSendMessage
	private int AVR_TMQ		= 0;		//AverageTimeMessageQueue
	private long SESSION_CNT	= 0;	//세션 수
	private Date UPDATE_TIME;
	

	@Override
	public void excute(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		if(!masBeansVec.isEmpty()){
			masBeansVec.clear();
		}

		while(rs.next()){
			MasBean bean = new MasBean();
			
			bean.seqNo 			= rs.getInt("SEQNO");
			bean.serverName 	= rs.getString("SERVERNAME");
			bean.ip 				= rs.getString("IP");
			bean.AVR_RMPS		= rs.getInt("AVR_RMPS");		
			bean.CUR_PM		= rs.getInt("CUR_PM");
			bean.CUR_MQ		= rs.getInt("CUR_MQ");
			bean.AVR_TMP		= rs.getInt("AVR_TMP");
			bean.CUR_WRM		= rs.getInt("CUR_WRM");
			bean.AVR_TMR		= rs.getInt("AVR_TMR");
			bean.CUR_RQ		= rs.getInt("CUR_RQ");
			bean.CUR_AJ			= rs.getInt("CUR_AJ");
			bean.CUR_NQ		= rs.getInt("CUR_NQ");
			bean.CUR_PP			= rs.getInt("CUR_PP");
			bean.AVR_RVPS		= rs.getInt("AVR_RVPS");
			bean.CUR_CR		= rs.getInt("CUR_CR");
			bean.AVR_RSPS		= rs.getInt("AVR_RSPS");
			bean.AVR_FPPS		= rs.getInt("AVR_FPPS");
			bean.AVR_RMMPS	= rs.getInt("AVR_RMMPS");
			bean.AVR_TSM		= rs.getInt("AVR_TSM");
			bean.AVR_TMQ		= rs.getInt("AVR_TMQ");
			bean.SESSION_CNT	= rs.getLong("SESSION_CNT");
			bean.UPDATE_TIME	= rs.getDate("UPDATE_TIME");
			
			masBeansVec.add(bean);
		}
	}

	@Override
	public String getQuery() {
		// TODO Auto-generated method stub
		return "SELECT * from P_PERF_MAS where SEQNO in (SELECT max(SEQNO) FROM P_PERF_MAS group by IP) order by IP";
	}
	
	public Vector<MasBean> getMasBeans(){
		return masBeansVec;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
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

	public int getAVR_RMPS() {
		return AVR_RMPS;
	}

	public void setAVR_RMPS(int aVR_RMPS) {
		AVR_RMPS = aVR_RMPS;
	}

	public int getCUR_PM() {
		return CUR_PM;
	}

	public void setCUR_PM(int cUR_PM) {
		CUR_PM = cUR_PM;
	}

	public int getCUR_MQ() {
		return CUR_MQ;
	}

	public void setCUR_MQ(int cUR_MQ) {
		CUR_MQ = cUR_MQ;
	}

	public int getAVR_TMP() {
		return AVR_TMP;
	}

	public void setAVR_TMP(int aVR_TMP) {
		AVR_TMP = aVR_TMP;
	}

	public int getCUR_WRM() {
		return CUR_WRM;
	}

	public void setCUR_WRM(int cUR_WRM) {
		CUR_WRM = cUR_WRM;
	}

	public int getAVR_TMR() {
		return AVR_TMR;
	}

	public void setAVR_TMR(int aVR_TMR) {
		AVR_TMR = aVR_TMR;
	}

	public int getCUR_RQ() {
		return CUR_RQ;
	}

	public void setCUR_RQ(int cUR_RQ) {
		CUR_RQ = cUR_RQ;
	}

	public int getCUR_AJ() {
		return CUR_AJ;
	}

	public void setCUR_AJ(int cUR_AJ) {
		CUR_AJ = cUR_AJ;
	}

	public int getCUR_NQ() {
		return CUR_NQ;
	}

	public void setCUR_NQ(int cUR_NQ) {
		CUR_NQ = cUR_NQ;
	}

	public int getCUR_PP() {
		return CUR_PP;
	}

	public void setCUR_PP(int cUR_PP) {
		CUR_PP = cUR_PP;
	}

	public int getAVR_RVPS() {
		return AVR_RVPS;
	}

	public void setAVR_RVPS(int aVR_RVPS) {
		AVR_RVPS = aVR_RVPS;
	}

	public int getCUR_CR() {
		return CUR_CR;
	}

	public void setCUR_CR(int cUR_CR) {
		CUR_CR = cUR_CR;
	}

	public int getAVR_RSPS() {
		return AVR_RSPS;
	}

	public void setAVR_RSPS(int aVR_RSPS) {
		AVR_RSPS = aVR_RSPS;
	}

	public int getAVR_FPPS() {
		return AVR_FPPS;
	}

	public void setAVR_FPPS(int aVR_FPPS) {
		AVR_FPPS = aVR_FPPS;
	}

	public int getAVR_RMMPS() {
		return AVR_RMMPS;
	}

	public void setAVR_RMMPS(int aVR_RMMPS) {
		AVR_RMMPS = aVR_RMMPS;
	}

	public int getAVR_TSM() {
		return AVR_TSM;
	}

	public void setAVR_TSM(int aVR_TSM) {
		AVR_TSM = aVR_TSM;
	}

	public int getAVR_TMQ() {
		return AVR_TMQ;
	}

	public void setAVR_TMQ(int aVR_TMQ) {
		AVR_TMQ = aVR_TMQ;
	}

	public long getSESSION_CNT() {
		return SESSION_CNT;
	}

	public void setSESSION_CNT(long sESSION_CNT) {
		SESSION_CNT = sESSION_CNT;
	}

	public Date getUPDATE_TIME() {
		return UPDATE_TIME;
	}

	public void setUPDATE_TIME(Date uPDATE_TIME) {
		UPDATE_TIME = uPDATE_TIME;
	}
}
