package ec.com.banco.cliente.domain.persona.models;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cliente {

    private Long clienteId;

    private Persona persona;

    private String password;

    private Boolean estado;
}
