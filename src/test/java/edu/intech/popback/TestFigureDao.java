package edu.intech.popback;

import org.junit.jupiter.api.TestMethodOrder;

import edu.intech.popback.dao.DaoFactory;
import edu.intech.popback.dao.interfaces.IFigureDao;
import edu.intech.popback.exceptions.DaoException;
import edu.intech.popback.models.Figure;
import edu.intech.popback.services.FigureService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@TestMethodOrder(OrderAnnotation.class) // Permet d'ordonner les tests
class TestFigurinesDao {

	private static Figure figure = null;
    private static IFigureDao figureDao;
    
    private final static Logger logger = LogManager.getLogger(FigureService.class);

    /**
     * Cette fonction est utilisée pour créer une nouvelle instance de figureDao avant chaque test
    */
    @BeforeEach
    void setUp() throws Exception {
        figureDao = DaoFactory.getInstance().getFigureDao(); 
    }

   /**
    * Cette fonction permet d'effacer la mémoire de la figure créée aprés chaque test
    */
    @AfterEach
    void tearDown() throws Exception {
        figureDao = null;
    }
    
    /**
     * La fonction de test crée une figure, puis vérifie que l'ID de la figure n'est pas égal é 0
     */
    @Test
    @Order(1)
    void createFigure() throws DaoException {
        logger.debug("Test - > createFigure");
        // Création d'une nouvelle figurine avec le nom "Test", l'URL de l'image "https://google.com"
        // et l'id 1 (en auto increment l'id sera 0)
        figure = new Figure("Test", "https://google.com", 1); 
        assertEquals(figure.getId(), 0);
       // Créer une nouvelle figure à partir de la figure créée précédemment pour vérifier que l'id change
        figureDao.createFigure(figure); 
        assertTrue(figure.getId() >= 0); 
        logger.debug("Test - > createFigure - Fin");
    }
    
    /**
     * Cette fonction teste la fonction getAllFigures() de la classe FigureDao
     * Elle vérifie que la liste de figures renvoyée par la fonction est bien non vide
     */
    @Test
    @Order(2)
    void getAllFigures() throws DaoException { 
        logger.debug("Test - > getAllFigures - Début");
        List<Figure> figurineList = figureDao.getAllFigures(); //On get la liste compléte
        assertTrue(figurineList.size() >= 1);
        logger.debug("Test - > getFigures - Fin");
    }

    /**
     * Cette fonction teste la fonction getFigureById() de la classe FigureDao
     */
    @Test
    @Order(3)
    public void getFigureById() throws DaoException { 
        logger.debug("Test - > getFigureById - Début");
        Figure f = figureDao.getFigureById(figure.getId());
        assertEquals("Test", f.getName()); // Compare les données de la figure créée avec celles de la figure récupérée
        assertEquals("Test", f.getImageURL());
        logger.debug("Test - > getFigureById - Fin");
    }

    /**
     * La fonction deleteFigure() teste la suppression d'une figure
     */
    @Test
    @Order(4)
    public void deleteFigure() throws DaoException { 
        logger.debug("Test - > deleteFigure - Début");
        figureDao.deleteFigure(figure);
       // Vérifier que la figure a été supprimée en essayant de la récupérer par son ID
        assertThrows(NoResultException.class, () -> {
            figureDao.getFigureById(figure.getId());
        });
        logger.debug("Test - > deleteFigure - Fin");
    }
    
}