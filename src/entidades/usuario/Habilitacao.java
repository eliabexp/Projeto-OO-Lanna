package entidades.usuario;

import exceptions.HabilitacaoVencidaException;

import java.util.Scanner;

public class Habilitacao {
    private String numeroDeRegistro;
    private int anoValidade;

    public Habilitacao(String numeroDeRegistro, int anoValidade) {
        if (anoValidade < 2025) throw new HabilitacaoVencidaException("Habilitação vencida.");

        this.numeroDeRegistro = numeroDeRegistro;
        this.anoValidade = anoValidade;
    }

    protected static Habilitacao cadastrarHabilitacao(Scanner sc) {
        System.out.println("Número de registro:");
        String numeroDeRegistro = sc.nextLine();

        System.out.println("Ano de validade:");
        int anoValidade = sc.nextInt();
        sc.nextLine();

        return new Habilitacao(numeroDeRegistro, anoValidade);
    }
}
