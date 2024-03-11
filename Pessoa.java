import java.util.ArrayList;

public class Pessoa {
    String nome, sobrenome;
    int idade;
    private String CPF;
    private ArrayList<ContaBancaria> contasBancarias;

    Pessoa(String nome, String sobrenome, int idade, String CPF) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.CPF = CPF;

        this.contasBancarias = new ArrayList<>();
    }

    public String getCPF() {
        return this.CPF;
    }

    void info() {
        System.out.println("Informações do titular da conta: ");
        System.out.println("Nome Completo: " + this.nome + " " + this.sobrenome);
        System.out.println("Idade: " + this.idade);
        System.out.println("CPF: " + this.CPF);
    }

    public void infoContas() {
        System.out.println("Contas Bancárias do titular: " + this.nome + " " + this.sobrenome);
        for (int i = 0; i < this.contasBancarias.size(); i++) {
            contasBancarias.get(i).info();
            System.out.println();
        }
    }

    public void adicionaContaCorrente (ContaCorrente contaCorrente) {
        this.contasBancarias.add (contaCorrente);
    }

    public void adicionaContaPoupanca (ContaPoupanca contaPoupanca) {
        this.contasBancarias.add (contaPoupanca);
    }
}
