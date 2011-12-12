package pms.beans.set;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pms.db.DBPreparedStatement;

public class ServerUpdateBean  extends ServerBean{
	
	@Override
	public void excute(ResultSet rs) throws SQLException {
	}

	@Override
	public String getQuery() {
		String query = "";
		if(seqNo >= 0){
			query = "update S_SERVER set NAME=?, TYPE=?, IP=?, OS=?, UPDATE_TIME=sysdate, CONTENT=? where SEQNO=?";			
		}else{
			query = "insert into S_SERVER (NAME, TYPE, IP, OS, CONTENT, SEQNO) values (?, ?, ?, ?, ?, seq_s_server.nextval)";			
		}

		return query;
	}

	@Override
	public boolean prepare(PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, name);
		pstmt.setString(2, type);
		pstmt.setString(3, ip);
		pstmt.setString(4, os);
		pstmt.setString(5, content);
		
		if(seqNo >= 0){
			pstmt.setInt(6, seqNo);
		}

		return true;
	}
}
