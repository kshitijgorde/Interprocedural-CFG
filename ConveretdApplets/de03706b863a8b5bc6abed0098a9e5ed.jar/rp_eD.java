import javax.swing.JScrollPane;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_eD extends rp_fG implements rp_fC
{
    rp_fT a;
    rp_dv a;
    rp_dC a;
    private rp_cB a;
    rp_ch a;
    private Frame a;
    JTextArea a;
    
    public rp_eD(final Frame a, final rp_dC a2, final rp_dv a3, final rp_fT a4) {
        super(a4.b());
        this.a = null;
        this.a(rp_au.a.a());
        this.a = a;
        this.a = a2;
        this.a = a4;
        this.a = a3;
        this.a();
        this.b();
    }
    
    public final void a(final JPanel panel) {
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        final JPanel panel2 = new JPanel(new BorderLayout());
        final JPanel panel3 = new JPanel(new BorderLayout());
        (this.a = new rp_cB(new Dimension(200, 150), this.a)).setBackground(rp_aJ.l);
        panel3.add(this.a, "Center");
        final JPanel panel4;
        (panel4 = new JPanel()).add(new JLabel(rp_au.a("Cd"), 4));
        final ArrayList<rp_dZ> list;
        (list = new ArrayList<rp_dZ>()).add(new rp_dZ(Color.red, Color.pink));
        list.add(new rp_dZ(Color.cyan, Color.yellow));
        final Dimension preferredSize = new Dimension(120, 26);
        (this.a = new rp_ch(this.a, preferredSize, true, rp_au.a.a)).setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        this.a.setBackground(rp_aJ.c);
        this.a.setPreferredSize(preferredSize);
        this.a.a(this.a.a(), this.a.b());
        this.a.a(this);
        if (this.a.b()) {
            this.a.setEnabled(false);
        }
        panel4.add(this.a);
        panel3.add(panel4, "South");
        panel2.add(panel3, "West");
        final JPanel panel5;
        (panel5 = new JPanel(new BorderLayout())).setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        final Component a;
        if ((a = this.a()) != null) {
            panel5.add(a, "North");
        }
        panel2.add(panel5, "Center");
        panel.add(panel2, "North");
        final JPanel panel6;
        (panel6 = new JPanel(new BorderLayout())).setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        panel6.add(new JLabel(rp_au.a.a().a(0, "ii1")), "North");
        (this.a = new JTextArea(4, 6)).setBackground(rp_aJ.l);
        if (this.a.b()) {
            this.a.setText(rp_au.a("nava1") + "\n" + rp_au.a("nava2"));
            this.a.setEditable(false);
        }
        else {
            this.a.setText(this.a.a());
        }
        panel6.add(new JScrollPane(this.a), "Center");
        panel.add(panel6, "South");
        panel.doLayout();
    }
    
    Component a() {
        return null;
    }
    
    public boolean a() {
        return true;
    }
    
    public final void a(final Color a, final Color b) {
        this.a.a = a;
        this.a.b = b;
        this.a.repaint();
    }
}
