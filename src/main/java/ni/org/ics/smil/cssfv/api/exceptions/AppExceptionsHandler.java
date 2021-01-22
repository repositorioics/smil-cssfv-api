package ni.org.ics.smil.cssfv.api.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
		
		String message = ex.getLocalizedMessage();
		if (message == null) message = ex.toString(); 
		if (ex.getCause() != null && ex.getCause().getCause() != null) message += " --> "+ex.getCause().getCause().getLocalizedMessage();
		else if (ex.getCause() != null) message += " --> "+ex.getCause().getLocalizedMessage();
			
		ApiError errorMessage = new ApiError(new Date(), message);
		
		return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {NotEntityFoundException.class})
	public ResponseEntity<Object> handleNotEntityFoundException(NotEntityFoundException ex, WebRequest request) {
		String message = ex.getLocalizedMessage();
		if (message == null) message = ex.toString();
		ApiError errorMessage = new ApiError(new Date(), message);
		
		return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = {BadCredentialsException.class})
	public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
		String message = ex.getLocalizedMessage();
		if (message == null) message = ex.toString();
		ApiError errorMessage = new ApiError(new Date(), message);
		
		return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value = {DisabledException.class})
	public ResponseEntity<Object> handleDisabledException(DisabledException ex, WebRequest request) {
		String message = ex.getLocalizedMessage();
		if (message == null) message = ex.toString();
		ApiError errorMessage = new ApiError(new Date(), message);
		
		return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
	}
}
