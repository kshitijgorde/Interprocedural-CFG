import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.BorderFactory;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class NetworkControl extends JPanel implements ActionListener
{
    JRadioButton dMode;
    JRadioButton tMode;
    int comp;
    
    public NetworkControl() {
        this.comp = 0;
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.dMode = new JRadioButton("Demo Mode");
        this.tMode = new JRadioButton("Test Mode");
        final ButtonGroup buttg = new ButtonGroup();
        buttg.add(this.dMode);
        buttg.add(this.tMode);
        this.dMode.addActionListener(this);
        this.tMode.addActionListener(this);
        this.add(this.dMode);
        this.add(this.tMode);
    }
    
    public void setBackground(final Color c) {
        super.setBackground(c);
        if (this.dMode != null) {
            this.dMode.setBackground(c);
        }
        if (this.tMode != null) {
            this.tMode.setBackground(c);
        }
    }
    
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == this.tMode) {
            Network.cp.setDemoMode(false);
        }
        else {
            Network.cp.setDemoMode(true);
        }
        Network.cp.initAnimation();
    }
}
