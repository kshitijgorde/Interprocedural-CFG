// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Container;

public final class cd extends bR
{
    private aP q;
    
    public cd(final Container container, final W w, final aP q) {
        super(container, w);
        this.q = q;
    }
    
    public final void q(final int n, final int n2, final Object o) {
        super.q(n, n2 + 20, (aY)o);
    }
    
    public final void e() {
        this.q.clear();
        this.q.put("mi_bgcr", Integer.toString(aU.w.x.getRGB(), 16));
        this.q.put("mi_bgcrin", Integer.toString(aU.w.c.getRGB(), 16));
        this.q.put("mi_fgcrin", Integer.toString(aU.w.v.getRGB(), 16));
        this.q.put("mi_" + 1 + "_txt", cB.f);
        this.q.put("mi_" + 2 + "_txt", cB.j);
    }
    
    public final void w(final aa aa) {
        final l l = (l)this.q;
        if (aa.q.equalsIgnoreCase(cB.f)) {
            this.q.r(l.s);
            return;
        }
        if (aa.q.equalsIgnoreCase(cB.j)) {
            this.w.q("" + l.s, this.q.q, 5);
        }
    }
}
