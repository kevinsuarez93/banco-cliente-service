package ec.com.banco.cliente.infrastructure.common.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cliente.domain.common.exception.ServiceException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(EntidadNoEncontradaException.class)
    public ResponseEntity<ErrorResponse> handleEntidadNoEncontrada(EntidadNoEncontradaException e) {
    	ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(RemoteExecutionException.class)
    public ResponseEntity<ErrorResponse> handleRemoteExecution(RemoteExecutionException e) {
    	ErrorResponse error = new ErrorResponse(e.getStatusCode(), e.getMessage());
		return ResponseEntity.status(e.getStatusCode()).body(error);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> handleServiceException(ServiceException e) {
    	log.error(e.getMessage(), e);
    	ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
    	log.error(e.getMessage(), e);
    	ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error en el servidor, por favor contacte al administrador: ".concat(e.getMessage()));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }

    /**
     * Handling for MethodArgumentNotValidException
     *
     * @param exception MethodArgumentNotValidException
     * @return Message detail of exception
     */
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidationExceptionHandle(HttpServletRequest request,
                                                                                    MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        ErrorResponse apiError = new ErrorResponse();
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setStatus(HttpStatus.BAD_REQUEST.value());
        apiError.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    
}