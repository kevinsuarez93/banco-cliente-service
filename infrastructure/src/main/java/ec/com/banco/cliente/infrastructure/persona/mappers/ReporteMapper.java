package ec.com.banco.cliente.infrastructure.persona.mappers;

import ec.com.banco.cliente.domain.persona.models.Reporte;
import ec.com.banco.cliente.share.persona.dto.ReporteDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReporteMapper {


    Reporte dtoToDomain(ReporteDto dto);

    ReporteDto domainToDto(Reporte domain);

    List<ReporteDto> domainsToDtos(List<Reporte> domains);
}
