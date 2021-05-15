package ni.org.ics.smil.cssfv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatMismoEpfebril;

/**
 * Created by Miguel Salinas on 26/11/2020.
 */
public interface CatMismoEpfebrilRepository extends RevisionRepository<CatMismoEpfebril, Long, Integer>, JpaRepository<CatMismoEpfebril, Long> {

	List<CatMismoEpfebril> findByNombre(String nombre);
}
