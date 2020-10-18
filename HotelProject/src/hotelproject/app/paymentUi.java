/*
 * 
 * Payment Panel
 * 
 */
package hotelproject.app;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author vatsalpatel
 */
public class paymentUi extends JPanel{
    public paymentUi(){
        initComponents();
    }
    
    private JLabel titleLabel;
    private JButton goBack;
    
    private void initComponents(){
        titleLabel = new JLabel("PAYMENT");
        goBack = new JButton("Go Back");
        
        this.add(titleLabel);
        this.add(goBack);
        
        goBack.addActionListener((ActionListner) -> {
            this.setVisible(false);
        });
    }
}
