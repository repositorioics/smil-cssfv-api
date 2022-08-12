package ni.org.ics.smil.cssfv.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ni.org.ics.smil.cssfv.api.entity.MxDengue;

public interface MuestraDengueRepository extends JpaRepository<MxDengue, Long> {

	//List<MxDengue> findByMuestraIdCodigoParticipante(Integer codigoParticipante);
	
	@Query(value = "SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigoParticipante", nativeQuery=true)
	List<MxDengue> findMuestrasDengueByCodigoParticipante(
			@Param("codigoParticipante") Integer codigoParticipante);
	
	@Query(value = "SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.mx_tomada = true "
			//+ "AND b.fecha_registro >= :startDate "
			//+ "AND b.fecha_registro <= :endDate "
			+ "AND b.fecha_toma BETWEEN :startDate AND :endDate ", nativeQuery=true)
	List<MxDengue> findAllMuestrasDengueFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	/******BUSQUEDAS DE TODAS LAS MUESTRAS DENGUE POR CODIGO PARTICIPANTE***********/
	/*Busqueda muestras Dengue Hematicas por codigo participante*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND c.cat_tipo_muestra_id = 12 "
			+ "AND c.estudio = 3 "
			+ "AND b.codigo_participante = :codigoParticipante " , nativeQuery=true)
	List<MxDengue> findMuestrasDengueHematicasByCodigoParticipante(
			@Param("codigoParticipante") Integer codigoParticipante);
	
	/*Busqueda muestras Dengue BCH por codigo participante*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND c.cat_tipo_muestra_id = 9 "
			+ "AND c.estudio = 3 "
			+ "AND b.codigo_participante = :codigoParticipante ", nativeQuery=true)
	List<MxDengue> findMuestrasDengueBHCByCodigoParticipante(
			@Param("codigoParticipante") Integer codigoParticipante);
	
	/*Busqueda muestras Dengue Paxgene por codigo participante*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND c.cat_tipo_muestra_id = 7 "
			+ "AND c.estudio = 3 "
			+ "AND b.codigo_participante = :codigoParticipante ", nativeQuery=true)
	List<MxDengue> findMuestrasDenguePaxgeneByCodigoParticipante(
			@Param("codigoParticipante") Integer codigoParticipante);
	
	/*Busqueda muestras Dengue Metabolomicas por codigo participante*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND c.estudio = 3 "
			+ "AND b.codigo_participante = :codigoParticipante "
			+ "AND c.cat_tipo_muestra_id = 10 "
			+ "OR c.cat_tipo_muestra_id = 11 ", nativeQuery=true)
	List<MxDengue> findMuestrasDengueMetabolomicasByCodigoParticipante(
			@Param("codigoParticipante") Integer codigoParticipante);
	
	/*Busqueda muestras Dengue PBMC por codigo participante*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND c.cat_tipo_muestra_id = 8 "
			+ "AND c.estudio = 3 "
			+ "AND b.codigo_participante = :codigoParticipante ", nativeQuery=true)
	List<MxDengue> findMuestrasDenguePBMCByCodigoParticipante(
			@Param("codigoParticipante") Integer codigoParticipante);
	
	/*******BUSQUEDA MUESTRAS DENGUE POR RANGO DE FECHA**********/
	/*Busqueda muestras Dengue Hematicas por rango de fecha*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			//+ "AND b.fecha_registro >= :startDate "
			//+ " :endDate "
			+ "AND b.fecha_toma BETWEEN :startDate AND :endDate "
			+ "AND c.cat_tipo_muestra_id = 12 "
			+ "AND c.estudio = 3 ", nativeQuery=true)
	List<MxDengue> findByMuestrasHematicasFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	/*Busqueda muestras Dengue Metabolomica por rango de fecha*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			//+ "AND b.fecha_registro >= :startDate "
			//+ "AND b.fecha_registro <= :endDate "
			+ "AND b.fecha_toma BETWEEN :startDate AND :endDate "
			+ "AND c.estudio = 3 "
			+ "AND c.cat_tipo_muestra_id IN(10, 11) ", nativeQuery=true)
	List<MxDengue> findByMuestrasMetabolomicasFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	/*Busqueda muestras Dengue BHC por rango de fecha*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			//+ "AND b.fecha_registro >= :startDate "
			//+ "AND b.fecha_registro <= :endDate "
			+ "AND b.fecha_toma BETWEEN :startDate AND :endDate "
			+ "AND c.cat_tipo_muestra_id = 9 "
			+ "AND c.estudio = 3 ", nativeQuery=true)
	List<MxDengue> findByMuestrasBHCFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	/*Busqueda muestras Dengue PBMC por rango de fecha*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			//+ "AND b.fecha_registro >= :startDate "
			//+ "AND b.fecha_registro <= :endDate "
			+ "AND b.fecha_toma BETWEEN :startDate AND :endDate "
			+ "AND c.cat_tipo_muestra_id = 8 "
			+ "AND c.estudio = 3 ", nativeQuery=true)
	List<MxDengue> findByMuestrasPBMCFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	/*Busqueda muestras Dengue PaxGene por rango de fecha*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			//+ "AND b.fecha_registro >= :startDate "
			//+ "AND b.fecha_registro <= :endDate "
			+ "AND b.fecha_toma BETWEEN :startDate AND :endDate "
			+ "AND c.cat_tipo_muestra_id = 7 "
			+ "AND c.estudio = 3 ", nativeQuery=true)
	List<MxDengue> findByMuestrasPaxGeneFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	List<MxDengue> findByMuestraIdCodigoParticipanteAndMuestraIdFechaTomaBetween(Integer codigoParticipante, Date fecha1, Date fecha2);
	
	/******BUSQUEDA MUESTRAS DENGUE POR CODIGO Y RANGO DE FECHA***********/
	/*Busqueda muestras Dengue Hematicas por codigo y rango de fecha*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND b.codigo_participante = :codigoParticipante "
			//+ "AND b.fecha_registro >= :startDate "
			//+ "AND b.fecha_registro <= :endDate "
			+ "AND b.fecha_toma BETWEEN :startDate AND :endDate "
			+ "AND c.cat_tipo_muestra_id = 12 "
			+ "AND c.estudio = 3 ", nativeQuery=true)
	List<MxDengue> findByMuestrasHematicasCodigoFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("codigoParticipante") Integer codigoParticipante);
	
	/*Busqueda muestras Dengue Metabolomica*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND b.codigo_participante = :codigoParticipante "
			//+ "AND b.fecha_registro >= :startDate "
			//+ "AND b.fecha_registro <= :endDate "
			+ "AND b.fecha_toma BETWEEN :startDate AND :endDate "
			+ "AND c.estudio = 3 "
			+ "AND c.cat_tipo_muestra_id IN(10, 11)", nativeQuery=true)
	List<MxDengue> findByMuestrasMetabolomicasCodigoFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("codigoParticipante") Integer codigoParticipante);
	
	/*Busqueda muestras Dengue BHC*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND b.codigo_participante = :codigoParticipante "
			//+ "AND b.fecha_registro >= :startDate "
			//+ "AND b.fecha_registro <= :endDate "
			+ "AND b.fecha_toma BETWEEN :startDate AND :endDate "
			+ "AND c.cat_tipo_muestra_id = 9 "
			+ "AND c.estudio = 3 ", nativeQuery=true)
	List<MxDengue> findByMuestrasBHCCodigoFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("codigoParticipante") Integer codigoParticipante);
	
	/*Busqueda muestras Dengue PBMC*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND b.codigo_participante = :codigoParticipante "
			//+ "AND b.fecha_registro >= :startDate "
			//+ "AND b.fecha_registro <= :endDate "
			+ "AND b.fecha_toma BETWEEN :startDate AND :endDate "
			+ "AND c.cat_tipo_muestra_id = 8 "
			+ "AND c.estudio = 3 ", nativeQuery=true)
	List<MxDengue> findByMuestrasPBMCCodigoFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("codigoParticipante") Integer codigoParticipante);
	
	/*Busqueda muestras Dengue PaxGene*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND b.codigo_participante = :codigoParticipante "
			//+ "AND b.fecha_registro >= :startDate "
			//+ "AND b.fecha_registro <= :endDate "
			+ "AND b.fecha_toma BETWEEN :startDate AND :endDate "
			+ "AND c.cat_tipo_muestra_id = 7 "
			+ "AND c.estudio = 3 ", nativeQuery=true)
	List<MxDengue> findByMuestrasPaxGeneCodigoFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("codigoParticipante") Integer codigoParticipante);
	
	
	/*@Query(value="SELECT * from mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "and b.mx_id = :id "
			+ "and b.mx_enviada = false "
			+ "and b.anulada = false", nativeQuery=true)
	List<MxDengue> getMuestrasDenguePendientesEnvio(
			@Param("id") Long id);*/
	
