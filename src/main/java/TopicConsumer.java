import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicConsumer {

    static ConnectionFactory connectionFactory;
    static Connection connection;
    static Session session;
    static Destination destination;
    static MessageConsumer messageConsumer;
    static Message message;
    static boolean useTransaction = false;
    static final String brokerURL = "tcp://localhost:61616";

    public static void main(String args[]) throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(useTransaction, Session.AUTO_ACKNOWLEDGE);
        destination = session.createTopic("Contests");
        messageConsumer = session.createConsumer(destination);
        messageConsumer.setMessageListener(new Listener());
    }
}
