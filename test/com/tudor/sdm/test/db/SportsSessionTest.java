package com.tudor.sdm.test.db;

import javax.persistence.EntityManagerFactory;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tudor.sdm.EMF;
import com.tudor.sdm.dao.ClientDAO;
import com.tudor.sdm.entity.SportsSession;

public class SportsSessionTest {
	
	private EntityManagerFactory emf;
	
	@BeforeClass
	public void oneTimeSetUp() {
		System.out.println("@BeforeClass - oneTimeSetUp");
		emf = EMF.getTestInstance();
	}
	
	@AfterClass
    public void oneTimeTearDown() {
        // one-time cleanup code
    	System.out.println("@AfterClass - oneTimeTearDown");
    	emf.close();
    	emf = null;
    }
	
	@Test(dataProvider="getSingleSportsSession", dataProviderClass=SportsSessionDataProvider.class)
	public void addNewSportsSession(SportsSession session) {
		ClientDAO.get().add(session.getClient());
	}

}
