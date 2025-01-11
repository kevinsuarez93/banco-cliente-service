package ec.com.banco.cliente.infrastructure.common.jms;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("jms.products")
@Getter
@Setter
public class JmsPropertiesService {

	private String message;
	private String replyqueue;
	private String requestqueue;

}
