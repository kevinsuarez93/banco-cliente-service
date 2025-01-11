package ec.com.banco.cliente.infrastructure.ejemplo.in.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.centric.commons.rest.CriterioBusqueda;
import ec.com.centric.commons.rest.PagerAndSortDto;
import ec.com.centric.commons.rest.Pagina;
import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cliente.domain.ejemplo.producto.Producto;
import ec.com.banco.cliente.domain.ejemplo.producto.ProductoService;
import ec.com.banco.cliente.infrastructure.ejemplo.mappers.ProductoMapper;
import ec.com.banco.cliente.share.ejemplo.dto.ProductoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/products")
@Tag(name = "Producto", description = "Productos API")
@Log4j2
public class ProductoRestAdapter {
	
    private ProductoService productService;
    private ProductoMapper productMapper;

    public ProductoRestAdapter(ProductoService productService, ProductoMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping("/search")
    public Pagina<ProductoDto> search(PagerAndSortDto paging, @RequestBody List<CriterioBusqueda> filters) {
    	filters.forEach(filter -> {
        	log.info(filter.toString());
    	});
    	
        Pagina<Producto> pagina = productService.search(filters, paging);

        return Pagina.<ProductoDto>builder()
        	.contenido(pagina.getContenido().stream().map(productMapper::domainToDto).collect(Collectors.toList()))
        	.totalRegistros(pagina.getTotalRegistros())
        	.paginaActual(pagina.getPaginaActual())
        	.totalpaginas(pagina.getTotalpaginas()).build();        
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtener productos")
    public ProductoDto getProductV1(@PathVariable(name = "id") Long id) throws EntidadNoEncontradaException {
        Producto product = productService.getProduct(id);
        return this.productMapper.domainToDto(product);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, headers = "X-API-VERSION=2")
    @Operation(summary = "Obtener productos")
    public ProductoDto getProductV2(@PathVariable(name = "id") Long id) throws EntidadNoEncontradaException {
        Producto product = productService.getProductV2(id);
        return this.productMapper.domainToDto(product);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualizar productos")
    public void updateProduct(@RequestBody ProductoDto productDto) {
        productService.updateProduct(productMapper.dtoToDomain(productDto));
    }

}
