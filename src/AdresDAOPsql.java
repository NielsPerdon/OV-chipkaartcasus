import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO{

    Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ovchip", "postgres", "donderwolk1");

    public AdresDAOPsql(Connection conn) throws SQLException {
        this.conn = conn;
    }


    private static final String UPDATE_ADRES_SQL = "UPDATE adres SET postcode = ?, huisnummer = ?, straat = ?, woonplaats = ? WHERE reiziger_id = ?";
    private static final String INSERT_ADRES_SQL = "INSERT INTO adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id)" +
            "VALUES (?, ?, ?, ?, ?, ?);";
    private static final String DELETE_ADRES_SQL = " DELETE FROM adres where reiziger_id =?; ";
    private static final String SELECT_ALL_ADRES = " SELECT * FROM  adres";
    private static final String SELECT_ALL_BY_REZIGER = "SELECT * FROM adres WHERE reiziger_id= ?";
    @Override
    public boolean save(Adres adres) throws SQLException {
        try {
            PreparedStatement pstmt = conn.prepareStatement(INSERT_ADRES_SQL);
            pstmt.setInt(1, adres.getId());
            pstmt.setString(2, adres.getPostcode());
            pstmt.setString(3, adres.getHuisnummer());
            pstmt.setString(4, adres.getStraat());
            pstmt.setString(5, adres.getWoonplaats());
            pstmt.setInt(6, adres.getId());
            pstmt.execute();
            pstmt.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Adres adres) throws SQLException {
        try {
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_ADRES_SQL);
            pstmt.setInt(1, adres.getId());
            pstmt.setString(2, adres.getPostcode());
            pstmt.setString(3, adres.getHuisnummer());
            pstmt.setString(4, adres.getStraat());
            pstmt.setString(5, adres.getWoonplaats());
            pstmt.execute();
            pstmt.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Adres adres) throws ClassNotFoundException {
        try {
            PreparedStatement pstmt = conn.prepareStatement(DELETE_ADRES_SQL);
            pstmt.setInt(1, adres.getId());
            pstmt.execute();
            pstmt.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
   public Adres findByReiziger(Reiziger reiziger) throws ClassNotFoundException, SQLException {
        return null;
    }

//    @Override
//    public Adres findByReiziger(Reiziger reiziger) throws ClassNotFoundException, SQLException {
//        try {
//            PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_BY_REZIGER );
//            pstmt.setInt(1, int.valueOf(reiziger));
//            ResultSet rs = pstmt.executeQuery();
//            List<Adres> lijst = new ArrayList<Adres>();
//            while (rs.next()){
//                Adres a = new Adres(rs.getInt("reiziger_id"),
//                        rs.getString("postcode"),
//                        rs.getString("huisnummer"),
//                        rs.getString("straat"),
//                        rs.getString("woonplaats"));
//                lijst.add(a);
//            }
//            rs.close();
//            pstmt.close();
//            return (Adres) lijst;
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public List<Adres> findAll() throws SQLException {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_ADRES);
            ResultSet rs = pstmt.executeQuery();
            List<Adres> lijst = new ArrayList<Adres>();
            while (rs.next()){
                Adres a = new Adres(rs.getInt("adres_id"),
                        rs.getString("postcode"),
                        rs.getString("huisnummer"),
                        rs.getString("straat"),
                        rs.getString("woonplaats"),
                        rs.getInt("reiziger_id"));
                lijst.add(a);
            }
            rs.close();
            pstmt.close();
            return lijst;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
