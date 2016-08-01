public interface MtcTransactionalService {
    MarshallingTesterClass getMtc(SpringJDBC springJDBC, String uuid);

    void insertMtc(SpringJDBC springJDBC, MarshallingTesterClass marshallingTesterClass);
}
