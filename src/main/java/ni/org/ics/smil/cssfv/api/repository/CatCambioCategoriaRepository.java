package ni.org.ics.smil.cssfv.api.repository;

import ni.org.ics.smil.cssfv.api.entity.catalogos.CatCambioCategoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Miguel Salinas on 26/11/2020.
 */
@Repository
public interface CatCambioCategoriaRepository extends RevisionRepository<CatCambioCategoria, Long, Integer>, JpaRepository<CatCambioCategoria, Long> {

	List<CatCambioCategoria> findByCambioCat(String cambioCat);
}
