package ni.org.ics.smil.cssfv.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ni.org.ics.smil.cssfv.api.entity.MxDengue;
import ni.org.ics.smil.cssfv.api.entity.MxInfluenza;

public interface MuestraDengueRepository extends JpaRepository<MxDengue, Long> {

	//List<MxDengue> findByMuestraIdCodigoParticipante(Integer codigoParticipante);
	
	@Query(value = "SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigoParticipante", nativeQuery=true)
	List<MxDengue> findMuestrasDengueByCodigoParticipante(
			@Param("codigoParticipante") Integer codigoParticipante);
	
	/******BUSQUEDAS DE TODAS LAS MUESTRAS DENGUE POR CODIGO PARTICIPANTE***********/
	/*Busqueda muestras Dengue Hematicas por codigo participante*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigoParticipante " 
			+ "AND b.cat_recepcion_id = 2 "
			+ "OR b.cat_recepcion_id = 43 ", nativeQuery=true)
	List<MxDengue> findMuestrasDengueHematicasByCodigoParticipante(
			@Param("codigoParticipante") Integer codigoParticipante);
	
	/*Busqueda muestras Dengue BCH por codigo participante*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigoParticipante "
			+ "AND b.cat_recepcion_id = 11 "
			+ "OR b.cat_recepcion_id = 12 ", nativeQuery=true)
	List<MxDengue> findMuestrasDengueBHCByCodigoParticipante(
			@Param("codigoParticipante") Integer codigoParticipante);
	
	/*Busqueda muestras Dengue Paxgene por codigo participante*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigoParticipante "
			+ "AND b.cat_recepcion_id = 10 ", nativeQuery=true)
	List<MxDengue> findMuestrasDenguePaxgeneByCodigoParticipante(
			@Param("codigoParticipante") Integer codigoParticipante);
	
	/*Busqueda muestras Dengue Metabolomicas por codigo participante*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigoParticipante "
			+ "AND b.cat_recepcion_id = 3 "
			+ "OR b.cat_recepcion_id = 4 "
			+ "OR b.cat_recepcion_id = 5 "
			+ "OR b.cat_recepcion_id = 6 "
			+ "OR b.cat_recepcion_id = 7 "
			+ "OR b.cat_recepcion_id = 8 ", nativeQuery=true)
	List<MxDengue> findMuestrasDengueMetabolomicasByCodigoParticipante(
			@Param("codigoParticipante") Integer codigoParticipante);
	
	/*Busqueda muestras Dengue PBMC por codigo participante*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigoParticipante "
			+ "AND b.cat_recepcion_id = 9 ", nativeQuery=true)
	List<MxDengue> findMuestrasDenguePBMCByCodigoParticipante(
			@Param("codigoParticipante") Integer codigoParticipante);
	
	/*******BUSQUEDA MUESTRAS DENGUE POR RANGO DE FECHA**********/
	/*Busqueda muestras Dengue Hematicas por rango de fecha*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.fecha_registro >= :startDate "
			+ "AND b.fecha_registro <= :endDate "
			+ "AND b.cat_recepcion_id = 2 "
			+ "OR b.cat_recepcion_id = 43 ", nativeQuery=true)
	List<MxDengue> findByMuestrasHematicasFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	/*Busqueda muestras Dengue Metabolomica por rango de fecha*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.fecha_registro >= :startDate "
			+ "AND b.fecha_registro <= :endDate "
			+ "AND b.cat_recepcion_id = 3 "
			+ "OR b.cat_recepcion_id = 4 "
			+ "OR b.cat_recepcion_id = 5 "
			+ "OR b.cat_recepcion_id = 6 "
			+ "OR b.cat_recepcion_id = 7 "
			+ "OR b.cat_recepcion_id = 8 ", nativeQuery=true)
	List<MxDengue> findByMuestrasMetabolomicasFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	/*Busqueda muestras Dengue BHC por rango de fecha*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.fecha_registro >= :startDate "
			+ "AND b.fecha_registro <= :endDate "
			+ "AND b.cat_recepcion_id = 11 "
			+ "OR b.cat_recepcion_id = 12 ", nativeQuery=true)
	List<MxDengue> findByMuestrasBHCFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	/*Busqueda muestras Dengue PBMC por rango de fecha*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.fecha_registro >= :startDate "
			+ "AND b.fecha_registro <= :endDate "
			+ "AND b.cat_recepcion_id = 9 ", nativeQuery=true)
	List<MxDengue> findByMuestrasPBMCFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	/*Busqueda muestras Dengue PaxGene por rango de fecha*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.fecha_registro >= :startDate "
			+ "AND b.fecha_registro <= :endDate "
			+ "AND b.cat_recepcion_id = 10 ", nativeQuery=true)
	List<MxDengue> findByMuestrasPaxGeneFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	List<MxDengue> findByMuestraIdCodigoParticipanteAndMuestraIdFechaTomaBetween(Integer codigoParticipante, Date fecha1, Date fecha2);
	
	/******BUSQUEDA MUESTRAS DENGUE POR CODIGO Y RANGO DE FECHA***********/
	/*Busqueda muestras Dengue Hematicas por codigo y rango de fecha*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigoParticipante "
			+ "AND b.fecha_registro >= :startDate "
			+ "AND b.fecha_registro <= :endDate "
			+ "AND b.cat_recepcion_id = 2 "
			+ "OR b.cat_recepcion_id = 43 ", nativeQuery=true)
	List<MxDengue> findByMuestrasHematicasCodigoFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("codigoParticipante") Integer codigoParticipante);
	
	/*Busqueda muestras Dengue Metabolomica*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigoParticipante "
			+ "AND b.fecha_registro >= :startDate "
			+ "AND b.fecha_registro <= :endDate "
			+ "AND b.cat_recepcion_id = 3 "
			+ "OR b.cat_recepcion_id = 4 "
			+ "OR b.cat_recepcion_id = 5 "
			+ "OR b.cat_recepcion_id = 6 "
			+ "OR b.cat_recepcion_id = 7 "
			+ "OR b.cat_recepcion_id = 8 ", nativeQuery=true)
	List<MxDengue> findByMuestrasMetabolomicasCodigoFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("codigoParticipante") Integer codigoParticipante);
	
	/*Busqueda muestras Dengue BHC*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigoParticipante "
			+ "AND b.fecha_registro >= :startDate "
			+ "AND b.fecha_registro <= :endDate "
			+ "AND b.cat_recepcion_id = 2 "
			+ "OR b.cat_recepcion_id = 43 ", nativeQuery=true)
	List<MxDengue> findByMuestrasBHCCodigoFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("codigoParticipante") Integer codigoParticipante);
	
	/*Busqueda muestras Dengue PBMC*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigoParticipante "
			+ "AND b.fecha_registro >= :startDate "
			+ "AND b.fecha_registro <= :endDate "
			+ "AND b.cat_recepcion_id = 9 ", nativeQuery=true)
	List<MxDengue> findByMuestrasPBMCCodigoFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("codigoParticipante") Integer codigoParticipante);
	
	/*Busqueda muestras Dengue PaxGene*/
	@Query(value = "SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigoParticipante "
			+ "AND b.fecha_registro >= :startDate "
			+ "AND b.fecha_registro <= :endDate "
			+ "AND b.cat_recepcion_id = 10 ", nativeQuery=true)
	List<MxDengue> findByMuestrasPaxGeneCodigoFechaRegistroBetween(
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("codigoParticipante") Integer codigoParticipante);
	
	
	@Query(value="SELECT * from mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "and b.mx_id = :id "
			+ "and b.mx_enviada = false "
			+ "and b.anulada = false", nativeQuery=true)
	List<MxDengue> getMuestrasDenguePendientesEnvio(
			@Param("id") Long id);
	
