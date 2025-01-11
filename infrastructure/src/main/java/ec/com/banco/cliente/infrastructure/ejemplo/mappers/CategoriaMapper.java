package ec.com.banco.cliente.infrastructure.ejemplo.mappers;

import org.mapstruct.Mapper;

import ec.com.banco.cliente.domain.ejemplo.producto.Categoria;
import ec.com.banco.cliente.infrastructure.ejemplo.entities.CategoriaEntity;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    Categoria toResource(CategoriaEntity categoryData);
    CategoriaEntity toData(Categoria categoryData);
}