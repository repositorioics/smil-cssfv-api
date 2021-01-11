package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.security.PerfilOpcionMenu;
import ni.org.ics.smil.cssfv.api.entity.security.PerfilUsuario;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.SegPerfilUsuarioRepository;

/**
 * Created by Miguel Salinas on 06/01/2021.
 */
@Service
public class SegPerfilUsuarioService {

	@Autowired
	private SegPerfilUsuarioRepository repository;

	public PerfilUsuario savePerfilUsuario(PerfilUsuario perfilUsuario) {
		return repository.save(perfilUsuario);
	}

	public List<PerfilUsuario> savePerfilUsuarios(List<PerfilUsuario> perfilUsuarios) {
		return repository.saveAll(perfilUsuarios);
	}

	public List<PerfilUsuario> getPerfilUsuarios() {
		return repository.findAll();
	}

	public PerfilUsuario getPerfilUsuarioById(int id) {
		PerfilUsuario perfilUsuario = repository.findById(id).orElse(null);
		if (perfilUsuario == null) throw new NotEntityFoundException(PerfilUsuario.class.getSimpleName(), "Id", String.valueOf(id));
		return perfilUsuario;
	}
	
	public PerfilUsuario updatePerfilUsuario(PerfilUsuario perfilUsuario) {
		PerfilUsuario oldPerfilUsuario = repository.findById(perfilUsuario.getId()).orElse(null);
		
		if (oldPerfilUsuario == null) throw new NotEntityFoundException(PerfilOpcionMenu.class.getSimpleName(), "Id", String.valueOf(perfilUsuario.getId()));
		
		oldPerfilUsuario.setActivo(perfilUsuario.isActivo());
		return repository.save(perfilUsuario);
	}

	public void deletePerfilUsuario(int id) {
		PerfilUsuario oldPerfilUsuario = repository.findById(id).orElse(null);		
		if (oldPerfilUsuario == null) throw new NotEntityFoundException(PerfilUsuario.class.getSimpleName(), "Id", String.valueOf(id));
		repository.deleteById(id);
	}
}
