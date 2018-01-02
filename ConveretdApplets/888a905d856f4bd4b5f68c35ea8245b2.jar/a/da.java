// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Hashtable;
import java.net.URL;
import java.awt.MenuItem;

public final class da extends cg
{
    public da(final ap ap) {
        super(ap);
    }
    
    protected final void w() {
        this.e();
        this.q.put("mi_1_txt", dN.e);
        for (int i = 1; i <= this.q.l.q; ++i) {
            this.q.put("mi_1_" + i + "_txt", ((MenuItem)this.q.l.w(i)).getLabel());
            this.q.put("mi_1_" + i + "_url", ((URL)this.q.z.w(i)).toString());
        }
        int n;
        if ((n = this.q.l.q + 1) > 1) {
            this.q.put("mi_1_" + n + "_line", "");
            ++n;
        }
        this.q.put("mi_1_" + n + "_txt", dX.q);
        ++n;
        this.q.put("mi_1_" + n + "_line", "");
        ++n;
        if ((this.q.w() & 0x3F2C00004000004L) != 0x0L) {
            this.q.put("mi_1_" + n + "_txt", dX.o);
            ++n;
            this.q.put("mi_1_" + n + "_line", "");
            ++n;
        }
        this.q.put("mi_1_" + n + "_txt", dX.e);
        for (int j = 1; j < 7; ++j) {
            this.q.put("mi_1_" + n + "_" + j + "_txt", cS.q()[j - 1]);
            this.q.put("mi_1_" + n + "_" + j + "_mark", "true");
        }
        ++n;
        this.q.put("mi_1_" + n + "_line", "");
        ++n;
        if (this.q.q(24) || this.q.q(32)) {
            int n2 = 1;
            this.q.put("mi_1_" + n + "_txt", dX.t);
            if (this.q.q(32)) {
                this.q.put("mi_1_" + n + "_" + 1 + "_txt", dX.y);
                final Hashtable q = this.q;
                final StringBuffer append = new StringBuffer().append("mi_1_").append(n).append("_");
                final int n3 = 1;
                ++n2;
                q.put(append.append(n3).append("_mark").toString(), "true");
            }
            if (this.q.q(24)) {
                this.q.put("mi_1_" + n + "_" + n2 + "_txt", dX.u);
                this.q.put("mi_1_" + n + "_" + n2++ + "_mark", "true");
            }
            this.q.put("mi_1_" + n + "_" + n2 + "_txt", dX.i);
            this.q.put("mi_1_" + n + "_" + n2 + "_mark", "true");
            ++n;
            this.q.put("mi_1_" + n + "_line", "");
            ++n;
        }
        if (!bC.w.p()) {
            this.q.put("mi_1_" + n + "_txt", dX.c);
            ++n;
        }
        if (bC.w.r()) {
            if (this.q.q(15)) {
                this.q.put("mi_1_" + n + "_txt", dX.n);
                ++n;
            }
            if (!bC.w.f()) {
                this.q.put("mi_1_" + n + "_txt", dX.m);
                ++n;
            }
        }
        this.q.put("mi_1_" + n + "_line", "");
        ++n;
        if ("Admin".equals(ap.f) && (this.q.w() & 0xC000000000000L) != 0x0L) {
            this.q.put("mi_1_" + n + "_txt", dX.p);
            ++n;
        }
        if (this.q.q(42)) {
            this.q.put("mi_1_" + n + "_txt", dX.a);
            ++n;
        }
        this.q.put("mi_1_" + n + "_txt", dX.v);
        ++n;
        if (bC.w.r() && !bC.w.d()) {
            this.w(1, n);
            ++n;
        }
        if (this.q.q(52)) {
            this.q.put("mi_1_" + n + "_txt", dX.d);
            ++n;
        }
        this.q.put("mi_1_" + n + "_txt", dX.f);
        this.q.put("mi_1_" + n + "_mark", "true");
        ++n;
        if (this.q.q(52)) {
            this.q.put("mi_1_" + n + "_txt", dX.g);
            ++n;
        }
        if (this.q.q(45)) {
            this.q.put("mi_1_" + n + "_txt", dX.s);
            ++n;
        }
        if (this.q.q(13)) {
            this.q.put("mi_1_" + n + "_txt", dX.h);
            ++n;
        }
        if (this.q.q(59) || this.q.q(60)) {
            this.q.put("mi_1_" + n + "_txt", this.q.q(59) ? dX.j : dX.k);
            ++n;
        }
        this.q.put("mi_1_" + n + "_line", "");
        ++n;
        this.q.put("mi_1_" + n + "_txt", dX.w);
        ++n;
        this.q.put("mi_1_" + n + "_txt", dX.r);
        ++n;
        if (!this.q.y) {
            this.q.put("mi_1_" + n + "_txt", dX.l);
            ++n;
            this.q.put("mi_1_" + n + "_txt", dX.z);
            ++n;
            this.q.put("mi_1_" + n + "_txt", dX.x);
        }
        if (!bC.w.r()) {
            if (!bC.w.d()) {
                this.w(2, 0);
            }
            int n4 = 2;
            if (this.q.q(15)) {
                ++n4;
                this.q.put("mi_" + 3 + "_txt", dX.n);
            }
            if (!bC.w.f()) {
                ++n4;
                this.q.put("mi_" + n4 + "_txt", dX.m);
            }
        }
    }
    
