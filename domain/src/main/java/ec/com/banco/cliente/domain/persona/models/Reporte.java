package ec.com.banco.cliente.domain.persona.models;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reporte {


    private String nombre;
    private String identificacion;
    private Long clienteId;
}
