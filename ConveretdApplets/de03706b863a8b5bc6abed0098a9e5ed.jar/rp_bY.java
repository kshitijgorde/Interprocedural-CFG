import java.awt.event.ActionEvent;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.AbstractButton;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_bY extends JPanel implements ActionListener
{
    rp_aJ a;
    AbstractButton a;
    AbstractButton b;
    AbstractButton c;
    AbstractButton d;
    rp_U a;
    
    public rp_bY(final rp_aJ a) {
        this.a = null;
        this.a = a;
        this.setBackground(rp_aJ.t);
        this.setLayout(new BoxLayout(this, 0));
        this.add(Box.createRigidArea(new Dimension(10, 1)));
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.a(actionEvent.getActionCommand());
    }
    
    public final void a(final String s) {
        this.a.setBorderPainted(s.equals("cmArrow"));
        this.b.setBorderPainted(s.equals("cmTape"));
        this.c.setBorderPainted(s.equals("cmText"));
    }
    
    public final void a(final boolean b) {
        if (this.d != null) {
            this.d.setText(this.a.a().a(0, b ? "LOUT" : "LIN"));
        }
    }
}
