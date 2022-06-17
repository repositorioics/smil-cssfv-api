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

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatAnioEstudio;
import ni.org.ics.smil.cssfv.api.service.CatAnioEstudioService;

@RestController
public class CatAnioEstudioController {
	
	@Autowired
	private CatAnioEstudioService service;

	@PostMapping("/catalogos/catAnioEstudio")
	public CatAnioEstudio addCatAnioEstudio(@RequestBody CatAnioEstudio catAnioEstudio) {
		return service.saveCatAnioEstudio(catAnioEstudio);
	}

    @GetMapping("/catalogos/catAnioEstudio/{id}")
    public CatAnioEstudio findCatAnioEstudioById(@PathVariable Long id) {
        return service.getCatAnioEstudioById(id);
    }
    
    @GetMapping("/catalogos/catAnioEstudio/ultimo-anio-estudio")
    public CatAnioEstudio findLastAnioEstudio() {
        return service.getLastAnioEstudio();
    }

    @GetMapping("/catalogos/catAnioEstudio")
    public List<CatAnioEstudio> findCatAnioEstudio(@RequestParam(required = false) String filter) {
    	return service.getCatAnioEstudio();
    	/*if (filter == null) return service.getCatAnioEstudio();
    	else return service.getCatAnioEstudioByName(filter);*/
    }

    @PutMapping("/catalogos/catAnioEstudio")
    public CatAnioEstudio updateCatMismoEpfebril(@RequestBody CatAnioEstudio catAnioEstudio) {
        return service.updateCatAnioEstudio(catAnioEstudio);
    }

    @DeleteMapping("/catalogos/catAnioEstudio/{id}")
    public ResponseEntity<Void> deleteCatAnioEstudio(@PathVariable Long id) {
        service.deleteCatAnioEstudio(id);
        return ResponseEntity.noContent().build();
    }

}
