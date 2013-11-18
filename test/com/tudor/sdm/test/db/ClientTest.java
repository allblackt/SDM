package com.tudor.sdm.test.db;

import javax.persistence.EntityManagerFactory;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tudor.sdm.EMF;
import com.tudor.sdm.dao.ClientDAO;
import com.tudor.sdm.entity.Client;

public class ClientTest {

	private EntityManagerFactory emf;
	private Client client;
	
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
    	client = null;
    }
	
	@Test(dataProvider="oneClient", dataProviderClass=ClientDataProvider.class)
	public void addClient(Client c1) {
		client = c1;
		Client nou = ClientDAO.get().add(client);
		Assert.assertEquals(client, nou);
	}
	
	@Test(dependsOnMethods={"addClient"})
	public void getClientById() {
		Client nou = ClientDAO.get().getById(client.getId());
		Assert.assertEquals(nou, client);
	}
}
