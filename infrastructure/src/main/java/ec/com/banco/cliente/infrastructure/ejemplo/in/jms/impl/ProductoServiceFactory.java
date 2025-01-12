package ec.com.banco.cliente.infrastructure.ejemplo.in.jms.impl;

import org.springframework.stereotype.Service;

import ec.com.banco.cliente.infrastructure.common.jms.Servicio;
import ec.com.banco.cuenta.share.ejemplo.enums.Operacion;

@Service
public class ProductoServiceFactory {

	private ObtenerProductoOperation getProduct;
	
	private ActualizarProductoOperation updateProduct;
	
	public ProductoServiceFactory(ObtenerProductoOperation getProduct, ActualizarProductoOperation updateProduct) {
		this.getProduct = getProduct;
		this.updateProduct = updateProduct;
	}
	
	public Servicio getInstance(String operacion) {
		Operacion o = null;
		try {
			o = Operacion.valueOf(operacion);
		} catch (Exception e) {
			throw new IllegalArgumentException(String.format("Operacion no soportada %s", operacion));
		}
		switch (o) {
		case UPDATE_PRODUCT:
			return updateProduct;
		case GET_PRODUCT:
			return getProduct;
		default:
			throw new IllegalArgumentException(String.format("Operacion no soportada %s", operacion));
		}
		
	}
}
