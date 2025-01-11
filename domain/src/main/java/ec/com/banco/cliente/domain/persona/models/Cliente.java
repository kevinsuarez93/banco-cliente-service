package ec.com.banco.cliente.domain.persona.models;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private Long clienteId;

    private Long personaId;

    private String password;

    private String estado;
}