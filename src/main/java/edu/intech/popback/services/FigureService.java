package edu.intech.popback.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.intech.popback.dao.DaoFactory;
import edu.intech.popback.exceptions.DaoException;
import edu.intech.popback.models.Figure;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Méthodes pour créer et manipuler des figurines avec les requêtes HTTP reçues
 * par le serveur
 */
@Path("/figure")
public class FigureService {
	
	private final static Logger logger = LogManager.getLogger(FigureService.class);

	/**
	 * Renvoi une liste de toutes les figurines
	 * 
	 * @return Un json de figurines
	 */
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllFigures(@Context HttpServletRequest req) throws DaoException {

		logger.debug("Figure - > getAll from : {}", req.getRemoteAddr());
		List<Figure> figures = DaoFactory.getInstance().getFigureDao().getAllFigures();
		final GenericEntity<List<Figure>> json = new GenericEntity<>(figures) {
		};

		return Response.ok().entity(json).build();
	}

	/**
	 * Renvoi une figurine par son id
	 * 
	 * @param FigureId de la figurine
	 * @return Un json de figurine
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFigureById(@PathParam("id") int FigureId) throws DaoException {

		Figure figure = DaoFactory.getInstance().getFigureDao().getFigureById(FigureId);
		final GenericEntity<Figure> json = new GenericEntity<>(figure) {
		};

		return Response.ok().entity(json).build();
	}

	/**
	 * Cree une nouvelle figurine
	 * 
	 * @param f La figurine a créer
	 * @return Le json de la figurine créée
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createFigure(Figure f) {

		try {
			DaoFactory.getInstance().getFigureDao().createFigure(f);
			return Response.ok().entity(f).build();

		} catch (DaoException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Couldn't create figure. Check params." + e)
					.build();
		}
	}

	/**
	 * Met a jour une figurine
	 * 
	 * @param f La figurine à mettre à jour
	 * @return Le json de la figurine mise à jour
	 */
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateFigure(Figure f) {

		try {
			DaoFactory.getInstance().getFigureDao().updateFigure(f);
			return Response.ok().entity(f).build();

		} catch (DaoException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Couldn't update figure. Check params." + e)
					.build();
		}
	}

	/**
	 * Supprime une figurine par son id
	 * 
	 * @param FigureId L'id de la figurine a supprimer
	 * @return Le json de la figurine supprimÃ©e
	 */
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteFigure(@PathParam("id") int FigureId) {

		try {
			Figure f = DaoFactory.getInstance().getFigureDao().getFigureById(FigureId);
			DaoFactory.getInstance().getFigureDao().deleteFigure(f);
			return Response.ok().entity(f).build();

		} catch (DaoException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Couldn't delete figure. Check params.\n\n" + e)
					.build();
		}

	}
}
