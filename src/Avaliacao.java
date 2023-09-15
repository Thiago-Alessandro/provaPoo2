import java.util.ArrayList;

public class Avaliacao {

    private double nota;
    private Disciplina disciplina;
    private Aluno aluno;

    private static ArrayList<Avaliacao> avaliacoes = new ArrayList<>();

    public Avaliacao(double nota, Disciplina disciplina, Aluno aluno){
        this.nota = nota;
        this.disciplina = disciplina;
        this.aluno = aluno;
        this.avaliacoes.add(this);
    }

    public Aluno getAluno() {
        return aluno;
    }

    public double getNota() {
        return nota;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public static ArrayList<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }
}
