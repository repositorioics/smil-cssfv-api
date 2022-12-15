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

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatClasificacionMuestra;
import ni.org.ics.smil.cssfv.api.service.CatClasificacionMuestraService;

@RestController
public class CatClasificacionMuestraController {
	
	@Autowired
	private CatClasificacionMuestraService service;
	
	@PostMapping("/catalogos/clasificaciones/muestras")
	public CatClasificacionMuestra addCatClasificacion(@RequestBody CatClasificacionMuestra catClasificacionMuestra) {
		return service.saveCatClasificacionMuestra(catClasificacionMuestra);
	}

    @GetMapping("/catalogos/clasificaciones/muestras/{id}")
    public CatClasificacionMuestra findCatClasificacionById(@PathVariable Long id) {
        return service.getCatClasificacionMuestraById(id);
    }

    @GetMapping("/catalogos/clasificaciones/muestras")
    public List<CatClasificacionMuestra> findCatClasificacion(@RequestParam(required = false) String filter) {
    	if (filter == null) return service.getCatClasificacionMuestra();
    	else return service.getCatClasificacionMuestraByName(filter);
    }
    
    @GetMapping("/catalogos/clasificaciones/muestras/activas")
    public List<CatClasificacionMuestra> findAllClasificaciones() {
    	return service.getAllCatClasificacionMuestraActivas();
    }

    @PutMapping("/catalogos/clasificaciones/muestras")
    public CatClasificacionMuestra updateCatClasificacion(@RequestBody CatClasificacionMuestra catClasificacion) {
        return service.updateCatClasificacionMuestra(catClasificacion);
    }

    @DeleteMapping("/catalogos/clasificaciones/muestras/{id}")
    public ResponseEntity<Void> deleteCatClasificacion(@PathVariable Long id) {
        service.deleteCatClasificacionMuestran(id);
        return ResponseEntity.noContent().build();
    }

}
