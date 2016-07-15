import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class QuePublisher {

    static ConnectionFactory connectionFactory;
    static Connection connection;
    static Session session;
    static Destination destination;
    static MessageProducer messageProducer;
    static Message message;
    static boolean useTransaction = false;
    static final String brokerURL = "tcp://localhost:61616";
//    static final String brokerURL = "nio://localhost:61618";

    public static void main(String[] args) throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(useTransaction, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("Contests");
        messageProducer = session.createProducer(destination);
        message = session.createTextMessage();
        Helper.publish(100, message, messageProducer, destination);
//        messageProducer.send(destination, session.createMessage());
    }


}
