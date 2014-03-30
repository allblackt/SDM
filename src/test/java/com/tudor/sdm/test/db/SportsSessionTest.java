package com.tudor.sdm.test.db;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tudor.sdm.EMF;
import com.tudor.sdm.dao.ClientDAO;
import com.tudor.sdm.dao.SportsSessionDAO;
import com.tudor.sdm.entity.SportsSession;

public class SportsSessionTest {
	
	private EntityManagerFactory emf;
	SportsSession session;
	
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
	
	@Test(dataProvider="getTwoSportsSession", dataProviderClass=SportsSessionDataProvider.class)
	public void addNewSportsSession(SportsSession session) {
		this.session = SportsSessionDAO.get().add(session);
	}
	
	@Test(dependsOnMethods={"addNewSportsSession"})
	public void getSportsSessionById() {
		SportsSession s = SportsSessionDAO.get().getById(2L);
		Assert.assertEquals(s, session);
	}
	
	@Test(dependsOnMethods={"addNewSportsSession"})
	public void getAllSportsSessions() {
		List<SportsSession> s = SportsSessionDAO.get().getAll();
		Assert.assertEquals(s.size(), 2);
	}

    @Test(dependsOnMethods={"addNewSportsSession"})
    public void getAllSportsSessionsOrderBy() {
        List<SportsSession> s = SportsSessionDAO.get().getAll("id", false);
        Assert.assertEquals(s.get(0).getId(), new Long(2));
    }


}
