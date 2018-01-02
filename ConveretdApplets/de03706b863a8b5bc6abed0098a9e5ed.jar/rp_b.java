import java.util.ArrayList;
import java.util.HashMap;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_b extends rp_gd
{
    public HashMap a;
    private rp_au a;
    private rp_fK a;
    
    public rp_b() {
        this.a = new HashMap();
        this.a = null;
        this.a = null;
    }
    
    public final void a(final String s, final rp_fn rp_fn, final rp_eY rp_eY, final Object o, final rp_cv rp_cv) {
        final ArrayList<rp_dj> list = this.a.get(s);
        final rp_dj rp_dj = new rp_dj(this, rp_fn, rp_eY, o, rp_cv);
        if (list == null) {
            final ArrayList list2;
            (list2 = new ArrayList()).add(rp_dj);
            this.a.put(s, list2);
            return;
        }
        list.add(rp_dj);
    }
    
    public final void a(final rp_fK a, final rp_au a2) {
        this.a = a2;
        this.a = a;
        this.c();
    }
}
