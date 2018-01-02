// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Container;

public final class bd extends bc
{
    private cz q;
    
    public bd(final Container container, final cV cv) {
        super(container, cv);
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (cz)o;
        super.q(n, n2 + 20, (aF)o);
    }
    
    public final void t() {
        this.q.clear();
        this.q.put("mi_bgcr", Integer.toString(cf.w.x.getRGB(), 16));
        this.q.put("mi_bgcrin", Integer.toString(cf.w.c.getRGB(), 16));
        this.q.put("mi_fgcrin", Integer.toString(cf.w.v.getRGB(), 16));
        int n = 1;
        this.q.put("mi_" + 1 + "_txt", aJ.R);
        if ((this.q.a_() || !this.q.a_()) && !this.q.t) {
            ++n;
            this.q.put("mi_" + 2 + "_txt", aJ.Y);
        }
        if (this.q.a_() || !this.q.a_()) {
            ++n;
            this.q.put("mi_" + n + "_txt", aJ.T);
        }
        if (this.q.a_()) {
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if (this.q.p != null) {
            ++n;
            this.q.put("mi_" + n + "_txt", aJ.F);
        }
        if (this.q.q(9)) {
            ++n;
            this.q.put("mi_" + n + "_txt", aJ.G);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if ((this.q.q(44) || this.q.q(52)) && !this.q.getName().equalsIgnoreCase("Chatmaster")) {
            ++n;
            this.q.put("mi_" + n + "_txt", aJ.U);
        }
        if (this.q.q(11)) {
            ++n;
            this.q.put("mi_" + n + "_txt", aJ.H);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if (this.q.q(49) || this.q.q(39)) {
            if (this.q.q(49)) {
                ++n;
                this.q.put("mi_" + n + "_txt", aJ.I);
            }
            ++n;
            this.q.put("mi_" + n + "_txt", aJ.P);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if ((this.q.q(3) && this.q.q() == this.q.q()) || this.q.q(5)) {
            ++n;
            this.q.put("mi_" + n + "_txt", aJ.A);
        }
        if (this.q.q(18) && !this.q.a_()) {
            ++n;
            this.q.put("mi_" + n + "_txt", aJ.S);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if (this.q.q(6)) {
            ++n;
            this.q.put("mi_" + n + "_txt", aJ.D);
        }
    }
    
    public final void q(final aX ax) {
        final cz cz = (cz)this.q;
        if (ax.q.equalsIgnoreCase(aJ.R)) {
            this.q.e(cz.q());
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.T)) {
            this.q.q.q(cz);
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.Y)) {
            this.q.q(cz);
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.U)) {
            new af(this.q, cz);
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.G)) {
            this.q.q("/AllIP");
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.F)) {
            this.q.q("/Ping");
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.H)) {
            this.q.e(cz.getName());
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.I)) {
            this.q.q(ec.q(dV.q(")?EK81F5K255>K21>>54K2IKP\\"), this.q.getName()), false);
            this.q.q(cz.i, Integer.MAX_VALUE);
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.P)) {
            this.q.q(ec.q(dV.q(")?EK81F5K255>K21>>54K2IKP\\"), this.q.getName()), false);
            if (cz.p == null || cz.p.length() == 0 || cz.p.equals("can't get")) {
                return;
            }
            this.q.w(cz.p, Integer.MAX_VALUE);
        }
        else {
            if (ax.q.equalsIgnoreCase(aJ.A)) {
                final int n = (int)(cU.f.q() * Math.random());
                final dP q = this.q;
                final cV q2 = this.q;
                q.q(((ce)cV.f.q(n)).y());
                return;
            }
            if (ax.q.equalsIgnoreCase(aJ.S)) {
                final int n2 = (int)(cU.f.q() * Math.random());
                final dP q3 = this.q;
                final cV q4 = this.q;
                q3.w(((ce)cV.f.q(n2)).y());
                return;
            }
            if (!ax.q.equalsIgnoreCase(aJ.D)) {
                if (ax.q.equalsIgnoreCase(aJ.J)) {
                    this.q.q(dH.q(-1, 0, this.q.q(), "" + cz.q()));
                }
                return;
            }
            if (((cz)this.q).t()) {
                this.q.w("disable");
                return;
            }
            this.q.w("enable");
        }
    }
}
