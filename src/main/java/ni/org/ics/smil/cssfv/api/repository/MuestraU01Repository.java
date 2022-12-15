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
	
	/*@Query(value="SELECT * FROM mx_U01 a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.mx_id = :id "
			+ "AND b.mx_enviada = false "
			+ "AND b.anulada = false", nativeQuery=true)
	List<MxU01> getMuestrasUO1PendientesEnvio(
			@Param("id") Long id);*/
	
	/*
	 * @Query(
	 * value="SELECT * FROM mx_u01 a, muestras b, cat_recepcion c, cat_tipo_muestras d, cat_envio_muestras e "
	 * + "WHERE a.muestra_id = b.id " + "AND b.cat_recepcion_id = c.id " +
	 * "AND c.cat_tipo_muestra_id = d.id " + "AND c.cat_envio_muestra_id = e.id " +
	 * "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') = CURDATE() " +
	 * "AND e.id = :id " + "AND b.mx_enviada = false", nativeQuery=true) List<MxU01>
	 * getMuestrasUO1PendientesEnvio(@Param("id") Long id);
	 */
	
	/*
	 * @Query(
	 * value="SELECT * FROM mx_u01 a, muestras b, cat_recepcion c, cat_tipo_muestras d, cat_envio_muestras e "
	 * + "WHERE a.muestra_id = b.id " + "AND b.cat_recepcion_id = c.id " +
	 * "AND c.cat_tipo_muestra_id = d.id " + "AND c.cat_envio_muestra_id = e.id " +
	 * "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') = CURDATE() " +
	 * "AND e.id = :id " + "AND b.viaje = :viaje " + "AND b.mx_enviada = true",
	 * nativeQuery=true) List<MxU01> getMuestrasUO1Enviadas(@Param("id") Long
	 * id, @Param("viaje") Integer viaje);
	 * 
	 * @Query(
	 * value="SELECT * FROM mx_u01 a, muestras b, cat_recepcion c, cat_tipo_muestras d, cat_envio_muestras e "
	 * + "WHERE a.muestra_id = b.id " + "AND b.cat_recepcion_id = c.id " +
	 * "AND c.cat_tipo_muestra_id = d.id " + "AND c.cat_envio_muestra_id = e.id " +
	 * "AND e.id = :id " + "AND b.viaje = :viaje " +
	 * "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= DATE_FORMAT(:startDate, '%Y-%m-%d') "
	 * +
	 * "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= DATE_FORMAT(:endDate, '%Y-%m-%d') "
	 * + "AND b.mx_enviada = true", nativeQuery=true) List<MxU01>
	 * getMuestrasUO1EnviodasRangoFecha(@Param("id") Long id, @Param("viaje")
	 * Integer viaje,
	 * 
	 * @Param("startDate") Date startDate,
	 * 
	 * @Param("endDate") Date endDate);
	 */
	
	/*@Query(value="SELECT * FROM mx_u01 a, muestras b, cat_recepcion c, cat_tipo_muestras d "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND c.cat_tipo_muestra_id = d.id "
			+ "AND b.cat_recepcion_id IN (26,27,28,29) "
			+ "AND b.fecha_registro = CURDATE() "
			+ "AND b.mx_enviada = false", nativeQuery=true)
	List<MxU01> getMuestrasUO1PendientesEnvio();
	
	@Query(value="SELECT * FROM mx_u01 a, muestras b, cat_recepcion c, cat_tipo_muestras d "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND c.cat_tipo_muestra_id = d.id "
			+ "AND b.cat_recepcion_id IN (30,31,32,33) "
			+ "AND b.fecha_registro = CURDATE() "
			+ "AND b.mx_enviada = false", nativeQuery=true)
	List<MxU01> getMuestrasVacunasUO1PendientesEnvio();*/
	
	@Query(value="SELECT b.cod_lab FROM mx_u01 a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigo "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.anulada = false "
			+ "ORDER BY a.id DESC limit 1", nativeQuery=true)
	String findMxUO1ByCode(
			@Param("codigo") Integer codigo, 
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT * FROM mx_U01 a,  muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cod_lab_scan = :codLabScan "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.anulada = false ", nativeQuery=true)
	MxU01 findMxU01ByCodLabScan(
			@Param("codLabScan") String codLabScan, 
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT * FROM mx_U01 a,  muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.id = :idMuestra ", nativeQuery=true)
	MxU01 findMxU01ByIdMuestra(
			@Param("idMuestra") Long idMuestra);

}
