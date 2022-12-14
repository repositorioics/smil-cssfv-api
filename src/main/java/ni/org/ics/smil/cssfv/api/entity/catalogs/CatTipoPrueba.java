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
Created by Miguel Salinas on 26/11/2020.
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cat_tipo_pruebas")
public class CatTipoPrueba {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String descripcion;
	private Integer nivel;
	private Boolean activo;
	
	@ManyToOne
	@JoinColumn(name="id_cat_muestra", referencedColumnName = "id")
	private CatMuestra idCatMuestra;
	
}
