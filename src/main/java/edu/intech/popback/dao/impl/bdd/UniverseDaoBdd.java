package edu.intech.popback.dao.impl.bdd;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import edu.intech.popback.dao.interfaces.IUniverseDao;
import edu.intech.popback.exceptions.DaoException;
import edu.intech.popback.models.Universe;

public class UniverseDaoBdd implements IUniverseDao {

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

	@Override
	public List<Universe> getAllUniverses() {
		TypedQuery<Universe> query = DaoBddHelper.getEm().createNamedQuery("universes.getAllUniverses", Universe.class);
		return query.getResultList();
	}

	@Override
	public Universe getUniverseById(int UniverseId) {
		TypedQuery<Universe> query = DaoBddHelper.getEm().createNamedQuery("universes.getUniverseById", Universe.class);
		query.setParameter("id", UniverseId);
		return query.getSingleResult();
	}

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
