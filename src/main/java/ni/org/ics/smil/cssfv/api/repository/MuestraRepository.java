package ni.org.ics.smil.cssfv.api.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ni.org.ics.smil.cssfv.api.entity.Muestra;

public interface MuestraRepository extends JpaRepository<Muestra, Long> {
	
	@Query(value = "SELECT Count(*) FROM muestras a " + 
			"WHERE a.codigo_participante = :codigoParticipante " +
			"AND a.mx_id = :id " +
			"AND a.anulada = false ", nativeQuery=true)
	long countBycodigoParticipanteAndMxIdId(
			@Param("codigoParticipante") Integer codigoParticipante, 
			@Param("id") Long id);
	
	long countBycodigoParticipanteAndFechaRegistroBetween(Integer codigoParticipante, Date startDate, Date endDate);
}
