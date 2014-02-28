package analytic.ofofo.net.services.exception.meta;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.cxf.jaxrs.client.ResponseExceptionMapper;

import analytic.ofofo.net.services.exception.MailNotFoundException;
import analytic.ofofo.net.services.exception.ValidationException;

public class MailResponseExceptionMapper implements ResponseExceptionMapper<MailBaseException>{

	@Override
	public MailBaseException fromResponse(Response r) {

		if (r.getStatus() == Status.NOT_FOUND.getStatusCode()) {
			return new MailNotFoundException();
		}

//		if (r.getStatus() == Status.CONFLICT.getStatusCode()) {
//			return new DuplicateMailException();
//		}

		if (r.getStatus() == Status.BAD_REQUEST.getStatusCode()) {

			JAXBContext context;
			try {
				context = JAXBContext.newInstance(ExceptionMessage.class);
				Unmarshaller um = context.createUnmarshaller();
				ExceptionMessage ed = (ExceptionMessage) um.unmarshal((InputStream) r.getEntity());
				
				return new ValidationException(ed.getValue());
				
			} catch (JAXBException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}

		}
		return null;
	}
	
}
