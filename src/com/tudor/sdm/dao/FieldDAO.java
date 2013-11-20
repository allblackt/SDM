package com.tudor.sdm.dao;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.tudor.sdm.entity.SportsPass;

public class FieldDAO extends DAO<SportsPass, Long> {

	private static FieldDAO instance = null;
	
	private FieldDAO(Class<SportsPass> clazz) {
		super(clazz);
	}
	
	public static FieldDAO get(){
		if(instance == null) {
			instance = new FieldDAO(SportsPass.class);
		}
		return instance;
	}
	

}
