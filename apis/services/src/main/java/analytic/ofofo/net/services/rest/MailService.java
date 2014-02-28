package analytic.ofofo.net.services.rest;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import analytic.ofofo.net.apis.gmail.model.Email;

@Path("/1.0/mails/")
public interface MailService {
	
	@GET
	@Path("*")
	@Descriptions({
		@Description(value = "returns all mails messages", target = DocTarget.METHOD),
		@Description(value = "the messages data", target = DocTarget.RETURN)
	})
	public Collection<Email> readAllMails();
	
	
	@GET
	@Path("/first")
	@Produces("application/json")
	@Descriptions({
		@Description(value = "returns the first e-mail messages", target = DocTarget.METHOD),
		@Description(value = "the first message data", target = DocTarget.RETURN)
	})
	public String getFirstMail();

	
	@GET
	@Path("/last")
	@Descriptions({
		@Description(value = "returns the last e-mail messages", target = DocTarget.METHOD),
		@Description(value = "the last message data", target = DocTarget.RETURN)
	})
	public Email getLastMail();

	
	@GET
	@Path("/bottom/{msgId}")
	@Descriptions({
		@Description(value = "returns the bottom e-mail messages", target = DocTarget.METHOD),
		@Description(value = "the bottom message data", target = DocTarget.RETURN)
	})
	public Email getBottomMail(@Description(value = "the integer representation of the e-mail message ID") @PathParam("msgId") int msgId);

	
	@GET
	@Path("/top/{msgId}")
	@Descriptions({
		@Description(value = "returns the top e-mail messages", target = DocTarget.METHOD),
		@Description(value = "the top message data", target = DocTarget.RETURN)
	})
	public Email getTopMail(@Description(value = "the integer representation of the e-mail message ID ") @PathParam("msgId") int msgId);

	@GET
	@Path("/size")
	@Descriptions({
		@Description(value = "returns the size of e-mail messages", target = DocTarget.METHOD),
		@Description(value = "the size of message", target = DocTarget.RETURN)
	})
	public Email getMailSize();

	
	@GET
	@Path("/message/{msgId}")
	@Descriptions({
		@Description(value = "returns the corresponding e-mail message", target = DocTarget.METHOD),
		@Description(value = "the e-mail message data", target = DocTarget.RETURN)
	})
	public Email getMail(@Description(value = "the integer representation of the e-mail message ID") @PathParam("msgId") int msgId);

	
	@GET
	@Path("/between/{fMsgId}/{lMsgId}")
	@Descriptions({
		@Description(value = "returns the corresponding e-mail message", target = DocTarget.METHOD),
		@Description(value = "the e-mail message data", target = DocTarget.RETURN)
	})
	public Email getBetweenMail(@Description(value = "the integer representation of the e-mail message ID") @PathParam("fMsgId") int fMsgId,@Description(value = "the integer representation of the e-mail message ID") @PathParam("lMsgId") int lMsgId);
	
}
