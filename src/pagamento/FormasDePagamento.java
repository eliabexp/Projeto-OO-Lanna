package pagamento;

import usuario.Passageiro;

public abstract class FormasDePagamento {
    public void processarPagamento(float valorCorrida, Passageiro passageiro) {
        float saldoAtual = passageiro.getSaldo();
        if (saldoAtual < valorCorrida) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }

        passageiro.setSaldo(saldoAtual - valorCorrida);
    }
}
