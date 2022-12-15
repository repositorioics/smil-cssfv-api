package ni.org.ics.smil.cssfv.api.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ni.org.ics.smil.cssfv.api.entity.Muestra;
//import ni.org.ics.smil.cssfv.api.dto.MuestrasDTO;
import ni.org.ics.smil.cssfv.api.entity.MxInfluenza;

public interface MuestraRepository extends JpaRepository<Muestra, Long> {
	
	@Query(value = "SELECT Count(*) FROM muestras a " + 
			"WHERE a.codigo_participante = :codigoParticipante " +
			"AND a.mx_id = :id " +
			"AND a.anulada = false ", nativeQuery=true)
	long countBycodigoParticipanteAndMxIdId(
			@Param("codigoParticipante") Integer codigoParticipante, 
			@Param("id") Long id);
	
	long countBycodigoParticipanteAndFechaRegistroBetween(Integer codigoParticipante, Date startDate, Date endDate);
	
	/*Obtener las ultimas 7 muestras tomadas*/
	@Query(value = "SELECT * FROM muestras "
			+ "ORDER BY fecha_registro DESC LIMIT 7", nativeQuery=true)
	List<Muestra> findTopMuestras();
	
	/*Obtener el registro by cod_lab_scan*/
	@Query(value = "SELECT * FROM muestras a "
			+ "WHERE a.cod_lab_scan = :codLabScan", nativeQuery=true)
	Muestra findMxByCodLabScan(@Param("codLabScan") String codLabScan);
	
	/*Obtener el registro by cod_lab*/
	@Query(value = "SELECT * FROM muestras a "
			+ "WHERE a.cod_lab = :codLab", nativeQuery=true)
	Muestra findMxByCodLab(@Param("codLab") String codLab);
	
	/*Verificar si existe muestra correspondiente al numero de envio*/
	@Query(value = "SELECT count(*) "
			+ "FROM muestras a "
			+ "WHERE DATE_FORMAT(a.fecha_envio, '%Y-%m-%d') = CURDATE() "
			+ "AND viaje = :viaje", nativeQuery=true)
	long countMuestraByViaje(@Param("viaje") Integer viaje);
	
	@Query(value="SELECT * " /**a.id, a.codigo_participante, a.cod_lab, a.cod_lab_scan, a.fif, a.fecha_toma, a.retoma*/
			+ "FROM muestras a "
			+ "LEFT JOIN mx_influenza b ON b.muestra_id = a.id "
			+ "LEFT JOIN mx_transmision c ON c.muestra_id = a.id "
			+ "WHERE ((SELECT TIMESTAMPDIFF(DAY, a.fecha_toma, curdate()) AS dias_fechaToma) < 7) "
			+ "AND a.retoma = false "
			+ "AND a.mx_tomada = true "
			+ "AND a.anulada = false "
			+ "AND b.tipo_prueba_id <> 3 " /**PRI*/
			+ "ORDER BY a.fecha_toma DESC ", nativeQuery=true)
	List<Muestra> getMuestrasInfluenzaRetoma();
	
	/*Obtener las muestras por codigo*/
	@Query(value = "SELECT * FROM muestras a "
			+ "WHERE a.codigo_participante = :codigoParticipante "
			+ "ORDER BY a.fecha_toma DESC", nativeQuery=true)
	List<Muestra> findMxByCodigoParticipante(@Param("codigoParticipante") Integer codigo);
	
	/*Obtener las muestras PBMC pendientes de envio*/
	@Query(value = "SELECT a.id AS id, a.codigo_participante AS codigo, a.cod_lab AS codigoLab, a.estudios_participante AS estudios, "
			+ "a.fif AS fif, a.fecha_toma AS fechaToma, "
			+ "(SELECT descripcion FROM cat_tubos WHERE cat_tubos.id = b.tubo_id) AS tipoTuboTransm, "
			+ "(SELECT descripcion FROM cat_tubos WHERE cat_tubos.id = c.tubo_id) AS tipoTuboUO1 FROM muestras a "
			+ "LEFT JOIN mx_transmision AS b ON b.muestra_id = a.id "
			+ "LEFT JOIN mx_u01 AS c ON c.muestra_id = a.id "
			+ "LEFT JOIN mx_influenza AS d ON d.muestra_id = a.id "
			+ "WHERE (b.tipo_muestra_id = 8 OR c.tipo_muestra_id = 8 OR d.tipo_muestra_id = 8) "
			+ "AND a.anulada = false "
			+ "AND a.mx_enviada = false", nativeQuery=true)
	List<Map<String, Object>> findAllMxPBMCPendientesEnvio();
	
