package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatClasificacion;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.CatClasificacionRepository;

/**
 * Created by Miguel Salinas on 14/12/2020.
 */
@Service
public class CatClasificacionService {

	@Autowired
	private CatClasificacionRepository repository;

	public CatClasificacion saveCatClasificacion(CatClasificacion catClasificacion) {
		return repository.save(catClasificacion);
	}

	public List<CatClasificacion> saveCatClasificacions(List<CatClasificacion> catClasificaciones) {
		return repository.saveAll(catClasificaciones);
	}

	public List<CatClasificacion> getCatClasificaciones() {
		return repository.findAll();
	}

	public CatClasificacion getCatClasificacionById(Long id) {
		CatClasificacion CatClasificacion = repository.findById(id).orElse(null);
		if (CatClasificacion == null) throw new NotEntityFoundException(CatClasificacion.class.getSimpleName(), "Id", id.toString());
		return CatClasificacion;
	}

	public List<CatClasificacion> getCatClasificacionByName(String name) {
		List<CatClasificacion> CatClasificacion = repository.findByNombre(name);
		if (CatClasificacion.size() <= 0) throw new NotEntityFoundException(CatClasificacion.class.getSimpleName(), "nombre", name);
		return CatClasificacion;
	}

	public void deleteCatClasificacion(Long id) {
		CatClasificacion oldCatClasificacion = repository.findById(id).orElse(null);		
		if (oldCatClasificacion == null) throw new NotEntityFoundException(CatClasificacion.class.getSimpleName(), "Id", id.toString());
		repository.deleteById(id);
	}

	public CatClasificacion updateCatClasificacion(CatClasificacion catClasificacion) {
		CatClasificacion oldCatClasificacion = repository.findById(catClasificacion.getId()).orElse(null);
		
		if (oldCatClasificacion == null) throw new NotEntityFoundException(CatClasificacion.class.getSimpleName(), "Id", catClasificacion.getId().toString());
		
		oldCatClasificacion.setNombre(catClasificacion.getNombre());
		oldCatClasificacion.setDescripcion(catClasificacion.getDescripcion());
		oldCatClasificacion.setActivo(catClasificacion.getActivo());
		return repository.save(oldCatClasificacion);
	}
}
