// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;

public final class aG extends ay
{
    private bp q;
    private cj q;
    
    public aG(final Frame frame, final bI q, final bp q2, final int n, final int n2) {
        this.q = q;
        this.q = q2;
        this.t();
        this.q(frame);
        this.q(n, n2);
        this.q = new cj(q, q2.q());
    }
    
    protected final void t() {
        this.y();
        this.q.put("mi_1_txt", a.e);
        this.q.put("mi_1_I", "");
        int n = 1;
        this.q.put("mi_1_" + 1 + "_txt", au.f);
        if (this.q.a_() || !this.q.a_()) {
            ++n;
            this.q.put("mi_1_" + 2 + "_txt", au.g);
        }
        ++n;
        if (a.w() && (this.q.a_() || !this.q.a_())) {
            this.q.put("mi_1_" + n + "_txt", au.h);
            return;
        }
        if (a.q()) {
            this.q.put("mi_1_" + n + "_txt", au.j);
        }
    }
    
    public final void q(final aI ai) {
        if (ai.q.equalsIgnoreCase(au.f)) {
            this.q.e(this.q.q());
            return;
        }
        if (ai.q.equalsIgnoreCase(au.g)) {
            this.q.q.q(this.q);
            return;
        }
        if (ai.q.equalsIgnoreCase(au.h)) {
            this.q.q(this.q);
            return;
        }
        if (ai.q.equalsIgnoreCase(au.j)) {
            new U(this.q, this.q);
        }
    }
}
