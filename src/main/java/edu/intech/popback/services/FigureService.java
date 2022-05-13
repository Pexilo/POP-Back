package edu.intech.popback.services;

import java.util.List;

import edu.intech.popback.dao.DaoFactory;
import edu.intech.popback.exceptions.DaoException;
import edu.intech.popback.models.Figure;
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

@Path("/figure")
public class FigureService {

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllFigures() throws DaoException {

		List<Figure> figures = DaoFactory.getInstance().getFigureDao().getAllFigures();
		final GenericEntity<List<Figure>> json = new GenericEntity<>(figures) {
		};

		return Response.ok().entity(json).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFigureById(@PathParam("id") int FigureId) throws DaoException {

		Figure figure = DaoFactory.getInstance().getFigureDao().getFigureById(FigureId);
		final GenericEntity<Figure> json = new GenericEntity<>(figure) {
		};

		return Response.ok().entity(json).build();
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createFigure(Figure f) {

		try {
			DaoFactory.getInstance().getFigureDao().createFigure(f);
			return Response.ok().entity(f).build();

		} catch (DaoException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Couldn't create figure. Check params.\n\n" + e)
					.build();
		}
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateFigure(Figure f) {

		try {
			DaoFactory.getInstance().getFigureDao().updateFigure(f);
			return Response.ok().entity(f).build();

		} catch (DaoException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Couldn't update figure. Check params.\n\n" + e)
					.build();
		}
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMyUser(@PathParam("id") int FigureId) {

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
