// 
// Decompiled by Procyon v0.5.30
// 

package a;

import com.spilka.client.muc.AppletAbstract;
import java.util.Hashtable;
import java.net.URL;
import java.awt.MenuItem;

public final class aU extends aN
{
    public aU(final cV cv) {
        super(cv);
    }
    
    protected final void t() {
        this.y();
        this.q.put("mi_1_txt", a.e);
        for (int i = 1; i <= this.q.s.q(); ++i) {
            this.q.put("mi_1_" + i + "_txt", ((MenuItem)this.q.s.w(i)).getLabel());
            this.q.put("mi_1_" + i + "_url", ((URL)this.q.d.w(i)).toString());
        }
        int n;
        if ((n = this.q.s.q() + 1) > 1) {
            this.q.put("mi_1_" + n + "_line", "");
            ++n;
        }
        this.q.put("mi_1_" + n + "_txt", aJ.q);
        ++n;
        this.q.put("mi_1_" + n + "_line", "");
        ++n;
        if ((this.q.q() & 0x3F2C00004000004L) != 0x0L) {
            this.q.put("mi_1_" + n + "_txt", aJ.o);
            ++n;
            this.q.put("mi_1_" + n + "_line", "");
            ++n;
        }
        this.q.put("mi_1_" + n + "_txt", aJ.e);
        for (int j = 1; j < 7; ++j) {
            this.q.put("mi_1_" + n + "_" + j + "_txt", cy.q()[j - 1]);
            this.q.put("mi_1_" + n + "_" + j + "_mark", "true");
        }
        ++n;
        this.q.put("mi_1_" + n + "_line", "");
        ++n;
        if (this.q.q(24) || this.q.q(32)) {
            int n2 = 1;
            this.q.put("mi_1_" + n + "_txt", aJ.t);
            if (this.q.q(32)) {
                this.q.put("mi_1_" + n + "_" + 1 + "_txt", aJ.y);
                final Hashtable q = this.q;
                final StringBuffer append = new StringBuffer().append("mi_1_").append(n).append("_");
                final int n3 = 1;
                ++n2;
                q.put(append.append(n3).append("_mark").toString(), "true");
            }
            if (this.q.q(24)) {
                this.q.put("mi_1_" + n + "_" + n2 + "_txt", aJ.u);
                this.q.put("mi_1_" + n + "_" + n2++ + "_mark", "true");
            }
            this.q.put("mi_1_" + n + "_" + n2 + "_txt", aJ.i);
            this.q.put("mi_1_" + n + "_" + n2 + "_mark", "true");
            ++n;
            this.q.put("mi_1_" + n + "_line", "");
            ++n;
        }
        if (this.q.q(80)) {
            this.q.put("mi_1_" + n + "_txt", aJ.c);
            ++n;
        }
        if (cf.w.r()) {
            if (this.q.q(15)) {
                this.q.put("mi_1_" + n + "_txt", aJ.n);
                ++n;
            }
            if (!cf.w.s()) {
                this.q.put("mi_1_" + n + "_txt", aJ.m);
                ++n;
            }
        }
        this.q.put("mi_1_" + n + "_line", "");
        ++n;
        final String s = "Admin";
        final cV q2 = this.q;
        if (s.equals(cV.a) && (this.q.q() & 0xC000000000000L) != 0x0L) {
            this.q.put("mi_1_" + n + "_txt", aJ.p);
            ++n;
        }
        if (this.q.q(42)) {
            this.q.put("mi_1_" + n + "_txt", aJ.a);
            ++n;
        }
        this.q.put("mi_1_" + n + "_txt", aJ.v);
        ++n;
        if (cf.w.r() && !cf.w.a()) {
            this.w(1, n);
            ++n;
        }
        if (this.q.q(52)) {
            this.q.put("mi_1_" + n + "_txt", aJ.d);
            ++n;
        }
        this.q.put("mi_1_" + n + "_txt", aJ.f);
        this.q.put("mi_1_" + n + "_mark", "true");
        ++n;
        if (this.q.q(52)) {
            this.q.put("mi_1_" + n + "_txt", aJ.g);
            ++n;
        }
        if (this.q.q(45)) {
            this.q.put("mi_1_" + n + "_txt", aJ.s);
            ++n;
        }
        if (this.q.q(13)) {
            this.q.put("mi_1_" + n + "_txt", aJ.h);
            ++n;
        }
        if (this.q.q(59) || this.q.q(60)) {
            this.q.put("mi_1_" + n + "_txt", this.q.q(59) ? aJ.j : aJ.k);
            ++n;
        }
        this.q.put("mi_1_" + n + "_line", "");
        ++n;
        this.q.put("mi_1_" + n + "_txt", aJ.w);
        ++n;
        this.q.put("mi_1_" + n + "_txt", aJ.r);
        ++n;
        if (!this.q.y) {
            this.q.put("mi_1_" + n + "_txt", aJ.l);
            ++n;
            this.q.put("mi_1_" + n + "_txt", aJ.z);
            ++n;
            this.q.put("mi_1_" + n + "_txt", aJ.x);
        }
        if (!cf.w.r()) {
            if (!cf.w.a()) {
                this.w(2, 0);
            }
            int n4 = 2;
            if (this.q.q(15)) {
                ++n4;
                this.q.put("mi_" + 3 + "_txt", aJ.n);
            }
            if (!cf.w.s()) {
                ++n4;
                this.q.put("mi_" + n4 + "_txt", aJ.m);
            }
        }
    }
    
