package analytic.ofofo.net.apis.gmail.avro;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;

public class Mail {
	public static Schema SCHEMA; //writer's schema

	static {
		try {
			SCHEMA = Schema.parse(Mail.class.getClassLoader().getResourceAsStream("email.avsc"));
		} catch (IOException e) {
			System.out.println("Couldn't load a schema: " + e.getMessage());
		}
	}
	
	private String message_id;
	private String thread_id;
	private String in_reply_to;
	private String subject;
	private String body;
	private String date;
	private Map<String, String> from;
	private Map<String, String> tos;
	private Map<String, String> ccs;
	private Map<String, String> bccs;
	private Map<String, String> reply_tos;
	
	public Mail(String message_id, String thread_id, String in_reply_to, String subject, String body, String date, Map<String, String> from, Map<String, String> tos, Map<String, String> ccs, Map<String, String> bccs, Map<String, String> reply_tos){
		this.message_id = message_id;
		this.thread_id = message_id;
		this.in_reply_to = in_reply_to;
		this.subject = subject;
		this.body = body;
		this.date = date;
		this.from = from;
		this.tos = tos;
		this.ccs = ccs;
		this.bccs= bccs;
		this.reply_tos = reply_tos;
	}
	public GenericData.Record serialize() {
		GenericData.Record record = new GenericData.Record(SCHEMA);

		record.put("message_id", this.message_id);
		record.put("thread_id", this.thread_id);
		record.put("in_reply_to", this.in_reply_to);
		record.put("subject", this.subject);
		record.put("body", this.body);
		record.put("date", this.date);
		
		// set from data
		GenericRecord grfrom = new GenericData.Record(SCHEMA.getField("from").schema());
		for (Entry<String, String> en : this.from.entrySet()){
			grfrom.put("real_name", en.getKey());
			grfrom.put("address", en.getValue());
		}
		record.put("from", grfrom);
		
		// set tos data
		System.out.println("size :"+ this.tos.size());
		System.out.println("Schema : "+ SCHEMA.getField("tos").schema());
		if( SCHEMA.getField("tos").schema() == null){
			System.out.println("Schema is null");
		}
		System.out.println("Type : " + SCHEMA.getField("tos").schema().getType());
		GenericData.Array artos = new GenericData.Array(this.tos.size(), SCHEMA.getField("tos").schema());
		GenericRecord grtos = new GenericData.Record(SCHEMA.getField("to").schema());
		for (Entry<String, String> en : this.tos.entrySet()){
			grtos.put("real_name", en.getKey());
			grtos.put("address", en.getValue());
		}
		artos.add(grtos);
		record.put("tos", artos);
		
		// set ccs data
		GenericRecord grccs = new GenericData.Record(SCHEMA.getField("ccs").schema());
		for (Entry<String, String> en : this.ccs.entrySet()){
			grccs.put("real_name", en.getKey());
			grccs.put("address", en.getValue());
		}
		record.put("ccs", grccs);
		
		// set bccs data
		GenericRecord grbccs = new GenericData.Record(SCHEMA.getField("bccs").schema());
		for (Entry<String, String> en : this.bccs.entrySet()){
			grbccs.put("real_name", en.getKey());
			grbccs.put("address", en.getValue());
		}
		record.put("bccs", grbccs);
		
		// set reply_tos data
		GenericRecord grre = new GenericData.Record(SCHEMA.getField("reply_tos").schema());
		for (Entry<String, String> en : this.reply_tos.entrySet()){
			grre.put("real_name", en.getKey());
			grre.put("address", en.getValue());
		}
		record.put("from", grfrom);
		
		
//
//		int nemails = (mails != null) ? this.mails.length : 0;
//		GenericData.Array emails = new GenericData.Array(nemails, SCHEMA.getField("emails").schema());
//		for (int i = 0; i < nemails; ++i)
//		emails.add(new Utf8(this.mails[i]));
//		record.put("emails", emails);

//		if (this.boss != null)
//		record.put("boss", this.boss.serialize());

		return record;
		}
}
