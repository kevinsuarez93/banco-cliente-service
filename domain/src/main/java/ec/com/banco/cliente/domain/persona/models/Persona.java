package ec.com.banco.cliente.domain.persona.models;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Persona {

    private Long personaId;

    private String nombre;

    private String genero;

    private Integer edad;

    private String identificacion;

    private String direccion;

    private String telefono;
}
