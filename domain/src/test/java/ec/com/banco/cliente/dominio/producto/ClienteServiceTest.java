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
import org.springframework.context.MessageSource;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    public ClienteRepository clienteRepository;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    public ClienteServiceImpl clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setClienteId(1L);
    }

    @Test
    void shouldCreateCliente() {
        clienteService.crearCliente(cliente);

        verify(clienteRepository, times(1)).crearCliente(cliente);
    }

    @Test
    void shouldUpdateCliente() throws EntidadNoEncontradaException {
        clienteService.actualizarCliente(cliente);

        verify(clienteRepository, times(1)).actualizarCliente(cliente);
    }

    @Test
    void shouldDeleteClienteIfExists() throws EntidadNoEncontradaException {
        Long clienteId = 1L;
        when(clienteRepository.obtenerCliente(clienteId)).thenReturn(cliente);

        clienteService.eliminarCliente(clienteId);

        verify(clienteRepository, times(1)).eliminarCliente(clienteId);
    }


    @Test
    void shouldReturnClienteIfExists() {
        Long clienteId = 3L;
        when(clienteRepository.obtenerCliente(clienteId)).thenReturn(cliente);

        Cliente found = clienteService.buscarClientePorId(clienteId);

        assertNotNull(found);
        assertEquals(cliente, found);
        verify(clienteRepository, times(1)).obtenerCliente(clienteId);
    }

    @Test
    void shouldReturnNullIfClienteDoesNotExist() {
        Long clienteId = 4L;
        when(clienteRepository.obtenerCliente(clienteId)).thenReturn(null);

        Cliente found = clienteService.buscarClientePorId(clienteId);

        assertNull(found);
        verify(clienteRepository, times(1)).obtenerCliente(clienteId);
    }
}
