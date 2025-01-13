package ec.com.banco.cliente.share.persona.dto;

import ec.com.banco.cuenta.share.cuenta.dto.CuentaDto;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDto {

    private String nombre;
    private String identificacion;
    private Long clienteId;
    private List<CuentaDto> cuentas;



}
