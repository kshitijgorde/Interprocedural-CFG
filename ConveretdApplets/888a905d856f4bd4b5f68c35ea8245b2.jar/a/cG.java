// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Container;

public class cG extends cv
{
    protected A q;
    
    public cG(final Container container, final ap ap) {
        super(container, ap);
    }
    
    public void q(final int n, final int n2, final Object o) {
        this.q = (A)o;
        if ((dN.q() && !bC.w.u()) || (dN.w() && !bC.w.o())) {
            super.q(n, n2);
        }
    }
    
    public void w() {
        this.q.clear();
        this.q.put("mi_bgcr", Integer.toString(bC.w.x.getRGB(), 16));
        this.q.put("mi_bgcrin", Integer.toString(bC.w.c.getRGB(), 16));
        this.q.put("mi_fgcrin", Integer.toString(bC.w.v.getRGB(), 16));
        this.q.put("mi_1_txt", dX.Q);
        this.q.put("mi_2_txt", dX.W);
        if (dN.q() && this.q != null && (this.q.q(49) || this.q.q(1))) {
            this.q.put("mi_3_line", "");
            this.q.put("mi_4_txt", dX.X);
        }
    }
    
    public void q(final at at) {
        if (at.q.equalsIgnoreCase(dX.Q)) {
            String string = "";
            if (this.q.q != null) {
                string = this.q.q.a + ", ";
            }
            cm.q(string + this.q.e + cm.q + this.q.q, this.q.q);
            return;
        }
        if (at.q.equalsIgnoreCase(dX.W)) {
            cm.q(this.q.q, this.q.q);
            return;
        }
        if (at.q.equalsIgnoreCase(dX.X)) {
            if (this.q.q != null) {
                new dd(this.q.q(), B.q(be.w("User: %1"), this.q.w), "IP: " + this.q.q.u + "\nHost: " + this.q.q.i, this.q).setVisible(true);
                return;
            }
            new dd(this.q.q(), be.w("Info"), be.w("User message should be choosen"), this.q).setVisible(true);
        }
    }
}
