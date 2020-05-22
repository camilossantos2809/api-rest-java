package rest.resource;

import rest.model.Estudante;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("estudante")
public class EstudanteResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estudante> estudantes() {
        List<Estudante> list = new ArrayList<>();
        Estudante obj = new Estudante();
        obj.setNome("Estudante 1");
        obj.setCodigo(1);
        obj.setEmail("teste@teste.com.br");
        list.add(obj);
        return list;
    }
}
