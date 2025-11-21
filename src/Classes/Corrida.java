package Classes;

import java.lang.Math;
import Enums.TipoVeiculo;

public class Corrida {
    private Local partida;
    private Local destino;
    private float preco;
    private Motorista motorista;
    private Passageiro passageiro;

    public Corrida(Local partida, Local destino, Motorista motorista, Passageiro passageiro) {
        this.partida = partida;
        this.destino = destino;
        this.motorista = motorista;
        this.passageiro = passageiro;
        this.preco = calcularPreco();
    }

    private double calcularDistancia() {
        Local partida = this.partida;
        Local destino = this.destino;

        // Calcula a distância em linha reta entre os dois pontos
        double quilometros = Math.pow(partida.latitude - destino.latitude, 2) + Math.pow(partida.longitude - destino.longitude, 2);
        quilometros = Math.sqrt(quilometros);

        return quilometros;
    }

    private float calcularPreco() {
        double distancia = calcularDistancia();
        TipoVeiculo tipo = motorista.getVeiculo().getTipoVeiculo();

        return (float) (tipo.getTarifaBase() + distancia * tipo.getTaxaPorQuilometro());
    }

    private void iniciarViagem() {

    }

    private void finalizarViagem() {

    }

    private void cancelar() {

    }
}
