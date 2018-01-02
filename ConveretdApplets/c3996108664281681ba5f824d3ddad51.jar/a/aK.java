// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Container;

public final class aK extends aO
{
    private bF q;
    
    public aK(final Container container, final bI bi) {
        super(container, bi);
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (bF)o;
        if ((a.q() && !be.w.u()) || (a.w() && !be.w.o())) {
            super.q(n, n2);
        }
    }
    
    public final void t() {
        this.q.clear();
        this.q.put("mi_bgcr", Integer.toString(be.w.x.getRGB(), 16));
        this.q.put("mi_bgcrin", Integer.toString(be.w.c.getRGB(), 16));
        this.q.put("mi_fgcrin", Integer.toString(be.w.v.getRGB(), 16));
        this.q.put("mi_1_txt", au.s);
        this.q.put("mi_2_txt", au.d);
    }
    
    public final void q(final aI ai) {
        if (ai.q.equalsIgnoreCase(au.s)) {
            String string = "";
            if (this.q.q != null) {
                string = this.q.q.getName() + ", ";
            }
            cu.q(string + this.q.e + cu.q + this.q.q, this.q.q);
            return;
        }
        if (ai.q.equalsIgnoreCase(au.d)) {
            cu.q(this.q.q, this.q.q);
        }
    }
}
