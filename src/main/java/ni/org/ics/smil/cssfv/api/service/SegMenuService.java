package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.security.Menu;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.SegMenuRepository;

/**
 * Created by Miguel Salinas on 06/01/2021.
 */
@Service
public class SegMenuService {

	@Autowired
	private SegMenuRepository repository;

	public Menu saveMenu(Menu menu) {
		return repository.save(menu);
	}

	public List<Menu> saveMenus(List<Menu> menus) {
		return repository.saveAll(menus);
	}

	public List<Menu> getMenus() {
		return repository.findAll();
	}

	public Menu getMenuById(int id) {
		Menu menu = repository.findById(id).orElse(null);
		if (menu == null) throw new NotEntityFoundException(Menu.class.getSimpleName(), "Id", String.valueOf(id));
		return menu;
	}

	public void deleteMenu(int id) {
		Menu oldMenu = repository.findById(id).orElse(null);		
		if (oldMenu == null) throw new NotEntityFoundException(Menu.class.getSimpleName(), "Id", String.valueOf(id));
		repository.deleteById(id);
	}

	public Menu updateMenu(Menu menu) {
		Menu oldMenu = repository.findById(menu.getId()).orElse(null);
		
		if (oldMenu == null) throw new NotEntityFoundException(Menu.class.getSimpleName(), "Id", String.valueOf(menu.getId()));
		
		oldMenu.setNombre(menu.getNombre());
		oldMenu.setDescripcion(menu.getDescripcion());
		oldMenu.setActivo(menu.isActivo());
		oldMenu.setOrden(menu.getOrden());
		return repository.save(oldMenu);
	}
}