	/*Obtener las muestras de tubo ROJO pendientes de envio*/
	@Query(value = "SELECT a.id AS id, a.codigo_participante AS codigo, a.cod_lab AS codigoLab, a.estudios_participante AS estudios, "
			+ "a.fif AS fif, a.fecha_toma AS fechaToma, "
			+ "(SELECT descripcion FROM cat_tubos WHERE cat_tubos.id = b.tubo_id) AS tipoTuboTransm, "
			+ "(SELECT descripcion FROM cat_tubos WHERE cat_tubos.id = c.tubo_id) AS tipoTuboUO1 FROM muestras a "
			+ "LEFT JOIN mx_transmision AS b ON b.muestra_id = a.id "
			+ "LEFT JOIN mx_u01 AS c ON c.muestra_id = a.id "
			+ "LEFT JOIN mx_influenza AS d ON d.muestra_id = a.id "
			+ "WHERE (b.tipo_muestra_id = 12 OR c.tipo_muestra_id = 12 OR d.tipo_muestra_id = 12 OR "
			+ "b.tipo_muestra_id = 13 OR c.tipo_muestra_id = 13 OR d.tipo_muestra_id = 13) "
			+ "AND a.anulada = false "
			+ "AND a.mx_enviada = false", nativeQuery=true)
	List<Map<String, Object>> findAllMxTuboRojoPendientesEnvio();
	
	
	/*Obtener los HISOPADOS pendientes de envio*/
	@Query(value = "SELECT a.id AS id, a.codigo_participante AS codigo, a.cod_lab AS codigoLab, a.estudios_participante AS estudios, "
			+ "a.fif AS fif, a.fecha_toma AS fechaToma, "
			+ "(SELECT descripcion FROM cat_tubos WHERE cat_tubos.id = b.tubo_id) AS tipoTuboTransm, "
			+ "(SELECT descripcion FROM cat_tubos WHERE cat_tubos.id = c.tubo_id) AS tipoTuboUO1 FROM muestras a "
			+ "LEFT JOIN mx_transmision AS b ON b.muestra_id = a.id "
			+ "LEFT JOIN mx_u01 AS c ON c.muestra_id = a.id "
			+ "LEFT JOIN mx_influenza AS d ON d.muestra_id = a.id "
			+ "WHERE (b.tipo_muestra_id IN(1,2,3,4,5,6) OR c.tipo_muestra_id IN(1,2,3,4,5,6) OR d.tipo_muestra_id IN(1,2,3,4,5,6)) "
			+ "AND a.anulada = false "
			+ "AND a.mx_enviada = false", nativeQuery=true)
	List<Map<String, Object>> findAllHisopadosPendientesEnvio();
	
	/*Obtener las muestras enviadas del dia filtradas por el numero de envio*/
	@Query(value="SELECT a.id AS id, a.codigo_participante AS codigo, a.cod_lab AS codigoLab, a.estudios_participante AS estudios, "
			+ "a.fif AS fif, a.fecha_toma AS fechaToma, a.volumen AS volumen,  a.observacion, "
			+ "(SELECT descripcion FROM cat_tubos WHERE cat_tubos.id = b.tubo_id) AS tipoTuboTransm, "
			+ "(SELECT descripcion FROM cat_tubos WHERE cat_tubos.id = c.tubo_id) AS tipoTuboUO1 "
			+ "FROM muestras a "
			+ "LEFT JOIN mx_transmision AS b ON b.muestra_id = a.id "
			+ "LEFT JOIN mx_u01 AS c ON c.muestra_id = a.id "
			+ "LEFT JOIN mx_influenza AS d ON d.muestra_id = a.id "
			+ "WHERE a.mx_enviada = true "
			+ "AND DATE_FORMAT(a.fecha_envio, '%Y-%m-%d') = CURDATE() "
			+ "AND a.viaje = :viaje" ,nativeQuery=true) 
	List<Map<String, Object>> findAllMuestrasEnviadas(@Param("viaje") Integer viaje);
	
