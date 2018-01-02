// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Container;

public final class cx extends bQ
{
    private l q;
    
    public cx(final Container container, final W w) {
        super(container, w);
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (l)o;
        super.q(n, n2 + 20, (aX)o);
    }
    
    public final void e() {
        this.q.clear();
        this.q.put("mi_bgcr", Integer.toString(aT.w.x.getRGB(), 16));
        this.q.put("mi_bgcrin", Integer.toString(aT.w.c.getRGB(), 16));
        this.q.put("mi_fgcrin", Integer.toString(aT.w.v.getRGB(), 16));
        int n = 1;
        this.q.put("mi_" + 1 + "_txt", cy.f);
        if ((this.w.q(61) || !this.q.q(61)) && !this.q.r) {
            ++n;
            this.q.put("mi_" + 2 + "_txt", cy.h);
        }
        if (this.w.q(61) || !this.q.q(61)) {
            ++n;
            this.q.put("mi_" + n + "_txt", cy.g);
        }
        if (this.w.q(61)) {
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if (this.w.i != null) {
            ++n;
            this.q.put("mi_" + n + "_txt", cy.v);
        }
        if (this.w.q(9)) {
            ++n;
            this.q.put("mi_" + n + "_txt", cy.b);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if ((this.w.q(44) || this.w.q(52)) && !this.q.o.equalsIgnoreCase("Chatmaster")) {
            ++n;
            this.q.put("mi_" + n + "_txt", cy.j);
        }
        if (this.w.q(11)) {
            ++n;
            this.q.put("mi_" + n + "_txt", cy.n);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if (this.w.q(49) || this.w.q(39)) {
            if (this.w.q(49)) {
                ++n;
                this.q.put("mi_" + n + "_txt", cy.k);
            }
            ++n;
            this.q.put("mi_" + n + "_txt", cy.l);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if ((this.w.q(3) && this.q.a == this.w.a) || this.w.q(5)) {
            ++n;
            this.q.put("mi_" + n + "_txt", cy.z);
        }
        if (this.w.q(18) && !this.w.q(61)) {
            ++n;
            this.q.put("mi_" + n + "_txt", cy.x);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if (this.w.q(6)) {
            ++n;
            this.q.put("mi_" + n + "_txt", cy.c);
        }
    }
    
    public final void w(final aa aa) {
        final l l = (l)this.q;
        if (aa.q.equalsIgnoreCase(cy.f)) {
            this.q.r(l.a);
            return;
        }
        if (aa.q.equalsIgnoreCase(cy.g)) {
            this.w.q.q(l);
            return;
        }
        if (aa.q.equalsIgnoreCase(cy.h)) {
            this.q.q(l);
            return;
        }
        if (aa.q.equalsIgnoreCase(cy.j)) {
            new z(this.w, l);
            return;
        }
        if (aa.q.equalsIgnoreCase(cy.b)) {
            this.q.w();
            return;
        }
        if (aa.q.equalsIgnoreCase(cy.v)) {
            this.q.q();
            return;
        }
        if (aa.q.equalsIgnoreCase(cy.n)) {
            this.q.e(l.o);
            return;
        }
        if (aa.q.equalsIgnoreCase(cy.k)) {
            this.q.q(t.q(ce.q(")?EK81F5K255>K21>>54K2IKP\\"), this.w.o), false);
            this.q.q(l.y, Integer.MAX_VALUE);
            return;
        }
        if (aa.q.equalsIgnoreCase(cy.l)) {
            this.q.q(t.q(ce.q(")?EK81F5K255>K21>>54K2IKP\\"), this.w.o), false);
            if (l.i == null || l.i.length() == 0 || l.i.equals("can't get")) {
                return;
            }
            this.q.w(l.i, Integer.MAX_VALUE);
        }
        else {
            if (aa.q.equalsIgnoreCase(cy.z)) {
                this.q.w(((ao)W.d.q((int)(co.d.q * Math.random()))).w());
                return;
            }
            if (aa.q.equalsIgnoreCase(cy.x)) {
                this.q.e(((ao)W.d.q((int)(co.d.q * Math.random()))).w());
                return;
            }
            if (!aa.q.equalsIgnoreCase(cy.c)) {
                if (aa.q.equalsIgnoreCase(cy.m)) {
                    this.w.r(t.q(-1, 0, this.w.a, "" + l.a));
                }
                return;
            }
            if (((l)this.q).q()) {
                this.q.w("disable");
                return;
            }
            this.q.w("enable");
        }
    }
}
