package ec.com.banco.cliente.infrastructure.ejemplo.in.jms;

import java.util.Optional;

import ec.com.banco.cliente.infrastructure.common.exceptions.RemoteExecutionException;
import ec.com.banco.cliente.infrastructure.common.jms.JmsClient;
import ec.com.banco.cliente.infrastructure.common.jms.JmsPropertiesService;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import ec.com.banco.cliente.domain.common.exception.ServiceException;
import ec.com.banco.cliente.domain.integracion.IntegracionProducto;
import ec.com.banco.cliente.share.Operacion;
import ec.com.banco.cliente.share.producto.ProductoDto;
import jakarta.jms.JMSException;

@Service
public class IntegracionProductoImpl implements IntegracionProducto {

	private JmsClient jmsClient;
    private JmsPropertiesService propertiesService;
    
    public IntegracionProductoImpl(JmsClient jmsClient, JmsPropertiesService propertiesService) {
        this.jmsClient = jmsClient;
        this.propertiesService = propertiesService;
    }
	


	@Override
	public ProductoDto buscarProducto(Long idProducto) {
		ProductoDto dto = ProductoDto.builder().id(idProducto).build();
		try {
			return jmsClient.sendAndWaitForResponse(dto, ProductoDto.class, propertiesService.getRequestqueue(),
					propertiesService.getReplyqueue(), Optional.empty(), Operacion.GET_PRODUCT.name());
		} catch (JsonProcessingException | RemoteExecutionException | JMSException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
