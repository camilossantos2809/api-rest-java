package rest.resource;

import jdk.jshell.Snippet;
import rest.model.Disciplina;
import rest.model.dao.Dao;
import rest.model.dao.DisciplinaDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Path("disciplina")
public class DisciplinaResource {
    private final DisciplinaDao dao = new DisciplinaDao(Dao.getConnection());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response disciplinas() {
        return Response.ok().entity(this.dao.list()).build();
    }

    @GET
    @Path("{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response disciplina(@PathParam("codigo") int codigo) {
        Disciplina obj = this.dao.read(codigo);
        if (obj.getCodigo() == 0){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok().entity(this.dao.read(codigo)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Disciplina dados) {
        try {
            this.dao.create(dados);
            return Response.ok().entity(true).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
}
