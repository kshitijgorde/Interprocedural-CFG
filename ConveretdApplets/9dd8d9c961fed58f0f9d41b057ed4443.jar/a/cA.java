// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Container;

public final class cA extends bR
{
    private l q;
    
    public cA(final Container container, final W w) {
        super(container, w);
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (l)o;
        super.q(n, n2 + 20, (aY)o);
    }
    
    public final void e() {
        this.q.clear();
        this.q.put("mi_bgcr", Integer.toString(aU.w.x.getRGB(), 16));
        this.q.put("mi_bgcrin", Integer.toString(aU.w.c.getRGB(), 16));
        this.q.put("mi_fgcrin", Integer.toString(aU.w.v.getRGB(), 16));
        int n = 1;
        this.q.put("mi_" + 1 + "_txt", cB.f);
        if ((this.w.q(61) || !this.q.q(61)) && !this.q.r) {
            ++n;
            this.q.put("mi_" + 2 + "_txt", cB.h);
        }
        if (this.w.q(61) || !this.q.q(61)) {
            ++n;
            this.q.put("mi_" + n + "_txt", cB.g);
        }
        if (this.w.q(61)) {
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if (this.w.i != null) {
            ++n;
            this.q.put("mi_" + n + "_txt", cB.v);
        }
        if (this.w.q(9)) {
            ++n;
            this.q.put("mi_" + n + "_txt", cB.b);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if ((this.w.q(44) || this.w.q(52)) && !this.q.o.equalsIgnoreCase("Chatmaster")) {
            ++n;
            this.q.put("mi_" + n + "_txt", cB.j);
        }
        if (this.w.q(11)) {
            ++n;
            this.q.put("mi_" + n + "_txt", cB.n);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if (this.w.q(49) || this.w.q(39)) {
            if (this.w.q(49)) {
                ++n;
                this.q.put("mi_" + n + "_txt", cB.k);
            }
            ++n;
            this.q.put("mi_" + n + "_txt", cB.l);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if ((this.w.q(3) && this.q.s == this.w.s) || this.w.q(5)) {
            ++n;
            this.q.put("mi_" + n + "_txt", cB.z);
        }
        if (this.w.q(18) && !this.w.q(61)) {
            ++n;
            this.q.put("mi_" + n + "_txt", cB.x);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if (this.w.q(6)) {
            ++n;
            this.q.put("mi_" + n + "_txt", cB.c);
        }
    }
    
    public final void w(final aa aa) {
        final l l = (l)this.q;
        if (aa.q.equalsIgnoreCase(cB.f)) {
            this.q.r(l.s);
            return;
        }
        if (aa.q.equalsIgnoreCase(cB.g)) {
            this.w.q.q(l);
            return;
        }
        if (aa.q.equalsIgnoreCase(cB.h)) {
            this.q.q(l);
            return;
        }
        if (aa.q.equalsIgnoreCase(cB.j)) {
            new y(this.w, l);
            return;
        }
        if (aa.q.equalsIgnoreCase(cB.b)) {
            this.q.w();
            return;
        }
        if (aa.q.equalsIgnoreCase(cB.v)) {
            this.q.q();
            return;
        }
        if (aa.q.equalsIgnoreCase(cB.n)) {
            this.q.e(l.o);
            return;
        }
        if (aa.q.equalsIgnoreCase(cB.k)) {
            this.q.q(s.q(cg.q(")?EK81F5K255>K21>>54K2IKP\\"), this.w.o), false);
            this.q.q(l.y, Integer.MAX_VALUE);
            return;
        }
        if (aa.q.equalsIgnoreCase(cB.l)) {
            this.q.q(s.q(cg.q(")?EK81F5K255>K21>>54K2IKP\\"), this.w.o), false);
            if (l.i == null || l.i.length() == 0 || l.i.equals("can't get")) {
                return;
            }
            this.q.w(l.i, Integer.MAX_VALUE);
        }
        else {
            if (aa.q.equalsIgnoreCase(cB.z)) {
                this.q.w(((an)W.d.q((int)(cq.d.q * Math.random()))).w());
                return;
            }
            if (aa.q.equalsIgnoreCase(cB.x)) {
                this.q.e(((an)W.d.q((int)(cq.d.q * Math.random()))).w());
                return;
            }
            if (!aa.q.equalsIgnoreCase(cB.c)) {
                if (aa.q.equalsIgnoreCase(cB.m)) {
                    this.w.r(s.q(-1, 0, this.w.s, "" + l.s));
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
