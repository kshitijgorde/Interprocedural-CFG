import java.net.URL;
import java.awt.Image;
import java.awt.Component;
import javax.swing.ButtonModel;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_fE extends JPanel implements rp_ai, rp_dX
{
    rp_v a;
    rp_es a;
    private JLabel a;
    rp_fA a;
    
    public rp_fE(final rp_es a, final rp_aJ rp_aJ, final rp_v a2) {
        this.a = null;
        this.a = a;
        this.a = a2;
        this.a = new rp_fA(a2);
        final String string = rp_aJ.a().a(0, "Nm") + ": " + this.a();
        String string2 = "";
        if (!a2.a()) {
            string2 = "<br>" + rp_aJ.a().a(0, "Desc") + ": " + ((this.a == null) ? "---" : this.a.b);
        }
        final String a3 = rp_aJ.a(string + string2);
        this.setToolTipText(a3);
        this.setLayout(new FlowLayout(1, 0, 0));
        this.a = null;
        if (a2.a()) {
            final Image a4;
            if ((a4 = rp_aJ.a("folder_big.png")) != null) {
                this.a = new JLabel(new ImageIcon(a4));
            }
            else {
                this.a = new JLabel(rp_aJ.a().a(0, "no_im"));
            }
        }
        else if (a.e()) {
            this.a = new JLabel("---------");
        }
        else {
            try {
                final URL a5;
                if ((a5 = rp_aJ.a().a(a2, 200, 150)) != null) {
                    this.a = new JLabel(rp_aJ.a().a(0, "ld1"));
                    new rp_gb(rp_aJ.a(), a5, this, 200, this.a).c();
                }
                else {
                    this.a = new JLabel(rp_aJ.a().a(0, "no_im"));
                }
            }
            catch (Exception ex) {
                rp_C.a(1, "Plan stamp loading error [" + this.a() + "]\n" + ex);
                this.a = new JLabel("missing image");
            }
        }
        this.a.setToolTipText(a3);
        this.a.setPreferredSize(new Dimension(200, 150));
        this.a.setCursor(Cursor.getPredefinedCursor(12));
        this.a.addMouseListener(new rp_dU(this));
        final JPanel panel;
        (panel = new JPanel(new BorderLayout(5, 0))).setBackground(rp_aJ.b);
        final JCheckBox checkBox;
        (checkBox = new JCheckBox(this.a())).addActionListener(new rp_dR(this));
        checkBox.setModel(this.a);
        panel.add(checkBox);
        panel.setPreferredSize(new Dimension(200, 28));
        final JPanel panel2;
        (panel2 = new JPanel(new BorderLayout())).add(this.a, "Center");
        panel2.add(panel, "South");
        this.add(panel2);
    }
    
    public final void a() {
        final URL a;
        if (this.a.getIcon() == null && (a = this.a.a.a().a(this.a, 200, 150)) != null) {
            new rp_gb(this.a.a.a(), a, this, 200, this.a).c();
        }
    }
    
    private String a() {
        if (this.a == null) {
            return "---";
        }
        return this.a.a;
    }
    
    public final String toString() {
        return this.a();
    }
    
    public final void a(final Image image, final int n) {
        if (image != null) {
            this.a.setText(null);
            this.a.setIcon(new ImageIcon(image));
        }
    }
    
    public final JComponent a() {
        return this;
    }
}
