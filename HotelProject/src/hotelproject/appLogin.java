/**
 * Login Page for Entry Into Management Software
 * 
 * @author vatsalpatel
 */

package hotelproject;

import java.sql.Connection;
import hotelproject.db.loginDatabaseConn;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class appLogin extends JFrame{
    Connection conn;
    
    public appLogin(){
        conn = loginDatabaseConn.dbConnection();
        initComponents();
    }
    
    //components variable
    private JTextField  userNameField;
    private JTextField  passwordField;
    private JButton     loginBtn;
    private JLabel      iconPlaceHolder;
    
    //components
    private void initComponents(){
        //objects
        userNameField = new JTextField();
        passwordField = new JTextField();
        loginBtn = new JButton();
        iconPlaceHolder = new JLabel("B", SwingConstants.CENTER);
        
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
        
        //LabelIcon Design
        iconPlaceHolder.setBackground(Color.red);
        iconPlaceHolder.setPreferredSize(new Dimension(100,100));
        iconPlaceHolder.setBorder(BorderFactory.createLineBorder(Color.yellow));
        iconPlaceHolder.setFont(new Font("Arial Rounded MT Bold", 0, 50));
        iconPlaceHolder.setForeground(new Color(29, 171, 67));
        
        //Buttons Design
        loginBtn.setText("Go");
        loginBtn.setPreferredSize(new Dimension(200, 50));
        
        //loginPanel layout
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(new Color(186, 215, 227));
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.add(iconPlaceHolder, getLabelConstraints(0, 0, GridBagConstraints.PAGE_START));
        loginPanel.add(userNameField, getConstraints(0,1, GridBagConstraints.CENTER));
        loginPanel.add(passwordField, getConstraints(0,2, GridBagConstraints.CENTER));
        loginPanel.add(loginBtn, getConstraints(0,3, GridBagConstraints.CENTER));
        
        getContentPane().add(loginPanel);
       

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private GridBagConstraints getLabelConstraints(int x, int y, int anchor) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.insets = new Insets(0,20,20,20);
        c.weightx = 0.5;
        c.gridx = x;
        c.gridy = y;
        c.anchor = anchor;
        return c;
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