// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;

public final class cy extends cg
{
    private p q;
    private aG q;
    
    public cy(final Frame frame, final ap q, final p q2, final int n, final int n2) {
        this.q = q;
        this.q = q2;
        this.w();
        this.q(frame);
        this.q(n, n2);
        this.q = new aG(q, q2.s);
    }
    
    protected final void w() {
        this.e();
        this.q.put("mi_1_txt", dN.e);
        this.q.put("mi_1_I", "");
        int n = 1;
        this.q.put("mi_1_" + 1 + "_txt", dX.E);
        if (this.q.q(61) || !this.q.q(61)) {
            ++n;
            this.q.put("mi_1_" + 2 + "_txt", dX.R);
        }
        ++n;
        if (dN.w() && (this.q.q(61) || !this.q.q(61))) {
            this.q.put("mi_1_" + n + "_txt", dX.T);
            return;
        }
        if (dN.q()) {
            this.q.put("mi_1_" + n + "_txt", dX.Y);
        }
    }
    
    public final void q(final at at) {
        if (at.q.equalsIgnoreCase(dX.E)) {
            this.q.y(this.q.s);
            return;
        }
        if (at.q.equalsIgnoreCase(dX.R)) {
            this.q.q.q(this.q);
            return;
        }
        if (at.q.equalsIgnoreCase(dX.T)) {
            this.q.q(this.q);
            return;
        }
        if (at.q.equalsIgnoreCase(dX.Y)) {
            new J(this.q, this.q);
        }
    }
}
