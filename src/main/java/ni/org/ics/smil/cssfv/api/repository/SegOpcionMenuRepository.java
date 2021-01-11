package ni.org.ics.smil.cssfv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import ni.org.ics.smil.cssfv.api.entity.security.OpcionMenu;

public interface SegOpcionMenuRepository extends RevisionRepository<OpcionMenu, Integer, Integer>, JpaRepository<OpcionMenu, Integer>{

}
