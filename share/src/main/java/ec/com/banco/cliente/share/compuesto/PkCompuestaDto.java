package ec.com.banco.cliente.share.compuesto;

import lombok.Getter;

@Getter
public class PkCompuestaDto {
	
	private String campo1;
	private String campo2;
	
    public PkCompuestaDto(String campo1, String campo2) {
        super();
        this.campo1 = campo1;
        this.campo2 = campo2;
    }
    
}
