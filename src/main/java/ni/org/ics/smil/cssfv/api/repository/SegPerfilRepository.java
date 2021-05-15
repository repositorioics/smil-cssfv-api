package ni.org.ics.smil.cssfv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ni.org.ics.smil.cssfv.api.entity.security.Perfil;
import ni.org.ics.smil.cssfv.api.entity.security.PerfilUsuario;

public interface SegPerfilRepository extends JpaRepository<Perfil, Integer> {
	
}
