package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatTipoMuestra;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.CatTipoMuestraRepository;

/**
 * Created by Miguel Salinas on 14/12/2020.
 */
@Service
public class CatTipoMuestraService {

	@Autowired
	private CatTipoMuestraRepository repository;

	public CatTipoMuestra saveCatTipoMuestra(CatTipoMuestra catTipoMuestra) {
		return repository.save(catTipoMuestra);
	}

	public List<CatTipoMuestra> saveCatTipoMuestras(List<CatTipoMuestra> catTipoMuestraes) {
		return repository.saveAll(catTipoMuestraes);
	}

	public List<CatTipoMuestra> getCatTiposMuestras() {
		return repository.findAll();
	}
	
	public List<CatTipoMuestra> getTipoMuestrasActivas() {
		return repository.findAllTipoMuestrasActivas();
	}

	public CatTipoMuestra getCatTipoMuestraById(Long id) {
		CatTipoMuestra CatTipoMuestra = repository.findById(id).orElse(null);
		if (CatTipoMuestra == null) throw new NotEntityFoundException(CatTipoMuestra.class.getSimpleName(), "Id", id.toString());
		return CatTipoMuestra;
	}

	public List<CatTipoMuestra> getCatTipoMuestraByName(String name) {
		List<CatTipoMuestra> CatTipoMuestra = repository.findByNombre(name);
		if (CatTipoMuestra.size() <= 0) throw new NotEntityFoundException(CatTipoMuestra.class.getSimpleName(), "nombre", name);
		return CatTipoMuestra;
	}

	public void deleteCatTipoMuestra(Long id) {
		CatTipoMuestra oldCatTipoMuestra = repository.findById(id).orElse(null);		
		if (oldCatTipoMuestra == null) throw new NotEntityFoundException(CatTipoMuestra.class.getSimpleName(), "Id", id.toString());
		repository.deleteById(id);
	}

	public CatTipoMuestra updateCatTipoMuestra(CatTipoMuestra catTipoMuestra) {
		CatTipoMuestra oldCatTipoMuestra = repository.findById(catTipoMuestra.getId()).orElse(null);
		
		if (oldCatTipoMuestra == null) throw new NotEntityFoundException(CatTipoMuestra.class.getSimpleName(), "Id", catTipoMuestra.getId().toString());
		
		oldCatTipoMuestra.setNombre(catTipoMuestra.getNombre());
		oldCatTipoMuestra.setDescripcion(catTipoMuestra.getDescripcion());
		oldCatTipoMuestra.setActivo(catTipoMuestra.getActivo());
		return repository.save(oldCatTipoMuestra);
	}
}
