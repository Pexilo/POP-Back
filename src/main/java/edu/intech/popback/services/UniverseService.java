package edu.intech.popback.services;

import java.util.List;

import edu.intech.popback.dao.DaoFactory;
import edu.intech.popback.exceptions.DaoException;
import edu.intech.popback.models.Figure;
import edu.intech.popback.models.Universe;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/universe")
public class UniverseService {

	/**
	 * Renvoie une liste de touts les univers
	 * 
	 * @return Un json des univers
	 */
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUniverses() throws DaoException {

		List<Universe> universe = DaoFactory.getInstance().getUniverseDao().getAllUniverses();
	
		final GenericEntity<List<Universe>> json = new GenericEntity<>(universe) {
		};

		return Response.ok().entity(json).build();
	}

	/**
	 * Renvoie un univers par son id
	 * 
	 * @param UniverseId L'id de l'univers.
	 * @return Un json de l'univers
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUniverseById(@PathParam("id") int UniverseId) throws DaoException {

		Universe universe = DaoFactory.getInstance().getUniverseDao().getUniverseById(UniverseId);
		final GenericEntity<Universe> json = new GenericEntity<>(universe) {
		};

		return Response.ok().entity(json).build();
	}

	/**
	 * Crée un univers
	 * 
	 * @param u L'univers a créer 
	 * @return Le json de l'univers crée
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUniverse(Universe u) {

		try {
			DaoFactory.getInstance().getUniverseDao().createUniverse(u);
			return Response.ok().entity(u).build();

		} catch (DaoException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Couldn't create universe. Check params.\n\n" + e)
					.build();
		}
	}
	
	/**
	 * Met à jour un univers
	 * 
	 * @param u L'univers à mettre a jour
	 * @return Le json de l'univers mis a jour
	 */
	//ca reset le tableau de figures
	@PATCH
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUniverse(Universe u) {

		try {
			Universe universDb = DaoFactory.getInstance().getUniverseDao().getUniverseById(u.getId());
            u = universDb.compareUpdate(u);
			DaoFactory.getInstance().getUniverseDao().updateUniverse(u);
			return Response.ok().entity(u).build();

		} catch (DaoException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Couldn't update universe. Check params.\n\n" + e)
					.build();
		}
	}

	/**
	 * Supprime un univers
	 * 
	 * @param UniverseId L'id de l'univers à supprimer
	 * @return Le json de l'univers supprimé
	 */
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUniverse(@PathParam("id") int UniverseId) {

		try {
			Universe u = DaoFactory.getInstance().getUniverseDao().getUniverseById(UniverseId);
			DaoFactory.getInstance().getUniverseDao().deleteUniverse(u);
			return Response.ok().entity(u).build();

		} catch (DaoException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Couldn't delete universe. Check params.\n\n" + e)
					.build();
		}

	}
	
	/**
	 * Ajoute la figurine à l'univers
	 * 
	 * @param f la figure à ajouter
	 * @return Le json de l'univers avec la figure ajoutée
	 */
	@POST
	@Path("/addFigure")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addFigureToUniverse(Figure f) {

		try {
			Universe u = DaoFactory.getInstance().getUniverseDao().getUniverseById(f.getIdUniverse());
			u.addFigure(f);
			DaoFactory.getInstance().getUniverseDao().updateUniverse(u);
			return Response.ok().entity(u).build();

		} catch (DaoException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Couldn't delete universe. Check params.\n\n" + e)
					.build();
		}

	}
}
