package ni.org.ics.smil.cssfv.api.entity.catalogs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
Created by Santiago Carballo on 20/04/2021.
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cat_resultados_muestras")
//@Audited(withModifiedFlag = true)
public class CatResultadosMuestras {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer codigo;
	private String descripcion;
	private Boolean activo;
	
	@ManyToOne
	@JoinColumn(name="id_cat_tipo_prueba", referencedColumnName = "id")
	private CatTipoPrueba idCatTipoPrueba;
	
	//@Transient
	//private RevisionMetadata<Integer> editVersion;
}
