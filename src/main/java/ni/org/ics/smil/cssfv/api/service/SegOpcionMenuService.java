package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.security.OpcionMenu;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.SegOpcionMenuRepository;

/**
 * Created by Miguel Salinas on 06/01/2021.
 */
@Service
public class SegOpcionMenuService {

	@Autowired
	private SegOpcionMenuRepository repository;

	public OpcionMenu saveOpcionMenu(OpcionMenu opcionMenu) {
		return repository.save(opcionMenu);
	}

	public List<OpcionMenu> saveOpcionesMenu(List<OpcionMenu> opcionMenus) {
		return repository.saveAll(opcionMenus);
	}

	public List<OpcionMenu> getOpcionesMenu() {
		return repository.findAll();
	}

	public OpcionMenu getOpcionMenuById(int id) {
		OpcionMenu opcionMenu = repository.findById(id).orElse(null);
		if (opcionMenu == null) throw new NotEntityFoundException(OpcionMenu.class.getSimpleName(), "Id", String.valueOf(id));
		return opcionMenu;
	}

	public void deleteOpcionMenu(int id) {
		OpcionMenu oldOpcionMenu = repository.findById(id).orElse(null);		
		if (oldOpcionMenu == null) throw new NotEntityFoundException(OpcionMenu.class.getSimpleName(), "Id", String.valueOf(id));
		repository.deleteById(id);
	}

	public OpcionMenu updateOpcionMenu(OpcionMenu opcionMenu) {
		OpcionMenu oldOpcionMenu = repository.findById(opcionMenu.getId()).orElse(null);
		
		if (oldOpcionMenu == null) throw new NotEntityFoundException(OpcionMenu.class.getSimpleName(), "Id", String.valueOf(opcionMenu.getId()));
		
		oldOpcionMenu.setNombre(opcionMenu.getNombre());
		oldOpcionMenu.setDescripcion(opcionMenu.getDescripcion());
		oldOpcionMenu.setActivo(opcionMenu.isActivo());
		oldOpcionMenu.setOrden(opcionMenu.getOrden());
		oldOpcionMenu.setUrl(opcionMenu.getUrl());
		oldOpcionMenu.setIcono(opcionMenu.getIcono());
		oldOpcionMenu.setVisible(opcionMenu.isVisible());
		return repository.save(oldOpcionMenu);
	}
}
