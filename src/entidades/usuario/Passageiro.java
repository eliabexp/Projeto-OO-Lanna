package entidades.usuario;

import entidades.corrida.Corrida;
import entidades.corrida.Local;
import entidades.corrida.Rota;
import entidades.pagamento.CartaoCredito;
import entidades.pagamento.CartaoDebito;
import entidades.pagamento.FormaDePagamento;
import entidades.pagamento.PIX;
import exceptions.SaldoInsuficienteException;

import java.util.ArrayList;

import static main.Main.sc;

public class Passageiro extends Usuario {
    private ArrayList<FormaDePagamento> formasDePagamento = new ArrayList<>();
    private float saldo;

    public Passageiro(String nome, String email, String cpf, String numeroDeTelefone, String senha) {
        super(nome, email, cpf, numeroDeTelefone, senha);
    }

    public void solicitarCorrida(ArrayList<Motorista> motoristas) {
        if (saldo < 0)
            throw new SaldoInsuficienteException("Você tem pendências de débitos a serem pagos. Por favor, resolva-as antes de solicitar uma nova corrida.");

        System.out.println("Local de partida:");
        Local partida = Local.inserirLocal();
        System.out.println("Local de destino:");
        Local destino = Local.inserirLocal();
        System.out.println("Escolha a categoria da viagem: \n1: Luxo\n2: Comfort\n3: Comum\n4: Moto");
        TipoVeiculo tipoVeiculo = Veiculo.mapTipoVeiculo(sc.nextInt());
        sc.nextLine();

        Rota rota = new Rota(partida, destino);
        Corrida corrida = new Corrida(rota, this, tipoVeiculo);

        System.out.printf("Valor da corrida: R$ %.2f\n", corrida.getPreco());
        System.out.println("Deseja confirmar a viagem? (s|n):");
        String resposta = sc.nextLine();

        if (!resposta.equalsIgnoreCase("s")) {
            corrida.cancelar();
            System.out.println("Corrida cancelada!");
        }

        System.out.println("Buscando motorista...");
        corrida.buscarMotorista(motoristas);
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public static Passageiro cadastrar() {
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

        return new Passageiro(nome, email, cpf, numeroDeTelefone, senhaHash);
    }

    public void ListaMetodosDePagamento() {
        for (int i = 0; i < formasDePagamento.size(); i++) {
            if (formasDePagamento.get(i) instanceof CartaoCredito) {
                System.out.println("Forma de pagamento " + (i + 1) + ":Cartão de crédito de código: " + ((CartaoCredito) formasDePagamento.get(i)).getCodigo());
            } else if (formasDePagamento.get(i) instanceof CartaoDebito) {
                System.out.println("Forma de pagamento " + (i + 1) + ":Cartão de débito de código: " + ((CartaoDebito) formasDePagamento.get(i)).getCodigo());
            } else if (formasDePagamento.get(i) instanceof PIX) {
                System.out.println("Forma de pagamento " + (i + 1) + ":PIX");
            }
        }
    }

    public ArrayList<FormaDePagamento> getFormasDePagamento() {
        return formasDePagamento;
    }
}
