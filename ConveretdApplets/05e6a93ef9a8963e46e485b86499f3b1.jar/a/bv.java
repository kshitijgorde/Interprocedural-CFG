// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;

public final class bv extends bi
{
    private l q;
    private T q;
    
    public bv(final Frame frame, final W q, final l q2, final int n, final int n2) {
        this.q = q;
        this.q = q2;
        this.w();
        this.q(frame);
        this.q(n, n2);
        this.q = new T(q, q2.a);
    }
    
    protected final void w() {
        this.e();
        this.q.put("mi_1_txt", cs.e);
        this.q.put("mi_1_I", "");
        int n = 1;
        this.q.put("mi_1_" + 1 + "_txt", cy.f);
        if (this.q.q(61) || !this.q.q(61)) {
            ++n;
            this.q.put("mi_1_" + 2 + "_txt", cy.g);
        }
        ++n;
        if (cs.w() && (this.q.q(61) || !this.q.q(61))) {
            this.q.put("mi_1_" + n + "_txt", cy.h);
            return;
        }
        if (cs.q()) {
            this.q.put("mi_1_" + n + "_txt", cy.j);
        }
    }
    
    public final void w(final aa aa) {
        if (aa.q.equalsIgnoreCase(cy.f)) {
            this.q.r(this.q.a);
            return;
        }
        if (aa.q.equalsIgnoreCase(cy.g)) {
            this.q.q.q(this.q);
            return;
        }
        if (aa.q.equalsIgnoreCase(cy.h)) {
            this.q.q(this.q);
            return;
        }
        if (aa.q.equalsIgnoreCase(cy.j)) {
            new z(this.q, this.q);
        }
    }
}
