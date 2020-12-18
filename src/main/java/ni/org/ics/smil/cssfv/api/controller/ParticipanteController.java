package ni.org.ics.smil.cssfv.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ni.org.ics.smil.cssfv.api.entity.ParticipanteView;
import ni.org.ics.smil.cssfv.api.service.ParticipanteViewService;

@RestController
public class ParticipanteController {

	@Autowired
	private ParticipanteViewService service;
	
	@GetMapping("/participantes")
	public List<ParticipanteView> getParticipantes() {
		return service.getParticipantes();
	}

	@GetMapping("/participantes/{codigo}")
	public ParticipanteView getParticipanteByCodigo(@PathVariable Long codigo) {
		return service.getParticipanteByCodigo(codigo);
	}
}
