package ni.org.ics.smil.cssfv.api.entity.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "seg_opciones_menu")
@Audited(withModifiedFlag = true)
public class OpcionMenu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="seg_menu_id", referencedColumnName = "id")
	private Menu menuId;
	
	private String nombre;
	private String descripcion;
	private String url;
	private String icono;
	private boolean activo;
	private int orden;
	private boolean visible;
	
}
