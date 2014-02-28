package analytic.ofofo.net.apis.gmail.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import analytic.ofofo.net.apis.gmail.api.IGmail;
import analytic.ofofo.net.apis.gmail.avro.Mail;
import analytic.ofofo.net.apis.gmail.model.Bcc;
import analytic.ofofo.net.apis.gmail.model.Cc;
import analytic.ofofo.net.apis.gmail.model.Email;
import analytic.ofofo.net.apis.gmail.model.From;
import analytic.ofofo.net.apis.gmail.model.Reply_to;
import analytic.ofofo.net.apis.gmail.model.To;
import analytic.ofofo.net.apis.gmail.utils.MailSettings;

public class MailReader implements IGmail{
	
	private static final Logger LOG = LoggerFactory.getLogger(MailReader.class );
	
	private static final String ENCODING = "ISO-8859-1";

	private String login;
	
	private String passwd;
	
	private String provider;
	
	private Message [] allMsgCache = null;
	
	private	List<Email> allMail = new ArrayList<Email>();
	
	public List<Email> getAllMail() {
		return allMail;
	}


	public void setAllMail(List<Email> allMail) {
		this.allMail = allMail;
	}

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
		this(login, passwd, null);
	}
	
	public MailReader(String login, String passwd, String provider){
		this.login = login;
		this.passwd = passwd;
		this.provider = provider;
	}
	
	public MailReader(){
		this.login = "fifiant";
		this.passwd = "R2507zopipo";
		this.provider = "gmail";
	}
	public String loadMailSetting(){
		String set  = null;
		
		if(this.getProvider()==null){
			set = MailSettings.GMAIL;
			LOG.info(" Provider Settings: " + MailSettings.GMAIL);
		}else if(this.getProvider().equalsIgnoreCase("gmail")){
			set = MailSettings.GMAIL;
			LOG.info(" Provider Settings: " + MailSettings.GMAIL);
		}else if(this.getProvider().equalsIgnoreCase("yahoo")){
			set = MailSettings.YAHOO;
			LOG.info(" Provider Settings: " + MailSettings.YAHOO);
		}
		return set;
	}
	
	public  int getMessagesCount(){
		return this.getMessages().length;
	}
	
	public String getProvider() {
		return provider;
	}


	public void setProvider(String provider) {
		this.provider = provider;
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
			throw new IllegalArgumentException("Error: illegal arguments.");
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
		
		Message messages[] = null;
		try {
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect(this.loadMailSetting(), this.getLogin(), this.getPasswd());
			LOG.info(" Trying authentification on " + this.getProvider());

			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_ONLY);
			
			messages = inbox.getMessages();
			LOG.info("Total messages load from " + this.getProvider() + "are " + messages.length);
			this.setMessages(messages); //set Message
			
		} catch (NoSuchProviderException e) {
			LOG.error(e.getMessage());
			//System.exit(1);
		} catch (MessagingException e) {
			LOG.error(e.getMessage());
			//System.exit(2);
		}
		allMsgCache = messages; // set the messages inside cache
		LOG.debug("Cache size : " + allMsgCache.length + "\n\n\n\n\n\n\n\n");
		return messages;
	}
	
	/**
	 * @param number
	 * @return
	 */
	private Message [] messagesToLoad(int number){
		Message [] messages = null;
		if(allMsgCache == null || allMsgCache.length==0){
			messages = Arrays.copyOf(collectEmail(), number);
			LOG.debug("Cache is empty \n\n\n\n\n\n\n\n\n");
		}else {
			messages = Arrays.copyOf(allMsgCache, number);
			LOG.debug("Cache is not empty \n\n\n\n\n\n\n\n\n");
		}
		return messages;
	}
	/**
	 * @param messageNumber
	 * @return
	 */
	private Message getMessages(int messageNumber){
		Message messages[] = collectEmail();
		if(messageNumber<0){
			LOG.error("Illegal arguments");
			throw new IllegalArgumentException("Id is negative.");
		}
		return messageNumber < messages.length ? messages[messageNumber] : messages[0];
	}
	public void emailSerial() throws MessagingException{
		List<Email> allMails = new ArrayList<Email>();
//		Message [] messages =  new Message[1];
//		messages[0] = getMessages(399);
		Message messages[] = messagesToLoad(100);
		allMails = messagesMapper(messages);
		//Email json file 
		File mf = new File("mail.json"); 
		LOG.info("Initialize the file name");
		try {
			writeJsonFile(mf, allMails);
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
	}
	
	public List<Email> getFirstMail() throws MessagingException{
		Message messages[] = messagesToLoad(1);
		return messagesMapper(messages);
	}


	private List<Email> messagesMapper(Message[] messages) throws MessagingException {
		List<Email> allMails = new ArrayList<Email>();
		
		for (int i =0; i< messages.length; i++) {
			try {
				CharSequence body = null;
				CharSequence message_id  = getMsgId(); //message_id = UUID
				CharSequence thread_id = String.valueOf(i); //hread_Id = message number in the loop
				CharSequence in_reply_to = (decomposeFrom(messages[i].getFrom()[0]))[1];
				CharSequence subject = messages[i].getSubject();
				if(this.getProvider().equalsIgnoreCase("gmail")){
					body = formatContent(messages[i].getContent());
				}else if(this.getProvider().equalsIgnoreCase("yahoo")){
					body = messages[i].getContent().toString();
				}else{
					body = formatContent(messages[i].getContent());
				}
				CharSequence date = String.valueOf(messages[i].getSentDate());
				From f = adressSplitter(messages[i].getFrom()[0]);
				Email email = new Email(message_id, thread_id, in_reply_to, subject, body, date, f, decomposeTos(messages[i].getRecipients(RecipientType.TO)), decomposeCc(messages[i].getRecipients(RecipientType.CC)), decomposeBcc(messages[i].getRecipients(RecipientType.BCC)), decomposeReply(messages[i].getAllRecipients(),f));
				allMails.add(email);
			} catch (IOException e) {
				LOG.error(e.getMessage());
			}
		}
		return allMails;
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
					LOG.error(e.getMessage());
				}
			} catch (MessagingException e) {
				LOG.error(e.getMessage());
			}
		} else {
			content = obj.toString();
		}
		return content;
	}
	/*
	private static String decode(String value, String encode) throws UnsupportedEncodingException{
		
		Charset UTF8_CHARSET = Charset.forName(encode);
		return new String(getBytesIso8859_1(value), UTF8_CHARSET);
	}

	public static byte[] getBytesIso8859_1(final String string) {
		return getBytes(string, Charsets.ISO_8859_1);
	}

	private static byte[] getBytes(final String string, final Charset charset) {
		if (string == null) {
			return null;
		}
		return string.getBytes(charset);
	}
	*/
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
	private static From adressSplitter(Address address){
		String pattern = "([<,>])";
		String [] tab = null;
		String [] temp = null;
		From f ;
		if(address.toString().indexOf('@') !=-1){
			temp  =  address.toString().split("<");
			if(temp.length > 1){
				f = new From(temp[0], temp[1].replaceAll(pattern, ""));
			}else {
				f = new From(null, temp[0].replaceAll(pattern, ""));
			}
		}else{
			f = new From(address.toString(), null);
		}
		return f;
	}
	
	/**
	 * @param f
	 * @param all
	 * @return
	 */
	private static Address[] mashup(From f, Address[] all) {
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
	private static List<To> decomposeTos(Address[] address){ //TOs
		String pattern = "([<,>])";
		Map<String, String> map = new HashMap<String, String>();
		List<To> t = new ArrayList<To>();
		if (address != null) {
			for (Address ad : address) {
				int j = ad.toString().indexOf('@');
				if(j!= -1){
					String [] temp = ad.toString().split("<");
					if(temp.length > 1){
						t.add(new To(temp[0], temp[1].replaceAll(pattern, "")));
					}else{
						t.add(new To(null, ad.toString().split(" ")[0].replaceAll(pattern, "")));
					}
				}else{
					t.add(new To(ad.toString(), null));
				}
			}
		}
		return t;
	}
	
	/**
	 * @param address
	 * @return
	 */
	private static List<Cc> decomposeCc(Address[] address){ //Cc
		String pattern = "([<,>])";
		Map<String, String> map = new HashMap<String, String>();
		List<Cc> c = new ArrayList<Cc>();
		if (address != null) {
			for (Address ad : address) {
				int j = ad.toString().indexOf('@');
				if(j!= -1){
					String [] temp = ad.toString().split("<");
					if(temp.length > 1){
						c.add(new Cc(temp[0], temp[1].replaceAll(pattern, "")));
						map.put(temp[0],temp[1].replaceAll(pattern, ""));
					}else{
						c.add(new Cc(null, ad.toString().split(" ")[0].replaceAll(pattern, "")));
					}
				}else{
					c.add(new Cc(ad.toString(), null));
				}
			}
		}
		return c;
	}
	
	/**
	 * @param address
	 * @return
	 */
	private static List<Bcc> decomposeBcc(Address[] address){ //Cc
		String pattern = "([<,>])";
		Map<String, String> map = new HashMap<String, String>();
		List<Bcc> b = new ArrayList<Bcc>();
		if (address != null) {
			for (Address ad : address) {
				int j = ad.toString().indexOf('@');
				if(j!= -1){
					String [] temp = ad.toString().split("<");
					if(temp.length > 1){
						b.add(new Bcc(temp[0], temp[1].replaceAll(pattern, "")));
						map.put(temp[0],temp[1].replaceAll(pattern, ""));
					}else{
						b.add(new Bcc(null, ad.toString().split(" ")[0].replaceAll(pattern, "")));
					}
				}else{
					b.add(new Bcc(ad.toString(), null));
				}
			}
		}
		return b;
	}

	/**
	 * @param address
	 * @return
	 */
	private static List<Reply_to> decomposeRepl(Address[] address){ //Cc
		String pattern = "([<,>])";
		Map<String, String> map = new HashMap<String, String>();
		List<Reply_to> r = new ArrayList<Reply_to>();
		if (address != null) {
			for (Address ad : address) {
				int j = ad.toString().indexOf('@');
				if(j!= -1){
					String [] temp = ad.toString().split("<");
					if(temp.length > 1){
						r.add(new Reply_to(temp[0], temp[1].replaceAll(pattern, "")));
					}else{
						r.add(new Reply_to(null, ad.toString().split(" ")[0].replaceAll(pattern, "")));
					}
				}else{
					r.add(new Reply_to(ad.toString(), null));
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
	private static List<Reply_to> decomposeReply(Address[] address, From f){ //Cc
		String pattern = "([<,>])";
		Map<String, String> map = new HashMap<String, String>();
		List<Reply_to> r = new ArrayList<Reply_to>();
		if (address != null) {
			for (Address ad : address) {
				int j = ad.toString().indexOf('@');
				if(j!= -1){
					String [] temp = ad.toString().split("<");
					if(temp.length > 1){
						r.add(new Reply_to(temp[0], temp[1].replaceAll(pattern, "")));
					}else{
						r.add(new Reply_to(null, ad.toString().split(" ")[0].replaceAll(pattern, "")));
					}
				}else{
					r.add(new Reply_to(ad.toString(), null));
				}
			}
		}
		// add from address here
		r.add(new Reply_to(f.getRealName(), f.getAddress()));
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
	
	public String writeJsonToString(List<Email> mail) throws IOException {
		GenericDatumWriter writer = new GenericDatumWriter(Mail.SCHEMA);
		OutputStream fos = new OutputStream() {
			private StringBuilder string = new StringBuilder();
			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b );
			}
	        public String toString(){
	            return this.string.toString();
	        }
		};
		Encoder e = EncoderFactory.get().jsonEncoder(Mail.SCHEMA,fos);

		for (Email m : mail) {
			writer.write(m, e);
		}
		e.flush();
		fos.flush();
		byte[] latin1 = fos.toString().getBytes(ENCODING);
		//return fos.toString();
		return new String(latin1);
	}
	
	/*
	public static void main(String args[]) {
		MailReader mail = new MailReader("identifier", "pass", "provider_name");
		try {
			mail.emailSerial();
		} catch (MessagingException e) {
			e.printStackTrace();
		} 

	}
	*/
	
}
