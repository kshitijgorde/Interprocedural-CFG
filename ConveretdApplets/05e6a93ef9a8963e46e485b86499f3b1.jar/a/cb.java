// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Container;

public final class cb extends bQ
{
    private aP q;
    
    public cb(final Container container, final W w, final aP q) {
        super(container, w);
        this.q = q;
    }
    
    public final void q(final int n, final int n2, final Object o) {
        super.q(n, n2 + 20, (aX)o);
    }
    
    public final void e() {
        this.q.clear();
        this.q.put("mi_bgcr", Integer.toString(aT.w.x.getRGB(), 16));
        this.q.put("mi_bgcrin", Integer.toString(aT.w.c.getRGB(), 16));
        this.q.put("mi_fgcrin", Integer.toString(aT.w.v.getRGB(), 16));
        this.q.put("mi_" + 1 + "_txt", cy.f);
        this.q.put("mi_" + 2 + "_txt", cy.j);
    }
    
    public final void w(final aa aa) {
        final l l = (l)this.q;
        if (aa.q.equalsIgnoreCase(cy.f)) {
            this.q.r(l.a);
            return;
        }
        if (aa.q.equalsIgnoreCase(cy.j)) {
            this.w.q("" + l.a, this.q.q, 5);
        }
    }
}
