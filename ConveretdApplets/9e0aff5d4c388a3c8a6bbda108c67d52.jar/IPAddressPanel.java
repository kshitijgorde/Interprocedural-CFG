import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class IPAddressPanel extends JPanel implements ActionListener
{
    ActionListener l;
    IPfield j1;
    IPfield j2;
    IPfield j3;
    IPfield j4;
    
    public IPAddressPanel(final Color bgcolor) {
        this.setLayout(new FlowLayout());
        this.setBackground(bgcolor);
        this.j1 = new IPfield(3);
        this.j2 = new IPfield(3);
        this.j3 = new IPfield(3);
        this.j4 = new IPfield(3);
        this.add(this.j1);
        this.add(new JLabel("."));
        this.add(this.j2);
        this.add(new JLabel("."));
        this.add(this.j3);
        this.add(new JLabel("."));
        this.add(this.j4);
        this.j1.addActionListener(this);
        this.j2.addActionListener(this);
        this.j3.addActionListener(this);
        this.j4.addActionListener(this);
    }
    
    public void setEditable(final boolean b) {
        this.j1.setEditable(b);
        this.j2.setEditable(b);
        this.j3.setEditable(b);
        this.j4.setEditable(b);
    }
    
    public void inTransmission() {
        this.setEditable(false);
    }
    
    public void endTransmission() {
        this.j1.setText("");
        this.j2.setText("");
        this.j3.setText("");
        this.j4.setText("");
        this.j1.requestFocus();
        this.setEditable(true);
    }
    
    public String getIPAddress() {
        return new String(this.j1.getText() + "." + this.j2.getText() + "." + this.j3.getText() + "." + this.j4.getText());
    }
    
    public void addActionListener(final ActionListener _l) {
        this.l = _l;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.l.actionPerformed(new ActionEvent(this, 1, "event"));
    }
}
