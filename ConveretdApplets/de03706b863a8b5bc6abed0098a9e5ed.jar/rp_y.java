import java.awt.Component;
import java.awt.Dimension;
import java.util.Vector;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_y extends rp_cJ
{
    private int b;
    private String a;
    
    public rp_y(final int b, final int a, final String a2) {
        this.b = b;
        this.a = a;
        this.a = a2;
    }
    
    public final InputStream a(final rp_fK rp_fK, final Object o) {
        int a = 0;
        int b = -1;
        if (o != null && o instanceof rp_ea) {
            final rp_ea rp_ea;
            a = (rp_ea = (rp_ea)o).a;
            b = rp_ea.b;
        }
        return rp_fK.a(this.b, this.a, a, b);
    }
    
    public final void a(final Object o, final Vector vector, final Dimension dimension, final Object o2) {
        if (!(o instanceof rp_dl)) {
            return;
        }
        final rp_dl rp_dl;
        (rp_dl = (rp_dl)o).a((Component)null);
        rp_dl.setEnabled(true);
        if (o2 == null || !(o2 instanceof rp_ea)) {
            return;
        }
        if (rp_dl.a != null && this != rp_dl.a) {
            System.out.println("UserData mismatch");
            return;
        }
        rp_dl.c(this.a);
        rp_b rp_b = null;
        final rp_ea rp_ea = (rp_ea)o2;
        if (vector != null) {
            for (int i = 0; i < vector.size(); ++i) {
                final rp_dv rp_dv = vector.elementAt(i);
                rp_cZ rp_cZ;
                if (this.b == 2) {
                    rp_cZ = new rp_fu(rp_dv, rp_dl);
                }
                else {
                    rp_cZ = new rp_cZ(rp_dv, rp_dl);
                }
                final rp_fn rp_fn;
                if (rp_dv != null && !rp_dv.a() && rp_dv instanceof rp_fn && (rp_fn = (rp_fn)rp_dv).a != null) {
                    if (rp_b == null) {
                        rp_b = new rp_b();
                    }
                    rp_b.a(rp_fn.a, rp_fn, rp_cZ, null, null);
                }
                rp_dl.a(rp_ea.a + i, rp_cZ);
            }
            rp_dl.a(dimension.width, dimension.height);
            if (rp_b != null) {
                rp_b.a(rp_au.a.a(), rp_au.a());
            }
        }
    }
}
