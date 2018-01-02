// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Container;

public final class cb extends bP
{
    private aO q;
    
    public cb(final Container container, final W w, final aO q) {
        super(container, w);
        this.q = q;
    }
    
    public final void q(final int n, final int n2, final Object o) {
        super.q(n, n2 + 20, (aW)o);
    }
    
    public final void e() {
        this.q.clear();
        this.q.put("mi_bgcr", Integer.toString(aS.w.x.getRGB(), 16));
        this.q.put("mi_bgcrin", Integer.toString(aS.w.c.getRGB(), 16));
        this.q.put("mi_fgcrin", Integer.toString(aS.w.v.getRGB(), 16));
        this.q.put("mi_" + 1 + "_txt", cz.f);
        this.q.put("mi_" + 2 + "_txt", cz.j);
    }
    
    public final void w(final aa aa) {
        final l l = (l)this.q;
        if (aa.q.equalsIgnoreCase(cz.f)) {
            this.q.r(l.s);
            return;
        }
        if (aa.q.equalsIgnoreCase(cz.j)) {
            this.w.q("" + l.s, this.q.q, 5);
        }
    }
}
