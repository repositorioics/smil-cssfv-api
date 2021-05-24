package ni.org.ics.smil.cssfv.api.security.model;

import java.util.List;

import lombok.Data;

@Data
public class MenuDTO {

	private Long id;
	private String nombre;
	private List<SubMenuDTO> opciones;
	
	public MenuDTO() {
		
	}

	public MenuDTO(Long id, String nombre, List<SubMenuDTO> opciones) {
		this.id = id;
		this.nombre = nombre;
		this.opciones = opciones;
	}
}
