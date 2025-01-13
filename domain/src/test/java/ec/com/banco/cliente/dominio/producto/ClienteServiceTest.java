package ec.com.banco.cliente.dominio.producto;

import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cliente.domain.persona.models.Cliente;
import ec.com.banco.cliente.domain.persona.repositories.ClienteRepository;
import ec.com.banco.cliente.domain.persona.services.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    public ClienteRepository clienteRepository;

    @InjectMocks
    public ClienteServiceImpl clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setClienteId(1L);
    }

    @Test
    public void shouldSaveCliente() throws EntidadNoEncontradaException {
        clienteService.actualizarCliente(cliente);
        verify(clienteRepository, times(1)).actualizarCliente(any(Cliente.class));
    }

    @Test
    public void shouldCreateCliente() {
        clienteService.crearCliente(cliente);
        verify(clienteRepository, times(1)).crearCliente(any(Cliente.class));
    }

    @Test
    public void shouldDeleteClienteWhenExists() throws EntidadNoEncontradaException {
        when(clienteRepository.obtenerCliente(1L)).thenReturn(cliente);

        clienteService.eliminarCliente(1L);

        verify(clienteRepository, times(1)).eliminarCliente(1L);
    }

    @Test
    public void shouldReturnNullWhenClienteNotFound() {
        when(clienteRepository.obtenerCliente(3L)).thenReturn(null);

        Cliente found = clienteService.buscarClientePorId(3L);

        assertNull(found);
        verify(clienteRepository, times(1)).obtenerCliente(3L);
    }
}
