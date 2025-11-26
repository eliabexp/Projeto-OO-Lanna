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
        this.preco = calcularPreco(rota, categoria);
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
            // Verifica se a entidades.corrida ainda está ativa
            if (status != StatusCorrida.SOLICITADA) return;

            // Filtragem de motoristas
            if (motorista.getStatus() != StatusMotorista.ONLINE || motorista.getVeiculo().getTipoVeiculo() != categoria) {
                continue;
            }

            if (motorista.solicitarCorrida(this)) {
                this.motorista = motorista;
                break;
            }
        }

        if (motorista == null)
            throw new NenhumMotoristaDisponivelException("Não há motoristas disponíveis por perto, tente novamente mais tarde.");
    }

    public void cancelar() {
        switch (status) {
            case StatusCorrida.EM_ANDAMENTO:
                throw new EstadoInvalidoDaCorridaException("Esta entidades.corrida não pode ser cancelada pois já está em andamento.");
            case StatusCorrida.FINALIZADA:
                throw new EstadoInvalidoDaCorridaException("Esta entidades.corrida já foi finalizada.");
            case StatusCorrida.CANCELADA:
                throw new EstadoInvalidoDaCorridaException("Esta entidades.corrida já foi cancelada");
        }

        setStatus(StatusCorrida.FINALIZADA);
    }

    public void finalizar() {
        setStatus(StatusCorrida.FINALIZADA);

        motorista.setStatus(StatusMotorista.ONLINE);

    }
}