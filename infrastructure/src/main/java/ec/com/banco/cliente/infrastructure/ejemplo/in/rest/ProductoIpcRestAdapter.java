package ec.com.banco.cliente.infrastructure.ejemplo.in.rest;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cliente.domain.producto.ProductoService;
import ec.com.banco.cliente.infrastructure.common.exceptions.RemoteExecutionException;
import ec.com.banco.cliente.infrastructure.ejemplo.mappers.ProductoMapper;
import ec.com.banco.cliente.share.producto.ProductoDto;
import jakarta.jms.JMSException;

@RestController
@RequestMapping("/productsipc")
public class ProductoIpcRestAdapter {
	
    private ProductoService productoService;
    
    private ProductoMapper productoMapper;

    public ProductoIpcRestAdapter(ProductoService productoService, ProductoMapper productoMapper) {
        this.productoService = productoService;
        this.productoMapper = productoMapper;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductoDto getProductUsandoMensajes(@PathVariable(name = "id") Long id, @RequestHeader("X-API-VERSION") Optional<String> apiVersion) throws EntidadNoEncontradaException {
		return productoService.getProductIpc(id, apiVersion);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateProduct(@RequestBody ProductoDto productDto) throws JsonProcessingException, RemoteExecutionException, JMSException {
//        jmsClient.sendAndWaitForResponse(productDto, ProductoDto.class, propertiesService.getRequestqueue(), 
//        		propertiesService.getReplyqueue(), Optional.empty(), Operacion.UPDATE_PRODUCT.name());
    }

}
