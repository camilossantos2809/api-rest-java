package rest.model;

public class Nota {
    private static final long serialVersionUID = 1L;
    private Disciplina disciplina;
    private Estudante estudante;
    private double nota;
    private double frequencia;

    public Nota() {
    }

    public Nota( Estudante estudante,Disciplina disciplina, double nota, double frequencia) {
        this.disciplina = disciplina;
        this.estudante = estudante;
        this.nota = nota;
        this.frequencia = frequencia;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
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
