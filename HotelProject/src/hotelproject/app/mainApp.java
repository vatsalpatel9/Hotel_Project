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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import net.proteanit.sql.DbUtils;

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
    
    private checkInPanelUi checkInPanel;
    private checkOutPanelUi   checkOutPanel;
    private JButton     homeBtn;
    private JButton     checkInBtn;
    private JButton     checkOutBtn;
    private JPanel      guestDetailPanel;
    private JScrollPane tableScrollPane;
    private JTable      guestListTable;
    private JLabel      showGuestIdLabel;
    private JLabel      showFNameLabel;
    private JLabel      showLNameLabel;
    private JLabel      showAddressLabel;
    private JLabel      showCityLabel;
    private JLabel      showStateLabel;
    private JLabel      showZipLabel;
    private JLabel      showRateLabel;
    private JLabel      showAirDateLabel;
    private JLabel      showDepDateLabel;
    private JLabel      showRoomNumLabel;
    private JTextField  showRoomNumField;
    private JTextField  showGuestIdField;
    private JTextField  showFNameField;
    private JTextField  showLNameField;
    private JTextField  showAddressField;
    private JTextField  showCityField;
    private JTextField  showStateField;
    private JTextField  showZipField;
    private JTextField  showRateField;
    private JTextField  showAirDateField;
    private JTextField  showDepDateField;

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
        JLayeredPane mainPanel = new JLayeredPane();
        mainPanel.setBackground(bgColor);
        mainPanel.setPreferredSize(new Dimension(600, 600));
        mainPanel.setLayout(new CardLayout());
        
        //LayeredPanel
        JLayeredPane layerPanel = new JLayeredPane();
        layerPanel.setLayout(new CardLayout());
        
        //Panel for Layered Pane
        tableScrollPane = new JScrollPane();
        checkInPanel = new checkInPanelUi();
        checkOutPanel = new checkOutPanelUi();

       // checkOutPanel = new JScrollPane();
        guestDetailPanel = new JPanel();
        
        //homePanelTable
        guestListTable = new JTable();
        popRoomTable(guestListTable);
        guestListTable.setRowHeight(25);
        guestListTable.setRowMargin(5);
        guestListTable.setShowGrid(true);
        guestListTable.setGridColor(Color.BLACK);
        guestListTable.setRowSelectionAllowed(false);
        tableScrollPane.setViewportView(guestListTable);   
        
        //START of Guest Detial Panel
        showGuestIdLabel = new JLabel("Guest ID");
        showFNameLabel = new JLabel("First Name");
        showLNameLabel = new JLabel("Last Name");
        showAddressLabel = new JLabel("Address");
        showCityLabel = new JLabel("City");
        showStateLabel = new JLabel("State");
        showZipLabel = new JLabel("Zip Code");
        showRateLabel = new JLabel("Rate");
        showAirDateLabel = new JLabel("Arrival Date");
        showDepDateLabel = new JLabel("Departure Date");
        showRoomNumLabel = new JLabel("Room");
        
        showGuestIdField = new JTextField();
        showFNameField = new JTextField();
        showLNameField = new JTextField();
        showAddressField = new JTextField();
        showCityField = new JTextField();
        showStateField = new JTextField();
        showZipField = new JTextField();
        showRateField = new JTextField();
        showAirDateField = new JTextField();
        showDepDateField = new JTextField();
        showRoomNumField = new JTextField();
        
        guestListTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt){
                viewGuestDetail();
            }
        });
        
        GroupLayout guestDetailLayout = new GroupLayout(guestDetailPanel);
        guestDetailPanel.setLayout(guestDetailLayout);
        guestDetailLayout.setHorizontalGroup(
            guestDetailLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(guestDetailLayout.createSequentialGroup()
                    .addGap(51,51,51)
                    .addGroup(guestDetailLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(guestDetailLayout.createSequentialGroup()
                            .addComponent(showGuestIdLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(showGuestIdField, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                            .addGap(20,20,20)
                            .addComponent(showRoomNumLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(showRoomNumField, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
                        .addGroup(guestDetailLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(guestDetailLayout.createSequentialGroup()
                                .addComponent(showFNameLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(showFNameField, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
                                .addGap(47,47,47)
                                .addComponent(showLNameLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(showLNameField, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(guestDetailLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(guestDetailLayout.createSequentialGroup()
                                 .addComponent(showAddressLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                 .addComponent(showAddressField, GroupLayout.PREFERRED_SIZE, 585, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(guestDetailLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(guestDetailLayout.createSequentialGroup()
                                .addComponent(showCityLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(showCityField, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
                                .addGap(47,47,47)
                                .addComponent(showStateLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(showStateField, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                .addGap(47,47,47)
                                .addComponent(showZipLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(showZipField, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(guestDetailLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(guestDetailLayout.createSequentialGroup()
                                .addComponent(showRateLabel,GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(showRateField, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
                               // .addGap(165,165,165)
                               // .addComponent(numOfNightsLabel)
                               // .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                              //  .addComponent(numOfNights, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(guestDetailLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(guestDetailLayout.createSequentialGroup()
                                .addComponent(showAirDateLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(showAirDateField, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
                                .addGap(47,47,47)
                                .addComponent(showDepDateLabel)//, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(showDepDateField, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)))))
        );
        guestDetailLayout.setVerticalGroup(
            guestDetailLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addGroup(guestDetailLayout.createSequentialGroup()
                .addGap(28,28,28)
                .addGroup(guestDetailLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(showGuestIdLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(showGuestIdField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(showRoomNumLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(showRoomNumField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGap(18,18,18)
                .addGroup(guestDetailLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(showFNameLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(showFNameField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(showLNameLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(showLNameField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGap(18,18,18)
                .addGroup(guestDetailLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                     .addComponent(showAddressLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                     .addComponent(showAddressField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGap(18,18,18)
                .addGroup(guestDetailLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(showCityLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(showCityField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(showStateLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(showStateField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(showZipLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(showZipField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGap(18,18,18)
                .addGroup(guestDetailLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(showRateLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(showRateField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                   // .addComponent(numOfNightsLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    //.addComponent(numOfNights, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGap(18,18,18)
                .addGroup(guestDetailLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(showAirDateLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(showAirDateField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(showDepDateLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(showDepDateField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
        );
        
        //END OF GUEST DETAIL PANEL

        //addPanels to layered Pane
        checkInPanel.setVisible(false);
        checkOutPanel.setVisible(false);
        guestDetailPanel.setVisible(false);
        mainPanel.add(tableScrollPane, "card1");
        mainPanel.add(checkInPanel, "card2");
        mainPanel.add(checkOutPanel, "card3");
        mainPanel.add(guestDetailPanel, "card4");
        
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
    
    private void popRoomTable(JTable table) {
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
    }
    
    private void switchPanel(JButton btn){
        String btnName = btn.getName();
        switch(btnName){
            case "homeBtn":
                checkInPanel.setVisible(false);
                checkOutPanel.setVisible(false);
                popRoomTable(guestListTable);
                tableScrollPane.setVisible(true);
                break;
            case "checkInBtn":
                tableScrollPane.setVisible(false);
                checkOutPanel.setVisible(false);
                checkInPanel.setVisible(true);
                checkInPanel.getUpdateRooms();
                break;
            case "checkOutBtn":
                tableScrollPane.setVisible(false);
                checkInPanel.setVisible(false);
                checkOutPanel.updateDepartureTable();
                checkOutPanel.setVisible(true);
                break;
            default:
                break;
        }
    }

    private void setTextFieldProperty(JPanel panel){
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
    
}