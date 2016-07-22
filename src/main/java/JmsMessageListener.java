import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.concurrent.atomic.AtomicInteger;

public class JmsMessageListener implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(JmsMessageListener.class);

    public void setCounter(AtomicInteger counter) {
        this.counter = counter;
    }

    public JmsMessageListener(AtomicInteger counter) {
        this.counter = counter;
    }

    private AtomicInteger counter = null;

    public void onMessage(Message message) {
        try {
            int messageCount = message.getIntProperty(JmsMessageProducer.MESSAGE_COUNT);

            if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage) message;
                String msg = tm.getText();

                logger.info("Processed message '{}'.  value={}", msg, messageCount);

                counter.incrementAndGet();
            }
        } catch (JMSException e) {
            logger.error(e.getMessage(), e);
        }
    }

}