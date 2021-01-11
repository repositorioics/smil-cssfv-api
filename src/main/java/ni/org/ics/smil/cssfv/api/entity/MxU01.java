package ni.org.ics.smil.cssfv.api.entity;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatClasificacion;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatConsulta;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatTubo;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mx_U01")
public class MxU01 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="muestra_id", referencedColumnName = "id")
	private Muestra muestraId;

	@ManyToOne
	@JoinColumn(name="consulta_id", referencedColumnName = "id")
	private CatConsulta consultaId;
	
	@ManyToOne
	@JoinColumn(name="tubo_id", referencedColumnName = "id")
	private CatTubo tuboId;
	
	@ManyToOne
	@JoinColumn(name="clasificacion_id", referencedColumnName = "id")
	private CatClasificacion clasificacionId;
	
	private String codLabScan;
	private String codLabMi;
	private String motivoSinFif;
	private String codLab;
	private Boolean mxNoTomada;
	private Time horaRefrigeracion;
	private Integer viaje;
	@Temporal(TemporalType.DATE)
	private Date fechaEnvio;
	private Time horaEnvio;
	private Boolean mxFinalInicial;

}
