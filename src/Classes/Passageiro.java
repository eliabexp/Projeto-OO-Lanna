package Classes;

import java.util.ArrayList;

public class Passageiro extends Usuario {
    private ArrayList<FormaDePagamento> formasDePagamento = new ArrayList<>();

    public Passageiro(String nome, String email, String cpf, String numeroDeTelefone, String senha) {
        super(nome, email, cpf, numeroDeTelefone, senha);
    }

    public static Passageiro cadastrarPassageiro(Scanner sc) {
        System.out.println("---Cadastro---");
        System.out.println("Nome:");
        String nome = sc.nextLine();
        System.out.println("Email:");
        String email = sc.nextLine();
        System.out.println("CPF [999.999.999-99]:");
        String cpf = sc.nextLine();
        System.out.println("Numero de Telefone [(99) 99999-9999]");
        String numeroDeTelefone = sc.nextLine();
        System.out.println("Senha:");
        String senhaHash = sc.nextLine();

        return new Passageiro(nome, email, cpf, numeroDeTelefone, senhaHash);
    }

}
