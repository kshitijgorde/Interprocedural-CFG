// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.net.URL;
import java.awt.MenuItem;

public final class cd extends bh
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
        this.q.put("mi_1_" + n + "_txt", cz.q);
        ++n;
        this.q.put("mi_1_" + n + "_line", "");
        ++n;
        this.q.put("mi_1_" + n + "_txt", cz.w);
        ++n;
        this.q.put("mi_1_" + n + "_line", "");
        ++n;
        this.q.put("mi_1_" + n + "_txt", cz.e);
        for (int j = 1; j < 7; ++j) {
            this.q.put("mi_1_" + n + "_" + j + "_txt", bK.q()[j - 1]);
            this.q.put("mi_1_" + n + "_" + j + "_mark", "true");
        }
        ++n;
        this.q.put("mi_1_" + n + "_line", "");
        if (!aS.w.p()) {
            ++n;
            this.q.put("mi_1_" + n + "_txt", cz.u);
            ++n;
            this.q.put("mi_1_" + n + "_line", "");
        }
        ++n;
        if (!aS.w.d()) {
            this.q.put("mi_1_" + n + "_txt", cz.i);
            ++n;
        }
        this.q.put("mi_1_" + n + "_line", "");
        ++n;
        if (aS.w.r() && cs.u && !aS.w.s()) {
            this.w(1, n);
            ++n;
            this.q.put("mi_1_" + n + "_line", "");
        }
        if (!this.q.y) {
            ++n;
            this.q.put("mi_1_" + n + "_txt", cz.r);
        }
        if (!aS.w.r() && cs.u && !aS.w.s()) {
            this.w(2, 0);
        }
    }
    
    public final void w(final aa aa) {
        if (aa.q.equalsIgnoreCase(cz.w)) {
            this.q.r(0);
            return;
        }
        if (aa.q.equalsIgnoreCase(cz.q)) {
            bh.q(this.q);
            return;
        }
        if (bK.q(aa.q)) {
            this.q.r(bK.q(this.q.a, this.q.s, this.q.d, this.q.f, bK.q(aa.q), this.q.u, 0));
            return;
        }
        if (aa.q.equalsIgnoreCase(cz.u)) {
            this.q.q("", -1, 4);
            return;
        }
        if (aa.q.equalsIgnoreCase(cz.i)) {
            new ao(this.q, this.q).setVisible(true);
            return;
        }
        if (aa.q.equalsIgnoreCase(cz.r)) {
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
