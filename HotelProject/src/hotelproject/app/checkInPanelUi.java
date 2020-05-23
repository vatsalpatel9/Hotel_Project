/*
 * 
 * The CheckIn Panel
 * 
 */
package hotelproject.app;

import java.sql.Connection;
import hotelproject.db.guestDatabaseConn;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author vatsalpatel
 */


public class checkInPanelUi extends JPanel{
    
    Connection conn;
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private JLabel      fNameLabel;
    private JLabel      lNameLabel;
    private JLabel      addressLabel;
    private JLabel      cityLabel;
    private JLabel      stateLabel;
    private JLabel      zipLabel;
    private JLabel      numOfNightsLabel;
    private JLabel      roomRateLabel;
    private JLabel      roomNumLabel;
    private JTextField  fNameField;
    private JTextField  lNameField;
    private JTextField  addressField;
    private JTextField  cityField;
    private JTextField  stateField;
    private JTextField  zipField;
    private JFormattedTextField  roomRateField;
    private JButton     continueBtn;
    private JSpinner    numOfNights;
    private JComboBox   roomNum;
    
    public checkInPanelUi(){
        initComponents();
    }
    
    private void initComponents(){
        
        conn = guestDatabaseConn.dbConnection();
        fNameLabel = new JLabel("First Name");
        lNameLabel = new JLabel("Last Name");
        addressLabel = new JLabel("Address");
        cityLabel = new JLabel("City");
        stateLabel = new JLabel("State");
        zipLabel = new JLabel("Zip Code");
        numOfNightsLabel = new JLabel("Nights");
        roomRateLabel = new JLabel("Rate");
        roomNumLabel = new JLabel("Room");
        
        fNameField = new JTextField();
        lNameField = new JTextField();
        addressField = new JTextField();
        cityField = new JTextField();
        stateField = new JTextField();
        zipField = new JTextField();
        continueBtn = new JButton("Continue");
        numOfNights = new JSpinner();
        roomNum = new JComboBox();
        roomRateField = new JFormattedTextField();
        MaskFormatter moneyMask = null;
        try {
            moneyMask = new MaskFormatter("##.##");
        } catch (ParseException ex) {
            Logger.getLogger(checkInPanelUi.class.getName()).log(Level.SEVERE, null, ex);
        }
        moneyMask.install(roomRateField);
        displayRoom();
      
        GroupLayout checkInPanelLayout = new GroupLayout(this);
        this.setLayout(checkInPanelLayout);
        checkInPanelLayout.setHorizontalGroup(
                checkInPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(checkInPanelLayout.createSequentialGroup()
                        .addGroup(checkInPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(checkInPanelLayout.createSequentialGroup()
                                .addGap(51,51,51)
                                .addGroup(checkInPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(fNameLabel, GroupLayout.PREFERRED_SIZE, 107,GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addressLabel, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(checkInPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(checkInPanelLayout.createSequentialGroup()
                                    .addComponent(fNameField, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lNameLabel, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lNameField, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
                                .addComponent(continueBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addressField)))
                            .addGroup(checkInPanelLayout.createSequentialGroup()
                                .addGap(49,49,49)
                                .addGroup(checkInPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                     .addGroup(checkInPanelLayout.createSequentialGroup()
                                        .addComponent(roomRateLabel, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(roomRateField, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                                        .addGap(184,184,184)
                                        .addComponent(numOfNightsLabel)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(numOfNights, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                                        .addGap(45,45,45)
                                        .addComponent(roomNumLabel)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(roomNum, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                                     .addGroup(checkInPanelLayout.createSequentialGroup()
                                        .addComponent(cityLabel, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cityField, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(stateLabel)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stateField, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47)
                                        .addComponent(zipLabel)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(zipField, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)))))
                        .addGap(38,38,38))
        );
        checkInPanelLayout.setVerticalGroup(
            checkInPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(checkInPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(checkInPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(fNameField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lNameField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(fNameLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lNameLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(checkInPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(addressLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(checkInPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cityField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(stateLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(stateField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(zipLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(zipField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cityLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(checkInPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(roomRateLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomRateField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(numOfNightsLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(numOfNights, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomNumLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomNum, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                    .addGroup(checkInPanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addComponent(continueBtn, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );    
       // checkInPanel.setBackground(bgColor);
        
        continueBtn.addActionListener((ActionListner) -> {
            checkInGuest();
        });
        
    }
    
    private void displayRoom(){
        try{
            String query = "SELECT RoomNum, RoomType, RoomStatus from roomView";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            while(rs.next()){
                String roomStatus = rs.getString("RoomStatus");
                if(roomStatus.equals("CLEAN")){
                    model.addElement(rs.getInt("RoomNum") +" "+ rs.getString("RoomType"));
                   // roomNum.addItem(rs.getInt("RoomNum") +" "+ rs.getString("RoomType"));
                }
                else if(roomStatus.equals("DIRTY")){
                    model.removeElement(rs.getInt("RoomNum") +" "+ rs.getString("RoomType"));
                    //roomNum.removeItem(rs.getInt("RoomNum") +" "+ rs.getString("RoomType"));
                }
            }
            roomNum.setModel(model);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getUpdateRooms(){
        displayRoom();
    }    
    
    private void checkInGuest(){
        try{
            String query = "INSERT INTO 'guestList' (FirstName, LastName, Address, City, State, ZipCode, Rate, ArrivalDate, DepartureDate) Values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst;
            pst = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, fNameField.getText());
            pst.setString(2, lNameField.getText());
            pst.setString(3, addressField.getText());
            pst.setString(4, cityField.getText());
            pst.setString(5, stateField.getText());
            pst.setString(6, zipField.getText());
            pst.setString(7, roomRateField.getText());
            pst.setString(8, getCurrentDate());
            pst.setString(9, setCheckOutDate((int) numOfNights.getValue()));
            
            int n1 = pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            int GuestID = 0;
            if(rs.next()){
                GuestID = rs.getInt(1);
            }
            rs.close();
            
            String selRoom = (String) roomNum.getSelectedItem();
            int room = Integer.parseInt(selRoom.substring(0, 2));
            
            String roomQuery = "UPDATE 'roomView' SET RoomRate = ?, GuestID = ?, FirstName = ?, LastName = ?, Arrival = ?, Departure = ?, RoomStatus = 'DIRTY' WHERE RoomNum = '"+room+"'";
            PreparedStatement pstRoom = conn.prepareStatement(roomQuery);
            pstRoom.setString(1, roomRateField.getText());
            pstRoom.setInt(2, GuestID);
            pstRoom.setString(3, fNameField.getText());
            pstRoom.setString(4, lNameField.getText());
            pstRoom.setString(5, getCurrentDate());
            pstRoom.setString(6, setCheckOutDate((int) numOfNights.getValue()));
            
            
            int n2 = pstRoom.executeUpdate();
            if(n1 > 0 && n2 > 0){
                JOptionPane.showMessageDialog(null, "Success");
                clearJTextFields(this);
                resetJSpinner(this);
            }
            
            pst.close();
            pstRoom.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        displayRoom();
    }
    
    private static String getCurrentDate(){
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }
    
    private static String setCheckOutDate(int date){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, date);
        return sdf.format(cal.getTime());
    }
    
    private void clearJTextFields(JPanel panel){
        for(Component control: panel.getComponents()){
            if(control instanceof JTextField){
                JTextField ctrl = (JTextField) control;
                ctrl.setText(null);
            }
        }
    }
    
    private void resetJSpinner(JPanel panel){
        for(Component control: panel.getComponents()){
            if(control instanceof JSpinner){
                JSpinner ctrl = (JSpinner) control;
                ctrl.setValue(0);
            }
        }
    }
    
}