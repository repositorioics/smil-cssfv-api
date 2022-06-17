package ni.org.ics.smil.cssfv.api.controller;

import java.util.ArrayList;
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

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatRecepcion;
import ni.org.ics.smil.cssfv.api.security.model.MenuDTO;
import ni.org.ics.smil.cssfv.api.service.CatRecepcionService;

@RestController
public class CatRecepcionController {
	
	@Autowired
	private CatRecepcionService service;
	
	@PostMapping("/catalogos/recepcion")
	public CatRecepcion addCatRecepcion(@RequestBody CatRecepcion catRecepcion) {
		return service.saveCatRecepcion(catRecepcion);
	}

    @GetMapping("/catalogos/recepcion/{id}")
    public CatRecepcion findCatRecepcionById(@PathVariable Long id) {
        return service.getCatRecepcionById(id);
    }

    @GetMapping("/catalogos/recepcion")
    public List<CatRecepcion> findCatRecepcion(@RequestParam(required = false) String filter) {
    	if (filter == null) return service.getCatRecepcion();
    	else return service.getCatRecepcionByName(filter);
    }
    
    /*@GetMapping("/catalogos/recepcion/lista")
    public List<CatRecepcionDTO> findAllRecepcionesDTO() {
    	
    	return service.getCatRecepcionDTO();
    }*/
    
    @GetMapping("/catalogos/recepcion/activas")
    public List<CatRecepcion> findAllRecepciones() {
    	return service.getAllCatRecepcion();
    }

    @PutMapping("/catalogos/recepcion")
    public CatRecepcion updateCatRecepcion(@RequestBody CatRecepcion catRecepcion) {
        return service.updateCatRecepcion(catRecepcion);
    }

    @DeleteMapping("/catalogos/recepcion/{id}")
    public ResponseEntity<Void> deleteCatRecepcion(@PathVariable Long id) {
        service.deleteCatRecepcion(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/catalogos/recepcion/codLab")
    public CatRecepcion findCatRecepcionByCodLab(@RequestParam String codLab) {
    	return service.getRecepcionByCodLab(codLab);
    }
}
