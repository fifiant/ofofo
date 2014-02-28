package analytic.ofofo.net.services.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;

public class MailServiceSopEndpoint implements MailService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Validator validator;
	
	@Override
	public String getFirstMail() {
		// TODO Auto-generated method stub
		return null;
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
