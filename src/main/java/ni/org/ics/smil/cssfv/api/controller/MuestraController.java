package ni.org.ics.smil.cssfv.api.controller;

import java.text.ParseException;
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
import ni.org.ics.smil.cssfv.api.entity.MxTransmision;
import ni.org.ics.smil.cssfv.api.entity.MxU01;
import ni.org.ics.smil.cssfv.api.entity.view.MuestrasEstudiosView;
import ni.org.ics.smil.cssfv.api.entity.view.MuestrasTomadasView;
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
	
	@GetMapping("/muestras/ultimos-registros")
	public List<Muestra> getMuestrasLast() {
		return service.getMuestrasLastRecords();
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
	
	@PutMapping("/muestras/anular")
    public Muestra updateAnularMuestra(@RequestBody Muestra muestra) {
        return service.anularMuestra(muestra);
    }
	
	@PutMapping("/muestras/envio")
	public List<Muestra> updateEnvioMuestras(@RequestBody List<Muestra> muestra) {
		return service.envioMuestras(muestra);
	}
	
	//@GetMapping("/muestras/byCodigoParticipante_Y_catMuestraId/{codigoParticipante}/{id}")
	//public long getMuestrasByCodigoParticipanteYCatMuestraId(@PathVariable Integer codigoParticipante ,@PathVariable Long id) {
	@GetMapping("/muestras/byCodigoParticipante_Y_catMuestraId")
	public long getMuestrasByCodigoParticipanteYCatMuestraId(@RequestParam Integer codigoParticipante ,@RequestParam Long id) {
		return service.countMxByCodigoParticipanteAndTypeMx(codigoParticipante, id);
	}
	
	@GetMapping("/muestras/cantidadMuestrasTomadas")
	public List<MuestrasTomadasView> getMuestrasTomadas() {
		//MuestrasResponse muestrasResponse = new MuestrasResponse();
		//List<MuestrasDTO> uestrasDTO = service.getAllCountMuestras();
		return service.getAllCountMuestras();
	}
	
	@GetMapping("/muestras/por_estudios")
	public List<MuestrasEstudiosView> getMuestrasEstudios() {
		return service.getMuestrasPorEstudios();
	}
	
	@GetMapping("/muestras/codLabScan")
	public Muestra mxByCodLabScan(@RequestParam String codLabScan) {
		return service.getMxByCodLabScan(codLabScan);
	}
	
	@GetMapping("/muestras/codLab")
	public Muestra mxByCodLab(@RequestParam String codLab) {
		return service.getMxByCodLab(codLab);
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
	
	@GetMapping("/muestras/bhc/fechaDelDia")
	public List<MxBhc> getMuestrasBhcByFechaDelDia() {
		return service.getMuestrasBhcByCurrentDate();
	}
	
	@GetMapping("/muestras/bhc/participantes/codigo/fechas")
    public List<MxBhc> getMuestraBhcByRangeFechaAndCodigo(@RequestParam Integer codigoParticipante, @RequestParam String strFecha1, @RequestParam String strFecha2) throws ParseException {
		return service.getMuestraBhcByCodigoParticipanteAndFechaToma(codigoParticipante, strFecha1, strFecha2);		
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
	
	@GetMapping("/muestras/bhc/pendientes/envio")
	public List<MxBhc> getMuestrasBhcPendientes(@RequestParam Long id) {
		return service.muestrasBhcPendinteEnvio(id);
	}
	
	@GetMapping("/muestras/bhc/ultimo/code_lab")
	public String getLastMxBHCByCode(@RequestParam Integer codigo) {
		return service.ultimaMuestraBHCByCode(codigo);
	}
	
	@GetMapping("/muestras/bhc/cod_lab_scan")
	public MxBhc getMxBHCByCodLabScan(String codLabScan) {
		return service.muestraBHCByCodLabScan(codLabScan);
	}
	
	/*Dengue*/
	@GetMapping("/muestras/dengue")
	public List<MxDengue> getMuestrasDengue() {
		return service.getMuestrasDengue();
	}
	
	@GetMapping("/muestras/dengue/rango-fecha")
	public List<MxDengue> getAllMuestrasDengue(@RequestParam String strFecha1, @RequestParam String strFecha2) throws ParseException {
		return service.getAllMuestrasDengueByRangeDate(strFecha1, strFecha2);
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
	
	@GetMapping("/muestras/dengue/fechaDelDia")
	public List<MxDengue> getMuestrasDengueByFechaDelDia() {
		return service.getMuestrasDengueByCurrentDate();
	}
	
	@GetMapping("/muestras/dengue/participantes/codigo/fechas")
    public List<MxDengue> getMuestraDengueByRangeFechaAndCodigo(@RequestParam Integer codigoParticipante, 
    		@RequestParam String strFecha1, @RequestParam String strFecha2, @RequestParam String mxType) throws ParseException {
		return service.getMuestraDengueByCodigoParticipanteAndFechaToma(codigoParticipante, strFecha1, strFecha2, mxType);		
    }
	
	@GetMapping("/muestras/dengue/participantes/{codigoParticipante}")
    public List<MxDengue> getMuestraDengueByCodigoParticipante(@PathVariable Integer codigoParticipante) {
        return service.getMuestraDengueByCodigoParticipante(codigoParticipante);
    }
	
	@GetMapping("/muestras/dengue/pendientes/envio")
	public List<MxDengue> getMuestrasDenguePendientes(@RequestParam Long id) {
		return service.muestrasDenguePendinteEnvio(id);
	}
	
	@GetMapping("/muestras/dengue/metabolomicas")
	public List<MxDengue> getMuestrasDengueMetabolomicas() {
		return service.muestrasDengueMetabolomicas();
	}
	
	@GetMapping("/muestras/dengue/bhc")
	public List<MxDengue> getMuestraDengueBhc() {
		return service.muestraDengueBHC();
	} 
	
	@GetMapping("/muestras/dengue/pbmc")
	public List<MxDengue> getMuestraDenguePbmc() {
		return service.muestraDenguePBMC();
	} 
	
	@GetMapping("/muestras/dengue/paxgene")
	public List<MxDengue> getMuestraDenguePaxGene() {
		return service.muestraDenguePaxGene();
	} 
	
	@GetMapping("/muestras/dengue/hematicas")
	public List<MxDengue> getMuestraDengueHematicas() {
		return service.muestraDengueHematica();
	}
	
	@GetMapping("/muestras/dengue/metabolomica/ultimo/code_lab")
	public String getLastMxDengueMetabolomicaByCode(@RequestParam Integer codigo) {
		return service.ultimaMuestraDengueMetabolomicaByCode(codigo);
	}
	
	@GetMapping("/muestras/dengue/bhc/ultimo/code_lab")
	public String getLastMxDengueBHCByCode(@RequestParam Integer codigo) {
		return service.ultimaMuestraDengueBHCByCode(codigo);
	}
	
	@GetMapping("/muestras/dengue/pbmc/ultimo/code_lab")
	public String getLastMxDenguePBMCByCode(@RequestParam Integer codigo) {
		return service.ultimaMuestraDenguePBMCByCode(codigo);
	}
	
	@GetMapping("/muestras/dengue/paxgene/ultimo/code_lab")
	public String getLastMxDenguePaxGeneByCode(@RequestParam Integer codigo) {
		return service.ultimaMuestraDenguePaxGeneByCode(codigo);
	}
	
	@GetMapping("/muestras/dengue/hematica/ultimo/code_lab")
	public String getLastMxDengueHematicaByCode(@RequestParam Integer codigo) {
		return service.ultimaMuestraDengueHematicaByCode(codigo);
	}
	/**/
	@GetMapping("/muestras/dengue/metabolomica/ultima/muestra/codigoParticipante")
	public MxDengue getMxDengueMetabolomicaByCode(@RequestParam Integer codigo) {
		return service.ultimaMxDengueMetabolomicaByCode(codigo);
	}
	
	@GetMapping("/muestras/dengue/bhc/ultima/muestra/codigoParticipante")
	public MxDengue getMxDengueBHCByCode(@RequestParam Integer codigo) {
		return service.ultimaMxDengueBHCByCode(codigo);
	}
	
	@GetMapping("/muestras/dengue/pbmc/ultima/muestra/codigoParticipante")
	public MxDengue getMxDenguePBMCByCode(@RequestParam Integer codigo) {
		return service.ultimaMxDenguePBMCByCode(codigo);
	}
	
	@GetMapping("/muestras/dengue/paxgene/ultima/muestra/codigoParticipante")
	public MxDengue getMxDenguePaxGeneByCode(@RequestParam Integer codigo) {
		return service.ultimaMxDenguePaxGeneByCode(codigo);
	}
	
	@GetMapping("/muestras/dengue/hematica/ultima/muestra/codigoParticipante")
	public MxDengue getMxDengueHematicaByCode(@RequestParam Integer codigo) {
		return service.ultimaMxDengueHematicaByCode(codigo);
	}
	
	@GetMapping("/muestras/dengue/cod_lab_scan")
	public MxDengue getMxDengueByCodLabScan(String codLabScan) {
		return service.muestraMxDengueByCodeLabScan(codLabScan);
	}
	
	@GetMapping("/muestras/dengue/candidatos/pbmc")
	public List<MxDengue> getMxDengueCandidatosPbmc() {
		return service.muestrasMxDengueCandidatosPbmc();
	}
	
	/*Influenza*/
	@GetMapping("/muestras/influenza")
	public List<MxInfluenza> getMuestrasInfluenza() {
		return service.getMuestrasInfluenza();
	}
	
	@GetMapping("/muestras/influenza/fechaDelDia")
	public List<MxInfluenza> getMuestrasInfluenzaByFechaDelDia() {
		return service.getMuestrasInfluenzaByCurrentDate();
	}
	
	@GetMapping("/muestras/influenza/participantes/codigo/fechas")
    public List<MxInfluenza> getMuestraInfluenzaByRangeFechaAndCodigo(@RequestParam Integer codigoParticipante, @RequestParam String strFecha1, @RequestParam String strFecha2) throws ParseException {
		return service.getMuestraInfluenzaByCodigoParticipanteAndFechaToma(codigoParticipante, strFecha1, strFecha2);		
    }
	
	@GetMapping("/muestras/influenza/{id}")
    public MxInfluenza getMuestraInfluenzaById(@PathVariable Long id) {
        return service.getMuestraInfluenzaById(id);
    }
	
	@PostMapping("/muestras/influenza")
    public MxInfluenza addMuestraInfluenza(@RequestBody MxInfluenza muestra) throws Exception{
        return service.saveMuestraInfluenza(muestra);
    }
	
	@PutMapping("/muestras/influenza")
    public MxInfluenza updateMuestraInfluenza(@RequestBody MxInfluenza muestra) {
        return service.updateMuestraInfluenza(muestra);
    }
	
	@GetMapping("/muestras/influenza/participantes/{codigoParticipante}")
    public List<MxInfluenza> getMuestraInfluenzaByCodigoParticipante(@PathVariable Integer codigoParticipante) {
        return service.getMuestraInfluenzaByCodigoParticipante(codigoParticipante);
    }
	
	@GetMapping("/muestras/influenza/ultimoRegistro")
	public MxInfluenza getUltimoRegistro(@RequestParam Integer codigoParticipante) {
		return service.getUltimoRegistroMuestraInfluenzaByCodigo(codigoParticipante);
	}
	
	@GetMapping("/muestras/influenza/pendientes/envio")
	public List<MxInfluenza> getMuestrasInfluenzaPendientes(@RequestParam Long id) {
		return service.muestrasInfluenzaPendinteEnvio(id);
	}
	
	@GetMapping("/muestras/influenza/ultimo/code_lab")
	public String getLastMxInfluenzaByCode(@RequestParam Integer codigo) {
		return service.ultimaMuestraInfluenzaByCode(codigo);
	}
	
	@GetMapping("/muestras/influenza/cod_lab_scan")
	public MxInfluenza getMxInfluenzaByCodLabScan(String codLabScan) {
		return service.muestraInfluenzaByCodLabScan(codLabScan);
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
	
	@GetMapping("/muestras/u01/fechaDelDia")
	public List<MxU01> getMuestrasU01ByFechaDelDia() {
		return service.getMuestrasU01ByCurrentDate();
	}
	
	@GetMapping("/muestras/u01/participantes/codigo/fechas")
    public List<MxU01> getMuestraU01ByRangeFechaAndCodigo(@RequestParam Integer codigoParticipante, @RequestParam String strFecha1, @RequestParam String strFecha2) throws ParseException {
		return service.getMuestraU01ByCodigoParticipanteAndFechaToma(codigoParticipante, strFecha1, strFecha2);		
    }
	
	@GetMapping("/muestras/u01/pendientes/envio")
	public List<MxU01> getMuestrasUO1Pendientes(@RequestParam Long id) {
		return service.muestrasUO1PendinteEnvio(id);
	}
	
	@GetMapping("/muestras/u01/ultimo/code_lab")
	public String getLastMxUO1ByCode(@RequestParam Integer codigo) {
		return service.ultimaMuestraUO1ByCode(codigo);
	}
	
	@GetMapping("/muestras/u01/cod_lab_scan")
	public MxU01 getMxUO1ByCodLabScan(String codLabScan) {
		return service.muestraU01ByCodeLabScan(codLabScan);
	}
	
	/*Transmision*/
	@GetMapping("/muestras/transmision")
	public List<MxTransmision> getMuestrasTR(@RequestParam Long idMx) {
		return service.getMuestrasTransmision(idMx);
	}
	
	@GetMapping("/muestras/transmision/{id}")
    public MxTransmision getMuestraTRById(@PathVariable Long id) {
        return service.getMuestraTransmisionById(id);
    }
	
	@PostMapping("/muestras/transmision")
    public MxTransmision addMuestraTR(@RequestBody MxTransmision muestra) {
        return service.saveMuestraTransmision(muestra);
    }
	
	@PutMapping("/muestras/transmision")
    public MxTransmision updateMuestraTR(@RequestBody MxTransmision muestra) {
        return service.updateMuestraTransmision(muestra);
    }
	
	@GetMapping("/muestras/transmision/fechaDelDia")
	public List<MxTransmision> getMuestrasTRByFechaDelDia(@RequestParam Long idMx) {
		return service.getMuestrasTransmisionByCurrentDate(idMx);
	}
	
	@GetMapping("/muestras/transmision/participantes/codigo/fechas")
    public List<MxTransmision> getMuestraTRByRangeFechaAndCodigo(@RequestParam Integer codigoParticipante, @RequestParam Long idMx, @RequestParam String strFecha1, @RequestParam String strFecha2) throws ParseException {
		return service.getMuestraTransmisionByCodigoParticipanteAndFechaToma(codigoParticipante, idMx, strFecha1, strFecha2);		
    }
	
	@GetMapping("/muestras/transmision/pendientes/envio")
	public List<MxTransmision> getMuestrasTransmisionPendientes(@RequestParam Long id) {
		return service.muestrasTransmisionPendinteEnvio(id);
	}
	
	@GetMapping("/muestras/transmision/ultimo/code_lab")
	public String getLastMxTransmisionByCode(@RequestParam Integer codigo) {
		return service.ultimaMuestraTransmisionByCode(codigo);
	}
	
	@GetMapping("/muestras/transmision/cod_lab_scan")
	public MxTransmision getMxTransmisionByCodLabScan(String codLabScan) {
		return service.muestraTransmisionByCodLabScan(codLabScan);
	}
}