	/******OBTENER LAS MUESTRAS DENGUE DEL DIA*********/
	@Query(value="SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') = CURDATE() "
			+ "AND b.cat_recepcion_id = 3 "
			+ "OR b.cat_recepcion_id = 4 "
			+ "OR b.cat_recepcion_id = 5 "
			+ "OR b.cat_recepcion_id = 6 "
			+ "OR b.cat_recepcion_id = 7 "
			+ "OR b.cat_recepcion_id = 8 ", nativeQuery=true)
	List<MxDengue> getMuestrasDengueMetabolomicas();
	
	@Query(value="SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') = CURDATE() "
			+ "AND b.cat_recepcion_id = 11 "
			+ "OR b.cat_recepcion_id = 12 ", nativeQuery=true)
	List<MxDengue> getMuestrasDengueBHC();
	
	@Query(value="SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') = CURDATE() "
			+ "AND b.cat_recepcion_id = 9 ", nativeQuery=true)
	List<MxDengue> getMuestrasDenguePBMC();
	
	@Query(value="SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') = CURDATE() "
			+ "AND b.cat_recepcion_id = 10 ", nativeQuery=true)
	List<MxDengue> getMuestrasDenguePaxGene();
	
	
	@Query(value="SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') = CURDATE() "
			+ "AND b.cat_recepcion_id = 2 "
			+ "OR b.cat_recepcion_id = 43 ", nativeQuery=true)
	List<MxDengue> getMuestrasDengueHematicas();
	
