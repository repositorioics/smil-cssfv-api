package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.security.Perfil;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.SegPerfilRepository;

/**
 * Created by Miguel Salinas on 06/01/2021.
 */
@Service
public class SegPerfilService {

	@Autowired
	private SegPerfilRepository repository;

	public Perfil savePerfil(Perfil perfil) {
		return repository.save(perfil);
	}

	public List<Perfil> savePerfiles(List<Perfil> perfils) {
		return repository.saveAll(perfils);
	}

	public List<Perfil> getPerfiles() {
		return repository.findAll();
	}

	public Perfil getPerfilById(int id) {
		Perfil perfil = repository.findById(id).orElse(null);
		if (perfil == null) throw new NotEntityFoundException(Perfil.class.getSimpleName(), "Id", String.valueOf(id));
		return perfil;
	}

	public void deletePerfil(int id) {
		Perfil oldPerfil = repository.findById(id).orElse(null);		
		if (oldPerfil == null) throw new NotEntityFoundException(Perfil.class.getSimpleName(), "Id", String.valueOf(id));
		repository.deleteById(id);
	}

	public Perfil updatePerfil(Perfil perfil) {
		Perfil oldPerfil = repository.findById(perfil.getId()).orElse(null);
		
		if (oldPerfil == null) throw new NotEntityFoundException(Perfil.class.getSimpleName(), "Id", String.valueOf(perfil.getId()));
		
		oldPerfil.setNombre(perfil.getNombre());
		oldPerfil.setDescripcion(perfil.getDescripcion());
		return repository.save(oldPerfil);
	}
}
