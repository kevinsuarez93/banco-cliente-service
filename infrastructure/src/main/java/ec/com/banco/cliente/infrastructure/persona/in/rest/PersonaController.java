package ec.com.banco.cliente.infrastructure.persona.in.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
@Tag(name = "Personas", description = "Personas API")
public class PersonaController {
}
