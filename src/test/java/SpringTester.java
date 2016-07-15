import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class SpringTester {
    AnnotationConfigApplicationContext context;
    @Before
    public void before(){
        context = new AnnotationConfigApplicationContext(PlaygroundConfiguration.class);
        context.refresh();
    }

    private AsyncQueConsumer player = mock(AsyncQueConsumer.class);

    @Test
    public void playerShouldNotBeNull(){
        assertNotNull(player);
    }
    @Test
    public void testBrokerCreation(){
    }
}
