package ni.org.ics.smil.cssfv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatCambioCategoria;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatCategoria;

/**
 * Created by Miguel Salinas on 26/11/2020.
 */
@Repository
public interface CatCambioCategoriaRepository extends RevisionRepository<CatCambioCategoria, Long, Integer>, JpaRepository<CatCambioCategoria, Long> {

	List<CatCambioCategoria> findByCambioCat(String cambioCat);
	
	@Query(value = "SELECT * FROM cat_camb_categoria a " + 
			"WHERE a.activo = true", nativeQuery=true)
	List<CatCambioCategoria> findAllCambioCategoriasActivas();
}
