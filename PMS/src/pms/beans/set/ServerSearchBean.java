package pms.beans.set;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ServerSearchBean extends ServerBean{

/*	
	@Override
	public void excute(ResultSet rs) throws SQLException {
	}
*/

	public String getQuery() {
		String query = "select * from S_SERVER where 1=1";
		
		if(!type.isEmpty()){
			query += " and TYPE like ?";
		}
		
		if(!name.isEmpty()){
			query += " and NAME like ?";
		}
		
		if(seqNo >= 0){
			query += " and SEQNO = ?";
		}

		return query;
	}


	public boolean prepare(PreparedStatement pstmt) throws SQLException {
		int index = 1;
		
		if(!type.isEmpty()){
			pstmt.setString(index, "%" + type + "%");
			index++;
		}
		
		if(!name.isEmpty()){
			pstmt.setString(index, "%" + name + "%");
			index++;
		}
		
		if(seqNo >= 0){
			pstmt.setInt(index, seqNo);
		}
		
		return true;
	}
}
