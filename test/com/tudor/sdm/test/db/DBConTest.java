package com.tudor.sdm.test.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tudor.sdm.EMF;

public class DBConTest {

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
	
	@Test
	public void testDB() {
		EntityManager em = emf.createEntityManager();
		System.out.println(em.createNativeQuery("Select count(*) from `client`").getFirstResult()); 
		
	}
	
}
