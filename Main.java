import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


public class Main {
    static Scanner tec = new Scanner (System.in);
    static ArrayList<Banco> bancos = new ArrayList<>();
    static ArrayList <Pessoa> pessoas = new ArrayList<>();


    public static int geranroBanco() {
        int nroBanco;
        Random gerador = new Random();
        do {
            nroBanco = gerador.nextInt(1000);
        } while (numeroestaRepetido(nroBanco));
        return nroBanco;
    }
    private static boolean numeroestaRepetido (int numero) {
        for (int i = 0; i < bancos.size(); i++) {
            if (bancos.get(i).getnroBanco() == numero) {
                return true;
            }
        }
        return false;
    }

    //opcao 1 do menu
    public static void cadastraBanco(){
        int achou =0;
        Scanner tec = new Scanner(System.in);
        System.out.println("Digite o nome do Banco: ");
        String nome = tec.nextLine();
        System.out.println("Digite o CNPJ do Banco: ");
        String CNPJ = tec.nextLine();
        int nroBanco = geranroBanco();

        for (Banco banco: bancos){
            if (banco.getCNPJ().equals(CNPJ)){
                System.out.println("Banco já cadastrado no sistema. O número de cadastro é: " + banco.getnroBanco());
                achou++;
                break;
            }
        }
        if (achou == 0){
            bancos.add (new Banco (nome, CNPJ, nroBanco));
            System.out.println("Banco cadastrado com sucesso!");
        }
    }

    //opcao 2 do menu
    public static void cadastraCliente(){
        int achou = 0;
        Scanner tec = new Scanner(System.in);
        System.out.println("Digite o nome do Cliente: ");
        String nome = tec.nextLine();
        System.out.println("Digite o sobrenome do Cliente: ");
        String sobrenome = tec.nextLine();
        System.out.println("Digite a idade do Cliente: ");
        int idade = tec.nextInt();
        System.out.println("Digite o CPF do Cliente: ");
        tec.nextLine();
        String CPF = tec.nextLine();

        for (Pessoa pessoa: pessoas){
            if (pessoa.getCPF().equals(CPF)){
                System.out.println("Pessoa já cadastrado no sistema.");
                achou++;
                break;
            }
        }
        if (achou == 0){
            pessoas.add (new Pessoa (nome, sobrenome, idade, CPF));
        }
    }

