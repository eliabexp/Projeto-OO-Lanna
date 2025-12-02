package entidades.pagamento;

import static main.Main.sc;

public class CartaoDebito extends Cartao {
    public CartaoDebito(String nome, float saldo, String codigo) {
        super(nome, saldo, codigo);
    }

    public CartaoDebito() {
        System.out.println("Informe o nome no cartão: ");
        String nome = sc.nextLine();
        System.out.println("Informe o código do cartão: ");
        String codigo = sc.nextLine();
        System.out.println("Informe o saldo do debito: ");
        float saldo = sc.nextFloat();
        sc.nextLine();

        this(nome, saldo, codigo);
    }
}
