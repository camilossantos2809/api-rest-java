package rest.resource;

import rest.model.Estudante;
import rest.model.dao.Dao;
import rest.model.dao.EstudanteDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Path("estudante")
public class EstudanteResource {
    private final EstudanteDao dao = new EstudanteDao(Dao.getConnection());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return Response.ok().entity(this.dao.list()).build();
    }

    @GET
    @Path("{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("codigo") int codigo) {
        Map<String, Integer> pk = new HashMap<>();
        pk.put("codigo", codigo);
        Estudante obj = this.dao.read(pk);
        if (obj.getCodigo() == 0) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok().entity(this.dao.read(pk)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Estudante dados) {
        try {
            this.dao.create(dados);
            return Response.ok().entity(true).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @PUT
    @Path("{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Estudante dados, @PathParam("codigo") int codigo) {
        Map<String, Integer> pk = new HashMap<>();
        pk.put("codigo", codigo);
        try {
            this.dao.update(pk, dados);
            return Response.ok().entity(true).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("codigo") int codigo) {
        Map<String, Integer> pk = new HashMap<>();
        pk.put("codigo", codigo);
        try {
            this.dao.delete(pk);
            return Response.ok().entity(true).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
}
