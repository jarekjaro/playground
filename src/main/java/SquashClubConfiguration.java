import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class SquashClubConfiguration {
    @Bean
    SquashClub squashClub() {
        return new SquashClub();
    }

    @Bean
    PlayerImpl playerImpl() {
        return new PlayerImpl();
    }
}
