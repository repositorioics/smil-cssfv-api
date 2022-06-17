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

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatTubo;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatVisitas;
import ni.org.ics.smil.cssfv.api.service.CatVisitasService;

@RestController
public class CatVisitasController {
	@Autowired
	private CatVisitasService service;

	@PostMapping("/catalogos/catVisitas")
	public CatVisitas addCatVisitas(@RequestBody CatVisitas catVisitas) {
		return service.saveCatVisitas(catVisitas);
	}

    @GetMapping("/catalogos/catVisitas/{id}")
    public CatVisitas findCatVisitaById(@PathVariable Long id) {
        return service.getVisitasById(id);
    }

    @GetMapping("/catalogos/catVisitas")
    public List<CatVisitas> findCatCategoria(@RequestParam(required = false) String filter) {
    	if (filter == null) return service.getCatVisitas();
    	else return service.getCatCategoriaByName(filter);
    }
    
    @GetMapping("/catalogos/catVisitas/activas")
    public List<CatVisitas> findAllVisitas() {
    	return service.getAllCatVisitas();
    }

    @PutMapping("/catalogos/catVisitas")
    public CatVisitas updateCatCatVisita(@RequestBody CatVisitas catVisitas) {
        return service.updateCatVisita(catVisitas);
    }

    @DeleteMapping("/catalogos/catVisitas/{id}")
    public ResponseEntity<Void> deleteCatCategoria(@PathVariable Long id) {
        service.deleteCatVisitas(id);
        return ResponseEntity.noContent().build();
    }

}
