package ni.org.ics.smil.cssfv.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatMismoEpfebril;
import ni.org.ics.smil.cssfv.api.service.CatMismoEpfebrilService;

@RestController
public class CatMismoEpfebrilController {

	@Autowired
	private CatMismoEpfebrilService service;

	@PostMapping("/catalogos/catMismoEpfebril")
	public CatMismoEpfebril addCatCategoria(@RequestBody CatMismoEpfebril catMismoEpfebril) {
		return service.saveCatMismoEpfebril(catMismoEpfebril);
	}

    @GetMapping("/catalogos/catMismoEpfebril/{id}")
    public CatMismoEpfebril findCatCatMismoEpfebrilById(@PathVariable Long id) {
        return service.getCatMismoEpfebrilById(id);
    }

    @GetMapping("/catalogos/catMismoEpfebril")
    public List<CatMismoEpfebril> findCatMismoEpfebril(@RequestParam(required = false) String filter) {
    	if (filter == null) return service.getCatMismoEpfebril();
    	else return service.getCatMismoEpfebrilByName(filter);
    }

    @PutMapping("/catalogos/catMismoEpfebril")
    public CatMismoEpfebril updateCatMismoEpfebril(@RequestBody CatMismoEpfebril catMismoEpfebril) {
        return service.updateCatMismoEpfebril(catMismoEpfebril);
    }

    @DeleteMapping("/catalogos/catMismoEpfebril/{id}")
    public ResponseEntity<Void> deleteCatMismoEpfebril(@PathVariable Long id) {
        service.deleteCatMismoEpfebril(id);
        return ResponseEntity.noContent().build();
    }
}
