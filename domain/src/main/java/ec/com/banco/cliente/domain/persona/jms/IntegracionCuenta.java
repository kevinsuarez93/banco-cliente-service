package ec.com.banco.cliente.domain.persona.jms;

import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cuenta.share.cuenta.dto.CuentaDto;

public interface IntegracionCuenta {

    CuentaDto obtenerCuenta(Long clienteId) throws EntidadNoEncontradaException;
}
