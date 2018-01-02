// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Container;

public final class dY extends cY
{
    private p q;
    
    public dY(final Container container, final ap ap) {
        super(container, ap);
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (p)o;
        super.q(n, n2 + 20, (bJ)o);
    }
    
    public final void w() {
        this.q.clear();
        this.q.put("mi_bgcr", Integer.toString(bC.w.x.getRGB(), 16));
        this.q.put("mi_bgcrin", Integer.toString(bC.w.c.getRGB(), 16));
        this.q.put("mi_fgcrin", Integer.toString(bC.w.v.getRGB(), 16));
        int n = 1;
        this.q.put("mi_" + 1 + "_txt", dX.E);
        if ((this.w.q(61) || !this.q.q(61)) && !this.q.r) {
            ++n;
            this.q.put("mi_" + 2 + "_txt", dX.T);
        }
        if (this.w.q(61) || !this.q.q(61)) {
            ++n;
            this.q.put("mi_" + n + "_txt", dX.R);
        }
        if (this.w.q(61)) {
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if (this.w.o != null) {
            ++n;
            this.q.put("mi_" + n + "_txt", dX.S);
        }
        if (this.w.q(9)) {
            ++n;
            this.q.put("mi_" + n + "_txt", dX.D);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if ((this.w.q(44) || this.w.q(52)) && !this.q.a.equalsIgnoreCase("Chatmaster")) {
            ++n;
            this.q.put("mi_" + n + "_txt", dX.Y);
        }
        if (this.w.q(11)) {
            ++n;
            this.q.put("mi_" + n + "_txt", dX.F);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if (this.w.q(49) || this.w.q(39)) {
            if (this.w.q(49)) {
                ++n;
                this.q.put("mi_" + n + "_txt", dX.U);
            }
            ++n;
            this.q.put("mi_" + n + "_txt", dX.I);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if ((this.w.q(3) && this.q.s == this.w.s) || this.w.q(5)) {
            ++n;
            this.q.put("mi_" + n + "_txt", dX.O);
        }
        if (this.w.q(18) && !this.w.q(61)) {
            ++n;
            this.q.put("mi_" + n + "_txt", dX.P);
            ++n;
            this.q.put("mi_" + n + "_line", "");
        }
        if (this.w.q(6)) {
            ++n;
            this.q.put("mi_" + n + "_txt", dX.A);
        }
    }
    
    public final void q(final at at) {
        final p p = (p)this.q;
        if (at.q.equalsIgnoreCase(dX.E)) {
            this.q.y(p.s);
            return;
        }
        if (at.q.equalsIgnoreCase(dX.R)) {
            this.w.q.q(p);
            return;
        }
        if (at.q.equalsIgnoreCase(dX.T)) {
            this.q.q(p);
            return;
        }
        if (at.q.equalsIgnoreCase(dX.Y)) {
            new J(this.w, p);
            return;
        }
        if (at.q.equalsIgnoreCase(dX.D)) {
            this.q.w();
            return;
        }
        if (at.q.equalsIgnoreCase(dX.S)) {
            this.q.q();
            return;
        }
        if (at.q.equalsIgnoreCase(dX.F)) {
            this.q.t(p.a);
            return;
        }
        if (at.q.equalsIgnoreCase(dX.U)) {
            this.q.q(B.q(ds.q(")?EK81F5K255>K21>>54K2IKP\\"), this.w.a), false);
            this.q.q(p.u, Integer.MAX_VALUE);
            return;
        }
        if (at.q.equalsIgnoreCase(dX.I)) {
            this.q.q(B.q(ds.q(")?EK81F5K255>K21>>54K2IKP\\"), this.w.a), false);
            if (p.o == null || p.o.length() == 0 || p.o.equals("can't get")) {
                return;
            }
            this.q.w(p.o, Integer.MAX_VALUE);
        }
        else {
            if (at.q.equalsIgnoreCase(dX.O)) {
                this.q.w(((aJ)ap.x.q((int)(dH.x.q * Math.random()))).w());
                return;
            }
            if (at.q.equalsIgnoreCase(dX.P)) {
                this.q.e(((aJ)ap.x.q((int)(dH.x.q * Math.random()))).w());
                return;
            }
            if (!at.q.equalsIgnoreCase(dX.A)) {
                if (at.q.equalsIgnoreCase(dX.G)) {
                    this.w.o(B.q(-1, 0, this.w.s, "" + p.s));
                }
                return;
            }
            if (((p)this.q).e()) {
                this.q.w("disable");
                return;
            }
            this.q.w("enable");
        }
    }
}
