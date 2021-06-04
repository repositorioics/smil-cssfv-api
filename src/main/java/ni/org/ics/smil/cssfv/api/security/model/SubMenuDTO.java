package ni.org.ics.smil.cssfv.api.security.model;

import lombok.Data;

@Data
public class SubMenuDTO {
	private Long id;
	private String nombre;
	private String url;
	private String iconoSubMenu;
	
	public SubMenuDTO() {
		
	}
	public SubMenuDTO(Long id, String nombre, String url, String iconoSubMenu) {
		this.id = id;
		this.nombre = nombre;
		this.url = url;
		this.iconoSubMenu = iconoSubMenu;
	}	
}
