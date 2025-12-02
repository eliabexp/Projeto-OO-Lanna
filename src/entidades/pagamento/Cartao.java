package entidades.pagamento;

public abstract class Cartao extends FormaDePagamento {
    private String codigo;

    public Cartao(String nome, float saldo, String codigo) {
        super(nome, saldo);
        this.codigo = codigo;
    }

    public String getCodigo(){
        return codigo;
    }
}
