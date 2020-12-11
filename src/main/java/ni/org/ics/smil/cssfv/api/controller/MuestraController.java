package ni.org.ics.smil.cssfv.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ni.org.ics.smil.cssfv.api.entity.Muestra;
import ni.org.ics.smil.cssfv.api.entity.catalogos.CatCambioCategoria;
import ni.org.ics.smil.cssfv.api.service.MuestraService;

@RestController
public class MuestraController {

	@Autowired
	private MuestraService service;
	
	@GetMapping("/muestras")
	public List<Muestra> getMuestras() {
		return service.getMuestras();
	}
	
	@PostMapping("/muestras")
    public Muestra addMuestra(@RequestBody Muestra muestra) {
        return service.saveMuestra(muestra);
    }
	
}
