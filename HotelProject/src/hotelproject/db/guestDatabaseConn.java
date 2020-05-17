package hotelproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author vatsalpatel
 */
public class guestDatabaseConn {
    Connection conn = null;
    public static Connection dbConnection(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:src/hotelproject/db/guestDatabaseConn.sqlite");
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
