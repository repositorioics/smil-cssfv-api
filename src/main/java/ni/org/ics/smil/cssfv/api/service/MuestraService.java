package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.Muestra;
import ni.org.ics.smil.cssfv.api.entity.catalogos.CatCambioCategoria;
import ni.org.ics.smil.cssfv.api.repository.MuestraRepository;

@Service
public class MuestraService {

	@Autowired
	private MuestraRepository repository;
	
	public List<Muestra> getMuestras() {
        return repository.findAll();
    }
	
	public Muestra saveMuestra(Muestra muestra) {
		return repository.save(muestra);
	}
}
