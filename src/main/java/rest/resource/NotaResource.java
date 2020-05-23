package rest.resource;

import rest.model.Disciplina;
import rest.model.Estudante;
import rest.model.Nota;
import rest.model.NotaBody;
import rest.model.dao.Dao;
import rest.model.dao.DisciplinaDao;
import rest.model.dao.EstudanteDao;
import rest.model.dao.NotaDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Path("nota")
public class NotaResource {
    private final NotaDao dao = new NotaDao(Dao.getConnection());
    private final EstudanteDao estudanteDao = new EstudanteDao(Dao.getConnection());
    private final DisciplinaDao disciplinaDao = new DisciplinaDao(Dao.getConnection());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return Response.ok().entity(this.dao.list()).build();
    }

    @GET
    @Path("estudante/{estudante}/disciplina/{disciplina}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(
            @PathParam("estudante") int estudante_cod,
            @PathParam("disciplina") int disciplina_cod
    ) {
        Map<String, Integer> pk = new HashMap<>();
        pk.put("estudante_cod", estudante_cod);
        pk.put("disciplina_cod", disciplina_cod);
        Nota obj = this.dao.read(pk);
        if (obj.getEstudante().getCodigo() == 0 && obj.getDisciplina().getCodigo() == 0) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok().entity(this.dao.read(pk)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(NotaBody dados) {
        try {
            Estudante estudante = this.estudanteDao.read(dados.getEstudante());
            Disciplina disciplina = this.disciplinaDao.read(dados.getDisciplina());
            this.dao.create(new Nota(estudante, disciplina, dados.getNota(), dados.getFrequencia()));
            return Response.ok().entity(true).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @PUT
    @Path("estudante/{estudante}/disciplina/{disciplina}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
            NotaBody dados,
            @PathParam("estudante") int estudante_cod,
            @PathParam("disciplina") int disciplina_cod
    ) {
        Map<String, Integer> pk = new HashMap<>();
        pk.put("estudante_cod", estudante_cod);
        pk.put("disciplina_cod", disciplina_cod);
        try {
            Estudante estudante = this.estudanteDao.read(dados.getEstudante());
            Disciplina disciplina = this.disciplinaDao.read(dados.getDisciplina());
            this.dao.update(pk, new Nota(estudante, disciplina, dados.getNota(), dados.getFrequencia()));
            return Response.ok().entity(true).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("estudante/{estudante}/disciplina/{disciplina}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("estudante") int estudante_cod,
            @PathParam("disciplina") int disciplina_cod
    ) {
        Map<String, Integer> pk = new HashMap<>();
        pk.put("estudante_cod", estudante_cod);
        pk.put("disciplina_cod", disciplina_cod);
        try {
            this.dao.delete(pk);
            return Response.ok().entity(true).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
}
