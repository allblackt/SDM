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
	private static boolean isTest = false;
	
	private EMF() {
		Map<String, String> properties = new HashMap<String, String>();
		
		if(isTest) {
			log.debug("Setting up the connection for Unit testing...");
			properties.put("hibernate.hbm2ddl.auto","create-drop");
			connectionString = String.format("jdbc:h2:mem:test");
		} else {
			
		}
		properties.put("hibernate.connection.url", connectionString);
		
		
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
		log.debug("Flagging as test instance....");
		if (instance == null) {
			isTest = true;
			instance = new EMF();
		}
		return emf;
	}
}
