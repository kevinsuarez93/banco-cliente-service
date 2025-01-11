package ec.com.banco.cliente.infrastructure.ejemplo.in.jms.impl;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ec.com.banco.cliente.infrastructure.common.jms.Servicio;
import ec.com.banco.cliente.infrastructure.ejemplo.mappers.ProductoMapper;
import ec.com.banco.cliente.share.ejemplo.dto.ProductoDto;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;

@Service
public class ActualizarProductoOperation implements Servicio {

	// Se mantiene comentado debido a una referencia circular
	// No deberia realizarse una invocacion asincrona a un servicio
	// que esta disponible en el mismo microservicio
//	private ProductoService productService;
	private ProductoMapper productMapper;
	
	public ActualizarProductoOperation(ProductoMapper productMapper) {
//		this.productService = productService;
		this.productMapper = productMapper;
	}
	
	@Override
	public String execute(TextMessage textMessage) throws JsonProcessingException, JMSException {
		ObjectMapper objectMapper = new ObjectMapper();
		ProductoDto productDto = objectMapper.readValue(textMessage.getText(), ProductoDto.class);
//		productService.updateProduct(productMapper.dtoToDomain(productDto));
		return "";
	}
}
