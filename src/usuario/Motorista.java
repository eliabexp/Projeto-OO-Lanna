package usuario;

import corrida.Corrida;
import corrida.Rota;

import java.util.Scanner;

public class Motorista extends Usuario {
    private StatusMotorista status;
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

    public StatusMotorista getStatus() {
        return status;
    }

    protected void setStatus(StatusMotorista status) {
        this.status = status;
    }

    protected Habilitacao getHabilitacao() {
        return habilitacao;
    }

    protected void setHabilitacao(Habilitacao habilitacao) {
        this.habilitacao = habilitacao;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    protected void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public boolean solicitarCorrida(Corrida corrida) {
        Rota rota = corrida.getRota();
        System.out.println(corrida.getPassageiro().getNome() + " solicitou uma viagem de " + rota.getPartida().getNome() + " para " + rota.getDestino().getNome());
        System.out.printf("\nValor da corrida: R$ .%2f%n", corrida.getPreco());

        System.out.println("Deseja aceitar a corrida? (s|n)");
        String resposta = sc.nextLine();

        if (!resposta.equalsIgnoreCase("s")) return false;

        this.status = StatusMotorista.EM_CORRIDA;
        return true;
    }
}