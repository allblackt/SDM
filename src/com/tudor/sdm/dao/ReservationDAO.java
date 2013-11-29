package com.tudor.sdm.dao;

import com.tudor.sdm.entity.Reservation;

public class ReservationDAO extends DAO<Reservation, Long> {

	private static ReservationDAO instance = null;
	
	protected ReservationDAO(Class<Reservation> clazz) {
		super(clazz);
	}
	
	public static ReservationDAO get() {
		if (instance == null) {
			instance = new ReservationDAO(Reservation.class);
		}
		return instance;
	}

}
