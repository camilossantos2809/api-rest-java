package rest.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class DisciplinaEstudante extends Disciplina  implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<EstudanteNota> estudantes;

    public List<EstudanteNota> getEstudantes() {
        return estudantes;
    }

    public void setEstudantes(List<EstudanteNota> estudantes) {
        this.estudantes = estudantes;
    }
}
