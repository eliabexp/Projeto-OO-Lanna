package entidades.corrida;

import entidades.usuario.Motorista;
import entidades.usuario.Passageiro;
import entidades.usuario.StatusMotorista;
import entidades.usuario.TipoVeiculo;
import exceptions.EstadoInvalidoDaCorridaException;
import exceptions.NenhumMotoristaDisponivelException;

import java.util.ArrayList;

public class Corrida {
    private Rota rota;
    private TipoVeiculo categoria;
    private float preco;
    private Passageiro passageiro;
    private Motorista motorista;
    private StatusCorrida status;

    public Corrida(Rota rota, Passageiro passageiro, TipoVeiculo categoria) {
        this.rota = rota;
        this.passageiro = passageiro;
        this.status = StatusCorrida.SOLICITADA;
        this.preco = calcularPreco(rota, categoria);
        this.categoria = categoria;
    }

    protected static float calcularPreco(Rota rota, TipoVeiculo categoria) {
        double distancia = rota.calcularDistancia();

        return (float) (categoria.getTarifaBase() + distancia * categoria.getTaxaPorQuilometro());
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
    }

    public void iniciar() {
        if (status != StatusCorrida.SOLICITADA)
            throw new EstadoInvalidoDaCorridaException("Esta corrida já foi iniciada.");

        this.status = StatusCorrida.EM_ANDAMENTO;
        motorista.setStatus(StatusMotorista.EM_CORRIDA);

        System.out.println("A viagem foi iniciada...");

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

    public void finalizar() {
        setStatus(StatusCorrida.FINALIZADA);

        motorista.setStatus(StatusMotorista.ONLINE);

        System.out.println("Você chegou ao seu destino, por favor avalie o motorista " + motorista.getNome() + "(1-5)");
        int notaMotorista = sc.nextInt();
        sc.nextLine();
        motorista.avaliar(notaMotorista);

        System.out.println();

        System.out.println("Escolha um método de pagamento: ");
        passageiro.ListaMetodosDePagamento();
        int escolha = sc.nextInt();
        escolha--;
        sc.nextLine();
        passageiro.getFormasDePagamento().get(escolha).processarPagamento(preco, passageiro);
        System.out.println("Pagamento concluído com sucesso, obrigado pela preferência!");

    }
}