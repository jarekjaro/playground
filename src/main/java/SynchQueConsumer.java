import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class SynchQueConsumer {

    static ConnectionFactory connectionFactory;
    static Connection connection;
    static Session session;
    static Destination destination;
    static MessageConsumer messageConsumer;
    static Message message;
    static boolean useTransaction = false;
    static final String brokerURL = "tcp://localhost:61616";
    static String username = "consumer";
    static String password = "password";
//    static final String brokerURL = "nio://localhost:61618";

    public static void main(String args[]) throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        connection = connectionFactory.createConnection(username, password);
        connection.start();
        session = connection.createSession(useTransaction, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("Contests");
        messageConsumer = session.createConsumer(destination);
        while (true) {
            message = messageConsumer.receive(10000);
            if (message instanceof TextMessage)
                System.out.println(((TextMessage) message).getText());
            else System.exit(25);
        }
    }
}
