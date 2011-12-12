package pms.db;

import java.security.*;
import java.sql.*;

import javax.naming.*;
import javax.sql.*;


public class DBManager {
	private DBManager(){}
	
	public static boolean excute(DBStatement query, String... params){
		boolean result = false;
		String context = "jdbc/pms1";
		
		if(params.length >= 1){
			context = params[0];
		}
		
		try{
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup(context);
			
			try{
				conn = ds.getConnection();
				conn.setAutoCommit(false);

				stmt = conn.createStatement();
				rs = stmt.executeQuery(query.getQuery());
				
				query.excute(rs);
				
				result = true;
			}catch(SQLException e){
				e.printStackTrace();
				if(conn != null){try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}}
			}finally{
				if(rs != null) try{rs.close();}catch(Exception e){e.printStackTrace();}
				if(stmt != null) try{stmt.close();}catch(Exception e){e.printStackTrace();}
				if(conn != null){
					try {
						conn.commit();
						conn.close();
					} catch (SQLException e1){
						e1.printStackTrace();
					}
				}
			}
		}catch(NamingException e){
			return false;
		}
		
		return result;		
	}
	
	
	public static boolean excute(DBPreparedStatement query, String... params){
		boolean result = false;
		String context = "jdbc/pms1";
		
		if(params.length >= 1){
			context = params[0];
		}
		
		try{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup(context);
			
			try{
				conn = ds.getConnection();
				conn.setAutoCommit(false);

				pstmt = conn.prepareStatement(query.getQuery());
				query.prepare(pstmt);
				rs = pstmt.executeQuery();
				
				query.excute(rs);
				
				result = true;
			}catch(SQLException e){
				e.printStackTrace();
				if(conn != null){try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}}
			}finally{
				if(rs != null) try{rs.close();}catch(Exception e){e.printStackTrace();}
				if(pstmt != null) try{pstmt.close();}catch(Exception e){e.printStackTrace();}
				if(conn != null){
					try {
						conn.commit();
						conn.close();
					} catch (SQLException e1){
						e1.printStackTrace();
					}
				}
			}
		}catch(NamingException e){
			return false;
		}
		
		return result;		
	}

	//WU 클래스 참고
	public static String WwStringCryptEither(String str, String method) throws NoSuchAlgorithmException { 
		String EncodeString = "";

		// MessageDigest 인스턴스 생성
		MessageDigest md = MessageDigest.getInstance(method); // "MD5", "SHA-1"

		byte[] byteString = str.getBytes();

		// 해쉬값 update
		md.update(byteString);

		// 해쉬값(다이제스트) 얻기
		byte[] digest = md.digest();
		String tmp = "";

		for( int i = 0; i < digest.length; i++ ) {
			tmp = Integer.toHexString(digest[i] & 0xFF);
		
			if( tmp.length() < 2 ) {
				tmp = "0" + tmp;
			}
			EncodeString = EncodeString + tmp;
		}

		return EncodeString;
	}
}
