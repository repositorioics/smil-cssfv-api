package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatTubo;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.CatTuboRepository;

/**
 * Created by Miguel Salinas on 14/12/2020.
 */
@Service
public class CatTuboService {

	@Autowired
	private CatTuboRepository repository;

	public CatTubo saveCatTubo(CatTubo catTubo) {
		return repository.save(catTubo);
	}

	public List<CatTubo> saveCatTubos(List<CatTubo> catTuboes) {
		return repository.saveAll(catTuboes);
	}

	public List<CatTubo> getCatTubos() {
		return repository.findAll();
	}

	public CatTubo getCatTuboById(Long id) {
		CatTubo CatTubo = repository.findById(id).orElse(null);
		if (CatTubo == null) throw new NotEntityFoundException(CatTubo.class.getSimpleName(), "Id", id.toString());
		return CatTubo;
	}

	public List<CatTubo> getCatTuboByName(String name) {
		List<CatTubo> CatTubo = repository.findByTubo(name);
		if (CatTubo.size() <= 0) throw new NotEntityFoundException(CatTubo.class.getSimpleName(), "tubo", name);
		return CatTubo;
	}

	public void deleteCatTubo(Long id) {
		CatTubo oldCatTubo = repository.findById(id).orElse(null);		
		if (oldCatTubo == null) throw new NotEntityFoundException(CatTubo.class.getSimpleName(), "Id", id.toString());
		repository.deleteById(id);
	}

	public CatTubo updateCatTubo(CatTubo catTubo) {
		CatTubo oldCatTubo = repository.findById(catTubo.getId()).orElse(null);
		
		if (oldCatTubo == null) throw new NotEntityFoundException(CatTubo.class.getSimpleName(), "Id", catTubo.getId().toString());
		
		oldCatTubo.setTubo(catTubo.getTubo());
		oldCatTubo.setDescripcion(catTubo.getDescripcion());
		oldCatTubo.setActivo(catTubo.getActivo());
		return repository.save(oldCatTubo);
	}
}
