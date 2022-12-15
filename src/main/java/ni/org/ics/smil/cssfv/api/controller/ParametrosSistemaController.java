package ni.org.ics.smil.cssfv.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ni.org.ics.smil.cssfv.api.entity.security.ParametrosSistema;
import ni.org.ics.smil.cssfv.api.service.ParametrosSistemaService;


@RestController
public class ParametrosSistemaController {

	@Autowired
	private ParametrosSistemaService parametrosSistemaService;
	
	@GetMapping("/seguridad/parametros/sistema/{nombre}")
    public ParametrosSistema getPerfilById(@PathVariable String nombre) {
        return parametrosSistemaService.getParametrosSistemaByName(nombre);
    }
}
