package entidades.corrida;

import entidades.usuario.*;
import exceptions.EstadoInvalidoDaCorridaException;
import exceptions.NenhumMotoristaDisponivelException;
import exceptions.PagamentoRecusadoException;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static main.Main.sc;

public class Corrida {
    private Rota rota;
    private TipoVeiculo categoria;
    private float preco;
    private Passageiro passageiro;
    private Motorista motorista;
    private StatusCorrida status;

    private float calcularPreco() {
        double distancia = rota.calcularDistancia();

        return (float) (categoria.getTarifaBase() + distancia * categoria.getTaxaPorQuilometro());
    }

    public Corrida(Passageiro passageiro) {
        System.out.println("Local de partida:");
        Local partida = new Local();
        System.out.println("Local de destino:");
        Local destino = new Local();
        System.out.println("Escolha a categoria da viagem: \n1 → Luxo\n2 → Comfort\n3 → Comum\n4 → Moto");
        TipoVeiculo tipoVeiculo = Veiculo.mapTipoVeiculo(sc.nextInt());
        sc.nextLine();

        Rota rota = new Rota(partida, destino);
        this(rota, passageiro, tipoVeiculo);
    }

    public Corrida(Rota rota, Passageiro passageiro, TipoVeiculo categoria) {
        this.rota = rota;
        this.passageiro = passageiro;
        this.status = StatusCorrida.SOLICITADA;
        this.categoria = categoria;
        this.preco = calcularPreco();
    }

    protected Motorista getMotorista() {
        return motorista;
    }

    public Rota getRota() {
        return rota;
    }

    public float getPreco() {
        return preco;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public StatusCorrida getStatus() {
        return status;
    }

    public void setStatus(StatusCorrida status) {
        this.status = status;
    }

    public void buscarMotorista(ArrayList<Motorista> cadastrados) {
        try {
            for (Motorista motorista : cadastrados) {
                // Verifica se a corrida ainda está ativa
                if (status != StatusCorrida.SOLICITADA) return;

                // Filtragem de motoristas
                if (motorista.getStatus() != StatusMotorista.ONLINE || motorista.getVeiculo().getTipoVeiculo() != categoria) {
                    continue;
                }

                // Envia uma solicitação para o motorista aceitar a corrida
                if (motorista.receberCorrida(this)) {
                    this.motorista = motorista;
                    this.iniciar();
                    break;
                }
            }

            if (this.motorista == null)
                throw new NenhumMotoristaDisponivelException("Não há motoristas disponíveis por perto, tente novamente mais tarde.");
        } catch (Exception e) {
            System.out.println("Erro ao encontrar corrida: " + e.getMessage());
        }
    }

    public void iniciar() {
        if (status != StatusCorrida.SOLICITADA)
            throw new EstadoInvalidoDaCorridaException("Esta corrida já foi iniciada.");

        this.status = StatusCorrida.EM_ANDAMENTO;
        motorista.setStatus(StatusMotorista.EM_CORRIDA);

        System.out.println();
        System.out.println(motorista.getNome() + " aceitou sua corrida! A viagem foi iniciada.");

        try {
            TimeUnit.SECONDS.sleep((long) rota.calcularDistancia());
        } catch (InterruptedException e) {
            System.out.println("Erro ao atualizar localização");
        }

        finalizar();
    }

    public void cancelar() {
        if (status != StatusCorrida.SOLICITADA)
            throw new EstadoInvalidoDaCorridaException("Esta corrida não pode ser cancelada.");

        setStatus(StatusCorrida.FINALIZADA);
    }

    private void finalizar() {
        // Motorista
        System.out.println("Você chegou ao destino de " + passageiro.getNome() + "\nAvaliação do Passageiro (1-5):");
        int notaPassageiro = sc.nextInt();
        sc.nextLine();
        passageiro.avaliar(notaPassageiro);
        System.out.println("Corrida finalizada, obrigado pelos seus serviços, " + motorista.getNome());
        setStatus(StatusCorrida.FINALIZADA);

        motorista.setStatus(StatusMotorista.ONLINE);

        System.out.println();

        // Passageiro
        System.out.println("Parabéns, você chegou ao seu destino!\nPor favor, avalie o motorista (1-5): " + motorista.getNome());
        int notaMotorista = sc.nextInt();
        sc.nextLine();
        motorista.avaliar(notaMotorista);

        System.out.println();

        try {
            passageiro.cobrar(preco);
        } catch (PagamentoRecusadoException e) {
            System.out.println("Erro ao processar pagamento: " + e.getMessage());
            System.out.println("O valor da corrida será debitado na próxima viagem.");
            passageiro.setSaldoDevedor(preco);
        }
    }
}