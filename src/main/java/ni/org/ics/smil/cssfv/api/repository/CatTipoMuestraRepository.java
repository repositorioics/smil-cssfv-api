package ni.org.ics.smil.cssfv.api.repository;

import ni.org.ics.smil.cssfv.api.entity.catalogos.CatTipoMuestra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Miguel Salinas on 14/12/2020.
 */
public interface CatTipoMuestraRepository extends JpaRepository<CatTipoMuestra, Long> {

	List<CatTipoMuestra> findByNombre(String nombre);
}
