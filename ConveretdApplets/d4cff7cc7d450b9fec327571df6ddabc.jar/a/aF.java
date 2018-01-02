// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.net.URL;
import java.awt.MenuItem;

public final class aF extends ay
{
    public aF(final bI bi) {
        super(bi);
    }
    
    protected final void t() {
        this.y();
        this.q.put("mi_1_txt", a.e);
        this.q.put("mi_1_I", "");
        for (int i = 1; i <= this.q.a.q(); ++i) {
            this.q.put("mi_1_" + i + "_txt", ((MenuItem)this.q.a.w(i)).getLabel());
            this.q.put("mi_1_" + i + "_url", ((URL)this.q.s.w(i)).toString());
        }
        int n = this.q.a.q() + 1;
        this.q.put("mi_1_" + n + "_txt", au.q);
        ++n;
        this.q.put("mi_1_" + n + "_line", "");
        ++n;
        this.q.put("mi_1_" + n + "_txt", au.w);
        ++n;
        this.q.put("mi_1_" + n + "_line", "");
        ++n;
        this.q.put("mi_1_" + n + "_txt", au.e);
        for (int j = 1; j < 7; ++j) {
            this.q.put("mi_1_" + n + "_" + j + "_txt", bo.q()[j - 1]);
            this.q.put("mi_1_" + n + "_" + j + "_mark", "true");
        }
        ++n;
        this.q.put("mi_1_" + n + "_line", "");
        if (this.q.q(80)) {
            ++n;
            this.q.put("mi_1_" + n + "_txt", au.u);
            ++n;
            this.q.put("mi_1_" + n + "_line", "");
        }
        ++n;
        if (!be.w.s()) {
            this.q.put("mi_1_" + n + "_txt", au.i);
            ++n;
        }
        this.q.put("mi_1_" + n + "_line", "");
        ++n;
        if (be.w.r() && !be.w.a()) {
            this.w(1, n);
            ++n;
            this.q.put("mi_1_" + n + "_line", "");
        }
        if (!this.q.y) {
            ++n;
            this.q.put("mi_1_" + n + "_txt", au.r);
        }
        if (!be.w.r() && !be.w.a()) {
            this.w(2, 0);
        }
    }
    
    public final void q(final aI ai) {
        if (ai.q.equalsIgnoreCase(au.w)) {
            this.q.o(0);
            return;
        }
        if (ai.q.equalsIgnoreCase(au.q)) {
            ay.q(this.q);
            return;
        }
        if (bo.q(ai.q)) {
            this.q.q(bo.q(this.q.a, this.q.s, this.q.d, this.q.f, bo.q(ai.q), this.q.a, 0));
            return;
        }
        if (ai.q.equalsIgnoreCase(au.u)) {
            this.q.q("", -1, 4);
            return;
        }
        if (ai.q.equalsIgnoreCase(au.i)) {
            new ao(this.q, this.q).setVisible(true);
            return;
        }
        if (ai.q.equalsIgnoreCase(au.r)) {
            this.q.w();
            return;
        }
        if (ai.q != null) {
            this.q.q(ai.q, ai.w);
            return;
        }
        this.w(ai);
    }
}
