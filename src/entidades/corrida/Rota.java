package entidades.corrida;

public class Rota {
    private Local partida;
    private Local destino;

    public Rota() {
        System.out.println("Local de partida:");
        Local partida = new Local();
        System.out.println("Local de destino:");
        Local destino = new Local();

        this(partida, destino);
    }

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
