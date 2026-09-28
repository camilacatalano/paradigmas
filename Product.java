import java.util.Locale;

/**
 * Classe abstrata que representa um produto do estoque.
 * Implementa Vendavel e obriga as subclasses a definirem
 * como o valor total é calculado (polimorfismo dinâmico).
 */
public abstract class Product implements Vendavel {

    private String nome;
    private double preco;
    private int quantidade;

    /**
     * @throws QuantidadeInvalidaException se preco ou quantidade forem negativos
     */
    public Product(String nome, double preco, int quantidade)
            throws QuantidadeInvalidaException {
        if (preco < 0) {
            throw new QuantidadeInvalidaException(
                    "Preço inválido para o produto '" + nome + "': " + preco);
        }
        if (quantidade < 0) {
            throw new QuantidadeInvalidaException(
                    "Quantidade inválida para o produto '" + nome + "': " + quantidade);
        }
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // ---------- Getters (atributos são private) ----------

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    // ---------- Método abstrato: cada subclasse decide ----------

    public abstract double calcularValorTotal();

    // ---------- Método concreto ----------

    public String getDescricao() {
        return String.format(Locale.forLanguageTag("pt-BR"),
                "%s | Preço: R$ %.2f | Quantidade: %d",
                nome, preco, quantidade);
    }

    // ---------- Vendavel ----------

    @Override
    public void vender(int quantidadeDesejada) throws ProdutoIndisponivelException {
        if (quantidadeDesejada <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade a vender deve ser maior que zero.");
        }
        if (quantidadeDesejada > quantidade) {
            throw new ProdutoIndisponivelException(
                    "Estoque insuficiente para '" + nome + "': pedido = "
                            + quantidadeDesejada + ", disponível = " + quantidade);
        }
        quantidade -= quantidadeDesejada;
    }

    // ---------- Sobrecarga (polimorfismo estático) ----------

    /**
     * Aplica um desconto percentual (ex.: 10 = 10%) sobre o preço.
     */
    public void aplicarDesconto(double percentual) {
        if (percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException(
                    "O percentual deve estar entre 0 e 100.");
        }
        preco = preco - (preco * percentual / 100.0);
    }

    /**
     * Aplica um desconto percentual, limitado a um percentual máximo.
     * Ex.: aplicarDesconto(30, 20) aplica apenas 20%.
     */
    public void aplicarDesconto(double percentual, double descontoMaximo) {
        double percentualAplicado = Math.min(percentual, descontoMaximo);
        aplicarDesconto(percentualAplicado);
    }
}
