package entidades.pagamento;

public abstract class Cartao extends FormaDePagamento {
    private String codigo;

    public String getCodigo(){
        return codigo;
    }
}
