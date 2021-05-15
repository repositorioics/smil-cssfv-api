package ni.org.ics.smil.cssfv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

import ni.org.ics.smil.cssfv.api.entity.security.PerfilUsuario;

public interface SegPerfilUsuarioRepository extends RevisionRepository<PerfilUsuario, Integer, Integer>, JpaRepository<PerfilUsuario, Integer>{

	@Query(value = "SELECT * FROM seg_perfil_usuario a, seg_perfil b, seg_usuario c " + 
			"WHERE a.seg_perfil_id = b.id " + 
			"AND a.seg_usuario_id = c.id " + 
			"AND b.nombre = :nombre " + 
			"AND a.activo = true", nativeQuery=true)
	List<PerfilUsuario> findByPerfilIdNombre(
			@Param("nombre") String nombre);
}
