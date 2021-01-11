package ni.org.ics.smil.cssfv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatClasificacion;

/**
 * Created by Miguel Salinas on 14/12/2020.
 */
public interface CatClasificacionRepository extends JpaRepository<CatClasificacion, Long> {

	List<CatClasificacion> findByNombre(String nombre);
}
