package analytic.ofofo.net.apis.gmail.api;

import javax.mail.Message;
import javax.mail.MessagingException;

public interface IGmail {
	
	public void emailSerial() throws MessagingException;
	 
	public Message getLastMessages();
	
	public Message [] getTopMessages(int number);
	
	public Message [] getBottomMessages(int number);
	
	public Message [] getMsgBetween(int numbera, int numberb);
	
	public Message getMessage(int messageNumber);
	
	public int getMessagesCount();
	
	
}
