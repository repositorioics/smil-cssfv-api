package ni.org.ics.smil.cssfv.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ni.org.ics.smil.cssfv.api.entity.MxDengue;
import ni.org.ics.smil.cssfv.api.entity.MxU01;

public interface MuestraU01Repository extends JpaRepository<MxU01, Long> {
	
	List<MxU01> findByMuestraIdCodigoParticipante(Integer codigoParticipante);
	
	@Query(value = "SELECT * FROM mx_U01 a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.fecha_registro >= :startDate "
			+ "AND b.fecha_registro <= :endDate ", nativeQuery=true)
	List<MxU01> findByMuestraIdFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	List<MxU01> findByMuestraIdCodigoParticipanteAndMuestraIdFechaTomaBetween(Integer codigoParticipante, Date fecha1, Date fecha2);
	
	@Query(value="SELECT * FROM mx_U01 a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.mx_id = :id "
			+ "AND b.mx_enviada = false "
			+ "AND b.anulada = false", nativeQuery=true)
	List<MxU01> getMuestrasUO1PendientesEnvio(
			@Param("id") Long id);
	
	@Query(value="SELECT a.cod_lab FROM mx_u01 a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigo "
			+ "ORDER BY a.id DESC limit 1", nativeQuery=true)
	String findMxUO1ByCode(
			@Param("codigo") Integer codigo);

}
