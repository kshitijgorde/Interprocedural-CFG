import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class p extends JPanel implements k
{
    JLabel do;
    
    public p(final o o) {
        this.do = new JLabel("     ");
        o.a(this);
        this.setLayout(new GridLayout(1, 2));
        this.add(new JLabel(o.do()));
        this.add(this.do);
    }
    
    public void a(final double n) {
        this.do.setText("" + n);
    }
}
