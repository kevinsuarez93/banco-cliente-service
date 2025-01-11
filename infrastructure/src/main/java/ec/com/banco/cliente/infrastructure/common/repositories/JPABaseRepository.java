package ec.com.banco.cliente.infrastructure.common.repositories;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.support.PageableExecutionUtils;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import ec.com.centric.commons.rest.CriterioOrden;
import ec.com.banco.cliente.domain.common.exception.ServiceException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

/**
 * Repository base with support querydsl
 *
 * @author ksuarez
 * @version 1.1
 * @param <T>
 */
public class JPABaseRepository<T,  ID extends Serializable> {

    private final SimpleJpaRepository<T, ID> simpleJpaRepository;
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public JPABaseRepository(Class<T> domainClass, EntityManager entityManager) {
        this.entityManager = entityManager;
        this.simpleJpaRepository = new SimpleJpaRepository<>(domainClass, entityManager);
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    // Métodos delegados de SimpleJpaRepository
    public Optional<T> findById(ID id) {
        return simpleJpaRepository.findById(id);
    }

    public T getReferenceById(ID id) {
        return simpleJpaRepository.getReferenceById(id);
    }

    public T save(T entity) {
        return simpleJpaRepository.save(entity);
    }

	// Añade otros métodos delegados según sea necesario

	public void delete(T entity) {
		simpleJpaRepository.delete(entity);
	}

    // Método para clonar una consulta
    protected <P> JPQLQuery<P> cloneQuery(JPAQuery<P> query) {
        return query.clone(this.entityManager);
    }

    // Método para encontrar datos paginados
    protected <Q> Page<Q> findPageData(JPQLQuery<Q> query, Pageable pageable) {
        JPQLQuery<Q> countQuery = cloneQuery((JPAQuery<Q>) query);
        return PageableExecutionUtils.getPage(
                getQuerydsl().applyPagination(pageable, query).fetch(),
                pageable,
                countQuery::fetchCount
        );
    }

    // Método para obtener Querydsl
    private Querydsl getQuerydsl() {
        return new Querydsl(entityManager, new PathBuilderFactory().create(this.simpleJpaRepository.getClass()));
    }

    // Método para utilizar JPAQueryFactory en consultas personalizadas
    protected JPAQueryFactory getQueryFactory() {
        return this.queryFactory;
    }

    // Método genérico para ejecutar consultas nativas
    public List<Object[]> executeNativeQuery(String sql, Object... params) {
        Query query = entityManager.createNativeQuery(sql);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }
        return query.getResultList();
    }

	protected BooleanExpression getPredicate(String key, String operator, String value, PathBuilder<?> entityPath,
			Class<?> classType) {
		boolean isMultiValue = value.contains(",");
		Class<?> propertyType = getPropertyType(classType, key);
		switch (propertyType.getSimpleName()) {
		case "Integer":
			if (isMultiValue) {
				NumberPath<Integer> path = entityPath.getNumber(key, Integer.class);
				Integer[] numValue = Stream.of(value.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
				return getNumberPredicate(path, operator, numValue);
			} else {
				NumberPath<Integer> path = entityPath.getNumber(key, Integer.class);
				Integer[] numValue = new Integer[1]; numValue[0] = Integer.parseInt(value);
				return getNumberPredicate(path, operator, numValue);
			}
		case "BigDecimal":
			if (isMultiValue) {
				NumberPath<BigDecimal> path = entityPath.getNumber(key, BigDecimal.class);
				BigDecimal[] numValue = Stream.of(value.split(",")).map(Double::parseDouble).toArray(BigDecimal[]::new);
				return getNumberPredicate(path, operator, numValue);
			} else {
				NumberPath<BigDecimal> path = entityPath.getNumber(key, BigDecimal.class);
				BigDecimal[] numValue = new BigDecimal[1]; numValue[0] = BigDecimal.valueOf(Double.parseDouble(value));
				return getNumberPredicate(path, operator, numValue);
			}
		case "Double":
			if (isMultiValue) {
				NumberPath<Double> path = entityPath.getNumber(key, Double.class);
				Double[] numValue = Stream.of(value.split(",")).map(Double::parseDouble).toArray(Double[]::new);
				return getNumberPredicate(path, operator, numValue);
			} else {
				NumberPath<Double> path = entityPath.getNumber(key, Double.class);
				Double[] numValue = new Double[1]; numValue[0] = Double.parseDouble(value);
				return getNumberPredicate(path, operator, numValue);
			}
		case "Boolean":
			if (":".equals(operator)) {
				return entityPath.getBoolean(key).eq(Boolean.parseBoolean(value));
			} else {
				throw new RuntimeException("Unsupported Boolean operation");
			}
		case "String":
			StringPath stringPath = entityPath.getString(key);
			return getStringPredicate(stringPath, operator, value, entityPath);
		default:
			String message = String.format("Tipo de dato no soportado: %s", propertyType.getSimpleName());
        	throw new ServiceException(message);
		}
	}

	protected OrderSpecifier<?> getOrderSpecifier(PathBuilder<?> pathBuilder, CriterioOrden criterioOrden, Class<?> classType) {
		Class<?> propertyType = getPropertyType(classType, criterioOrden.getCampo());
		switch (propertyType.getSimpleName()) {
		case "Integer":
			NumberPath<Integer> numberPath = pathBuilder.getNumber(criterioOrden.getCampo(), Integer.class);
			return criterioOrden.isAscendente() ? numberPath.asc() : numberPath.desc();
		case "BigDecimal":
			NumberPath<BigDecimal> bgPath = pathBuilder.getNumber(criterioOrden.getCampo(), BigDecimal.class);
			return criterioOrden.isAscendente() ? bgPath.asc() : bgPath.desc();
		case "Double":
			NumberPath<Double> doublePath = pathBuilder.getNumber(criterioOrden.getCampo(), Double.class);
			return criterioOrden.isAscendente() ? doublePath.asc() : doublePath.desc();
		case "String":
			StringPath stringPath = pathBuilder.getString(criterioOrden.getCampo());
			return criterioOrden.isAscendente() ? stringPath.asc() : stringPath.desc();
		default:
			String message = String.format("Tipo de dato no soportado: %s", propertyType.getSimpleName());
        	throw new ServiceException(message);
		}
	}
	
	private Class<?> getPropertyType(Class<?> classType, String key) {
		try {
			return classType.getDeclaredField(key).getType();
		} catch (NoSuchFieldException | SecurityException e) {
			String message = String.format("Campo de filtro no existe: %s", key);
        	throw new ServiceException(message);
		}
	}

	private BooleanExpression getStringPredicate(StringPath stringPath, String operator, String value,
			PathBuilder<?> entityPath) {
		switch (operator) {
		case "like":
			return stringPath.like("%" + value + "%");
		case "=":
			return stringPath.eq(value);
		case "!=":
			return stringPath.notIn(value);
		default:
			String message = String.format("Operador no soportado: %s", operator);
        	throw new ServiceException(message);
		}
	}

	private <T extends Number & Comparable<?>> BooleanExpression getNumberPredicate(NumberPath<T> path, String operator, T[] value) {
        switch (operator) {
            case "=":
                return path.eq(value[0]);
            case ">":
                return path.gt(value[0]);
            case "<":
                return path.lt(value[0]);
            case ">=":
                return path.goe(value[0]);
            case "<=":
                return path.loe(value[0]);
            case "in":
                return path.in(value);
            case "!=":
                return path.notIn(value);
            default:
                String message = String.format("Operador no soportado: %s", operator);
                throw new ServiceException(message);
        }
    }

}
