package ec.com.banco.cliente.share.compuesto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntidadConPkCompuestaDto {

	private PkCompuestaDto pkCompuestaDto;
	
	private String nombreEntidad;

	private EntidadConPkCompuestaDto() {
        // Private constructor to prevent direct instantiation
    }

    public static EntidadConPkCompuestaDtoBuilder builder() {
        return new EntidadConPkCompuestaDtoBuilder();
    }

    public static class EntidadConPkCompuestaDtoBuilder {
        private PkCompuestaDto pkCompuestaDto;
        private String nombreEntidad;

        public EntidadConPkCompuestaDtoBuilder pkCompuestaDto(PkCompuestaDto pkCompuestaDto) {
            this.pkCompuestaDto = pkCompuestaDto;
            return this;
        }

        public EntidadConPkCompuestaDtoBuilder nombreEntidad(String nombreEntidad) {
            this.nombreEntidad = nombreEntidad;
            return this;
        }

        public EntidadConPkCompuestaDto build() {
            EntidadConPkCompuestaDto entidad = new EntidadConPkCompuestaDto();
            entidad.setPkCompuestaDto(this.pkCompuestaDto);
            entidad.setNombreEntidad(this.nombreEntidad);
            return entidad;
        }
    }
	
}
