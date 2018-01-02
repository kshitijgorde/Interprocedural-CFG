// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Container;

public final class aN extends aM
{
    private bp q;
    
    public aN(final Container container, final bI bi) {
        super(container, bi);
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (bp)o;
        super.q(n, n2 + 20, (aq)o);
    }
    
    public final void t() {
        this.q.clear();
        this.q.put("mi_bgcr", Integer.toString(be.w.x.getRGB(), 16));
        this.q.put("mi_bgcrin", Integer.toString(be.w.c.getRGB(), 16));
        this.q.put("mi_fgcrin", Integer.toString(be.w.v.getRGB(), 16));
        int n = 1;
        this.q.put("mi_" + 1 + "_txt", au.f);
        if ((this.q.a_() || !this.q.a_()) && !this.q.r) {
            ++n;
            this.q.put("mi_" + 2 + "_txt", au.h);
        }
        if (this.q.a_() || !this.q.a_()) {
            ++n;
            this.q.put("mi_" + n + "_txt", au.g);
        }
        if (this.q.a_()) {
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if (this.q.o != null) {
            ++n;
            this.q.put("mi_" + n + "_txt", au.v);
        }
        if (this.q.q(9)) {
            ++n;
            this.q.put("mi_" + n + "_txt", au.b);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if ((this.q.q(44) || this.q.q(52)) && !this.q.getName().equalsIgnoreCase("Chatmaster")) {
            ++n;
            this.q.put("mi_" + n + "_txt", au.j);
        }
        if (this.q.q(11)) {
            ++n;
            this.q.put("mi_" + n + "_txt", au.n);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if (this.q.q(49) || this.q.q(39)) {
            if (this.q.q(49)) {
                ++n;
                this.q.put("mi_" + n + "_txt", au.k);
            }
            ++n;
            this.q.put("mi_" + n + "_txt", au.l);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if ((this.q.q(3) && this.q.q() == this.q.q()) || this.q.q(5)) {
            ++n;
            this.q.put("mi_" + n + "_txt", au.z);
        }
        if (this.q.q(18) && !this.q.a_()) {
            ++n;
            this.q.put("mi_" + n + "_txt", au.x);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if (this.q.q(6)) {
            ++n;
            this.q.put("mi_" + n + "_txt", au.c);
        }
    }
    
    public final void q(final aI ai) {
        final bp bp = (bp)this.q;
        if (ai.q.equalsIgnoreCase(au.f)) {
            this.q.e(bp.q());
            return;
        }
        if (ai.q.equalsIgnoreCase(au.g)) {
            this.q.q.q(bp);
            return;
        }
        if (ai.q.equalsIgnoreCase(au.h)) {
            this.q.q(bp);
            return;
        }
        if (ai.q.equalsIgnoreCase(au.j)) {
            new U(this.q, bp);
            return;
        }
        if (ai.q.equalsIgnoreCase(au.b)) {
            this.q.q("/AllIP");
            return;
        }
        if (ai.q.equalsIgnoreCase(au.v)) {
            this.q.q("/Ping");
            return;
        }
        if (ai.q.equalsIgnoreCase(au.n)) {
            this.q.e(bp.getName());
            return;
        }
        if (ai.q.equalsIgnoreCase(au.k)) {
            this.q.q(cv.q(cl.q(")?EK81F5K255>K21>>54K2IKP\\"), this.q.getName()), false);
            this.q.q(bp.u, Integer.MAX_VALUE);
            return;
        }
        if (ai.q.equalsIgnoreCase(au.l)) {
            this.q.q(cv.q(cl.q(")?EK81F5K255>K21>>54K2IKP\\"), this.q.getName()), false);
            if (bp.o == null || bp.o.length() == 0 || bp.o.equals("can't get")) {
                return;
            }
            this.q.w(bp.o, Integer.MAX_VALUE);
        }
        else {
            if (ai.q.equalsIgnoreCase(au.z)) {
                final int n = (int)(bH.d.q() * Math.random());
                final cj q = this.q;
                final bI q2 = this.q;
                q.q(((bd)bI.d.q(n)).r());
                return;
            }
            if (ai.q.equalsIgnoreCase(au.x)) {
                final int n2 = (int)(bH.d.q() * Math.random());
                final cj q3 = this.q;
                final bI q4 = this.q;
                q3.w(((bd)bI.d.q(n2)).r());
                return;
            }
            if (!ai.q.equalsIgnoreCase(au.c)) {
                if (ai.q.equalsIgnoreCase(au.m)) {
                    this.q.q(cb.q(-1, 0, this.q.q(), "" + bp.q()));
                }
                return;
            }
            if (((bp)this.q).e()) {
                this.q.w("disable");
                return;
            }
            this.q.w("enable");
        }
    }
}
