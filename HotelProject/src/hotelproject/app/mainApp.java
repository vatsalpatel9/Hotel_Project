/*
 * The main Application Frame
 * 
 * @author vatsalpatel
 */
package hotelproject.app;

import java.sql.Connection;
import hotelproject.db.guestDatabaseConn;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class mainApp extends JFrame{
    Connection conn;
    public mainApp(){
        conn = guestDatabaseConn.dbConnection();
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException e){
            JOptionPane.showMessageDialog(null, e);
        }
        initComponents();
    }
    
    private JButton homeBtn;
    private JButton checkInBtn;
    private JButton checkOutBtn;
    
    private void initComponents(){
        
        homeBtn = new JButton("Home");
        checkInBtn = new JButton("CheckIn");
        checkOutBtn = new JButton("Checkout");
        
        //buttons
        Dimension btnDim = new Dimension(190,50);
        homeBtn.setPreferredSize(btnDim);
        checkInBtn.setPreferredSize(btnDim);
        checkOutBtn.setPreferredSize(btnDim);
        
        //Frame setUp
        Dimension frameDim = new Dimension(800, 600);
        setName("MainFrame");
        setMinimumSize(frameDim);
        setPreferredSize(frameDim);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        //Container Panel
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        
        //Button Panel
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.RED);
        //btnPanel.setPreferredSize(new Dimension(200, 600));
       // btnPanel.setMinimumSize(new Dimension(200, 600));
        btnPanel.setSize(200,600);
        btnPanel.setLayout(new GridBagLayout());
        btnPanel.add(homeBtn, getLabelConstraints(0,0, GridBagConstraints.LINE_START));
        btnPanel.add(checkInBtn, getLabelConstraints(0,1, GridBagConstraints.LINE_START));
        btnPanel.add(checkOutBtn, getLabelConstraints(0,2, GridBagConstraints.LINE_START));
        
        
        //Main Display Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLUE);
        
        //add Panels
        containerPanel.add(btnPanel, BorderLayout.WEST);
        containerPanel.add(mainPanel);
        getContentPane().add(containerPanel);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private GridBagConstraints getLabelConstraints(int x, int y, int anchor) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.insets = new Insets(5,5,5,5);
        c.weightx = 0.5;
        c.gridx = x;
        c.gridy = y;
        c.anchor = anchor;
        return c;
    }
}
