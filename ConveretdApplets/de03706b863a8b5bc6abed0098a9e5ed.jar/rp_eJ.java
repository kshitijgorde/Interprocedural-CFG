import java.util.Vector;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_eJ extends rp_fT
{
    public String a;
    public String b;
    public String c;
    
    public final int a() {
        return 1;
    }
    
    public rp_eJ() {
        this.a = null;
        this.b = null;
        this.c = null;
    }
    
    final boolean a(final Frame frame, final rp_dC rp_dC, final rp_dv rp_dv) {
        final rp_bw rp_bw;
        (rp_bw = new rp_bw(frame, rp_dC, rp_dv, this)).setVisible(true);
        return rp_bw.d();
    }
    
    public final String a() {
        return this.a;
    }
    
    public final String b() {
        if (this.c == null) {
            try {
                this.c = rp_au.a.a().a(super.d, this.a);
            }
            catch (Exception ex) {}
            if (this.c == null) {
                this.c = "";
            }
        }
        if (this.c != null && this.c.length() > 0) {
            return this.c;
        }
        return super.d;
    }
    
    public final void a(final Vector vector, final rp_dv rp_dv, final int n, final rp_eP rp_eP) {
        if (this.f != null && this.f.length() > 0) {
            vector.addElement(new String(this.f));
        }
        if (this.b != null && this.b.length() > 0) {
            vector.addElement(new String(this.b));
        }
        if (this.e != null && this.e.length() > 0) {
            vector.addElement(new String(this.e));
        }
        if (rp_dv != null) {
            vector.addElement(rp_dv.a(rp_eP));
        }
    }
}
