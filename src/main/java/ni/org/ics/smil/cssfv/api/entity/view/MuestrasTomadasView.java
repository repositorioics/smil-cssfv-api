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
		"SELECT b.nombre as Muestra, count(*) as Cantidad "
				+ "FROM muestras a, cat_muestras b "
				+ "where b.id = a.mx_id "
				+ "and a.mx_tomada = true "
				+ "group by b.nombre"
	)
public class MuestrasTomadasView {
	@Id
	private String muestra;
	private Integer cantidad;
}
