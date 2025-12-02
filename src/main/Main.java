package main;

import entidades.usuario.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

// main.Main
public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Motoristas pré cadastrados
        ArrayList<Motorista> motoristas = new ArrayList<>();
        motoristas.add(
                new Motorista(
                        "Daniel Sundfeld",
                        "danielsundfeld@gmail.com",
                        "181.440.120-10",
                        "(61) 99110-0100",
                        "12345678",
                        new Habilitacao("123", 2025),
                        new Veiculo("JPG0101", "BYD", "Preto", 2025, TipoVeiculo.LUXO))
        );
        motoristas.add(
                new Motorista(
                        "João Batista",
                        "joaobatista@gmail.com",
                        "181.440.120-20",
                        "(61) 99110-0200",
                        "senhadificil",
                        new Habilitacao("124", 2031),
                        new Veiculo("MOV0101", "Gol", "Branco", 2024, TipoVeiculo.COMFORT))
        );
        motoristas.add(
                new Motorista(
                        "Cardial",
                        "cardial@gmail.com",
                        "181.440.120-30",
                        "(61) 99110-0200",
                        "senhadificil",
                        new Habilitacao("124", 2031),
                        new Veiculo("MP40101", "Gol", "Rosa", 2020, TipoVeiculo.COMUM))
        );
        motoristas.add(
                new Motorista(
                        "João",
                        "joaocardone@gmail.com",
                        "181.440.120-30",
                        "(61) 98830-9379",
                        "12345678",
                        new Habilitacao("123", 2025),
                        new Veiculo("JPG0101", "Honda Bis", "Rosa", 2024, TipoVeiculo.MOTO))
        );

        // Passageiros pré-cadastrados
        ArrayList<Passageiro> passageiros = new ArrayList<>();
        passageiros.add(
                new Passageiro(
                        "André Lanna",
                        "andrelanna@gmail.com",
                        "024.982.660-70",
                        "(61) 99110-0200",
                        "SenhaMestre"
                )
        );
        passageiros.add(
                new Passageiro(
                        "Eliabe Alves",
                        "eliabealves@gmail.com",
                        "024.982.660-70",
                        "(61) 99110-0200",
                        "SenhaMestre"
                )
        );
        passageiros.add(
                new Passageiro(
                        "Gabriel Cardone",
                        "gabrielcardone@gmail.com",
                        "024.982.660-70",
                        "(61) 99110-0200",
                        "SenhaMestre"
                )
        );
        passageiros.add(
                new Passageiro(
                        "Igor Dantas",
                        "igordagraudemoto@gmail.com",
                        "024.982.660-70",
                        "(61) 99110-0200",
                        "SenhaMestre"
                )
        );

        System.out.println("===Chenride===");
        System.out.println("Seja bem vindo ao Chenride!");
        System.out.println("Nós somos um aplicativo de corridas que prioriza a simplicidade e a sua agilidade, pois sabemos o valor do seu tempo. :)");

        boolean menuAtivo = true;
        while (menuAtivo) {
            System.out.println("***Digite 0 para sair do aplicativo       ***");
            System.out.println("***Digite 1 para login                    ***");
            System.out.println("***Digite 2 para cadastro de motorista    ***");
            System.out.println("***Digite 3 para cadastro de passageiro   ***");

            int escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 0:
                    break;
                case 1: {
                    System.out.println("Digite seu e-mail:");
                    String email = sc.nextLine();
                    System.out.println("Digite sua senha:");
                    String senha = sc.nextLine();

                    Passageiro passageiro = null;
                    for (Passageiro item : passageiros) {
                        if (Objects.equals(item.getEmail(), email) && item.validateSenha(senha)) passageiro = item;
                    }

                    if (passageiro == null) {
                        System.out.println("Usuário não encontrado!");
                        break;
                    }

                    InterfacePassageiro.acessarPassageiro(passageiro, motoristas);
                    break;
                }
                case 2: {
                    Motorista motoristaCadastrando = Motorista.cadastrar();
                    motoristas.add(motoristaCadastrando);
                    System.out.println(motoristaCadastrando.getNome() + " cadastrado com sucesso!");
                    break;
                }
                case 3: {
                    Passageiro passageiroCadastrando = Passageiro.cadastrar();
                    passageiros.add(passageiroCadastrando);
                    System.out.println(passageiroCadastrando.getNome() + " cadastrado com sucesso!");
                    break;
                }
                default: {
                    System.out.println("Opção inválida.");
                }
            }
        }
    }
}