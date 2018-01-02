// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.net.URL;
import java.awt.MenuItem;

public final class cd extends bi
{
    public cd(final W w) {
        super(w);
    }
    
    protected final void w() {
        this.e();
        this.q.put("mi_1_txt", cs.e);
        this.q.put("mi_1_I", "");
        for (int i = 1; i <= this.q.a.q; ++i) {
            this.q.put("mi_1_" + i + "_txt", ((MenuItem)this.q.a.w(i)).getLabel());
            this.q.put("mi_1_" + i + "_url", ((URL)this.q.s.w(i)).toString());
        }
        int n = this.q.a.q + 1;
        this.q.put("mi_1_" + n + "_txt", cy.q);
        ++n;
        this.q.put("mi_1_" + n + "_line", "");
        ++n;
        this.q.put("mi_1_" + n + "_txt", cy.w);
        ++n;
        this.q.put("mi_1_" + n + "_line", "");
        ++n;
        this.q.put("mi_1_" + n + "_txt", cy.e);
        for (int j = 1; j < 7; ++j) {
            this.q.put("mi_1_" + n + "_" + j + "_txt", bL.q()[j - 1]);
            this.q.put("mi_1_" + n + "_" + j + "_mark", "true");
        }
        ++n;
        this.q.put("mi_1_" + n + "_line", "");
        if (!aT.w.p()) {
            ++n;
            this.q.put("mi_1_" + n + "_txt", cy.u);
            ++n;
            this.q.put("mi_1_" + n + "_line", "");
        }
        ++n;
        this.q.put("mi_1_" + n + "_txt", cy.i);
        ++n;
        this.q.put("mi_1_" + n + "_line", "");
        ++n;
        if (aT.w.r() && cs.u) {
            this.w(1, n);
            ++n;
            this.q.put("mi_1_" + n + "_line", "");
        }
        if (!this.q.y) {
            ++n;
            this.q.put("mi_1_" + n + "_txt", cy.r);
        }
        if (!aT.w.r() && cs.u) {
            this.w(2, 0);
        }
    }
    
    public final void w(final aa aa) {
        if (aa.q.equalsIgnoreCase(cy.w)) {
            this.q.w(0);
            return;
        }
        if (aa.q.equalsIgnoreCase(cy.q)) {
            bi.q(this.q);
            return;
        }
        if (bL.q(aa.q)) {
            this.q.r(bL.q(this.q.a, this.q.s, this.q.d, this.q.f, bL.q(aa.q), this.q.u, 0));
            return;
        }
        if (aa.q.equalsIgnoreCase(cy.u)) {
            this.q.q("", -1, 4);
            return;
        }
        if (aa.q.equalsIgnoreCase(cy.i)) {
            new ap(this.q, this.q).setVisible(true);
            return;
        }
        if (aa.q.equalsIgnoreCase(cy.r)) {
            this.q.y();
            return;
        }
        if (aa.q != null) {
            this.q.q(aa.q, aa.w);
            return;
        }
        this.q(aa);
    }
}
