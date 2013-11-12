package com.tudor.sdm.da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private Connection conn;
	// TODO configurability for connection string
	private String connectionString = "jdbc:h2://e:/Tudor/Proiecte/Workspace/SDM/db/SDM";
	private static final Database instance = new Database();
	
	private Database(){
		
	}
	
	public void open() throws SQLException, ClassNotFoundException {
//		Class.forName(org.h2.Driver.class.getName());
		DriverManager.registerDriver(org.h2.Driver.load());
		conn = DriverManager.getConnection(connectionString);
	}
}
