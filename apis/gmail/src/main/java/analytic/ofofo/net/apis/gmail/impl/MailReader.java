package analytic.ofofo.net.apis.gmail.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.commons.compress.utils.Charsets;

import analytic.ofofo.net.apis.gmail.api.IGmail;
import analytic.ofofo.net.apis.gmail.avro.Mail;
import analytic.ofofo.net.apis.gmail.model.Email;
import analytic.ofofo.net.apis.gmail.model.bcc;
import analytic.ofofo.net.apis.gmail.model.cc;
import analytic.ofofo.net.apis.gmail.model.from;
import analytic.ofofo.net.apis.gmail.model.reply_to;
import analytic.ofofo.net.apis.gmail.model.to;

public class MailReader implements IGmail{
	
	private String login;
	private String passwd;
	private Message [] messages;
	
	public Message[] getMessages() {
		return messages;
	}


	public void setMessages(Message[] messages) {
		this.messages = messages;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPasswd() {
		return passwd;
	}


	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public MailReader(String login, String passwd){
		this.login = login;
		this.passwd = passwd;
	}
	
	
	public  int getMessagesCount(){
		return this.getMessages().length;
	}

	/* (non-Javadoc)
	 * @see analytic.ofofo.net.apis.gmail.api.IGmail#getMessage(int)
	 */
	public Message getMessage(int messageNumber){
		Message msg = null;
		if(messageNumber<this.getMessagesCount()){
			msg = this.getMessages()[messageNumber];
		}
		
		return msg;
	}
	
	/* (non-Javadoc)
	 * @see analytic.ofofo.net.apis.gmail.api.IGmail#getLastMessages()
	 */
	public Message getLastMessages(){
		return this.getMessages()[this.getMessagesCount() - 1];
	}
	
	/* (non-Javadoc)
	 * @see analytic.ofofo.net.apis.gmail.api.IGmail#getTopMessages(int)
	 */
	public Message [] getTopMessages(int number){
		Message [] temp = null;
		if(number > 1){
		System.arraycopy(this.getMessages(), 0, temp, 0, number);
		}
		return temp;
	}
	
	/* (non-Javadoc)
	 * @see analytic.ofofo.net.apis.gmail.api.IGmail#getBottomMessages(int)
	 */
	public Message [] getBottomMessages(int number){
		Message [] temp = null;
		if(number > 1){
		System.arraycopy(this.getMessages(), 0, temp, 0, number);
		}
		return temp;
	}
	
	/* (non-Javadoc)
	 * @see analytic.ofofo.net.apis.gmail.api.IGmail#getMsgBetween(int, int)
	 */
	public Message [] getMsgBetween(int numbera, int numberb){
		Message [] temp = null;
		if(numbera <0 && numberb>this.getMessagesCount() && numberb - numbera > 0){
			throw new IllegalArgumentException("Error: illegal argument.");
		}
		
		System.arraycopy(this.getMessages(), numbera, temp, 0, numberb - numbera +1);
		
		return null;
	}
	/**
	 * @return
	 */
	private Message [] collectEmail(){// download message from server
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
	    String login = System.getenv("login") ;
	    String kokoro = System.getenv("kokoro") ;
		
		Message messages[] = null;
		try {
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", this.getLogin(), "xxxxx");
			System.out.println(store);

			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_ONLY);
			
			messages = inbox.getMessages();
			this.setMessages(messages); //set Message
			
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (MessagingException e) {
			e.printStackTrace();
			System.exit(2);
		}
		
		return messages;
	}
	
	/**
	 * @param number
	 * @return
	 */
	private Message [] messagesToLoad(int number){
		Message [] messages = Arrays.copyOf(collectEmail(), number);
		return messages;
	}
	/**
	 * @param messageNumber
	 * @return
	 */
	private Message getMessages(int messageNumber){
		Message messages[] = collectEmail();
		if(messageNumber<0){
			 throw new IllegalArgumentException("Id is negative.");
		}
		return messageNumber < messages.length ? messages[messageNumber] : messages[0];
	}
	public void emailSerial() throws MessagingException{
//		Message [] messages =  new Message[1];
//		messages[0] = getMessages(399);
		Message messages[] = messagesToLoad(400);
		List<Email> allMail = new ArrayList<Email>();
		//messages.length
		for (int i =0; i< messages.length; i++) {
			try {
				CharSequence message_id  = getMsgId(); //message_id = UUID
				CharSequence thread_id = String.valueOf(i); //hread_Id = message number in the loop
				CharSequence in_reply_to = (decomposeFrom(messages[i].getFrom()[0]))[1];
				CharSequence subject = messages[i].getSubject();
				CharSequence body = formatContent(messages[i].getContent());
				CharSequence date = String.valueOf(messages[i].getSentDate());
				from f = adressSplitter(messages[i].getFrom()[0]);
				Email email = new Email(message_id, thread_id, in_reply_to, subject, body, date, f, decomposeTos(messages[i].getRecipients(RecipientType.TO)), decomposeCc(messages[i].getRecipients(RecipientType.CC)), decomposeBcc(messages[i].getRecipients(RecipientType.BCC)), decomposeReply(messages[i].getAllRecipients(),f));
				allMail.add(email);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//Email json file 
		File mf = new File("mail.json"); 
		try {
			writeJsonFile(mf, allMail);
		} catch (IOException e) {
			System.err.println("Main: " + e.getMessage());
		}
	}
	
	/**
	 * @param obj
	 * @return
	 */
	private static String formatContent(Object obj) {
		String content = null;
		if (obj instanceof Multipart) {
			Multipart mp = (Multipart) obj;
			try {
				BodyPart bp = mp.getBodyPart(0);
				try {
					content = bp.getContent().toString();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		} else {
			content = obj.toString();
		}
		return content;
	}
	/**
	 * @param value
	 * @param encode
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String decode(String value, String encode) throws UnsupportedEncodingException{
		
		Charset UTF8_CHARSET = Charset.forName(encode);
		return new String(getBytesIso8859_1(value), UTF8_CHARSET);
	}

	/**
	 * @param string
	 * @return
	 */
	public static byte[] getBytesIso8859_1(final String string) {
		return getBytes(string, Charsets.ISO_8859_1);
	}

	private static byte[] getBytes(final String string, final Charset charset) {
		if (string == null) {
			return null;
		}
		return string.getBytes(charset);
	}
	
	/**
	 * @param address
	 * @return
	 */
	private static String[] decomposeFrom(Address address){
		String pattern = "([<,>])";
		String [] temp  =  address.toString().split(" ");
		String [] tab = null;
		if(temp.length > 1){
			tab = new String [] {temp[0],temp[1].replaceAll(pattern, "")};
		}else {
			tab = new String [] {null,temp[0].replaceAll(pattern, "")};		
		}
		return tab;
	}
	
	/**
	 * @param address
	 * @return
	 */
	private static from adressSplitter(Address address){
		String pattern = "([<,>])";
		String [] tab = null;
		String [] temp = null;
		from f ;
		if(address.toString().indexOf('@') !=-1){
			temp  =  address.toString().split("<");
			if(temp.length > 1){
				f = new from(temp[0], temp[1].replaceAll(pattern, ""));
			}else {
				f = new from(null, temp[0].replaceAll(pattern, ""));
			}
		}else{
			f = new from(address.toString(), null);
		}
		return f;
	}
	
	/**
	 * @param f
	 * @param all
	 * @return
	 */
	private static Address[] mashup(from f, Address[] all) {
		Address[] a = new Address[1];
		CharSequence tmp = "<" + f.getAddress() + ">";
		a[0] = (Address) tmp; //this is not good ...
		int newsize = (a != null ? a.length : 0)
				+ (all != null ? all.length : 0);
		Address[] addresses = new Address[newsize];
		int pos = 0;

		if (a != null) {
			System.arraycopy(a, 0, addresses, pos, a.length);
			pos += a.length;
		}

		if (all != null) {
			System.arraycopy(all, 0, addresses, pos, all.length);
		}

		return addresses;
	}
	
	/**
	 * @param address
	 * @return
	 */
	private static Map<String, String> decomposeMailFrom(Address address){
		String pattern = "([<,>])";
		String [] temp  =  address.toString().split(" ");
		Map<String, String> map = new HashMap<String, String>();
		if(temp.length > 1){
			map.put(temp[0], temp[1].replaceAll(pattern, ""));
		}else {
			map.put(null, temp[0].replaceAll(pattern, ""));		
		}
		return map;
	}
	
	/**
	 * @param address
	 * @return
	 */
	private static Map<String, String> decomposeDest(Address[] address){ //TOs, CCs, BCCs
		String pattern = "([<,>])";
		Map<String, String> map = new HashMap<String, String>();
		if (address != null) {
			for (Address ad : address) {
				int j = ad.toString().indexOf('@');
				if(j!= -1){
					String [] temp = ad.toString().split("<");
					if(temp.length > 1){
						map.put(temp[0],temp[1].replaceAll(pattern, ""));
					}else{
						map.put(null,address.toString().split(" ")[0].replaceAll(pattern, ""));
					}
				}else{
					map.put(ad.toString(),null);
				}
			}
		}
		return map;
	}
	
	/**
	 * @param address
	 * @return
	 */
	private static List<to> decomposeTos(Address[] address){ //TOs
		String pattern = "([<,>])";
		Map<String, String> map = new HashMap<String, String>();
		List<to> t = new ArrayList<to>();
		if (address != null) {
			for (Address ad : address) {
				int j = ad.toString().indexOf('@');
				if(j!= -1){
					String [] temp = ad.toString().split("<");
					if(temp.length > 1){
						t.add(new to(temp[0], temp[1].replaceAll(pattern, "")));
					}else{
						t.add(new to(null, ad.toString().split(" ")[0].replaceAll(pattern, "")));
					}
				}else{
					t.add(new to(ad.toString(), null));
				}
			}
		}
		return t;
	}
	
	/**
	 * @param address
	 * @return
	 */
	private static List<cc> decomposeCc(Address[] address){ //Cc
		String pattern = "([<,>])";
		Map<String, String> map = new HashMap<String, String>();
		List<cc> c = new ArrayList<cc>();
		if (address != null) {
			for (Address ad : address) {
				int j = ad.toString().indexOf('@');
				if(j!= -1){
					String [] temp = ad.toString().split("<");
					if(temp.length > 1){
						c.add(new cc(temp[0], temp[1].replaceAll(pattern, "")));
						map.put(temp[0],temp[1].replaceAll(pattern, ""));
					}else{
						c.add(new cc(null, ad.toString().split(" ")[0].replaceAll(pattern, "")));
					}
				}else{
					c.add(new cc(ad.toString(), null));
				}
			}
		}
		return c;
	}
	
	/**
	 * @param address
	 * @return
	 */
	private static List<bcc> decomposeBcc(Address[] address){ //Cc
		String pattern = "([<,>])";
		Map<String, String> map = new HashMap<String, String>();
		List<bcc> b = new ArrayList<bcc>();
		if (address != null) {
			for (Address ad : address) {
				int j = ad.toString().indexOf('@');
				if(j!= -1){
					String [] temp = ad.toString().split("<");
					if(temp.length > 1){
						b.add(new bcc(temp[0], temp[1].replaceAll(pattern, "")));
						map.put(temp[0],temp[1].replaceAll(pattern, ""));
					}else{
						b.add(new bcc(null, ad.toString().split(" ")[0].replaceAll(pattern, "")));
					}
				}else{
					b.add(new bcc(ad.toString(), null));
				}
			}
		}
		return b;
	}

	/**
	 * @param address
	 * @return
	 */
	private static List<reply_to> decomposeRepl(Address[] address){ //Cc
		String pattern = "([<,>])";
		Map<String, String> map = new HashMap<String, String>();
		List<reply_to> r = new ArrayList<reply_to>();
		if (address != null) {
			for (Address ad : address) {
				int j = ad.toString().indexOf('@');
				if(j!= -1){
					String [] temp = ad.toString().split("<");
					if(temp.length > 1){
						r.add(new reply_to(temp[0], temp[1].replaceAll(pattern, "")));
					}else{
						r.add(new reply_to(null, ad.toString().split(" ")[0].replaceAll(pattern, "")));
					}
				}else{
					r.add(new reply_to(ad.toString(), null));
				}
			}
		}
		return r;
	}
	
	/**
	 * @param address
	 * @param f
	 * @return
	 */
	private static List<reply_to> decomposeReply(Address[] address, from f){ //Cc
		String pattern = "([<,>])";
		Map<String, String> map = new HashMap<String, String>();
		List<reply_to> r = new ArrayList<reply_to>();
		if (address != null) {
			for (Address ad : address) {
				int j = ad.toString().indexOf('@');
				if(j!= -1){
					String [] temp = ad.toString().split("<");
					if(temp.length > 1){
						r.add(new reply_to(temp[0], temp[1].replaceAll(pattern, "")));
					}else{
						r.add(new reply_to(null, ad.toString().split(" ")[0].replaceAll(pattern, "")));
					}
				}else{
					r.add(new reply_to(ad.toString(), null));
				}
			}
		}
		// add from address here
		r.add(new reply_to(f.getRealName(), f.getAddress()));
		return r;
	}
	
	/**
	 * @return
	 */
	private static String getMsgId(){
		return UUID.randomUUID().toString();
	}
	
	/**
	 * @param file
	 * @param mail
	 * @throws IOException
	 */
	public static void writeBinaryFile(File file, Mail[] mail)
			throws IOException {
		GenericDatumWriter datum = new GenericDatumWriter(Mail.SCHEMA);
		DataFileWriter writer = new DataFileWriter(datum);

		writer.create(Mail.SCHEMA, file);

		for (Mail m : mail) {
			writer.append(m.serialize());
		}

		writer.close();
	}

	/**
	 * @param file
	 * @param mail
	 * @throws IOException
	 */
	public static void writeJsonFile(File file, List<Email> mail)
			throws IOException {
		GenericDatumWriter writer = new GenericDatumWriter(Mail.SCHEMA);
		Encoder e = EncoderFactory.get().jsonEncoder(Mail.SCHEMA, new FileOutputStream(file));

		for (Email m : mail) {
			writer.write(m, e);
		}
		e.flush();
	}
	
	/*
	public static void main(String args[]) {
		try {
			emailSerial();
		} catch (MessagingException e) {
			e.printStackTrace();
		} 

	}
	
*/
}
