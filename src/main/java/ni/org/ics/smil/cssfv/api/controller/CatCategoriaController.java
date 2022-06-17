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

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatCategoria;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatClasificacion;
import ni.org.ics.smil.cssfv.api.service.CatCategoriaService;

@RestController
public class CatCategoriaController {

	@Autowired
	private CatCategoriaService service;

	@PostMapping("/catalogos/categorias")
	public CatCategoria addCatCategoria(@RequestBody CatCategoria catCategoria) {
		return service.saveCatCategoria(catCategoria);
	}

    @GetMapping("/catalogos/categorias/{id}")
    public CatCategoria findCatCategoriaById(@PathVariable Long id) {
        return service.getCatCategoriaById(id);
    }

    @GetMapping("/catalogos/categorias")
    public List<CatCategoria> findCatCategoria(@RequestParam(required = false) String filter) {
    	if (filter == null) return service.getCatCategorias();
    	else return service.getCatCategoriaByName(filter);
    }
    
    @GetMapping("/catalogos/categorias/activas")
    public List<CatCategoria> findAllCategorias() {
    	return service.getAllCategorias();
    }

    @PutMapping("/catalogos/categorias")
    public CatCategoria updateCatCategoria(@RequestBody CatCategoria catCategoria) {
        return service.updateCatCategoria(catCategoria);
    }

    @DeleteMapping("/catalogos/categorias/{id}")
    public ResponseEntity<Void> deleteCatCategoria(@PathVariable Long id) {
        service.deleteCatCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
