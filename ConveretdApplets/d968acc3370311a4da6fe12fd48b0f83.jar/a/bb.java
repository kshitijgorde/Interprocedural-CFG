// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Container;

public final class bb extends bc
{
    private M q;
    
    public bb(final Container container, final cV cv, final M q) {
        super(container, cv);
        this.q = q;
    }
    
    public final void q(final int n, final int n2, final Object o) {
        super.q(n, n2 + 20, (aF)o);
    }
    
    public final void t() {
        this.q.clear();
        this.q.put("mi_bgcr", Integer.toString(cf.w.x.getRGB(), 16));
        this.q.put("mi_bgcrin", Integer.toString(cf.w.c.getRGB(), 16));
        this.q.put("mi_fgcrin", Integer.toString(cf.w.v.getRGB(), 16));
        this.q.put("mi_" + 1 + "_txt", aJ.R);
        this.q.put("mi_" + 2 + "_txt", aJ.U);
    }
    
    public final void q(final aX ax) {
        final cz cz = (cz)this.q;
        if (ax.q.equalsIgnoreCase(aJ.R)) {
            this.q.e(cz.q());
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.U)) {
            this.q.q("" + cz.q(), this.q.q(), 5);
        }
    }
}
