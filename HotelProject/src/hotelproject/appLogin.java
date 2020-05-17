/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelproject;

import java.sql.Connection;
import hotelproject.db.loginDatabaseConn;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.WindowConstants;

/**
 *
 * @author vatsalpatel
 */
public class appLogin extends JFrame{
    Connection conn;
    
    public appLogin(){
        conn = loginDatabaseConn.dbConnection();
        initComponents();
    }
    
    //components variable
    JFrame loginFrame;
    GroupLayout loginFrameLayout;
    
    //components
    private void initComponents(){
        //objects
        loginFrame = new JFrame();
        
        //Frame layout
        loginFrameLayout = new GroupLayout(loginFrame.getContentPane());
        loginFrame.getContentPane().setLayout(loginFrameLayout);
        setTitle("Log In");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        pack();
        setVisible(true);
    }
}
