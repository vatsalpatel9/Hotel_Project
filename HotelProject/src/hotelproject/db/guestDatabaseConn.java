package hotelproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author vatsalpatel
 */
public class guestDatabaseConn implements credential{
    Connection conn = null;
    public static Connection dbConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(credential.DbLink,credential.DbUser,credential.DbPass);
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
