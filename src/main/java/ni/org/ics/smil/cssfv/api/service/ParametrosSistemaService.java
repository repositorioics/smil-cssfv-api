package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.security.ParametrosSistema;
import ni.org.ics.smil.cssfv.api.repository.ParametrosSistemaRepository;


@Service
public class ParametrosSistemaService {
	
	@Autowired
	private ParametrosSistemaRepository repository;

	public List<ParametrosSistema> getParametrosSistemas() {
		return repository.findAll();
	}
	
	public ParametrosSistema getParametrosSistemaByName(String nombre) {
		return repository.findAllParametrosSistemaByName(nombre);
	}
}
