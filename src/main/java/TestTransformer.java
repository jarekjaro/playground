import org.springframework.integration.transformer.Transformer;
import org.springframework.messaging.Message;

import javax.jms.JMSException;
import javax.jms.TextMessage;

public class TestTransformer implements Transformer {
    @Override
    public Message<?> transform(Message<?> message) {
        if (message instanceof TextMessage) {
            try {
                TextMessage returnMessage = (TextMessage) message;
                int msgNo = ((TextMessage) message).getIntProperty(JmsMessageProducer.MESSAGE_COUNT);
                returnMessage.setIntProperty(JmsMessageProducer.MESSAGE_COUNT, msgNo * 100);
                return (Message<?>) returnMessage;
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
