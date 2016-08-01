import org.xml.sax.SAXException;

import javax.jms.JMSException;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@XmlRootElement
public class MarshallingTesterClass {
    private UUID uuid = UUID.randomUUID();
    private List<String> orders = new ArrayList<>();

    public MarshallingTesterClass() {
        orders.add("ziomal");
        orders.add("ziomal_2");
        orders.add("ziomal_3");
    }

    @XmlElementWrapper(name = "orders")
    public List<String> getOrders() {
        return orders;
    }

    public void setOrders(List<String> order) {
        this.orders = order;
    }

    @XmlElement
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UUID: ");
        sb.append(uuid.toString());
        sb.append(" ");
        sb.append("ORDERS: ");
        sb.append(orders.toString());
        return sb.toString();
    }

    public String toXMLString(){
        JAXBContext ctx = null;
        try {
            ctx = JAXBContext.newInstance(this.getClass());
            Marshaller m = ctx.createMarshaller();
            StringWriter sw = new StringWriter();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(this, sw);
            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MarshallingTesterClass fromString(String XMLString) throws JAXBException, SAXException {
        JAXBContext ctx = JAXBContext.newInstance(this.getClass());
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        StringReader sr = new StringReader(XMLString);
        return (MarshallingTesterClass) unmarshaller.unmarshal(sr);
    }
}
