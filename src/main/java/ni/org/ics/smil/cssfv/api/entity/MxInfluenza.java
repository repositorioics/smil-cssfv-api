package ni.org.ics.smil.cssfv.api.entity;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
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
import ni.org.ics.smil.cssfv.api.entity.catalogos.CatTipoMuestra;
import ni.org.ics.smil.cssfv.api.entity.catalogos.CatTipoPrueba;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mx_influenza")
public class MxInfluenza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="muestra_id", referencedColumnName = "id")
	private Muestra muestraId;

	@ManyToOne
	@JoinColumn(name="tipo_prueba_id", referencedColumnName = "id")
	private CatTipoPrueba tipoPruebaId;
	
	@ManyToOne
	@JoinColumn(name="tipo_muestra_id", referencedColumnName = "id")
	private CatTipoMuestra tipoMuestraId;
	
	private String codLabScan;
	private Boolean retoma;
	private Boolean pruebaRapida;
	private Boolean pruebaRapidaVsr;
	private String codLab;
	private Boolean mxNoTomada;
	private String motivoMismoEf;
	private String motivoSinFif;
	private Long perteneceEstTransm;
	private Long casoIndiceEstTransm;
	@Temporal(TemporalType.TIMESTAMP)
	private Date retEstTransm;
	private Boolean positivoMi;
	private Boolean mxCovid;
	private Boolean covidPositivo;
	private String procPri;
	private Integer numeroPruebas;
	private Integer resultado;
	//private Time tiempoprocesamiento;

}
