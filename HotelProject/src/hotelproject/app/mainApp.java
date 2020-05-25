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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class mainApp extends JFrame{
    Connection conn;
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    
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
    
    private checkInPanelUi     checkInPanel;
    private checkOutPanelUi    checkOutPanel;
    private guestDetailPanelUi guestDetailPanel;
    private homeLayerPane      homeLayer;
   
    private JButton     homeBtn;
    private JButton     checkInBtn;
    private JButton     checkOutBtn;
    
    private JLayeredPane mainPanel;
    private CardLayout card;

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
        Dimension frameDim = new Dimension(1000, 600);
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
        mainPanel = new JLayeredPane();
        card = new CardLayout();
        mainPanel.setBackground(bgColor);
        mainPanel.setPreferredSize(new Dimension(600, 600));
        mainPanel.setLayout(card);
        
        //Panel for Layered Pane
        homeLayer = new homeLayerPane();
        checkInPanel = new checkInPanelUi();
        checkOutPanel = new checkOutPanelUi();

        //addPanels to layered Pane
        checkInPanel.setVisible(false);
        checkOutPanel.setVisible(false);
        guestDetailPanel.setVisible(false);
        mainPanel.add(homeLayer, "card1");
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
    
 /*   private void popRoomTable(JTable table) {
        table.getTableHeader().setFont(new Font("SansSerif", Font.ITALIC, 18));
        try {
            String query = "select RoomNum, RoomType, RoomStatus, GuestID, FirstName, LastName, Departure from roomView";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            pst.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        table.setDefaultEditor(Object.class, null);
    }*/
    
    private void switchPanel(JButton btn){
        String btnName = btn.getName();
        switch(btnName){
            case "homeBtn":
                homeLayer.popRoomTable();
                card.show(mainPanel, "card1");
                break;
            case "checkInBtn":
                checkInPanel.getUpdateRooms();
                card.show(mainPanel, "card2");
                break;
            case "checkOutBtn":
                checkOutPanel.updateDepartureTable();
                card.show(mainPanel, "card3");
                break;
            default:
                break;
        }
    }
    
    protected void setVisibleTrue(JPanel panel){
        panel.setVisible(true);
    }

/*    private void setTextFieldProperty(JPanel panel){
        for(Component control: panel.getComponents()){
            if(control instanceof JTextField){
                JTextField ctrl = (JTextField) control;
                ctrl.setEditable(false);
            }
        }
    }
    
    private void viewGuestDetail(){
        tableScrollPane.setVisible(false);
        setTextFieldProperty(guestDetailPanel);
        guestDetailPanel.setVisible(true);
        try{
            int row = guestListTable.getSelectedRow();
            String tableClick = (guestListTable.getModel().getValueAt(row, 3).toString());
            String query = "SELECT * FROM 'guestList' WHERE GuestId = '"+tableClick+"'";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String add1 = rs.getString(("GuestId").toString());
                    showGuestIdField.setText(add1);
                String add2 = rs.getString("FirstName");
                    showFNameField.setText(add2);
                String add3 = rs.getString("LastName");
                    showLNameField.setText(add3);
                String add4 = rs.getString("Address");
                    showAddressField.setText(add4);
                String add5 = rs.getString("City");
                    showCityField.setText(add5);
                String add6 = rs.getString("State");
                    showStateField.setText(add6);
                String add7 = rs.getString("ZipCode");
                    showZipField.setText(add7);
                String add8 = rs.getString(("Rate").toString());
                    showRateField.setText(add8);
                String add9 = rs.getString("ArrivalDate");
                    showAirDateField.setText(add9);
                String add10 = rs.getString("DepartureDate");
                    showDepDateField.setText(add10);
                String add11 = rs.getString("RoomNum");
                    showRoomNumField.setText(add11);
                
                pst.close();
                rs.close();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    */
}