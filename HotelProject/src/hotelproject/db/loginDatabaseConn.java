package hotelproject.db;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author vatsalpatel
 */
public class loginDatabaseConn{
    Connection conn;
    public static Connection dbConnection(){
        Scanner input = new Scanner(System.in);
        System.out.print("Username: ");
        String userName = input.next();

        System.out.print("Password: ");
        String passWord = input.next();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://10.20.11.124:3306/hotellogindb",userName,passWord);
            JOptionPane.showMessageDialog(null, "Connection Successful");
            return conn;
        }catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
