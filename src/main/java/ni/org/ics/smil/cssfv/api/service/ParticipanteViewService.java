package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.ParticipanteView;
import ni.org.ics.smil.cssfv.api.repository.ParticipanteViewRepository;

@Service
public class ParticipanteViewService {
	
	@Autowired
	private ParticipanteViewRepository repository;
	
	public List<ParticipanteView> getParticipantes() {
        return repository.findAll();
    }

    public ParticipanteView getParticipanteByCodigo(Long codigo) {
        return repository.findById(codigo).orElse(null);
    }


}
