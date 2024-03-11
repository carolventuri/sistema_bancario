public class ContaPoupanca extends ContaBancaria {

    private double rendimento; //considerei 0,005 ou 0,5% ao mês
    private int saquesMensais;

    public ContaPoupanca(Banco banco, int nroConta, Pessoa titular,
                         double saldo, String senha, double rendimento, int tipoConta) {
        super(banco, nroConta, titular, saldo, senha, tipoConta);
        this.rendimento = rendimento;
    }

    public void info(){
        double variacaoPoupanca = (this.saldo * this.rendimento); //quanto em R$ foi o rendimento do mês
        double rendimentoMensal = ((variacaoPoupanca*100)/this.saldo); //quanto em % foi o rendimento do mês
        int saquesRestantes = (3 - this.saquesMensais);

        System.out.println("Informações da Conta Poupança: ");
        super.info();
        System.out.println("Variação da poupança no mês (R$): " + variacaoPoupanca);
        System.out.println("Rendimento mensal (%): " + rendimentoMensal);
        System.out.println("Saques restantes no mês: " + saquesRestantes);
    }

    public void novoMes(){
        this.saldo += (this.saldo*this.rendimento);
        this.saquesMensais = 0;
    }

    public void saque(double valor){
        if (this.saquesMensais < 3){
            super.saque(valor);
            this.saquesMensais++;
        } else {
            System.out.println("Quantidade máxima de saques excedida.");
        }
    }

    public void saque(){
        if (this.saquesMensais < 3){
            super.saque();
            this.saquesMensais++;
        } else {
            System.out.println("Quantidade máxima de saques excedida.");
        }
    }
}