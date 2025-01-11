package ec.com.banco.cliente.domain.producto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
	
	private Long id;
	
	private String name;

}
