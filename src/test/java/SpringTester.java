import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import java.io.*;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
@ContextConfiguration(classes = PlaygroundConfiguration.class)
public class SpringTester {

    @Autowired
    private AsyncQueConsumer player;
    private Schema mySchema;
    private SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    private JAXBContext context;

    @Before
    public void before() {
        try {
            mySchema = schemaFactory.newSchema();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void playerShouldNotBeNull() {
        assertNotNull(player);

    }

    @Test
    public void testMarshallingOnSampleClass() throws JAXBException, IOException {
        MarshallingTesterClass mtc = new MarshallingTesterClass();
        writeDocument(mtc, "");
//        unmarshal(Broker.class, new FileInputStream("broker.xml"));
    }

    void writeDocument(Object document, String pathname)
            throws JAXBException, IOException {
        Class clazz = document.getClass();
        context = JAXBContext.newInstance(clazz);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(document, System.out);
    }

    public <T> T unmarshal(Class<T> docClass, InputStream is) throws JAXBException {
        String packageName = docClass.getPackage().getName();
        JAXBContext jaxbContext = JAXBContext.newInstance(packageName);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setSchema(mySchema);
        JAXBElement<T> doc = (JAXBElement<T>) unmarshaller.unmarshal(is);
        return doc.getValue();
    }
}
