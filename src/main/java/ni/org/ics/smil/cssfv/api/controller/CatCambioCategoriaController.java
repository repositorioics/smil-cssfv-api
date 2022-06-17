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

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatCambioCategoria;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatCategoria;
import ni.org.ics.smil.cssfv.api.service.CatCambioCategoriaService;

@RestController
public class CatCambioCategoriaController {

	@Autowired
	private CatCambioCategoriaService service;

	@PostMapping("/catalogos/cambio-categorias")
	public CatCambioCategoria addCatCambioCategoria(@RequestBody CatCambioCategoria catCambioCategoria) {
		return service.saveCatCambioCategoria(catCambioCategoria);
	}

    @GetMapping("/catalogos/cambio-categorias/{id}")
    public CatCambioCategoria findCatCambioCategoriaById(@PathVariable Long id) {
        return service.getCatCambioCategoriaById(id);
    }

    @GetMapping("/catalogos/cambio-categorias")
    public List<CatCambioCategoria> findCatCambioCategoria(@RequestParam(required = false) String filter) {
    	if (filter == null) return service.getCatCambioCategorias();
    	else return service.getCatCambioCategoriaByName(filter);
    }
    
    @GetMapping("/catalogos/cambio-categorias/activas")
    public List<CatCambioCategoria> findAllCambioCategorias() {
    	return service.getAllCambioCategorias();
    }

    @PutMapping("/catalogos/cambio-categorias")
    public CatCambioCategoria updateCatCambioCategoria(@RequestBody CatCambioCategoria catCambioCategoria) {
        return service.updateCatCambioCategoria(catCambioCategoria);
    }

    @DeleteMapping("/catalogos/cambio-categorias/{id}")
    public ResponseEntity<Void> deleteCatCambioCategoria(@PathVariable Long id) {
        service.deleteCatCambioCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
