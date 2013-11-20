package com.tudor.sdm.dao;

import com.tudor.sdm.entity.SportsSession;

public class SportsPassDAO extends DAO<SportsSession, Long> {

	private static SportsPassDAO instance = null;
	
	private SportsPassDAO(Class<SportsSession> clazz) {
		super(clazz);
	}
	
	public static SportsPassDAO get(){
		if(instance == null) {
			instance = new SportsPassDAO(SportsSession.class);
		}
		return instance;
	}
}
