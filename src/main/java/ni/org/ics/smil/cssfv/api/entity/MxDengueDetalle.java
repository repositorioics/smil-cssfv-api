package ni.org.ics.smil.cssfv.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ni.org.ics.smil.cssfv.api.entity.catalogs.CatTipoMuestra;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mx_dengue_detalle")
public class MxDengueDetalle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="muestra_dengue_detalle_id", referencedColumnName = "id")
	private MxDengue muestraDengueDetalleId;

	@ManyToOne
	@JoinColumn(name="tipo_muestra_id", referencedColumnName = "id")
	private CatTipoMuestra tipoMuestraId;
	
	private Long muestraDengueId;
}