    //opcao 3 do menu
    public static void cadastraConta () {
        int achou = 0;
        Banco banco = null;
        Pessoa titular = null;

        System.out.println("Digite o numero do Banco: ");
        int nroBanco = tec.nextInt();
        for (int i = 0; i < bancos.size(); i++) {
            if (bancos.get(i).getnroBanco() == nroBanco) {
                banco = bancos.get(i);
                achou++;
                break;
            }
        }
        if (achou == 0){
            System.out.println("Banco não cadastrado no sistema.");
            return;
        }

        System.out.println("Por favor, digite o CPF do titular: ");
        tec.nextLine();
        String CPFdigitado = tec.nextLine();
        achou = 0;
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCPF().equals(CPFdigitado)) {
                titular = pessoa;
                achou++;
                break;
            }
        }
        if (achou == 0){
            System.out.println("Pessoa não cadastrada no sistema.");
            return;
        }

        System.out.println("Por favor, digite a senha a ser cadastrada: ");
        String senhaCadastrar = tec.nextLine();
        System.out.println("Por favor, digite novamente a senha: ");
        String senha = tec.nextLine();
        if (senhaCadastrar.equals(senha)) {
            System.out.println("As senhas coincidem. Senha cadastrada com sucesso!");
            banco.criarConta(titular, senha, 0);
        } else {
            System.out.println("A senha digitada não corresponte à senha da conta");
        }
    }

    //opcao 4 do menu
    public static void bancosCadastrados(){
        for (Banco banco: bancos){
            banco.infoBanco();
            System.out.println();
        }
    }

    //opcao 5 do menu
    public static void contasCadastradas(){
        int achou = 0;
        System.out.println("Digite o número do banco para consultar as contas cadastradas: ");
        int numeroBanco = tec.nextInt();
        for (Banco banco: bancos){
            if (banco.getnroBanco() == numeroBanco){
                banco.infoContas();
                achou++;
            }
        }
        if (achou == 0){
            System.out.println("Banco não cadastrado no sistema.");
        }
    }

    //opcao 6 do menu
    public static void clientesCadastrados(){
        int achou = 0;
        System.out.println("Digite o número do banco para consultar os clientes cadastrados: ");
        int numeroBanco = tec.nextInt();
        for (Banco banco: bancos){
            if (banco.getnroBanco() == numeroBanco){
                banco.infoClientes();
                achou++;
            }
        }
        System.out.println();
        if (achou ==0){
            System.out.println("Banco não cadastrado no sistema.");
        }
    }

    //opcao 7 do menu
    public static void consultaContasCliente(){
        System.out.println("Digite o CPF do cliente para obter as informações sobre suas contas:");
        tec.nextLine();
        String CPF = tec.nextLine();
        for (Pessoa pessoa: pessoas){
            if (pessoa.getCPF().equals(CPF)){
                pessoa.infoContas();
                System.out.println();
            }
        }
    }

    //opcao 8 do menu
    public static void infoConta (){
        int achou = 0;
        System.out.println("Digite o número do banco:");
        int numBanco = tec.nextInt();
        for (int i=0; i<bancos.size(); i++){
            if (bancos.get(i).getnroBanco() == numBanco){
                System.out.println("Digite o número da conta:");
                int numConta = tec.nextInt();
                bancos.get(i).infoContadoBanco(numConta);
                achou++;
            }
        }
        if (achou == 0){
            System.out.println("Banco não cadastrado no sistema.");
        }
    }

    //opcao 9 do menu
    public static void passagemMes(){
        int achou = 0;
        System.out.println("Digite o número do banco:");
        int numBanco = tec.nextInt();
        for (int i=0; i<bancos.size(); i++){
            if (bancos.get(i).getnroBanco() == numBanco){
                System.out.println("Digite o número da conta:");
                int numConta = tec.nextInt();
                bancos.get(i).atualizaMes(numConta);
                System.out.println("Passagem do mês atualizada com sucesso!");
                achou++;
            }
        }
        if (achou ==0){
            System.out.println("Banco não cadastrado no sistema.");
        }
    }

    //opcao 10 do menu
    public static void deposito(){
        int achou = 0;
        System.out.println("Digite o número do banco:");
        int numBanco = tec.nextInt();
        for (int i=0; i<bancos.size(); i++){
            if (bancos.get(i).getnroBanco() == numBanco){
                System.out.println("Digite o número da conta:");
                int numConta = tec.nextInt();
                bancos.get(i).deposito (numConta);
                achou++;
            }
        }
        if (achou ==0){
            System.out.println("Banco não cadastrado no sistema.");
        }
    }

    //opcao 11 do menu
    public static void saque(){
        int achou = 0;
        System.out.println("Digite o número do banco:");
        int numBanco = tec.nextInt();
        for (int i=0; i<bancos.size(); i++){
            if (bancos.get(i).getnroBanco() == numBanco){
                bancos.get(i).saque();
                achou++;
            }
        }
        if (achou ==0){
            System.out.println("Banco não cadastrado no sistema.");
        }
    }

    //opcao 12 do menu
    public static void encerrarConta(){
        int achou = 0;
        System.out.println("Digite o número do banco:");
        int numBanco = tec.nextInt();
        for (int i=0; i<bancos.size(); i++){
            if (bancos.get(i).getnroBanco() == numBanco){
                System.out.println("Digite o número da conta:");
                int numConta = tec.nextInt();
                bancos.get(i).fecharConta(numConta);
                return;
            }
        }
        if (achou ==0){
            System.out.println("Banco não cadastrado no sistema.");
        }
    }

    public static void main(String[] args) {

        bancos.add (new Banco ("Banrisul", "45304938464", geranroBanco()));
        bancos.add (new Banco ("Banco do Brasil", "44453022224", geranroBanco()));
        bancos.add (new Banco ("Itaú", "297257908788", geranroBanco()));

        pessoas.add (new Pessoa ("Vanessa", "Venturi", 39,"001"));
        pessoas.add (new Pessoa ("Gabriel", "Venturi", 49,"002"));
        pessoas.add (new Pessoa ("Caroline", "Venturi", 29,"003"));

        bancos.get(0).criaConta(pessoas.get(0), "345", 100, 1);
        bancos.get(1).criaConta(pessoas.get(1),"345", 200, 2);
        bancos.get(2).criaConta(pessoas.get(2),"345", 300, 1);

        int opcao = 34;

        while (opcao != 0) {

            System.out.println();
            System.out.println (">>>>>> Menu <<<<<<<");
            System.out.println ("1 - Cadastrar banco");
            System.out.println ("2 - Cadastrar cliente");
            System.out.println ("3 - Cadastrar conta");
            System.out.println ("4 - Consultar bancos cadastrados");
            System.out.println ("5 - Consultar contas cadastradas");
            System.out.println ("6 - Consultar clientes cadastrados");
            System.out.println ("7 - Consultar as contas do cliente");
            System.out.println ("8 - Consultar informações da conta");
            System.out.println ("9 - Simular a passagem de mês");
            System.out.println ("10 - Realizar depósito");
            System.out.println ("11 - Realizar saque");
            System.out.println ("12 - Fechar conta");
            System.out.println ("0 - Sair do sistema");
            System.out.println();

            System.out.println ("Digite um número para escolher uma opçao do menu:");

            opcao = tec.nextInt();

            switch (opcao){
                case 1 -> cadastraBanco();
                case 2 -> cadastraCliente();
                case 3 -> cadastraConta();
                case 4 -> bancosCadastrados();
                case 5 -> contasCadastradas();
                case 6 -> clientesCadastrados();
                case 7 -> consultaContasCliente();
                case 8 -> infoConta();
                case 9 -> passagemMes();
                case 10 -> deposito();
                case 11 -> saque();
                case 12 -> encerrarConta();
                case 0 -> System.out.println("Até logo!");
                default -> System.out.println("Digite um número entre as opções do menu (de 0 a 12)");
            }
        }
    }
}