    public final void q(final aX ax) {
        if (ax.q.equalsIgnoreCase(aJ.z)) {
            this.q.w();
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.x) || ax.q.equalsIgnoreCase(aJ.r)) {
            this.q.t();
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.p)) {
            ((dz)this.q).p();
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.o)) {
            ((dz)this.q).f();
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.a)) {
            ((dz)this.q).a();
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.s)) {
            ((dz)this.q).r(false);
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.d)) {
            ((dz)this.q).g();
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.f)) {
            this.q.g = !this.q.g;
            this.q.q(cy.q(this.q.s, this.q.d, this.q.f, this.q.g, this.q.a, this.q.s, 0));
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.l)) {
            new dz();
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.g)) {
            ((dz)this.q).s();
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.h)) {
            final b b;
            (b = new b(this.q, eb.q("Confirmation"), new String[] { eb.q("OK"), eb.q("Cancel") }, new String[] { eb.q("Restart site?") }, null, this.q)).setVisible(true);
            if (eb.q("OK").equals(b.q())) {
                ((dz)this.q).d();
            }
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.j) || ax.q.equalsIgnoreCase(aJ.k)) {
            if (((dz)this.q).q != null) {
                ((dz)this.q).q.setVisible(true);
            }
        }
        else {
            if (ax.q.equalsIgnoreCase(aJ.q)) {
                ea.q(new String[] { "window.focus(); setTimeout(\"window.external.AddFavorite('" + AppletAbstract.q().getCodeBase() + "', window.document.title);\", 100);" }, this.q);
                return;
            }
            if (ax.q.equalsIgnoreCase(aJ.w)) {
                this.q.a(0);
                return;
            }
            if (ax.q.equalsIgnoreCase(aJ.y)) {
                this.q.q(cy.q(this.q.s, this.q.d, this.q.f, this.q.g, this.q.a, 9, 0));
                return;
            }
            if (ax.q.equalsIgnoreCase(aJ.u)) {
                this.q.q(cy.q(this.q.s, this.q.d, this.q.f, this.q.g, this.q.a, 8, 0));
                return;
            }
            if (ax.q.equalsIgnoreCase(aJ.i)) {
                this.q.q(cy.q(this.q.s, this.q.d, this.q.f, this.q.g, this.q.a, 7, 0));
                return;
            }
            if (cy.q(ax.q)) {
                this.q.q(cy.q(this.q.s, this.q.d, this.q.f, this.q.g, cy.q(ax.q), this.q.s, 0));
                return;
            }
            if (ax.q != null) {
                this.q.q(ax.q, ax.w);
                return;
            }
            if (ax.q.equalsIgnoreCase(aJ.n)) {
                new al(this.q, (dz)this.q).setVisible(true);
                return;
            }
            if (ax.q.equalsIgnoreCase(aJ.c)) {
                this.q.q("", -1, 4);
                return;
            }
            if (ax.q.equalsIgnoreCase(aJ.v)) {
                new aD(this.q, this.q).setVisible(true);
                return;
            }
            if (ax.q.equalsIgnoreCase(aJ.m)) {
                new am(this.q, (dz)this.q).setVisible(true);
                return;
            }
            this.w(ax);
        }
    }
}
