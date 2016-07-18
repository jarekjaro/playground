import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
@ContextConfiguration(classes = PlaygroundConfiguration.class)
public class SpringTester {

    @Autowired
    private AsyncQueConsumer player;

    @Test
    public void playerShouldNotBeNull() {
        assertNotNull(player);
    }

    @Test
    public void testBrokerCreation() {
    }
}
