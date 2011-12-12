package pms.db;
import java.sql.*;

public interface DBPreparedStatement extends DBStatement {
	public boolean prepare(PreparedStatement pstmt) throws SQLException;
}
