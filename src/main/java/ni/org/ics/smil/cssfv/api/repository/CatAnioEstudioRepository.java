package ni.org.ics.smil.cssfv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

import ni.org.ics.smil.cssfv.api.entity.catalogs.CatAnioEstudio;

public interface CatAnioEstudioRepository extends RevisionRepository<CatAnioEstudio, Long, Integer>, JpaRepository<CatAnioEstudio, Long> {
	@Query(value = "SELECT * FROM cat_anio_estudio "
			+ "order by id desc limit 1 ", nativeQuery=true)
	CatAnioEstudio findLastRecordAnioEstudio();
}
