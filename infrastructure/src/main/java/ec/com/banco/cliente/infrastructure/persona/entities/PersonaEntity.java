package ec.com.banco.cliente.infrastructure.persona.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persona")
public class PersonaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id")
    private Long personaId;

    @NotNull
    @Size(max = 50)
    private String nombre;

    @NotNull
    @Column(length = 1)
    private String genero;

    @NotNull
    @Min(0)
    private Integer edad;

    @NotNull
    @Size(max = 20)
    private String identificacion;

    @Size(max = 100)
    private String direccion;

    @Size(max = 20)
    private String telefono;

    // Relaciones
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    private ClienteEntity cliente;
}
