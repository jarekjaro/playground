import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SquashClub {

    public SquashClub() {
    }

    void establishConnection() {
        Connection con = null;
        try {
            Context ctx = new InitialContext();
            ConnectionFactory factory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
            String admDestName = ".";
            Destination dest = (Destination) ctx.lookup(admDestName);
            con = factory.createConnection();
            Session ses = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer sender = ses.createProducer(dest);

            con.start();
            TextMessage msg = ses.createTextMessage();
            msg.setText("test1");
            sender.send(msg);
            System.out.println("Sender sent msg: " + msg.getText());
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
