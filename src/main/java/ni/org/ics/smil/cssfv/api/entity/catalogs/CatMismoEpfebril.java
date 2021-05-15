package ni.org.ics.smil.cssfv.api.entity.catalogs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
Created by Santiago Carballo on 19/04/2021.
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cat_mismo_epfebril")
//@Audited(withModifiedFlag = true)
public class CatMismoEpfebril {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String descripcion;
	private Boolean activo;
	
	//@Transient
	//private RevisionMetadata<Integer> editVersion;
}
