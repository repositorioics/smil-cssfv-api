package ni.org.ics.smil.cssfv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatConsulta;

/**
 * Created by Miguel Salinas on 14/12/2020.
 */
public interface CatConsultaRepository extends JpaRepository<CatConsulta, Long> {

	List<CatConsulta> findByConsulta(String consulta);
	
	@Query(value = "SELECT * FROM cat_consultas a " + 
			"WHERE a.activo = true", nativeQuery=true)
	List<CatConsulta> findAllConsultasActivas();
}
