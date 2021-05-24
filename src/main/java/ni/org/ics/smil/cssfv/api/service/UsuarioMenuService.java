package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.view.UsuarioMenuView;
import ni.org.ics.smil.cssfv.api.repository.UsuarioMenuRepository;

@Service
public class UsuarioMenuService {

	@Autowired
	private UsuarioMenuRepository repository;
	
	public List<UsuarioMenuView> getMenu() {
        return repository.findAll();
    }
	
	
	public List<UsuarioMenuView> getMenuByUsuario(String usuario) {
        return repository.findByUsuario(usuario);
    }
	
}
