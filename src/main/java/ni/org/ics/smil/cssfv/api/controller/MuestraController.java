package ni.org.ics.smil.cssfv.api.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ni.org.ics.smil.cssfv.api.entity.Muestra;
import ni.org.ics.smil.cssfv.api.entity.MxBhc;
import ni.org.ics.smil.cssfv.api.entity.MxDengue;
import ni.org.ics.smil.cssfv.api.entity.MxInfluenza;
import ni.org.ics.smil.cssfv.api.entity.MxU01;
import ni.org.ics.smil.cssfv.api.service.MuestraService;
import ni.org.ics.smil.cssfv.api.util.DateUtil;

@RestController
public class MuestraController {

	@Autowired
	private MuestraService service;
	
	@GetMapping("/muestras")
	public List<Muestra> getMuestras() {
		return service.getMuestras();
	}
	
    @GetMapping("/muestras/{id}")
    public Muestra getMuestraById(@PathVariable Long id) {
        return service.getMuestraById(id);
    }
	
	@PostMapping("/muestras")
    public Muestra addMuestra(@RequestBody Muestra muestra) {
        return service.saveMuestra(muestra);
    }
	
	@PutMapping("/muestras")
    public Muestra updateMuestra(@RequestBody Muestra muestra) {
        return service.updateMuestra(muestra);
    }
	
	/*BHC*/
	@GetMapping("/muestras/bhc")
	public List<MxBhc> getMuestrasBhc() {
		return service.getMuestrasBhc();
	}
	
	@GetMapping("/muestras/bhc/{id}")
    public MxBhc getMuestraBhcById(@PathVariable Long id) {
        return service.getMuestraBhcById(id);
    }
	
	@GetMapping("/muestras/bhc/participantes/{codigoParticipante}")
    public List<MxBhc> getMuestraBhcByCodigoParticipante(@PathVariable Integer codigoParticipante) {
        return service.getMuestraBhcByCodigoParticipante(codigoParticipante);
    }
	
	@GetMapping("/muestras/bhc/fechas")
    public List<MxBhc> getMuestraBhcByRangeFechaToma(@RequestParam String strFecha1, @RequestParam String strFecha2) {
		Date dFecha1 = DateUtil.StringToDate(strFecha1 + " 00:00:00", "ddMMyyyy HH:mm:ss");
		Date dFecha2 = DateUtil.StringToDate(strFecha2 + " 23:59:59", "ddMMyyyy HH:mm:ss");
		return service.getMuestraBhcByRangeFechaToma(dFecha1, dFecha2);		
    }
	
	@GetMapping("/muestras/bhc/participantes/fechas")
    public List<MxBhc> getMuestraBhcByRangeFechaToma(@RequestParam Integer codigoParticipante, @RequestParam String strFecha1, @RequestParam String strFecha2) {
		Date dFecha1 = DateUtil.StringToDate(strFecha1 + " 00:00:00", "ddMMyyyy HH:mm:ss");
		Date dFecha2 = DateUtil.StringToDate(strFecha2 + " 23:59:59", "ddMMyyyy HH:mm:ss");
		return service.getMuestraBhcByCodigoParticipanteAndFechaToma(codigoParticipante, dFecha1, dFecha2);		
    }
	
	@PostMapping("/muestras/bhc")
    public MxBhc addMuestraBhc(@RequestBody MxBhc muestra) {
        return service.saveMuestraBhc(muestra);
    }
	
	@PutMapping("/muestras/bhc")
    public MxBhc updateMuestraBhc(@RequestBody MxBhc muestra) {
        return service.updateMuestraBhc(muestra);
    }
	

	/*Dengue*/
	@GetMapping("/muestras/dengue")
	public List<MxDengue> getMuestrasDengue() {
		return service.getMuestrasDengue();
	}
	
	@GetMapping("/muestras/dengue/{id}")
    public MxDengue getMuestraDengueById(@PathVariable Long id) {
        return service.getMuestraDengueById(id);
    }
	
	@PostMapping("/muestras/dengue")
    public MxDengue addMuestraDengue(@RequestBody MxDengue muestra) {
        return service.saveMuestraDengue(muestra);
    }
	
	@PutMapping("/muestras/dengue")
    public MxDengue updateMuestraDengue(@RequestBody MxDengue muestra) {
        return service.updateMuestraDengue(muestra);
    }
	
	/*Influenza*/
	@GetMapping("/muestras/influenza")
	public List<MxInfluenza> getMuestrasInfluenza() {
		return service.getMuestrasInfluenza();
	}
	
	@GetMapping("/muestras/influenza/{id}")
    public MxInfluenza getMuestraInfluenzaById(@PathVariable Long id) {
        return service.getMuestraInfluenzaById(id);
    }
	
	@PostMapping("/muestras/influenza")
    public MxInfluenza addMuestraInfluenza(@RequestBody MxInfluenza muestra) {
        return service.saveMuestraInfluenza(muestra);
    }
	
	@PutMapping("/muestras/influenza")
    public MxInfluenza updateMuestraInfluenza(@RequestBody MxInfluenza muestra) {
        return service.updateMuestraInfluenza(muestra);
    }
	
	/*U01*/
	@GetMapping("/muestras/u01")
	public List<MxU01> getMuestrasU01() {
		return service.getMuestrasU01();
	}
	
	@GetMapping("/muestras/u01/{id}")
    public MxU01 getMuestraU01ById(@PathVariable Long id) {
        return service.getMuestraU01ById(id);
    }
	
	@PostMapping("/muestras/u01")
    public MxU01 addMuestraU01(@RequestBody MxU01 muestra) {
        return service.saveMuestraU01(muestra);
    }
	
	@PutMapping("/muestras/u01")
    public MxU01 updateMuestraU01(@RequestBody MxU01 muestra) {
        return service.updateMuestraU01(muestra);
    }
	
}
