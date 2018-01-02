import java.util.GregorianCalendar;
import java.util.Date;
import java.awt.CheckboxMenuItem;
import java.applet.AudioClip;
import java.awt.Color;
import java.net.URL;
import java.applet.AppletContext;

// 
// Decompiled by Procyon v0.5.30
// 

class br
{
    static int a;
    String[] b;
    String[] c;
    y[] d;
    bf e;
    p f;
    bu g;
    bq h;
    bc i;
    n j;
    j k;
    bk l;
    AppletContext m;
    URL n;
    boolean o;
    bd[] p;
    be q;
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
    AudioClip G;
    CheckboxMenuItem H;
    String I;
    String[] J;
    String[] K;
    String[] L;
    
    void a(final bf e) {
        this.e = e;
    }
    
    void a(final String s, final boolean b) {
        int n = 0;
        while (true) {
            Label_0046: {
                if (!bm.dX) {
                    break Label_0046;
                }
                if (this.d[n] != null && this.d[n].e()) {
                    this.d[n].a(s, bn.q, b);
                }
                ++n;
            }
            if (n == br.a) {
                return;
            }
            continue;
        }
    }
    
    void a(final String s) {
        final y d = this.d(s);
        if (d != null) {
            d.g();
        }
    }
    
    void b(final String s, final boolean b) {
        final y d = this.d(s);
        if (d != null) {
            d.a(b);
        }
    }
    
    void a(final String s, final char c) {
        final y d = this.d(s);
        if (d != null) {
            d.a(c);
        }
    }
    
    boolean b(final String s) {
        final y d = this.d(s);
        if (d != null) {
            d.i();
        }
        return false;
    }
    
