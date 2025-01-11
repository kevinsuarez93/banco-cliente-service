package ec.com.banco.cliente.infrastructure.ejemplo.in.jms.impl;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cliente.domain.producto.Producto;
import ec.com.banco.cliente.infrastructure.common.jms.Servicio;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;

@Service
public class ObtenerProductoOperation implements Servicio {

	// Se mantiene comentado debido a una referencia circular
	// No deberia realizarse una invocacion asincrona a un servicio
	// que esta disponible en el mismo microservicio

	// 	private ProductoService productService;
	
//	public ObtenerProductoOperation(ProductoService productService) {
//		this.productService = productService;
//	}
	
	@Override
	public String execute(TextMessage textMessage) throws JsonProcessingException, JMSException, EntidadNoEncontradaException {
		String apiVersion = textMessage.getStringProperty("X-API-VERSION");
		if ("2".equals(apiVersion)) {
    		return obtenerProductoV2(textMessage);
        } else {
            return obtenerProducto(textMessage);
		}
	}

	public String obtenerProducto(TextMessage textMessage) throws JsonProcessingException, JMSException, EntidadNoEncontradaException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readTree(textMessage.getText());
		Long productId = node.get("id").asLong();
//		Producto product = productService.getProduct(productId);
		return objectMapper.writeValueAsString(Producto.builder().build());
	}

	public String obtenerProductoV2(TextMessage textMessage) throws JsonProcessingException, JMSException, EntidadNoEncontradaException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readTree(textMessage.getText());
		Long productId = node.get("id").asLong();
//		Producto product = productService.getProductV2(productId);
		return objectMapper.writeValueAsString(Producto.builder().build());
	}	

}
