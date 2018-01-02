// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Container;

public final class aT extends cG
{
    private bk q;
    private cT q;
    
    public aT(final Container container, final cT q) {
        super(container, q);
        this.q = q;
    }
    
    public final void w() {
        super.w();
        this.q.put("mi_3_line", "");
        this.q.put("mi_4_txt", dX.J);
        this.q.put("mi_5_txt", dX.K);
        this.q.put("mi_6_txt", dX.L);
        this.q.put("mi_7_txt", dX.Z);
        this.q.put("mi_8_line", "");
        this.q.put("mi_9_txt", dX.H);
    }
    
    public final void q(final at at) {
        this.q = ((z)this.q).q;
        final int q = this.q.q;
        final String e = this.q.e;
        if (at.q.equalsIgnoreCase(dX.J)) {
            q(this.q, 4, this.q.f, this.q.w, e);
            this.q.q(q);
            this.q.q.w(this.q.f);
            return;
        }
        if (at.q.equalsIgnoreCase(dX.K)) {
            q(this.q, 5, this.q.f, this.q.w, e);
            q(this.q, this.q, this.q.w);
            return;
        }
        if (at.q.equalsIgnoreCase(dX.L)) {
            q(this.q, 6, this.q.f, this.q.w, e);
            w(this.q, this.q, this.q.e);
            return;
        }
        if (at.q.equalsIgnoreCase(dX.Z)) {
            q(this.q, 7, this.q.f, this.q.w, e);
            q(this.q, this.q);
            return;
        }
        if (at.q.equalsIgnoreCase(dX.H)) {
            q(this.q, 8, this.q.f, this.q.w, e);
            q(this.q, this.q, this.q.w);
            return;
        }
        super.q(at);
    }
    
    public final void q(final int n, final int n2, final Object o) {
        super.q(n + 25, n2 + 50, o);
    }
    
    public static void q(final ap ap, final int n, final int n2, final String s, final String s2) {
        final dI di;
        (di = new dI(4202528, 1)).q = -1;
        di.w = -1;
        di.q(0, 0, n);
        di.q(0, 1, n2);
        di.q(0, 0, s);
        di.q(0, 2, s2);
        ap.o(di);
    }
    
    public static void q(final cT ct, final bk bk, final String s) {
        for (int i = 0; i < bk.w(); ++i) {
            final A q;
            if ((q = bk.q(i)).w.equals(s)) {
                bk.q(i);
                ct.q.w(q.f);
                --i;
            }
        }
    }
    
    public static void w(final cT ct, final bk bk, final String s) {
        for (int i = 0; i < bk.w(); ++i) {
            final A q = bk.q(i);
            if (s.equals(q.e)) {
                bk.q(i);
                ct.q.w(q.f);
                --i;
            }
        }
    }
    
    public static void q(final cT ct, final bk bk) {
        for (int i = 0; i < bk.w(); ++i) {
            final A q = bk.q(i);
            bk.q(i);
            ct.q.w(q.f);
        }
    }
}