	@Query(value="SELECT * FROM mx_dengue a, muestras b, cat_recepcion c, cat_tipo_muestras d, cat_envio_muestras e "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND c.cat_tipo_muestra_id = d.id "
			+ "AND c.cat_envio_muestra_id = e.id "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') = CURDATE() "
			+ "AND e.id = :id "
			+ "AND b.mx_enviada = false;", nativeQuery=true)
	List<MxDengue> getMuestrasDenguePendientesEnvio(@Param("id") Long id);
	
	/******OBTENER LAS MUESTRAS DENGUE DEL DIA*********/
	@Query(value="SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') = CURDATE() "
			+ "AND c.estudio = 3 "
			+ "AND c.cat_tipo_muestra_id IN(10, 11) ", nativeQuery=true)
	List<MxDengue> getMuestrasDengueMetabolomicas();
	
	@Query(value="SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') = CURDATE() "
			+ "AND c.cat_tipo_muestra_id = 9 "
			+ "AND c.estudio = 3 ", nativeQuery=true)
	List<MxDengue> getMuestrasDengueBHC();
	
	@Query(value="SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') = CURDATE() "
			+ "AND c.cat_tipo_muestra_id = 8 "
			+ "AND c.estudio = 3 ", nativeQuery=true)
	List<MxDengue> getMuestrasDenguePBMC();
	
