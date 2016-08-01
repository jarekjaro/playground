import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.xml.sax.SAXException;

import javax.jms.JMSException;
import javax.xml.bind.JAXBException;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class SpringTester {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("JmsMessageListenerTest-context.xml");
    SpringJDBC springJDBC = (SpringJDBC) context.getBean("springJDBC");

    @Test
    public void testMarshallingOnSampleClass() throws JMSException {
        JmsMessageProducer messageProducer = (JmsMessageProducer) context.getBean("jmsMessageProducer");
        messageProducer.generateMessages();
    }

    @Test
    public void testJdbcConnection() {
        MarshallingTesterClass testerClass = (MarshallingTesterClass) springJDBC
                .getJdbcTemplate()
                .queryForObject("select * from Objects where UUID = 'f7ed237e-efc2-4c05-97a1-5033e2436478'",
                        new TesterClassRowMapper());
        assertEquals("ziomal", testerClass.getOrders().get(0));
        assertEquals("ziomal_2", testerClass.getOrders().get(1));
        assertEquals("ziomal_3", testerClass.getOrders().get(2));
    }

    @Test
    public void testWritingToDB() {
        int starting_rows = springJDBC.getJdbcTemplate().queryForObject(
                "select count(*) from OBJECTS where UUID<>0", Integer.class);
        MarshallingTesterClass testerClass = new MarshallingTesterClass();
        springJDBC.getJdbcTemplate().execute("INSERT INTO OBJECTS (UUID, ORDERS) VALUES (" +
                "'" + testerClass.getUuid() + "'" + "," +
                "(" + "'" + testerClass.getOrders().get(0) + "', " +
                      "'" + testerClass.getOrders().get(1) + "', " +
                      "'" + testerClass.getOrders().get(2) + "'))");
        int ending_rows = springJDBC.getJdbcTemplate().queryForObject(
                "select count(*) from OBJECTS where UUID<>0", Integer.class);
        assertEquals(starting_rows + 1, ending_rows);
    }

    @Test
    public void testObjectCreationFromXMLStringFromDB() throws JAXBException, SAXException {
//        springJDBC.getJdbcTemplate().execute("INSERT INTO XML_OBJECT (XML) VALUES ("+
//        "'"+ new MarshallingTesterClass().toXMLString() +"')");
        String fromDB = springJDBC.getJdbcTemplate()
                .queryForObject("SELECT XML FROM XML_OBJECT WHERE ID =1", String.class);
        MarshallingTesterClass mtc = new MarshallingTesterClass().fromString(fromDB);
        assertEquals("89f86b3c-0961-4d8a-ac6f-5ff7ac73954c", mtc.getUuid().toString());
    }
}
