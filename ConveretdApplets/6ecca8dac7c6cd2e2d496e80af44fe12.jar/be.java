import java.awt.Color;
import java.net.URL;
import java.applet.AppletContext;

// 
// Decompiled by Procyon v0.5.30
// 

class be
{
    static int a;
    String[] b;
    t[] c;
    p d;
    bf e;
    bu f;
    br g;
    bq h;
    bc i;
    n j;
    j k;
    bk l;
    AppletContext m;
    URL n;
    String o;
    boolean p;
    bd[] q;
    Color r;
    Color s;
    Color t;
    Color u;
    Color v;
    Color w;
    Color x;
    Color y;
    Color z;
    Color A;
    Color B;
    Color C;
    Color D;
    Color E;
    Color F;
    Color G;
    Color H;
    Color I;
    Color J;
    String[] K;
    String[] L;
    String[] M;
    
    void a(final bf e) {
        this.e = e;
    }
    
    void a(final String s) {
        final t h = this.h(s);
        if (h != null && h.i()) {
            h.a();
        }
    }
    
    void a(final String s, final boolean b) {
        int n = 0;
        while (true) {
            Label_0046: {
                if (!bm.dX) {
                    break Label_0046;
                }
                if (this.c[n] != null && this.c[n].i()) {
                    this.c[n].a(s, bn.q, b);
                }
                ++n;
            }
            if (n == be.a) {
                return;
            }
            continue;
        }
    }
    
    void b(final String s) {
        final t h = this.h(s);
        if (h != null && h.i()) {
            h.b();
        }
    }
    
    void a(final String s, final String s2) {
        int n = 0;
        while (true) {
            Label_0043: {
                if (!bm.dX) {
                    break Label_0043;
                }
                if (this.c[n] != null && this.c[n].i()) {
                    this.c[n].a(s, s2);
                }
                ++n;
            }
            if (n == be.a) {
                return;
            }
            continue;
        }
    }
    
    void b(final String s, final String s2) {
        int n = 0;
        while (true) {
            Label_0100: {
                if (!bm.dX) {
                    break Label_0100;
                }
                if (this.c[n] != null && this.c[n].i() && this.c[n].a(s)) {
                    this.c[n].a(irc.R + s + bm.bG + s2, bn.j, false);
                    this.c[n].b(s, s2);
                }
                ++n;
            }
            if (n >= be.a) {
                return;
            }
            continue;
        }
    }
    
    void a(final String s, final char c) {
        final t h = this.h(s);
        if (h != null) {
            h.a(c);
        }
    }
    
    void a(final String s, String trim, final v v, final boolean b) {
        final boolean dx = bm.dX;
        trim = trim.trim();
        int n = 0;
        while (true) {
            Label_0267: {
                if (!dx) {
                    break Label_0267;
                }
                if (this.c[n] != null && this.c[n].i() && this.c[n].a(s)) {
                    Label_0189: {
                        if (b) {
                            v.a(irc.R + s + bm.cz + bm.bK + this.b[n] + i("Z\u0012") + trim + ")", bn.h, false);
                            if (!dx) {
                                break Label_0189;
                            }
                        }
                        this.c[n].a(irc.R + s + bm.cz + i("Z\u0012") + trim + ")", bn.g, false);
                    }
                    if (irc.cE != null && !irc.cE.equals("")) {
                        this.c[n].a(i("P\u001a") + s + " " + irc.cE, bn.h, false);
                    }
                    this.c[n].l(s);
                }
                ++n;
            }
            if (n >= be.a) {
                return;
            }
            continue;
        }
    }
    
    void a(final String s, final String s2, final Color color, final boolean b) {
        final t h = this.h(s);
        if (h != null) {
            h.a(s2, color, b);
        }
    }
    
    void c(final String s, final String s2) {
        final t h = this.h(s);
        if (h != null) {
            h.b(s2);
        }
    }
    
    public void a(final String s, final int n) {
        final t h = this.h(s);
        if (h != null) {
            h.b(n);
        }
    }
    
