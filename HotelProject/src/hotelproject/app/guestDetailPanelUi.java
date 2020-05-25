/*
 * guestDetailPanel view
 * 
 */
package hotelproject.app;

import hotelproject.db.guestDatabaseConn;
import java.sql.Connection;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;


/**
 *
 * @author vatsalpatel
 */
public class guestDetailPanelUi extends JPanel{
    public guestDetailPanelUi(){
        initComponents();
    }
    
    Connection conn;
    
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
    private JButton     goBackBtn;
    
    private void initComponents(){
        
        conn = new guestDatabaseConn().dbConnection();
        
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
        
        goBackBtn = new JButton("Go Back <ESC>");
        
        setTextFieldProperty(this);
        
        GroupLayout guestDetailLayout = new GroupLayout(this);
        this.setLayout(guestDetailLayout);
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
                                .addComponent(showDepDateField, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(guestDetailLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(guestDetailLayout.createSequentialGroup()
                                .addComponent(goBackBtn, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))))
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
                    .addComponent(showDepDateField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGap(18,18,18)
                .addGroup(guestDetailLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(goBackBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
        );  
        
        goBackBtn.addActionListener((ActionListner) -> {
            goBackCard();
        });
        
        this.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent evt){
                escPressed(evt);
            }
        });
        
        this.setFocusable(true);
    }
    
    private void goBackCard(){
        this.setVisible(false);
    }
    
    private void escPressed(KeyEvent evt){
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            this.setVisible(false);
        }
    }
    
    protected void viewGuestDetail(String tableClick){
        try{
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
    
    private void setTextFieldProperty(JPanel panel){
        for(Component control: panel.getComponents()){
            if(control instanceof JTextField){
                JTextField ctrl = (JTextField) control;
                ctrl.setEditable(false);
            }
        }
    }
}