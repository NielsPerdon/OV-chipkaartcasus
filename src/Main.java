import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
       ReizigerDAO rdp = new ReizigerDAOPsql(createConnection());
       testReizigerDAO(rdp);

       AdresDAO adp = new AdresDAOPsql(createConnection());
       testAdresDAO(adp);

    }
    private static  Connection createConnection() throws SQLException {
        String jbcUrl = "jdbc:postgresql://localhost:5432/ovchip";
        String username = "postgres";
        String password = "donderwolk1";

        return DriverManager.getConnection(jbcUrl, username, password);
    }
    /**
     * P2. Reiziger DAO: persistentie van een klasse
     *
     * Deze methode test de CRUD-functionaliteit van de Reiziger DAO
     *
     * @throws SQLException
     */
    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException, ClassNotFoundException {
            System.out.println("\n---------- Test ReizigerDAO -------------");

            // find all
            List<Reiziger> reizigers = rdao.findAll();
            System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
            for (Reiziger r : reizigers) {
                System.out.println(r);
            }

            // find by id
            int id = 1;
            Reiziger r = rdao.findById(id);
            System.out.println("[Test] ReizigerDAO.findById() geeft van de volgende reiziger zijn/haar achternaam:");
            System.out.println(r.getAchternaam());

            //find by geboortedatum
        System.out.println( "find by geboortedatum");
            String datum = "2002-09-17";
            List<Reiziger> reizigerslijst = rdao.findByGbdatum(datum);
            for (Reiziger reiziger : reizigerslijst) {
                System.out.println(reiziger);
            }

            // save reiziger
            String gbdatum = "1981-03-14";
            Reiziger sietske = new Reiziger(79, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
            System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
            rdao.save(sietske);
            reizigers = rdao.findAll();
            System.out.println(reizigers.size() + " reizigers\n");

            // update reiziger
             System.out.print("[Test] Eerst " + sietske.getAchternaam());
             sietske.setAchternaam("tuinissen");
             rdao.update(sietske);
             System.out.print(" na updaten is " + sietske.getAchternaam() + " de nieuwe achternaam van sietske\n");

            // delete reiziger
            System.out.print("[Test] Eerst " + reizigers.size());
            rdao.delete(sietske);
            reizigers = rdao.findAll();
            System.out.print("[Test] Na delete " + reizigers.size());
        }

        public static void testAdresDAO(AdresDAO adao) throws SQLException, ClassNotFoundException {
        // find all
            List<Adres> adres = adao.findAll();
            System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
            for (Adres a : adres) {
                System.out.println(a);
            }

            // find by reiziger
            // moet nog gemaakt worden

            // save adress
            Adres mijnadres = new Adres( 4,"3723pd", "18", "dirkboutslaan 18", "bilthoven", 6);
            System.out.print("[Test] Eerst " + adres.size() + " adres, na AdresDAO.save() ");
            adao.save(mijnadres);
            adres = adao.findAll();
            System.out.println(adres.size() + " adres\n");

            // update reiziger
            System.out.print("[Test] Eerst " + mijnadres.getStraat());
            mijnadres.setStraat("PC Hoofdstraat");
            adao.update(mijnadres);
            System.out.print(" na updaten is " + mijnadres.getStraat() + " het nieuwe adres\n");

            // delete reiziger
            System.out.print("[Test] Eerst " + adres.size());
            adao.delete(mijnadres);
            adres = adao.findAll();
            System.out.print("[Test] Na delete " + adres.size());
        }

}