	@Query(value="SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') = CURDATE() "
			+ "AND c.cat_tipo_muestra_id = 7 "
			+ "AND c.estudio = 3 ", nativeQuery=true)
	List<MxDengue> getMuestrasDenguePaxGene();
	
	@Query(value="SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') = CURDATE() "
			+ "AND c.cat_tipo_muestra_id = 12 "
			+ "AND c.estudio = 3 ", nativeQuery=true)
	List<MxDengue> getMuestrasDengueHematicas();
	
	/***Obteniendo el Codigo Lab de la ultima consulta por el codigo del participante***/
	@Query(value="SELECT b.cod_lab FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND b.codigo_participante = :codigo "
			//+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			//+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.fecha_toma BETWEEN :startDate AND :endDate "
			+ "AND b.anulada = false "
			+ "AND c.estudio = 3 "
			+ "AND c.cat_tipo_muestra_id IN(10, 11) "
			+ "ORDER BY a.id DESC limit 1 ", nativeQuery=true)
	String findCodLabDengueMetabolomicaByCode(@Param("codigo") Integer codigo,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT b.cod_lab FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND b.codigo_participante = :codigo "
			//+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			//+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.fecha_toma BETWEEN :startDate AND :endDate "
			+ "AND b.anulada = false "
			+ "AND c.cat_tipo_muestra_id = 9 "
			+ "AND c.estudio = 3 "
			+ "ORDER BY a.id DESC limit 1 ", nativeQuery=true)
	String findCodLabDengueBHCByCode(@Param("codigo") Integer codigo,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT b.cod_lab FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND b.codigo_participante = :codigo "
			//+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			//+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.fecha_toma BETWEEN :startDate AND :endDate "
			+ "AND b.anulada = false "
			+ "AND c.cat_tipo_muestra_id = 8 "
			+ "AND c.estudio = 3 "
			+ "ORDER BY a.id DESC limit 1 ", nativeQuery=true)
	String findCodLabDenguePBMCByCode(@Param("codigo") Integer codigo,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT b.cod_lab FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND b.codigo_participante = :codigo "
			//+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			//+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.fecha_toma BETWEEN :startDate AND :endDate "
			+ "AND b.anulada = false "
			+ "AND c.cat_tipo_muestra_id = 7 "
			+ "AND c.estudio = 3 "
			+ "ORDER BY a.id DESC limit 1 ", nativeQuery=true)
	String findCodLabDenguePaxGeneByCode(@Param("codigo") Integer codigo,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	
	@Query(value="SELECT b.cod_lab FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND b.codigo_participante = :codigo "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			//+ "AND b.fecha_toma BETWEEN :startDate AND :endDate "
			+ "AND b.anulada = false "
			+ "AND c.cat_tipo_muestra_id = 12 "
			+ "AND c.estudio = 3 "
			+ "ORDER BY a.id DESC limit 1 ", nativeQuery=true)
	String findCodLabDengueHematicaByCode(@Param("codigo") Integer codigo,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	/***Obteniendo el ultimo registro ingresado por el codigo del participante***/
	@Query(value="SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND b.codigo_participante = :codigo "
			//+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			//+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.fecha_toma BETWEEN :startDate AND :endDate "
			+ "AND b.anulada = false "
			+ "AND c.estudio = 3 "
			+ "AND c.cat_tipo_muestra_id IN (10, 11) "
			+ "ORDER BY a.id DESC limit 1 ", nativeQuery=true)
	MxDengue findMxDengueMetabolomicaByCode(@Param("codigo") Integer codigo,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND b.codigo_participante = :codigo "
			//+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			//+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.fecha_toma BETWEEN :startDate AND :endDate "
			+ "AND b.anulada = false "
			+ "AND c.cat_tipo_muestra_id = 9 "
			+ "AND c.estudio = 3 "
			+ "ORDER BY a.id DESC limit 1 ", nativeQuery=true)
	MxDengue findMxDengueBHCByCode(@Param("codigo") Integer codigo,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND b.codigo_participante = :codigo "
			+ "AND b.fecha_toma BETWEEN :startDate AND :endDate "
			//+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			//+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.anulada = false "
			+ "AND c.cat_tipo_muestra_id = 8 "
			+ "AND c.estudio = 3 "
			+ "ORDER BY a.id DESC limit 1 ", nativeQuery=true)
	MxDengue findMxDenguePBMCByCode(@Param("codigo") Integer codigo,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND b.codigo_participante = :codigo "
			//+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			//+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.fecha_toma BETWEEN :startDate AND :endDate "
			+ "AND b.anulada = false "
			+ "AND c.cat_tipo_muestra_id = 7 "
			+ "AND c.estudio = 3 "
			+ "ORDER BY a.id DESC limit 1 ", nativeQuery=true)
	MxDengue findMxDenguePaxGeneByCode(@Param("codigo") Integer codigo,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	
	@Query(value="SELECT * FROM mx_dengue a, muestras b, cat_recepcion c "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.cat_recepcion_id = c.id "
			+ "AND b.anulada = false "
			+ "AND c.cat_tipo_muestra_id = 12 "
			+ "AND c.estudio = 3 "
			+ "AND b.codigo_participante = :codigo "
			//+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			//+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.fecha_toma BETWEEN :startDate AND :endDate "
			+ "ORDER BY a.id DESC limit 1 ", nativeQuery=true)
	MxDengue findMxDengueHematicaByCode(
			@Param("codigo") Integer codigo,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.anulada = false "
			+ "AND b.cod_lab_scan = :codLabScan", nativeQuery=true)
	MxDengue findMxDengueByCodLabScan(String codLabScan);
	
	/*MUESTRAS DENGUE CANDIDATOS A PBMC*/
	@Query(value = "SELECT * FROM mx_dengue a, cat_consultas b, muestras c, cat_recepcion d "
			+ "WHERE a.consulta_id = b.id "
			+ "AND a.muestra_id = c.id "
			+ "AND c.cat_recepcion_id = d.id "
			+ "AND b.consulta = 'I' "
			+ "AND d.cat_tipo_muestra_id = 12 "
			+ "AND d.estudio = 3 "
			+ "AND ((SELECT TIMESTAMPDIFF(DAY, c.fif, curdate()) AS dias_fif) <= 20) " //9 es el valor que tiene que llevar
			+ "OR ((SELECT TIMESTAMPDIFF(DAY, c.fis, curdate()) AS dias_fis) <= 20);", nativeQuery=true)
	List<MxDengue> findMuestrasDengueCandidatosPbmc();
}