    int a() {
        final boolean dx = bm.dX;
        int n = 0;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0040: {
                    if (!dx) {
                        break Label_0040;
                    }
                    Label_0037: {
                        if (this.c[n2] == null) {
                            break Label_0037;
                        }
                        this.c[n2].i();
                        final int n3;
                        if (n3 == 1) {
                            ++n;
                        }
                    }
                    ++n2;
                }
                if (n2 < be.a) {
                    continue;
                }
                break;
            }
            final int n3 = n;
            if (!dx) {
                return n3;
            }
            continue;
        }
    }
    
    int b() {
        final boolean dx = bm.dX;
        int n = 0;
        while (true) {
            Label_0036: {
                if (!dx) {
                    break Label_0036;
                }
                if (this.c[n] != null && this.c[n].i()) {
                    ++n;
                    break Label_0036;
                }
                return;
            }
            if (n < be.a) {
                continue;
            }
            break;
        }
        final int n2 = -1;
        if (!dx) {
            return n2;
        }
        return n2;
    }
    
    void c() {
        int n = 0;
        while (true) {
            Label_0043: {
                if (!bm.dX) {
                    break Label_0043;
                }
                if (this.c[n] != null && this.c[n].n) {
                    this.c[n].a(true);
                }
                ++n;
            }
            if (n >= be.a) {
                return;
            }
            continue;
        }
    }
    
    boolean isChannelOp(final String s, final String s2) {
        final t h = this.h(s);
        return h != null && h.isChannelOp(s2);
    }
    
    void d(final String s, final String s2) {
        final t h = this.h(s);
        if (h != null) {
            h.c(s2);
        }
    }
    
    boolean e(final String s, final String s2) {
        final t h = this.h(s);
        return h != null && h.d(s2);
    }
    
    void f(final String s, final String s2) {
        final t h = this.h(s);
        if (h != null) {
            h.k(s2);
        }
    }
    
    void a(final String s, final String s2, final String s3) {
        final t h = this.h(s);
        if (h != null) {
            h.m.a(s2, s3);
        }
    }
    
    void g(final String s, final String s2) {
        final t h = this.h(s);
        if (h != null) {
            h.e(s2);
        }
    }
    
    void h(final String s, final String s2) {
        final t h = this.h(s);
        if (h != null) {
            h.g(s2);
        }
    }
    
    boolean i(final String s, final String s2) {
        final t h = this.h(s);
        return h != null && h.h(s2);
    }
    
    void b(final String s, final boolean b) {
        final t h = this.h(s);
        if (h != null) {
            h.a(b);
        }
    }
    
    void j(final String s, final String s2) {
        final t h = this.h(s);
        if (h != null && h.i()) {
            h.f(s2);
        }
    }
    
    void c(final String s, final boolean b) {
        final t h = this.h(s);
        if (h != null) {
            h.c(b);
        }
    }
    
    boolean c(final String s) {
        final t h = this.h(s);
        return h != null && h.m();
    }
    
    be(final bu f, final br g, final bq h, final bc i, final n j, final j k, final bk l, final p d, final String s, final bd[] q, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11, final String s12, final String s13, final String s14, final String s15, final String s16, final String s17, final String s18, final String s19, final String s20, final AppletContext m, final URL n, final String o, final String[] k2, final String[] l2, final String[] m2) {
        this.b = new String[be.a];
        this.c = new t[be.a];
        this.p = false;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
        this.j = j;
        this.k = k;
        this.l = l;
        this.d = d;
        this.m = m;
        this.n = n;
        this.o = o;
        this.K = k2;
        this.L = l2;
        this.M = m2;
        int n2 = 0;
        while (true) {
            Label_0130: {
                if (!bm.dX) {
                    break Label_0130;
                }
                this.c[n2] = null;
                ++n2;
            }
            if (n2 >= be.a) {
                if (s != null && s.toUpperCase().equals(i("#\u007fx"))) {
                    this.p = true;
                }
                this.q = q;
                this.r = bn.b(s2);
                this.s = bn.b(s3);
                this.t = bn.b(s4);
                this.u = bn.b(s5);
                this.v = bn.b(s6);
                this.w = bn.b(s7);
                this.x = bn.b(s8);
                this.y = bn.b(s9);
                this.z = bn.b(s10);
                this.A = bn.b(s11);
                this.B = bn.b(s12);
                this.C = bn.b(s13);
                this.D = bn.b(s14);
                this.E = bn.b(s15);
                this.F = bn.b(s16);
                this.G = bn.b(s17);
                this.H = bn.b(s18);
                this.I = bn.b(s19);
                this.J = bn.b(s20);
                return;
            }
            continue;
        }
    }
    
    void k(final String s, final String s2) {
        final t h = this.h(s);
        if (h != null && h.i()) {
            if (s2.equals("+")) {
                h.i("");
                return;
            }
            h.i(s2.substring(1));
        }
    }
    
    public void b(final String s, final int n) {
        final t h = this.h(s);
        if (h != null) {
            h.d(n);
        }
    }
    
    void d(final String s) {
        if (this.h(s) != null) {
            return;
        }
        final int b = this.b();
        if (b >= 0) {
            this.b[b] = new String(s);
            this.c[b] = new t(s, this.d, this.e, this.f, this.g, this.h, this, this.i, this.j, this.k, this.l, this.p, this.q, this.K, this.L, this.M);
        }
    }
    
    void e(final String s) {
        final t h = this.h(s);
        if (h != null && h.i()) {
            h.n();
        }
    }
    
    void f(final String s) {
        final t h = this.h(s);
        if (h != null && h.i()) {
            h.o();
        }
    }
    
    void g(final String s) {
        final boolean dx = bm.dX;
        int n = 0;
        be be;
        int n2;
        String[] m;
        String[] array = null;
        String[] i;
        int n3;
        Label_0079_Outer:Label_0124_Outer:Label_0133_Outer:
        while (true) {
            while (true) {
                Label_0049: {
                    if (!dx) {
                        break Label_0049;
                    }
                    be = this;
                    if (be.c[n] != null && this.c[n].i()) {
                        this.c[n].ba.a(s);
                    }
                    ++n;
                }
                if (n != be.a) {
                    continue;
                }
                break;
            }
            be = this;
            if (!dx) {
                if (this.M != null) {
                    n2 = 0;
                    while (true) {
                        while (true) {
                            Label_0092: {
                                if (!dx) {
                                    break Label_0092;
                                }
                                m = this.M;
                                if (array[n2].equalsIgnoreCase(s)) {
                                    return;
                                }
                                ++n2;
                            }
                            if (n2 != this.M.length) {
                                continue Label_0079_Outer;
                            }
                            break;
                        }
                        array = new String[this.M.length + 1];
                        if (dx) {
                            continue Label_0124_Outer;
                        }
                        break;
                    }
                    i = array;
                    n3 = 0;
                    while (true) {
                        while (true) {
                            Label_0136: {
                                if (!dx) {
                                    break Label_0136;
                                }
                                i[n3] = this.M[n3];
                                ++n3;
                            }
                            if (n3 != this.M.length) {
                                continue Label_0133_Outer;
                            }
                            break;
                        }
                        i[this.M.length] = s;
                        if (dx) {
                            continue;
                        }
                        break;
                    }
                    this.M = i;
                }
                return;
            }
            continue;
        }
    }
    
    void l(final String s, final String s2) {
        final t h = this.h(s);
        if (h != null) {
            h.j(s2);
        }
    }
    
    void d() {
        int n = 0;
        while (true) {
            Label_0041: {
                if (!bm.dX) {
                    break Label_0041;
                }
                if (this.c[n] != null && this.c[n].i()) {
                    this.c[n].r();
                }
                ++n;
            }
            if (n == be.a) {
                return;
            }
            continue;
        }
    }
    
    static {
        be.a = 10;
    }
    
    void m(final String s, final String s2) {
        final t h = this.h(s);
        if (h != null) {
            h.l(s2);
        }
    }
    
    void d(final String s, final boolean b) {
        int n = 0;
        while (true) {
            Label_0044: {
                if (!bm.dX) {
                    break Label_0044;
                }
                if (this.c[n] != null && this.c[n].i()) {
                    this.c[n].a(s, b);
                }
                ++n;
            }
            if (n >= be.a) {
                return;
            }
            continue;
        }
    }
    
    t h(String upperCase) {
        upperCase = upperCase.trim().toUpperCase();
        int n = 0;
        while (true) {
            Label_0063: {
                if (!bm.dX) {
                    break Label_0063;
                }
                if (this.b[n] != null && upperCase.equals(this.b[n].toUpperCase()) && this.c[n].i()) {
                    return this.c[n];
                }
                ++n;
            }
            if (n >= be.a) {
                return null;
            }
            continue;
        }
    }
    
    private static String i(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'z';
                    break;
                }
                case 1: {
                    c2 = ':';
                    break;
                }
                case 2: {
                    c2 = '+';
                    break;
                }
                case 3: {
                    c2 = '_';
                    break;
                }
                default: {
                    c2 = 'P';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
