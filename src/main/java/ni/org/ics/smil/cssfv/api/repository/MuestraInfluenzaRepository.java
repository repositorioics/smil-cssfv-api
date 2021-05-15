package ni.org.ics.smil.cssfv.api.repository;

import java.util.List;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ni.org.ics.smil.cssfv.api.entity.MxBhc;
import ni.org.ics.smil.cssfv.api.entity.MxInfluenza;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;

public interface MuestraInfluenzaRepository extends JpaRepository<MxInfluenza, Long> {
	
	List<MxInfluenza> findByMuestraIdCodigoParticipante(Integer codigoParticipante);
	
	@Query(value = "SELECT * FROM mx_influenza a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.fecha_registro >= :startDate "
			+ "AND b.fecha_registro <= :endDate ", nativeQuery=true)
	List<MxInfluenza> findByMuestraIdFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	List<MxInfluenza> findByMuestraIdCodigoParticipanteAndMuestraIdFechaTomaBetween(Integer codigoParticipante, Date fecha1, Date fecha2);
	
	@Query(value = "SELECT * FROM mx_influenza a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigoParticipante "
			+ "AND b.anulada = false "
			+ "ORDER BY b.fecha_toma DESC LIMIT 1", nativeQuery=true)
	MxInfluenza findTopByOrderByMuestraIdCodigoParticipanteDesc(
			@Param("codigoParticipante") Integer codigoParticipante);
}
