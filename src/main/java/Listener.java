import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class Listener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage txtMsg = (TextMessage) message;
            try {
                System.out.println("Received: " + txtMsg.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else if (message != null) {
            System.out.println("Received non text message, now exiting...");
            System.exit(25);
        }
    }
}

