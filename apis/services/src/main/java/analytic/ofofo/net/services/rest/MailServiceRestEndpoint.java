package analytic.ofofo.net.services.rest;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import analytic.ofofo.net.apis.gmail.impl.MailReader;

public class MailServiceRestEndpoint implements MailService{
	
	
	public MailReader mr = new MailReader();
//	ClassPathXmlApplicationContext appContext = new  ClassPathXmlApplicationContext(new String[] {this.getClass().getClassLoader().getResource("emailprocess.xml").toString()});
//	public MailReader mr = (MailReader) appContext.getBean("getFirstMail");
	public MailServiceRestEndpoint(){
		
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
		String value = null;
		try {
			value = mr.writeJsonToString(mr.getLastMail());
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public String getBottomMail(int msgId) {
		String value = null;
		try {
			value = mr.writeJsonToString(mr.getBottomMail(msgId));
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public String getTopMail(int msgId) {
		String value = null;
		try {
			value = mr.writeJsonToString(mr.getTopMail(msgId));
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
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
