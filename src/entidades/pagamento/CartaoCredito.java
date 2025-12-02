package entidades.pagamento;

import entidades.usuario.Passageiro;
import exceptions.SaldoInsuficienteException;

public class CartaoCredito extends Cartao {
    private float limite;

    public void processarPagamento(float valorCorrida, Passageiro passageiro) {
        float saldoAtual = passageiro.getSaldo();
        if (saldoAtual < valorCorrida) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }

        passageiro.setSaldo(saldoAtual - valorCorrida);
    }
}
