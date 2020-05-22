package rest.resource;

import rest.model.Nota;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("nota")
public class NotaResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Nota> notas() {
        List<Nota> list = new ArrayList<>();
//        Nota obj = new Nota();
//        list.add(obj);
        return list;
    }
}
