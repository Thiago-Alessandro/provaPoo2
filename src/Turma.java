import java.util.ArrayList;

public class Turma {

    int numero;

    private ArrayList<Usuario> participantes = new ArrayList<>();

    public static ArrayList<Turma> turmas;

    public Turma(int numero){
        this.numero = numero;
        turmas.add(this);
    }

    public ArrayList<Usuario> getParticipantes() {
        return participantes;
    }

    public void adicionarParticipante(Usuario usuario){
        this.participantes.add(usuario);
    }

    public static String listarTurmas(){
        String listaTurmas = "";
        int cont = 1;
        for(Turma turma : turmas){
            listaTurmas += cont + " - Número: " + turma.numero;
        }
        return listaTurmas;
    }

    public static ArrayList<Turma> getTurmas() {
        return turmas;
    }
}
