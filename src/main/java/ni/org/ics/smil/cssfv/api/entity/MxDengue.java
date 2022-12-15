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
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatCambioCategoria;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatCategoria;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatConsulta;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatMismoEpfebril;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatTipoPrueba;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatTubo;
import ni.org.ics.smil.cssfv.api.entity.security.Usuario;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mx_dengue")
public class MxDengue {

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
	@JoinColumn(name="categoria_id", referencedColumnName = "id")
	private CatCategoria categoriaId;
	
	@ManyToOne
	@JoinColumn(name="consulta_id", referencedColumnName = "id")
	private CatConsulta consultaId;
	
	@ManyToOne
	@JoinColumn(name="tubo_id", referencedColumnName = "id")
	private CatTubo tuboId;
	
	@ManyToOne
	@JoinColumn(name="cambio_categoria_id", referencedColumnName = "id")
	private CatCambioCategoria cambioCategoriaId;
	
	@ManyToOne
	@JoinColumn(name="bioanalista_vial_id", referencedColumnName = "id")
	private Usuario bioanalistaVialId;
	
	@ManyToOne
	@JoinColumn(name="motivo_mismo_ef", referencedColumnName = "id")
	private CatMismoEpfebril motivoMismoEf;
	
	//private Boolean retoma;
	private Boolean completarVol;
	private Boolean pruebaRapida;
	private Boolean mxPapelFiltro;
	private Boolean mxPapelFiltroEnviada;
	private Boolean mxSeparada;
	//private String codLab;
	//private String codLabScan;
	private Boolean mxNoTomada;
	private Integer anioEstSegunFif;
	//private String horaRefrigeracion;
	@Temporal(TemporalType.DATE)
	private Date fechaSeparacion;
	private String horaSeparacion;
	private Integer numViales;
	private Float volumenSuero;
	private String horaRefrigeracionVial;
	private Integer numeroPruebas;
	private String resultado;
	private String observacionMxSeparada;
	private Boolean procInmediato;
	private String observacionPrRapida;
	private Boolean positivoZika;
	private Boolean orina;
	private Boolean saliva;
	private Boolean tienePbmc;
	private Boolean tienePaxGene;
	private Boolean tieneBhc;
	//private String motivoMismoEf;
	//private Time tiempoProcesamiento;
}
