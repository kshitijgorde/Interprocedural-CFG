// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Container;

public final class aL extends aM
{
    private J q;
    
    public aL(final Container container, final bI bi, final J q) {
        super(container, bi);
        this.q = q;
    }
    
    public final void q(final int n, final int n2, final Object o) {
        super.q(n, n2 + 20, (aq)o);
    }
    
    public final void t() {
        this.q.clear();
        this.q.put("mi_bgcr", Integer.toString(be.w.x.getRGB(), 16));
        this.q.put("mi_bgcrin", Integer.toString(be.w.c.getRGB(), 16));
        this.q.put("mi_fgcrin", Integer.toString(be.w.v.getRGB(), 16));
        this.q.put("mi_" + 1 + "_txt", au.f);
        this.q.put("mi_" + 2 + "_txt", au.j);
    }
    
    public final void q(final aI ai) {
        final bp bp = (bp)this.q;
        if (ai.q.equalsIgnoreCase(au.f)) {
            this.q.e(bp.q());
            return;
        }
        if (ai.q.equalsIgnoreCase(au.j)) {
            this.q.q("" + bp.q(), this.q.q(), 5);
        }
    }
}
