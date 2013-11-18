package com.tudor.sdm.test.db;

import java.util.Date;

import org.testng.annotations.DataProvider;

import com.tudor.sdm.entity.Client;
import com.tudor.sdm.entity.SportsSession;

public class SportsSessionDataProvider {

	@DataProvider(name="getSingleSportsSession")
	public static Object[][] getSingleSportsSession() {
		return new Object[][] {
			{
				new SportsSession.SportsSessionBuilder().name("Tennis 1hr session").cost(100L).startTime(new Date(1384765200000L)).endTime(new Date(1384768800000L))
				.client( (Client)(ClientDataProvider.getOneClient()[0][0] )).build()
			}
		};
	}
	
}
