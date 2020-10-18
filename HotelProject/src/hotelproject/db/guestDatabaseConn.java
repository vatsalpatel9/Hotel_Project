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
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://10.20.11.124:1433;databaseName=GuestData;user=sa;password=9299");
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
