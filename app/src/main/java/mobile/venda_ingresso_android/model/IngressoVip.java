package mobile.venda_ingresso_android.model;

public class IngressoVip extends Ingresso{
    private String funcao;

    public IngressoVip(String codigo, float valor, String funcao) {
        super(codigo, valor);
        this.funcao = funcao;
    }

    public IngressoVip(String funcao) {
        super();
        this.funcao = funcao;
    }

    @Override
    public float valorFinal(float taxaInconveniencia) {
        float valor = super.valorFinal(taxaInconveniencia);
        return valor * 1.18f;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
