import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class PlaygroundConfiguration {
    @Bean
    public Broker broker() {
        return new Broker();
    }
    @Bean
    public AsyncQueConsumer asyncQueConsumer(){
        return new AsyncQueConsumer();
    }
    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        return new ActiveMQConnectionFactory();
    }
}
