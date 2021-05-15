package ni.org.ics.smil.cssfv.api.service;

import java.util.ArrayList;
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
		List<PerfilOpcionMenu> opcionesMenus = new ArrayList<>();
		
		for (PerfilOpcionMenu perfilOpcionMenu : perfilOpcionMenus) {
			
			if (perfilOpcionMenu.isActivo() == true) {
				
				PerfilOpcionMenu verifExist = null;
				verifExist = repository.findByPerfilIdIdAndOpcionMenuIdId(perfilOpcionMenu.getPerfilId().getId(), perfilOpcionMenu.getOpcionMenuId().getId());
				
				if (verifExist == null) {
					PerfilOpcionMenu newPerfilOpcionMenu = new PerfilOpcionMenu();
					
					newPerfilOpcionMenu.setPerfilId(perfilOpcionMenu.getPerfilId());
					newPerfilOpcionMenu.setOpcionMenuId(perfilOpcionMenu.getOpcionMenuId());
					newPerfilOpcionMenu.setActivo(perfilOpcionMenu.isActivo());
					
					opcionesMenus.add(newPerfilOpcionMenu);
				} else {
					verifExist.setActivo(true);
					repository.save(verifExist);
				}
			} else {
				PerfilOpcionMenu oldData = null;
				oldData = repository.findByPerfilIdIdAndOpcionMenuIdId(perfilOpcionMenu.getPerfilId().getId(), perfilOpcionMenu.getOpcionMenuId().getId());
				if (oldData != null) {
					oldData.setActivo(false);
					repository.save(oldData);
				}
			}
		}
		return repository.saveAll(opcionesMenus);
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
	
	public List<PerfilOpcionMenu> getPerfilOpcionMenuByPerfilId(int id) {
		return repository.findByPerfilIdId(id);
	}
}
