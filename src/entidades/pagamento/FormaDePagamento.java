package entidades.pagamento;

import exceptions.SaldoInsuficienteException;
import entidades.usuario.Passageiro;

public abstract class FormaDePagamento {
    private float saldo;

    public void processarPagamento(float valorCorrida, Passageiro passageiro) {
        float saldoAtual = passageiro.getSaldo();
        if (saldoAtual < valorCorrida) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }

        passageiro.setSaldo(saldoAtual - valorCorrida);
    }
}
