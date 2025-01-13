package ec.com.banco.cliente.infrastructure.persona.mappers;

import ec.com.banco.cliente.domain.persona.models.Cliente;
import ec.com.banco.cliente.domain.persona.models.Persona;
import ec.com.banco.cliente.infrastructure.persona.entities.ClienteEntity;
import ec.com.banco.cliente.infrastructure.persona.entities.PersonaEntity;
import ec.com.banco.cliente.share.persona.dto.ClienteDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {


    Cliente entityToDomain(ClienteEntity entity);

    ClienteEntity domainToEntity(Cliente domain);


    Persona entityToDomain(PersonaEntity entity);

    PersonaEntity domainToEntity(Persona domain);


    List<Cliente> entitiesToDomains(List<ClienteEntity> entidades);

    Cliente dtoToDomain(ClienteDto dto);


    ClienteDto domainToDto(Cliente domain);

    List<ClienteDto> domainsToDtos(List<Cliente> domains);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void domainToEntity(Cliente domain, @MappingTarget ClienteEntity entity);

}
