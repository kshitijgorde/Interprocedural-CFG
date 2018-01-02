import java.util.Vector;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_dK extends rp_fT
{
    public final int a() {
        return 2;
    }
    
    public rp_dK() {
    }
    
    public rp_dK(final rp_fT rp_fT) {
        super(rp_fT);
    }
    
    final boolean a(final Frame frame, final rp_dC rp_dC, final rp_dv rp_dv) {
        final rp_bi rp_bi;
        (rp_bi = new rp_bi(frame, rp_dC, rp_dv, this)).setVisible(true);
        return rp_bi.d();
    }
    
    public final void a(final Vector vector, final rp_dv rp_dv, final int n, final rp_eP rp_eP) {
        if (rp_dv != null) {
            vector.addElement(rp_dv.a(rp_eP));
        }
    }
    
    final String a() {
        return rp_aJ.e;
    }
}
