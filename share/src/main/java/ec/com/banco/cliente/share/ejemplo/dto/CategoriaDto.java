package ec.com.banco.cliente.share.ejemplo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoriaDto {
	
	private Long id;
	
	private String name;

}