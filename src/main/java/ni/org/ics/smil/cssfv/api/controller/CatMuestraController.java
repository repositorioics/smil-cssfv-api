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

import ni.org.ics.smil.cssfv.api.entity.catalogos.CatMuestra;
import ni.org.ics.smil.cssfv.api.service.CatMuestraService;

@RestController
public class CatMuestraController {

	@Autowired
	private CatMuestraService service;

	@PostMapping("/catalogos/muestras")
	public CatMuestra addCatMuestra(@RequestBody CatMuestra catMuestra) {
		return service.saveCatMuestra(catMuestra);
	}

    @GetMapping("/catalogos/muestras/{id}")
    public CatMuestra findCatMuestraById(@PathVariable Long id) {
        return service.getCatMuestraById(id);
    }

    @GetMapping("/catalogos/muestras")
    public List<CatMuestra> findCatMuestra(@RequestParam(required = false) String filter) {
    	if (filter == null) return service.getCatMuestras();
    	else return service.getCatMuestraByName(filter);
    }

    @PutMapping("/catalogos/muestras")
    public CatMuestra updateCatMuestra(@RequestBody CatMuestra catMuestra) {
        return service.updateCatMuestra(catMuestra);
    }

    @DeleteMapping("/catalogos/muestras/{id}")
    public ResponseEntity<Void> deleteCatMuestra(@PathVariable Long id) {
        service.deleteCatMuestra(id);
        return ResponseEntity.noContent().build();
    }
}
