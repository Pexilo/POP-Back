package edu.intech.popback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import edu.intech.popback.dao.DaoFactory;
import edu.intech.popback.dao.interfaces.IUniverseDao;
import edu.intech.popback.exceptions.DaoException;
import edu.intech.popback.models.Universe;
import edu.intech.popback.services.FigureService;

@TestMethodOrder(OrderAnnotation.class) // Permet d'ordonner les tests
public class TestUniverseDao {

	private static Universe universe = null;
    private static IUniverseDao universeDao;
    
    private final static Logger logger = LogManager.getLogger(FigureService.class);

    @BeforeEach
    void setUp() throws Exception {
        universeDao = DaoFactory.getInstance().getUniverseDao(); // On crée une nouvelle instance de universeDao
    }

    @AfterEach
    void tearDown() throws Exception {
        universeDao = null;
    }
    
    @Test
    @Order(1)
    void createUniverse() throws DaoException {
        logger.debug("Test - > createUniverse - Début");
        universe = new Universe("Test", "https://google.com"); //On stocke en local l'univers crée pour le réutiliser par la suite
        assertEquals(universe.getId(), 0);
        universeDao.createUniverse(universe); //On crée la universe
        assertTrue(universe.getId() >= 0); //On vérifie que l'ID de l'univers est bien différent de 0
        logger.debug("Test - > createUniverse - Fin");
    }
    
    @Test
    @Order(2)
    void getAllUniverses() throws DaoException { //On test la récupération des univers
        logger.debug("Test - > getAllUniverses - Début");
        List<Universe> figurineList = universeDao.getAllUniverses(); //On get la liste complète
        assertTrue(figurineList.size() >= 1);
        logger.debug("Test - > getAllUniverses - Fin");
    }

    @Test
    @Order(3)
    public void getUniverseById() throws DaoException { //On test la récupération d'un univers
        logger.debug("Test - > getUniverseById - Début");
        Universe f = universeDao.getUniverseById(universe.getId());
        assertEquals("Test", f.getName()); //On compare les données de l'univers avec celles utilisés au départ
        assertEquals("Test", f.getImageURL());
        logger.debug("Test - > getUniverseById - Fin");
    }

    @Test
    @Order(4)
    public void deleteUniverse() throws DaoException { //On test la suppression d'un univers
        logger.debug("Test - > deleteUniverse - Début");
        universeDao.deleteUniverse(universe);
        assertThrows(NoResultException.class, () -> {
            universeDao.getUniverseById(universe.getId());
        });
        logger.debug("Test - > deleteUniverse - Fin");
    }
}
