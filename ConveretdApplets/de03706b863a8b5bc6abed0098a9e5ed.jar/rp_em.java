import java.util.Vector;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_em extends rp_aE
{
    private /* synthetic */ rp_v a;
    private /* synthetic */ rp_di a;
    
    rp_em(final rp_di a, final rp_v a2) {
        this.a = a;
        this.a = a2;
    }
    
    public final InputStream a(final rp_fK rp_fK, final Object o) {
        final rp_fq a;
        if ((a = rp_fK.a(rp_aw.f, new rp_v[] { this.a }, null)).a() && a instanceof rp_aS) {
            return ((rp_aS)a).a;
        }
        return null;
    }
    
    public final void a(Vector a) {
        if (a == null) {
            this.a.a.a(false);
            return;
        }
        final rp_v a2 = this.a;
        a = a;
        final rp_v rp_v = a2;
        a2.a = a;
        rp_v.a(2, true);
    }
}
