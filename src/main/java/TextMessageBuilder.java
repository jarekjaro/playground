import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class TextMessageBuilder {
    private TextMessage message = null;
    private Session session = null;
    private Object jaxbElement = null;

    public TextMessageBuilder() {
    }

    public TextMessageBuilder elementToSend(Object jaxbElement) {
        this.jaxbElement = jaxbElement;
        return this;
    }

    public TextMessageBuilder session(Session session) {
        this.session = session;
        return this;
    }

    public TextMessage build() {
        JAXBContext ctx = null;
        try {
            ctx = JAXBContext.newInstance(jaxbElement.getClass());
            Marshaller m = ctx.createMarshaller();
            StringWriter sw = new StringWriter();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(jaxbElement, sw);
            message = session.createTextMessage();
            message.setText(sw.toString());
        } catch (JAXBException | JMSException e) {
            e.printStackTrace();
        }

        return message;
    }
}
