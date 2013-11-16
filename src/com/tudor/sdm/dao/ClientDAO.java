package com.tudor.sdm.dao;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.tudor.sdm.EMF;
import com.tudor.sdm.entity.Client;

public class ClientDAO {
	private static ClientDAO instance = null;
	private static final Logger log = Logger.getLogger(ClientDAO.class);
	private ClientDAO () {
		log.debug("Created.");
	}
	
	public static ClientDAO get() {
		if(instance == null) {
			instance = new ClientDAO();
		}
		return instance;
	}
	
	public Client add(Client client) {
		EntityManager em = null;
		try {
			em = EMF.get().createEntityManager();
			em.getTransaction().begin();
			em.persist(client);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null) {
				if(em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
				em.clear();
				em.close();
			}
		}
		return client;
	}
	
	public Client getById(Long id) {
		EntityManager em = null;
		Client client = null;
		try {
			em = EMF.get().createEntityManager();
			client = em.find(Client.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null) {
				if(em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
				em.clear();
				em.close();
			}
		}
		return client;
	}
}
