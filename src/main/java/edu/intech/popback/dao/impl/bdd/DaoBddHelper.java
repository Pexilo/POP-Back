package edu.intech.popback.dao.impl.bdd;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DaoBddHelper {

	private static EntityManager em;

	/**
	 * Si EntityManager est null, crée une nouvelle EntityManagerFactory et crée une nouvelle
	 * EntityManager
	 * 
	 * @return Un objet EntityManager.
	 */
	public static EntityManager getEm() {
		if (em == null) {
			em = Persistence.createEntityManagerFactory("popback").createEntityManager();
		}
		return em;

	}
	/**
	 * > Commencer une transaction
	 */
	public static void beginTransaction() {
		getEm().getTransaction().begin();
	}

	/**
	 * Si la transaction est active, la valider. Sinon, ne rien faire.
	 */
	public static void commitTransaction() {
		if (getEm().getTransaction().isActive()) {
			getEm().getTransaction().commit();
		}
	}

	/**
	 * Si la transaction est active, l'annuler. Sinon, ne rien faire.
	 */
	public static void rollbackTransaction() {
		if (getEm().getTransaction().isActive()) {
			getEm().getTransaction().rollback();
		}
	}
}
