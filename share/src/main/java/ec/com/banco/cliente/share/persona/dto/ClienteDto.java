package ec.com.banco.cliente.share.persona.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

    @NotNull(groups = { Default.class, Actualizar.class })
    private Long clienteId;

    @NotNull(groups = { Default.class, Crear.class })
    private Long personaId;

    @NotBlank(groups = { Default.class, Crear.class })
    @Size(max = 100, groups = { Default.class, Crear.class, Actualizar.class })
    private String password;

    @NotNull(groups = { Default.class, Crear.class })
    private Boolean estado;


    private PersonaDto persona;


    // Interfaces para definir grupos
    public interface Crear {
    }

    public interface Actualizar {
    }
}
