import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class PlayerImplTest {
    private AsyncQueConsumer player = mock(AsyncQueConsumer.class);

    @Test
    public void playerShouldNotBeNull(){
        assertNotNull(player);
    }

}
