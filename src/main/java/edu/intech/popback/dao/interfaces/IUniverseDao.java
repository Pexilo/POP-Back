package edu.intech.popback.dao.interfaces;

import java.util.List;

import edu.intech.popback.exceptions.DaoException;
import edu.intech.popback.models.Universe;

public interface IUniverseDao {
	
	Universe createUniverse(Universe u) throws DaoException;
	
	List<Universe> getAllUniverses() throws DaoException;
	
	Universe getUniverseById(int UniverseId) throws DaoException;
	
	Universe updateUniverse(Universe u) throws DaoException;
	
	void deleteUniverse(Universe u) throws DaoException;
}
