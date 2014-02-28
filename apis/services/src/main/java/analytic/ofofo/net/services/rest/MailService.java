package analytic.ofofo.net.services.rest;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

@Path("/1.0/mails/")
@WebService
public interface MailService {
	
	
	@WebMethod
	@GET
	@Path("/first")
	@Produces("application/json")
	@Descriptions({
		@Description(value = "returns the first e-mail messages", target = DocTarget.METHOD),
		@Description(value = "the first message data", target = DocTarget.RETURN)
	})
	public String getFirstMail();

	@WebMethod
	@GET
	@Path("/last")
	@Descriptions({
		@Description(value = "returns the last e-mail messages", target = DocTarget.METHOD),
		@Description(value = "the last message data", target = DocTarget.RETURN)
	})
	public String getLastMail();

	@WebMethod
	@GET
	@Path("/bottom/{msgId}")
	@Descriptions({
		@Description(value = "returns the bottom e-mail messages", target = DocTarget.METHOD),
		@Description(value = "the bottom message data", target = DocTarget.RETURN)
	})
	public String getBottomMail(@Description(value = "the integer representation of the e-mail message ID") @PathParam("msgId") int msgId);

	
	@WebMethod
	@GET
	@Path("/top/{msgId}")
	@Descriptions({
		@Description(value = "returns the top e-mail messages", target = DocTarget.METHOD),
		@Description(value = "the top message data", target = DocTarget.RETURN)
	})
	public String getTopMail(@Description(value = "the integer representation of the e-mail message ID ") @PathParam("msgId") int msgId);

	
	@WebMethod
	@GET
	@Path("/size")
	@Descriptions({
		@Description(value = "returns the size of e-mail messages", target = DocTarget.METHOD),
		@Description(value = "the size of message", target = DocTarget.RETURN)
	})
	public String getMailSize();

	
	@WebMethod
	@GET
	@Path("/message/{msgId}")
	@Descriptions({
		@Description(value = "returns the corresponding e-mail message", target = DocTarget.METHOD),
		@Description(value = "the e-mail message data", target = DocTarget.RETURN)
	})
	public String getMail(@Description(value = "the integer representation of the e-mail message ID") @PathParam("msgId") int msgId);

	
	@WebMethod
	@GET
	@Path("/between/{fMsgId}/{lMsgId}")
	@Descriptions({
		@Description(value = "returns the corresponding e-mail message", target = DocTarget.METHOD),
		@Description(value = "the e-mail message data", target = DocTarget.RETURN)
	})
	public String getBetweenMail(@Description(value = "the integer representation of the e-mail message ID") @PathParam("fMsgId") int fMsgId,@Description(value = "the integer representation of the e-mail message ID") @PathParam("lMsgId") int lMsgId);
	
}
