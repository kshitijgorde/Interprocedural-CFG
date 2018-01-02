// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.util.Hashtable;
import java.awt.Container;

public class ba extends aZ
{
    public ba(final Container container, final cV cv) {
        super(container, cv);
    }
    
    public final void t() {
        super.t();
        if (a.q() && this.w != null) {
            int n = 3;
            if (this.w.q(49) || this.w.q(1)) {
                this.q.put("mi_3_line", "");
                this.q.put("mi_4_txt", aJ.V);
                n = 5;
            }
            if (this.w.q(72)) {
                if (n == 3) {
                    final Hashtable q = this.q;
                    final StringBuffer append = new StringBuffer().append("mi_");
                    final int n2 = 3;
                    ++n;
                    q.put(append.append(n2).append("_line").toString(), "");
                }
                this.q.put("mi_" + n + "_txt", aJ.E);
            }
        }
    }
    
    public final void q(final aX ax) {
        if (!ax.q.equalsIgnoreCase(aJ.V)) {
            if (ax.q.equalsIgnoreCase(aJ.E)) {
                final Vector vector;
                (vector = new Vector()).addElement(new bV(this.w.p.w(), this.q.q));
                bx.q(vector, this.w);
            }
            return;
        }
        if (this.q.q != null) {
            new b(this.w.q(), ec.q(eb.q("User: %1"), this.q.w), "IP: " + this.q.q.i + "\nHost: " + this.q.q.o, this.w).setVisible(true);
            return;
        }
        new b(this.w.q(), eb.q("Info"), eb.q("User message should be choosen"), this.w).setVisible(true);
    }
}
