package edu.intech.popback.dao.impl.bdd;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import edu.intech.popback.dao.interfaces.IFigureDao;
import edu.intech.popback.exceptions.DaoException;
import edu.intech.popback.models.Figure;

public class FigureDaoBdd implements IFigureDao {

	/**
	 * Créée une nouvelle figurine dans la base de données
	 * 
	 * @param f la figurine à créer
	 * @return La figurine qui a été créée.
	 */
	@Override
	public Figure createFigure(Figure f) throws DaoException {
		try {
			DaoBddHelper.beginTransaction();
			DaoBddHelper.getEm().persist(f);
			DaoBddHelper.commitTransaction();
			return f;
		} catch (PersistenceException e) {
			DaoBddHelper.rollbackTransaction();
			throw new DaoException("Saving: " + f.getName() + " in DB went wrong", e);
		}
	}

	/**
	 * Obtenir toutes les figurines de la base de données.
	 * 
	 * @return Une liste de toutes les figurines.
	 */
	@Override
	public List<Figure> getAllFigures() {
		TypedQuery<Figure> query = DaoBddHelper.getEm().createNamedQuery("figures.getAllFigures", Figure.class);
		return query.getResultList();
	}

	/**
	 * Renvoi une figurine par son id
	 * 
	 * @param FigureId l'id de la figurine à renvoyer
	 * @return La figurine correspondante à l'id.
	 */
	@Override
	public Figure getFigureById(int FigureId) {
		TypedQuery<Figure> query = DaoBddHelper.getEm().createNamedQuery("figures.getFigureById", Figure.class);
		query.setParameter("id", FigureId);
		return query.getSingleResult();
	}

	/**
	 * Met Ã  jour une figurine dans la base de données
	 * 
	 * @param f la figurine à mettre à jour
	 * @return Lea figurine mise à jour.
	 */
	@Override
	public Figure updateFigure(Figure f) throws DaoException {
		try {
			DaoBddHelper.beginTransaction();
			DaoBddHelper.getEm().merge(f);
			DaoBddHelper.commitTransaction();
			return f;
		} catch (PersistenceException e) {
			DaoBddHelper.rollbackTransaction();
			throw new DaoException("Updating: " + f.getName() + " in DB went wrong", e);
		}
	}

	/**
	 * Supprime une figurine de la base de données
	 * 
	 * @param f la figurine à supprimer
	 */
	@Override
	public void deleteFigure(Figure f) throws DaoException {
		try {
			DaoBddHelper.beginTransaction();
			DaoBddHelper.getEm().remove(f);
			DaoBddHelper.commitTransaction();
		} catch (PersistenceException e) {
			DaoBddHelper.rollbackTransaction();
			throw new DaoException("Deleting: " + f.getName() + " in DB went wrong", e);
		}
	}
}
