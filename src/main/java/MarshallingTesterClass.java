import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@XmlRootElement
public class MarshallingTesterClass {
    @XmlElement
    private UUID uuid = UUID.randomUUID();
    @XmlElementWrapper(name = "orders")
    private List<String> orders = new ArrayList<>();

    public MarshallingTesterClass() {
        orders.add("ziomal");
        orders.add("ziomal_2");
        orders.add("ziomal_3");
    }

    public List<String> getOrders() {
        return orders;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setOrders(List<String> order) {
        this.orders = order;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UUID: ");
        sb.append(uuid.toString());
        sb.append(" ");
        sb.append("ORDERS: ");
        sb.append(orders.toString());
        return sb.toString();
    }
}
