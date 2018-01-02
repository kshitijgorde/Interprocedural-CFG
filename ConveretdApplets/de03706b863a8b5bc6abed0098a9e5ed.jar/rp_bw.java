import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.Border;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_bw extends rp_eD
{
    rp_bw(final Frame frame, final rp_dC rp_dC, final rp_dv rp_dv, final rp_eJ rp_eJ) {
        super(frame, rp_dC, rp_dv, rp_eJ);
    }
    
    final Component a() {
        final JPanel panel;
        (panel = new JPanel(new GridLayout(7, 2, 0, 2))).setBorder(rp_ap.a(rp_au.a("ii2"), rp_aJ.o));
        rp_au.a.a().a(false);
        final rp_eJ rp_eJ = (rp_eJ)this.a;
        panel.add(new JLabel(rp_au.a("SKU") + ":"));
        panel.add(new JLabel((rp_eJ.b() == null) ? "" : rp_eJ.b()));
        panel.add(new JLabel(rp_au.a("Style") + ":"));
        panel.add(new JLabel((rp_eJ.f == null) ? "" : rp_eJ.f));
        panel.add(new JLabel(rp_au.a("Mfg") + ":"));
        String string;
        if ((string = ((rp_eJ.b == null) ? "" : rp_eJ.b)).length() > 30) {
            string = string.substring(0, 28) + "...";
        }
        panel.add(new JLabel(string));
        panel.add(new JLabel(rp_au.a("Grp") + ":"));
        panel.add(new JLabel((rp_eJ.e == null) ? "" : rp_eJ.e));
        panel.add(new JLabel(rp_au.a("Wd")));
        panel.add(new JLabel(rp_au.a.a().a(this.a.a())));
        panel.add(new JLabel(rp_au.a("Dd")));
        panel.add(new JLabel(rp_au.a.a().a(this.a.d())));
        panel.add(new JLabel(rp_au.a("Hd")));
        panel.add(new JLabel(rp_au.a.a().a(this.a.e())));
        this.a(this.a.a(), this.a.b());
        panel.doLayout();
        panel.setMaximumSize(panel.getPreferredSize());
        return panel;
    }
    
    public final boolean a() {
        final String text = super.a.getText();
        final Color a = this.a.a();
        final Color b = this.a.b();
        if (!text.equals(this.a.a()) || (a == null && this.a.a() != null) || (a != null && !a.equals(this.a.a())) || (b == null && this.a.b() != null) || (b != null && !b.equals(this.a.b()))) {
            if (this.a.h > 0) {
                rp_au.a.a.a(new rp_cH(this.a, text, a, b));
            }
            rp_au.a.a(a, b);
        }
        return true;
    }
}
