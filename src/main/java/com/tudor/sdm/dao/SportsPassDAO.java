package com.tudor.sdm.dao;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.tudor.sdm.entity.SportsPass;

public class SportsPassDAO extends DAO<SportsPass, Long> {

	private static SportsPassDAO instance = null;
	
	private SportsPassDAO(Class<SportsPass> clazz) {
		super(clazz);
	}
	
	public static SportsPassDAO get(){
		if(instance == null) {
			instance = new SportsPassDAO(SportsPass.class);
		}
		return instance;
	}
	

}
