package com.tudor.sdm.dao;

import com.tudor.sdm.entity.Client;

public class ClientDAO extends DAO<Client, Long> {
	
	private ClientDAO(Class<Client> clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}

	private static ClientDAO instance = null;
	
	public static ClientDAO get() {
		if(instance == null) {
			instance = new ClientDAO(Client.class);
		}
		return instance;
	}

}
