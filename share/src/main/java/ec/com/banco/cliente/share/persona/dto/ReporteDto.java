package ec.com.banco.cliente.share.persona.dto;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDto {

    private String nombre;
    private String identificacion;
    private Long clienteId;



}
