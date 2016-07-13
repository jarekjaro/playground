import javax.jms.JMSException;
import javax.jms.TextMessage;

class AsyncPlayerImpl extends Player {

    public AsyncPlayerImpl() {
    }

    public static void main(String[] args) {
//        receiver.setMessageListener(message -> {
//            if (message instanceof TextMessage) {
//                TextMessage txtMsg = (TextMessage) message;
//                try {
//                    System.out.println("Received: " + txtMsg.getText());
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
//            } else if (message != null) {
//                System.out.println("Received non text message");
//            }
//        });
    }
}
