package com.tudor.sdm.test.db;

import com.tudor.sdm.entity.SportsSession;
import org.testng.annotations.DataProvider;

public class SportsSessionDataProvider {

	@DataProvider(name="getTwoSportsSession")
	public static Object[][] getSingleSportsSession() {
		return new Object[][] {
			{ new SportsSession.SportsSessionBuilder().name("Tennis 1hr session").price(100L).duration(50).build() },
            { new SportsSession.SportsSessionBuilder().name("Tennis 2hr session").price(200L).duration(99).build() }
		};
	}
	
}
