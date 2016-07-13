import javax.annotation.Resource;
import javax.jms.*;

public class SynchPlayerImpl extends Player {

    @Resource(lookup = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;

    public static void main(String args[]) {
        Connection con = null;
        try {
            String destType = Helper.determineDestType(args[0]);
            Destination dest = SquashClub.assignProperDest(destType);

            con = connectionFactory.createConnection();
            Session ses = con.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageConsumer receiver = ses.createConsumer(dest);

            con.start();
            System.out.println("Receiver started");
            TextMessage message = null;

            while (true) {
                Message m = receiver.receive(10);
                if (m != null) {
                    if (m instanceof TextMessage) {
                        message = (TextMessage) m;
                        System.out.println("Reading message: " + message.getText());
                    } else {
                        break;
                    }
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
            System.exit(1);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (JMSException exc) {
                    System.err.println(exc);
                }
            }
        }
        System.exit(0);
    }
}
