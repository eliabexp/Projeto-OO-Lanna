package entidades.pagamento;

import exceptions.SaldoInsuficienteException;

public abstract class FormaDePagamento {
    private String nome;
    private float saldo;

    protected FormaDePagamento(String nome, float saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }

    public void processarPagamento(float valorCorrida) {
        if (saldo < valorCorrida) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }

        saldo -= valorCorrida;
    }

    public String getNome() {
        return nome;
    }

}
