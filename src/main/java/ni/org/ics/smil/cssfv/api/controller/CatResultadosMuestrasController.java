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

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatResultadosMuestras;
import ni.org.ics.smil.cssfv.api.service.CatResultadosMuestrasService;

@RestController
public class CatResultadosMuestrasController {

	@Autowired
	private CatResultadosMuestrasService service;

	@PostMapping("/catalogos/catResultadosMuestras")
	public CatResultadosMuestras addCatResultadosMuestra(@RequestBody CatResultadosMuestras catResultadosMuestras) {
		return service.saveCatResultadosMuestras(catResultadosMuestras);
	}

    @GetMapping("/catalogos/catResultadosMuestras/{id}")
    public CatResultadosMuestras findCatResultadosMuestraById(@PathVariable Long id) {
        return service.getCatResultadosMuestrasById(id);
    }
    
    @GetMapping("/catalogos/catResultadosMuestras/ByIdTipoPrueba")
    public List<CatResultadosMuestras> findAllCatResultadosMuestraByIdTipoPrueba(@RequestParam Long id) {
        return service.getAllResultadosByIdTipoPrueba(id);
    }

    @GetMapping("/catalogos/catResultadosMuestras")
    public List<CatResultadosMuestras> findCatResultadosMuestras(@RequestParam(required = false) String filter) {
    	if (filter == null) return service.getCatResultadosMuestras();
    	else return service.getCatResultadosMuestrasByDescripcion(filter);
    }

    @PutMapping("/catalogos/catResultadosMuestras")
    public CatResultadosMuestras updateCatResultadosMuestras(@RequestBody CatResultadosMuestras catResultadosMuestras) {
        return service.updateCatResultadosMuestras(catResultadosMuestras);
    }

    @DeleteMapping("/catalogos/catResultadosMuestras/{id}")
    public ResponseEntity<Void> deleteCatResultadosMuestras(@PathVariable Long id) {
        service.deleteCatResultadosMuestras(id);
        return ResponseEntity.noContent().build();
    }
}
