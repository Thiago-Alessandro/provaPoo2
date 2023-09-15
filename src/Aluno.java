import java.util.ArrayList;

public class Aluno extends Usuario{

    private Turma turma; //disciplina?!?

    ArrayList<Avaliacao> avaliacoes = new ArrayList<>();


    public Aluno(String nome, String senha, int idade, String endereco) {
        super(nome, senha, idade, endereco);
    }

    public String verBoletim(){

        double quantiaAvaliacoesPoo = 0;
        double somaNotasPoo = 0;

        double quantiaAvaliacoesJava = 0;
        double somaNotasJava = 0;

        double quantiaAvaliacoesLogica = 0;
        double somaNotasLogica = 0;

        for(Avaliacao avaliacao: avaliacoes){
            if(avaliacao.getAluno() == this){
                if(avaliacao.getDisciplina().getNome().equals("Programação Java")){

                    somaNotasJava += avaliacao.getNota();
                    quantiaAvaliacoesJava++;

                } else if (avaliacao.getDisciplina().getNome().equals("Programação Orientada a Objetos")){

                    somaNotasPoo += avaliacao.getNota();
                    quantiaAvaliacoesPoo++;

                } else if(avaliacao.getDisciplina().getNome().equals(" Lógica de Programação")){

                    somaNotasLogica += avaliacao.getNota();
                    quantiaAvaliacoesLogica++;
                }
            }
        }
        String boletim = "";
        boletim += "\nMédia de Programação Java: " + (somaNotasJava / quantiaAvaliacoesJava);
        boletim += "\nMédia de Programação Orientada à Objetos: " + (somaNotasPoo / quantiaAvaliacoesPoo);
        boletim += "\nMédia de Lógica de Programação: " + (somaNotasLogica / quantiaAvaliacoesLogica);
        return boletim;
    }

    public Turma getTurma() {
        return turma;
    }

    public double getMedia(Disciplina discplina){
        double somaNotas = 0;
        for(Avaliacao avaliacao : avaliacoes){
            if(discplina == avaliacao.getDisciplina()){
                somaNotas += avaliacao.getNota();
            }
        }
        return somaNotas / avaliacoes.size();
    }

    public void setAvaliacoes(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public String menu(){
        return """
                   MENU
               1 - Ver Boletim
               0 - Deslogar
               """;
    }

}
