package ni.org.ics.smil.cssfv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatRecepcion;

public interface CatRecepcionRepository extends RevisionRepository<CatRecepcion, Long, Integer>, JpaRepository<CatRecepcion, Long> {
	
	List<CatRecepcion> findByEstudio(String estudio);
	
	@Query(value = "SELECT * FROM cat_recepcion a " + 
			"WHERE a.activo = true", nativeQuery=true)
	List<CatRecepcion> findAllRecepcionesActivas();
	
	@Query(value = "SELECT * FROM cat_recepcion a "
			+ "WHERE :codLab REGEXP(a.expresion_regular)", nativeQuery=true)
	CatRecepcion findTypeReceipt(@Param("codLab") String codLab);
	
	
}
