import java.sql.Connection;

public interface BaseDAO {
    Connection openConnection() throws DAOException;

    void closeConnection();
}