package ni.org.ics.smil.cssfv.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ni.org.ics.smil.cssfv.api.entity.Muestra;
import ni.org.ics.smil.cssfv.api.entity.MxBhc;
import ni.org.ics.smil.cssfv.api.entity.MxDengue;
import ni.org.ics.smil.cssfv.api.entity.MxInfluenza;
import ni.org.ics.smil.cssfv.api.entity.MxTransmision;
import ni.org.ics.smil.cssfv.api.entity.MxU01;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatAnioEstudio;
import ni.org.ics.smil.cssfv.api.entity.view.MuestrasEstudiosView;
import ni.org.ics.smil.cssfv.api.entity.view.MuestrasTomadasView;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.CatAnioEstudioRepository;
import ni.org.ics.smil.cssfv.api.repository.MuestraBhcRepository;
import ni.org.ics.smil.cssfv.api.repository.MuestraDengueRepository;
import ni.org.ics.smil.cssfv.api.repository.MuestraInfluenzaRepository;
import ni.org.ics.smil.cssfv.api.repository.MuestraRepository;
import ni.org.ics.smil.cssfv.api.repository.MuestraTransmisionRepository;
import ni.org.ics.smil.cssfv.api.repository.MuestraU01Repository;
import ni.org.ics.smil.cssfv.api.repository.MuestrasEstudiosViewRepository;
import ni.org.ics.smil.cssfv.api.repository.MuestrasTomadasViewRepository;

@Service
public class MuestraService {

	@Autowired
	private MuestraRepository repository;
	
	@Autowired
	private MuestraBhcRepository repositoryBhc;

	@Autowired
	private MuestraDengueRepository repositoryDengue;
	
	@Autowired
	private MuestraInfluenzaRepository repositoryInf;
	
	@Autowired
	private MuestraU01Repository repositoryU01;
	
	@Autowired
	private MuestraTransmisionRepository repositoryTransmision;
	
	@Autowired
	private MuestrasTomadasViewRepository repositoryMuestrasTomadasView;
	
	@Autowired
	private MuestrasEstudiosViewRepository muestrasEstudiosViewRepository;
	
	@Autowired
	private CatAnioEstudioRepository repositoryCatAnioEstudio;
	
	
	/*
	 * Tabla Maestro de mx
	 * */
	public List<Muestra> getMuestras() {
        return repository.findAll();
    }
	
	public List<Muestra> getMuestrasLastRecords() {
		return repository.findTopMuestras();
	}
	
	@Transactional
	public Muestra saveMuestra(Muestra muestra) {
		return repository.save(muestra);
	}
	
