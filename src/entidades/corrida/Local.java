package entidades.corrida;

import static main.Main.sc;

public class Local {
    private String nome;
    private double latitude;
    private double longitude;

    public Local() {
        System.out.println("---Inserir local---");
        System.out.println("Digite o nome do local:");
        this.nome = sc.nextLine();

        System.out.println("Latitude:");
        this.latitude = sc.nextDouble();
        sc.nextLine();

        System.out.println("Longitude:");
        this.longitude = sc.nextDouble();
        sc.nextLine();
    }

    public Local(String nome, double latitude, double longitude) {
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }

    public String getNome() {
        return nome;
    }
}
