package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatMuestra;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.CatMuestraRepository;

/**
 * Created by Miguel Salinas on 14/12/2020.
 */
@Service
public class CatMuestraService {

	@Autowired
	private CatMuestraRepository repository;

	public CatMuestra saveCatMuestra(CatMuestra catMuestra) {
		return repository.save(catMuestra);
	}

	public List<CatMuestra> saveCatMuestras(List<CatMuestra> catMuestraes) {
		return repository.saveAll(catMuestraes);
	}

	public List<CatMuestra> getCatMuestras() {
		return repository.findAll();
	}

	public CatMuestra getCatMuestraById(Long id) {
		CatMuestra CatMuestra = repository.findById(id).orElse(null);
		if (CatMuestra == null) throw new NotEntityFoundException(CatMuestra.class.getSimpleName(), "Id", id.toString());
		return CatMuestra;
	}

	public List<CatMuestra> getCatMuestraByName(String name) {
		List<CatMuestra> CatMuestra = repository.findByNombre(name);
		if (CatMuestra.size() <= 0) throw new NotEntityFoundException(CatMuestra.class.getSimpleName(), "nombre", name);
		return CatMuestra;
	}

	public void deleteCatMuestra(Long id) {
		CatMuestra oldCatMuestra = repository.findById(id).orElse(null);		
		if (oldCatMuestra == null) throw new NotEntityFoundException(CatMuestra.class.getSimpleName(), "Id", id.toString());
		repository.deleteById(id);
	}

	public CatMuestra updateCatMuestra(CatMuestra catMuestra) {
		CatMuestra oldCatMuestra = repository.findById(catMuestra.getId()).orElse(null);
		
		if (oldCatMuestra == null) throw new NotEntityFoundException(CatMuestra.class.getSimpleName(), "Id", catMuestra.getId().toString());
		
		oldCatMuestra.setNombre(catMuestra.getNombre());
		oldCatMuestra.setDescripcion(catMuestra.getDescripcion());
		oldCatMuestra.setActivo(catMuestra.getActivo());
		return repository.save(oldCatMuestra);
	}
}
