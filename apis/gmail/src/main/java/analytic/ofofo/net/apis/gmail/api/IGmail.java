package analytic.ofofo.net.apis.gmail.api;

import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;

import analytic.ofofo.net.apis.gmail.model.Email;

public interface IGmail {
	
	public void emailSerial() throws MessagingException;
	 
	public Message getLastMessages();
	
	public Message [] getTopMessages(int number);
	
	public Message [] getBottomMessages(int number);
	
	public Message [] getMsgBetween(int numbera, int numberb);
	
	public Message getMessage(int messageNumber);
	
	public int getMessagesCount();
	
	public List<Email> getFirstMail() throws MessagingException;
	
	public List<Email> getLastMail() throws MessagingException;
	
	public List<Email> getBottomMail(int msgId) throws MessagingException;
	
	public List<Email> getTopMail(int msgId) throws MessagingException;
	
	
}
