package rest.resource;

import rest.model.Disciplina;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("disciplina")
public class DisciplinaResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Disciplina> disciplinas() {
        List<Disciplina> list = new ArrayList<>();
        Disciplina disc = new Disciplina();
        disc.setNome("Disciplina 1");
        disc.setCodigo(1);
        list.add(disc);
        return list;
    }
}
