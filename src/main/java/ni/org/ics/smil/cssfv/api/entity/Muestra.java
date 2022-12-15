package ni.org.ics.smil.cssfv.api.entity;

import java.math.BigDecimal;
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
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatMotivoAnulacion;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatMuestra;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatRecepcion;
import ni.org.ics.smil.cssfv.api.entity.security.Usuario;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "muestras")
public class Muestra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	private Integer codigoParticipante;
	private Integer codigoCasa;
	private Long quienOrdena;
	private String horaToma;
	private String horaRefrigeracion;
	private String motivoNoMx;
	private Float volumen;
	private Boolean mxTomada;
	private Boolean mxEnviada;
	private Boolean retoma;
	private String observacion;
	private Boolean anulada;
	private Boolean otroMotivoAnulacion;
	private String motivoAnulacion;
	private Long usuarioAnulacion;
	private Boolean mxCompartida;
	private String estudiosParticipante;
	private String horaEnvio;
	private Integer viaje;
	private BigDecimal tempEnvio;
	private String codLab;
	private String codLabScan;
	private BigDecimal tempRecepcion;
	private BigDecimal tempAlmacenamiento;
	
	@Temporal(TemporalType.DATE)
	private Date fechaToma;	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;
	
	@Temporal(TemporalType.DATE)
	private Date fis;
	
	@Temporal(TemporalType.DATE)
	private Date fif;
	
	@Temporal(TemporalType.DATE)
	private Date fechaEnvio;	

	@ManyToOne
	@JoinColumn(name="usuario_id", referencedColumnName = "id")
	private Usuario usuarioId;
	
	@ManyToOne
	@JoinColumn(name="bioanalista_id", referencedColumnName = "id")
	private Usuario bioanalistaId;
	
	@ManyToOne
	@JoinColumn(name="mx_id", referencedColumnName = "id")
	private CatMuestra mxId;
	
	@ManyToOne
	@JoinColumn(name="motivo_anulacion_id", referencedColumnName = "id")
	private CatMotivoAnulacion motivoAnulacionId;
	
	@ManyToOne
	@JoinColumn(name="cat_recepcion_id", referencedColumnName = "id")
	private CatRecepcion catRecepcionId;
	
	@ManyToOne
	@JoinColumn(name="usuario_recepciona_id", referencedColumnName = "id")
	private Usuario usuarioRecepciona;
	
	@ManyToOne
	@JoinColumn(name="bioanalista_envia", referencedColumnName = "id")
	private Usuario bioanalistaEnvia;
	
}
