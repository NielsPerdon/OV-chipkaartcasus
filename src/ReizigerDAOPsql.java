import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO{

   Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ovchip", "postgres", "donderwolk1");

    public ReizigerDAOPsql(Connection conn) throws SQLException {
        this.conn = conn;
    }


    private static final String UPDATE_REIZIGER_SQL = "UPDATE reiziger SET voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ? WHERE reiziger_id = ?";
    private static final String INSERT_REIZIGER_SQL = "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum)" +
            "VALUES (?, ?, ?, ?, ?);";
    private static final String DELETE_REIZIGER_SQL = " DELETE FROM reiziger where reiziger_id =?; ";
    private static final String SELECT_ALL_REIZIGERS_SQL = " SELECT * FROM  reiziger";
    private static final String SELECT_REIZIGER_BY_ID = "SELECT * FROM reiziger WHERE reiziger_id= ?";
    private static final String SELECT_ALL_BY_DATE = "SELECT * FROM reiziger WHERE geboortedatum= ?";


    @Override
    public boolean save(Reiziger reiziger) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(INSERT_REIZIGER_SQL);
            pstmt.setInt(1, reiziger.getId());
            pstmt.setString(2, reiziger.getVoorletters());
            pstmt.setString(3, reiziger.getTussenvoegsel());
            pstmt.setString(4, reiziger.getAchternaam());
            pstmt.setDate(5, reiziger.getGeboortedatum());
            pstmt.execute();
            pstmt.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_REIZIGER_SQL);
            pstmt.setString(1, reiziger.getVoorletters());
            pstmt.setString(2, reiziger.getTussenvoegsel());
            pstmt.setString(3, reiziger.getAchternaam());
            pstmt.setDate(4, reiziger.getGeboortedatum());
            pstmt.setInt(5, reiziger.getId());
            pstmt.execute();
            pstmt.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(DELETE_REIZIGER_SQL);
            pstmt.setInt(1, reiziger.getId());
            pstmt.execute();
            pstmt.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Reiziger findById(int id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SELECT_REIZIGER_BY_ID);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                return new Reiziger(rs.getInt("reiziger_id"),
                        rs.getString("voorletters"),
                        rs.getString("tussenvoegsel"),
                        rs.getString("achternaam"),
                        rs.getDate("geboortedatum"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_BY_DATE );
            pstmt.setDate(1, Date.valueOf(datum));
            ResultSet rs = pstmt.executeQuery();
            List<Reiziger> lijst = new ArrayList<Reiziger>();
            while (rs.next()){
                Reiziger r = new Reiziger(rs.getInt("reiziger_id"),
                        rs.getString("voorletters"),
                        rs.getString("tussenvoegsel"),
                        rs.getString("achternaam"),
                        rs.getDate("geboortedatum"));
                lijst.add(r);
            }
            rs.close();
            pstmt.close();
            return lijst;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reiziger> findAll() {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_REIZIGERS_SQL);
            ResultSet rs = pstmt.executeQuery();
            List<Reiziger> lijst = new ArrayList<Reiziger>();
            while (rs.next()){
                Reiziger r = new Reiziger(rs.getInt("reiziger_id"),
                        rs.getString("voorletters"),
                        rs.getString("tussenvoegsel"),
                        rs.getString("achternaam"),
                        rs.getDate("geboortedatum"));
                lijst.add(r);
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