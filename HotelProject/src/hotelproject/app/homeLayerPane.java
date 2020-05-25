/*
 * 
 * layered panel for GuestDetailView and RoomView
 */
package hotelproject.app;

import hotelproject.db.guestDatabaseConn;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author vatsalpatel
 */
public class homeLayerPane extends JLayeredPane{
    Connection conn;
    public homeLayerPane(){
        initComponents();
    }
    
    private CardLayout card;
    private guestDetailPanelUi guestDetailPanel;
    private JScrollPane tableScroll;
    private JTable      guestListTable;
    
    private void initComponents(){
        card = new CardLayout();
        guestDetailPanel = new guestDetailPanelUi();
        
        conn = new guestDatabaseConn().dbConnection();
       

        guestListTable = new JTable();
        tableScroll = new JScrollPane();
        
        popRoomTable();
        guestListTable.setRowHeight(25);
        guestListTable.setRowMargin(5);
        guestListTable.setShowGrid(true);
        guestListTable.setGridColor(Color.BLACK);
        guestListTable.setRowSelectionAllowed(false);
        tableScroll.setViewportView(guestListTable);   
        
        guestListTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt){
                viewGuest();
            }
        });
        
        this.setLayout(card);
        this.add(tableScroll, "card1");
        this.add(guestDetailPanel, "card2");
    }
    
    protected void viewGuest(){
        try{
            int row = guestListTable.getSelectedRow();
            String tableClick = (guestListTable.getModel().getValueAt(row, 3).toString());
            card.show(this, "card2");
            guestDetailPanel.requestFocusInWindow();
            guestDetailPanel.viewGuestDetail(tableClick);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No Guest Present In The Selected Room");
        }
    }
    
    protected void popRoomTable() {
        guestListTable.getTableHeader().setFont(new Font("SansSerif", Font.ITALIC, 18));
        try {
            String query = "select RoomNum, RoomType, RoomStatus, GuestID, FirstName, LastName, Departure from roomView";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            guestListTable.setModel(DbUtils.resultSetToTableModel(rs));
            pst.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        guestListTable.setDefaultEditor(Object.class, null);
    }
}
