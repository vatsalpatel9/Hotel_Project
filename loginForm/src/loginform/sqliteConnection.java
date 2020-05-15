/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginform;

import java.sql.*;
import javax.swing.*;

/**
 *
 * @author vatsalpatel
 */
public class sqliteConnection {
    Connection conn = null;
    public static Connection dbConnection(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:./LoginFormDb.sqlite");
            JOptionPane.showMessageDialog(null, "ConnectionSuccessful!");
            return conn;  
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
 
}
