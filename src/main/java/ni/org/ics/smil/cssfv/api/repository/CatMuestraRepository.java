package ni.org.ics.smil.cssfv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatMuestra;

/**
 * Created by Miguel Salinas on 14/12/2020.
 */
public interface CatMuestraRepository extends JpaRepository<CatMuestra, Long> {

	List<CatMuestra> findByNombre(String nombre);
}
