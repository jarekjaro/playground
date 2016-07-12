import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

@Component
public class PlayerImpl implements Player {

    private String name;
    private String surname;
    private int rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public PlayerImpl() {
    }

    public void subscribe() {

    }

    public void unsubscribe() {

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
            MessageConsumer receiver = ses.createConsumer(dest);
            receiver.setMessageListener(message -> {
                if (message instanceof TextMessage) {
                    TextMessage txtMsg = (TextMessage) message;
                    try {
                        System.out.println("Received: " + txtMsg.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                } else if (message != null) {
                    System.out.println("Received non text message");
                }
            });

            con.start();
            System.out.println("Receiver started");
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
