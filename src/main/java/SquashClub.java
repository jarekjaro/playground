import javax.annotation.Resource;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SquashClub {

    private static Context ctx;

    private static ConnectionFactory connectionFactory;

    private static Queue queue;

    private static Topic topic;

    static{

    }

    public SquashClub() {
    }

    public static void main(String[] args) {
        final int NUM_MSGS;
        String destType = Helper.determineDestType(args[0]);
        NUM_MSGS = Helper.determineNoOfMsgs(args);
        Destination dest = assignProperDest(destType);

        try {
            ctx = new InitialContext();
            connectionFactory = (ConnectionFactory) ctx.lookup("jms/ConnectionFactory");
            queue = (Queue) ctx.lookup("jms/Queue");
            topic = (Topic) ctx.lookup("jms/Topic");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageProducer producer = session.createProducer(dest);
            TextMessage message = session.createTextMessage();

            for (int i = 0; i < NUM_MSGS; i++) {
                message.setText("This is message " + (i + 1) + " from producer");
                System.out.println("Sending message: " + message.getText());
                producer.send(message);
            }

            producer.send(session.createMessage());

        } catch (Exception exc) {
            exc.printStackTrace();
            System.exit(1);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exc) {
                    System.err.println(exc);
                }
            }
        }
        System.exit(0);
    }

    static Destination assignProperDest(String destType) {
        Destination dest = null;
        try {
            if (destType.equals("queue")) {
                dest = queue;
            } else {
                dest = topic;
            }
        } catch (Exception e) {
            System.err.println("Error setting destination: " + e.toString());
            e.printStackTrace();
            System.exit(1);
        }
        return dest;
    }
}
