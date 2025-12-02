package entidades.corrida;

import static main.Main.sc;

public class Local {
    private String nome;
    public double latitude;
    public double longitude;

    public Local(String nome, double latitude, double longitude) {
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Local inserirLocal() {
        System.out.println("---Inserir local---");
        System.out.println("Digite o nome do local:");
        String nome = sc.nextLine();
        System.out.println("Latitude:");
        double latitude = sc.nextDouble();
        sc.nextLine();
        System.out.println("Longitude:");
        double longitude = sc.nextDouble();
        sc.nextLine();

        return new Local(nome, latitude, longitude);
    }

    public String getNome() {
        return nome;
    }
}
