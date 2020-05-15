/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlogin.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author vatsalpatel
 */
public class LoginForm extends JFrame {
    
    private JFrame activeFrame;
    private JButton button1;
    private JPanel buttonPanel;
    
    public LoginForm() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        initComponents();
    }
    
    private void initComponents(){
        activeFrame = new JFrame();
        buttonPanel = new JPanel();
        button1 = new JButton();
        
        //frame
        activeFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        activeFrame.setMinimumSize(new Dimension(508, 515));
        activeFrame.setTitle("ActiveAccount"); // NOI18N
        activeFrame.setLayout(new BorderLayout());
        
        //button pannel
        button1.setSize(new java.awt.Dimension(138, 37));
        button1.setText("Hello");
        
        buttonPanel.add(button1);
        buttonPanel.setLocation(200, 50);
        
        activeFrame.add(buttonPanel);
        
        pack();
        activeFrame.setLocationRelativeTo(null);
        activeFrame.setVisible(true);
        
    }
}
