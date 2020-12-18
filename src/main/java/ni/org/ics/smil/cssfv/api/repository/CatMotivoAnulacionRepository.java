package ni.org.ics.smil.cssfv.api.repository;

import ni.org.ics.smil.cssfv.api.entity.catalogos.CatMotivoAnulacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Miguel Salinas on 14/12/2020.
 */
public interface CatMotivoAnulacionRepository extends JpaRepository<CatMotivoAnulacion, Long> {

	List<CatMotivoAnulacion> findByNombre(String nombre);
}
