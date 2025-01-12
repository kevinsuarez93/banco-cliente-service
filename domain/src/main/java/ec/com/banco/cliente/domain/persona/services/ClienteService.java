package ec.com.banco.cliente.domain.persona.services;

import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cliente.domain.persona.models.Cliente;

public interface ClienteService {
    void crearCliente(Cliente cliente);

    void actualizarCliente(Cliente cliente) throws EntidadNoEncontradaException;

    void eliminarCliente(Long clienteId) throws EntidadNoEncontradaException;

    Cliente buscarClientePorId(Long clienteId);
}
