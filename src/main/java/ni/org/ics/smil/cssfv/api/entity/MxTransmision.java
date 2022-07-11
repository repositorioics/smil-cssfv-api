package ni.org.ics.smil.cssfv.api.entity;

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
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatTipoMuestra;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatTipoPrueba;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatTubo;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatVisitas;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mx_transmision")
public class MxTransmision {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="muestra_id", referencedColumnName = "id")
	private Muestra muestraId;

	@ManyToOne
	@JoinColumn(name="tubo_id", referencedColumnName = "id")
	private CatTubo tuboId;
	
	@ManyToOne
	@JoinColumn(name="visita_id", referencedColumnName = "id")
	private CatVisitas visitaId;
	
	@ManyToOne
	@JoinColumn(name="tipo_prueba_id", referencedColumnName = "id") 
	private CatTipoPrueba tipoPruebaId;
	  
	@ManyToOne
	@JoinColumn(name="tipo_muestra_id", referencedColumnName = "id") 
	private CatTipoMuestra tipoMuestraId;
	
	@ManyToOne
	@JoinColumn(name="consulta_id", referencedColumnName = "id") 
	private CatTipoMuestra consultaId;
	
	//private String codLabScan;
	//private String codLabMi;
	private String motivoSinFif;
	//private String codLab;
	private Boolean mxNoTomada;
	private String horaRefrigeracion;
	private Integer viaje;
	@Temporal(TemporalType.DATE)
	private Date fechaEnvio;
	private String horaEnvio;
	private Boolean mxFinalInicial;
	private Boolean plasma;
	private Boolean transmision;
}
