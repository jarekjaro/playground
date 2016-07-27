import org.springframework.jdbc.core.RowMapper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TesterClassRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        MarshallingTesterClass testerClass = new MarshallingTesterClass();
        testerClass.setUuid(UUID.nameUUIDFromBytes(rs.getBytes("UUID")));
        Array array = rs.getArray("ORDERS");
        ResultSet resultSet = array.getResultSet();
        List<String> ordersList = new ArrayList<>();
        while (resultSet.next()) {
            ordersList.add(resultSet.getString(2));
        }
        testerClass.setOrders(ordersList);
        System.out.println(testerClass);
        return testerClass;
    }
}
