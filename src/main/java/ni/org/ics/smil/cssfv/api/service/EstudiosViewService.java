package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.view.EstudiosView;
import ni.org.ics.smil.cssfv.api.repository.EstudiosViewRepository;

@Service
public class EstudiosViewService {
	@Autowired
	private EstudiosViewRepository repository;
	
	public List<EstudiosView> getEstudios() {
        return repository.findAll();
    }

    public EstudiosView getEstudiosByCodigo(Long codigo) {
        return repository.findById(codigo).orElse(null);
    }

}
