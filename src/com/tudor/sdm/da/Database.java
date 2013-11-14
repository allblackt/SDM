package com.tudor.sdm.da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.common.base.Joiner;

public class Database {

	private static final Logger log = Logger
			.getLogger(Database.class.getName());

	private Connection conn;
	private String connectionString = "jdbc:h2://"
			+ System.getProperty("user.dir") + "/db/SDM";

	public Database() throws SQLException {
		log.debug("Constructor called");
		DriverManager.registerDriver(org.h2.Driver.load());
	}

	public void open() throws SQLException, ClassNotFoundException {
		log.debug("Attempting to load database from " + connectionString);
		conn = DriverManager.getConnection(connectionString);
	}

	public void close() throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}
	
	private void checkConnection() throws SQLException, IllegalStateException {
		if (conn == null || conn.isClosed()) {
			throw new IllegalStateException();
		}
	}

	/** Runs a query against the database and returns the first column of the first row. Typically used for COUNT or similar purpose queries. 
	 * @param query
	 * SQL query to execute.
	 * @return
	 * The value of the first column of the first row, as a string.
	 * @throws IllegalStateException
	 * When the connection is not open or improperly initialized.
	 * @throws SQLException
	 * When SQL Exceptions occur.
	 */
	public String executeScalar(String query) throws IllegalStateException, SQLException {
		checkConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		rs.next();
		String result = rs.getString(0);
		rs.close();
		return result;
	}

	public int Insert(String table, Map<String, String> data) throws IllegalStateException, SQLException {
		checkConnection();
		Statement stmt = conn.createStatement();
		
		String columns = Joiner.on(",").join(data.keySet());
		String[] valuePH = new String[data.size()];
		Arrays.fill(valuePH, "?");
		String sql = String.format("INSERT INTO %s(%s) VALUES (%s)", table, columns, Joiner.on(",").join(valuePH));
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < data.size(); i++) {
			pstmt.setString(i +1, data.values().iterator().next());
		}
		
		
		return 0;
	}

}
