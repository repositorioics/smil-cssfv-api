package ni.org.ics.smil.cssfv.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ni.org.ics.smil.cssfv.api.entity.MxDengue;
import ni.org.ics.smil.cssfv.api.entity.MxInfluenza;
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
	
	/*@Query(value="SELECT * from mx_transmision a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.mx_id = :id "
			+ "AND b.mx_enviada = false "
			+ "AND b.anulada = false", nativeQuery=true)
	List<MxTransmision> getMuestrasTransmisionPendientesEnvio(
			@Param("id") Long id);*/
	
	/*
	 * @Query(
	 * value="SELECT * FROM mx_transmision a, muestras b, cat_recepcion c, cat_tipo_muestras d, cat_envio_muestras e "
	 * + "WHERE a.muestra_id = b.id " + "AND b.cat_recepcion_id = c.id " +
	 * "AND c.cat_tipo_muestra_id = d.id " + "AND c.cat_envio_muestra_id = e.id " +
	 * "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') = CURDATE() " +
	 * "AND e.id = :id " + "AND b.mx_enviada = false", nativeQuery=true)
	 * List<MxTransmision> getMuestrasTransmisionPendientesEnvio(@Param("id") Long
	 * id);
	 */
	
	/*
	 * @Query(
	 * value="SELECT * FROM mx_transmision a, muestras b, cat_recepcion c, cat_tipo_muestras d, cat_envio_muestras e "
	 * + "WHERE a.muestra_id = b.id " + "AND b.cat_recepcion_id = c.id " +
	 * "AND c.cat_tipo_muestra_id = d.id " + "AND c.cat_envio_muestra_id = e.id " +
	 * "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') = CURDATE() " +
	 * "AND e.id = :id " + "AND b.viaje = :viaje " + "AND b.mx_enviada = true",
	 * nativeQuery=true) List<MxTransmision>
	 * getMuestrasTransmisionEnviadas(@Param("id") Long id, @Param("viaje") Integer
	 * viaje);
	 * 
	 * @Query(
	 * value="SELECT * FROM mx_transmision a, muestras b, cat_recepcion c, cat_tipo_muestras d, cat_envio_muestras e "
	 * + "WHERE a.muestra_id = b.id " + "AND b.cat_recepcion_id = c.id " +
	 * "AND c.cat_tipo_muestra_id = d.id " + "AND c.cat_envio_muestra_id = e.id " +
	 * "AND e.id = :id " + "AND b.viaje = :viaje " +
	 * "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= DATE_FORMAT(:startDate, '%Y-%m-%d') "
	 * +
	 * "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= DATE_FORMAT(:endDate, '%Y-%m-%d') "
	 * + "AND b.mx_enviada = true", nativeQuery=true) List<MxTransmision>
	 * getMuestrasTransmisionEnviadasRangoFecha(@Param("id") Long
	 * id, @Param("viaje") Integer viaje,
	 * 
	 * @Param("startDate") Date startDate,
	 * 
	 * @Param("endDate") Date endDate);
	 */
	
	/*Monitoreo Intensivo Influenza PBMC
	@Query(value="SELECT * FROM mx_transmision a, muestras b, cat_recepcion c, cat_tipo_muestras d "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND c.cat_tipo_muestra_id = d.id "
			+ "AND b.cat_recepcion_id IN (22,23) "
			+ "AND b.fecha_registro = CURDATE() "
			+ "AND b.mx_enviada = false", nativeQuery=true)
	List<MxTransmision> getMxMonitoreoIntensivoPBMCPendientesEnvio();
	
	/*Monitoreo Intensivo Influenza ROJO
	@Query(value="SELECT * FROM mx_transmision a, muestras b, cat_recepcion c, cat_tipo_muestras d "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND c.cat_tipo_muestra_id = d.id "
			+ "AND b.cat_recepcion_id IN (24,25) "
			+ "AND b.fecha_registro = CURDATE() "
			+ "AND b.mx_enviada = false", nativeQuery=true)
	List<MxTransmision> getMxMonitoreoIntensivoRojoPendientesEnvio();
	
	/*Monitoreo Intensivo Covid-19 PBMC
	@Query(value="SELECT * FROM mx_transmision a, muestras b, cat_recepcion c, cat_tipo_muestras d "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND c.cat_tipo_muestra_id = d.id "
			+ "AND b.cat_recepcion_id IN (35,36) "
			+ "AND b.fecha_registro = CURDATE() "
			+ "AND b.mx_enviada = false", nativeQuery=true)
	List<MxTransmision> getMxCovidPbmcPendientesEnvio();
	
	/*Monitoreo Intensivo Covid-19 ROJO
	@Query(value="SELECT * FROM mx_transmision a, muestras b, cat_recepcion c, cat_tipo_muestras d "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND c.cat_tipo_muestra_id = d.id "
			+ "AND b.cat_recepcion_id IN (37,38) "
			+ "AND b.fecha_registro = CURDATE() "
			+ "AND b.mx_enviada = false", nativeQuery=true)
	List<MxTransmision> getMxCovidRojoPendientesEnvio();
	
	/*Hisopados Covid-19
	@Query(value="SELECT * FROM mx_transmision a, muestras b, cat_recepcion c, cat_tipo_muestras d "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND c.cat_tipo_muestra_id = d.id "
			+ "AND b.cat_recepcion_id = 34 "
			+ "AND b.fecha_registro = CURDATE() "
			+ "AND b.mx_enviada = false", nativeQuery=true)
	List<MxTransmision> getMxHisopadosCovidPendientesEnvio();
	
	/*Hisopados Monitoreo Intensivo
	@Query(value="SELECT * FROM mx_transmision a, muestras b, cat_recepcion c, cat_tipo_muestras d "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND c.cat_tipo_muestra_id = d.id "
			+ "AND b.cat_recepcion_id = 21 "
			+ "AND b.fecha_registro = CURDATE() "
			+ "AND b.mx_enviada = false", nativeQuery=true)
	List<MxTransmision> getHisopadosMonitoreoIntensivoPendientesEnvio();*/
	
	@Query(value="SELECT b.cod_lab "
			+ "FROM mx_transmision a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigo "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.anulada = false "
			+ "ORDER BY a.id DESC limit 1", nativeQuery=true)
	String findMxTransmisionByCode(
			@Param("codigo") Integer codigo, 
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT * FROM mx_transmision a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cod_lab_scan = :codLabScan "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.anulada = false ", nativeQuery=true)
	MxTransmision findMxTransmisionByCodLabScan(
			@Param("codLabScan") String codLabScan, 
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT * FROM mx_transmision a,  muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.id = :idMuestra ", nativeQuery=true)
	MxTransmision findMxTransmisionByIdMuestra(
			@Param("idMuestra") Long idMuestra);
}
