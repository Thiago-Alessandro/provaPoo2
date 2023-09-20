import java.util.ArrayList;

public class Usuario {

    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    private String nome;
    private int idade;
    private String endereco;
    private String senha;

    public Usuario(String nome, String senha, int idade, String endereco){
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.senha = senha; //adicionado
        usuarios.add(this);
    }

    public String menu(){
        return "";
    }

    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public int getIdade() {
        return idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public static Usuario getUsuario(String nome, String senha){
        for (Usuario usuario : usuarios){
            if(usuario.nome.equals(nome) && usuario.senha.equals(senha)){
                return usuario;
            }
        }
        return null;
    }

}
