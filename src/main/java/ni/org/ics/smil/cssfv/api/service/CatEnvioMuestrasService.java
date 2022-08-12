package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatEnvioMuestras;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.CatEnvioMuestrasRepository;

@Service
public class CatEnvioMuestrasService {

	@Autowired
	private CatEnvioMuestrasRepository envioMuestraRepository;
	
	public List<CatEnvioMuestras> getAllCatEnvioMuestras() {
		return envioMuestraRepository.findAllCatEnvioMuestrasActivas();
	}
	
	public List<CatEnvioMuestras> saveCatAnioEstudio(List<CatEnvioMuestras> catEnvioMuestra) {
		return envioMuestraRepository.saveAll(catEnvioMuestra);
	}
	
	public CatEnvioMuestras updateCatAnioEstudio(CatEnvioMuestras catEnvioMuestra) {
		CatEnvioMuestras oldCatEnvioMuestra = envioMuestraRepository.findById(catEnvioMuestra.getId()).orElse(null);
		
		if (oldCatEnvioMuestra == null) throw new NotEntityFoundException(CatEnvioMuestras.class.getSimpleName(), "Id", catEnvioMuestra.getId().toString());
		
		oldCatEnvioMuestra.setNombre(catEnvioMuestra.getNombre());
		oldCatEnvioMuestra.setEstado(catEnvioMuestra.getEstado());
		return envioMuestraRepository.save(catEnvioMuestra);
	}
}
