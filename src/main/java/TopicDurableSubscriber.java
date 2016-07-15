import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class TopicDurableSubscriber {
    static ConnectionFactory connectionFactory;
    static Connection connection;
    static Session session;
    static Topic topic;
    static TopicSubscriber messageConsumer;
    static boolean useTransaction = false;
    static final String brokerURL = "tcp://localhost:61616";

    public static void main(String args[]) throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        connection = connectionFactory.createConnection();
        connection.setClientID("durableSub");
        session = connection.createSession(useTransaction, Session.AUTO_ACKNOWLEDGE);
        topic = session.createTopic("Contests");
        messageConsumer = session.createDurableSubscriber(topic, "durableSubSubscription");
        messageConsumer.setMessageListener(new Listener());
        connection.start();
    }
}
