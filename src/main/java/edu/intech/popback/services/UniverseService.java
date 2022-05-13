package edu.intech.popback.services;

import java.util.List;

import edu.intech.popback.dao.DaoFactory;
import edu.intech.popback.exceptions.DaoException;
import edu.intech.popback.models.Figure;
import edu.intech.popback.models.Universe;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/universe")
public class UniverseService {

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUniverses() throws DaoException {

		List<Universe> figures = DaoFactory.getInstance().getUniverseDao().getAllUniverses();
		final GenericEntity<List<Universe>> json = new GenericEntity<>(figures) {
		};

		return Response.ok().entity(json).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUniverseById(@PathParam("id") int UniverseId) throws DaoException {

		Universe universe = DaoFactory.getInstance().getUniverseDao().getUniverseById(UniverseId);
		final GenericEntity<Universe> json = new GenericEntity<>(universe) {
		};

		return Response.ok().entity(json).build();
	}

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
	
	//ça reset le tableau de figures
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUniverse(Universe u) {

		try {
			DaoFactory.getInstance().getUniverseDao().updateUniverse(u);
			return Response.ok().entity(u).build();

		} catch (DaoException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Couldn't update universe. Check params.\n\n" + e)
					.build();
		}
	}

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
	
	@POST
	@Path("/{id}/addFigure")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addFigureToUniverse(@PathParam("id") int UniverseId, Figure f) {

		try {
			Universe u = DaoFactory.getInstance().getUniverseDao().getUniverseById(UniverseId);
			u.addFigure(f);
			DaoFactory.getInstance().getUniverseDao().updateUniverse(u);
			return Response.ok().entity(u).build();

		} catch (DaoException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Couldn't delete universe. Check params.\n\n" + e)
					.build();
		}

	}
}
