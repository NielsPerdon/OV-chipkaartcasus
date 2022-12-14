import java.sql.SQLException;
import java.util.List;

public interface AdresDAO {
    boolean save (Adres adres) throws SQLException;

    boolean update (Adres adres) throws SQLException;

    boolean delete (Adres adres) throws ClassNotFoundException;

    Adres findByReiziger(Reiziger reiziger) throws ClassNotFoundException, SQLException;

    List<Adres> findAll() throws SQLException;
}
