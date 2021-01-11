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
import ni.org.ics.smil.cssfv.api.service.CatTuboService;

@RestController
public class CatTuboController {

	@Autowired
	private CatTuboService service;

	@PostMapping("/catalogos/tubos")
	public CatTubo addCatTubo(@RequestBody CatTubo catTubo) {
		return service.saveCatTubo(catTubo);
	}

    @GetMapping("/catalogos/tubos/{id}")
    public CatTubo findCatTuboById(@PathVariable Long id) {
        return service.getCatTuboById(id);
    }

    @GetMapping("/catalogos/tubos")
    public List<CatTubo> findCatTubo(@RequestParam(required = false) String filter) {
    	if (filter == null) return service.getCatTubos();
    	else return service.getCatTuboByName(filter);
    }

    @PutMapping("/catalogos/tubos")
    public CatTubo updateCatTubo(@RequestBody CatTubo catTubo) {
        return service.updateCatTubo(catTubo);
    }

    @DeleteMapping("/catalogos/tubos/{id}")
    public ResponseEntity<Void> deleteCatTubo(@PathVariable Long id) {
        service.deleteCatTubo(id);
        return ResponseEntity.noContent().build();
    }
}
