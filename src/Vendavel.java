/**
 * Contrato para itens que podem ser vendidos.
 */
public interface Vendavel {

    /**
     * Vende a quantidade desejada, subtraindo-a do estoque.
     *
     * @param quantidadeDesejada quantidade que se quer vender
     * @throws ProdutoIndisponivelException se a quantidade desejada for
     *                                      maior que o estoque disponível
     */
    void vender(int quantidadeDesejada) throws ProdutoIndisponivelException;
}
