import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class SquashClub {

    static ConnectionFactory connectionFactory;
    static Connection connection;
    static Session session;
    static Destination destination;
    static MessageProducer messageProducer;
    static Message message;
    static boolean useTransaction = false;
    static final String brokerURL = "tcp://localhost:61616";

    public static void main(String[] args) throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(useTransaction, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("Contests");
        messageProducer = session.createProducer(destination);
        message = session.createTextMessage();
        for (int i = 0; i < 100; i++) {
            TextMessage txtMsg = (TextMessage) message;
            txtMsg.setText("this is message number " + i);
            messageProducer.send(destination, txtMsg);
        }
    }
}
