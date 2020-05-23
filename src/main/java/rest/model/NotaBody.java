package rest.model;

public class NotaBody {
    private static final long serialVersionUID = 1L;
    private int disciplina;
    private int estudante;
    private double nota;
    private double frequencia;

    public int getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(int disciplina) {
        this.disciplina = disciplina;
    }

    public int getEstudante() {
        return estudante;
    }

    public void setEstudante(int estudante) {
        this.estudante = estudante;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public double getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(double frequencia) {
        this.frequencia = frequencia;
    }
}
