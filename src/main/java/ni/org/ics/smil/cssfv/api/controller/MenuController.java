package ni.org.ics.smil.cssfv.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ni.org.ics.smil.cssfv.api.entity.security.Menu;
import ni.org.ics.smil.cssfv.api.entity.security.OpcionMenu;
import ni.org.ics.smil.cssfv.api.entity.security.Perfil;
import ni.org.ics.smil.cssfv.api.service.SegMenuService;
import ni.org.ics.smil.cssfv.api.service.SegOpcionMenuService;

@RestController
public class MenuController {

	@Autowired
	private SegMenuService menuService;
	
	@Autowired
	private SegOpcionMenuService opcionMenuService;
	
	@PostMapping("/seguridad/menus")
	public Menu addMenu(@RequestBody Menu menu) {
		return menuService.saveMenu(menu);
	}
	
	@GetMapping("/seguridad/menus")
	public List<Menu> getMenus() {
		return menuService.getMenus();
	}
	
    @GetMapping("/seguridad/menus/{id}")
    public Menu getMenuById(@PathVariable int id) {
        return menuService.getMenuById(id);
    }
	
	@PutMapping("/seguridad/menus")
    public Menu updateMenu(@RequestBody Menu menu) {
        return menuService.updateMenu(menu);
    }
	
	/*Opciones Menu*/
	
	@PostMapping("/seguridad/opcionesmenu")
	public OpcionMenu addOpcionMenu(@RequestBody OpcionMenu opcionMenu) {
		return opcionMenuService.saveOpcionMenu(opcionMenu);
	}
	
	@GetMapping("/seguridad/opcionesmenu")
	public List<OpcionMenu> getOpcionesMenu() {
		return opcionMenuService.getOpcionesMenu();
	}
	
    @GetMapping("/seguridad/opcionesmenu/{id}")
    public OpcionMenu getOpcionMenuById(@PathVariable int id) {
        return opcionMenuService.getOpcionMenuById(id);
    }
	
	@PutMapping("/seguridad/opcionesmenu")
    public OpcionMenu updateOpcionMenu(@RequestBody OpcionMenu opcionMenu) {
        return opcionMenuService.updateOpcionMenu(opcionMenu);
    }
}
