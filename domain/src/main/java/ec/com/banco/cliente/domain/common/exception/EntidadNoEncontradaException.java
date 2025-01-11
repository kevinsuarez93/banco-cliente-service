package ec.com.banco.cliente.domain.common.exception;

import java.io.Serializable;

public class EntidadNoEncontradaException extends Exception implements Serializable {
	
	private static final long serialVersionUID = 2673534739978278013L;

	public EntidadNoEncontradaException(String message) {
		super(message);
	}
	
	public EntidadNoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public EntidadNoEncontradaException(Throwable cause) {
		super(cause);
	}

}
