package com.tudor.sdm;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
	private static EntityManagerFactory emf ;
	private static EMF instance = new EMF();
	
	private EMF() {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("hibernate.connection.url", "jdbc:h2://" + System.getProperty("user.dir") + "/db/SDM");
		emf = Persistence.createEntityManagerFactory("sdm-ds", properties);
	}

	public static EntityManagerFactory get() {
		return emf;
	}
}
