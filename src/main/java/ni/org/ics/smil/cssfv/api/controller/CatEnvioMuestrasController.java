package ni.org.ics.smil.cssfv.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatEnvioMuestras;
import ni.org.ics.smil.cssfv.api.service.CatEnvioMuestrasService;

@RestController
public class CatEnvioMuestrasController {
	
	@Autowired
	private CatEnvioMuestrasService service;
	
	@GetMapping("/catalogos/envio-muestras/activas")
    public List<CatEnvioMuestras> findAllCatEnvioMuestras() {
    	return service.getAllCatEnvioMuestras();
    }
}
