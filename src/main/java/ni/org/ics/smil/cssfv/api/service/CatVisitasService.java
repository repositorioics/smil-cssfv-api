package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatTubo;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatVisitas;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.CatVisitasRepository;

/**
 * Created by SC on 16/06/2021.
 */
@Service
public class CatVisitasService {

	@Autowired
	private CatVisitasRepository repository;

	public CatVisitas saveCatVisitas(CatVisitas catVisitas) {
		return repository.save(catVisitas);
	}

	public List<CatVisitas> saveCatVisitas(List<CatVisitas> visitas) {
		return repository.saveAll(visitas);
	}

	public List<CatVisitas> getCatVisitas() {
		return repository.findAll();
	}
	
	public List<CatVisitas> getAllCatVisitas() {
		return repository.findAllVisitasActivas();
	}

	public CatVisitas getVisitasById(Long id) {
		CatVisitas catVisitas = repository.findById(id).orElse(null);
		if (catVisitas == null) throw new NotEntityFoundException(CatVisitas.class.getSimpleName(), "Id", id.toString());
		return catVisitas;
	}

	public List<CatVisitas> getCatCategoriaByName(String name) {
		List<CatVisitas> CatVisitas = repository.findByNombre(name);
		if (CatVisitas.size() <= 0) throw new NotEntityFoundException(CatVisitas.class.getSimpleName(), "nombre", name);
		return CatVisitas;
	}

	public void deleteCatVisitas(Long id) {
		CatVisitas oldCatVisita = repository.findById(id).orElse(null);		
		if (oldCatVisita == null) throw new NotEntityFoundException(CatVisitas.class.getSimpleName(), "Id", id.toString());
		repository.deleteById(id);
	}

	public CatVisitas updateCatVisita(CatVisitas catVisita) {
		CatVisitas oldCatVisita = repository.findById(catVisita.getId()).orElse(null);
		
		if (oldCatVisita == null) throw new NotEntityFoundException(CatVisitas.class.getSimpleName(), "Id", catVisita.getId().toString());
		
		oldCatVisita.setNombre(catVisita.getNombre());
		oldCatVisita.setDescripcion(catVisita.getDescripcion());
		oldCatVisita.setActivo(catVisita.getActivo());
		return repository.save(oldCatVisita);
	}
}
