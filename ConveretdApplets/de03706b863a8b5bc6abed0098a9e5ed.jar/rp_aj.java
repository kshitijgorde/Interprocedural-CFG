import java.awt.Component;
import java.awt.Dimension;
import java.util.Vector;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_aj extends rp_cJ
{
    public final InputStream a(final rp_fK rp_fK, final Object o) {
        if (o != null && o instanceof rp_k) {
            return rp_fK.a((rp_k)o);
        }
        return null;
    }
    
    public final void a(final Object o, Vector vector, Dimension dimension, final Object o2) {
        if (o instanceof rp_fY) {
            ((rp_fY)o).a(vector, dimension, o2, this.a);
        }
        if (!(o2 instanceof rp_k)) {
            return;
        }
        if (o instanceof rp_ck) {
            final rp_ck rp_ck = (rp_ck)o;
            final rp_k rp_k = (rp_k)o2;
            final Vector<Object> vector2 = vector;
            dimension = dimension;
            vector = vector2;
            final rp_k rp_k2 = rp_k;
            final rp_ck rp_ck2 = rp_ck;
            rp_ck.a.height = Math.max(rp_ck2.a.height, dimension.height);
            rp_ck2.a.width = Math.max(rp_ck2.a.width, dimension.width);
            rp_ck2.a.setSize(rp_k2.d + rp_k2.e);
            for (int i = 0; i < vector.size(); ++i) {
                rp_ck2.a.setElementAt(vector.elementAt(i), rp_k2.d + i);
            }
            for (int j = rp_k2.d; j < rp_k2.d + rp_k2.f + 1; ++j) {
                rp_ck2.a(j, new rp_cZ((rp_dv)rp_ck2.a.elementAt(j), rp_ck2));
            }
            rp_ck2.a(dimension.width, dimension.height);
            rp_ck2.a((Component)null);
            rp_ck2.setEnabled(true);
        }
    }
}
