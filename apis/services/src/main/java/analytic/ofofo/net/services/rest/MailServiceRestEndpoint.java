package analytic.ofofo.net.services.rest;

import java.io.IOException;

import analytic.ofofo.net.apis.gmail.impl.MailReader;

public class MailServiceRestEndpoint implements MailService{
	
	
	public MailReader mr = new MailReader();
	public MailServiceRestEndpoint(){
		
	}
	
	@Override
	public String getFirstMail() {
		String value = null;
		try {
			mr.load();
			value = mr.getAllMsg().get(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public String getLastMail() {
		String value = null;
		try {
			mr.load();
			value = mr.getAllMsg().get(mr.getAllMsg().size()-1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public String getBottomMail(int msgId) {
		String value = null;
		try {
			mr.load();
			value = mr.arrayToString(mr.top(msgId));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public String getTopMail(int msgId) {
		String value = null;
		try {
			mr.load();
			value = mr.arrayToString(mr.top(msgId));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public String getMailSize() {
		String value = null;
		try {
			mr.load();
			value = String.valueOf(mr.getAllMsg().size());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
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
