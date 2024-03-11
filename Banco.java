import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Banco{
    Scanner tec = new Scanner (System.in);
    private String nome, CNPJ;
    private int nroBanco;
    private ArrayList<ContaBancaria> contasBancarias;

    public Banco (String nome, String CNPJ, int nroBanco) {
        this.nome = nome;
        this.CNPJ = CNPJ;
        this.nroBanco = nroBanco;

        this.contasBancarias = new ArrayList<>();
    }

    public int getnroBanco() {
        return this.nroBanco;
    }

    public String getCNPJ(){
        return this.CNPJ;
    }

    public String getNomeBanco() {
        return this.nome;
    }

    public int geranroConta() {
        int nroConta;
        Random gerador = new Random();
        do {
            nroConta = gerador.nextInt(1000);
        } while (numeroestaRepetido(nroConta));
        return nroConta;
    }
    public boolean numeroestaRepetido (int numero) {
        for (int i = 0; i < contasBancarias.size(); i++) {
            if (contasBancarias.get(i).getNroConta() == numero) {
                return true;
            }
        }
        return false;
    }

    public void infoBanco (){
        System.out.println("Informações do banco número " + this.nroBanco + ":");
        System.out.println("Nome: " + this.nome);
        System.out.println("CNPJ: " + this.CNPJ);
    }

    public void infoContas (){
        System.out.println("Contas Bancárias do banco " + this.getNomeBanco() + ":");
        for (int i = 0; i < this.contasBancarias.size(); i++){
            this.contasBancarias.get(i).info();
            System.out.println();
        }
    }

    public void infoContadoBanco (int numConta){
        int achou = 0;
        for (int i = 0; i < this.contasBancarias.size(); i++){
            if (this.contasBancarias.get(i).getNroConta() == numConta) {
                System.out.println("Informações da conta: ");
                this.contasBancarias.get(i).info();
                achou++;
                System.out.println();
            }
        }
        if (achou == 0){
            System.out.println("Conta não encontrada.");
        }
    }

    public void atualizaMes(int numConta){
        int achou = 0;
        for (int i = 0; i < this.contasBancarias.size(); i++){
            if (this.contasBancarias.get(i).getNroConta() == numConta) {
                this.contasBancarias.get(i).novoMes();
                achou++;
            }
        }
        if (achou == 0){
            System.out.println("Conta não encontrada.");
        }
    }

    public void deposito(int numConta){
        int achou = 0;
        for (int i = 0; i < this.contasBancarias.size(); i++){
            if (this.contasBancarias.get(i).getNroConta() == numConta) {
                this.contasBancarias.get(i).deposito();
                System.out.println("Depósito realizado com sucesso!");
                System.out.println();
                achou++;
            }
        }
        if (achou == 0){
            System.out.println("Conta não encontrada.");
        }
    }

    public void saque (){
        System.out.println("Digite o número da conta: ");
        int numConta = tec.nextInt();
        int achou = 0;
        for (int i = 0; i < this.contasBancarias.size(); i++){
            if (this.contasBancarias.get(i).getNroConta() == numConta) {
                this.contasBancarias.get(i).saque();
                System.out.println();
                achou++;
            }
        }
        if (achou == 0){
            System.out.println("Conta não encontrada.");
        }
    }

    public void infoClientes(){
        int achou = 0;
        for (int i = 0; i < this.contasBancarias.size(); i++){
            System.out.println("Clientes do banco " + this.getNomeBanco() + ": ");
            this.contasBancarias.get(i).titular.info();
            System.out.println();
            achou++;
        }
        if (achou ==0){
            System.out.println("O banco " + this.getNomeBanco() + "não possui clientes cadastrados.");
        }
    }

    public void criarConta(Pessoa titular, String senha, double saldo) {
        System.out.println("Por favor, digite 1 para criar uma conta corrente ou 2 para criar uma conta poupança: ");
        int tipoConta = tec.nextInt();
        if (tipoConta == 1) {
            System.out.println("Por favor, digite o valor das taxas mensais: ");
            double taxasMensais = tec.nextDouble();
            contasBancarias.add(new ContaCorrente(this, geranroConta(), titular, saldo, senha, taxasMensais, 1));
            titular.adicionaContaCorrente ((ContaCorrente) contasBancarias.get(contasBancarias.size() - 1));
            System.out.println("Conta Corrente cadastrada com sucesso!");
        } else if (tipoConta == 2) {
            System.out.println("Por favor, digite o valor do rendimento mensal: ");
            double rendimento = tec.nextDouble();
            contasBancarias.add(new ContaPoupanca(this, geranroConta(), titular, saldo, senha, rendimento, 2));
            titular.adicionaContaPoupanca ((ContaPoupanca) contasBancarias.get(contasBancarias.size() - 1));
            System.out.println("Conta Poupança cadastrada com sucesso!");
        } else {
            System.out.println("Opção inválida.");
        }
    }
    //fiz esse metodo abaixo só para as contas pré cadastradas
    public void criaConta(Banco this, Pessoa titular, String senha, double saldo, int tipoConta) {
        if (tipoConta == 1) {
            double taxasMensais = 5;
            contasBancarias.add(new ContaCorrente(this, geranroConta(), titular, saldo, senha, taxasMensais, 1));
            titular.adicionaContaCorrente ((ContaCorrente) contasBancarias.get(contasBancarias.size() - 1));
        } else if (tipoConta == 2) {
            double rendimento = 0.005;
            contasBancarias.add(new ContaPoupanca(this, geranroConta(), titular, saldo, senha, rendimento, 2));
            titular.adicionaContaPoupanca ((ContaPoupanca) contasBancarias.get(contasBancarias.size() - 1));
        }
    }

        public void fecharConta (int numeroConta){
            for (int i = 0; i < this.contasBancarias.size(); i++){
                if (this.contasBancarias.get(i).nroConta == numeroConta){
                    if (this.contasBancarias.get(i).saldo > 0){
                        System.out.println("Favor zerar o saldo da conta antes de removê-la.");
                        return;
                    }else{
                        this.contasBancarias.remove(i);
                        System.out.println("Conta fechada com sucesso.");
                        return; // Termina o método após encontrar a conta e removê-la
                    }
                }
            }
            System.out.println("Conta não encontrada.");
        }
    }
