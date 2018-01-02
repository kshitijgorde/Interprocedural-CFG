// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;

public final class aV extends aN
{
    private cz q;
    private dP q;
    
    public aV(final Frame frame, final cV q, final cz q2, final int n, final int n2) {
        this.q = q;
        this.q = q2;
        this.t();
        this.q(frame);
        this.q(n, n2);
        this.q = new dP(q, q2.q());
    }
    
    protected final void t() {
        this.y();
        this.q.put("mi_1_txt", a.e);
        this.q.put("mi_1_I", "");
        int n = 1;
        this.q.put("mi_1_" + 1 + "_txt", aJ.R);
        if (this.q.a_() || !this.q.a_()) {
            ++n;
            this.q.put("mi_1_" + 2 + "_txt", aJ.T);
        }
        ++n;
        if (a.w() && (this.q.a_() || !this.q.a_())) {
            this.q.put("mi_1_" + n + "_txt", aJ.Y);
            return;
        }
        if (a.q()) {
            this.q.put("mi_1_" + n + "_txt", aJ.U);
        }
    }
    
    public final void q(final aX ax) {
        if (ax.q.equalsIgnoreCase(aJ.R)) {
            this.q.e(this.q.q());
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.T)) {
            this.q.q.q(this.q);
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.Y)) {
            this.q.q(this.q);
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.U)) {
            new af(this.q, this.q);
        }
    }
}
