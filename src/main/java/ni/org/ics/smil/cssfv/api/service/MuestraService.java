package ni.org.ics.smil.cssfv.api.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.Muestra;
import ni.org.ics.smil.cssfv.api.entity.MxBhc;
import ni.org.ics.smil.cssfv.api.entity.MxDengue;
import ni.org.ics.smil.cssfv.api.entity.MxInfluenza;
import ni.org.ics.smil.cssfv.api.entity.MxU01;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.MuestraBhcRepository;
import ni.org.ics.smil.cssfv.api.repository.MuestraDengueRepository;
import ni.org.ics.smil.cssfv.api.repository.MuestraInfluenzaRepository;
import ni.org.ics.smil.cssfv.api.repository.MuestraRepository;
import ni.org.ics.smil.cssfv.api.repository.MuestraU01Repository;

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
	/*
	 * Tabla Maestro de mx
	 * */
	public List<Muestra> getMuestras() {
        return repository.findAll();
    }
	
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

	public Muestra updateMuestra(Muestra muestra) {
		Muestra oldMuestra = repository.findById(muestra.getId()).orElse(null);
		
		if (oldMuestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", muestra.getId().toString());		
				
		return repository.save(muestra);
	}
	
	/*
	 * Tabla de muestras BHC
	 * */
	public List<MxBhc> getMuestrasBhc() {
        return repositoryBhc.findAll();
    }
	
	public MxBhc saveMuestraBhc(MxBhc muestra) {
		return repositoryBhc.save(muestra);
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

	public MxBhc updateMuestraBhc(MxBhc muestra) {
		MxBhc oldMuestra = repositoryBhc.findById(muestra.getId()).orElse(null);
		
		if (oldMuestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", muestra.getId().toString());		
				
		return repositoryBhc.save(muestra);
	}
	
	/*
	 * Tabla de muestras Dengue
	 * */
	public List<MxDengue> getMuestrasDengue() {
        return repositoryDengue.findAll();
    }
	
	public MxDengue saveMuestraDengue(MxDengue muestra) {
		return repositoryDengue.save(muestra);
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

	public MxDengue updateMuestraDengue(MxDengue muestra) {
		MxDengue oldMuestra = repositoryDengue.findById(muestra.getId()).orElse(null);
		
		if (oldMuestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", muestra.getId().toString());		
				
		return repositoryDengue.save(muestra);
	}
	
	/*
	 * Tabla de muestras Influenza
	 * */
	public List<MxInfluenza> getMuestrasInfluenza() {
        return repositoryInf.findAll();
    }
	
	public MxInfluenza saveMuestraInfluenza(MxInfluenza muestra) {
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

	public MxInfluenza updateMuestraInfluenza(MxInfluenza muestra) {
		MxInfluenza oldMuestra = repositoryInf.findById(muestra.getId()).orElse(null);
		
		if (oldMuestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", muestra.getId().toString());		
				
		return repositoryInf.save(muestra);
	}
	
	/*
	 * Tabla de muestras U01
	 * */
	public List<MxU01> getMuestrasU01() {
        return repositoryU01.findAll();
    }
	
	public MxU01 saveMuestraU01(MxU01 muestra) {
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

	public MxU01 updateMuestraU01(MxU01 muestra) {
		MxU01 oldMuestra = repositoryU01.findById(muestra.getId()).orElse(null);
		
		if (oldMuestra == null) throw new NotEntityFoundException(Muestra.class.getSimpleName(), "Id", muestra.getId().toString());		
				
		return repositoryU01.save(muestra);
	}
	
}
