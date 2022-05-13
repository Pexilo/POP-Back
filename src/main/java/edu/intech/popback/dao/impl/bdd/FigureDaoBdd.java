package edu.intech.popback.dao.impl.bdd;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import edu.intech.popback.dao.interfaces.IFigureDao;
import edu.intech.popback.exceptions.DaoException;
import edu.intech.popback.models.Figure;

public class FigureDaoBdd implements IFigureDao {

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

	@Override
	public List<Figure> getAllFigures() {
		TypedQuery<Figure> query = DaoBddHelper.getEm().createNamedQuery("figures.getAllFigures", Figure.class);
		return query.getResultList();
	}

	@Override
	public Figure getFigureById(int FigureId) {
		TypedQuery<Figure> query = DaoBddHelper.getEm().createNamedQuery("figures.getFigureById", Figure.class);
		query.setParameter("id", FigureId);
		return query.getSingleResult();
	}

	@Override
	public List<Figure> getFiguresByIdUniverse(int IdUniverse) {
		TypedQuery<Figure> query = DaoBddHelper.getEm().createNamedQuery("figures.getFiguresByIdUniverse",
				Figure.class);
		query.setParameter("id", IdUniverse);
		return query.getResultList();
	}

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
