package entidades.usuario;

import entidades.corrida.Corrida;
import entidades.pagamento.CartaoCredito;
import entidades.pagamento.CartaoDebito;
import entidades.pagamento.FormaDePagamento;
import entidades.pagamento.PIX;
import exceptions.PagamentoRecusadoException;

import java.util.ArrayList;

import static main.Main.sc;

public class Passageiro extends Usuario {
    private float saldoDevedor;
    private ArrayList<FormaDePagamento> formasDePagamento = new ArrayList<>();

    public Passageiro() {
        System.out.println("| Viaje com a RoadLines |");
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


        this(nome, email, cpf, numeroDeTelefone, senhaHash, new ArrayList<>());

        cadastrarMetodoDePagamento();
    }

    public Passageiro(String nome, String email, String cpf, String numeroDeTelefone, String senha, ArrayList<FormaDePagamento> formasDePagamento) {
        super(nome, email, cpf, numeroDeTelefone, senha);
        this.formasDePagamento = formasDePagamento;
    }

    public void solicitarCorrida(ArrayList<Motorista> motoristas) {
        try {
            if (saldoDevedor > 0) {
                System.out.println("Você possui débitos a serem pagos. Gostaria de pagá-los agora? (s|n)");
                String prompt = sc.nextLine();

                if (prompt.equalsIgnoreCase("s")) {
                    cobrar(saldoDevedor);
                    saldoDevedor = 0;
                    System.out.println("Suas pendências foram resolvidas, obrigado por viajar com a RoadLines!");
                } else {
                    throw new PagamentoRecusadoException("Você tem pendências de débitos a serem pagos. Por favor, resolva-as antes de solicitar uma nova corrida.");
                }

            }

            System.out.println("Olá, " + getNome() + ", para onde você vai hoje?");

            Corrida corrida = new Corrida(this);

            System.out.printf("Valor da corrida: R$ %.2f\n", corrida.getPreco());
            System.out.println("Deseja confirmar a viagem? (s|n):");
            String resposta = sc.nextLine();

            if (!resposta.equalsIgnoreCase("s")) {
                corrida.cancelar();
                System.out.println("Corrida cancelada!");
                return;
            }

            System.out.println("Buscando motorista...");
            System.out.println();
            corrida.buscarMotorista(motoristas);
        } catch (PagamentoRecusadoException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listaMetodosDePagamento() {
        for (int i = 0; i < formasDePagamento.size(); i++) {
            if (formasDePagamento.get(i) instanceof CartaoCredito) {
                System.out.println((i + 1) + " → Cartão de crédito: " + ((CartaoCredito) formasDePagamento.get(i)).getNome());
            } else if (formasDePagamento.get(i) instanceof CartaoDebito) {
                System.out.println((i + 1) + " → Cartão de débito: " + ((CartaoDebito) formasDePagamento.get(i)).getNome());
            } else if (formasDePagamento.get(i) instanceof PIX) {
                System.out.println((i + 1) + " → PIX: " + ((PIX) formasDePagamento.get(i)).getNome());
            }
        }
    }

    public void cobrar(float valor) throws PagamentoRecusadoException {
        System.out.println("Escolha um método de pagamento: ");
        listaMetodosDePagamento();
        int escolha = sc.nextInt();
        escolha--;
        sc.nextLine();

        getFormasDePagamento().get(escolha).processarPagamento(valor);
    }

    public void cadastrarMetodoDePagamento() {
        System.out.println("Qual método de pagamento deseja cadastrar?");
        System.out.println("1 → Cartão de Débito");
        System.out.println("2 → Cartão de Crédito");
        System.out.println("3 → Pix");

        int escolha = sc.nextInt();
        sc.nextLine();

        switch (escolha) {
            case 1: {
                formasDePagamento.add(new CartaoDebito());
                break;
            }
            case 2: {
                formasDePagamento.add(new CartaoCredito());
                break;
            }

            case 3: {
                formasDePagamento.add(new PIX());
                break;
            }
        }
    }

    public ArrayList<FormaDePagamento> getFormasDePagamento() {
        return formasDePagamento;
    }

    public float getSaldoDevedor() {
        return saldoDevedor;
    }

    public void setSaldoDevedor(float saldo) {
        this.saldoDevedor = saldo;
    }

}
