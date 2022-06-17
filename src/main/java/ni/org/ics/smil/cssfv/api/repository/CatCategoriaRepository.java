package ni.org.ics.smil.cssfv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatCategoria;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatClasificacion;

/**
 * Created by Miguel Salinas on 26/11/2020.
 */
public interface CatCategoriaRepository extends RevisionRepository<CatCategoria, Long, Integer>, JpaRepository<CatCategoria, Long> {

	List<CatCategoria> findByNombre(String nombre);
	
	@Query(value = "SELECT * FROM cat_categoria a " + 
			"WHERE a.activo = true", nativeQuery=true)
	List<CatCategoria> findAllCategoriasActivas();
}
