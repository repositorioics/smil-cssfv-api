package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatRecepcion;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.CatRecepcionRepository;

@Service
public class CatRecepcionService {
	@Autowired
	private CatRecepcionRepository repository;
	
	public CatRecepcion saveCatRecepcion(CatRecepcion catRecepcion) {
		return repository.save(catRecepcion);
	}

	public List<CatRecepcion> saveCatRecepciones(List<CatRecepcion> catRecepcion) {
		return repository.saveAll(catRecepcion);
	}

	public List<CatRecepcion> getCatRecepcion() {
		return repository.findAll();
	}
	
	/*public List<CatRecepcionDTO> getCatRecepcionDTO() {
		return repositoryRecepcionDTO.getListFormatoRecepciones();
	}*/
	
	public List<CatRecepcion> getAllCatRecepcion() {
		return repository.findAllRecepcionesActivas();
	}

	public CatRecepcion getCatRecepcionById(Long id) {
		CatRecepcion catRecepcion = repository.findById(id).orElse(null);
		if (catRecepcion == null) throw new NotEntityFoundException(CatRecepcion.class.getSimpleName(), "Id", id.toString());
		return catRecepcion;
	}

	public List<CatRecepcion> getCatRecepcionByName(String estudio) {
		List<CatRecepcion> CatRecepcion = repository.findByEstudio(estudio);
		if (CatRecepcion.size() <= 0) throw new NotEntityFoundException(CatRecepcion.class.getSimpleName(), "estudio", estudio);
		return CatRecepcion;
	}

	public void deleteCatRecepcion(Long id) {
		CatRecepcion oldCatRecepcion = repository.findById(id).orElse(null);		
		if (oldCatRecepcion == null) throw new NotEntityFoundException(CatRecepcion.class.getSimpleName(), "Id", id.toString());
		repository.deleteById(id);
	}

	public CatRecepcion updateCatRecepcion(CatRecepcion catRecepcion) {
		CatRecepcion oldCatRecepcion = repository.findById(catRecepcion.getId()).orElse(null);
		
		if (oldCatRecepcion == null) throw new NotEntityFoundException(CatRecepcion.class.getSimpleName(), "Id", catRecepcion.getId().toString());
		
		oldCatRecepcion.setEstudio(catRecepcion.getEstudio());
		oldCatRecepcion.setCatTipoMuestraId(catRecepcion.getCatTipoMuestraId());
		oldCatRecepcion.setDescripcion(catRecepcion.getDescripcion());
		oldCatRecepcion.setCriteriosEvaluar(catRecepcion.getCriteriosEvaluar());
		oldCatRecepcion.setCadenaCaracteresCodigo(catRecepcion.getCadenaCaracteresCodigo());
		oldCatRecepcion.setDescripcionCadena(catRecepcion.getDescripcionCadena());
		oldCatRecepcion.setExpresionRegular(catRecepcion.getExpresionRegular());
		oldCatRecepcion.setActivo(catRecepcion.getActivo());
		return repository.save(catRecepcion);
	}
	
	public CatRecepcion getRecepcionByCodLab(String codLab) {
		return repository.findTypeReceipt(codLab);
	}
}
