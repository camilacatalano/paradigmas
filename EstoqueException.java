/**
 * Exceção base do sistema de estoque.
 * Estende Exception (checked): quem chama é obrigado a tratar ou declarar.
 */
public class EstoqueException extends Exception {
    private static final long serialVersionUID = 1L;

    public EstoqueException(String mensagem) {
        super(mensagem);
    }
}
