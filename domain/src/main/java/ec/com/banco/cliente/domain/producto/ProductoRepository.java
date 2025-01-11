package ec.com.banco.cliente.domain.producto;

import java.util.List;

import ec.com.centric.commons.rest.CriterioBusqueda;
import ec.com.centric.commons.rest.PagerAndSortDto;
import ec.com.centric.commons.rest.Pagina;
import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;

public interface ProductoRepository {
	
	public Producto getProduct(Long id) throws EntidadNoEncontradaException;
	
	public void updateProduct(Producto product);

	public Pagina<Producto> search(List<CriterioBusqueda> criterios, PagerAndSortDto paging);

}