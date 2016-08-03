import javax.jms.*;

public class Helper {
    static void publish(int noOfMsgs, Message message, MessageProducer msgProducer, Destination destination) {
        for (int i = 0; i < noOfMsgs; i++) {
            try {
                Thread.sleep(20);
                TextMessage txtMsg = (TextMessage) message;
                txtMsg.setText("this is message number " + i);
                txtMsg.setIntProperty("id", i);
                msgProducer.send(destination, txtMsg);
            } catch (InterruptedException | JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
