/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelproject;

import java.sql.Connection;
import hotelproject.db.loginDatabaseConn;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
    private JFrame      loginFrame;
    private GroupLayout loginFrameLayout;
    private JTextField  userNameField;
    private JTextField  passwordField;
    private JButton     loginBtn;
    
    //components
    private void initComponents(){
        //objects
        loginFrame = new JFrame();
        userNameField = new JTextField();
        passwordField = new JTextField();
        loginBtn = new JButton();
        
        //Frame settings
        Dimension frameDim = new Dimension(350, 600);
        setName("loginFrame");
        setTitle("Log In");
        setMinimumSize(frameDim);
        setPreferredSize(frameDim);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        
        //Input TextFields Design
        userNameField.setText("Username");
        userNameField.setForeground(new java.awt.Color(153, 153, 153));
        userNameField.setPreferredSize(new Dimension(200, 50));
        passwordField.setText("Password");
        passwordField.setForeground(new java.awt.Color(153, 153, 153));
        passwordField.setPreferredSize(new Dimension(200, 50));
        
        
        //Buttons Design
        loginBtn.setText("Go");
        loginBtn.setPreferredSize(new Dimension(200, 50));
        
        //loginPanel layout
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(new Color(186, 215, 227));
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.add(userNameField, getConstraints(0,0, GridBagConstraints.PAGE_START));
        loginPanel.add(passwordField, getConstraints(0,1, GridBagConstraints.PAGE_START));
        loginPanel.add(loginBtn, getConstraints(0,2, GridBagConstraints.PAGE_START));
        
        getContentPane().add(loginPanel);
       

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private GridBagConstraints getConstraints(int x, int y, int anchor) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.insets = new Insets(10,10,10,10);
        c.weightx = 0.5;
        c.gridx = x;
        c.gridy = y;
        c.anchor = anchor;
        return c;
    }
}
