package ni.org.ics.smil.cssfv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatResultadosMuestras;

/**
 * Created by Santiago Carballo on 20/04/2021.
 */
public interface CatResultadosMuestrasRepository extends RevisionRepository<CatResultadosMuestras, Long, Integer>, JpaRepository<CatResultadosMuestras, Long> {

	List<CatResultadosMuestras> findByDescripcion(String descripcion);
	
	@Query(value = "SELECT * FROM cat_resultados_muestras a, cat_tipo_pruebas b " + 
			"WHERE a.id_cat_tipo_prueba = b.id " + 
			"AND a.id_cat_tipo_prueba = :id " + 
			"AND a.activo = true", nativeQuery=true)
	List<CatResultadosMuestras> findByIdCatTipoPruebaId(
			@Param("id") Long id);
}