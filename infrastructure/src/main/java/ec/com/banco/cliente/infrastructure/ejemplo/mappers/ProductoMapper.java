package ec.com.banco.cliente.infrastructure.ejemplo.mappers;

import org.mapstruct.Mapper;

import ec.com.banco.cliente.domain.ejemplo.producto.Categoria;
import ec.com.banco.cliente.domain.ejemplo.producto.Producto;
import ec.com.banco.cliente.infrastructure.ejemplo.entities.CategoriaEntity;
import ec.com.banco.cliente.infrastructure.ejemplo.entities.ProductoEntity;
import ec.com.banco.cliente.share.ejemplo.dto.ProductoDto;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    Producto entityToDomain(ProductoEntity productData);
    Producto dtoToDomain(ProductoDto productDto);
    ProductoEntity domainToEntiy(Producto productData);
    ProductoDto domainToDto(Producto product);
    CategoriaEntity map(Categoria value);
}