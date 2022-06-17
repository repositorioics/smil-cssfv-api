package ni.org.ics.smil.cssfv.api.controller;

import java.util.ArrayList;
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
import ni.org.ics.smil.cssfv.api.entity.view.UsuarioMenuView;
import ni.org.ics.smil.cssfv.api.security.model.MenuDTO;
import ni.org.ics.smil.cssfv.api.security.model.MenuResponse;
import ni.org.ics.smil.cssfv.api.security.model.SubMenuDTO;
import ni.org.ics.smil.cssfv.api.service.SegMenuService;
import ni.org.ics.smil.cssfv.api.service.SegOpcionMenuService;
import ni.org.ics.smil.cssfv.api.service.UsuarioMenuService;

@RestController
public class MenuController {

	@Autowired
	private SegMenuService menuService;
	
	@Autowired
	private SegOpcionMenuService opcionMenuService;
	
	@Autowired
	private UsuarioMenuService usuarioMenuService;
	
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
	
    @GetMapping("/seguridad/opcionesmenuusuario/{usuario}")
    public MenuResponse getOpcionMenuById(@PathVariable String usuario) {
    	MenuResponse menuResponse = new MenuResponse();
        List<UsuarioMenuView> usuarioMenu = usuarioMenuService.getMenuByUsuario(usuario);
        menuResponse.setUsuario(usuario);
        menuResponse.setUsuarioId(usuarioMenu.get(0).getUsuarioid());
        Long idMenu = 0L;
        List<MenuDTO> menus = new ArrayList<MenuDTO>();
        List<SubMenuDTO> subMenus = new ArrayList<SubMenuDTO>();
        int i = 0, j = usuarioMenu.size();
        UsuarioMenuView menuAnterior = new UsuarioMenuView();
        for(UsuarioMenuView menuView : usuarioMenu) {        	
        	
        	//primera iteracion
        	if (idMenu == 0L) {
        		idMenu = menuView.getMenuid();
        		menuAnterior = menuView;
        	} else {
        		menuAnterior = usuarioMenu.get(i-1);
        	}
        	
        	if (idMenu != menuView.getMenuid()) { //cambio de menu padre, es necesario agregarlo al response el menu padre antes del cambio
        		menus.add(new MenuDTO(menuAnterior.getMenuid(), menuAnterior.getMenu(), menuAnterior.getIconomenu(), subMenus));
        		//marcar cambio de menu padre
        		idMenu = menuView.getMenuid();
        		//resetear lista de submenus
        		subMenus = new ArrayList<SubMenuDTO>();
        		//agregar elemento a la nueva lista
        		SubMenuDTO subMenu = new SubMenuDTO(menuView.getSubmenuid(), menuView.getSubmenu(), menuView.getUrl(), menuView.getIconosubmenu());
            	subMenus.add(subMenu);
            	if (i == j - 1) { // es el ultimo elemento, agregar menu padre nuevo
            		menus.add(new MenuDTO(menuView.getMenuid(), menuView.getMenu(), menuView.getIconomenu(), subMenus));
        		}
        	} else {
        		//es el mismo menu padre, solo agregar el submenu
        		SubMenuDTO subMenu = new SubMenuDTO(menuView.getSubmenuid(), menuView.getSubmenu(), menuView.getUrl(), menuView.getIconosubmenu());
            	subMenus.add(subMenu);
        		if (i == j - 1) { // es el ultimo elemento, agregar menu padre
        			menus.add(new MenuDTO(menuAnterior.getMenuid(), menuAnterior.getMenu(), menuAnterior.getIconomenu(), subMenus));
        		}        			
        	}        	
        	i++;
        }
        menuResponse.setMenus(menus);
        
    	return menuResponse;        
    }
	
	
}
