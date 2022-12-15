package ni.org.ics.smil.cssfv.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

import ni.org.ics.smil.cssfv.api.entity.security.ParametrosSistema;

public interface ParametrosSistemaRepository extends RevisionRepository<ParametrosSistema, Integer, Integer>, JpaRepository<ParametrosSistema, Integer> {

	@Query(value = "SELECT * FROM parametros_sistema a " + 
			"WHERE a.nombre = :nombre ", nativeQuery=true)
	ParametrosSistema findAllParametrosSistemaByName(
			@Param("nombre") String nombre);
}
