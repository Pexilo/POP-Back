package edu.intech.popback.dao.interfaces;

import java.util.List;

import edu.intech.popback.exceptions.DaoException;
import edu.intech.popback.models.Figure;

public interface IFigureDao {

	Figure createFigure(Figure f) throws DaoException;
	
	List<Figure> getAllFigures() throws DaoException;
	
	Figure getFigureById(int FigureId) throws DaoException;
	
	List<Figure> getFiguresByIdUniverse(int IdUniverse) throws DaoException;
	
	Figure updateFigure(Figure f) throws DaoException;
	
	void deleteFigure(Figure f) throws DaoException;
}
