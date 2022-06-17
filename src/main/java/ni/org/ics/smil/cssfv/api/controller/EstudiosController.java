package ni.org.ics.smil.cssfv.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ni.org.ics.smil.cssfv.api.entity.view.EstudiosView;
import ni.org.ics.smil.cssfv.api.service.EstudiosViewService;

@RestController
public class EstudiosController {
	@Autowired
	private EstudiosViewService service;
	
	@GetMapping("/estudios")
	public List<EstudiosView> getEstudios() {
		return service.getEstudios();
	}

	@GetMapping("/estudios/{codigo}")
	public EstudiosView getEstudioByCodigo(@PathVariable Long codigo) {
		return service.getEstudiosByCodigo(codigo);
	}
}
