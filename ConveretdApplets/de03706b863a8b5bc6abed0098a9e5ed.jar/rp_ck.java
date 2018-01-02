import java.util.Collection;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_ck extends rp_dl implements rp_ar
{
    private rp_k a;
    Vector a;
    private int f;
    Dimension a;
    private rp_fY a;
    
    public rp_ck(final rp_aJ rp_aJ, final rp_fY a, final rp_k a2, final Vector vector, final int f, final Dimension a3) {
        super(new ImageIcon(rp_aJ.a("pl_l_arrow.png")), new ImageIcon(rp_aJ.a("pl_r_arrow.png")));
        this.a = new Vector();
        this.f = 0;
        this.a = a2;
        this.a = a;
        this.f = f;
        this.a.addAll(vector);
        this.a = a3;
        ((rp_aC)(super.a = new rp_ct(rp_aJ.a().a(), "jakasi action", rp_aJ.a().a()))).a(rp_aJ.a().a());
        super.a = new rp_cF(rp_aJ.a().a());
        this.setBackground(rp_aJ.v);
        this.setMinimumSize(new Dimension(240, 100));
        this.a((rp_ar)this);
        this.a(f, a2);
    }
    
    public final String toString() {
        if (this.a == null) {
            return "undefined";
        }
        final rp_k a = this.a;
        return "[ " + a.a + " ] - " + a.c;
    }
    
    public final void a(final rp_dl rp_dl, final int n, final int n2) {
        for (int n3 = n; n3 < this.a.size() && n3 < n + n2 && n3 < this.f; ++n3) {
            this.a(n3, new rp_cZ((rp_dv)this.a.elementAt(n3), rp_dl));
        }
        rp_dl.a(this.a.width, this.a.height);
        if (this.a.size() <= n + n2 && this.a.size() < this.f) {
            rp_ae.b(rp_dl);
            this.a.a(new rp_k(this.a, this.a.size(), 50, n + n2 - 1 - this.a.size()), this);
        }
    }
}
