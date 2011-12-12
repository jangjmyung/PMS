package pms.beans;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

import pms.db.DBManager;
import pms.db.DBPreparedStatement;

public class UserBean implements DBPreparedStatement {
	public UserBean(String id, String pwd){
		try {
			if(id != null && !id.isEmpty()) this.id = id;
			if(pwd != null && !pwd.isEmpty()) this.pwd = DBManager.WwStringCryptEither(pwd, "SHA-1");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int 	 auth = 0;
	private String id ="";
	private String pwd = "";
	private String name = "";
	private boolean validation = false;
	
	public int getAuth(){
		return auth;
	}
	
	public void setAuth(int auth){
		this.auth = auth;
	}
	
	public String getId(){
		return id;
	}
	
	public void SetId(String id){
		this.id = id;
	}
	
	public String getPwd(){
		return pwd;
	}
	
	public void setPwd(String pwd){
		try {
			this.pwd = DBManager.WwStringCryptEither(pwd, "SHA-1");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public boolean getValidation(){
		return validation;
	}
	
	public void SetValidation(boolean validation){
		this.validation = validation;
	}
	
	@Override
	public void excute(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		while( rs.next() ) {
			validation = true;
			name = rs.getString("NAME");
			auth = rs.getInt("AUTH");
			break;
		}
	}

	@Override
	public String getQuery() {
		// TODO Auto-generated method stub
		return "select * from P_USER where id=? AND pwd=?";
	}

	@Override
	public boolean prepare(PreparedStatement pstmt) throws SQLException {
		// TODO Auto-generated method stub
		pstmt.setString(1, id);
		pstmt.setString(2, pwd);
		
		return false;
	}
}
