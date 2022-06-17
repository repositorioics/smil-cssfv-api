package ni.org.ics.smil.cssfv.api.entity.catalogs;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
Created by SC on 30/03/2022.
*/


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cat_recepcion")
public class CatRecepcion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long estudio;
	private String tipo;
	private String descripcion;
	private String criteriosEvaluar;
	private String cadenaCaracteresCodigo;
	private String descripcionCadena;
	private String expresionRegular;
	private Boolean activo;
	private String nombreEstudio;
}
