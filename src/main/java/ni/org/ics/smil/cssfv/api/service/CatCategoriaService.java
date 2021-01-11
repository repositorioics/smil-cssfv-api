package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatCategoria;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.CatCategoriaRepository;

/**
 * Created by Miguel Salinas on 10/12/2020.
 */
@Service
public class CatCategoriaService {

	@Autowired
	private CatCategoriaRepository repository;

	public CatCategoria saveCatCategoria(CatCategoria CatCategoria) {
		return repository.save(CatCategoria);
	}

	public List<CatCategoria> saveCatCategorias(List<CatCategoria> categorias) {
		return repository.saveAll(categorias);
	}

	public List<CatCategoria> getCatCategorias() {
		return repository.findAll();
	}

	public CatCategoria getCatCategoriaById(Long id) {
		CatCategoria CatCategoria = repository.findById(id).orElse(null);
		if (CatCategoria == null) throw new NotEntityFoundException(CatCategoria.class.getSimpleName(), "Id", id.toString());
		return CatCategoria;
	}

	public List<CatCategoria> getCatCategoriaByName(String name) {
		List<CatCategoria> CatCategoria = repository.findByNombre(name);
		if (CatCategoria.size() <= 0) throw new NotEntityFoundException(CatCategoria.class.getSimpleName(), "nombre", name);
		return CatCategoria;
	}

	public void deleteCatCategoria(Long id) {
		CatCategoria oldCatCategoria = repository.findById(id).orElse(null);		
		if (oldCatCategoria == null) throw new NotEntityFoundException(CatCategoria.class.getSimpleName(), "Id", id.toString());
		repository.deleteById(id);
	}

	public CatCategoria updateCatCategoria(CatCategoria CatCategoria) {
		CatCategoria oldCatCategoria = repository.findById(CatCategoria.getId()).orElse(null);
		
		if (oldCatCategoria == null) throw new NotEntityFoundException(CatCategoria.class.getSimpleName(), "Id", CatCategoria.getId().toString());
		
		oldCatCategoria.setNombre(CatCategoria.getNombre());
		oldCatCategoria.setDescripcion(CatCategoria.getDescripcion());
		oldCatCategoria.setActivo(CatCategoria.getActivo());
		return repository.save(oldCatCategoria);
	}
}
