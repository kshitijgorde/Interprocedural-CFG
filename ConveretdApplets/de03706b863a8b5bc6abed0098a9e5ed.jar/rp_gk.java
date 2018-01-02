import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Vector;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_gk implements rp_cI
{
    private String[] a;
    private rp_fB a;
    
    public rp_gk(final rp_fB a, final String[] a2) {
        this.a = a;
        this.a = a2;
    }
    
    public final int a() {
        if (this.a != null) {
            return this.a.length;
        }
        return 0;
    }
    
    public final InputStream a(final rp_fK rp_fK, final Object o) {
        int a = 0;
        int n = this.a.length;
        if (o != null && o instanceof rp_ea) {
            final rp_ea rp_ea;
            a = (rp_ea = (rp_ea)o).a;
            n = rp_ea.b;
        }
        final String[] array = new String[n];
        int n2 = 0;
        for (int i = a; i < Math.min(this.a.length, a + n); ++i) {
            if (this.a.a(this.a[i]) == null) {
                array[n2++] = this.a[i];
            }
        }
        if (n2 == 0) {
            return null;
        }
        return rp_fK.a(20, array);
    }
    
    public final boolean a(final InputStream inputStream, final Vector vector, final Dimension dimension) {
        if (inputStream != null) {
            this.a.a(inputStream);
        }
        return true;
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
        final int a = ((rp_ea)o2).a;
        final int b = ((rp_ea)o2).b;
        if (rp_dl.a != null && this != rp_dl.a) {
            System.out.println("UserData mismatch");
            return;
        }
        dimension.width = 0;
        dimension.height = 0;
        for (int i = a; i < a + b; ++i) {
            final rp_aV a2;
            if (rp_dl.a(i) == null && (a2 = this.a.a(this.a[i])) != null) {
                dimension.width = Math.max(dimension.width, a2.c());
                dimension.height = Math.max(dimension.height, a2.d());
            }
        }
        for (int j = a; j < a + b; ++j) {
            final rp_aV a3;
            if (rp_dl.a(j) == null && (a3 = this.a.a(this.a[j])) != null) {
                rp_dl.a(j, new rp_fH(new rp_fn(this.a[j], a3), rp_dl));
                SwingUtilities.invokeLater(new rp_eo(this));
            }
        }
        rp_dl.a(dimension.width, dimension.height);
    }
}
