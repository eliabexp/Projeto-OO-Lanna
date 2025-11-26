import entidades.usuario.*;

import java.util.ArrayList;
import java.util.Scanner;

// Chen
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
                        new Veiculo("JPG0101", "BYD", "Branco", 2024, TipoVeiculo.LUXO))
        );
        motoristas.add(
                new Motorista(
                        "João Batista",
                        "joaobatista@gmail.com",
                        "181.440.120-20",
                        "(61) 99110-0200",
                        "senhadificil",
                        new Habilitacao("124", 2031),
                        new Veiculo("MOV0101", "Gol", "Branco", 2024, TipoVeiculo.COMUM))
        );
        motoristas.add(
                new Motorista(
                        "João",
                        "joaocardone@gmail.com",
                        "181.440.120-30",
                        "(61) 98830-9379",
                        "12345678",
                        new Habilitacao("123", 2025),
                        new Veiculo("JPG0101", "Gol", "Branco", 2024, TipoVeiculo.COMUM))
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
        while (true) {
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
                    break;
                }
                case 2: {
                    Motorista motoristaCadastrando = Motorista.cadastrarMotorista(sc);
                    motoristas.add(motoristaCadastrando);
                    System.out.println(motoristaCadastrando.getNome() + " cadastrado com sucesso!");
                    break;
                }
                case 3: {
                    Passageiro passageiroCadastrando = Passageiro.cadastrarPassageiro(sc);
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