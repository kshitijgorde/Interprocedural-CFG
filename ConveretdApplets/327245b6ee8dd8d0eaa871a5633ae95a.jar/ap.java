// 
// Decompiled by Procyon v0.5.30
// 

public class ap extends ad
{
    int a;
    
    ap(final int a) {
        super((a == 1) ? 1 : 3);
        super.a = 14;
        super.g = true;
        this.a = a;
        super.i = new ai[2];
    }
    
    int a(final x x) {
        super.d = x.e();
        super.c = x.k();
        super.h = x.a;
        super.i = x.b;
        return super.d;
    }
    
    void a(final z z) {
        final w w = new w();
        w.a(super.h, super.i);
        final ac a = ac.a(w.l(), z.j);
        final ac ac = new ac();
        ao ao = null;
        final int d = w.d();
        final int d2 = w.d();
        int n;
    Label_0232_Outer:
        while ((n = (w.a[w.b++] & 0xFF)) != 0) {
            if ((n & 0x8) != 0x0) {
                ao = (ao)super.b.a(w.e());
            }
            if ((n & 0x4) != 0x0) {
                final ai ai = new ai(w.c(super.a));
                ai.i = true;
                z.aa.addElement(ai);
                if (z.n != null) {
                    z.n.a(ai);
                }
                super.i[1] = ai;
            }
            if ((n & 0x1) != 0x0) {
                ac.e = w.f();
            }
            if ((n & 0x2) != 0x0) {
                ac.f = w.f();
            }
            if ((n & 0x8) != 0x0) {
                final ac ac2 = ac;
                final ac ac3 = ac;
                final int n2 = (w.e() << 16) / ao.k;
                ac3.d = n2;
                ac2.a = n2;
            }
            final int d3 = w.d();
            w.c();
            int n3 = 0;
            while (true) {
                Label_0291: {
                    if (!c.l) {
                        break Label_0291;
                    }
                    final int a2 = w.a(d);
                    if (ao != null) {
                        super.aq = ao.a(a2, this);
                        this.a(z, ac.a(ac, a));
                        this.d();
                    }
                    final ac ac4 = ac;
                    ac4.e += w.b(d2);
                    ++n3;
                }
                if (n3 >= d3) {
                    continue Label_0232_Outer;
                }
                continue;
            }
        }
    }
}
