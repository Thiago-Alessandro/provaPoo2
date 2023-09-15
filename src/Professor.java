import java.util.ArrayList;

public class Professor extends Usuario{

    private ArrayList<Turma> turmas = new ArrayList<>();
    private Disciplina disciplina;

    private ArrayList<Avaliacao> avaliacoesRealizadas = new ArrayList<>();

    private static ArrayList<Professor> professores = new ArrayList<>();

    public Professor(String nome, String senha, int idade, String endereco) {
        super(nome, senha, idade, endereco);
    }

    public void registrarAvaliacao(Avaliacao avaliacao){
        this.avaliacoesRealizadas.add(avaliacao);
    }

    public String listarAvaliacoes(){

        String listaAvaliacoes = "";
        int cont = 1;
        double somaNotas = 0;

        for(Avaliacao avaliacao : avaliacoesRealizadas){
            listaAvaliacoes += cont + " - Aluno: " + avaliacao.getAluno().getNome() + " Turma: " + avaliacao.getAluno().getTurma() + " Nota: " + avaliacao.getNota() + " Média: " + avaliacao.getAluno().getMedia(this.disciplina) + "\n";
            somaNotas += avaliacao.getNota();
        }
        listaAvaliacoes += "Média Geral: " + somaNotas / avaliacoesRealizadas.size() + "\n";
        return listaAvaliacoes;
    }

    public String listarTurmas(){

        String turmasToString = "";
        int cont = 1;

        for(Turma turma : turmas){
            turmasToString += cont + " - " + turma.numero + "\n";
        }

        return turmasToString;
    }

    public static String listarProfessores(){
        String listaProfessores = "";
        int cont = 1;
        for(Professor professor : professores){
            listaProfessores += "\n" + cont + " - " + professor.getNome();
        }
        return listaProfessores;
    }

    public static ArrayList<Professor> getProfessores() {
        return professores;
    }

    public ArrayList<Turma> getTurmas() {
        return turmas;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setTurmas(Turma turma){
        this.turmas.add(turma);
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public String menu(){
        return """
                   MENU
               1 - Registrar Notas
               2 - Ver notas/média
               0 - Deslogar
               """;
    }

}
