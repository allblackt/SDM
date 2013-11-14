package com.tudor.sdm.da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Database {
	
	private static final Logger log = Logger.getLogger(Database.class.getName());
	
	private Connection conn;
	private String connectionString = "jdbc:h2://" + System.getProperty("user.dir") + "/db/SDM";
	
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
}
