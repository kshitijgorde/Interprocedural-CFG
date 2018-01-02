// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;

public final class bw extends bj
{
    private l q;
    private S q;
    
    public bw(final Frame frame, final W q, final l q2, final int n, final int n2) {
        this.q = q;
        this.q = q2;
        this.w();
        this.q(frame);
        this.q(n, n2);
        this.q = new S(q, q2.s);
    }
    
    protected final void w() {
        this.e();
        this.q.put("mi_1_txt", cu.e);
        this.q.put("mi_1_I", "");
        int n = 1;
        this.q.put("mi_1_" + 1 + "_txt", cB.f);
        if (this.q.q(61) || !this.q.q(61)) {
            ++n;
            this.q.put("mi_1_" + 2 + "_txt", cB.g);
        }
        ++n;
        if (cu.w() && (this.q.q(61) || !this.q.q(61))) {
            this.q.put("mi_1_" + n + "_txt", cB.h);
            return;
        }
        if (cu.q()) {
            this.q.put("mi_1_" + n + "_txt", cB.j);
        }
    }
    
    public final void w(final aa aa) {
        if (aa.q.equalsIgnoreCase(cB.f)) {
            this.q.r(this.q.s);
            return;
        }
        if (aa.q.equalsIgnoreCase(cB.g)) {
            this.q.q.q(this.q);
            return;
        }
        if (aa.q.equalsIgnoreCase(cB.h)) {
            this.q.q(this.q);
            return;
        }
        if (aa.q.equalsIgnoreCase(cB.j)) {
            new y(this.q, this.q);
        }
    }
}
