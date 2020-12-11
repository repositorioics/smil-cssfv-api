package ni.org.ics.smil.cssfv.api.repository;

import ni.org.ics.smil.cssfv.api.entity.catalogos.CatCambioCategoria;
import ni.org.ics.smil.cssfv.api.entity.catalogos.CatCategoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

/**
 * Created by Miguel Salinas on 26/11/2020.
 */
public interface CatCategoriaRepository extends RevisionRepository<CatCategoria, Long, Integer>, JpaRepository<CatCategoria, Long> {

	List<CatCategoria> findByNombre(String nombre);
}
