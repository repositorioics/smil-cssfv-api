package ni.org.ics.smil.cssfv.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatConsulta;
import ni.org.ics.smil.cssfv.api.service.CatConsultaService;

@RestController
public class CatConsultaController {

	@Autowired
	private CatConsultaService service;

	@PostMapping("/catalogos/consultas")
	public CatConsulta addCatConsulta(@RequestBody CatConsulta catConsulta) {
		return service.saveCatConsulta(catConsulta);
	}

    @GetMapping("/catalogos/consultas/{id}")
    public CatConsulta findCatConsultaById(@PathVariable Long id) {
        return service.getCatConsultaById(id);
    }

    @GetMapping("/catalogos/consultas")
    public List<CatConsulta> findCatConsulta(@RequestParam(required = false) String filter) {
    	if (filter == null) return service.getCatConsultas();
    	else return service.getCatConsultaByName(filter);
    }
    
    @GetMapping("/catalogos/consultas/activas")
    public List<CatConsulta> findAllConsultas() {
    	return service.getAllConsultas();
    }

    @PutMapping("/catalogos/consultas")
    public CatConsulta updateCatConsulta(@RequestBody CatConsulta catConsulta) {
        return service.updateCatConsulta(catConsulta);
    }

    @DeleteMapping("/catalogos/consultas/{id}")
    public ResponseEntity<Void> deleteCatConsulta(@PathVariable Long id) {
        service.deleteCatConsulta(id);
        return ResponseEntity.noContent().build();
    }
}
