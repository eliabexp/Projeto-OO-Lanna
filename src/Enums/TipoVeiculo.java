package Enums;

public enum TipoVeiculo {
    LUXO(9, 2.2F),
    COMFORT(7, 1.6F),
    COMUM(5, 1),
    MOTO(3, 0.8F);

    private float tarifaBase;
    private float taxaPorQuilometro;

    TipoVeiculo(int tarifaBase, float taxaPorQuilometro) {
        this.tarifaBase = tarifaBase;
        this.taxaPorQuilometro = taxaPorQuilometro;
    }

    public float getTarifaBase() {
        return tarifaBase;
    }

    public float getTaxaPorQuilometro() {
        return taxaPorQuilometro;
    }
}
