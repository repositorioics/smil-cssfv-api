package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatConsulta;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.CatConsultaRepository;

/**
 * Created by Miguel Salinas on 14/12/2020.
 */
@Service
public class CatConsultaService {

	@Autowired
	private CatConsultaRepository repository;

	public CatConsulta saveCatConsulta(CatConsulta catConsulta) {
		return repository.save(catConsulta);
	}

	public List<CatConsulta> saveCatConsultas(List<CatConsulta> catConsultaes) {
		return repository.saveAll(catConsultaes);
	}

	public List<CatConsulta> getCatConsultas() {
		return repository.findAll();
	}

	public CatConsulta getCatConsultaById(Long id) {
		CatConsulta CatConsulta = repository.findById(id).orElse(null);
		if (CatConsulta == null) throw new NotEntityFoundException(CatConsulta.class.getSimpleName(), "Id", id.toString());
		return CatConsulta;
	}

	public List<CatConsulta> getCatConsultaByName(String name) {
		List<CatConsulta> CatConsulta = repository.findByConsulta(name);
		if (CatConsulta.size() <= 0) throw new NotEntityFoundException(CatConsulta.class.getSimpleName(), "Consulta", name);
		return CatConsulta;
	}

	public void deleteCatConsulta(Long id) {
		CatConsulta oldCatConsulta = repository.findById(id).orElse(null);		
		if (oldCatConsulta == null) throw new NotEntityFoundException(CatConsulta.class.getSimpleName(), "Id", id.toString());
		repository.deleteById(id);
	}

	public CatConsulta updateCatConsulta(CatConsulta catConsulta) {
		CatConsulta oldCatConsulta = repository.findById(catConsulta.getId()).orElse(null);
		
		if (oldCatConsulta == null) throw new NotEntityFoundException(CatConsulta.class.getSimpleName(), "Id", catConsulta.getId().toString());
		
		oldCatConsulta.setConsulta(catConsulta.getConsulta());
		oldCatConsulta.setDescripcion(catConsulta.getDescripcion());
		oldCatConsulta.setActivo(catConsulta.getActivo());
		return repository.save(oldCatConsulta);
	}
}
