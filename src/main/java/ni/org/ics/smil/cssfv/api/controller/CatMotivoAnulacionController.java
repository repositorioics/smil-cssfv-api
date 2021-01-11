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

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatMotivoAnulacion;
import ni.org.ics.smil.cssfv.api.service.CatMotivoAnulacionService;

@RestController
public class CatMotivoAnulacionController {

	@Autowired
	private CatMotivoAnulacionService service;

	@PostMapping("/catalogos/motivos-anulaciones")
	public CatMotivoAnulacion addCatMotivoAnulacion(@RequestBody CatMotivoAnulacion catMotivoAnulacion) {
		return service.saveCatMotivoAnulacion(catMotivoAnulacion);
	}

    @GetMapping("/catalogos/motivos-anulaciones/{id}")
    public CatMotivoAnulacion findCatMotivoAnulacionById(@PathVariable Long id) {
        return service.getCatMotivoAnulacionById(id);
    }

    @GetMapping("/catalogos/motivos-anulaciones")
    public List<CatMotivoAnulacion> findCatMotivoAnulacion(@RequestParam(required = false) String filter) {
    	if (filter == null) return service.getCatMotivosAnulaciones();
    	else return service.getCatMotivoAnulacionByName(filter);
    }

    @PutMapping("/catalogos/motivos-anulaciones")
    public CatMotivoAnulacion updateCatMotivoAnulacion(@RequestBody CatMotivoAnulacion catMotivoAnulacion) {
        return service.updateCatMotivoAnulacion(catMotivoAnulacion);
    }

    @DeleteMapping("/catalogos/motivos-anulaciones/{id}")
    public ResponseEntity<Void> deleteCatMotivoAnulacion(@PathVariable Long id) {
        service.deleteCatMotivoAnulacion(id);
        return ResponseEntity.noContent().build();
    }
}
