package com.tudor.sdm.test.db;

import org.testng.annotations.DataProvider;

import com.tudor.sdm.entity.Client;

public class ClientDataProvider {

	@DataProvider(name="oneClient")
	public static Object[][] getOneClient() {
		return new Object[][] {
			{ new Client.ClientBuilder().name("Ion Popescu")
				.country("Romania").city("Bucharest").street("Bulevardul Unirii")
				.streetNo("319 C").miscAddress("Cladirea Trust Center").build()  }	
		};
	}
}
