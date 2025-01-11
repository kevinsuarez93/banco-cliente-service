package ec.com.banco.cliente.infrastructure.ejemplo;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import ec.com.banco.cliente.infrastructure.common.jms.JmsPropertiesService;

@Service
@EnableConfigurationProperties(JmsPropertiesService.class)
public class MyService {

	private final JmsPropertiesService serviceProperties;

	public MyService(JmsPropertiesService serviceProperties) {
		this.serviceProperties = serviceProperties;
	}

	public String message() {
		return this.serviceProperties.getMessage();
	}
}
