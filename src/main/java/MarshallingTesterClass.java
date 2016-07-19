import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@XmlRootElement
public class MarshallingTesterClass {
    @XmlElement
    private UUID uuid = UUID.randomUUID();
    @XmlElementWrapper(name = "orders")
    private List<String> order = new ArrayList<>();

    public MarshallingTesterClass() {
        order.add("ziomal");
        order.add("ziomal_2");
        order.add("ziomal_3");
    }
}
