package usuario;

import java.util.Scanner;

public class Habilitacao {
    private String numeroDeRegistro;
    private int anoValidade;

    protected Habilitacao(String numeroDeRegistro, int anoValidade) {
        if (anoValidade < 2025) throw new IllegalArgumentException("Habilitação vencida");

        this.numeroDeRegistro = numeroDeRegistro;
        this.anoValidade = anoValidade;
    }

    protected static Habilitacao cadastrarHabilitacao(Scanner sc) {
        System.out.println("Número de registro:");
        String numeroDeRegistro = sc.nextLine();

        System.out.println("Ano de validade:");
        int anoValidade = sc.nextInt();

        return new Habilitacao(numeroDeRegistro, anoValidade);
    }
}
