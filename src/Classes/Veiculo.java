package Classes;

import Enums.TipoVeiculo;

import java.util.Scanner;

public class Veiculo {
    private String placa;
    private String modelo;
    private String cor;
    private int ano;
    private TipoVeiculo tipoVeiculo;

    protected Veiculo(String placa, String modelo, String cor, int ano, TipoVeiculo tipoVeiculo) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.tipoVeiculo = tipoVeiculo;
    }

    private TipoVeiculo mapTipoVeiculo(int escolha) {
        return switch (escolha) {
            case 1 -> TipoVeiculo.LUXO;
            case 2 -> TipoVeiculo.COMFORT;
            case 3 -> TipoVeiculo.COMUM;
            case 4 -> TipoVeiculo.MOTO;
            default -> null;
        };
    }

    protected static Veiculo cadastrarVeiculo(Scanner sc) {
        System.out.println("---Cadastro de veículo---");
        System.out.println("Placa:");
        String placa = sc.nextLine();
        System.out.println("Modelo:");
        String modelo = sc.nextLine();
        System.out.println("Ano:");
        int ano = sc.nextInt();

        System.out.println("Cor:");
        String cor = sc.nextLine();
        System.out.println("Digite o número de acordo com o tipo do veículo:\n1: Luxo\n2: Comfort\n3: Comum\n4: Moto");
        TipoVeiculo tipoVeiculo = mapTipoVeiculo(sc.nextInt());

        return new Veiculo(placa, modelo, cor, ano, tipoVeiculo);
    }

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }
}
