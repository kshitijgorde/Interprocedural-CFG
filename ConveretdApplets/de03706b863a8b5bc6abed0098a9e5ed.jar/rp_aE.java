import java.awt.Dimension;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class rp_aE extends rp_cJ
{
    public final void a(final Object o, final Vector vector, final Dimension dimension, final Object o2) {
        if (!(o instanceof rp_dl)) {
            return;
        }
        this.a((rp_dl)o, vector, dimension);
    }
    
    final void a(final rp_dl rp_dl, final Vector vector, final Dimension dimension) {
        int max = 0;
        if (vector != null) {
            max = Math.max(vector.size(), this.a);
        }
        rp_dl.c(max);
        rp_b rp_b = null;
        if (vector != null) {
            for (int i = 0; i < vector.size(); ++i) {
                final rp_dv rp_dv = vector.elementAt(i);
                final rp_cZ rp_cZ = new rp_cZ(rp_dv, rp_dl);
                final rp_fn rp_fn;
                if (!rp_dv.a() && rp_dv instanceof rp_fn && (rp_fn = (rp_fn)rp_dv).a != null) {
                    if (rp_b == null) {
                        rp_b = new rp_b();
                    }
                    rp_b.a(rp_fn.a, rp_fn, rp_cZ, null, null);
                }
                rp_dl.a(i, rp_cZ);
            }
            rp_dl.a(dimension.width, dimension.height);
            this.a(vector);
            if (rp_b != null) {
                rp_b.a(rp_au.a.a(), rp_au.a());
            }
        }
        else {
            this.a((Vector)null);
        }
    }
    
    public abstract void a(final Vector p0);
}
