package rest.resource;

import rest.model.Disciplina;
import rest.model.DisciplinaEstudante;
import rest.model.dao.Dao;
import rest.model.dao.DisciplinaDao;
import rest.model.dao.NotaDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("disciplina")
public class DisciplinaResource {
    private final DisciplinaDao dao = new DisciplinaDao(Dao.getConnection());
    private final NotaDao daoNota = new NotaDao(Dao.getConnection());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return Response.ok().entity(this.dao.list()).build();
    }

    @GET
    @Path("estudantes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listEstudantes() {
        try {
            List<DisciplinaEstudante> list = new ArrayList<>();
            this.dao.list().forEach(disciplina -> {
                DisciplinaEstudante disc = new DisciplinaEstudante();
                disc.setCodigo(disciplina.getCodigo());
                disc.setNome(disciplina.getNome());
                disc.setEstudantes(this.daoNota.findByDisciplina(disc.getCodigo()));
                list.add(disc);
            });
            return Response.ok().entity(list).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @GET
    @Path("{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("codigo") int codigo) {
        Map<String, Integer> pk = new HashMap<>();
        pk.put("codigo", codigo);
        Disciplina obj = this.dao.read(pk);
        if (obj.getCodigo() == 0) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok().entity(this.dao.read(pk)).build();
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

    @PUT
    @Path("{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Disciplina dados, @PathParam("codigo") int codigo) {
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
