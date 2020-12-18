package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.catalogos.CatTipoPrueba;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.CatTipoPruebaRepository;

/**
 * Created by Miguel Salinas on 14/12/2020.
 */
@Service
public class CatTipoPruebaService {

	@Autowired
	private CatTipoPruebaRepository repository;

	public CatTipoPrueba saveCatTipoPrueba(CatTipoPrueba catTipoPrueba) {
		return repository.save(catTipoPrueba);
	}

	public List<CatTipoPrueba> saveCatTipoPruebas(List<CatTipoPrueba> catTipoPruebaes) {
		return repository.saveAll(catTipoPruebaes);
	}

	public List<CatTipoPrueba> getCatTiposPruebas() {
		return repository.findAll();
	}

	public CatTipoPrueba getCatTipoPruebaById(Long id) {
		CatTipoPrueba CatTipoPrueba = repository.findById(id).orElse(null);
		if (CatTipoPrueba == null) throw new NotEntityFoundException(CatTipoPrueba.class.getSimpleName(), "Id", id.toString());
		return CatTipoPrueba;
	}

	public List<CatTipoPrueba> getCatTipoPruebaByName(String name) {
		List<CatTipoPrueba> CatTipoPrueba = repository.findByNombre(name);
		if (CatTipoPrueba.size() <= 0) throw new NotEntityFoundException(CatTipoPrueba.class.getSimpleName(), "nombre", name);
		return CatTipoPrueba;
	}

	public void deleteCatTipoPrueba(Long id) {
		CatTipoPrueba oldCatTipoPrueba = repository.findById(id).orElse(null);		
		if (oldCatTipoPrueba == null) throw new NotEntityFoundException(CatTipoPrueba.class.getSimpleName(), "Id", id.toString());
		repository.deleteById(id);
	}

	public CatTipoPrueba updateCatTipoPrueba(CatTipoPrueba catTipoPrueba) {
		CatTipoPrueba oldCatTipoPrueba = repository.findById(catTipoPrueba.getId()).orElse(null);
		
		if (oldCatTipoPrueba == null) throw new NotEntityFoundException(CatTipoPrueba.class.getSimpleName(), "Id", catTipoPrueba.getId().toString());
		
		oldCatTipoPrueba.setNombre(catTipoPrueba.getNombre());
		oldCatTipoPrueba.setDescripcion(catTipoPrueba.getDescripcion());
		oldCatTipoPrueba.setActivo(catTipoPrueba.getActivo());
		return repository.save(oldCatTipoPrueba);
	}
}
