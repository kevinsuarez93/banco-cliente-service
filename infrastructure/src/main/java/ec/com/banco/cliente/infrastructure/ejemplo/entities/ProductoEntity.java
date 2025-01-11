package ec.com.banco.cliente.infrastructure.ejemplo.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "products")
public class ProductoEntity {

	@Id
    private Long id;

    private String name;

    private Integer stock;

    private BigDecimal price;

    private BigDecimal pvp;

    @Column(name = "has_discount")
    private boolean hasDiscount;

    @ManyToOne
    @JoinColumn(name = "category_id", updatable = false, insertable = false)
    private CategoriaEntity category;
    
    @Column(name = "category_id")
    private Long categoryId;
}
