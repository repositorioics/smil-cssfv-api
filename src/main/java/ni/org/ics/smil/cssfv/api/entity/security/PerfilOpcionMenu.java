package ni.org.ics.smil.cssfv.api.entity.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;
import org.springframework.data.history.RevisionMetadata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "seg_perfiles_opcion_menu")
@Audited(withModifiedFlag = true)
public class PerfilOpcionMenu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private boolean activo;

	@ManyToOne
	@JoinColumn(name="seg_perfil_id", referencedColumnName = "id")
	private Perfil perfilId;
	
	@ManyToOne
	@JoinColumn(name="seg_opcion_menu_id", referencedColumnName = "id")
	private OpcionMenu opcionMenuId;

	@Transient
	private RevisionMetadata<Integer> editVersion;
}
