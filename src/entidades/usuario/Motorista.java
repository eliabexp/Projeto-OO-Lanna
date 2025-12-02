package entidades.usuario;

import entidades.corrida.Corrida;
import entidades.corrida.Rota;

import static main.Main.sc;

public class Motorista extends Usuario {
    private StatusMotorista status;
    private Habilitacao habilitacao;
    private Veiculo veiculo;

    public Motorista(String nome, String email, String cpf, String numeroDeTelefone, String senha, Habilitacao habilitacao, Veiculo veiculo) {
        super(nome, email, cpf, numeroDeTelefone, senha);
        this.habilitacao = habilitacao;
        this.veiculo = veiculo;
        this.status = StatusMotorista.ONLINE;
    }

    public static Motorista cadastrar() {
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

        System.out.println("Agora, as informações de habilitação");
        Habilitacao habilitacao = Habilitacao.cadastrarHabilitacao();

        System.out.println("Agora, insira os dados do seu veículo");
        Veiculo veiculo = Veiculo.cadastrarVeiculo();

        return new Motorista(nome, email, cpf, numeroDeTelefone, senhaHash, habilitacao, veiculo);
    }

    public StatusMotorista getStatus() {
        return status;
    }

    public void setStatus(StatusMotorista status) {
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

    public boolean receberCorrida(Corrida corrida) {
        Rota rota = corrida.getRota();
        Passageiro passageiro = corrida.getPassageiro();
        System.out.printf("%s (%.2f★) solicitou uma viagem de %s para %s (%.2f km)\n", passageiro.getNome(), passageiro.getNota(), rota.getPartida().getNome(), rota.getDestino().getNome(), rota.calcularDistancia());
        System.out.printf("Valor da corrida: R$ %.2f\n", corrida.getPreco());

        System.out.println("Deseja aceitar a corrida? (s|n)");
        String resposta = sc.nextLine();

        return resposta.equalsIgnoreCase("s");
    }

}