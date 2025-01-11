package ec.com.banco.cliente.domain.common.exception;

public class RegistroDuplicadoException extends RuntimeException {

    private static final long serialVersionUID = 2061287620328825383L;

    public RegistroDuplicadoException(String message) {
        super(message);
    }
}
