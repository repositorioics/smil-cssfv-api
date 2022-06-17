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
@Subselect("SELECT a.CODIGO as Codigo, a.ESTADO as Estado, a.PASIVO as Pasivo, a.NOMBRE as Nombre "
		+ "FROM estudios_ics.estudios a"
)
public class EstudiosView {
	@Id
	private Long codigo;
	private String estado;
	private String pasivo;
	private String nombre;
}
