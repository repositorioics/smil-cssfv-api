package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatMotivoAnulacion;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.CatMotivoAnulacionRepository;

/**
 * Created by Miguel Salinas on 14/12/2020.
 */
@Service
public class CatMotivoAnulacionService {

	@Autowired
	private CatMotivoAnulacionRepository repository;

	public CatMotivoAnulacion saveCatMotivoAnulacion(CatMotivoAnulacion catMotivoAnulacion) {
		return repository.save(catMotivoAnulacion);
	}

	public List<CatMotivoAnulacion> saveCatMotivoAnulacions(List<CatMotivoAnulacion> catMotivoAnulaciones) {
		return repository.saveAll(catMotivoAnulaciones);
	}

	public List<CatMotivoAnulacion> getCatMotivosAnulaciones() {
		return repository.findAll();
	}

	public CatMotivoAnulacion getCatMotivoAnulacionById(Long id) {
		CatMotivoAnulacion CatMotivoAnulacion = repository.findById(id).orElse(null);
		if (CatMotivoAnulacion == null) throw new NotEntityFoundException(CatMotivoAnulacion.class.getSimpleName(), "Id", id.toString());
		return CatMotivoAnulacion;
	}

	public List<CatMotivoAnulacion> getCatMotivoAnulacionByName(String name) {
		List<CatMotivoAnulacion> CatMotivoAnulacion = repository.findByNombre(name);
		if (CatMotivoAnulacion.size() <= 0) throw new NotEntityFoundException(CatMotivoAnulacion.class.getSimpleName(), "nombre", name);
		return CatMotivoAnulacion;
	}

	public void deleteCatMotivoAnulacion(Long id) {
		CatMotivoAnulacion oldCatMotivoAnulacion = repository.findById(id).orElse(null);		
		if (oldCatMotivoAnulacion == null) throw new NotEntityFoundException(CatMotivoAnulacion.class.getSimpleName(), "Id", id.toString());
		repository.deleteById(id);
	}

	public CatMotivoAnulacion updateCatMotivoAnulacion(CatMotivoAnulacion catMotivoAnulacion) {
		CatMotivoAnulacion oldCatMotivoAnulacion = repository.findById(catMotivoAnulacion.getId()).orElse(null);
		
		if (oldCatMotivoAnulacion == null) throw new NotEntityFoundException(CatMotivoAnulacion.class.getSimpleName(), "Id", catMotivoAnulacion.getId().toString());
		
		oldCatMotivoAnulacion.setNombre(catMotivoAnulacion.getNombre());
		oldCatMotivoAnulacion.setDescripcion(catMotivoAnulacion.getDescripcion());
		oldCatMotivoAnulacion.setActivo(catMotivoAnulacion.getActivo());
		return repository.save(oldCatMotivoAnulacion);
	}
}
