package entidades.usuario;

import exceptions.MotoristaInvalidoException;

import static main.Main.sc;

public class Habilitacao {
    private String numeroDeRegistro;
    private int anoValidade;

    public Habilitacao() {
        System.out.println("Número de registro:");
        String numeroDeRegistro = sc.nextLine();

        System.out.println("Ano de validade:");
        int anoValidade = sc.nextInt();
        sc.nextLine();

        this(numeroDeRegistro, anoValidade);
    }

    public Habilitacao(String numeroDeRegistro, int anoValidade) {
        if (anoValidade < 2025)
            throw new MotoristaInvalidoException("Habilitação vencida. Por favor, cadastre uma habilitação válida.");

        this.numeroDeRegistro = numeroDeRegistro;
        this.anoValidade = anoValidade;
    }
}
