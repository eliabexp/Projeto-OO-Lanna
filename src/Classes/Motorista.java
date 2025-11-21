package Classes;

import Enums.TipoDoCarro;

import java.util.Scanner;

public class Motorista extends Usuario {
    private Habilitacao habilitacao;
    private Veiculo veiculo;

    public Motorista(String nome, String email, String cpf, String numeroDeTelefone, String senha, Habilitacao habilitacao, Veiculo veiculo) {
        super(nome, email, cpf, numeroDeTelefone, senha);
        this.habilitacao = habilitacao;
        this.veiculo = veiculo;
    }

    public static Motorista cadastrarMotorista(Scanner sc) {
        System.out.println("---Cadastro---");
        System.out.println("Nome:");
        String nome = sc.nextLine();
        System.out.println("Email:");
        String email = sc.nextLine();
        System.out.println("CPF [999.999.999-99]:");
        String cpf = sc.nextLine();
        System.out.println("Numero de Telefone [(99) 99999-9999]");
        String numeroDeTelefone = sc.nextLine();
        System.out.println("Senha:");
        String senhaHash = sc.nextLine();

        Habilitacao habilitacao = Habilitacao.cadastrarHabilitacao(sc);
        Veiculo veiculo = Veiculo.cadastrarVeiculo(sc);

        return new Motorista(nome, email, cpf, numeroDeTelefone, senhaHash, habilitacao, veiculo);
    }

    protected Habilitacao getHabilitacao() {
        return habilitacao;
    }
    protected void setHabilitacao(Habilitacao habilitacao) {
        this.habilitacao = habilitacao;
    }

    protected Veiculo getVeiculo() {
        return veiculo;
    }
    protected void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}