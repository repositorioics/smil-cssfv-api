package ni.org.ics.smil.cssfv.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ni.org.ics.smil.cssfv.api.entity.MxTransmision;
import ni.org.ics.smil.cssfv.api.entity.MxU01;

public interface MuestraTransmisionRepository extends JpaRepository<MxTransmision, Long> {
	
	@Query(value="SELECT * FROM mx_transmision a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.mx_id = :mxId", nativeQuery=true)
	List<MxTransmision> getListTransmision(
			@Param("mxId") Long mxId);
	
	List<MxTransmision> findByMuestraIdCodigoParticipanteAndMuestraIdMxId(Integer codigoParticipante, Long id);
	
	@Query(value = "SELECT * FROM mx_transmision a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.fecha_registro >= :startDate "
			+ "AND b.fecha_registro <= :endDate "
			+ "AND b.mx_id = :mxId", nativeQuery=true)
	List<MxTransmision> findByMuestraIdFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("mxId") Long id);
	
	List<MxTransmision> findByMuestraIdCodigoParticipanteAndMuestraIdMxIdIdAndMuestraIdFechaTomaBetween(
			Integer codigoParticipante, Long id, Date fecha1, Date fecha2);
	
	@Query(value="SELECT * from mx_transmision a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.mx_id = :id "
			+ "AND b.mx_enviada = false "
			+ "AND b.anulada = false", nativeQuery=true)
	List<MxTransmision> getMuestrasTransmisionPendientesEnvio(
			@Param("id") Long id);
	
	@Query(value="SELECT a.cod_lab "
			+ "FROM mx_transmision a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigo "
			+ "ORDER BY a.id DESC limit 1", nativeQuery=true)
	String findMxTransmisionByCode(
			@Param("codigo") Integer codigo);
}
