package analytic.ofofo.net.services.exception;

import java.util.Map;

import javax.ws.rs.core.Response.Status;

import analytic.ofofo.net.services.exception.meta.MailBaseException;

public class ValidationException extends MailBaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5711869469080351665L;
	
	public ValidationException(Map<String, String> value) {
		super(Status.BAD_REQUEST, "Validation failed", value);
	}
}
