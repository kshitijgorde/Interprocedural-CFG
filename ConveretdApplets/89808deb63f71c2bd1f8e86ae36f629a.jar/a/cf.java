// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.net.URL;
import java.awt.MenuItem;

public final class cf extends bj
{
    public cf(final W w) {
        super(w);
    }
    
    protected final void w() {
        this.e();
        this.q.put("mi_1_txt", cu.e);
        this.q.put("mi_1_I", "");
        for (int i = 1; i <= this.q.a.q; ++i) {
            this.q.put("mi_1_" + i + "_txt", ((MenuItem)this.q.a.w(i)).getLabel());
            this.q.put("mi_1_" + i + "_url", ((URL)this.q.s.w(i)).toString());
        }
        int n = this.q.a.q + 1;
        this.q.put("mi_1_" + n + "_txt", cB.q);
        ++n;
        this.q.put("mi_1_" + n + "_line", "");
        ++n;
        this.q.put("mi_1_" + n + "_txt", cB.w);
        ++n;
        this.q.put("mi_1_" + n + "_line", "");
        ++n;
        this.q.put("mi_1_" + n + "_txt", cB.e);
        for (int j = 1; j < 7; ++j) {
            this.q.put("mi_1_" + n + "_" + j + "_txt", bM.q()[j - 1]);
            this.q.put("mi_1_" + n + "_" + j + "_mark", "true");
        }
        ++n;
        this.q.put("mi_1_" + n + "_line", "");
        if (!aU.w.p()) {
            ++n;
            this.q.put("mi_1_" + n + "_txt", cB.u);
            ++n;
            this.q.put("mi_1_" + n + "_line", "");
        }
        ++n;
        if (!aU.w.d()) {
            this.q.put("mi_1_" + n + "_txt", cB.i);
            ++n;
        }
        this.q.put("mi_1_" + n + "_line", "");
        ++n;
        if (aU.w.r() && cu.u && !aU.w.s()) {
            this.w(1, n);
            ++n;
            this.q.put("mi_1_" + n + "_line", "");
        }
        if (!this.q.y) {
            ++n;
            this.q.put("mi_1_" + n + "_txt", cB.r);
        }
        if (!aU.w.r() && cu.u && !aU.w.s()) {
            this.w(2, 0);
        }
    }
    
    public final void w(final aa aa) {
        if (aa.q.equalsIgnoreCase(cB.w)) {
            this.q.r(0);
            return;
        }
        if (aa.q.equalsIgnoreCase(cB.q)) {
            bj.q(this.q);
            return;
        }
        if (bM.q(aa.q)) {
            this.q.r(bM.q(this.q.a, this.q.s, this.q.d, this.q.f, bM.q(aa.q), this.q.u, 0));
            return;
        }
        if (aa.q.equalsIgnoreCase(cB.u)) {
            this.q.q("", -1, 4);
            return;
        }
        if (aa.q.equalsIgnoreCase(cB.i)) {
            new ao(this.q, this.q).setVisible(true);
            return;
        }
        if (aa.q.equalsIgnoreCase(cB.r)) {
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
