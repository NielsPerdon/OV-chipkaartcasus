import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO {

    boolean save (Reiziger reiziger) throws SQLException;

    boolean update (Reiziger reiziger) throws SQLException;

    boolean delete (Reiziger reiziger) throws ClassNotFoundException;

    Reiziger findById(int id) throws ClassNotFoundException, SQLException;

    List<Reiziger> findByGbdatum(String datum);

    List<Reiziger> findAll() throws SQLException;
}
