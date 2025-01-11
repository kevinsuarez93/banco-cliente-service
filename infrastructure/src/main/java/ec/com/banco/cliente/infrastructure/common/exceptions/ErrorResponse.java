package ec.com.banco.cliente.infrastructure.common.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse implements Serializable {
    private String backendMessage;
    private String message;
    private String url;
    private String method;
    private int status;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime timestamp;

    public ErrorResponse(int statusCode, String message) {
        this.status = statusCode;
        this.message = message;
    }
}