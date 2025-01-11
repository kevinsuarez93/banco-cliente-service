package ec.com.banco.cliente.infrastructure.ejemplo.out;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;

import ec.com.centric.commons.rest.CriterioBusqueda;
import ec.com.centric.commons.rest.CriterioOrden;
import ec.com.centric.commons.rest.PagerAndSortDto;
import ec.com.centric.commons.rest.Pagina;
import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cliente.domain.producto.Producto;
import ec.com.banco.cliente.domain.producto.ProductoRepository;
import ec.com.banco.cliente.infrastructure.common.repositories.JPABaseRepository;
import ec.com.banco.cliente.infrastructure.ejemplo.entities.ProductoEntity;
import ec.com.banco.cliente.infrastructure.ejemplo.entities.QProductoEntity;
import ec.com.banco.cliente.infrastructure.ejemplo.mappers.ProductoMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class ProductoRepositoryImpl extends JPABaseRepository<ProductoEntity, Long> implements ProductoRepository {

	private ProductoMapper productMapper;
	
	public ProductoRepositoryImpl(ProductoMapper productMapper, EntityManager em) {
		super(ProductoEntity.class, em);
		this.productMapper = productMapper;
	}
	
	@Override
	public Producto getProduct(Long id) throws EntidadNoEncontradaException{
		try {
			ProductoEntity referenceById = getReferenceById(id);
			return productMapper.entityToDomain(referenceById);
		} catch (EntityNotFoundException e) {
			throw new EntidadNoEncontradaException(e.getMessage());
		}
	}

	@Override
	public void updateProduct(Producto product) {
		this.save(productMapper.domainToEntiy(product));
	}

	public Pagina<Producto> search(List<CriterioBusqueda> criterios, PagerAndSortDto paging) {
        Pageable pageable = PageRequest.of(paging.getPage(), paging.getSize());
        JPQLQuery<ProductoEntity> jpqlQuery = getQueryFactory().selectFrom(QProductoEntity.productoEntity)
                .select(Projections.bean(ProductoEntity.class,
                		QProductoEntity.productoEntity.id,
                		QProductoEntity.productoEntity.categoryId,
                		QProductoEntity.productoEntity.price,
                		QProductoEntity.productoEntity.pvp,
                		QProductoEntity.productoEntity.name,
                		QProductoEntity.productoEntity.stock,
                		QProductoEntity.productoEntity.hasDiscount,
                		QProductoEntity.productoEntity.category
                ))
                .where(buildQuery(criterios));
        
        if (paging.datosOrdenamientoCompleto()) {
        	jpqlQuery.orderBy(buildOrder(paging));
        }
        
        Page<ProductoEntity> pageData = this.findPageData(jpqlQuery, pageable);
		
        Pagina<Producto> pagina = Pagina.<Producto>builder()
    		.paginaActual(paging.getPage())
    		.totalpaginas(pageData.getTotalPages())
    		.totalRegistros(pageData.getTotalElements())
    		.contenido(pageData.stream().map(productMapper::entityToDomain).collect(Collectors.toList()))
    		.build();
        return pagina;
	}
	
	private OrderSpecifier<?> buildOrder(PagerAndSortDto paging) {
		PathBuilder<QProductoEntity> pathBuilder = new PathBuilder<>(QProductoEntity.class, "productoEntity");
		return getOrderSpecifier(pathBuilder, new CriterioOrden(paging.getOrderBy(), paging.getDirection()), ProductoEntity.class);
	}

	private com.querydsl.core.types.Predicate buildQuery(List<CriterioBusqueda> criterios) {
		BooleanBuilder builder = new BooleanBuilder();
		PathBuilder<QProductoEntity> pathBuilder = new PathBuilder<>(QProductoEntity.class, "productoEntity");
		criterios.forEach(criterio -> {
			builder.and(getPredicate(criterio.getLlave(), criterio.getOperacion(), criterio.getValor(), pathBuilder, ProductoEntity.class));
		});
		return builder;
	}

}
