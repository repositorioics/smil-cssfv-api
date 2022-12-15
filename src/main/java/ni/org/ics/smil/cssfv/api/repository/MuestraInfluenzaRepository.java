package ni.org.ics.smil.cssfv.api.repository;

import java.util.List;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ni.org.ics.smil.cssfv.api.entity.MxDengue;
import ni.org.ics.smil.cssfv.api.entity.MxInfluenza;

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
	
	/*
	 * @Query(value="SELECT * from mx_influenza a, muestras b " +
	 * "WHERE a.muestra_id = b.id " + "AND b.mx_id = :id " +
	 * "AND b.mx_enviada = false " + "AND b.anulada = false", nativeQuery=true)
	 * List<MxInfluenza> getMuestrasInfluenzaPendientesEnvio(
	 * 
	 * @Param("id") Long id);
	 */
	
	@Query(value="SELECT b.cod_lab "
			+ "FROM mx_influenza a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigo "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.anulada = false "
			+ "ORDER BY a.id DESC limit 1", nativeQuery=true)
	String findMxInfluenzaByCode(
			@Param("codigo") Integer codigo, 
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT * FROM mx_influenza a , muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cod_lab_scan = :codLabScan "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.anulada = false ", nativeQuery=true)
	MxInfluenza findMxInfluenzaByCodLabScan(
			@Param("codLabScan") String codLabScan, 
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT * FROM mx_influenza a,  muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.id = :idMuestra ", nativeQuery=true)
	MxInfluenza findMxInfluenzaByIdMuestra(
			@Param("idMuestra") Long idMuestra);
}
