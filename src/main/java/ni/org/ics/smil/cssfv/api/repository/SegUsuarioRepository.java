package ni.org.ics.smil.cssfv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import ni.org.ics.smil.cssfv.api.entity.security.Usuario;

public interface SegUsuarioRepository extends RevisionRepository<Usuario, Integer, Integer>, JpaRepository<Usuario, Integer>{

}
