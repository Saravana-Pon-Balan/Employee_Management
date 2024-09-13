package employeeManagement.DBConnection;

import java.sql.*;

/**
 * <p>
 * Implementation of connect to database.
 * </p>
 */

public class DbConnection {
	private static Connection conn = null;
	private static String url = "jdbc:postgresql://localhost:5432/EmployeeManagement?user=postgres&password=admin";
	
	/**
 * <p>
 * It creates the connection to database if connection doesn't created yet.
 * </p>
 */
	public static Connection getInstance() throws SQLException {
		if(conn == null || conn.isClosed()) {
			conn = DriverManager.getConnection(url);
			return conn;
		} else {
			return conn;
		}
	}
}
/*
ANTLR

*/