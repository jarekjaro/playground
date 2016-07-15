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
}
