import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicPublisher {

    static ConnectionFactory connectionFactory;
    static Connection connection;
    static Session session;
    static Destination destination;
    static MessageProducer messageProducer;
    static Message message;
    static boolean useTransaction = false;
    static final String brokerURL = "tcp://localhost:61616";
    static String username = "publisher";
    static String password = "password";

    public static void main(String[] args) throws JMSException, InterruptedException {
        connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        connection = connectionFactory.createConnection(username, password);
        connection.start();
        session = connection.createSession(useTransaction, Session.AUTO_ACKNOWLEDGE);
        destination = session.createTopic("Contests");
        messageProducer = session.createProducer(destination);
        message = session.createTextMessage();
        Helper.publish(100, message, messageProducer, destination);
    }
}
