import java.util.Locale;

public class EstoqueApp {

    private static String moeda(double valor) {
        return String.format(Locale.forLanguageTag("pt-BR"), "R$ %.2f", valor);
    }

    public static void main(String[] args) {
        Estoque estoque = new Estoque();

        // ---------- 1) Cadastro de produtos válidos ----------
        System.out.println("=== 1) Cadastrando produtos ===");
        try {
            estoque.adicionarProduto(new ProdutoComum("Caderno", 15.90, 20));
            estoque.adicionarProduto(new ProdutoComum("Caneta", 3.50, 100));
            estoque.adicionarProduto(new ProdutoPerecivel("Iogurte", 6.00, 30, 10));   // sem desconto
            estoque.adicionarProduto(new ProdutoPerecivel("Leite", 5.00, 40, 2));      // 20% de desconto
        } catch (QuantidadeInvalidaException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
        estoque.listarProdutos();

        // ---------- 2) Produto com quantidade negativa ----------
        System.out.println("\n=== 2) Tentando cadastrar produto com quantidade negativa ===");
        try {
            estoque.adicionarProduto(new ProdutoComum("Borracha", 2.00, -5));
        } catch (QuantidadeInvalidaException e) {
            System.out.println("Capturada QuantidadeInvalidaException: " + e.getMessage());
        }

        // ---------- 3) Venda válida ----------
        System.out.println("\n=== 3) Venda válida ===");
        try {
            estoque.venderProduto(0, 5); // 5 cadernos
            System.out.println("Venda de 5 unidades do índice 0 realizada com sucesso.");
            System.out.println("  " + estoque.getProdutos().get(0).getDescricao());
        } catch (ProdutoIndisponivelException e) {
            System.out.println("Capturada ProdutoIndisponivelException: " + e.getMessage());
        }

        // ---------- 4) Venda acima do disponível ----------
        System.out.println("\n=== 4) Venda acima do estoque disponível ===");
        try {
            estoque.venderProduto(1, 500); // só há 100 canetas
            System.out.println("Esta linha não deve ser executada.");
        } catch (ProdutoIndisponivelException e) {
            System.out.println("Capturada ProdutoIndisponivelException: " + e.getMessage());
        }

        // ---------- 5) Sobrecarga de aplicarDesconto ----------
        System.out.println("\n=== 5) Sobrecarga de aplicarDesconto() ===");
        Product caneta = estoque.getProdutos().get(1);
        System.out.println("Antes:  " + caneta.getDescricao());
        caneta.aplicarDesconto(10);        // versão 1: 10%
        System.out.println("Após aplicarDesconto(10):      " + caneta.getDescricao());
        caneta.aplicarDesconto(30, 20);    // versão 2: pedido 30%, máximo 20% -> aplica 20%
        System.out.println("Após aplicarDesconto(30, 20):  " + caneta.getDescricao());

        // ---------- 6) Valor total do estoque (polimorfismo) ----------
        System.out.println("\n=== 6) Valor total do estoque ===");
        estoque.listarProdutos();
        for (Product p : estoque.getProdutos()) {
            System.out.println("  " + p.getNome() + " -> " + moeda(p.calcularValorTotal()));
        }
        System.out.println("VALOR TOTAL DO ESTOQUE: " + moeda(estoque.calcularValorTotalEstoque()));

        // ---------- 7) Ordem dos catches: específicas primeiro, genérica por último ----------
        System.out.println("\n=== 7) Ordem dos catches (específicas -> genérica) ===");
        try {
            estoque.adicionarProduto(new ProdutoComum("Régua", 4.00, 10));
            estoque.venderProduto(99, 1); // índice inexistente
        } catch (QuantidadeInvalidaException e) {
            System.out.println("Capturada QuantidadeInvalidaException: " + e.getMessage());
        } catch (ProdutoIndisponivelException e) {
            System.out.println("Capturada ProdutoIndisponivelException: " + e.getMessage());
        } catch (EstoqueException e) { // a mais genérica vem por último
            System.out.println("Capturada EstoqueException: " + e.getMessage());
        }
    }
}
