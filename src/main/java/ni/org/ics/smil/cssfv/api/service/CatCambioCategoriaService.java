package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.catalogos.CatCambioCategoria;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.CatCambioCategoriaRepository;

/**
 * Created by Miguel Salinas on 26/11/2020.
 */
@Service
public class CatCambioCategoriaService {

	@Autowired
	private CatCambioCategoriaRepository repository;

	public CatCambioCategoria saveCatCambioCategoria(CatCambioCategoria catCambioCategoria) {
		return repository.save(catCambioCategoria);
	}

	public List<CatCambioCategoria> saveCatCambioCategorias(List<CatCambioCategoria> cambioCategorias) {
		return repository.saveAll(cambioCategorias);
	}

	public List<CatCambioCategoria> getCatCambioCategorias() {
		return repository.findAll();
	}

	public CatCambioCategoria getCatCambioCategoriaById(Long id) {
		CatCambioCategoria catCambioCategoria = repository.findById(id).orElse(null);
		if (catCambioCategoria == null) throw new NotEntityFoundException(CatCambioCategoria.class.getSimpleName(), "Id", id.toString());
		return catCambioCategoria;
	}

	public List<CatCambioCategoria> getCatCambioCategoriaByName(String name) {
		List<CatCambioCategoria> catCambioCategoria = repository.findByCambioCat(name);
		if (catCambioCategoria.size() <= 0) throw new NotEntityFoundException(CatCambioCategoria.class.getSimpleName(), "Cambiocat", name);
		return catCambioCategoria;
	}

	public void deleteCatCambioCategoria(Long id) {
		CatCambioCategoria oldCatCambioCategoria = repository.findById(id).orElse(null);		
		if (oldCatCambioCategoria == null) throw new NotEntityFoundException(CatCambioCategoria.class.getSimpleName(), "Id", id.toString());
		repository.deleteById(id);
	}

	public CatCambioCategoria updateCatCambioCategoria(CatCambioCategoria catCambioCategoria) {
		CatCambioCategoria oldCatCambioCategoria = repository.findById(catCambioCategoria.getId()).orElse(null);
		
		if (oldCatCambioCategoria == null) throw new NotEntityFoundException(CatCambioCategoria.class.getSimpleName(), "Id", catCambioCategoria.getId().toString());
		
		oldCatCambioCategoria.setCambioCat(catCambioCategoria.getCambioCat());
		oldCatCambioCategoria.setDescripcion(catCambioCategoria.getDescripcion());
		oldCatCambioCategoria.setActivo(catCambioCategoria.getActivo());
		return repository.save(oldCatCambioCategoria);
	}
}
