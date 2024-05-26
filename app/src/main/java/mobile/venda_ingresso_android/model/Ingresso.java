package mobile.venda_ingresso_android.model;

public class Ingresso {
    private String codigo;
    private float valor;

    public Ingresso(String codigo, float valor) {
        this.codigo = codigo;
        this.valor = valor;
    }

    public Ingresso() {
        super();
    }

    public float valorFinal(float taxaInconveniencia) {
        return this.valor + taxaInconveniencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
