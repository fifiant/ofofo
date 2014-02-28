package analytic.ofofo.net.services.exception.meta;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Exception")
public class ExceptionMessage {
	
	private String message;
	private Map<String, String> value;
	
	public ExceptionMessage(){
		
	}

	public ExceptionMessage(String message, Map<String, String> value){
		this.setMessage(message);
		this.setValue(value);
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getValue() {
		return value;
	}

	public void setValue(Map<String, String> value) {
		this.value = value;
	}
	
	
	public static void main(String[] args) throws JAXBException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "value");

		ExceptionMessage em = new ExceptionMessage("Fake Error message", map);

		JAXBContext context = JAXBContext.newInstance(ExceptionMessage.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(em, System.out);
		}

}
