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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mx_bhc")
public class MxBhc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="muestra_id", referencedColumnName = "id")
	private Muestra muestraId;
	
	private String motivoSinFif;
	private Boolean mxNoTomada;
	
	@Temporal(TemporalType.DATE)
	private Date fechaSeparacion;
	
	private Time horaSeparacion;
	private Time horaRefrigeracionVial;
	
	@Column(precision=4, scale=2) 
	private Float volumenVial;
}
