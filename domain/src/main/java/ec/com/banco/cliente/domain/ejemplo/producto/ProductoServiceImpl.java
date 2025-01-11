package ec.com.banco.cliente.domain.ejemplo.producto;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ec.com.centric.commons.rest.CriterioBusqueda;
import ec.com.centric.commons.rest.PagerAndSortDto;
import ec.com.centric.commons.rest.Pagina;
import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cliente.domain.ejemplo.integracion.IntegracionProducto;
import ec.com.banco.cliente.share.ejemplo.dto.ProductoDto;

@Service
public class ProductoServiceImpl implements ProductoService {

    private ProductoRepository productRepository;
    
    private IntegracionProducto integracionProducto;
    
    public ProductoServiceImpl(ProductoRepository productRepository, IntegracionProducto integracionProducto) {
        this.productRepository = productRepository;
        this.integracionProducto = integracionProducto;
    }

    @Override
    public Producto getProduct(Long id) throws EntidadNoEncontradaException {
    	System.out.println("Implementacion original");
    	return productRepository.getProduct(id);
    }

    @Override
    public ProductoDto getProductIpc(Long id, Optional<String> apiVersion) throws EntidadNoEncontradaException {
		return integracionProducto.buscarProducto(id);
    }

    @Override
    public Producto getProductV2(Long id) throws EntidadNoEncontradaException {
    	System.out.println("Implementacion V2");
    	return productRepository.getProduct(id);
    }

    @Override
    public void updateProduct(Producto product) {
        productRepository.updateProduct(product);
    }
    
	@Override
	public Pagina<Producto> search(List<CriterioBusqueda> criterios, PagerAndSortDto paging) {
        return productRepository.search(criterios, paging);
	}
}
