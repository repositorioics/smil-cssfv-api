package ni.org.ics.smil.cssfv.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ni.org.ics.smil.cssfv.api.entity.security.Perfil;
import ni.org.ics.smil.cssfv.api.entity.security.PerfilOpcionMenu;
import ni.org.ics.smil.cssfv.api.entity.security.PerfilUsuario;
import ni.org.ics.smil.cssfv.api.service.SegPerfilOpcionMenuService;
import ni.org.ics.smil.cssfv.api.service.SegPerfilService;
import ni.org.ics.smil.cssfv.api.service.SegPerfilUsuarioService;

@RestController
public class PerfilController {

	@Autowired
	private SegPerfilService perfilService;

	@Autowired
	private SegPerfilOpcionMenuService perfilOpcionMenuService;
	
	@Autowired
	private SegPerfilUsuarioService perfilUsuarioService;
	
	@PostMapping("/seguridad/perfiles")
	public Perfil addPerfil(@RequestBody Perfil perfil) {
		return perfilService.savePerfil(perfil);
	}
	
	@GetMapping("/seguridad/perfiles")
	public List<Perfil> getPerfiles() {
		return perfilService.getPerfiles();
	}
	
    @GetMapping("/seguridad/perfiles/{id}")
    public Perfil getPerfilById(@PathVariable int id) {
        return perfilService.getPerfilById(id);
    }
	
	@PutMapping("/seguridad/perfiles")
    public Perfil updatePerfil(@RequestBody Perfil perfil) {
        return perfilService.updatePerfil(perfil);
    }
	
	/*PerfilOpcionMenu*/
	/*@PostMapping("/seguridad/perfilesopcionmenu")
	public PerfilOpcionMenu addPerfilOpcionMenu(@RequestBody PerfilOpcionMenu perfilOpcionMenu) {
		return perfilOpcionMenuService.savePerfilOpcionMenu(perfilOpcionMenu);
	}*/
	@PostMapping("/seguridad/perfilesopcionmenu")
	public List<PerfilOpcionMenu> addPerfilOpcionMenu(@RequestBody List<PerfilOpcionMenu> perfilOpcionMenu) {
		return perfilOpcionMenuService.savePerfilesOpcionesMenu(perfilOpcionMenu);
	}
	
	@GetMapping("/seguridad/perfilesopcionmenu")
	public List<PerfilOpcionMenu> getPerfilesOpcionesMenu() {
		return perfilOpcionMenuService.getPerfilesOpcionesMenu();
	}
	
    @GetMapping("/seguridad/perfilesopcionmenu/{id}")
    public PerfilOpcionMenu getPerfilOpcionMenuById(@PathVariable int id) {
        return perfilOpcionMenuService.getPerfilOpcionMenuById(id);
    }
	
	@PutMapping("/seguridad/perfilesopcionmenu")
    public PerfilOpcionMenu updatePerfilOpcionMenu(@RequestBody PerfilOpcionMenu perfilOpcionMenu) {
        return perfilOpcionMenuService.updatePerfilOpcionMenu(perfilOpcionMenu);
    }
	
	@GetMapping("/seguridad/perfilesopcionmenu/byperfil/{id}")
    public List<PerfilOpcionMenu> getPerfilOpcionMenuByPerfilId(@PathVariable int id) {
        return perfilOpcionMenuService.getPerfilOpcionMenuByPerfilId(id);
    }
	
	/*PerfilUsuario*/
	@PostMapping("/seguridad/perfilesusuario")
	public List<PerfilUsuario> addPerfilUsuario(@RequestBody List<PerfilUsuario> perfilUsuario) {
		return perfilUsuarioService.savePerfilUsuario(perfilUsuario);
	}
	
	@GetMapping("/seguridad/perfilesusuario")
	public List<PerfilUsuario> getPerfilUsuarios() {
		return perfilUsuarioService.getPerfilUsuarios();
	}
	
    @GetMapping("/seguridad/perfilesusuario/{id}")
    public PerfilUsuario getPerfilUsuarioById(@PathVariable int id) {
        return perfilUsuarioService.getPerfilUsuarioById(id);
    }
	
    @PutMapping("/seguridad/perfilesusuario")
    public PerfilUsuario updatePerfilOpcionMenu(@RequestBody PerfilUsuario perfilUsuario) {
        return perfilUsuarioService.updatePerfilUsuario(perfilUsuario);
    }
    
    @GetMapping("/seguridad/perfilesusuario/perfil")
    public List<PerfilUsuario> getPerfilUsuarioByNombre(@RequestParam String nombre) {
    	return perfilUsuarioService.getPerfilUsuarioByNombre(nombre);
    }
}
