// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Container;

public final class cy extends bP
{
    private l q;
    
    public cy(final Container container, final W w) {
        super(container, w);
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (l)o;
        super.q(n, n2 + 20, (aW)o);
    }
    
    public final void e() {
        this.q.clear();
        this.q.put("mi_bgcr", Integer.toString(aS.w.x.getRGB(), 16));
        this.q.put("mi_bgcrin", Integer.toString(aS.w.c.getRGB(), 16));
        this.q.put("mi_fgcrin", Integer.toString(aS.w.v.getRGB(), 16));
        int n = 1;
        this.q.put("mi_" + 1 + "_txt", cz.f);
        if ((this.w.q(61) || !this.q.q(61)) && !this.q.r) {
            ++n;
            this.q.put("mi_" + 2 + "_txt", cz.h);
        }
        if (this.w.q(61) || !this.q.q(61)) {
            ++n;
            this.q.put("mi_" + n + "_txt", cz.g);
        }
        if (this.w.q(61)) {
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if (this.w.i != null) {
            ++n;
            this.q.put("mi_" + n + "_txt", cz.v);
        }
        if (this.w.q(9)) {
            ++n;
            this.q.put("mi_" + n + "_txt", cz.b);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if ((this.w.q(44) || this.w.q(52)) && !this.q.o.equalsIgnoreCase("Chatmaster")) {
            ++n;
            this.q.put("mi_" + n + "_txt", cz.j);
        }
        if (this.w.q(11)) {
            ++n;
            this.q.put("mi_" + n + "_txt", cz.n);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if (this.w.q(49) || this.w.q(39)) {
            if (this.w.q(49)) {
                ++n;
                this.q.put("mi_" + n + "_txt", cz.k);
            }
            ++n;
            this.q.put("mi_" + n + "_txt", cz.l);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if ((this.w.q(3) && this.q.s == this.w.s) || this.w.q(5)) {
            ++n;
            this.q.put("mi_" + n + "_txt", cz.z);
        }
        if (this.w.q(18) && !this.w.q(61)) {
            ++n;
            this.q.put("mi_" + n + "_txt", cz.x);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if (this.w.q(6)) {
            ++n;
            this.q.put("mi_" + n + "_txt", cz.c);
        }
    }
    
    public final void w(final aa aa) {
        final l l = (l)this.q;
        if (aa.q.equalsIgnoreCase(cz.f)) {
            this.q.r(l.s);
            return;
        }
        if (aa.q.equalsIgnoreCase(cz.g)) {
            this.w.q.q(l);
            return;
        }
        if (aa.q.equalsIgnoreCase(cz.h)) {
            this.q.q(l);
            return;
        }
        if (aa.q.equalsIgnoreCase(cz.j)) {
            new y(this.w, l);
            return;
        }
        if (aa.q.equalsIgnoreCase(cz.b)) {
            this.q.w();
            return;
        }
        if (aa.q.equalsIgnoreCase(cz.v)) {
            this.q.q();
            return;
        }
        if (aa.q.equalsIgnoreCase(cz.n)) {
            this.q.e(l.o);
            return;
        }
        if (aa.q.equalsIgnoreCase(cz.k)) {
            this.q.q(s.q(ce.q(")?EK81F5K255>K21>>54K2IKP\\"), this.w.o), false);
            this.q.q(l.y, Integer.MAX_VALUE);
            return;
        }
        if (aa.q.equalsIgnoreCase(cz.l)) {
            this.q.q(s.q(ce.q(")?EK81F5K255>K21>>54K2IKP\\"), this.w.o), false);
            if (l.i == null || l.i.length() == 0 || l.i.equals("can't get")) {
                return;
            }
            this.q.w(l.i, Integer.MAX_VALUE);
        }
        else {
            if (aa.q.equalsIgnoreCase(cz.z)) {
                this.q.w(((an)W.d.q((int)(co.d.q * Math.random()))).w());
                return;
            }
            if (aa.q.equalsIgnoreCase(cz.x)) {
                this.q.e(((an)W.d.q((int)(co.d.q * Math.random()))).w());
                return;
            }
            if (!aa.q.equalsIgnoreCase(cz.c)) {
                if (aa.q.equalsIgnoreCase(cz.m)) {
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
