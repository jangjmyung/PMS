package pms.beans.service;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import pms.db.DBStatement;
import java.util.*;

public class SessionBean {
	
	 protected long sessionID = 0;
	 protected String spID = "";
	 protected String euID = "";
	 protected String clientIP = "";
	 protected String masID = "";
	 protected Date conTime;
	 protected String masIP = "";
	 protected String clientVer = "";
	 protected String csdkVer = "";
	 
	 
	 
	public long getSessionID() {
		return sessionID;
	}

	public void setSessionID(long sessionID) {
		this.sessionID = sessionID;
	}

	public String getSpID() {
		return spID;
	}

	public void setSpID(String spID) {
		this.spID = spID;
	}

	public String getEuID() {
		return euID;
	}

	public void setEuID(String euID) {
		this.euID = euID;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	public String getMasID() {
		return masID;
	}

	public void setMasID(String masID) {
		this.masID = masID;
	}

	public Date getConTime() {
		return conTime;
	}

	public void setConTime(Date conTime) {
		this.conTime = conTime;
	}

	public String getMasIP() {
		return masIP;
	}

	public void setMasIP(String masIP) {
		this.masIP = masIP;
	}

	public String getClientVer() {
		return clientVer;
	}

	public void setClientVer(String clientVer) {
		this.clientVer = clientVer;
	}

	public String getCsdkVer() {
		return csdkVer;
	}

	public void setCsdkVer(String sdkVer) {
		this.csdkVer = sdkVer;
	}
}
