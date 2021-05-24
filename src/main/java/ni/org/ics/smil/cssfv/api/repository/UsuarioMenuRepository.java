package ni.org.ics.smil.cssfv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ni.org.ics.smil.cssfv.api.entity.view.UsuarioMenuView;

public interface UsuarioMenuRepository extends JpaRepository<UsuarioMenuView, Long> {

	List<UsuarioMenuView> findByUsuario(String usuario);
}
