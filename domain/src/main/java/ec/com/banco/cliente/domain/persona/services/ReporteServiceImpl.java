package ec.com.banco.cliente.domain.persona.services;

import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cliente.domain.persona.jms.IntegracionCuenta;
import ec.com.banco.cliente.domain.persona.models.Cliente;
import ec.com.banco.cliente.domain.persona.models.Reporte;
import ec.com.banco.cuenta.share.cuenta.dto.CuentaDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final IntegracionCuenta integracionCuenta;
    private final ClienteService clienteService;

    public ReporteServiceImpl(ClienteService clienteService, IntegracionCuenta integracionCuenta) {
        this.integracionCuenta = integracionCuenta;
        this.clienteService = clienteService;
    }



    @Override
    public Reporte generarReporte(Long clienteId, Date fechaInicio, Date fechaFinal) throws EntidadNoEncontradaException {

        Cliente cliente =this.clienteService.buscarClientePorId(clienteId);
        System.out.println(cliente.toString());
        List<CuentaDto> cuentas= this.integracionCuenta.obtenerCuentas(clienteId, fechaInicio, fechaFinal);

        System.out.println(cuentas.toString());

        return  Reporte.builder()
                .clienteId(clienteId)
                .nombre(cliente.getPersona().getNombre())
                .identificacion(cliente.getPersona().getIdentificacion())
                .cuentas(cuentas)
                .build();



    }
}
