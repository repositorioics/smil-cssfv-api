package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatResultadosMuestras;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.CatResultadosMuestrasRepository;

/**
 * Created by Santiago Carballo on 20/12/2020.
 */
@Service
public class CatResultadosMuestrasService {
	@Autowired
	private CatResultadosMuestrasRepository repository;

	public CatResultadosMuestras saveCatResultadosMuestras(CatResultadosMuestras CatMismoEpfebril) {
		return repository.save(CatMismoEpfebril);
	}

	public List<CatResultadosMuestras> saveCatResultadosMuestras(List<CatResultadosMuestras> CatMismoEpfebril) {
		return repository.saveAll(CatMismoEpfebril);
	}

	public List<CatResultadosMuestras> getCatResultadosMuestras() {
		return repository.findAll();
	}

	public CatResultadosMuestras getCatResultadosMuestrasById(Long id) {
		CatResultadosMuestras CatResultadosMuestras = repository.findById(id).orElse(null);
		if (CatResultadosMuestras == null) throw new NotEntityFoundException(CatResultadosMuestras.class.getSimpleName(), "Id", id.toString());
		return CatResultadosMuestras;
	}
	
	public List<CatResultadosMuestras> getAllResultadosByIdTipoPrueba(Long id) {
		return repository.findByIdCatTipoPruebaId(id);
	}

	public List<CatResultadosMuestras> getCatResultadosMuestrasByDescripcion(String descripcion) {
		List<CatResultadosMuestras> CatResultadosMuestras = repository.findByDescripcion(descripcion);
		if (CatResultadosMuestras.size() <= 0) throw new NotEntityFoundException(CatResultadosMuestras.class.getSimpleName(), "descripcion", descripcion);
		return CatResultadosMuestras;
	}

	public void deleteCatResultadosMuestras(Long id) {
		CatResultadosMuestras oldCatResultadosMuestras = repository.findById(id).orElse(null);		
		if (oldCatResultadosMuestras == null) throw new NotEntityFoundException(CatResultadosMuestras.class.getSimpleName(), "Id", id.toString());
		repository.deleteById(id);
	}

	public CatResultadosMuestras updateCatResultadosMuestras(CatResultadosMuestras catResultadosMuestras) {
		CatResultadosMuestras oldCatResultadosMuestras = repository.findById(catResultadosMuestras.getId()).orElse(null);
		
		if (oldCatResultadosMuestras == null) throw new NotEntityFoundException(CatResultadosMuestras.class.getSimpleName(), "Id", catResultadosMuestras.getId().toString());
		
		oldCatResultadosMuestras.setCodigo(catResultadosMuestras.getCodigo());
		oldCatResultadosMuestras.setDescripcion(catResultadosMuestras.getDescripcion());
		oldCatResultadosMuestras.setActivo(catResultadosMuestras.getActivo());
		oldCatResultadosMuestras.setIdCatTipoPrueba(catResultadosMuestras.getIdCatTipoPrueba());
		return repository.save(oldCatResultadosMuestras);
	}

}
