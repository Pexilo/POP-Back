package edu.intech.popback.dao.impl.bdd;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import edu.intech.popback.dao.interfaces.IUniverseDao;
import edu.intech.popback.exceptions.DaoException;
import edu.intech.popback.models.Universe;

public class UniverseDaoBdd implements IUniverseDao {

	/**
	 * Crée un nouveau univers dans la base de données
	 * 
	 * @param f l'univers à créer
	 * @return L'univers qui a été créé.
	 */
	@Override
	public Universe createUniverse(Universe u) throws DaoException {
		try {
			DaoBddHelper.beginTransaction();
			DaoBddHelper.getEm().persist(u);
			DaoBddHelper.commitTransaction();
			return u;
		} catch (PersistenceException e) {
			DaoBddHelper.rollbackTransaction();
			throw new DaoException("Saving: " + u.getName() + " in DB went wrong", e);
		}
	}

	/**
	 * Obtenir touts les univers de la base de données.
	 * 
	 * @return Une liste de touts les univers.
	 */
	@Override
	public List<Universe> getAllUniverses() {
		TypedQuery<Universe> query = DaoBddHelper.getEm().createNamedQuery("universes.getAllUniverses", Universe.class);
		return query.getResultList();
	}

	/**
	 * Renvoie un univers par son id
	 * 
	 * @param FigureId l'id de l'univers à renvoyer
	 * @return L'univers correspondante à l'id.
	 */
	@Override
	public Universe getUniverseById(int UniverseId) {
		TypedQuery<Universe> query = DaoBddHelper.getEm().createNamedQuery("universes.getUniverseById", Universe.class);
		query.setParameter("id", UniverseId);
		return query.getSingleResult();
	}

	/**
	 * Met à jour un univers dans la base de données
	 * 
	 * @param f l'univers à mettre à jour
	 * @return L'univers mise à jour.
	 */
	@Override
	public Universe updateUniverse(Universe u) throws DaoException {
		try {
			DaoBddHelper.beginTransaction();
			DaoBddHelper.getEm().merge(u);
			DaoBddHelper.commitTransaction();
			return u;
		} catch (PersistenceException e) {
			DaoBddHelper.rollbackTransaction();
			throw new DaoException("Updating: " + u.getName() + " in DB went wrong", e);
		}
	}

	/**
	 * Supprime un univers de la base de données
	 * 
	 * @param f l'univers à supprimer
	 */
	@Override
	public void deleteUniverse(Universe u) throws DaoException {
		try {
			DaoBddHelper.beginTransaction();
			DaoBddHelper.getEm().remove(u);
			DaoBddHelper.commitTransaction();
		} catch (PersistenceException e) {
			DaoBddHelper.rollbackTransaction();
			throw new DaoException("Deleting: " + u.getName() + " in DB went wrong", e);
		}
	}

}
