package ec.com.banco.cliente.domain.persona.services;

import ec.com.banco.cliente.domain.common.constants.ClienteExceptionMessages;
import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cliente.domain.persona.models.Cliente;
import ec.com.banco.cliente.domain.persona.repositories.ClienteRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final MessageSource messageSource;

    public ClienteServiceImpl(ClienteRepository clienteRepository, MessageSource messageSource) {
        this.clienteRepository = clienteRepository;
        this.messageSource = messageSource;
    }

    @Override
    public void crearCliente(Cliente cliente) {

        clienteRepository.crearCliente(cliente);
    }

    @Override
    public void actualizarCliente(Cliente cliente) throws EntidadNoEncontradaException {
        clienteRepository.actualizarCliente(cliente);
    }

    @Override
    public void eliminarCliente(Long clienteId) throws EntidadNoEncontradaException {
        if (this.clienteRepository.obtenerCliente(clienteId) == null) {
            throw new EntidadNoEncontradaException(messageSource.getMessage(ClienteExceptionMessages.ERROR_NO_EXISTE,
                    null, LocaleContextHolder.getLocale()));
        }
        clienteRepository.eliminarCliente(clienteId);
    }

    @Override
    public Cliente buscarClientePorId(Long clienteId) {
        return this.clienteRepository.obtenerCliente(clienteId);
    }

    @Override
    public List<Cliente> obtenerListadoClientes() {
        return this.clienteRepository.obtenerListadoClientes();
    }

}