    public final void q(final at at) {
        if (at.q.equalsIgnoreCase(dX.z)) {
            this.q.u();
            return;
        }
        if (at.q.equalsIgnoreCase(dX.x) || at.q.equalsIgnoreCase(dX.r)) {
            this.q.g();
            return;
        }
        if (at.q.equalsIgnoreCase(dX.p)) {
            ((cT)this.q).o();
            return;
        }
        if (at.q.equalsIgnoreCase(dX.o)) {
            ((cT)this.q).d();
            return;
        }
        if (at.q.equalsIgnoreCase(dX.a)) {
            ((cT)this.q).p();
            return;
        }
        if (at.q.equalsIgnoreCase(dX.s)) {
            ((cT)this.q).w(false);
            return;
        }
        if (at.q.equalsIgnoreCase(dX.d)) {
            ((cT)this.q).f();
            return;
        }
        if (at.q.equalsIgnoreCase(dX.f)) {
            this.q.g = !this.q.g;
            this.q.o(cS.q(this.q.s, this.q.d, this.q.f, this.q.g, this.q.y, this.q.u, 0));
            return;
        }
        if (at.q.equalsIgnoreCase(dX.l)) {
            new cT();
            return;
        }
        if (at.q.equalsIgnoreCase(dX.g)) {
            ((cT)this.q).a();
            return;
        }
        if (at.q.equalsIgnoreCase(dX.h)) {
            final dd dd;
            (dd = new dd(this.q, be.w("Confirmation"), new String[] { be.w("OK"), be.w("Cancel") }, new String[] { be.w("Restart site?") }, null, this.q)).setVisible(true);
            if (be.w("OK").equals(dd.q)) {
                ((cT)this.q).s();
            }
            return;
        }
        if (at.q.equalsIgnoreCase(dX.j) || at.q.equalsIgnoreCase(dX.k)) {
            if (((cT)this.q).q != null) {
                ((cT)this.q).q.setVisible(true);
            }
        }
        else {
            if (at.q.equalsIgnoreCase(dX.q)) {
                cm.q(new String[] { "window.focus(); setTimeout(\"window.external.AddFavorite('" + m.q().getCodeBase() + "', window.document.title);\", 100);" }, this.q);
                return;
            }
            if (at.q.equalsIgnoreCase(dX.w)) {
                this.q.r(0);
                return;
            }
            if (at.q.equalsIgnoreCase(dX.y)) {
                this.q.o(cS.q(this.q.s, this.q.d, this.q.f, this.q.g, this.q.y, 9, 0));
                return;
            }
            if (at.q.equalsIgnoreCase(dX.u)) {
                this.q.o(cS.q(this.q.s, this.q.d, this.q.f, this.q.g, this.q.y, 8, 0));
                return;
            }
            if (at.q.equalsIgnoreCase(dX.i)) {
                this.q.o(cS.q(this.q.s, this.q.d, this.q.f, this.q.g, this.q.y, 7, 0));
                return;
            }
            if (cS.q(at.q)) {
                this.q.o(cS.q(this.q.s, this.q.d, this.q.f, this.q.g, cS.q(at.q), this.q.u, 0));
                return;
            }
            if (at.q != null) {
                this.q.q(at.q, at.w);
                return;
            }
            if (at.q.equalsIgnoreCase(dX.n)) {
                new dU(this.q, (cT)this.q).setVisible(true);
                return;
            }
            if (at.q.equalsIgnoreCase(dX.c)) {
                this.q.q("", -1, 4);
                return;
            }
            if (at.q.equalsIgnoreCase(dX.v)) {
                new aM(this.q, this.q).setVisible(true);
                return;
            }
            if (at.q.equalsIgnoreCase(dX.m)) {
                new z(this.q, (cT)this.q).setVisible(true);
                return;
            }
            this.w(at);
        }
    }
}
