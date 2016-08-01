public class DefaultMtcTransServiceImpl implements MtcTransactionalService {
    @Override
    public MarshallingTesterClass getMtc(SpringJDBC springJDBC, String uuid) {
        return (MarshallingTesterClass) springJDBC
                .getJdbcTemplate()
                .queryForObject("select * from Objects where UUID ='" + uuid + "'",
                        new TesterClassRowMapper());
    }

    @Override
    public void insertMtc(SpringJDBC springJDBC, MarshallingTesterClass testerClass) {
        springJDBC.getJdbcTemplate().execute("INSERT INTO OBJECTS (UUID, ORDERS) VALUES (" +
                "'" + testerClass.getUuid() + "'" + "," +
                "(" + "'" + testerClass.getOrders().get(0) + "', " +
                "'" + testerClass.getOrders().get(1) + "', " +
                "'" + testerClass.getOrders().get(2) + "'))");
    }
}
