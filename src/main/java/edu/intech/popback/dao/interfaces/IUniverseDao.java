package edu.intech.popback.dao.interfaces;

import java.util.List;

import edu.intech.popback.exceptions.DaoException;
import edu.intech.popback.models.Universe;

// L'interface de la classe UniverseDao.
public interface IUniverseDao {
	
	Universe createUniverse(Universe u) throws DaoException;
	
	List<Universe> getAllUniverses();
	
	Universe getUniverseById(int UniverseId);
	
	Universe updateUniverse(Universe u) throws DaoException;
	
	void deleteUniverse(Universe u) throws DaoException;
}
