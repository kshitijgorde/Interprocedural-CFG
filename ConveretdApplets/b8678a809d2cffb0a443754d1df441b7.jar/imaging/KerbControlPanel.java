// 
// Decompiled by Procyon v0.5.30
// 

package imaging;

import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class KerbControlPanel extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 8765614044307238665L;
    private KerbCanvas kerbCanvas;
    
    public KerbControlPanel(final KerbCanvas kerbCanvas) {
        this.kerbCanvas = kerbCanvas;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.lightGray);
        final Panel innerPanel = new Panel();
        innerPanel.setLayout(new GridLayout(0, 1, 1, 2));
        final Font smallFont = new Font("SansSerif", 0, 10);
        final Font mediumFont = new Font("SansSerif", 0, 12);
        innerPanel.add(new JLabel("Draw kerb lines: "));
        this.add("North", innerPanel);
    }
    
    @Override
    public void actionPerformed(final ActionEvent aev) {
    }
}
