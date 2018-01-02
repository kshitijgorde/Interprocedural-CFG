// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Container;

public final class bz extends br
{
    private r q;
    
    public bz(final Container container, final W w) {
        super(container, w);
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (r)o;
        if ((cs.q() && !aS.w.u()) || (cs.w() && !aS.w.o())) {
            super.q(n, n2);
        }
    }
    
    public final void e() {
        this.q.clear();
        this.q.put("mi_bgcr", Integer.toString(aS.w.x.getRGB(), 16));
        this.q.put("mi_bgcrin", Integer.toString(aS.w.c.getRGB(), 16));
        this.q.put("mi_fgcrin", Integer.toString(aS.w.v.getRGB(), 16));
        this.q.put("mi_1_txt", cz.s);
        this.q.put("mi_2_txt", cz.d);
        if (cs.q() && this.q != null && (this.q.q(49) || this.q.q(1))) {
            this.q.put("mi_3_line", "");
            this.q.put("mi_4_txt", cz.Q);
        }
    }
    
    public final void w(final aa aa) {
        if (aa.q.equalsIgnoreCase(cz.s)) {
            String string = "";
            if (this.q.q != null) {
                string = this.q.q.o + ", ";
            }
            bm.q(string + this.q.e + bm.q + this.q.q, this.q.q);
            return;
        }
        if (aa.q.equalsIgnoreCase(cz.d)) {
            bm.q(this.q.q, this.q.q);
            return;
        }
        if (aa.q.equalsIgnoreCase(cz.Q)) {
            if (this.q.q != null) {
                new bR(this.q.q(), s.q(ak.q("User: %1"), this.q.w), "IP: " + this.q.q.y + "\nHost: " + this.q.q.u, this.q).setVisible(true);
                return;
            }
            new bR(this.q.q(), ak.q("Info"), ak.q("User message should be choosen"), this.q).setVisible(true);
        }
    }
}
