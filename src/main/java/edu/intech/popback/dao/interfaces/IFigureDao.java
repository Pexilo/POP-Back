package edu.intech.popback.dao.interfaces;

import java.util.List;

import edu.intech.popback.exceptions.DaoException;
import edu.intech.popback.models.Figure;

// L'interface de la classe FigureDao.
public interface IFigureDao {

	Figure createFigure(Figure f) throws DaoException;
	
	List<Figure> getAllFigures();
	
	Figure getFigureById(int FigureId);
	
	Figure updateFigure(Figure f) throws DaoException;
	
	void deleteFigure(Figure f) throws DaoException;
}
