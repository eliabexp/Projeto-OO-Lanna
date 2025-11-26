package entidades.usuario;

import entidades.pagamento.FormasDePagamento;

import java.util.ArrayList;
import java.util.Scanner;


public abstract class Usuario {
    private String nome;
    private String email;
    private ArrayList<Integer> avaliacoes = new ArrayList<>();
    private String cpf;
    private String numeroDeTelefone;
    private String senhaHash;
    private ArrayList<FormasDePagamento> formasdepagamento = new ArrayList<>();

    private String hashSenha(String senha) {
        return senha + "1234Token@";
    }

    protected Usuario(String nome, String email, String cpf, String numeroDeTelefone, String senha) {
        this.nome = nome;
        this.email = email;
        this.numeroDeTelefone = numeroDeTelefone;
        this.cpf = cpf;
        this.senhaHash = hashSenha(senha);
    }

    public void avaliar(int nota) {
        avaliacoes.add(nota);
    }

    public float getNota() {
        float soma = 0;
        for (Integer avaliacoes : avaliacoes) {
            soma += avaliacoes;
        }

        return soma /= avaliacoes.size();
    }

    public String getNome() {
        return nome;
    }
}
