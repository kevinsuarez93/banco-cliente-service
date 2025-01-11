package ec.com.banco.cliente.share.ejemplo.dto;

import java.math.BigDecimal;

import ec.com.centric.commons.rest.ValidacionCondicional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductoDto {

	@NotNull(groups = ValidacionCondicional.Crear.class)
	@Null(groups = ValidacionCondicional.Actualizar.class)
    private Long id;

	@NotNull
    private String name;

    private Integer stock;

    private BigDecimal price;

    private BigDecimal pvp;

    private boolean hasDiscount;

    private CategoriaDto category;

    private Long categoryId;
}

