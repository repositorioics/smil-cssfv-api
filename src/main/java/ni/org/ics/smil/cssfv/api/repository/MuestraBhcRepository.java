package ni.org.ics.smil.cssfv.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ni.org.ics.smil.cssfv.api.entity.MxBhc;
import ni.org.ics.smil.cssfv.api.entity.MxDengue;
import ni.org.ics.smil.cssfv.api.entity.MxU01;

public interface MuestraBhcRepository extends JpaRepository<MxBhc, Long> {
	
	List<MxBhc> findByMuestraIdCodigoParticipante(Integer codigoParticipante);
	
	@Query(value = "SELECT * FROM mx_bhc a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.fecha_registro >= :startDate "
			+ "AND b.fecha_registro <= :endDate ", nativeQuery=true)
	List<MxBhc> findByMuestraIdFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	List<MxBhc> findByMuestraIdFechaTomaBetween(Date fecha1, Date fecha2);
	
	List<MxBhc> findByMuestraIdCodigoParticipanteAndMuestraIdFechaTomaBetween(Integer codigoParticipante, Date fecha1, Date fecha2);
	
	/*
	 * @Query(value="SELECT * from mx_bhc a, muestras b " +
	 * "WHERE a.muestra_id = b.id " + "AND b.mx_id = :id " +
	 * "AND b.mx_enviada = false " + "AND b.anulada = false", nativeQuery=true)
	 * List<MxBhc> getMuestrasBhcPendientesEnvio(
	 * 
	 * @Param("id") Long id);
	 */
	
	/*
	 * @Query(
	 * value="SELECT * FROM mx_bhc a, muestras b, cat_recepcion c, cat_tipo_muestras d, cat_envio_muestras e "
	 * + "WHERE a.muestra_id = b.id " + "AND b.cat_recepcion_id = c.id " +
	 * "AND c.cat_tipo_muestra_id = d.id " + "AND c.cat_envio_muestra_id = e.id " +
	 * "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') = CURDATE() " +
	 * "AND e.id = :id " + "AND b.mx_enviada = false", nativeQuery=true) List<MxBhc>
	 * getMuestrasBhcPendientesEnvio(@Param("id") Long id);
	 */
	
	/*
	 * @Query(
	 * value="SELECT * FROM mx_bhc a, muestras b, cat_recepcion c, cat_tipo_muestras d, cat_envio_muestras e "
	 * + "WHERE a.muestra_id = b.id " + "AND b.cat_recepcion_id = c.id " +
	 * "AND c.cat_tipo_muestra_id = d.id " + "AND c.cat_envio_muestra_id = e.id " +
	 * "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') = CURDATE() " +
	 * "AND e.id = :id " + "AND b.viaje = :viaje " + "AND b.mx_enviada = true",
	 * nativeQuery=true) List<MxBhc> getMuestrasBhcEnviadas(@Param("id") Long
	 * id, @Param("viaje") Integer viaje);
	 * 
	 * @Query(
	 * value="SELECT * FROM mx_bhc a, muestras b, cat_recepcion c, cat_tipo_muestras d, cat_envio_muestras e "
	 * + "WHERE a.muestra_id = b.id " + "AND b.cat_recepcion_id = c.id " +
	 * "AND c.cat_tipo_muestra_id = d.id " + "AND c.cat_envio_muestra_id = e.id " +
	 * "AND e.id = :id " + "AND b.viaje = :viaje " +
	 * "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= DATE_FORMAT(:startDate, '%Y-%m-%d') "
	 * +
	 * "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= DATE_FORMAT(:endDate, '%Y-%m-%d') "
	 * + "AND b.mx_enviada = true", nativeQuery=true) List<MxBhc>
	 * getMuestrasBhcEnviadasRangoFecha(@Param("id") Long id, @Param("viaje")
	 * Integer viaje,
	 * 
	 * @Param("startDate") Date startDate,
	 * 
	 * @Param("endDate") Date endDate);
	 */
	
	@Query(value="SELECT b.cod_lab "
			+ "FROM mx_bhc a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigo "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.anulada = false "
			+ "ORDER BY a.id DESC limit 1", nativeQuery=true)
	String findMxBHCByCode(
			@Param("codigo") Integer codigo, 
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT * FROM mx_bhc a , muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cod_lab_scan = :codLabScan "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.anulada = false ", nativeQuery=true)
	MxBhc findMxBHCByCodLabScan(
			@Param("codLabScan") String codLabScan, 
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT * FROM mx_bhc a,  muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.id = :idMuestra ", nativeQuery=true)
	MxBhc findMxBHCByIdMuestra(
			@Param("idMuestra") Long idMuestra);

}
