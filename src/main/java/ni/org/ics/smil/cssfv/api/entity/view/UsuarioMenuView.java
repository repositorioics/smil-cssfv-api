package ni.org.ics.smil.cssfv.api.entity.view;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Immutable
@Subselect(
		"select om.id as id, usuario.usuario as Usuario, perfil.nombre as Perfil, menu.id as MenuId, menu.nombre as Menu, om.id as SubMenuId, om.nombre as SubMenu, om.url as Url "
		+"from seg_usuario usuario, seg_perfil perfil, seg_perfil_usuario pu, seg_menu menu, seg_opciones_menu om, seg_perfiles_opcion_menu pom "
		+"where usuario.id  = pu.seg_usuario_id and "
		+"perfil.id = pu.seg_perfil_id and "
		+"menu.id = om.seg_menu_id and "
		+"om.id = pom.seg_opcion_menu_id and "
		+"perfil.id = pom.seg_perfil_id and "
		+"usuario.activo = 1 and pu.activo = 1 and menu.activo = 1 and om.activo = 1 and pom.activo = 1 "
		+"order by menu.orden asc, om.orden asc "
	)
public class UsuarioMenuView {

	@Id
	private Long id;
	private String usuario;
	private String perfil;
	private Long menuid;
	private String menu;
	private Long submenuid;
	private String submenu;
	private String url;
	
}
