package analytic.ofofo.net.services.rest;

import java.util.LinkedList;
import java.util.List;

import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import analytic.ofofo.net.services.exception.meta.MailResponseExceptionMapper;

public class MailServiceClient {
	
	private MailService mailService;
	
	private static final String REST = "/service/1.0/rest/";
	private static final String SOAP = "/service/1.0/rest/";
	
	public MailServiceClient(String applicationURI, CLIENT_TYPE clientType){
		if (clientType == CLIENT_TYPE.REST) {
				List<Object> providers = new LinkedList<Object>();
				providers.add(new MailResponseExceptionMapper());
				mailService = JAXRSClientFactory.create(applicationURI + REST, MailService.class, providers, true);
				ClientConfiguration cfgProxy = WebClient.getConfig(mailService);
				cfgProxy.getHttpConduit().getAuthorization().setPassword("ofofouser");
				cfgProxy.getHttpConduit().getAuthorization().setUserName("ofofouser");
			} else {
				JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
				factory.setServiceClass(MailService.class);
				factory.setAddress(applicationURI + SOAP);
				factory.setUsername("ofofouser");
				factory.setPassword("ofofouser");
				mailService = (MailService) factory.create();
			}
	}
	
	public static enum CLIENT_TYPE {
		REST("REST"), SOAP("SOAP");

		private String value;

		private CLIENT_TYPE(String value) {
			this.value = value;
		}

		public static CLIENT_TYPE fromString(String value) {

			if (REST.value.equalsIgnoreCase(value)) {
				return REST;
			}

			if (SOAP.value.equalsIgnoreCase(value)) {
				return SOAP;
			}

			return null;
		}
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	};
	
	
}
