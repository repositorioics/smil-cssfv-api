package ni.org.ics.smil.cssfv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatEnvioMuestras;

public interface CatEnvioMuestrasRepository extends RevisionRepository<CatEnvioMuestras, Long, Integer>, JpaRepository<CatEnvioMuestras, Long> {
	
	List<CatEnvioMuestras> findByNombre(String nombre);
	
	@Query(value = "SELECT * FROM cat_envio_muestras a " + 
			"WHERE a.estado = true ", nativeQuery=true)
	List<CatEnvioMuestras> findAllCatEnvioMuestrasActivas();
}
