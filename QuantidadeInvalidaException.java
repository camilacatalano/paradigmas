/**
 * Lançada quando preço ou quantidade informados são inválidos (negativos).
 */
public class QuantidadeInvalidaException extends EstoqueException {
    private static final long serialVersionUID = 1L;

    public QuantidadeInvalidaException(String mensagem) {
        super(mensagem);
    }
}
