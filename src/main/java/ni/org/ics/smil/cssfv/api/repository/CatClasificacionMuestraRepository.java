package ni.org.ics.smil.cssfv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatClasificacionMuestra;

/**
 * Created by SC on 28/09/2022.
 */
public interface CatClasificacionMuestraRepository extends JpaRepository<CatClasificacionMuestra, Long> {
	
	List<CatClasificacionMuestra> findByNombre(String nombre);
	
	@Query(value = "SELECT * FROM cat_clasificacion_muestra a " + 
			"WHERE a.activo = true", nativeQuery=true)
	List<CatClasificacionMuestra> getAllClasificacionMuestraActivas();
}
