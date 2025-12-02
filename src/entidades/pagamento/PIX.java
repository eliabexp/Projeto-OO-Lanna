package entidades.pagamento;

import static main.Main.sc;

public class PIX extends FormaDePagamento {
    public PIX(String nome, float saldo) {
        super(nome, saldo);
    }

    public PIX(){
        System.out.println("Informe o nome da conta: ");
        String nome = sc.nextLine();
        System.out.println("Informe o saldo da conta: ");
        float saldo = sc.nextFloat();

        this(nome, saldo);
    }
}