	/***Obteniendo el Codigo Lab de la ultima consulta por el codigo del participante***/
	@Query(value="SELECT a.cod_lab FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigo "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.anulada = false "
			+ "AND b.cat_recepcion_id = 3 "
			+ "OR b.cat_recepcion_id = 4 "
			+ "OR b.cat_recepcion_id = 5 "
			+ "OR b.cat_recepcion_id = 6 "
			+ "OR b.cat_recepcion_id = 7 "
			+ "OR b.cat_recepcion_id = 8 "
			+ "ORDER BY a.id DESC limit 1 ", nativeQuery=true)
	String findCodLabDengueMetabolomicaByCode(@Param("codigo") Integer codigo,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT a.cod_lab FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigo "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.anulada = false "
			+ "AND b.cat_recepcion_id = 11 "
			+ "OR b.cat_recepcion_id = 12 "
			+ "ORDER BY a.id DESC limit 1 ", nativeQuery=true)
	String findCodLabDengueBHCByCode(@Param("codigo") Integer codigo,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT a.cod_lab FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigo "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.anulada = false "
			+ "AND b.cat_recepcion_id = 9 "
			+ "ORDER BY a.id DESC limit 1 ", nativeQuery=true)
	String findCodLabDenguePBMCByCode(@Param("codigo") Integer codigo,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT a.cod_lab FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigo "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.anulada = false "
			+ "AND b.cat_recepcion_id = 10 "
			+ "ORDER BY a.id DESC limit 1 ", nativeQuery=true)
	String findCodLabDenguePaxGeneByCode(@Param("codigo") Integer codigo,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	
	@Query(value="SELECT a.cod_lab FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigo "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.anulada = false "
			+ "AND b.cat_recepcion_id = 2 "
			+ "OR b.cat_recepcion_id = 43 "
			+ "ORDER BY a.id DESC limit 1 ", nativeQuery=true)
	String findCodLabDengueHematicaByCode(@Param("codigo") Integer codigo,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	/***Obteniendo el ultimo registro ingresado por el codigo del participante***/
	@Query(value="SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigo "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.anulada = false "
			+ "AND b.cat_recepcion_id = 3 "
			+ "OR b.cat_recepcion_id = 4 "
			+ "OR b.cat_recepcion_id = 5 "
			+ "OR b.cat_recepcion_id = 6 "
			+ "OR b.cat_recepcion_id = 7 "
			+ "OR b.cat_recepcion_id = 8 "
			+ "ORDER BY a.id DESC limit 1 ", nativeQuery=true)
	MxDengue findMxDengueMetabolomicaByCode(@Param("codigo") Integer codigo,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigo "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.anulada = false "
			+ "AND b.cat_recepcion_id = 11 "
			+ "OR b.cat_recepcion_id = 12 "
			+ "ORDER BY a.id DESC limit 1 ", nativeQuery=true)
	MxDengue findMxDengueBHCByCode(@Param("codigo") Integer codigo,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigo "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.anulada = false "
			+ "AND b.cat_recepcion_id = 9 "
			+ "ORDER BY a.id DESC limit 1 ", nativeQuery=true)
	MxDengue findMxDenguePBMCByCode(@Param("codigo") Integer codigo,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigo "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.anulada = false "
			+ "AND b.cat_recepcion_id = 10 "
			+ "ORDER BY a.id DESC limit 1 ", nativeQuery=true)
	MxDengue findMxDenguePaxGeneByCode(@Param("codigo") Integer codigo,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	
	@Query(value="SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.codigo_participante = :codigo "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') >= :startDate "
			+ "AND DATE_FORMAT(b.fecha_registro, '%Y-%m-%d') <= :endDate "
			+ "AND b.anulada = false "
			+ "AND b.cat_recepcion_id = 2 "
			+ "OR b.cat_recepcion_id = 43 "
			+ "ORDER BY a.id DESC limit 1 ", nativeQuery=true)
	MxDengue findMxDengueHematicaByCode(@Param("codigo") Integer codigo,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query(value="SELECT * FROM mx_dengue a, muestras b "
			+ "WHERE a.muestra_id = b.id "
			+ "AND b.anulada = false "
			+ "AND a.cod_lab_scan = :codLabScan", nativeQuery=true)
	MxDengue findMxDengueByCodLabScan(String codLabScan); 
}
