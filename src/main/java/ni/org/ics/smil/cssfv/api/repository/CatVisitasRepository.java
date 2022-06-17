package ni.org.ics.smil.cssfv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatTubo;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatVisitas;

/**
 * Created by SC on 16/06/2021.
 */
public interface CatVisitasRepository extends RevisionRepository<CatVisitas, Long, Integer>, JpaRepository<CatVisitas, Long> {

	List<CatVisitas> findByNombre(String nombre);
	
	@Query(value = "SELECT * FROM cat_visitas a " + 
			"WHERE a.activo = true", nativeQuery=true)
	List<CatVisitas> findAllVisitasActivas();
}
