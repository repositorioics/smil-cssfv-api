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
Created by SC on 28/09/2022.
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cat_clasificacion_muestra")
public class CatClasificacionMuestra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String descripcion;
	private Boolean activo;
}
