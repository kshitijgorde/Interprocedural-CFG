// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Container;

public final class be extends aZ
{
    private cP q;
    private dz q;
    
    public be(final Container container, final dz q) {
        super(container, q);
        this.q = q;
    }
    
    public final void t() {
        super.t();
        this.q.put("mi_3_line", "");
        this.q.put("mi_4_txt", aJ.L);
        this.q.put("mi_5_txt", aJ.Z);
        this.q.put("mi_6_txt", aJ.X);
        this.q.put("mi_7_txt", aJ.C);
        this.q.put("mi_8_line", "");
        this.q.put("mi_9_txt", aJ.K);
    }
    
    public final void q(final aX ax) {
        this.q = ((am)this.q).q;
        final int q = this.q.q;
        final String e = this.q.e;
        if (ax.q.equalsIgnoreCase(aJ.L)) {
            q(this.w, 4, this.q.f, this.q.w, e);
            this.q.q(q);
            this.q.c.w(this.q.f);
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.Z)) {
            q(this.w, 5, this.q.f, this.q.w, e);
            q(this.q, this.q, this.q.w);
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.X)) {
            q(this.w, 6, this.q.f, this.q.w, e);
            w(this.q, this.q, this.q.e);
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.C)) {
            q(this.w, 7, this.q.f, this.q.w, e);
            q(this.q, this.q);
            return;
        }
        if (ax.q.equalsIgnoreCase(aJ.K)) {
            q(this.w, 8, this.q.f, this.q.w, e);
            q(this.q, this.q, this.q.w);
            return;
        }
        super.q(ax);
    }
    
    public final void q(final int n, final int n2, final Object o) {
        super.q(n + 25, n2 + 50, o);
    }
    
    public static void q(final cV cv, final int n, final int n2, final String s, final String s2) {
        final es es;
        (es = new es(4202528, 1)).q = -1;
        es.w = -1;
        es.q(0, 0, n);
        es.q(0, 1, n2);
        es.q(0, 0, s);
        es.q(0, 2, s2);
        cv.q(es);
    }
    
    public static void q(final dz dz, final cP cp, final String s) {
        for (int i = 0; i < cp.w(); ++i) {
            final cS q;
            if ((q = cp.q(i)).w.equals(s)) {
                cp.q(i);
                dz.c.w(q.f);
                --i;
            }
        }
    }
    
    public static String q(cS cs) {
        return (cs = cs).e;
    }
    
    public static void w(final dz dz, final cP cp, final String s) {
        for (int i = 0; i < cp.w(); ++i) {
            final cS q = cp.q(i);
            if (s.equals(q.e)) {
                cp.q(i);
                dz.c.w(q.f);
                --i;
            }
        }
    }
    
    public static void q(final dz dz, final cP cp) {
        for (int i = 0; i < cp.w(); ++i) {
            final cS q = cp.q(i);
            cp.q(i);
            dz.c.w(q.f);
        }
    }
}
