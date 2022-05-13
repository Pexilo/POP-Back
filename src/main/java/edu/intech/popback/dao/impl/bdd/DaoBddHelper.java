package edu.intech.popback.dao.impl.bdd;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DaoBddHelper {
	
	private static EntityManager em;

	public static EntityManager getEm() {
		if (em == null) {
			em = Persistence.createEntityManagerFactory("popback").createEntityManager();
		}
		return em;

	}

	public static void beginTransaction() {
		getEm().getTransaction().begin();
	}

	public static void commitTransaction() {
		if (getEm().getTransaction().isActive()) {
			getEm().getTransaction().commit();
		}
	}

	public static void rollbackTransaction() {
		if (getEm().getTransaction().isActive()) {
			getEm().getTransaction().rollback();
		}
	}
}
