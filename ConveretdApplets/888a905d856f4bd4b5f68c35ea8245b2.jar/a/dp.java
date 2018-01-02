// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Container;

public final class dp extends cY
{
    private bz q;
    
    public dp(final Container container, final ap ap, final bz q) {
        super(container, ap);
        this.q = q;
    }
    
    public final void q(final int n, final int n2, final Object o) {
        super.q(n, n2 + 20, (bJ)o);
    }
    
    public final void w() {
        this.q.clear();
        this.q.put("mi_bgcr", Integer.toString(bC.w.x.getRGB(), 16));
        this.q.put("mi_bgcrin", Integer.toString(bC.w.c.getRGB(), 16));
        this.q.put("mi_fgcrin", Integer.toString(bC.w.v.getRGB(), 16));
        this.q.put("mi_" + 1 + "_txt", dX.E);
        this.q.put("mi_" + 2 + "_txt", dX.Y);
    }
    
    public final void q(final at at) {
        final p p = (p)this.q;
        if (at.q.equalsIgnoreCase(dX.E)) {
            this.q.y(p.s);
            return;
        }
        if (at.q.equalsIgnoreCase(dX.Y)) {
            this.w.q("" + p.s, this.q.q, 5);
        }
    }
}
