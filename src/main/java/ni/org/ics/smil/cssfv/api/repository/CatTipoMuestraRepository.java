package ni.org.ics.smil.cssfv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatTipoMuestra;

/**
 * Created by Miguel Salinas on 14/12/2020.
 */
public interface CatTipoMuestraRepository extends JpaRepository<CatTipoMuestra, Long> {

	List<CatTipoMuestra> findByNombre(String nombre);
}
