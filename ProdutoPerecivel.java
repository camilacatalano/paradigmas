import java.util.Locale;

/**
 * Produto perecível: tem prazo de validade e recebe 20% de desconto
 * automático quando faltam 3 dias ou menos para vencer.
 */
public class ProdutoPerecivel extends Product {

    private static final int DIAS_LIMITE_DESCONTO = 3;
    private static final double DESCONTO_AUTOMATICO = 0.20;

    private int diasParaVencer;

    public ProdutoPerecivel(String nome, double preco, int quantidade, int diasParaVencer)
            throws QuantidadeInvalidaException {
        super(nome, preco, quantidade);
        this.diasParaVencer = diasParaVencer;
    }

    public int getDiasParaVencer() {
        return diasParaVencer;
    }

    @Override
    public double calcularValorTotal() {
        double total = getPreco() * getQuantidade();
        if (diasParaVencer <= DIAS_LIMITE_DESCONTO) {
            total = total * (1 - DESCONTO_AUTOMATICO);
        }
        return total;
    }

    @Override
    public String getDescricao() {
        return super.getDescricao()
                + String.format(Locale.forLanguageTag("pt-BR"),
                        " | Vence em: %d dia(s)", diasParaVencer);
    }
}
