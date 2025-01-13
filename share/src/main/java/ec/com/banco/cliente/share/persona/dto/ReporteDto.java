package ec.com.banco.cliente.share.persona.dto;

import ec.com.banco.cuenta.share.cuenta.dto.CuentaDto;
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
    private CuentaDto cuenta;



}
