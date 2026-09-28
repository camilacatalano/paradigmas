import java.util.ArrayList;
import java.util.List;

/**
 * Estoque TEM UMA lista de produtos (composição). Não herda de Product.
 */
public class Estoque {

    private final List<Product> produtos = new ArrayList<>();

    public void adicionarProduto(Product p) {
        produtos.add(p);
    }

    /**
     * Vende do produto que está na posição 'indice' da lista.
     * Propaga ProdutoIndisponivelException se não houver estoque suficiente.
     */
    public void venderProduto(int indice, int quantidade)
            throws ProdutoIndisponivelException {
        if (indice < 0 || indice >= produtos.size()) {
            throw new ProdutoIndisponivelException(
                    "Não existe produto no índice " + indice);
        }
        produtos.get(indice).vender(quantidade);
    }

    /**
     * Polimorfismo: o Estoque não sabe se o produto é comum ou perecível.
     * Cada um calcula o seu valor total do seu jeito.
     */
    public double calcularValorTotalEstoque() {
        double total = 0;
        for (Product p : produtos) {
            total += p.calcularValorTotal();
        }
        return total;
    }

    public List<Product> getProdutos() {
        return new ArrayList<>(produtos); // cópia defensiva
    }

    public void listarProdutos() {
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println("  [" + i + "] " + produtos.get(i).getDescricao());
        }
    }
}
