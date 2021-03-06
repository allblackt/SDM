package com.tudor.sdm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import org.apache.log4j.Logger;

import com.tudor.sdm.EMF;

public abstract class DAO<T , ID> implements IDAO<T, ID> {
	protected final Logger log = Logger.getLogger(this.getClass());
	
	private Class<T> clazz;
	
	protected DAO(Class<T> clazz){
		this.clazz = clazz;
	}
	
	@Override
	public T add(T item) {
		log.debug("Entered");
		EntityManager em = null;
		try {
			em = EMF.get().createEntityManager();
			em.getTransaction().begin();
			em.persist(item);
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
			log.debug("Exiting");
		}
		return item;
	}
	
	@Override
	public T save(T item) {
		EntityManager em = null;
		try {
			em = EMF.get().createEntityManager();
			em.getTransaction().begin();
			em.merge(item);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (em != null) {
				if(em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
				em.clear();
				em.close();
			}
		}
		return item;
	}
	
	@Override
	public T getById(ID id) {
		EntityManager em = null;
		T item = null;
		try {
			em = EMF.get().createEntityManager();
			item = (T) em.find(clazz, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (em != null) {
				if(em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
				em.clear();
				em.close();
			}
		}
		return item;
	}
	
	@Override
	public List<T> getAll() {
		return getAll(null, null);
	}

    @Override
    public List<T> getAll(String orderByColumn, Boolean asc) {
        EntityManager em = null;
        try {
            em = EMF.get().createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(clazz);
            Root<T> root = cq.from(clazz);

            if (orderByColumn != null) {
                cq.orderBy(
                        asc != null && asc == true ?
                                cb.asc(cb.upper(root.get(orderByColumn).as(String.class))):
                                cb.desc(cb.upper(root.get(orderByColumn).as(String.class)))
                );
            }
            TypedQuery<T> tq = em.createQuery(cq);
            return tq.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }
}
