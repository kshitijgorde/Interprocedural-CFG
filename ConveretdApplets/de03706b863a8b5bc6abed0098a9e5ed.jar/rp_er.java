import java.awt.Window;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Frame;
import javax.swing.JTextField;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_er extends rp_fG
{
    private rp_bF a;
    private JTextField a;
    String a;
    int a;
    private Frame a;
    private rp_v a;
    private rp_aJ a;
    
    public rp_er(final Frame a, final rp_aJ a2, final rp_v a3, final rp_bF a4) {
        super((a4 == rp_bF.a) ? rp_au.a("end1") : rp_au.a("end5"));
        this.a = new JTextField();
        this.a = null;
        this.a = -9;
        this.a = a2;
        this.a = a;
        this.a = a3;
        this.a = a4;
        this.a(rp_au.a.a());
        this.a();
        this.b();
    }
    
    public final void a(final JPanel panel) {
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        final JPanel panel2;
        (panel2 = new JPanel()).add(new JLabel((this.a == rp_bF.a) ? rp_au.a("end2") : rp_au.a("end6")));
        this.a.setBackground(rp_aJ.l);
        this.a.setBorder(BorderFactory.createBevelBorder(1));
        this.a.setColumns(32);
        if (this.a == rp_bF.b) {
            this.a.setText(this.a.a);
            this.a.selectAll();
        }
        panel2.add(this.a);
        panel.add(panel2, "North");
        panel.add(new JLabel(rp_au.a("end3")), "South");
    }
    
    public final boolean a() {
        final String replaceAll = this.a.getText().replaceAll(" +$", "");
        rp_v a = this.a;
        if (this.a == rp_bF.b) {
            a = (rp_v)this.a.getParent();
        }
        final String s = replaceAll;
        final rp_v rp_v = a;
        final Frame a2 = this.a;
        final rp_v rp_v2 = rp_v;
        final String s2 = s;
        boolean b = false;
        Label_0131: {
            if (!rp_C.a(s, true, a2)) {
                b = false;
            }
            else {
                for (int i = 0; i < rp_v2.getChildCount(); ++i) {
                    if (s2.equalsIgnoreCase(((rp_v)rp_v2.getChildAt(i)).a)) {
                        rp_bd.a(a2, rp_au.a.a(), "err", "end4");
                        b = false;
                        break Label_0131;
                    }
                }
                b = true;
            }
        }
        if (b) {
            final rp_bg rp_bg;
            if ((rp_bg = (rp_bg)this.a.a().a((this.a == rp_bF.a) ? rp_aw.g : rp_aw.i, new rp_v[] { this.a }, replaceAll)).a()) {
                if (this.a == rp_bF.a) {
                    if (rp_bg.a(1) == null) {
                        return false;
                    }
                    this.a = Integer.parseInt(rp_bg.a(1));
                }
                this.a = replaceAll;
                return true;
            }
            if (!rp_bg.a()) {
                rp_bg.a(this.a.a());
            }
        }
        return false;
    }
}