	public Muestra getMuestraById(Long id) {
		Muestra Muestra = repository.findById(id).orElse(null);
		if (Muestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", id.toString());
		return Muestra;
	}


	public void deleteMuestra(Long id) {
		Muestra oldMuestra = repository.findById(id).orElse(null);		
		if (oldMuestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", id.toString());
		repository.deleteById(id);
	}

	@Transactional
	public Muestra updateMuestra(Muestra muestra) {
		Muestra oldMuestra = repository.findById(muestra.getId()).orElse(null);
		
		if (oldMuestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", muestra.getId().toString());		
				
		return repository.save(muestra);
	}
	
	@Transactional
	public Muestra anularMuestra(Muestra muestra) {
		Muestra oldMuestra = repository.findById(muestra.getId()).orElse(null);
		
		if (oldMuestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", muestra.getId().toString());		
		
		if (muestra.getOtroMotivoAnulacion()) {
			oldMuestra.setOtroMotivoAnulacion(muestra.getOtroMotivoAnulacion());
			oldMuestra.setMotivoAnulacion(muestra.getMotivoAnulacion());
			oldMuestra.setAnulada(true);
		} else {
			oldMuestra.setOtroMotivoAnulacion(muestra.getOtroMotivoAnulacion());
			oldMuestra.setMotivoAnulacionId(muestra.getMotivoAnulacionId());
			oldMuestra.setAnulada(true);
		}
		
		return repository.save(oldMuestra);
	}
	
	public Muestra getMxByCodLabScan(String codLabScan) {
		return repository.findMxByCodLabScan(codLabScan);
	}
	
	public Muestra getMxByCodLab(String codLab) {
		return repository.findMxByCodLab(codLab);
	}
	
	public List<Muestra> envioMuestras(List<Muestra> muestra) {
		for (Muestra envioMx : muestra) {
			Muestra muestraById = repository.findById(envioMx.getId()).orElse(null);
			if (muestraById != null) {
				muestraById.setMxEnviada(true);
				muestraById.setBioanalistaEnvia(envioMx.getBioanalistaEnvia());
				muestraById.setFechaEnvio(envioMx.getFechaEnvio());
				muestraById.setHoraEnvio(envioMx.getHoraEnvio());
				muestraById.setTempEnvio(envioMx.getTempEnvio());
				muestraById.setViaje(envioMx.getViaje());
				repository.save(muestraById);
			}
		}
		return muestra;
	}
	
	public long countMxByCodigoParticipanteAndTypeMx(Integer codigoParticipante, long id) {
		long count = repository.countBycodigoParticipanteAndMxIdId(codigoParticipante, id);
		return count;
	}
	
	public List<MuestrasTomadasView> getAllCountMuestras() {
		return repositoryMuestrasTomadasView.findAll();
	}
	
	public List<MuestrasEstudiosView> getMuestrasPorEstudios() {
		return muestrasEstudiosViewRepository.findAll();
	}
	/*
	 * Tabla de muestras BHC
	 * */
	public List<MxBhc> getMuestrasBhc() {
        return repositoryBhc.findAll();
    }
	
	@Transactional
	public MxBhc saveMuestraBhc(MxBhc muestra) {
		Muestra muestraObj = muestra.getMuestraId();
		
		repository.save(muestraObj);
		
		muestra.getMuestraId().setId(muestraObj.getId());
		
		return repositoryBhc.save(muestra);
	}
	
	public List<MxBhc> getMuestrasBhcByCurrentDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		Date startDate = cal.getTime();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		Date endDate = cal.getTime();
	    
	    List<MxBhc> mxBhc = repositoryBhc.findByMuestraIdFechaRegistroBetween(startDate, endDate);
	    return mxBhc;
    }
	
	public List<MxBhc> getMuestraBhcByCodigoParticipanteAndFechaToma(Integer codigoParticipante, String fecha1, String fecha2) throws ParseException {
		
		List<MxBhc> mxBhc = new ArrayList<MxBhc>();
		
		/*Obteniendo la lista por rango de fecha y codigo*/
		if (codigoParticipante > 0 && fecha1 != "" && fecha2 != "") {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Date startDate = sdf.parse(fecha1);
			Date endDate = sdf.parse(fecha2);

			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();

			cal.setTime(startDate);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);

			cal2.setTime(endDate);
			cal2.set(Calendar.HOUR_OF_DAY, 23);
			cal2.set(Calendar.MINUTE, 59);
			cal2.set(Calendar.SECOND, 59);

			Date dFecha1 = cal.getTime();

			Date dFecha2 = cal2.getTime();

			mxBhc = repositoryBhc.findByMuestraIdCodigoParticipanteAndMuestraIdFechaTomaBetween(
					codigoParticipante, dFecha1, dFecha2);
		} else if (codigoParticipante == 0 && fecha1 != "" && fecha2 != "") {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Date startDate = sdf.parse(fecha1);
			Date endDate = sdf.parse(fecha2);

			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();

			cal.setTime(startDate);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);

			cal2.setTime(endDate);
			cal2.set(Calendar.HOUR_OF_DAY, 23);
			cal2.set(Calendar.MINUTE, 59);
			cal2.set(Calendar.SECOND, 59);

			Date dFecha1 = cal.getTime();

			Date dFecha2 = cal2.getTime();

			mxBhc = repositoryBhc.findByMuestraIdFechaRegistroBetween(dFecha1, dFecha2);
		} else if (codigoParticipante > 0 && fecha1 == "" && fecha2 == "") {
			mxBhc = repositoryBhc.findByMuestraIdCodigoParticipante(codigoParticipante);
		}
		
		//if (mxInfluenza.size() <= 0) throw new NotEntityFoundException(MxInfluenza.class.getSimpleName(), "codigo-participante y fecha toma", codigoParticipante.toString() + " & " + fecha1.toString() + " - "+ fecha2.toString());
		return mxBhc;
	}
	
	public MxBhc getMuestraBhcById(Long id) {
		MxBhc Muestra = repositoryBhc.findById(id).orElse(null);
		if (Muestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", id.toString());
		return Muestra;
	}
	
	public List<MxBhc> getMuestraBhcByCodigoParticipante(Integer codigoParticipante) {
		List<MxBhc> mxBhc = repositoryBhc.findByMuestraIdCodigoParticipante(codigoParticipante);
		if (mxBhc.size() <= 0) throw new NotEntityFoundException(MxBhc.class.getSimpleName(), "codigo-participante", codigoParticipante.toString());
		return mxBhc;
	}
	
	public List<MxBhc> getMuestraBhcByRangeFechaToma(Date fecha1, Date fecha2) {
		List<MxBhc> mxBhc = repositoryBhc.findByMuestraIdFechaTomaBetween(fecha1, fecha2);
		if (mxBhc.size() <= 0) throw new NotEntityFoundException(MxBhc.class.getSimpleName(), "fechaToma between ", fecha1.toString() + " - "+ fecha2.toString());
		return mxBhc;
	}
	
	public List<MxBhc> getMuestraBhcByCodigoParticipanteAndFechaToma(Integer codigoParticipante, Date fecha1, Date fecha2) {
		List<MxBhc> mxBhc = repositoryBhc.findByMuestraIdCodigoParticipanteAndMuestraIdFechaTomaBetween(codigoParticipante, fecha1, fecha2);
		if (mxBhc.size() <= 0) throw new NotEntityFoundException(MxBhc.class.getSimpleName(), "codigo-participante y fecha toma", codigoParticipante.toString() + " & " + fecha1.toString() + " - "+ fecha2.toString());
		return mxBhc;
	}


	public void deleteMuestraBhc(Long id) {
		MxBhc oldMuestra = repositoryBhc.findById(id).orElse(null);		
		if (oldMuestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", id.toString());
		repositoryBhc.deleteById(id);
	}

	@Transactional
	public MxBhc updateMuestraBhc(MxBhc muestra) {
		Muestra muestraObj = muestra.getMuestraId();
		
		Muestra oldMuestra = repository.findById(muestraObj.getId()).orElse(null);
		if (oldMuestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", muestraObj.getId().toString());		
		repository.save(muestraObj);
		
		MxBhc oldMuestraBhc = repositoryBhc.findById(muestra.getId()).orElse(null);
		if (oldMuestraBhc == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", muestra.getId().toString());		
		return repositoryBhc.save(muestra);
		/*
		 * MxBhc oldMuestra = repositoryBhc.findById(muestra.getId()).orElse(null);
		 * 
		 * if (oldMuestra == null) throw new
		 * NotEntityFoundException(Muestra.class.getSimpleName(), "Id",
		 * muestra.getId().toString());
		 * 
		 * return repositoryBhc.save(muestra);
		 */
	}
	
	public List<MxBhc> muestrasBhcPendinteEnvio(Long id) {
		return repositoryBhc.getMuestrasBhcPendientesEnvio(id);
	}
	
	public String ultimaMuestraBHCByCode(Integer codigo) {
		CatAnioEstudio catAnioEstudio = repositoryCatAnioEstudio.findLastRecordAnioEstudio();
		return repositoryBhc.findMxBHCByCode(codigo, catAnioEstudio.getFechaInicio(), catAnioEstudio.getFechaFin());
	}
	
	public MxBhc muestraBHCByCodLabScan(String codLabScan) {
		CatAnioEstudio catAnioEstudio = repositoryCatAnioEstudio.findLastRecordAnioEstudio();
		return repositoryBhc.findMxBHCByCodLabScan(codLabScan, catAnioEstudio.getFechaInicio(), catAnioEstudio.getFechaFin());
	}
	
	
	/*
	 * Tabla de muestras Dengue
	 * */
	public List<MxDengue> getMuestrasDengue() {
        return repositoryDengue.findAll();
    }
	
	public List<MxDengue> getAllMuestrasDengueByRangeDate(String fecha1, String fecha2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date startDate = sdf.parse(fecha1);
		Date endDate = sdf.parse(fecha2);

		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		cal.setTime(startDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		cal2.setTime(endDate);
		cal2.set(Calendar.HOUR_OF_DAY, 23);
		cal2.set(Calendar.MINUTE, 59);
		cal2.set(Calendar.SECOND, 59);

		Date dFecha1 = cal.getTime();

		Date dFecha2 = cal2.getTime();
		return repositoryDengue.findAllMuestrasDengueFechaRegistroBetween(dFecha1, dFecha2);
	}
	public List<MxDengue> getMuestraDengueByCodigoParticipante(Integer codigoParticipante) {
		List<MxDengue> mxDengue = repositoryDengue.findMuestrasDengueByCodigoParticipante(codigoParticipante);
		if (mxDengue.size() <= 0) throw new NotEntityFoundException(MxBhc.class.getSimpleName(), "codigo-participante", codigoParticipante.toString());
		return mxDengue;
	}
	
	@Transactional
	public MxDengue saveMuestraDengue(MxDengue muestra) {
		Muestra muestraObj = muestra.getMuestraId();
		
		repository.save(muestraObj);
		
		muestra.getMuestraId().setId(muestraObj.getId());
		
		return repositoryDengue.save(muestra);
		//return repositoryDengue.save(muestra);
	}
	
	public MxDengue getMuestraDengueById(Long id) {
		MxDengue Muestra = repositoryDengue.findById(id).orElse(null);
		if (Muestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", id.toString());
		return Muestra;
	}

	public void deleteMuestraDengue(Long id) {
		MxDengue oldMuestra = repositoryDengue.findById(id).orElse(null);		
		if (oldMuestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", id.toString());
		repositoryDengue.deleteById(id);
	}

	@Transactional
	public MxDengue updateMuestraDengue(MxDengue muestra) {
		Muestra muestraObj = muestra.getMuestraId();
		
		Muestra oldMuestra = repository.findById(muestraObj.getId()).orElse(null);
		if (oldMuestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", muestraObj.getId().toString());		
		repository.save(muestraObj);
		
		MxDengue oldMuestraDengue = repositoryDengue.findById(muestra.getId()).orElse(null);
		if (oldMuestraDengue == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", muestra.getId().toString());		
		return repositoryDengue.save(muestra);
		/*
		 * MxDengue oldMuestra =
		 * repositoryDengue.findById(muestra.getId()).orElse(null);
		 * 
		 * if (oldMuestra == null) throw new
		 * NotEntityFoundException(Muestra.class.getSimpleName(), "Id",
		 * muestra.getId().toString());
		 * 
		 * return repositoryDengue.save(muestra);
		 */
	}
	
	public List<MxDengue> getMuestrasDengueByCurrentDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		Date startDate = cal.getTime();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		Date endDate = cal.getTime();
	    
	    List<MxDengue> mxDengue = repositoryDengue.findByMuestrasHematicasFechaRegistroBetween(startDate, endDate);
	    return mxDengue;
    }
	
	public List<MxDengue> getMuestraDengueByCodigoParticipanteAndFechaToma(Integer codigoParticipante, 
			String fecha1, String fecha2, String mxType) throws ParseException {
		
		List<MxDengue> mxDengue = new ArrayList<MxDengue>();
		
		/*Obteniendo la lista por rango de fecha y codigo*/
		if (codigoParticipante > 0 && fecha1 != "" && fecha2 != "") {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Date startDate = sdf.parse(fecha1);
			Date endDate = sdf.parse(fecha2);

			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();

			cal.setTime(startDate);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);

			cal2.setTime(endDate);
			cal2.set(Calendar.HOUR_OF_DAY, 23);
			cal2.set(Calendar.MINUTE, 59);
			cal2.set(Calendar.SECOND, 59);

			Date dFecha1 = cal.getTime();

			Date dFecha2 = cal2.getTime();

			if (mxType.equals("hematica")) {
				mxDengue = repositoryDengue.findByMuestrasHematicasCodigoFechaRegistroBetween(dFecha1, dFecha2, codigoParticipante);
			}
			else if (mxType.equals("bhc")) {
				mxDengue = repositoryDengue.findByMuestrasBHCCodigoFechaRegistroBetween(dFecha1, dFecha2, codigoParticipante);
			}
			else if (mxType.equals("metabolomica")) {
				mxDengue = repositoryDengue.findByMuestrasMetabolomicasCodigoFechaRegistroBetween(dFecha1, dFecha2, codigoParticipante);
			}
			else if (mxType.equals("paxgene")) {
				mxDengue = repositoryDengue.findByMuestrasPaxGeneCodigoFechaRegistroBetween(dFecha1, dFecha2, codigoParticipante);
			}
			else if (mxType.equals("pbmc")) {
				mxDengue = repositoryDengue.findByMuestrasPBMCCodigoFechaRegistroBetween(dFecha1, dFecha2, codigoParticipante);
			}
			else {
				mxDengue = new ArrayList<MxDengue>();
			}
			/*mxDengue = repositoryDengue.findByMuestraIdCodigoParticipanteAndMuestraIdFechaTomaBetween(
					codigoParticipante, dFecha1, dFecha2);*/
		} else if (codigoParticipante == 0 && fecha1 != "" && fecha2 != "") {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Date startDate = sdf.parse(fecha1);
			Date endDate = sdf.parse(fecha2);

			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();

			cal.setTime(startDate);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);

			cal2.setTime(endDate);
			cal2.set(Calendar.HOUR_OF_DAY, 23);
			cal2.set(Calendar.MINUTE, 59);
			cal2.set(Calendar.SECOND, 59);

			Date dFecha1 = cal.getTime();

			Date dFecha2 = cal2.getTime();

			if (mxType.equals("hematica")) {
				mxDengue = repositoryDengue.findByMuestrasHematicasFechaRegistroBetween(dFecha1, dFecha2);
			}
			else if (mxType.equals("bhc")) {
				mxDengue = repositoryDengue.findByMuestrasBHCFechaRegistroBetween(dFecha1, dFecha2);
			}
			else if (mxType.equals("metabolomica")) {
				mxDengue = repositoryDengue.findByMuestrasMetabolomicasFechaRegistroBetween(dFecha1, dFecha2);
			}
			else if (mxType.equals("paxgene")) {
				mxDengue = repositoryDengue.findByMuestrasPaxGeneFechaRegistroBetween(dFecha1, dFecha2);
			}
			else if (mxType.equals("pbmc")) {
				mxDengue = repositoryDengue.findByMuestrasPBMCFechaRegistroBetween(dFecha1, dFecha2);
			}
			else {
				mxDengue = new ArrayList<MxDengue>();
			}
			
			//mxDengue = repositoryDengue.findByMuestrasHematicasFechaRegistroBetween(dFecha1, dFecha2);
		} else if (codigoParticipante > 0 && fecha1 == "" && fecha2 == "") {
			if (mxType.equals("hematica")) {
				mxDengue = repositoryDengue.findMuestrasDengueHematicasByCodigoParticipante(codigoParticipante);
			}
			else if (mxType.equals("bhc")) {
				mxDengue = repositoryDengue.findMuestrasDengueBHCByCodigoParticipante(codigoParticipante);
			}
			else if (mxType.equals("metabolomica")) {
				mxDengue = repositoryDengue.findMuestrasDengueMetabolomicasByCodigoParticipante(codigoParticipante);
			}
			else if (mxType.equals("paxgene")) {
				mxDengue = repositoryDengue.findMuestrasDenguePaxgeneByCodigoParticipante(codigoParticipante);
			}
			else if (mxType.equals("pbmc")) {
				mxDengue = repositoryDengue.findMuestrasDenguePBMCByCodigoParticipante(codigoParticipante);
			}
			else {
				mxDengue = new ArrayList<MxDengue>();
			}
			
		}
		
		//if (mxInfluenza.size() <= 0) throw new NotEntityFoundException(MxInfluenza.class.getSimpleName(), "codigo-participante y fecha toma", codigoParticipante.toString() + " & " + fecha1.toString() + " - "+ fecha2.toString());
		return mxDengue;
	}
	
	public List<MxDengue> muestrasDenguePendinteEnvio(Long id) {
		return repositoryDengue.getMuestrasDenguePendientesEnvio(id);
	}
	
	public List<MxDengue> muestrasDengueMetabolomicas() {
		return repositoryDengue.getMuestrasDengueMetabolomicas();
	}
	
	public List<MxDengue> muestraDengueBHC() {
		return repositoryDengue.getMuestrasDengueBHC();
	}
	
	public List<MxDengue> muestraDenguePBMC() {
		return repositoryDengue.getMuestrasDenguePBMC();
	}
	
	public List<MxDengue> muestraDenguePaxGene() {
		return repositoryDengue.getMuestrasDenguePaxGene();
	}
	
	public List<MxDengue> muestraDengueHematica() {
		return repositoryDengue.getMuestrasDengueHematicas();
	}
	
	/**Ultimo codigo_lab de la muestra de dengue por codigo*/
	public String ultimaMuestraDengueMetabolomicaByCode(Integer codigo) {
		CatAnioEstudio catAnioEstudio = repositoryCatAnioEstudio.findLastRecordAnioEstudio();
		return repositoryDengue.findCodLabDengueMetabolomicaByCode(codigo, catAnioEstudio.getFechaInicio(), catAnioEstudio.getFechaFin());
	}
	
	public String ultimaMuestraDengueBHCByCode(Integer codigo) {
		CatAnioEstudio catAnioEstudio = repositoryCatAnioEstudio.findLastRecordAnioEstudio();
		return repositoryDengue.findCodLabDengueBHCByCode(codigo, catAnioEstudio.getFechaInicio(), catAnioEstudio.getFechaFin());
	}
	
	public String ultimaMuestraDenguePBMCByCode(Integer codigo) {
		CatAnioEstudio catAnioEstudio = repositoryCatAnioEstudio.findLastRecordAnioEstudio();
		return repositoryDengue.findCodLabDenguePBMCByCode(codigo, catAnioEstudio.getFechaInicio(), catAnioEstudio.getFechaFin());
	}
	
	public String ultimaMuestraDenguePaxGeneByCode(Integer codigo) {
		CatAnioEstudio catAnioEstudio = repositoryCatAnioEstudio.findLastRecordAnioEstudio();
		return repositoryDengue.findCodLabDenguePaxGeneByCode(codigo, catAnioEstudio.getFechaInicio(), catAnioEstudio.getFechaFin());
	}
	
	public String ultimaMuestraDengueHematicaByCode(Integer codigo) {
		CatAnioEstudio catAnioEstudio = repositoryCatAnioEstudio.findLastRecordAnioEstudio();
		return repositoryDengue.findCodLabDengueHematicaByCode(codigo, catAnioEstudio.getFechaInicio(), catAnioEstudio.getFechaFin());
	}
	
	/**Ultimo registro de la muestra de dengue por codigo*/
	public MxDengue ultimaMxDengueMetabolomicaByCode(Integer codigo) {
		CatAnioEstudio catAnioEstudio = repositoryCatAnioEstudio.findLastRecordAnioEstudio();
		return repositoryDengue.findMxDengueMetabolomicaByCode(codigo, catAnioEstudio.getFechaInicio(), catAnioEstudio.getFechaFin());
	}
	
	public MxDengue ultimaMxDengueBHCByCode(Integer codigo) {
		CatAnioEstudio catAnioEstudio = repositoryCatAnioEstudio.findLastRecordAnioEstudio();
		return repositoryDengue.findMxDengueBHCByCode(codigo, catAnioEstudio.getFechaInicio(), catAnioEstudio.getFechaFin());
	}
	
	public MxDengue ultimaMxDenguePBMCByCode(Integer codigo) {
		CatAnioEstudio catAnioEstudio = repositoryCatAnioEstudio.findLastRecordAnioEstudio();
		return repositoryDengue.findMxDenguePBMCByCode(codigo, catAnioEstudio.getFechaInicio(), catAnioEstudio.getFechaFin());
	}
	
	public MxDengue ultimaMxDenguePaxGeneByCode(Integer codigo) {
		CatAnioEstudio catAnioEstudio = repositoryCatAnioEstudio.findLastRecordAnioEstudio();
		return repositoryDengue.findMxDenguePaxGeneByCode(codigo, catAnioEstudio.getFechaInicio(), catAnioEstudio.getFechaFin());
	}
	
	public MxDengue ultimaMxDengueHematicaByCode(Integer codigo) {
		CatAnioEstudio catAnioEstudio = repositoryCatAnioEstudio.findLastRecordAnioEstudio();
		return repositoryDengue.findMxDengueHematicaByCode(codigo, catAnioEstudio.getFechaInicio(), catAnioEstudio.getFechaFin());
	}
	
	public MxDengue muestraMxDengueByCodeLabScan(String codeLabScan) {
		return repositoryDengue.findMxDengueByCodLabScan(codeLabScan);
	}
	
	public List<MxDengue> muestrasMxDengueCandidatosPbmc() {
		return repositoryDengue.findMuestrasDengueCandidatosPbmc();
	}
	/*
	 * Tabla de muestras Influenza
	 * */
	public List<MxInfluenza> getMuestrasInfluenza() {
        return repositoryInf.findAll();
    }
	
	public List<MxInfluenza> getMuestrasInfluenzaByCurrentDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		Date startDate = cal.getTime();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		Date endDate = cal.getTime();
	    
	    List<MxInfluenza> mxInfluenza = repositoryInf.findByMuestraIdFechaRegistroBetween(startDate, endDate);
	    return mxInfluenza;
    }
	
	public List<MxInfluenza> getMuestraInfluenzaByCodigoParticipanteAndFechaToma(Integer codigoParticipante, String fecha1, String fecha2) throws ParseException {
		
		List<MxInfluenza> mxInfluenza = new ArrayList<MxInfluenza>();
		
		/*Obteniendo la lista por rango de fecha y codigo*/
		if (codigoParticipante > 0 && fecha1 != "" && fecha2 != "") {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Date startDate = sdf.parse(fecha1);
			Date endDate = sdf.parse(fecha2);

			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();

			cal.setTime(startDate);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);

			cal2.setTime(endDate);
			cal2.set(Calendar.HOUR_OF_DAY, 23);
			cal2.set(Calendar.MINUTE, 59);
			cal2.set(Calendar.SECOND, 59);

			Date dFecha1 = cal.getTime();

			Date dFecha2 = cal2.getTime();

			mxInfluenza = repositoryInf.findByMuestraIdCodigoParticipanteAndMuestraIdFechaTomaBetween(
					codigoParticipante, dFecha1, dFecha2);
		} else if (codigoParticipante == 0 && fecha1 != "" && fecha2 != "") {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Date startDate = sdf.parse(fecha1);
			Date endDate = sdf.parse(fecha2);

			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();

			cal.setTime(startDate);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);

			cal2.setTime(endDate);
			cal2.set(Calendar.HOUR_OF_DAY, 23);
			cal2.set(Calendar.MINUTE, 59);
			cal2.set(Calendar.SECOND, 59);

			Date dFecha1 = cal.getTime();

			Date dFecha2 = cal2.getTime();

			mxInfluenza = repositoryInf.findByMuestraIdFechaRegistroBetween(dFecha1, dFecha2);
		} else if (codigoParticipante > 0 && fecha1 == "" && fecha2 == "") {
			mxInfluenza = repositoryInf.findByMuestraIdCodigoParticipante(codigoParticipante);
		}
		
		//if (mxInfluenza.size() <= 0) throw new NotEntityFoundException(MxInfluenza.class.getSimpleName(), "codigo-participante y fecha toma", codigoParticipante.toString() + " & " + fecha1.toString() + " - "+ fecha2.toString());
		return mxInfluenza;
	}
	
	@Transactional
	public MxInfluenza saveMuestraInfluenza(MxInfluenza muestra) throws Exception {
		
		Muestra muestraObj = muestra.getMuestraId();
		
		repository.save(muestraObj);
		
		muestra.getMuestraId().setId(muestraObj.getId());
		
		return repositoryInf.save(muestra);
	}
	
	@Transactional
	public MxInfluenza updateMuestraInfluenza(MxInfluenza muestra) {
		
		Muestra muestraObj = muestra.getMuestraId();
		
		Muestra oldMuestra = repository.findById(muestraObj.getId()).orElse(null);
		if (oldMuestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", muestraObj.getId().toString());		
		repository.save(muestraObj);
		
		MxInfluenza oldMuestraInfluenza = repositoryInf.findById(muestra.getId()).orElse(null);
		if (oldMuestraInfluenza == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", muestra.getId().toString());		
		return repositoryInf.save(muestra);
	}
	
	public MxInfluenza getMuestraInfluenzaById(Long id) {
		MxInfluenza Muestra = repositoryInf.findById(id).orElse(null);
		if (Muestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", id.toString());
		return Muestra;
	}


	public void deleteMuestraInfluenza(Long id) {
		MxInfluenza oldMuestra = repositoryInf.findById(id).orElse(null);		
		if (oldMuestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", id.toString());
		repositoryInf.deleteById(id);
	}
	
	public List<MxInfluenza> getMuestraInfluenzaByCodigoParticipante(Integer codigoParticipante) {
		List<MxInfluenza> mxInfluenza = repositoryInf.findByMuestraIdCodigoParticipante(codigoParticipante);
		if (mxInfluenza.size() <= 0) throw new NotEntityFoundException(MxInfluenza.class.getSimpleName(), "codigo-participante", codigoParticipante.toString());
		return mxInfluenza;
	}
	
	public MxInfluenza getUltimoRegistroMuestraInfluenzaByCodigo(Integer codigoParticipante) {
		MxInfluenza mxInfluenza = repositoryInf.findTopByOrderByMuestraIdCodigoParticipanteDesc(codigoParticipante);
		return mxInfluenza;
	}
	
	public List<MxInfluenza> muestrasInfluenzaPendinteEnvio(Long id) {
		return repositoryInf.getMuestrasInfluenzaPendientesEnvio(id);
	}
	
	public String ultimaMuestraInfluenzaByCode(Integer codigo) {
		CatAnioEstudio catAnioEstudio = repositoryCatAnioEstudio.findLastRecordAnioEstudio();
		return repositoryInf.findMxInfluenzaByCode(codigo, catAnioEstudio.getFechaInicio(), catAnioEstudio.getFechaFin());
	}
	
	public MxInfluenza muestraInfluenzaByCodLabScan(String codLabScan) {
		CatAnioEstudio catAnioEstudio = repositoryCatAnioEstudio.findLastRecordAnioEstudio();
		return repositoryInf.findMxInfluenzaByCodLabScan(codLabScan, catAnioEstudio.getFechaInicio(), catAnioEstudio.getFechaFin());
	}
	
	/*
	 * Tabla de muestras U01
	 * */
	public List<MxU01> getMuestrasU01() {
        return repositoryU01.findAll();
    }
	
	@Transactional
	public MxU01 saveMuestraU01(MxU01 muestra) {
		
		Muestra muestraObj = muestra.getMuestraId();

		repository.save(muestraObj);
		
		muestra.getMuestraId().setId(muestraObj.getId());
		
		return repositoryU01.save(muestra);
	}
	
	public MxU01 getMuestraU01ById(Long id) {
		MxU01 Muestra = repositoryU01.findById(id).orElse(null);
		if (Muestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", id.toString());
		return Muestra;
	}


	public void deleteMuestraU01(Long id) {
		MxU01 oldMuestra = repositoryU01.findById(id).orElse(null);		
		if (oldMuestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", id.toString());
		repositoryU01.deleteById(id);
	}

	@Transactional
	public MxU01 updateMuestraU01(MxU01 muestra) {
		/*
		 * MxU01 oldMuestra = repositoryU01.findById(muestra.getId()).orElse(null);
		 * 
		 * if (oldMuestra == null) throw new
		 * NotEntityFoundException(Muestra.class.getSimpleName(), "Id",
		 * muestra.getId().toString());
		 * 
		 * return repositoryU01.save(muestra);
		 */
		Muestra muestraObj = muestra.getMuestraId();
		
		Muestra oldMuestra = repository.findById(muestraObj.getId()).orElse(null);
		if (oldMuestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", muestraObj.getId().toString());		
		repository.save(muestraObj);
		
		MxU01 oldMuestraUO1 = repositoryU01.findById(muestra.getId()).orElse(null);
		if (oldMuestraUO1 == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", muestra.getId().toString());		
		return repositoryU01.save(muestra);
	}
	
	public List<MxU01> getMuestrasU01ByCurrentDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		Date startDate = cal.getTime();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		Date endDate = cal.getTime();
	    
	    List<MxU01> mxU01 = repositoryU01.findByMuestraIdFechaRegistroBetween(startDate, endDate);
	    return mxU01;
    }
	
	public List<MxU01> getMuestraU01ByCodigoParticipanteAndFechaToma(Integer codigoParticipante, String fecha1, String fecha2) throws ParseException {
		
		List<MxU01> mxU01 = new ArrayList<MxU01>();
		
		/*Obteniendo la lista por rango de fecha y codigo*/
		if (codigoParticipante > 0 && fecha1 != "" && fecha2 != "") {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Date startDate = sdf.parse(fecha1);
			Date endDate = sdf.parse(fecha2);

			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();

			cal.setTime(startDate);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);

			cal2.setTime(endDate);
			cal2.set(Calendar.HOUR_OF_DAY, 23);
			cal2.set(Calendar.MINUTE, 59);
			cal2.set(Calendar.SECOND, 59);

			Date dFecha1 = cal.getTime();

			Date dFecha2 = cal2.getTime();

			mxU01 = repositoryU01.findByMuestraIdCodigoParticipanteAndMuestraIdFechaTomaBetween(
					codigoParticipante, dFecha1, dFecha2);
		} else if (codigoParticipante == 0 && fecha1 != "" && fecha2 != "") {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Date startDate = sdf.parse(fecha1);
			Date endDate = sdf.parse(fecha2);

			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();

			cal.setTime(startDate);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);

			cal2.setTime(endDate);
			cal2.set(Calendar.HOUR_OF_DAY, 23);
			cal2.set(Calendar.MINUTE, 59);
			cal2.set(Calendar.SECOND, 59);

			Date dFecha1 = cal.getTime();

			Date dFecha2 = cal2.getTime();

			mxU01 = repositoryU01.findByMuestraIdFechaRegistroBetween(dFecha1, dFecha2);
		} else if (codigoParticipante > 0 && fecha1 == "" && fecha2 == "") {
			mxU01 = repositoryU01.findByMuestraIdCodigoParticipante(codigoParticipante);
		}
		
		//if (mxInfluenza.size() <= 0) throw new NotEntityFoundException(MxInfluenza.class.getSimpleName(), "codigo-participante y fecha toma", codigoParticipante.toString() + " & " + fecha1.toString() + " - "+ fecha2.toString());
		return mxU01;
	}
	
	public List<MxU01> muestrasUO1PendinteEnvio(Long id) {
		return repositoryU01.getMuestrasUO1PendientesEnvio(id);
	}
	
	public String ultimaMuestraUO1ByCode(Integer codigo) {
		CatAnioEstudio catAnioEstudio = repositoryCatAnioEstudio.findLastRecordAnioEstudio();
		return repositoryU01.findMxUO1ByCode(codigo, catAnioEstudio.getFechaInicio(), catAnioEstudio.getFechaFin());
	}
	
	public MxU01 muestraU01ByCodeLabScan(String codLabScan) {
		CatAnioEstudio catAnioEstudio = repositoryCatAnioEstudio.findLastRecordAnioEstudio();
		return repositoryU01.findMxU01ByCodLabScan(codLabScan, catAnioEstudio.getFechaInicio(), catAnioEstudio.getFechaFin());
	}

	/*
	 * Tabla de muestras Transmision
	 * */

	public List<MxTransmision> getMuestrasTransmision(Long idMx) {
	    //return repositoryTransmision.findAll();
		return repositoryTransmision.getListTransmision(idMx);
	}

	@Transactional
	public MxTransmision saveMuestraTransmision(MxTransmision muestra) {
		
		Muestra muestraObj = muestra.getMuestraId();
	
		repository.save(muestraObj);
		
		muestra.getMuestraId().setId(muestraObj.getId());
		
		return repositoryTransmision.save(muestra);
	}

	public MxTransmision getMuestraTransmisionById(Long id) {
		MxTransmision Muestra = repositoryTransmision.findById(id).orElse(null);
		if (Muestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", id.toString());
		return Muestra;
	}

	public void deleteMuestraTransmision(Long id) {
		MxTransmision oldMuestra = repositoryTransmision.findById(id).orElse(null);		
		if (oldMuestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", id.toString());
		repositoryTransmision.deleteById(id);
	}
	
	@Transactional
	public MxTransmision updateMuestraTransmision(MxTransmision muestra) {
		/*
		 * MxU01 oldMuestra = repositoryU01.findById(muestra.getId()).orElse(null);
		 * 
		 * if (oldMuestra == null) throw new
		 * NotEntityFoundException(Muestra.class.getSimpleName(), "Id",
		 * muestra.getId().toString());
		 * 
		 * return repositoryU01.save(muestra);
		 */
		Muestra muestraObj = muestra.getMuestraId();
		
		Muestra oldMuestra = repository.findById(muestraObj.getId()).orElse(null);
		if (oldMuestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", muestraObj.getId().toString());		
		repository.save(muestraObj);
		
		MxTransmision oldMuestraTransmision = repositoryTransmision.findById(muestra.getId()).orElse(null);
		if (oldMuestraTransmision == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", muestra.getId().toString());		
		return repositoryTransmision.save(muestra);
	}
	
	public List<MxTransmision> getMuestrasTransmisionByCurrentDate(Long id) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		Date startDate = cal.getTime();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		Date endDate = cal.getTime();
	    
	    List<MxTransmision> mxTransmision = repositoryTransmision.findByMuestraIdFechaRegistroBetween(startDate, endDate, id);
	    return mxTransmision;
    }
	
	public List<MxTransmision> getMuestraTransmisionByCodigoParticipanteAndFechaToma(Integer codigoParticipante, Long id,
			String fecha1, String fecha2) throws ParseException {
		
		List<MxTransmision> mxTransmision = new ArrayList<MxTransmision>();
		
		/*Obteniendo la lista por rango de fecha y codigo*/
		if (codigoParticipante > 0 && fecha1 != "" && fecha2 != "") {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Date startDate = sdf.parse(fecha1);
			Date endDate = sdf.parse(fecha2);

			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();

			cal.setTime(startDate);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);

			cal2.setTime(endDate);
			cal2.set(Calendar.HOUR_OF_DAY, 23);
			cal2.set(Calendar.MINUTE, 59);
			cal2.set(Calendar.SECOND, 59);

			Date dFecha1 = cal.getTime();

			Date dFecha2 = cal2.getTime();

			mxTransmision = repositoryTransmision.findByMuestraIdCodigoParticipanteAndMuestraIdMxIdIdAndMuestraIdFechaTomaBetween(
					codigoParticipante, id, dFecha1, dFecha2);
		} else if (codigoParticipante == 0 && fecha1 != "" && fecha2 != "") {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Date startDate = sdf.parse(fecha1);
			Date endDate = sdf.parse(fecha2);

			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();

			cal.setTime(startDate);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);

			cal2.setTime(endDate);
			cal2.set(Calendar.HOUR_OF_DAY, 23);
			cal2.set(Calendar.MINUTE, 59);
			cal2.set(Calendar.SECOND, 59);

			Date dFecha1 = cal.getTime();

			Date dFecha2 = cal2.getTime();

			mxTransmision = repositoryTransmision.findByMuestraIdFechaRegistroBetween(dFecha1, dFecha2, id);
		} else if (codigoParticipante > 0 && fecha1 == "" && fecha2 == "") {
			mxTransmision = repositoryTransmision.findByMuestraIdCodigoParticipanteAndMuestraIdMxId(codigoParticipante, id);
		}
		
		//if (mxInfluenza.size() <= 0) throw new NotEntityFoundException(MxInfluenza.class.getSimpleName(), "codigo-participante y fecha toma", codigoParticipante.toString() + " & " + fecha1.toString() + " - "+ fecha2.toString());
		return mxTransmision;
	}
	
	public List<MxTransmision> muestrasTransmisionPendinteEnvio(Long id) {
		return repositoryTransmision.getMuestrasTransmisionPendientesEnvio(id);
	}
	
	public String ultimaMuestraTransmisionByCode(Integer codigo) {
		CatAnioEstudio catAnioEstudio = repositoryCatAnioEstudio.findLastRecordAnioEstudio();
		return repositoryTransmision.findMxTransmisionByCode(codigo, catAnioEstudio.getFechaInicio(), catAnioEstudio.getFechaFin());
	}
	
	public MxTransmision muestraTransmisionByCodLabScan(String codLabScan) {
		CatAnioEstudio catAnioEstudio = repositoryCatAnioEstudio.findLastRecordAnioEstudio();
		return repositoryTransmision.findMxTransmisionByCodLabScan(codLabScan, catAnioEstudio.getFechaInicio(), catAnioEstudio.getFechaFin());
	}
	
	/*
	 * Funcion para verificar si existe una muestra(Influenza, Dengue, BHC, etc) y que no este
	 * anulada en el dia actual para el codigo ingresado
	 * */
	public boolean verificarExisteMuestraMismoCodigoYDia(Muestra muestra) {
		boolean result = false;
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		Date startDate = cal.getTime();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		Date endDate = cal.getTime();
		
		long count = repository.countBycodigoParticipanteAndFechaRegistroBetween(muestra.getCodigoParticipante(), startDate, endDate);
		if (count > 0) {
			result = true;
		}
		
		return result;
	}
	
}
