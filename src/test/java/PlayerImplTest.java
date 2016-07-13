import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class PlayerImplTest {
    private AsyncPlayerImpl player = mock(AsyncPlayerImpl.class);

    @Test
    public void playerShouldNotBeNull(){
        assertNotNull(player);
    }

}
