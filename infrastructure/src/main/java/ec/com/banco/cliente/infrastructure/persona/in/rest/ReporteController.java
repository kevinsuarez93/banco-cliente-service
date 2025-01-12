package ec.com.banco.cliente.infrastructure.persona.in.rest;

import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cliente.domain.common.exception.RegistroDuplicadoException;
import ec.com.banco.cliente.domain.persona.services.ReporteService;
import ec.com.banco.cliente.infrastructure.common.exceptions.ErrorResponse;
import ec.com.banco.cliente.infrastructure.persona.mappers.ReporteMapper;
import ec.com.banco.cliente.share.persona.dto.ReporteDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes")
@Tag(name = "Reportes", description = "Reportes API")
public class ReporteController {


    private final ReporteService reporteService;
    private final ReporteMapper reporteMapper;

    public ReporteController(ReporteService reporteService, ReporteMapper reporteMapper) {
        this.reporteService = reporteService;
        this.reporteMapper = reporteMapper;
    }


    /**
     * Crear nueva Companias.
     *
     * @param crearDto Dto de crear compania
     * @return CompaniasCrearDto
     */


    @GetMapping(value = "/{clienteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "generar Reportes")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Reportes generado."),
            @ApiResponse(responseCode = "400", description = "Entrada inválida.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            })
    public ResponseEntity<List<ReporteDto>> generarReporte(@PathVariable(name = "clienteId") Long clienteId)
            throws RegistroDuplicadoException, EntidadNoEncontradaException {
        return new ResponseEntity<>(
                this.reporteMapper.domainsToDtos(reporteService.generarReporte(clienteId)),
                HttpStatus.OK);
    }
}
