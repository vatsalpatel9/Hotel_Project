/**
 * Login Page for Entry Into Management Software
 * 
 * @author vatsalpatel
 */

package hotelproject;

import hotelproject.app.mainApp;
import java.sql.Connection;
import hotelproject.db.loginDatabaseConn;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JPasswordField;

public class appLogin extends JFrame{
    Connection conn;
    
    public appLogin(){
        conn = loginDatabaseConn.dbConnection();
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException e){
            JOptionPane.showMessageDialog(null, e);
        }
        initComponents();
    }
    
    //components variable
    private JTextField      userNameField;
    private JPasswordField  passwordField;
    private JButton         loginBtn;
    private JLabel          iconPlaceHolder;
    
    //components
    private void initComponents(){
        //objects
        userNameField = new JTextField();
        passwordField = new JPasswordField();
        loginBtn = new JButton();
        iconPlaceHolder = new JLabel("B", SwingConstants.CENTER);
        
        //Frame settings
        Dimension frameDim = new Dimension(350, 600);
        setName("loginFrame");
        setTitle("Log In");
        setMinimumSize(frameDim);
        setPreferredSize(frameDim);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        
        //Input TextFields Design
        //userNameField attributes
        userNameField.setName("userNameField");
        userNameField.setText("Username");
        userNameField.setForeground(new java.awt.Color(153, 153, 153));
        userNameField.setPreferredSize(new Dimension(200, 50));
        
        //passwordField attributes
        passwordField.setName("passwordField");
        passwordField.setText("Password");
        passwordField.setForeground(new java.awt.Color(153, 153, 153));
        passwordField.setPreferredSize(new Dimension(200, 50));
        
        //Input listener
        userNameField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                fieldTextHide(userNameField);
            }

            @Override
            public void focusLost(FocusEvent e) {
                fieldTextShow(userNameField);
            } 
        });
        
        passwordField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
               fieldTextHide(passwordField);
            }

            @Override
            public void focusLost(FocusEvent e) {
                fieldTextShow(passwordField);
            }
            
        });
        
        passwordField.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent evt){
                enterPressed(evt);
            }
        });
        
        loginBtn.addActionListener((ActionEvent) -> {
            loginBtnClicked();
        });
        
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
        iconPlaceHolder.requestFocusInWindow();
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
    
    private void fieldTextShow(JTextField field){
        String name = field.getName();
        if(field.getText().equals("")){
            if(name.equals("userNameField")){
                field.setText("Username");
                field.setForeground(new Color(153,153,153));
            }
            else if (name.equals("passwordField")){
                field.setText("Password");
                field.setForeground(new Color(153,153,153));
            }
        }
    }
    
    private void fieldTextHide(JTextField field){
        if(field.getText().equals("Username") || field.getText().equals("Password")){
            field.setText("");
            field.setForeground(Color.BLACK);
        }
    }
    
    private void loginBtnClicked(){
        logIn();
    }
    
    private void enterPressed(KeyEvent evt){
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
           logIn();
        }
    }
    
    private void logIn(){
        try{
            String query = "Select * from login where userName=? and password =?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, userNameField.getText());
            pst.setString(2, passwordField.getText());
            
            ResultSet rs = pst.executeQuery();
            int count = 0;
            while(rs.next()){
                count++;
            }
            if(count == 1){
                rs.close();
                pst.close();
                JOptionPane.showMessageDialog(null, "Successful");
                closeFrame();
                mainApp app = new mainApp();
                app.setVisible(true);
                
            
            }else if (count > 1){
                JOptionPane.showMessageDialog(null, rs);
            }else{
                JOptionPane.showMessageDialog(null, "Username or Password is Incorrect!");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void closeFrame(){
        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
    
}