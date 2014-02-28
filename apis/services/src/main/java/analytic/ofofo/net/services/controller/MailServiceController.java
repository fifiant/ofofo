package analytic.ofofo.net.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import analytic.ofofo.net.services.rest.MailService;
import analytic.ofofo.net.services.rest.MailServiceClient;
import analytic.ofofo.net.services.rest.MailServiceRestEndpoint;
import analytic.ofofo.net.services.rest.MailServiceSoapEndpoint;

@Controller
@RequestMapping("/")
public class MailServiceController {
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	private String appURI;
	
	private MailServiceRestEndpoint mailServiceRestEndpoint;
	private MailServiceSoapEndpoint mailServiceSoapEndpoint;
	
	private MailService mailService;
	
	private MailService getMailService(HttpServletRequest request, MailServiceClient.CLIENT_TYPE clientType) {
		if (clientType.equals(MailServiceClient.CLIENT_TYPE.SOAP)) {
			if (mailService == null) {
				mailService = (new MailServiceClient(getApplicationURI(request),MailServiceClient.CLIENT_TYPE.SOAP)).getMailService();
			}

			return mailService;
		}

		if (clientType.equals(MailServiceClient.CLIENT_TYPE.REST)) {
			if (mailService == null) {
				mailService = (new MailServiceClient(getApplicationURI(request),MailServiceClient.CLIENT_TYPE.REST)).getMailService();
			}

			return mailService;
		}

		return null;
	}
	
	private String getApplicationURI(HttpServletRequest req) {
		if (appURI == null) {
			String port;

			if ("http".equalsIgnoreCase(req.getScheme()) && req.getServerPort() != 80 || "https".equalsIgnoreCase(req.getScheme()) && req.getServerPort() != 443) {
				port = ":" + req.getServerPort();
			} else {
				port = "";
			}

			appURI = req.getScheme() + "://" + req.getServerName() + port + req.getContextPath();

			LOG.debug("Application URL was set to " + appURI);
		}

		return appURI;
	}


}
