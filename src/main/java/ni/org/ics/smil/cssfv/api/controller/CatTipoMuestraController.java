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

import ni.org.ics.smil.cssfv.api.entity.catalogos.CatTipoMuestra;
import ni.org.ics.smil.cssfv.api.service.CatTipoMuestraService;

@RestController
public class CatTipoMuestraController {

	@Autowired
	private CatTipoMuestraService service;

	@PostMapping("/catalogos/tipos-muestras")
	public CatTipoMuestra addCatTipoMuestra(@RequestBody CatTipoMuestra catTipoMuestra) {
		return service.saveCatTipoMuestra(catTipoMuestra);
	}

    @GetMapping("/catalogos/tipos-muestras/{id}")
    public CatTipoMuestra findCatTipoMuestraById(@PathVariable Long id) {
        return service.getCatTipoMuestraById(id);
    }

    @GetMapping("/catalogos/tipos-muestras")
    public List<CatTipoMuestra> findCatTipoMuestra(@RequestParam(required = false) String filter) {
    	if (filter == null) return service.getCatTiposMuestras();
    	else return service.getCatTipoMuestraByName(filter);
    }

    @PutMapping("/catalogos/tipos-muestras")
    public CatTipoMuestra updateCatTipoMuestra(@RequestBody CatTipoMuestra catTipoMuestra) {
        return service.updateCatTipoMuestra(catTipoMuestra);
    }

    @DeleteMapping("/catalogos/tipos-muestras/{id}")
    public ResponseEntity<Void> deleteCatTipoMuestra(@PathVariable Long id) {
        service.deleteCatTipoMuestra(id);
        return ResponseEntity.noContent().build();
    }
}
