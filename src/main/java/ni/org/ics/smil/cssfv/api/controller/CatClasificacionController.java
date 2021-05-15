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

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatClasificacion;
import ni.org.ics.smil.cssfv.api.service.CatClasificacionService;

@RestController
public class CatClasificacionController {

	@Autowired
	private CatClasificacionService service;

	@PostMapping("/catalogos/clasificaciones")
	public CatClasificacion addCatClasificacion(@RequestBody CatClasificacion catClasificacion) {
		return service.saveCatClasificacion(catClasificacion);
	}

    @GetMapping("/catalogos/clasificaciones/{id}")
    public CatClasificacion findCatClasificacionById(@PathVariable Long id) {
        return service.getCatClasificacionById(id);
    }

    @GetMapping("/catalogos/clasificaciones")
    public List<CatClasificacion> findCatClasificacion(@RequestParam(required = false) String filter) {
    	if (filter == null) return service.getCatClasificaciones();
    	else return service.getCatClasificacionByName(filter);
    }
    
    @GetMapping("/catalogos/clasificaciones/activas")
    public List<CatClasificacion> findAllClasificaciones() {
    	return service.getAllClasificaciones();
    }

    @PutMapping("/catalogos/clasificaciones")
    public CatClasificacion updateCatClasificacion(@RequestBody CatClasificacion catClasificacion) {
        return service.updateCatClasificacion(catClasificacion);
    }

    @DeleteMapping("/catalogos/clasificaciones/{id}")
    public ResponseEntity<Void> deleteCatClasificacion(@PathVariable Long id) {
        service.deleteCatClasificacion(id);
        return ResponseEntity.noContent().build();
    }
}
