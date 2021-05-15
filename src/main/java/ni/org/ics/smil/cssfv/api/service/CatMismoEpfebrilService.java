package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatMismoEpfebril;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.CatMismoEpfebrilRepository;

/**
 * Created by Santiago Carballo on 19/12/2020.
 */
@Service
public class CatMismoEpfebrilService {

	@Autowired
	private CatMismoEpfebrilRepository repository;

	public CatMismoEpfebril saveCatMismoEpfebril(CatMismoEpfebril CatMismoEpfebril) {
		return repository.save(CatMismoEpfebril);
	}

	public List<CatMismoEpfebril> saveCatMismoEpfebril(List<CatMismoEpfebril> CatMismoEpfebril) {
		return repository.saveAll(CatMismoEpfebril);
	}

	public List<CatMismoEpfebril> getCatMismoEpfebril() {
		return repository.findAll();
	}

	public CatMismoEpfebril getCatMismoEpfebrilById(Long id) {
		CatMismoEpfebril CatMismoEpfebril = repository.findById(id).orElse(null);
		if (CatMismoEpfebril == null) throw new NotEntityFoundException(CatMismoEpfebril.class.getSimpleName(), "Id", id.toString());
		return CatMismoEpfebril;
	}

	public List<CatMismoEpfebril> getCatMismoEpfebrilByName(String name) {
		List<CatMismoEpfebril> CatMismoEpfebril = repository.findByNombre(name);
		if (CatMismoEpfebril.size() <= 0) throw new NotEntityFoundException(CatMismoEpfebril.class.getSimpleName(), "nombre", name);
		return CatMismoEpfebril;
	}

	public void deleteCatMismoEpfebril(Long id) {
		CatMismoEpfebril oldCatMismoEpfebril = repository.findById(id).orElse(null);		
		if (oldCatMismoEpfebril == null) throw new NotEntityFoundException(CatMismoEpfebril.class.getSimpleName(), "Id", id.toString());
		repository.deleteById(id);
	}

	public CatMismoEpfebril updateCatMismoEpfebril(CatMismoEpfebril CatMismoEpfebril) {
		CatMismoEpfebril oldCatMismoEpfebril = repository.findById(CatMismoEpfebril.getId()).orElse(null);
		
		if (oldCatMismoEpfebril == null) throw new NotEntityFoundException(CatMismoEpfebril.class.getSimpleName(), "Id", CatMismoEpfebril.getId().toString());
		
		oldCatMismoEpfebril.setNombre(CatMismoEpfebril.getNombre());
		oldCatMismoEpfebril.setDescripcion(CatMismoEpfebril.getDescripcion());
		oldCatMismoEpfebril.setActivo(CatMismoEpfebril.getActivo());
		return repository.save(oldCatMismoEpfebril);
	}
}
