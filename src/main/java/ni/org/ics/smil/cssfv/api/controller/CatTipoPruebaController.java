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

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatTipoPrueba;
import ni.org.ics.smil.cssfv.api.service.CatTipoPruebaService;

@RestController
public class CatTipoPruebaController {

	@Autowired
	private CatTipoPruebaService service;

	@PostMapping("/catalogos/tipos-pruebas")
	public CatTipoPrueba addCatTipoPrueba(@RequestBody CatTipoPrueba catTipoPrueba) {
		return service.saveCatTipoPrueba(catTipoPrueba);
	}

    @GetMapping("/catalogos/tipos-pruebas/{id}")
    public CatTipoPrueba findCatTipoPruebaById(@PathVariable Long id) {
        return service.getCatTipoPruebaById(id);
    }

    @GetMapping("/catalogos/tipos-pruebas")
    public List<CatTipoPrueba> findCatTipoPrueba(@RequestParam(required = false) String filter) {
    	if (filter == null) return service.getCatTiposPruebas();
    	else return service.getCatTipoPruebaByName(filter);
    }

    @PutMapping("/catalogos/tipos-pruebas")
    public CatTipoPrueba updateCatTipoPrueba(@RequestBody CatTipoPrueba catTipoPrueba) {
        return service.updateCatTipoPrueba(catTipoPrueba);
    }

    @DeleteMapping("/catalogos/tipos-pruebas/{id}")
    public ResponseEntity<Void> deleteCatTipoPrueba(@PathVariable Long id) {
        service.deleteCatTipoPrueba(id);
        return ResponseEntity.noContent().build();
    }
}
