/*
 * 
 * CheckOutPanel
 * 
 */
package hotelproject.app;

import hotelproject.db.guestDatabaseConn;
import java.sql.Connection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author vatsalpatel
 */
public class checkOutPanelUi extends JScrollPane{
    Connection conn;
    public checkOutPanelUi() {   
        initComponents();
    }
    
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private JTable      checkOutTable;
    
    private void initComponents(){
        conn = guestDatabaseConn.dbConnection();
        checkOutTable = new JTable(){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
            {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)){
                    String checkOutDate = checkOutTable.getModel().getValueAt(row, 4).toString();
                    String currDate = getCurrentDate();
                    if(checkOutDate.compareTo(currDate) <= 0){
                        c.setForeground(Color.RED);
                    }else{
                         c.setForeground(Color.BLACK);
                    }
                    //c.setBackground(row % 2 == 0 ? getBackground() : Color.LIGHT_GRAY);
                }
                return c;
            }
        };
        popTable(checkOutTable);
        checkOutTable.setRowHeight(25);
        checkOutTable.setRowMargin(5);
        checkOutTable.setShowGrid(true);
        checkOutTable.setGridColor(Color.BLACK);
        checkOutTable.setRowSelectionAllowed(false);
        this.setViewportView(checkOutTable);
    }
    
    private void popTable(JTable table) {
        table.getTableHeader().setFont(new Font("SansSerif", Font.ITALIC, 18));
        try {
            String query = "select GuestId, FirstName, LastName, ArrivalDate, DepartureDate from guestList";
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
    
    public void updateDepartureTable(){
        popTable(checkOutTable);
    }
    
    private static String getCurrentDate(){
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }
}