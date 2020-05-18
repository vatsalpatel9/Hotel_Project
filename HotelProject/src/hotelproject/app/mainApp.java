/*
 * The main Application Frame
 * 
 * @author vatsalpatel
 */
package hotelproject.app;

import java.sql.Connection;
import hotelproject.db.guestDatabaseConn;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import net.proteanit.sql.DbUtils;

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
    
    private JButton     homeBtn;
    private JButton     checkInBtn;
    private JButton     checkOutBtn;
    private JPanel      checkInPanel;
    private JPanel      checkOutPanel;
    private JScrollPane tableScrollPane;
    private JTable      guestListTable;
    
    private void initComponents(){
        
        homeBtn = new JButton("Home");
        checkInBtn = new JButton("CheckIn");
        checkOutBtn = new JButton("Checkout");
        
        //buttons
        Dimension btnDim = new Dimension(190,50);
        homeBtn.setPreferredSize(btnDim);
        checkInBtn.setPreferredSize(btnDim);
        checkOutBtn.setPreferredSize(btnDim);
        
        //setButton Names
        homeBtn.setName("homeBtn");
        checkInBtn.setName("checkInBtn");
        checkOutBtn.setName("checkOutBtn");
        
        //Frame setUp
        Dimension frameDim = new Dimension(800, 600);
        setName("MainFrame");
        setMinimumSize(frameDim);
        setPreferredSize(frameDim);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        //Container guestTableScrol
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        
        //Button guestTableScrol
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.RED);
        btnPanel.setPreferredSize(new Dimension(200, 600));
        btnPanel.setMinimumSize(new Dimension(200, 600));
        btnPanel.setLayout(new GridBagLayout());
        btnPanel.add(homeBtn, getLabelConstraints(0,0, GridBagConstraints.PAGE_START));
        btnPanel.add(checkInBtn, getLabelConstraints(0,1, GridBagConstraints.PAGE_START));
        btnPanel.add(checkOutBtn, getLabelConstraints(0,2, GridBagConstraints.PAGE_START));
        
        Color bgColor = new Color(245,245,245);
        
        //Main Display guestTableScrol
        JLayeredPane mainPanel = new JLayeredPane();
      //  mainPanel.setBackground(bgColor);
        mainPanel.setPreferredSize(new Dimension(600, 600));
        mainPanel.setLayout(new CardLayout());
        
        //LayeredPanel
        JLayeredPane layerPanel = new JLayeredPane();
        layerPanel.setLayout(new CardLayout());
        
        //Panel for Layered Pane
        tableScrollPane = new JScrollPane();
        checkInPanel = new JPanel();
        checkOutPanel = new JPanel();
        
        //homePanelTable
        guestListTable = new JTable();
        popTable(guestListTable);
        guestListTable.setRowHeight(25);
        guestListTable.setRowMargin(5);
        guestListTable.setShowGrid(true);
        guestListTable.setGridColor(Color.BLACK);
        guestListTable.setRowSelectionAllowed(false);
        tableScrollPane.setViewportView(guestListTable);
        
        checkInPanel.setBackground(bgColor);
        checkInPanel.setVisible(false);
        
        checkOutPanel.setVisible(false);

        
        //addPanels to layered Pane
        mainPanel.add(tableScrollPane, "card1");
        mainPanel.add(checkInPanel, "card2");
        mainPanel.add(checkOutPanel, "card3");
        
        //Button Links
        homeBtn.addActionListener((ActionListner) -> {
            switchPanel(homeBtn);
        });
        checkInBtn.addActionListener((ActionListner) -> {
            switchPanel(checkInBtn);
        });
        checkOutBtn.addActionListener((ActionListner) -> {
            switchPanel(checkOutBtn);
        });

        //add Panels to main frame
        containerPanel.add(btnPanel, BorderLayout.WEST);
        containerPanel.add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(containerPanel, BorderLayout.CENTER);
        
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
    
    private void popTable(JTable table) {
        table.getTableHeader().setFont(new Font("SansSerif", Font.ITALIC, 18));
        try {
            String query = "select FirstName, LastName, ArrivalDate, DepartureDate from guestList";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        table.setDefaultEditor(Object.class, null);
    }
    
    private void switchPanel(JButton btn){
        String btnName = btn.getName();
        switch(btnName){
            case "homeBtn":
                checkInPanel.setVisible(false);
                checkOutPanel.setVisible(false);
                popTable(guestListTable);
                tableScrollPane.setVisible(true);
                break;
            case "checkInBtn":
                tableScrollPane.setVisible(false);
                checkOutPanel.setVisible(false);
                checkInPanel.setVisible(true);
                break;
            case "checkOutBtn":
                checkOutPanel.setVisible(true);
                tableScrollPane.setVisible(false);
                checkInPanel.setVisible(false);
                break;
            default:
                break;
        }
    }
}