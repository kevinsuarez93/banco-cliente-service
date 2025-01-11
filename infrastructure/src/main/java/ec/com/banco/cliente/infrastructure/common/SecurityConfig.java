package ec.com.banco.cliente.infrastructure.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilitar CSRF
                .csrf(csrf -> csrf.disable())

                // Permitir todas las solicitudes
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )

        // Deshabilitar el Resource Server
        //.oauth2ResourceServer(oauth2 -> oauth2.jwt());

        ;

        return http.build();
    }
}
