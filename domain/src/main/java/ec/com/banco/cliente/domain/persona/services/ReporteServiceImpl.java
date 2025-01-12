package ec.com.banco.cliente.domain.persona.services;

import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cliente.domain.persona.jms.IntegracionCuenta;
import ec.com.banco.cliente.domain.persona.models.Cliente;
import ec.com.banco.cliente.domain.persona.models.Reporte;
import ec.com.banco.cuenta.share.cuenta.dto.CuentaDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

    private IntegracionCuenta integracionCuenta;
    private ClienteService clienteService;

    public ReporteServiceImpl(ClienteService clienteService, IntegracionCuenta integracionCuenta) {
        this.integracionCuenta = integracionCuenta;
        this.clienteService = clienteService;
    }



    @Override
    public List<Reporte> generarReporte(Long clienteId) throws EntidadNoEncontradaException {
        Cliente cliente =this.clienteService.buscarClientePorId(clienteId);
        System.out.println(cliente.toString());
        CuentaDto cuentas= this.integracionCuenta.obtenerCuenta(clienteId);

        System.out.println(cuentas.toString());

        return null;
    }
}
