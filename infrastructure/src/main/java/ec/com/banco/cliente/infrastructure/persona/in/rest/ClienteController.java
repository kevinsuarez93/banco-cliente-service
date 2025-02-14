package ec.com.banco.cliente.infrastructure.persona.in.rest;


import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cliente.domain.common.exception.RegistroDuplicadoException;
import ec.com.banco.cliente.domain.persona.services.ClienteService;
import ec.com.banco.cliente.infrastructure.common.exceptions.ErrorResponse;
import ec.com.banco.cliente.infrastructure.persona.mappers.ClienteMapper;
import ec.com.banco.cliente.share.persona.dto.ClienteDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Cliente", description = "Cliente API")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    public ClienteController(ClienteService clienteService, ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }


    /**
     * Crear nueva cliente.
     *
     * @param crearDto Dto de crear cliente
     * @return CompaniasCrearDto
     */
    @PostMapping
    @Operation(summary = "Crear nuevo cliente")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Cliente creada."),
            @ApiResponse(responseCode = "400", description = "Entrada inválida.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Cliente ya exists", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })

    public ResponseEntity<Void> crearCliente(@Validated(ClienteDto.Crear.class) @RequestBody ClienteDto crearDto)
            throws RegistroDuplicadoException {
        this.clienteService.crearCliente(this.clienteMapper.dtoToDomain(crearDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Actualizar Compania
     *
     * @param actualizarDto dto para actualizar compania
     * @return CompaniasActualizarDto
     * @throws EntidadNoEncontradaException
     */
    @PatchMapping()
    @Operation(summary = "Actualizar Cliente")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente ha sido actualizado."),
            @ApiResponse(responseCode = "404", description = "Cliente no existe.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
    public ResponseEntity<Void> actualizarCliente(
            @Validated(ClienteDto.Actualizar.class) @RequestBody ClienteDto actualizarDto)
            throws EntidadNoEncontradaException {
        this.clienteService.actualizarCliente(this.clienteMapper.dtoToDomain(actualizarDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Eliminar compania
     *
     * @param noCia Id company
     */
    @DeleteMapping("/{noCia}")
    @Operation(summary = "Eliminar cliente")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente ha sido eliminada."),
            @ApiResponse(responseCode = "404", description = "Cliente no existe.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
    public ResponseEntity<Void> eliminarCliente(@NotNull @PathVariable Long noCia)
            throws EntidadNoEncontradaException {
        clienteService.eliminarCliente(noCia);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
