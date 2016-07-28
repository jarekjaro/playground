import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@XmlRootElement
public class MarshallingTesterClass {
    private UUID uuid = UUID.randomUUID();
    private List<String> orders = new ArrayList<>();

    public MarshallingTesterClass() {
        orders.add("ziomal");
        orders.add("ziomal_2");
        orders.add("ziomal_3");
    }

    @XmlElementWrapper(name = "orders")
    public List<String> getOrders() {
        return orders;
    }

    public void setOrders(List<String> order) {
        this.orders = order;
    }

    @XmlElement
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
