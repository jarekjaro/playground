import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.xml.sax.SAXException;

import javax.jms.JMSException;
import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class SpringTester {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("JmsMessageListenerTest-context.xml");

    @Test
    public void testMarshallingOnSampleClass() throws JMSException {
        JmsMessageProducer messageProducer = (JmsMessageProducer) context.getBean("jmsMessageProducer");
        messageProducer.generateMessages();
    }

    @Test
    public void testJdbcConnection() {
        SpringJDBC springJDBC = (SpringJDBC) context.getBean("springJDBC");
        int rows = springJDBC.getJdbcTemplate().queryForObject(
                "select count(*) from OBJECTS where UUID<>0", Integer.class);
        MarshallingTesterClass testerClass = (MarshallingTesterClass) springJDBC
                .getJdbcTemplate()
                .queryForObject("select * from Objects where UUID = 'b5994efb-6594-3e21-be67-749d3a7f8d5d'",
                        new TesterClassRowMapper());

        MarshallingTesterClass testerClass2 = (MarshallingTesterClass) springJDBC
                .getJdbcTemplate()
                .queryForObject("select * from Objects where UUID = 'ae9f8f81-a870-436b-bb40-4ec4bcdcb92a'",
                        new TesterClassRowMapper());

        assertEquals("burek", testerClass.getOrders().get(0).substring(0,5));
        assertEquals(2, rows);
    }

    private String getMessageFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    void writeXMLClassToTextMessage(Object document, String pathName)
            throws JAXBException, IOException {
        Class clazz = document.getClass();
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Marshaller m = jaxbContext.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(document, new File(pathName));
    }

    public <T> T unmarshal(Class<T> docClass, InputStream is) throws JAXBException, SAXException {
        String packageName = docClass.getPackage().getName();
        JAXBContext jaxbContext = JAXBContext.newInstance(packageName);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema mySchema = schemaFactory.newSchema();

        unmarshaller.setSchema(mySchema);
        JAXBElement<T> doc = (JAXBElement<T>) unmarshaller.unmarshal(is);
        return doc.getValue();
    }
}
