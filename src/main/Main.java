package main;

import entidades.pagamento.CartaoCredito;
import entidades.pagamento.CartaoDebito;
import entidades.pagamento.PIX;
import entidades.usuario.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void menuPassageiro(Passageiro passageiro, ArrayList<Motorista> motoristas) {
        boolean menuAtivo = true;

        while (menuAtivo) {
            System.out.println();
            System.out.println("Selecione uma opção:");
            System.out.println("1 → Solicitar uma viagem");
            System.out.println("2 → Cadastrar método de pagamento");
            System.out.println("0 → Desconectar");

            int escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    passageiro.solicitarCorrida(motoristas);
                    break;
                case 2:
                    passageiro.cadastrarMetodoDePagamento();
                    break;
                case 0:
                    menuAtivo = false;
                    break;
            }
        }
    }

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
                        "024.981.661-70",
                        "(61) 99110-0200",
                        "SenhaMestre",
                        new ArrayList<>(List.of(new CartaoDebito("Débito Visa", 5000, "1234"), new CartaoCredito("Black", 15000, "1234")))
                )
        );
        passageiros.add(
                new Passageiro(
                        "Eliabe Alves",
                        "eliabealves@gmail.com",
                        "024.982.660-70",
                        "(61) 99110-0200",
                        "SenhaMestre",
                        new ArrayList<>(List.of(new CartaoDebito("Débito Visa", 250, "1234"), new CartaoCredito("Black", 5000, "1234"), new PIX("Banco", 1000)))
                )
        );
        passageiros.add(
                new Passageiro(
                        "Gabriel Cardone",
                        "gabrielcardone@gmail.com",
                        "024.982.660-70",
                        "(61) 99110-0200",
                        "SenhaMestre",
                        new ArrayList<>(List.of(new CartaoDebito("Débito Visa", 250, "1234"), new CartaoCredito("Black", 2.5f, "1234"), new PIX("Banco", 1000)))
                )
        );
        passageiros.add(
                new Passageiro(
                        "Igor Dantas",
                        "igordagraudemoto@gmail.com",
                        "024.982.660-70",
                        "(61) 99110-0200",
                        "SenhaMestre",
                        new ArrayList<>(List.of(new CartaoDebito("Débito Visa", 250, "1234"), new CartaoCredito("Black", 5000, "1234"), new PIX("Banco", 0)))
                )
        );

        System.out.println("RoadLines - Menu principal");
        System.out.println("Seja bem vindo ao RoadLines!");
        System.out.println("Nós somos um aplicativo de corridas que prioriza a simplicidade\ne a sua agilidade, pois sabemos o valor do seu tempo. :)");

        boolean menuAtivo = true;
        while (menuAtivo) {
            System.out.println("1 → Login");
            System.out.println("2 → Cadastro de motorista");
            System.out.println("3 → Cadastro de passageiro");
            System.out.println("0 → Sair");

            int escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1: {
                    System.out.println("Digite seu e-mail:");
                    String email = sc.nextLine();
                    System.out.println("Digite sua senha:");
                    String senha = sc.nextLine();

                    Passageiro passageiro = null;
                    for (Passageiro item : passageiros) {
                        if (Objects.equals(item.getEmail(), email) && item.validateSenha(senha))
                            passageiro = item;
                    }

                    if (passageiro == null) {
                        System.out.println("Você digitou usuário ou senha inválidos, tente novamente.");
                        break;
                    }

                    menuPassageiro(passageiro, motoristas);
                    break;
                }
                case 2: {
                    try {
                        Motorista motoristaCadastrando = new Motorista();
                        motoristas.add(motoristaCadastrando);
                        System.out.println(motoristaCadastrando.getNome() + " cadastrado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Não foi possível cadastrar este motorista: " + e.getMessage());
                    }
                    break;
                }
                case 3: {
                    try {
                        Passageiro passageiroCadastrando = new Passageiro();
                        passageiros.add(passageiroCadastrando);
                        System.out.println(passageiroCadastrando.getNome() + " cadastrado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Não foi possível cadastrar este passageiro: " + e.getMessage());
                    }
                    break;
                }
                case 0:
                    System.out.println("Volte sempre!");
                    menuAtivo = false;
                    break;
                default: {
                    System.out.println("Opção inválida.");
                }
            }
        }
    }
}