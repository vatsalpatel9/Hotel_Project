package hotelproject.db;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author vatsalpatel
 */
public class loginDatabaseConn implements credential{
    Connection conn;
    public static Connection dbConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(credential.DbLink,credential.DbUser,credential.DbPass);
            JOptionPane.showMessageDialog(null, "Connection Successful");
            return conn;
        }catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
