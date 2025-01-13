package ec.com.banco.cliente.domain.persona.models;

import ec.com.banco.cuenta.share.cuenta.dto.CuentaDto;
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
    private List<CuentaDto> cuentas;
}
