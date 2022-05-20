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
	
	/**
	 * Si l'instance est nulle, crï¿½er une nouvelle instance de DaoFactory
	 * 
	 * @return L'instance de la classe DaoFactory.
	 */
	public static DaoFactory getInstance() {
		if (instance == null)
			instance = new DaoFactory();
		return instance;
	}
	
	/**
	 * Si figureDao est nul, alors créer un nouveau FigureDaoBdd et l'attribuer au figureDao
	 * 
	 * @return L'objet FigureDaoBdd.
	 */
	public IFigureDao getFigureDao() {
		if(this.figureDao == null) this.figureDao = new FigureDaoBdd();
		return figureDao;
	}

	/**
	 * Cette fonction définit la variable figureDao sur le paramètre figureDao
	 * 
	 * @param figureDao L'objet IFigureDao à attribuer à la variable figureDao
	 */
	public void setFigureDao(IFigureDao figureDao) {
		this.figureDao = figureDao;
	}

	/**
	 * Si l'universDao est nul, alors créer un nouveau UniverseDaoBdd et l'attribuer au universeDao
	 * 
	 * @return L'objet UniverseDaoBdd.
	 */
	public IUniverseDao getUniverseDao() {
		if(this.universeDao == null) this.universeDao = new UniverseDaoBdd();
		return universeDao;
	}

	/**
	 * > Cette fonction définit la variable universeDao à la valeur du paramètre universeDao
	 * 
	 * @param universeDao La valeur à attribuer à universeDao
	 */
	public void setUniverseDao(IUniverseDao universeDao) {
		this.universeDao = universeDao;
	}
}
