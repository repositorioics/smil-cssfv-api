package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatAnioEstudio;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.CatAnioEstudioRepository;


/**
 * Created by SC on 07/06/2022.
 */
@Service
public class CatAnioEstudioService {

	@Autowired
	private CatAnioEstudioRepository repository;
	
	public CatAnioEstudio saveCatAnioEstudio(CatAnioEstudio catAnioEstudio) {
		return repository.save(catAnioEstudio);
	}
	
	public CatAnioEstudio getLastAnioEstudio() {
		return repository.findLastRecordAnioEstudio();
	}

	public List<CatAnioEstudio> saveCatAnioEstudio(List<CatAnioEstudio> catAnioEstudio) {
		return repository.saveAll(catAnioEstudio);
	}

	public List<CatAnioEstudio> getCatAnioEstudio() {
		return repository.findAll();
	}

	public CatAnioEstudio getCatAnioEstudioById(Long id) {
		CatAnioEstudio catAnioEstudio = repository.findById(id).orElse(null);
		if (catAnioEstudio == null) throw new NotEntityFoundException(CatAnioEstudio.class.getSimpleName(), "Id", id.toString());
		return catAnioEstudio;
	}

	/*public List<CatAnioEstudio> getCatAnioEstudioByName(String name) {
		List<CatAnioEstudio> catAnioEstudio = repository.findByNombre(name);
		if (catAnioEstudio.size() <= 0) throw new NotEntityFoundException(CatAnioEstudio.class.getSimpleName(), "nombre", name);
		return catAnioEstudio;
	}*/

	public void deleteCatAnioEstudio(Long id) {
		CatAnioEstudio oldCatAnioEstudio = repository.findById(id).orElse(null);		
		if (oldCatAnioEstudio == null) throw new NotEntityFoundException(CatAnioEstudio.class.getSimpleName(), "Id", id.toString());
		repository.deleteById(id);
	}

	public CatAnioEstudio updateCatAnioEstudio(CatAnioEstudio catAnioEstudio) {
		CatAnioEstudio oldCatAnioEstudio = repository.findById(catAnioEstudio.getId()).orElse(null);
		
		if (oldCatAnioEstudio == null) throw new NotEntityFoundException(CatAnioEstudio.class.getSimpleName(), "Id", catAnioEstudio.getId().toString());
		
		oldCatAnioEstudio.setFechaInicio(catAnioEstudio.getFechaInicio());
		oldCatAnioEstudio.setFechaFin(catAnioEstudio.getFechaFin());
		oldCatAnioEstudio.setAnio(catAnioEstudio.getAnio());
		return repository.save(oldCatAnioEstudio);
	}
}
