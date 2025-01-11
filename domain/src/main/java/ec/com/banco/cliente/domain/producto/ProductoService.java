package ec.com.banco.cliente.domain.producto;

import java.util.List;
import java.util.Optional;

import ec.com.centric.commons.rest.CriterioBusqueda;
import ec.com.centric.commons.rest.PagerAndSortDto;
import ec.com.centric.commons.rest.Pagina;
import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cliente.share.producto.ProductoDto;

public interface ProductoService {

	Producto getProduct(Long id) throws EntidadNoEncontradaException;

	ProductoDto getProductIpc(Long id, Optional<String> apiVersion) throws EntidadNoEncontradaException;

	Producto getProductV2(Long id) throws EntidadNoEncontradaException;

	void updateProduct(Producto product);

	Pagina<Producto> search(List<CriterioBusqueda> criterios, PagerAndSortDto paging);

}
