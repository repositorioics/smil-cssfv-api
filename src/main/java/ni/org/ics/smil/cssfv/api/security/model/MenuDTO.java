package ni.org.ics.smil.cssfv.api.security.model;

import java.util.List;

import lombok.Data;

@Data
public class MenuDTO {

	private Long id;
	private String nombre;
	private String icono;
	private List<SubMenuDTO> opciones;
	
	public MenuDTO() {
		
	}

	public MenuDTO(Long id, String nombre, String icono, List<SubMenuDTO> opciones) {
		this.id = id;
		this.nombre = nombre;
		this.icono = icono;
		this.opciones = opciones;
	}
}
