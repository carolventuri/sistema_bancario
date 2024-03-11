import java.util.Scanner;

public abstract class ContaBancaria {
    protected Pessoa titular;
    protected Banco banco;
    protected int nroConta;
    protected double saldo;
    protected String senha;
    protected int tipoConta;

    public ContaBancaria(Banco banco, int nroConta, Pessoa titular, double saldo, String senha, int tipoConta) {
        this.banco = banco;
        this.nroConta = nroConta;
        this.titular = titular;
        this.saldo = saldo;
        this.senha = senha;
        this.tipoConta = tipoConta;
    }

    public void info(){
        System.out.println("Nome do banco: " + this.banco.getNomeBanco());
        System.out.println ("Número da conta: " + this.nroConta);
        System.out.println("Titular: " + this.titular.nome+ " " + this.titular.sobrenome);
        System.out.println ("Saldo: " + this.saldo);
    }

    public void novoMes(){
    }

    public void saque (){
        Scanner tec = new Scanner (System.in);
        System.out.println("Digite a senha para realizar o saque:");
        tec.nextLine();
        boolean senhaCorreta = verificaSenha();
        if (senhaCorreta){
            System.out.println("Digite o valor a ser sacado:");
            double valor = tec.nextDouble();
            if (valor > saldo){
                System.out.println("Saldo insuficiente");
            }else{
                saldo -= valor;
            }
        }
    }

    public void saque (double valor){
        Scanner tec = new Scanner (System.in);
        System.out.println("Digite a senha para realizar o saque:");
        tec.nextLine();
        boolean senhaCorreta = verificaSenha();
        if (senhaCorreta){
            if (valor > saldo){
                System.out.println("Saldo insuficiente");
            }else{
                saldo -= valor;
                System.out.println("Saque realizado com sucesso!");
            }
        }
    }

    public void deposito (){
        Scanner tec = new Scanner (System.in);
        System.out.println("Digite o valor a ser depositado:");
        double valor = tec.nextDouble();
        saldo += valor;
    }

    public void deposito (double valor){
        saldo += valor;
    }

    public boolean verificaSenha (String senhaVerificar){
        if (senhaVerificar.equals(this.senha)){
            System.out.println("As senhas coincidem. Senha verificada com sucesso!");
            return true;
        }else{
            System.out.println("A senha digitada não corresponte à senha da conta");
            return false;
        }
    }

    public boolean verificaSenha (){
        Scanner tec = new Scanner (System.in);
        System.out.println("Digite a senha para verificação:");
        String senhaVerificar = tec.nextLine();
        if (senhaVerificar.equals(this.senha)){
            System.out.println("As senhas coincidem. Senha verificada com sucesso!");
            return true;
        }else{
            System.out.println("A senha digitada não corresponte à senha da conta");
            return false;
        }
    }
    public int getNroConta(){
        return this.nroConta;
    }
}