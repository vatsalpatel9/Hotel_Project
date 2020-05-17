package hotelproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author vatsalpatel
 */
public class loginDatabaseConn {
    Connection conn;
    public static Connection dbConnection(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:src/hotelproject/db/loginDatabaseConn.sqlite");
            JOptionPane.showMessageDialog(null, "Connection Successful");
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
