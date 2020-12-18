package ni.org.ics.smil.cssfv.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ni.org.ics.smil.cssfv.api.entity.MxBhc;


public interface MuestraBhcRepository extends JpaRepository<MxBhc, Long> {
	
	List<MxBhc> findByMuestraIdCodigoParticipante(Integer codigoParticipante);
	
	List<MxBhc> findByMuestraIdFechaTomaBetween(Date fecha1, Date fecha2);
	
	List<MxBhc> findByMuestraIdCodigoParticipanteAndMuestraIdFechaTomaBetween(Integer codigoParticipante, Date fecha1, Date fecha2);

}
