// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Container;

public final class bA extends bs
{
    private s q;
    
    public bA(final Container container, final W w) {
        super(container, w);
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (s)o;
        if ((cs.q() && !aT.w.u()) || (cs.w() && !aT.w.o())) {
            super.q(n, n2);
        }
    }
    
    public final void e() {
        this.q.clear();
        this.q.put("mi_bgcr", Integer.toString(aT.w.x.getRGB(), 16));
        this.q.put("mi_bgcrin", Integer.toString(aT.w.c.getRGB(), 16));
        this.q.put("mi_fgcrin", Integer.toString(aT.w.v.getRGB(), 16));
        this.q.put("mi_1_txt", cy.s);
        this.q.put("mi_2_txt", cy.d);
        if (cs.q() && this.q != null && (this.q.q(49) || this.q.q(1))) {
            this.q.put("mi_3_line", "");
            this.q.put("mi_4_txt", cy.Q);
        }
    }
    
    public final void w(final aa aa) {
        if (aa.q.equalsIgnoreCase(cy.s)) {
            String string = "";
            if (this.q.q != null) {
                string = this.q.q.o + ", ";
            }
            bn.q(string + this.q.e + bn.q + this.q.q, this.q.q);
            return;
        }
        if (aa.q.equalsIgnoreCase(cy.d)) {
            bn.q(this.q.q, this.q.q);
            return;
        }
        if (aa.q.equalsIgnoreCase(cy.Q)) {
            if (this.q.q != null) {
                new p(this.q.q(), t.q(al.q("User: %1"), this.q.w), "IP: " + this.q.q.y + "\nHost: " + this.q.q.u, this.q).setVisible(true);
                return;
            }
            new p(this.q.q(), al.q("Info"), al.q("User message should be choosen"), this.q).setVisible(true);
        }
    }
}
