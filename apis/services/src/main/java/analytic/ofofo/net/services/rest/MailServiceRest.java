package analytic.ofofo.net.services.rest;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;

import analytic.ofofo.net.apis.gmail.impl.MailReader;
import analytic.ofofo.net.apis.gmail.model.Email;
import analytic.ofofo.net.services.exception.meta.MailResponseExceptionMapper;

public class MailServiceRest implements MailService{
	
	private MailService mailService;
	
	public MailReader mr = new MailReader();; 
	public MailServiceRest(){
		
	}
	public MailServiceRest(String appURI){
		List<Object> providers = new LinkedList<Object>();
		providers.add(new MailResponseExceptionMapper());
		mailService = JAXRSClientFactory.create(appURI + "/service/", MailService.class, providers, true);
		ClientConfiguration cfgProxy = WebClient.getConfig(mailService);
		cfgProxy.getHttpConduit().getAuthorization().setPassword("ofofouser");
		cfgProxy.getHttpConduit().getAuthorization().setUserName("ofofouser");
	}
	
	@Override
	public String getFirstMail() {
		//new MailServiceImpl("http://localhost:8080");
		String value = null;
		try {
			value = mr.writeJsonToString(mr.getFirstMail());
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public String getLastMail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBottomMail(int msgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTopMail(int msgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMailSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMail(int msgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBetweenMail(int fMsgId, int lMsgId) {
		// TODO Auto-generated method stub
		return null;
	}

}