package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.security.PerfilOpcionMenu;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.SegPerfilOpcionMenuRepository;

/**
 * Created by Miguel Salinas on 06/01/2021.
 */
@Service
public class SegPerfilOpcionMenuService {

	@Autowired
	private SegPerfilOpcionMenuRepository repository;

	public PerfilOpcionMenu savePerfilOpcionMenu(PerfilOpcionMenu perfilOpcionMenu) {
		return repository.save(perfilOpcionMenu);
	}

	public List<PerfilOpcionMenu> savePerfilesOpcionesMenu(List<PerfilOpcionMenu> perfilOpcionMenus) {
		return repository.saveAll(perfilOpcionMenus);
	}

	public List<PerfilOpcionMenu> getPerfilesOpcionesMenu() {
		return repository.findAll();
	}

	public PerfilOpcionMenu getPerfilOpcionMenuById(int id) {
		PerfilOpcionMenu perfilOpcionMenu = repository.findById(id).orElse(null);
		if (perfilOpcionMenu == null) throw new NotEntityFoundException(PerfilOpcionMenu.class.getSimpleName(), "Id", String.valueOf(id));
		return perfilOpcionMenu;
	}

	public void deletePerfilOpcionMenu(int id) {
		PerfilOpcionMenu oldPerfilOpcionMenu = repository.findById(id).orElse(null);		
		if (oldPerfilOpcionMenu == null) throw new NotEntityFoundException(PerfilOpcionMenu.class.getSimpleName(), "Id", String.valueOf(id));
		repository.deleteById(id);
	}

	public PerfilOpcionMenu updatePerfilOpcionMenu(PerfilOpcionMenu perfilOpcionMenu) {
		PerfilOpcionMenu oldPerfilOpcionMenu = repository.findById(perfilOpcionMenu.getId()).orElse(null);
		
		if (oldPerfilOpcionMenu == null) throw new NotEntityFoundException(PerfilOpcionMenu.class.getSimpleName(), "Id", String.valueOf(perfilOpcionMenu.getId()));
		
		oldPerfilOpcionMenu.setActivo(perfilOpcionMenu.isActivo());
		return repository.save(oldPerfilOpcionMenu);
	}
}
