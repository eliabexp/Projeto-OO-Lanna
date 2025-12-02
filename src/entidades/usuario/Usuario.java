package entidades.usuario;

import entidades.pagamento.FormaDePagamento;

import java.util.ArrayList;


public abstract class Usuario {
    private String nome;
    private String email;
    private ArrayList<Integer> avaliacoes;
    private String cpf;
    private String numeroDeTelefone;
    private String senhaHash;
    private ArrayList<FormaDePagamento> formasdepagamento = new ArrayList<>();

    private String hashSenha(String senha) {
        return senha + "1234Token@";
    }

    protected Usuario(String nome, String email, String cpf, String numeroDeTelefone, String senha) {
        this.nome = nome;
        this.email = email;
        this.numeroDeTelefone = numeroDeTelefone;
        this.cpf = cpf;
        this.senhaHash = hashSenha(senha);
        this.avaliacoes = new ArrayList<>();

        // Média inicial será a máxima
        avaliacoes.add(5);
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

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public boolean validateSenha(String senha) {
        return this.senhaHash.equals(hashSenha(senha));
    }
}