    br(final bu g, final bq h, final bc i, final n j, final j k, final bk l, final p f, final String s, final bd[] p32, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11, final String s12, final String s13, final String s14, final String s15, final String s16, final AudioClip g2, final CheckboxMenuItem h2, final AppletContext m, final URL n, final String i2, final String[] j2, final String[] k2, final String[] l2) {
        final boolean dx = bm.dX;
        this.b = new String[br.a];
        this.c = new String[br.a];
        this.d = new y[br.a];
        this.o = false;
        this.g = g;
        this.h = h;
        this.i = i;
        this.j = j;
        this.k = k;
        this.l = l;
        this.f = f;
        this.m = m;
        this.n = n;
        this.I = i2;
        this.J = j2;
        this.K = k2;
        this.L = l2;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0138: {
                    if (!dx) {
                        break Label_0138;
                    }
                    this.d[n2] = null;
                    ++n2;
                }
                if (n2 < br.a) {
                    continue;
                }
                break;
            }
            this.p = p32;
            if (!dx) {
                if (s != null && s.toUpperCase().equals(f("|}P"))) {
                    this.o = true;
                }
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
                this.E = bn.b(s15);
                this.F = bn.b(s16);
                this.C = bn.b(s13);
                this.D = bn.b(s14);
                this.G = g2;
                this.H = h2;
                return;
            }
            continue;
        }
    }
    
    public void a(final String s, final int n) {
        final y d = this.d(s);
        if (d != null) {
            d.d(n);
        }
    }
    
    void c(final String s, final boolean b) {
        if (!irc.bF) {
            return;
        }
        if (this.d(s) != null) {
            return;
        }
        final int d = this.d();
        if (d >= 0) {
            new Date();
            this.b[d] = new String(s);
            this.c[d] = irc.bO.format(new GregorianCalendar().getTime());
            this.d[d] = new y(s, this.c[d], this.f, this.e, this.g, this, this.h, this.q, this.i, this.j, this.k, this.l, this.o, this.p, this.J, this.K, this.L);
            if (b) {
                this.a();
            }
            this.e.a(f("pkF\u0001*jkWsX") + s + "\n");
        }
    }
    
    void c(final String s) {
        final boolean dx = bm.dX;
        int n = 0;
    Label_0095_Outer:
        while (true) {
            while (true) {
                Label_0049: {
                    if (!dx) {
                        break Label_0049;
                    }
                    final br br = this;
                    if (br.d[n] != null && this.d[n].e()) {
                        this.d[n].Y.a(s);
                    }
                    ++n;
                }
                if (n != br.a) {
                    continue;
                }
                break;
            }
            final br br = this;
            if (!dx) {
                if (this.L != null) {
                    final String[] l = new String[this.L.length + 1];
                    int n2 = 0;
                    while (true) {
                        while (true) {
                            Label_0098: {
                                if (!dx) {
                                    break Label_0098;
                                }
                                l[n2] = this.L[n2];
                                ++n2;
                            }
                            if (n2 != this.L.length) {
                                continue Label_0095_Outer;
                            }
                            break;
                        }
                        l[this.L.length] = s;
                        if (dx) {
                            continue;
                        }
                        break;
                    }
                    this.L = l;
                }
                return;
            }
            continue;
        }
    }
    
    void a(final String s, final String s2) {
        final y d = this.d(s);
        if (d != null) {
            d.b(s2);
        }
    }
    
    void a(final be q) {
        this.q = q;
    }
    
    void a() {
        if (this.H.getState()) {
            this.G.play();
        }
    }
    
    public void b(final String s, final int n) {
        final y d = this.d(s);
        if (d != null) {
            d.b(n);
        }
    }
    
    y d(String upperCase) {
        upperCase = upperCase.toUpperCase();
        int n = 0;
        while (true) {
            Label_0060: {
                if (!bm.dX) {
                    break Label_0060;
                }
                if (this.b[n] != null && upperCase.equals(this.b[n].toUpperCase()) && this.d[n].m) {
                    return this.d[n];
                }
                ++n;
            }
            if (n >= br.a) {
                return null;
            }
            continue;
        }
    }
    
    static {
        br.a = 20;
    }
    
    public String e(final String s) {
        final y d = this.d(s);
        if (d != null) {
            return d.d();
        }
        return "";
    }
    
    void b() {
        int n = 0;
        while (true) {
            Label_0042: {
                if (!bm.dX) {
                    break Label_0042;
                }
                if (this.d[n] != null && this.d[n].m) {
                    this.d[n].g();
                }
                ++n;
            }
            if (n >= br.a) {
                return;
            }
            continue;
        }
    }
    
    int c() {
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
                        if (this.d[n2] == null) {
                            break Label_0037;
                        }
                        this.d[n2].e();
                        final int n3;
                        if (n3 == 1) {
                            ++n;
                        }
                    }
                    ++n2;
                }
                if (n2 != br.a) {
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
    
    void b(final String s, final String s2) {
        final boolean dx = bm.dX;
        int n = 0;
        while (true) {
            Label_0289: {
                if (!dx) {
                    break Label_0289;
                }
                if (this.b[n] != null && s.toUpperCase().equals(this.b[n].toUpperCase()) && this.d[n].m) {
                    Label_0251: {
                        if (!irc.e) {
                            if (irc.cy.c(s2) != -1) {
                                break Label_0251;
                            }
                            this.d[n].X.setText(bm.cD + s2 + bm.cE + this.c[n]);
                            irc.cy.a(s, s2);
                            final y y = this.d[n];
                            final String[] b = this.b;
                            final int n2 = n;
                            final String f = new String(s2);
                            b[n2] = f;
                            y.f = f;
                            if (this.d[n].Z == null) {
                                break Label_0251;
                            }
                            this.d[n].Z.a(s2);
                            if (!dx) {
                                break Label_0251;
                            }
                        }
                        if (this.d(s2) == null) {
                            final y y2 = this.d[n];
                            final String[] b2 = this.b;
                            final int n3 = n;
                            final String f2 = new String(s2);
                            b2[n3] = f2;
                            y2.f = f2;
                            this.d[n].W.setTitle(s2 + bm.cE + this.c[n]);
                        }
                    }
                    this.e.a(f("pkF\u0001*jkWsX") + s2 + "\n");
                    return;
                }
                ++n;
            }
            if (n >= br.a) {
                return;
            }
            continue;
        }
    }
    
    int d() {
        final boolean dx = bm.dX;
        int n = 0;
        while (true) {
            Label_0036: {
                if (!dx) {
                    break Label_0036;
                }
                if (this.d[n] != null && this.d[n].e()) {
                    ++n;
                    break Label_0036;
                }
                return;
            }
            if (n < br.a) {
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
    
    private static String f(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '%';
                    break;
                }
                case 1: {
                    c2 = '8';
                    break;
                }
                case 2: {
                    c2 = '\u0003';
                    break;
                }
                case 3: {
                    c2 = 'S';
                    break;
                }
                default: {
                    c2 = 'b';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
