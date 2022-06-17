package ni.org.ics.smil.cssfv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatTipoPrueba;

/**
 * Created by Miguel Salinas on 14/12/2020.
 */
public interface CatTipoPruebaRepository extends JpaRepository<CatTipoPrueba, Long> {

	List<CatTipoPrueba> findByNombre(String nombre);
	
	List<CatTipoPrueba> findByIdCatMuestraId(Long id);
	
	@Query(value = "SELECT * FROM cat_tipo_pruebas a " + 
			"WHERE a.id_cat_muestra = :idCatMuestraId " +
			"AND a.nivel = :nivel " +
			"AND a.activo = true ", nativeQuery=true)
	List<CatTipoPrueba> findByCatMuestraIdAndNivel(
			@Param("idCatMuestraId") Long id,
			@Param("nivel") Integer nivel);
}
