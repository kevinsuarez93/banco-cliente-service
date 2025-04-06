package ec.com.banco.cliente.domain.persona.repositories;

import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cliente.domain.persona.models.Cliente;

import java.util.List;

public interface ClienteRepository {

    void crearCliente(Cliente cliente);

    void actualizarCliente(Cliente cliente) throws EntidadNoEncontradaException;

    Cliente obtenerCliente(Long clienteId);

    void eliminarCliente(Long clienteId) throws EntidadNoEncontradaException;


    List<Cliente> obtenerListadoClientes();
}
