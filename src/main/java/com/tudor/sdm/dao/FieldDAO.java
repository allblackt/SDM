package com.tudor.sdm.dao;

import com.tudor.sdm.entity.Field;

public class FieldDAO extends DAO<Field, Long> {

	private static FieldDAO instance = null;
	
	private FieldDAO(Class<Field> clazz) {
		super(clazz);
	}
	
	public static FieldDAO get(){
		if(instance == null) {
			instance = new FieldDAO(Field.class);
		}
		return instance;
	}
	

}
