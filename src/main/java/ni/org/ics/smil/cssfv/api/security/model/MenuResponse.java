package ni.org.ics.smil.cssfv.api.security.model;

import java.util.List;

import lombok.Data;

@Data
public class MenuResponse {
	
	private String usuario;
	//private String perfil;
	private List<MenuDTO> menus;
	
}
