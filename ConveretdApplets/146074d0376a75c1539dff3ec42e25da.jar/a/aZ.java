// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Container;

public class aZ extends bf
{
    protected cS q;
    
    public aZ(final Container container, final cV cv) {
        super(container, cv);
    }
    
    public void q(final int n, final int n2, final Object o) {
        this.q = (cS)o;
        if ((a.q() && !cf.w.u()) || (a.w() && !cf.w.o())) {
            super.q(n, n2);
        }
    }
    
    public void t() {
        this.q.clear();
        this.q.put("mi_bgcr", Integer.toString(cf.w.x.getRGB(), 16));
        this.q.put("mi_bgcrin", Integer.toString(cf.w.c.getRGB(), 16));
        this.q.put("mi_fgcrin", Integer.toString(cf.w.v.getRGB(), 16));
        this.q.put("mi_1_txt", aJ.Q);
        this.q.put("mi_2_txt", aJ.W);
    }
    
    public void q(final aX ax) {
        if (ax.q.equalsIgnoreCase(aJ.Q)) {
            String string = "";
            if (this.q.q != null) {
                string = this.q.q.getName() + ", ";
            }
            ea.q(string + this.q.e + ea.q + this.q.q, this.q.q);
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.W)) {
            ea.q(this.q.q, this.q.q);
        }
    }
}
