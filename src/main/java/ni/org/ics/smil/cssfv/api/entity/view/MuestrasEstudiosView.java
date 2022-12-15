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

/*@Subselect("select b.nombre_estudio as NombreEstudio, count(1) as Cantidad "
		+ "from muestras a, cat_recepcion b "
		+ "where a.cat_recepcion_id = b.id "
		+ "group by b.nombre_estudio")*/
@Subselect("SELECT"
		+ "	CASE"
		+ "	WHEN b.nombre_estudio = 'Cohorte Pediátrica Dengue' THEN 'Dengue' "
		+ "	WHEN b.nombre_estudio = 'Cohorte Pediátrica Influenza' THEN 'Influenza' "
		+ "	WHEN b.nombre_estudio = 'Cohorte Pediátrica Influenza UO1' THEN 'U01' "
		+ " WHEN b.nombre_estudio = 'Estudio Cohorte Familia' THEN 'CHF' "
		+ "	WHEN b.nombre_estudio = 'Transmisión Covid19' THEN 'CV' "
		+ " ELSE '' "
		+ "	END AS NombreEstudio,  count(b.nombre_estudio) as cantidad "
		+ "	FROM muestras a, cat_recepcion b WHERE a.cat_recepcion_id = b.id "
		+ "	group by b.nombre_estudio")
public class MuestrasEstudiosView {
	@Id
	private String nombreestudio;
	private Integer cantidad;
	

}
