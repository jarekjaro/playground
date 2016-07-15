import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class Helper {
    static void publish(int noOfMsgs, Message message, MessageProducer msgProducer, Destination destination) {
        for (int i = 0; i < noOfMsgs; i++) {
            try {
                Thread.sleep(20);
                TextMessage txtMsg = (TextMessage) message;
                txtMsg.setText("this is message number " + i);
                msgProducer.send(destination, txtMsg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
