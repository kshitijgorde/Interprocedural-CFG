// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Container;

public final class bB extends bt
{
    private r q;
    
    public bB(final Container container, final W w) {
        super(container, w);
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (r)o;
        if ((cu.q() && !aU.w.u()) || (cu.w() && !aU.w.o())) {
            super.q(n, n2);
        }
    }
    
    public final void e() {
        this.q.clear();
        this.q.put("mi_bgcr", Integer.toString(aU.w.x.getRGB(), 16));
        this.q.put("mi_bgcrin", Integer.toString(aU.w.c.getRGB(), 16));
        this.q.put("mi_fgcrin", Integer.toString(aU.w.v.getRGB(), 16));
        this.q.put("mi_1_txt", cB.s);
        this.q.put("mi_2_txt", cB.d);
        if (cu.q() && this.q != null && (this.q.q(49) || this.q.q(1))) {
            this.q.put("mi_3_line", "");
            this.q.put("mi_4_txt", cB.Q);
        }
    }
    
    public final void w(final aa aa) {
        if (aa.q.equalsIgnoreCase(cB.s)) {
            String string = "";
            if (this.q.q != null) {
                string = this.q.q.o + ", ";
            }
            bo.q(string + this.q.e + bo.q + this.q.q, this.q.q);
            return;
        }
        if (aa.q.equalsIgnoreCase(cB.d)) {
            bo.q(this.q.q, this.q.q);
            return;
        }
        if (aa.q.equalsIgnoreCase(cB.Q)) {
            if (this.q.q != null) {
                new bT(this.q.q(), s.q(ak.q("User: %1"), this.q.w), "IP: " + this.q.q.y + "\nHost: " + this.q.q.u, this.q).setVisible(true);
                return;
            }
            new bT(this.q.q(), ak.q("Info"), ak.q("User message should be choosen"), this.q).setVisible(true);
        }
    }
}
