package com.tudor.sdm.dao;

import com.tudor.sdm.entity.SportsSession;

public class SportsSessionDAO extends DAO<SportsSession, Long> {

	private static SportsSessionDAO instance = null;
	
	private SportsSessionDAO(Class<SportsSession> clazz) {
		super(clazz);
	}
	
	public static SportsSessionDAO get(){
		if(instance == null) {
			instance = new SportsSessionDAO(SportsSession.class);
		}
		return instance;
	}
}
