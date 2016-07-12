import org.springframework.stereotype.Component;

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

//    public static void main(String[] args) {
//        Connection con = null;
//        try {
//            Context ctx = new InitialContext();
//            ConnectionFactory factory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
//            String admDestName = args[0];
//            Destination dest = (Destination) ctx.lookup(admDestName);
//            con = factory.createConnection();
//            Session ses = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            MessageConsumer receiver = ses.createConsumer(dest);
//            //MESSAGE LISTENER EXAMPLE
////            receiver.setMessageListener(new MessageListener() {
////                public void onMessage(Message message) {
////                    if (message instanceof TextMessage) {
////                        TextMessage txtMsg = (TextMessage) message;
////                        try {
////                            System.out.println("Received: " + txtMsg.getText());
////                        } catch (JMSException e) {
////                            e.printStackTrace();
////                        }
////                    } else if (message != null) {
////                        System.out.println("Received non text message");
////                    }
////                }
////            });
//
//            con.start();
//            System.out.println("Receiver started");
//            Message msg = receiver.receive();
//            if (msg instanceof TextMessage) {
//                TextMessage text = (TextMessage) msg;
//                System.out.println("Received: " + text.getText());
//            } else if (msg != null) {
//                System.out.println("Received non text message");
//            }
//        } catch (Exception exc) {
//            exc.printStackTrace();
//            System.exit(1);
//        } finally {
//            if (con != null) {
//                try {
//                    con.close();
//                } catch (JMSException exc) {
//                    System.err.println(exc);
//                }
//            }
//        }
//        System.exit(0);
//    }
}
