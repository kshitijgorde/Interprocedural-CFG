import java.awt.Component;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class rp_ae extends JPanel implements rp_ar
{
    rp_aJ a;
    int a;
    
    public rp_ae(final rp_aJ a) {
        this.setBorder(BorderFactory.createLineBorder(rp_aJ.u));
        this.a = a;
        this.setBackground(rp_aJ.t);
    }
    
    final rp_dl a() {
        final rp_dl rp_dl;
        ((rp_aC)((rp_dl = new rp_dl(new ImageIcon(this.a.a("pl_l_arrow.png")), new ImageIcon(this.a.a("pl_r_arrow.png")))).a = new rp_ct(this.a.a().a(), "jakasi action", this.a.a().a()))).a(this.a.a().a());
        rp_dl.a = new rp_cF(this.a.a().a());
        rp_dl.setBackground(rp_aJ.v);
        rp_dl.setMinimumSize(new Dimension(240, 100));
        rp_dl.setPreferredSize(new Dimension(260, 100));
        rp_dl.a((rp_ar)this);
        return rp_dl;
    }
    
    static boolean a(final Object o, final rp_cI rp_cI, final Object o2) {
        if (rp_cI == null) {
            return false;
        }
        if (rp_cI instanceof rp_cJ || rp_cI instanceof rp_gk) {
            new rp_be(rp_au.a.a(), rp_cI, o, o2).c();
            return true;
        }
        rp_C.a(4, "*-* LoadZipThread missing *-*");
        return true;
    }
    
    static boolean b(final Object o, final rp_cI rp_cI, final Object o2) {
        if (rp_cI == null) {
            return false;
        }
        if (rp_cI instanceof rp_cJ || rp_cI instanceof rp_gk) {
            final rp_be rp_be = new rp_be(rp_au.a.a(), rp_cI, o, o2);
            try {
                rp_be.a(rp_be.a());
            }
            catch (Exception ex) {
                rp_C.a(10, "Exc. in SelectionActionSubpanel.immediateLoading " + ex.getMessage());
            }
            return true;
        }
        rp_C.a(4, "*-* LoadZipThread missing *-*");
        return true;
    }
    
    static rp_eA a(final int n) {
        rp_au.a().setCursor(Cursor.getPredefinedCursor(3));
        final rp_eA rp_eA = new rp_eA();
        try {
            rp_eA.a(new BufferedReader(new InputStreamReader(rp_au.a.a().a(n, null))));
            return rp_eA;
        }
        catch (Exception ex2) {
            final Exception ex = ex2;
            ex2.printStackTrace();
            System.out.println("XML List load: " + ex.getMessage() + " and: " + ex);
        }
        finally {
            rp_au.a().setCursor(Cursor.getPredefinedCursor(0));
        }
        return null;
    }
    
    final void a(final rp_dl rp_dl) {
        final Dimension a;
        if ((a = rp_dl.a()).width > 0 && a.height > 0) {
            this.a = 120;
            final int n = a.width / this.a;
            final int n2 = a.height / (this.a / 4 * 3);
            rp_dl.a(n);
            rp_dl.b(n2);
        }
    }
    
    static void b(final rp_dl rp_dl) {
        final rp_eB rp_eB;
        (rp_eB = new rp_eB(rp_au.a.a())).setBackground(rp_aJ.l);
        rp_dl.a(rp_eB);
        rp_dl.setEnabled(false);
    }
}
