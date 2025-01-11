package ec.com.banco.cliente.infrastructure.ejemplo.in.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.banco.cliente.share.compuesto.EntidadConPkCompuestaDto;
import ec.com.banco.cliente.share.compuesto.PkCompuestaDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;


@RestController
@RequestMapping("/entidadcompuesta")
@Log4j2
@Tag(name = "EjemploLlaveCompuestaRestAdapter", description = "Ejemplo de get de recursos con llave compuesta")
public class EjemploLlaveCompuestaRestAdapter {	
	
    @GetMapping(value = "/{nocia}:{area}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EntidadConPkCompuestaDto getEntidad(@PathVariable(name = "nocia") String nocia, 
    		@PathVariable(name = "area") String area) {
    	log.trace("Trace");
    	log.debug("Debug");
    	log.info("Info");
    	log.warn("Warn");
    	log.error("Error");
        PkCompuestaDto pkCompuestaDto = new PkCompuestaDto(nocia, area);
        return EntidadConPkCompuestaDto.builder().pkCompuestaDto(pkCompuestaDto).nombreEntidad("Ejemplo Llave Compuesta").build();
    }	
}