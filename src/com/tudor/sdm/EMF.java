package com.tudor.sdm;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

public class EMF {
	private static Logger log = Logger.getLogger(EMF.class.getName());
	private static EntityManagerFactory emf = null;
	private static EMF instance = null;
	private static String connectionString;
	private static String importFiles;
	
	private EMF() {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("hibernate.connection.url", connectionString);
		
		if(importFiles != null) {
			log.debug("Setting import_files property...");
			properties.put("hibernate.hbm2ddl.import_files", importFiles);
		}
		
		emf = Persistence.createEntityManagerFactory("sdm-ds", properties);
	}

	public static EntityManagerFactory get() {
		if (instance == null) {
			connectionString = "jdbc:h2://" + System.getProperty("user.dir") + "/db/SDM";
			instance = new EMF();
		}
		return emf;
	}
	
	public static EntityManagerFactory getTestInstance() {
		log.debug("Creating the test instance....");
		if (instance == null) {
			String createTablesScriptLocation = System.getProperty("user.dir") + "sql_scripts_unit_testing/create_tables.sql";
			String insertDataScriptLocation = System.getProperty("user.dir") + "sql_scripts_unit_testing/insert_data.sql";
			//importFiles = String.format("%s,%s", "/create_tables.sql", "/insert_data.sql");
			connectionString = String.format("jdbc:h2:mem:test");
			log.debug("Connection string for test instance is: " + connectionString);
			instance = new EMF();
		}
		return emf;
	}
}
