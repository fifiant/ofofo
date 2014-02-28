package analytic.ofofo.net.services.exception;

import javax.ws.rs.core.Response.Status;

import analytic.ofofo.net.services.exception.meta.MailBaseException;

public class MailNotFoundException extends MailBaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5319001551316059139L;

	public MailNotFoundException() {
		super(Status.NOT_FOUND, "Location not found");
	}

}
