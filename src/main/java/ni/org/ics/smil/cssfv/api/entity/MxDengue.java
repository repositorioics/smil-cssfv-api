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
import ni.org.ics.smil.cssfv.api.entity.catalogos.CatCambioCategoria;
import ni.org.ics.smil.cssfv.api.entity.catalogos.CatCategoria;
import ni.org.ics.smil.cssfv.api.entity.catalogos.CatConsulta;
import ni.org.ics.smil.cssfv.api.entity.catalogos.CatTipoPrueba;
import ni.org.ics.smil.cssfv.api.entity.catalogos.CatTubo;

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
	
	private Boolean retoma;
	private Boolean completarVol;
	private Boolean pruebaRapida;
	private Boolean mxPapelFiltro;
	private Boolean mxPapelFiltroEnviada;
	private Boolean mxSeparada;
	private String codLab;
	private Boolean mxNoTomada;
	private Integer anioEstSegunFif;
	private Time horaRefrigeracion;
	@Temporal(TemporalType.DATE)
	private Date fechaSeparacion;
	private Time horaSeparacion;
	private Integer numViales;
	private Float volumenSuero;
	private Time horaRefrigeracionVial;
	private Integer numeroPruebas;
	private String resultado;
	//private Time tiempoProcesamiento;
}
