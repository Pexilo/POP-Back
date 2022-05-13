package edu.intech.popback.dao;

import edu.intech.popback.dao.impl.bdd.FigureDaoBdd;
import edu.intech.popback.dao.impl.bdd.UniverseDaoBdd;
import edu.intech.popback.dao.interfaces.IFigureDao;
import edu.intech.popback.dao.interfaces.IUniverseDao;

public class DaoFactory {

	private static DaoFactory instance;
	private IFigureDao figureDao;
	private IUniverseDao universeDao;
	
	private DaoFactory() {}
	
	
	public static DaoFactory getInstance() {
		if (instance == null)
			instance = new DaoFactory();
		return instance;
	}
	

	public IFigureDao getFigureDao() {
		if(this.figureDao == null) this.figureDao = new FigureDaoBdd();
		return figureDao;
	}



	public void setFigureDao(IFigureDao figureDao) {
		this.figureDao = figureDao;
	}



	public IUniverseDao getUniverseDao() {
		if(this.universeDao == null) this.universeDao = new UniverseDaoBdd();
		return universeDao;
	}



	public void setUniverseDao(IUniverseDao universeDao) {
		this.universeDao = universeDao;
	}
}
