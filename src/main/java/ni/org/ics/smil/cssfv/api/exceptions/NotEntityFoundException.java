package ni.org.ics.smil.cssfv.api.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class NotEntityFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final String FORMATO = "%s no encontrado con %s = %s";
	private static final long serialVersionUID = -6075793413610505506L;
	
	public NotEntityFoundException(String message) {
		super(message);
	}

	public NotEntityFoundException(String entity, String property, String value) {
		super(String.format(FORMATO, entity, property, value));	
	}
}
