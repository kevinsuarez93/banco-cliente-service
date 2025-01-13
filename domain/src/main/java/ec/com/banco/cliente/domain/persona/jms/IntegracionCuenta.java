package ec.com.banco.cliente.domain.persona.jms;

import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cuenta.share.cuenta.dto.CuentaDto;

import java.util.Date;
import java.util.List;

public interface IntegracionCuenta {

    List<CuentaDto> obtenerCuentas(Long clienteId, Date fechaInicio, Date fechaFinal) throws EntidadNoEncontradaException;
}
