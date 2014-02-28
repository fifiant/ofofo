package analytic.ofofo.net.services.exception.meta;

import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class MailBaseException extends WebApplicationException{
	
	private ExceptionMessage exceptionMessage;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1866016203950542055L;

	public MailBaseException(Status status, String message, Map<String, String> data){
		
		super(Response.status(status).entity(new ExceptionMessage(message, data)).build());
		
		this.setExceptionMessage((ExceptionMessage)getResponse().getEntity());
	}
	
	public MailBaseException(Status status, String message){
		this(status, message, null);
	}

	@Override
	public String getMessage() {
		return this.getExceptionMessage().getMessage();
	}

	public ExceptionMessage getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(ExceptionMessage exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	
	
}
