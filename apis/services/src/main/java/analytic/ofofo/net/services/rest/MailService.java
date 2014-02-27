package analytic.ofofo.net.services.rest;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import analytic.ofofo.net.apis.gmail.model.Email;

@Path("/1.0/")
public interface MailService {
	
	@GET
	@Path("{mails}")
	@Descriptions({
		@Description(value = "returns all mails messages", target = DocTarget.METHOD),
		@Description(value = "the messages data", target = DocTarget.RETURN)
	})
	public Collection<Email> readAllMails();
}
