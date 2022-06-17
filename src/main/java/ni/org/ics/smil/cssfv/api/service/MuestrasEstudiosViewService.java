package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.view.MuestrasEstudiosView;
import ni.org.ics.smil.cssfv.api.repository.MuestrasEstudiosViewRepository;

@Service
public class MuestrasEstudiosViewService {
	
	@Autowired
	private MuestrasEstudiosViewRepository muestrasEstudiosViewRepository;

	public List<MuestrasEstudiosView> getMuestrasPorEstudios() {
		return muestrasEstudiosViewRepository.findAll();
	}
}
