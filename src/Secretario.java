public class Secretario extends Usuario{

    private int boletinsGerados = 0;

    public Secretario(String nome, String senha, int idade, String endereco) {
        super(nome, senha, idade, endereco);
    }

    public int getBoletinsGerados() {
        return boletinsGerados;
    }


}
