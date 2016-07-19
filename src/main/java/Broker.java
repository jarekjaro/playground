import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.security.AuthenticationUser;
import org.apache.activemq.security.SimpleAuthenticationPlugin;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Component
public class Broker {
    BrokerService broker;
    SimpleAuthenticationPlugin authentication;
    List<AuthenticationUser> users;

    public Broker() {
        broker = new BrokerService();
        broker.setBrokerName("myBroker");
        broker.setDataDirectory("data/");
        authentication = new SimpleAuthenticationPlugin();
        users = new ArrayList<>(4);
    }

    public static void main(String[] args) throws Exception {
        Broker br = new Broker();
        br.users.add(new AuthenticationUser("admin", "password", "admins,publishers,consumers"));
        br.users.add(new AuthenticationUser("publisher", "password", "publishers,consumers"));
        br.users.add(new AuthenticationUser("consumer", "password", "consumers"));
        br.users.add(new AuthenticationUser("guest", "password", "guests"));
        br.authentication.setUsers(br.users);
        br.broker.setPlugins(new BrokerPlugin[]{br.authentication});
        br.broker.addConnector("tcp://localhost:61616");
        br.broker.start();

        System.out.println();
        System.out.println("Press any key to stop the broker");
        System.out.println();

        System.in.read();
    }
}
