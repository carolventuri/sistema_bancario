import java.util.Scanner;

public class ContaCorrente extends ContaBancaria {

    private double taxasMensais;

    public ContaCorrente(Banco banco, int nroConta, Pessoa titular,
                         double saldo, String senha, double taxasMensais, int tipoConta) {
        super(banco, nroConta, titular, saldo, senha, tipoConta);
        this.taxasMensais = taxasMensais;
    }

    public void info(){
        System.out.println("Informações da Conta Corrente: ");
        super.info();
        System.out.println("Taxas Mensais: " + this.taxasMensais);
    }

    public void novoMes(){
        this.saldo -= this.taxasMensais;
    }
}