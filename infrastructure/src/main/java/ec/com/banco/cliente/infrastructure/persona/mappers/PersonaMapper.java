package ec.com.banco.cliente.infrastructure.persona.mappers;

import ec.com.banco.cliente.domain.persona.models.Persona;
import ec.com.banco.cliente.share.persona.dto.PersonaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonaMapper {

    Persona dtoToDomain(PersonaDto dto);

    PersonaDto domainToDto(Persona domain);
}
