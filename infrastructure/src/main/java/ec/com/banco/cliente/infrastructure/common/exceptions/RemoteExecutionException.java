package ec.com.banco.cliente.infrastructure.common.exceptions;

import lombok.Getter;

@Getter
public class RemoteExecutionException extends Exception {

    private static final long serialVersionUID = 7077690177821723442L;
    
    private int statusCode;
    
    public RemoteExecutionException(int statusCode, String message) {
		super(message);
		this.statusCode = statusCode;
	}

	public RemoteExecutionException(String message) {
        super(message);
    }

    public RemoteExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

}
