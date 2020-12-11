package ni.org.ics.smil.cssfv.api.exceptions;

import java.util.Date;

import lombok.Data;

@Data
public class ApiError {

	public ApiError() {}
	
	public ApiError(Date timestamp, String message) {
		super();
		this.timestamp = timestamp;
		this.message = message;
	}
	private Date timestamp;
	private String message;
	
	
	
}
