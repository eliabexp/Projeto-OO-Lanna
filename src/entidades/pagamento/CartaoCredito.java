package entidades.pagamento;

import exceptions.PagamentoRecusadoException;

import static main.Main.sc;

public class CartaoCredito extends Cartao {
    private float limite;

    public CartaoCredito() {
        System.out.println("Informe o nome no cartão: ");
        String nome = sc.nextLine();
        System.out.println("Informe o código do cartão: ");
        String codigo = sc.nextLine();
        System.out.println("Informe o crédito disponível: ");
        float limite = sc.nextFloat();
        sc.nextLine();

        this(nome, limite, codigo);
    }

    public CartaoCredito(String nome, float limite, String codigo) {
        super(nome, 0, codigo);
        this.limite = limite;
    }

    public void processarPagamento(float valorCorrida) {
        if (limite < valorCorrida) {
            throw new PagamentoRecusadoException("Saldo insuficiente");
        }
    }

}
