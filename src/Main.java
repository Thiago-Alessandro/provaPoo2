import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Usuario usuarioLogado;

    public static void main(String[] args) {

        do {

            do {

                switch (telaInicial()){
                    case 1 -> cadastrar();
                    case 2 -> logar();
                    case 0 -> System.exit(0);
                    default -> System.out.println("Insira uma opcção válida!");
                }

            } while (usuarioLogado == null);

            do{
                menu();

            }while (usuarioLogado != null);

        } while(true);
    }

    private static int telaInicial(){
        System.out.println("""
                    TELA INICIAL
                1 - Cadastrar usuario
                2 - Logar
                0 - Sair
                """);
        return sc.nextInt();
    }

    private static void cadastrar(){
        System.out.println("Qual tipo de usuário desejas cadastrar?\n1 - Aluno\n2 - Professor\n3 - Secretário\n0 - Voltar");
        int opcao = sc.nextInt();

        System.out.println("Digite seu nome: ");
        String nome = sc.next();
        System.out.println("Digite sua senha: ");
        String senha = sc.next();
        System.out.println("Digite sua idade: ");
        int idade = sc.nextInt();
        System.out.println("Digite seu endereco: ");
        String endereco = sc.next();

        do {
            switch (opcao) {
                case 1 -> new Aluno(nome, senha, idade, endereco);
                case 2 -> new Professor(nome, senha, idade, endereco);
                case 3 -> new Secretario(nome, senha, idade, endereco);
                case 0 -> {}
                default -> System.out.println("Insira uma opção válida!");
            }
        }while(opcao != 0);
    }

    private static void logar(){
        System.out.println("Insira seu nome: ");
        String nome = sc.next();
        System.out.println("Insira sua senha: ");
        String senha = sc.next();

        usuarioLogado = Usuario.getUsuario(nome, senha);

        if(usuarioLogado == null){
            System.out.println("nome e/ou senha incorretos!");
        } else {
            System.out.println("Login sucedido!");
        }
    }

    private static void menu(){

        System.out.println(usuarioLogado.menu());
        int opcao =  sc.nextInt();

        if(usuarioLogado instanceof Aluno){

            switch (opcao){
                case 1 -> ((Aluno)usuarioLogado).verBoletim();
                case 0 -> usuarioLogado = null;
                default -> System.out.println("Insira uma opcção válida!");
            }

        } else if(usuarioLogado instanceof Professor){

            switch (opcao){
                case 1 -> registrarNotas();
                case 2 -> System.out.println(((Professor)usuarioLogado).listarAvaliacoes());
                case 0 -> usuarioLogado = null;
                default -> System.out.println("Insira uma opcção válida!");
            }
        } else if (usuarioLogado instanceof Secretario){

            switch (opcao){
                case 1 -> atribuirDisciplinaAProfessor();
                case 2 -> adicionarUsuarioATurma();
                case 3 -> adicionarNovaTurma();
                case 4 -> gerarBoletins();
                case 0 -> usuarioLogado = null;
                default -> System.out.println("Insira uma opcção válida!");
            }
        }
    }

    private static void registrarNotas(){
        if(((Professor)usuarioLogado).getTurmas().size() > 0) {
            System.out.println("De qual turma quer registrar as notas?");
            System.out.println(((Professor) usuarioLogado).listarTurmas());
            int opcao = sc.nextInt() - 1;
            if (opcao < 0 || opcao > ((Professor) usuarioLogado).getTurmas().size()) {
                System.out.println("Opção inválida!");
                return;
            }
            Turma turmaEscolhida = ((Professor) usuarioLogado).getTurmas().get(opcao);
            for (Usuario aluno : turmaEscolhida.getParticipantes()) {
                if (!(aluno instanceof Professor)) {
                    System.out.println("Digite a nota do aluno " + aluno.getNome() + " ");
                    double nota = sc.nextDouble();
                    Avaliacao avaliacao = new Avaliacao(nota, ((Professor) usuarioLogado).getDisciplina(), ((Aluno)aluno));
                    ((Aluno)aluno).setAvaliacoes(avaliacao);
                    ((Professor)usuarioLogado).registrarAvaliacao(avaliacao);
                }
            }
        } else {
            System.out.println("Você não possui turmas associadas à você!");
        }
    }

    private static void adicionarUsuarioATurma(){

        System.out.println("A qual turma deseja adicionar um participante: ");
        System.out.println(Turma.listarTurmas());
        int opcao = sc.nextInt() - 1;
        if(opcao < 0 || opcao > Turma.getTurmas().size()){
            System.out.println("Turma inválida!");
            return;
        }
        Turma turmaEscolhida = Turma.getTurmas().get(opcao);

        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        for(Usuario usuario : Usuario.getUsuarios()){
            if((usuario instanceof Aluno && ((Aluno)usuario).getTurma() == null) ||
                    !(usuario instanceof Professor && ((Professor)usuario).getTurmas().contains(turmaEscolhida))
            ){
                listaUsuarios.add(usuario);
            }
        }
        if(listaUsuarios.size()>1){
            int cont = 1;
            System.out.println("Qual usuario deseja adicionar à turma: ");
            for(Usuario usuario : listaUsuarios){
                System.out.println(cont + " - " + usuario.getNome());
                cont++;
            }
            int indice = sc.nextInt() - 1;
            Usuario usuario = listaUsuarios.get(indice);
            if(usuario instanceof Aluno){
                ((Aluno)usuario).setTurma(turmaEscolhida);
                turmaEscolhida.adicionarParticipante(usuario);
            } else if(usuario instanceof Professor){
                ((Professor)usuario).setTurmas(turmaEscolhida);
                turmaEscolhida.adicionarParticipante(usuario);
            }
        } else {
            System.out.println("Não há usuários que possam ser atribuidos à esta turma");
        }
    }

    private static void adicionarNovaTurma(){
        System.out.println("Digite o numero da turma: ");
        int numero = sc.nextInt();
        new Turma(numero);
        System.out.println("Turma registrada com sucesso!");
    }

    private static void gerarBoletins(){
        System.out.println("Selecione a turma para gerar um boletim: ");
        System.out.println(Turma.listarTurmas());
        int indice = sc.nextInt() - 1;
        if(indice < 0 || indice > Turma.getTurmas().size()){
            System.out.println("Opção inválida!");
            return;
        }
        Turma turmaEscolhida = Turma.getTurmas().get(indice);
        for(Usuario usuario : turmaEscolhida.getParticipantes()){
            if(usuario instanceof Aluno){
                ((Aluno)usuario).verBoletim();
            }
        }
    }

    private static void atribuirDisciplinaAProfessor(){
        System.out.println("Qual professor deseja atribuir uma disciplina: ");
        System.out.println(Professor.listarProfessores());
        int indice = sc.nextInt();
        if(indice < 0 || indice > Professor.getProfessores().size()){
            System.out.println("Opcao invalida");
            return;
        }
        Professor professorEscolhido = Professor.getProfessores().get(indice);
        System.out.println("Qual disciplina deseja atribuir\n1 - POO\n2 - Lógica\n3 - Java");
        int opcao = sc.nextInt();

        switch (opcao){
            case 1 -> professorEscolhido.setDisciplina(new Disciplina(" Programação Orientada a Objetos", 3, 340));
            case 2 -> professorEscolhido.setDisciplina(new Disciplina(" Logica de Programação", 2, 100));
            case 3 -> professorEscolhido.setDisciplina(new Disciplina(" Programação Java", 1, 160));
            default -> System.out.println("opcao invalida");
        }

        System.out.println("Turma atribuida com sucesso");

    }
}
