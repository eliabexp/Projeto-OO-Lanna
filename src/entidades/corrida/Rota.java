package entidades.corrida;

public class Rota {
    private Local partida;
    private Local destino;

    public Rota(Local partida, Local destino) {
        this.partida = partida;
        this.destino = destino;
    }

    public Local getPartida() {
        return partida;
    }

    public Local getDestino() {
        return destino;
    }

    public double calcularDistancia() {
        // Calcula a distância em linha reta entre os dois pontos
        double quilometros = Math.pow(partida.getLatitude() - destino.getLatitude(), 2) + Math.pow(partida.getLongitude() - destino.getLongitude(), 2);
        quilometros = Math.sqrt(quilometros);

        return quilometros;
    }
}
