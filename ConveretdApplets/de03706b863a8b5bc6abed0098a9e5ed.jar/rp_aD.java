import javax.swing.SwingUtilities;
import javax.swing.Icon;
import java.awt.Component;
import java.awt.event.ComponentListener;
import java.awt.Cursor;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_aD extends JPanel
{
    private static ImageIcon a;
    private rp_aJ a;
    private JTabbedPane a;
    
    public rp_aD(final rp_aJ a) {
        this.a = a;
        this.setBackground(rp_aJ.t);
    }
    
    public final void a() {
        this.setLayout(new BorderLayout(0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        (this.a = new JTabbedPane(1, 1)).setFont(rp_aJ.a);
        this.a.setCursor(Cursor.getPredefinedCursor(12));
        if (this.a.b()) {
            final rp_L rp_L3;
            final rp_L rp_L2;
            final rp_L rp_L = rp_L2 = (rp_L3 = new rp_L(this.a));
            rp_L.a = rp_L2.a();
            rp_L2.a.setCursor(Cursor.getPredefinedCursor(0));
            rp_L2.a.addComponentListener(new rp_dD(rp_L2));
            rp_L2.add(rp_L2.a, "Center");
            rp_L2.validate();
            rp_ae.a(rp_L2.a, new rp_dB(rp_L2), null);
            this.a.addTab(rp_au.b("M_YI"), null, rp_L3, rp_aJ.a(rp_au.a("tt_your")));
        }
        this.a.addTab(rp_au.b("M_WL"), null, new rp_di(this.a), rp_aJ.a(rp_au.a("tt_wl")));
        if (this.a.a("p_gen") == 1) {
            this.a.addTab(rp_au.b("M_GI"), null, new rp_bA(this.a), rp_aJ.a(rp_au.a("tt_gen")));
        }
        if (this.a.a("p_mfg") == 1) {
            final rp_eG rp_eG;
            (rp_eG = new rp_eG(this.a)).a();
            this.a.addTab(rp_au.b("M_SC"), null, rp_eG, rp_aJ.a(rp_au.a("tt_mfg")));
        }
        if (this.a.a("p_srch") == 1) {
            this.a.addTab(null, rp_aD.a, new rp_fY(this.a), rp_aJ.a(rp_au.a("tt_srch")));
        }
        final boolean b;
        if (!(b = (this.a.a().a() >= 10))) {
            SwingUtilities.invokeLater(new rp_ge(this));
        }
        this.add(this.a, "Center");
    }
    
    public final boolean a() {
        if (this.a() != null) {
            return true;
        }
        final rp_fZ rp_fZ;
        (rp_fZ = new rp_fZ(this.a)).a();
        final boolean b;
        if ((b = (this.a.a().a() >= 10)) || rp_fZ.a.b() > 0) {
            this.a.insertTab(rp_au.b("M_CI"), null, rp_fZ, rp_aJ.a(rp_au.a("tt_cus")), 1);
            return true;
        }
        return false;
    }
    
    public final boolean b() {
        final rp_fZ a;
        if ((a = this.a()) != null) {
            this.a.remove(a);
            return true;
        }
        return false;
    }
    
    public final rp_fZ a() {
        for (int i = 0; i < this.a.getTabCount(); ++i) {
            if (this.a.getComponentAt(i) instanceof rp_fZ) {
                return (rp_fZ)this.a.getComponentAt(i);
            }
        }
        return null;
    }
    
    public final boolean c() {
        if (this.a() != -1) {
            return true;
        }
        boolean b = false;
        if (((rp_bZ)this.a.a()).c) {
            b = true;
        }
        if (this.a.a("p_wl") == 1 && !b && this.a.a().b("cmWL", true)) {
            final rp_di rp_di = new rp_di(this.a);
            int n = 0;
            while (true) {
                for (int i = 0; i < this.a.getTabCount(); ++i) {
                    if (this.a.getComponentAt(i) instanceof rp_L) {
                        final int n2 = i;
                        if (n2 != -1) {
                            n = 1;
                        }
                        this.a.insertTab(rp_au.b("M_WL"), null, rp_di, rp_aJ.a(rp_au.a("tt_wl")), n);
                        return false;
                    }
                }
                final int n2 = -1;
                continue;
            }
        }
        return false;
    }
    
    public final boolean d() {
        final int a;
        if ((a = this.a()) != -1) {
            this.a.remove(a);
            return true;
        }
        return false;
    }
    
    private int a() {
        for (int i = 0; i < this.a.getTabCount(); ++i) {
            if (this.a.getComponentAt(i) instanceof rp_di) {
                return i;
            }
        }
        return -1;
    }
    
    static {
        rp_aD.a = new ImageIcon(rp_aD.class.getClassLoader().getResource("res/search.png"));
    }
}
