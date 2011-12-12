package pms.db;

import java.sql.*;

public interface DBStatement {
	public void excute(ResultSet rs) throws SQLException;
	public String getQuery();
}