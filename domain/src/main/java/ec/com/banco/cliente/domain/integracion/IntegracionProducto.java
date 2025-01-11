package ec.com.banco.cliente.domain.integracion;

import ec.com.banco.cliente.share.producto.ProductoDto;

public interface IntegracionProducto {

	ProductoDto buscarProducto(Long idProducto);

}
