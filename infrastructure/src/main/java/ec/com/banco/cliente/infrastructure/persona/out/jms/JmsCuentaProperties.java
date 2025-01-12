package ec.com.banco.cliente.infrastructure.persona.out.jms;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jms.cuentas")
@Getter
@Setter
public class JmsCuentaProperties {
    private String replyqueue;
    private String requestqueue;
    private Long timeout;
}
