package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection con = null;

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException("Erro: " + e.getMessage());
		}
	}

	// Connect with DB
	public static Connection getConnection() {

		if (con == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				con = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new DbException("ERROR: " + e.getMessage());
			}
		}
		return con;
	}

	public static void closeConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DbException("ERROR: " + e.getMessage());
			}
		}
	}

	public static void closeConnection(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DbException("ERROR: " + e.getMessage());
			}
		}
	}

	public static void closeConnection(Statement stmt, ResultSet rs) {
		if (rs != null && stmt != null) {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				throw new DbException("ERROR: " + e.getMessage());
			}
		}
	}

}
