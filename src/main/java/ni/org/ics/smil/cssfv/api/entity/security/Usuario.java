package ni.org.ics.smil.cssfv.api.entity.security;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;
import org.springframework.data.history.RevisionMetadata;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "seg_usuario")
@Audited(withModifiedFlag = true)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombres;
	private String apellidos;
	private String usuario;
	private int codigoPersonal;
	private String clave;
	
	private String correo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimoAcceso;
	
	private String imagenUrl;
	private boolean activo;
	
	@JsonIgnore
	@Transient
	private RevisionMetadata<Integer> editVersion;
}
