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
	    "select a.CODIGO AS Codigo,a.NOMBRE1 AS Nombre1,a.NOMBRE2 AS Nombre2,a.APELLIDO1 AS Apellido1,a.APELLIDO2 AS Apellido2, "
	    + "a.SEXO AS Sexo, DATE_FORMAT(a.FECHANAC, \"%d-%m-%Y\") AS FechaNac,b.tutor AS Tutor,b.est_part AS EstadoParticipante,b.estudio AS EstudiosParticipante,"
	    + "c.DIRECCION AS Direccion, a.CODIGO_CASA AS CodigoCasa, b.casa_chf AS CasaChf "
	    + "from ((estudios_ics.participantes a join estudios_ics.participantes_procesos b on((a.CODIGO = b.codigo))) "
	    + "join estudios_ics.casas c on((a.CODIGO_CASA = c.CODIGO))) where (b.est_part = 1) order by CODIGO"
	)
public class ParticipanteView {

	@Id
	private Long codigo;
	private String nombre1;
	private String nombre2;
	private String apellido1;
	private String apellido2;
	private String sexo;
	private String fechanac;
	private String tutor;
	private String estadoparticipante;
	private String estudiosparticipante;
	private String direccion;
	private String codigocasa;
	private String casachf;
	
}
