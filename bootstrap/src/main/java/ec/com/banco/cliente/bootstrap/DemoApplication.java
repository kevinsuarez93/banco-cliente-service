package ec.com.banco.cliente.bootstrap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = {"ec.com.banco.cliente"})
@EnableJpaRepositories(basePackages = {"ec.com.banco.cliente"})
@EntityScan("ec.com.banco.cliente")
@EnableWebMvc
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}
}
