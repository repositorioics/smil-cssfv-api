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

import ni.org.ics.smil.cssfv.api.entity.catalogos.CatCategoria;
import ni.org.ics.smil.cssfv.api.service.CatCategoriaService;

@RestController
public class CatCategoriaController {

	@Autowired
	private CatCategoriaService service;

	@PostMapping("/catalogos/categorias")
	public CatCategoria addCatCategoria(@RequestBody CatCategoria CatCategoria) {
		return service.saveCatCategoria(CatCategoria);
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

    @PutMapping("/catalogos/categorias")
    public CatCategoria updateCatCategoria(@RequestBody CatCategoria CatCategoria) {
        return service.updateCatCategoria(CatCategoria);
    }

    @DeleteMapping("/catalogos/categorias/{id}")
    public ResponseEntity<Void> deleteCatCategoria(@PathVariable Long id) {
        service.deleteCatCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
