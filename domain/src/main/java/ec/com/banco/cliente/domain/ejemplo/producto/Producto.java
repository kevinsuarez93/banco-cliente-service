package ec.com.banco.cliente.domain.ejemplo.producto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
 
    private Long id;

    private String name;

    private Integer stock;

    private BigDecimal price;

    private BigDecimal pvp;

    private boolean hasDiscount;

    private Categoria category;

    private Long categoryId;
}
