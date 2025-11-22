package pagamento;

import usuario.Passageiro;

public class CartaoCredito extends Cartao {
    private float limiteDisponivel;

    public void processarPagamento(float valorCorrida, Passageiro passageiro) {
        if (limiteDisponivel < valorCorrida) throw new SaldoInsuficienteException("Limite insuficiente");

        limiteDisponivel -= valorCorrida;
    }
}
