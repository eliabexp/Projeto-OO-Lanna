package entidades.pagamento;

import exceptions.PagamentoRecusadoException;

public abstract class FormaDePagamento {
    private String nome;
    private float saldo;

    protected FormaDePagamento(String nome, float saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }

    public void processarPagamento(float valorCorrida) {
        if (saldo < valorCorrida) {
            throw new PagamentoRecusadoException("Saldo insuficiente");
        }

        saldo -= valorCorrida;
    }

    public String getNome() {
        return nome;
    }

}