	/*Obtener las muestras PBMC enviadas por dia y rango de fecha*/
	@Query(value="SELECT a.id AS id, a.codigo_participante AS codigo, a.cod_lab AS codigoLab, a.estudios_participante AS estudios, "
			+ "a.fif AS fif, a.fecha_toma AS fechaToma, a.volumen AS volumen,  a.observacion, "
			+ "(SELECT descripcion FROM cat_tubos WHERE cat_tubos.id = b.tubo_id) AS tipoTuboTransm, "
			+ "(SELECT descripcion FROM cat_tubos WHERE cat_tubos.id = c.tubo_id) AS tipoTuboUO1 FROM muestras a "
			+ "LEFT JOIN mx_transmision AS b ON b.muestra_id = a.id "
			+ "LEFT JOIN mx_u01 AS c ON c.muestra_id = a.id "
			+ "LEFT JOIN mx_influenza AS d ON d.muestra_id = a.id "
			+ "WHERE (b.tipo_muestra_id = 8 OR c.tipo_muestra_id = 8 OR d.tipo_muestra_id = 8) "
			+ "AND a.anulada = false AND a.mx_enviada = true "
			+ "AND a.fecha_envio BETWEEN :fechaInicio AND :fechaFin  "
			+ "AND a.viaje = :viaje", nativeQuery=true)
	List<Map<String, Object>> findAllPBMCEnviadasByRangeDateAndViaje(
			@Param("fechaInicio") Date fechaInicio, 
			@Param("fechaFin") Date fechaFin, 
			@Param("viaje") Integer viaje);
	
	/*Obtener las muestras ROJO O SEROLOGIA enviadas por dia y rango de fecha*/
	@Query(value="SELECT a.id AS id, a.codigo_participante AS codigo, a.cod_lab AS codigoLab, a.estudios_participante AS estudios, "
			+ "a.fif AS fif, a.fecha_toma AS fechaToma, a.volumen AS volumen,  a.observacion, "
			+ "(SELECT descripcion FROM cat_tubos WHERE cat_tubos.id = b.tubo_id) AS tipoTuboTransm, "
			+ "(SELECT descripcion FROM cat_tubos WHERE cat_tubos.id = c.tubo_id) AS tipoTuboUO1 FROM muestras a "
			+ "LEFT JOIN mx_transmision AS b ON b.muestra_id = a.id "
			+ "LEFT JOIN mx_u01 AS c ON c.muestra_id = a.id "
			+ "LEFT JOIN mx_influenza AS d ON d.muestra_id = a.id "
			+ "WHERE (b.tipo_muestra_id = 12 OR c.tipo_muestra_id = 12 OR d.tipo_muestra_id = 12 OR "
			+ "b.tipo_muestra_id = 13 OR c.tipo_muestra_id = 13 OR d.tipo_muestra_id = 13) "
			+ "AND a.anulada = false AND a.mx_enviada = true "
			+ "AND a.fecha_envio BETWEEN :fechaInicio AND :fechaFin  "
			+ "AND a.viaje = :viaje", nativeQuery=true)
	List<Map<String, Object>> findAllROJOEnviadasByRangeDateAndViaje(
			@Param("fechaInicio") Date fechaInicio, 
			@Param("fechaFin") Date fechaFin, 
			@Param("viaje") Integer viaje);
	
	/*Obtener los HISOPADOS enviados por dia y rango de fecha*/
	@Query(value="SELECT a.id AS id, a.codigo_participante AS codigo, a.cod_lab AS codigoLab, a.estudios_participante AS estudios, "
			+ "a.fif AS fif, a.fecha_toma AS fechaToma, a.volumen AS volumen,  a.observacion, "
			+ "(SELECT descripcion FROM cat_tubos WHERE cat_tubos.id = b.tubo_id) AS tipoTuboTransm, "
			+ "(SELECT descripcion FROM cat_tubos WHERE cat_tubos.id = c.tubo_id) AS tipoTuboUO1 FROM muestras a "
			+ "LEFT JOIN mx_transmision AS b ON b.muestra_id = a.id "
			+ "LEFT JOIN mx_u01 AS c ON c.muestra_id = a.id "
			+ "LEFT JOIN mx_influenza AS d ON d.muestra_id = a.id "
			+ "WHERE (b.tipo_muestra_id IN(1,2,3,4,5,6) OR c.tipo_muestra_id IN(1,2,3,4,5,6) OR d.tipo_muestra_id IN(1,2,3,4,5,6)) "
			+ "AND a.anulada = false AND a.mx_enviada = true "
			+ "AND a.fecha_envio BETWEEN :fechaInicio AND :fechaFin "
			+ "AND a.viaje = :viaje", nativeQuery=true)
	List<Map<String, Object>> findAllHISOPADOSEnviadosByRangeDateAndViaje(
			@Param("fechaInicio") Date dFecha1, 
			@Param("fechaFin") Date dFecha2, 
			@Param("viaje") Integer viaje);
}
