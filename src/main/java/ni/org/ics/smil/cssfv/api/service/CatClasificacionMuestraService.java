package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatClasificacionMuestra;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.CatClasificacionMuestraRepository;

/**
 * Created by SC on 28/09/2022.
 */
@Service
public class CatClasificacionMuestraService {

	@Autowired
	private CatClasificacionMuestraRepository repository;
	
	public CatClasificacionMuestra saveCatClasificacionMuestra(CatClasificacionMuestra catClasificacionMuestra) {
		return repository.save(catClasificacionMuestra);
	}

	public List<CatClasificacionMuestra> saveCatClasificacionMuestra(List<CatClasificacionMuestra> catClasificacionMuestra) {
		return repository.saveAll(catClasificacionMuestra);
	}

	public List<CatClasificacionMuestra> getCatClasificacionMuestra() {
		return repository.findAll();
	}
	
	public List<CatClasificacionMuestra> getAllCatClasificacionMuestraActivas() {
		return repository.getAllClasificacionMuestraActivas();
	}

	public CatClasificacionMuestra getCatClasificacionMuestraById(Long id) {
		CatClasificacionMuestra catClasificacionMuestra = repository.findById(id).orElse(null);
		if (catClasificacionMuestra == null) throw new NotEntityFoundException(CatClasificacionMuestra.class.getSimpleName(), "Id", id.toString());
		return catClasificacionMuestra;
	}

	public List<CatClasificacionMuestra> getCatClasificacionMuestraByName(String name) {
		List<CatClasificacionMuestra> catClasificacionMuestra = repository.findByNombre(name);
		if (catClasificacionMuestra.size() <= 0) throw new NotEntityFoundException(CatClasificacionMuestra.class.getSimpleName(), "nombre", name);
		return catClasificacionMuestra;
	}

	public void deleteCatClasificacionMuestran(Long id) {
		CatClasificacionMuestra oldCatClasificacionMuestra = repository.findById(id).orElse(null);		
		if (oldCatClasificacionMuestra == null) throw new NotEntityFoundException(CatClasificacionMuestra.class.getSimpleName(), "Id", id.toString());
		repository.deleteById(id);
	}

	public CatClasificacionMuestra updateCatClasificacionMuestra(CatClasificacionMuestra catClasificacionMuestra) {
		CatClasificacionMuestra oldCatClasificacionMuestra = repository.findById(catClasificacionMuestra.getId()).orElse(null);
		
		if (oldCatClasificacionMuestra == null) throw new NotEntityFoundException(CatClasificacionMuestra.class.getSimpleName(), "Id", catClasificacionMuestra.getId().toString());
		
		oldCatClasificacionMuestra.setNombre(catClasificacionMuestra.getNombre());
		oldCatClasificacionMuestra.setDescripcion(catClasificacionMuestra.getDescripcion());
		oldCatClasificacionMuestra.setActivo(catClasificacionMuestra.getActivo());
		return repository.save(oldCatClasificacionMuestra);
	}
}
