package ec.com.banco.cliente.infrastructure.persona.mappers;

import ec.com.banco.cliente.domain.persona.models.Cliente;
import ec.com.banco.cliente.infrastructure.persona.entities.ClienteEntity;
import ec.com.banco.cliente.share.persona.dto.ClienteDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {


    @Mapping(target = "personaId", source = "entity.persona.personaId")
    Cliente entityToDomain(ClienteEntity entity);

    @InheritInverseConfiguration
    ClienteEntity domainToEntity(Cliente domain);

    List<Cliente> entitiesToDomains(List<ClienteEntity> entidades);

    Cliente dtoToDomain(ClienteDto dto);

    ClienteDto domainToDto(Cliente domain);

    List<ClienteDto> domainsToDtos(List<Cliente> domains);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void domainToEntity(Cliente domain, @MappingTarget ClienteEntity entity);

}
