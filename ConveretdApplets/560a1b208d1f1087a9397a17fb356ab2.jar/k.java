import java.awt.Point;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

class k
{
    protected int a;
    protected int b;
    protected static final int c = 12;
    protected static final int d = 10000;
    static final boolean e = true;
    static final boolean f = false;
    protected g g;
    protected h h;
    protected m i;
    protected int j;
    protected int k;
    protected a[] l;
    protected d[] m;
    protected int[] n;
    protected int[] o;
    protected boolean p;
    protected boolean q;
    protected int r;
    protected int s;
    protected int t;
    protected int u;
    
    protected void a() {
        final int a = q.a;
        int b = 0;
        int b2;
        int n;
        while (true) {
            while (true) {
                Label_0038: {
                    if (a == 0) {
                        break Label_0038;
                    }
                    this.m[b].Q = 0;
                    this.m[b].R = this.q;
                    ++b;
                }
                if (b < this.k * this.j) {
                    continue;
                }
                break;
            }
            b = this.b(this.g.c.height);
            b2 = this.b(this.g.c.width);
            n = 0;
            if (a != 0) {
                continue;
            }
            break;
        }
        while (true) {
            Label_0526: {
                if (a == 0) {
                    break Label_0526;
                }
                final a a2 = new a(this.o[n], n, 0, 0);
                final a a3 = new a(a2);
                final a a4 = new a(a2);
                Label_0519: {
                    if (a2.a < b2 && a2.a >= 0) {
                        Label_0218: {
                            if (!this.q) {
                                final a a5 = a3;
                                a5.a -= this.b;
                                final a a6 = a4;
                                a6.a += this.b;
                                if (a == 0) {
                                    break Label_0218;
                                }
                            }
                            final a a7 = a3;
                            a7.b -= this.b;
                            final a a8 = a4;
                            a8.b += this.b;
                        }
                        this.a(a2);
                        this.a(a3);
                        this.a(a4);
                        final int n2 = a2.a >> this.a;
                        final int n3 = a2.b >> this.a;
                        Label_0392: {
                            if (n3 < this.j) {
                                final int n4 = n3 * this.k + n2;
                                final d d = this.m[n4];
                                d.Q |= 0x20;
                                if (!this.q || !this.p) {
                                    this.m[n4].t = a2;
                                    this.m[n4].u = a3;
                                    this.m[n4].v = a4;
                                    if (a == 0) {
                                        break Label_0392;
                                    }
                                }
                                this.m[n4].w = a2;
                                this.m[n4].x = a3;
                                this.m[n4].y = a4;
                            }
                        }
                        if (n3 > 0) {
                            final int n5 = (n3 - 1) * this.k + n2;
                            final d d2 = this.m[n5];
                            d2.Q |= 0x10;
                            if (!this.q || !this.p) {
                                this.m[n5].w = a2;
                                this.m[n5].x = a3;
                                this.m[n5].y = a4;
                                if (a == 0) {
                                    break Label_0519;
                                }
                            }
                            this.m[n5].t = a2;
                            this.m[n5].u = a3;
                            this.m[n5].v = a4;
                        }
                    }
                }
                n += this.b;
            }
            if (n <= b) {
                continue;
            }
            break;
        }
        int n6 = 0;
        while (true) {
            Label_0972: {
                if (a == 0) {
                    break Label_0972;
                }
                final a a9 = new a(n6, this.n[n6], 0, 0);
                final a a10 = new a(a9);
                final a a11 = new a(a9);
                Label_0965: {
                    if (a9.b < b && a9.b >= 0) {
                        Label_0663: {
                            if (!this.q) {
                                final a a12 = a10;
                                a12.a -= this.b;
                                final a a13 = a11;
                                a13.a += this.b;
                                if (a == 0) {
                                    break Label_0663;
                                }
                            }
                            final a a14 = a10;
                            a14.b -= this.b;
                            final a a15 = a11;
                            a15.b += this.b;
                        }
                        this.a(a9);
                        this.a(a10);
                        this.a(a11);
                        final int n7 = a9.a >> this.a;
                        final int n8 = a9.b >> this.a;
                        Label_0838: {
                            if (n7 < this.k) {
                                final int n9 = n8 * this.k + n7;
                                final d d3 = this.m[n9];
                                d3.Q |= 0x8;
                                if (this.q || !this.p) {
                                    this.m[n9].t = a9;
                                    this.m[n9].u = a10;
                                    this.m[n9].v = a11;
                                    if (a == 0) {
                                        break Label_0838;
                                    }
                                }
                                this.m[n9].w = a9;
                                this.m[n9].x = a10;
                                this.m[n9].y = a11;
                            }
                        }
                        if (n7 > 0) {
                            final int n10 = n8 * this.k + n7 - 1;
                            final d d4 = this.m[n10];
                            d4.Q |= 0x4;
                            if (this.q || !this.p) {
                                this.m[n10].w = a9;
                                this.m[n10].x = a10;
                                this.m[n10].y = a11;
                                if (a == 0) {
                                    break Label_0965;
                                }
                            }
                            this.m[n10].t = a9;
                            this.m[n10].u = a10;
                            this.m[n10].v = a11;
                        }
                    }
                }
                n6 += this.b;
            }
            if (n6 > b2) {
                return;
            }
            continue;
        }
    }
    
    protected boolean a(final Dimension dimension) {
        final int a = q.a;
        this.a = Math.min((int)Math.ceil(Math.log(dimension.width * dimension.height / 75.0f) / Math.log(2.0) / 2.0), 4);
        this.b = 1 << this.a;
        final int b = this.b(dimension.width);
        final int b2 = this.b(dimension.height);
        this.k = b >> this.a;
        this.j = b2 >> this.a;
        if (dimension.width == 0 || dimension.height == 0) {
            return true;
        }
        this.l = new a[(this.k + 1) * (this.j + 1)];
        this.m = new d[this.k * this.j];
        this.n = new int[b + 1];
        this.o = new int[b2 + 1];
        int n = 0;
        int n2 = 0;
    Label_0182_Outer:
        while (true) {
            if (a != 0) {
                break Label_0174;
            }
            while (true) {
                Label_0237: {
                    break Label_0237;
                    while (true) {
                        int n3 = 0;
                        while (true) {
                            final int y;
                            if (y > b) {
                                n3 = n2 + this.b;
                                if (a == 0) {
                                    break;
                                }
                            }
                            else {
                                this.l[n] = new a(y, n2, 0, 0);
                                final int n4 = y + this.b;
                            }
                            y = n3;
                            ++n;
                        }
                        n2 = n3;
                        break Label_0237;
                        int y = 0;
                        if (a != 0) {}
                        continue Label_0182_Outer;
                    }
                }
                if (n2 > b2) {
                    n = 0;
                    n2 = 0;
                    int y = 0;
                    if (a != 0) {
                        if (a != 0) {
                            continue;
                        }
                    }
                    int n5 = 0;
                    while (true) {
                        if (y >= b2) {
                            n5 = 1;
                            if (a == 0) {
                                break;
                            }
                        }
                        int x = n5;
                        while (true) {
                            Label_0421: {
                                if (a == 0) {
                                    break Label_0421;
                                }
                                final d[] m = this.m;
                                final int n6 = n2;
                                final d d = new d();
                                m[n6] = d;
                                final d d2 = d;
                                d2.x = x;
                                d2.y = y;
                                d2.width = Math.min(this.b, dimension.width - x);
                                d2.height = Math.min(this.b, dimension.height - y);
                                d2.p = this.l[n];
                                d2.q = this.l[n + 1];
                                d2.r = this.l[n + this.k + 1];
                                d2.s = this.l[n + this.k + 2];
                                x += this.b;
                                ++n2;
                                ++n;
                            }
                            if (x < b) {
                                continue;
                            }
                            break;
                        }
                        y += this.b;
                        ++n;
                    }
                    return n5 != 0;
                }
                break;
            }
            continue Label_0182_Outer;
        }
    }
    
    protected boolean b(final Dimension dimension) {
        final int n = (int)Math.ceil(Math.log(dimension.width) / Math.log(2.0));
        this.t = 4096;
        this.u = this.t << n;
        this.r = dimension.width - 1 << 12;
        this.s = this.r << n;
        return true;
    }
    
    k(final h h, final g g) throws NullPointerException, IllegalArgumentException {
        this.i = new m();
        this.j = 0;
        this.k = 0;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = true;
        this.q = true;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        if (h == null || g == null) {
            throw new NullPointerException();
        }
        final Dimension c = h.c;
        final Dimension c2 = g.c;
        if (c.height != c.width << 1 && c.height != c.width) {
            throw new IllegalArgumentException();
        }
        this.h = h;
        this.b(c);
        this.a(this.h.f());
        this.i.a(this.h.k);
        this.i.b(this.h.e());
        this.g = g;
        this.a(c2);
    }
    
    void a(final q q) {
        this.i.a(q);
    }
    
    q b() {
        return this.i.l;
    }
    
    boolean c() {
        return a(this.h.c.width);
    }
    
    protected void d() {
        int a = q.a;
        final float b = this.g.d.b;
        final float a2 = this.g.d.a;
        final int b2 = this.b(this.g.c.height);
        final int b3 = this.b(this.g.c.width);
        final float a3 = this.i.a();
        final float b4 = this.i.b();
        final float c = this.i.c();
        final float n = this.i.l.e * this.i.e;
        final float n2 = (a3 != 0.0f) ? ((b4 != 0.0f) ? (-a3 / b4) : ((a3 > 0.0f) ? -10000 : 10000)) : 0.0f;
        if (Math.abs(n2) > 1000.0f) {
            final float n3 = n * -c / a3 + a2;
            final int n4 = (n3 > b3) ? 10000 : ((n3 < 0.0f) ? -10000 : ((int)n3));
            int i = 0;
            while (true) {
                while (true) {
                    Label_0228: {
                        if (a == 0) {
                            break Label_0228;
                        }
                        this.o[i] = n4;
                        ++i;
                    }
                    if (i <= b2) {
                        continue;
                    }
                    break;
                }
                i = 0;
                if (a != 0) {
                    if (a != 0) {
                        continue;
                    }
                }
                break;
            }
            while (i <= b3) {
                this.n[i] = 10000;
                ++i;
            }
        }
        else if (Math.abs(n2) < 0.001f) {
            final float n5 = (b4 != 0.0f) ? (n * -c / b4 + b) : ((c > 0.0f) ? -1.0f : (b2 + 1));
            final int n6 = (n5 > b2) ? 10000 : ((n5 < 0.0f) ? -10000 : ((int)n5));
            int n7 = 0;
            while (true) {
                while (true) {
                    Label_0377: {
                        if (a == 0) {
                            break Label_0377;
                        }
                        this.n[n7] = n6;
                        ++n7;
                    }
                    if (n7 <= b3) {
                        continue;
                    }
                    break;
                }
                n7 = 0;
                if (a != 0) {
                    continue;
                }
                break;
            }
            while (true) {
                Label_0410: {
                    if (a == 0) {
                        break Label_0410;
                    }
                    this.o[n7] = 10000;
                    ++n7;
                }
                if (n7 <= b2) {
                    continue;
                }
                break;
            }
        }
        else {
            final float n8 = n * -c / b4 - n2 * a2 + b;
            final float n9 = n * -c / a3 - b / n2 + a2;
            int n10 = 0;
            while (true) {
                Label_0514: {
                    if (a == 0) {
                        break Label_0514;
                    }
                    final float n11 = n2 * n10 + n8;
                    this.n[n10] = ((n11 > b2) ? 10000 : ((n11 < 0.0f) ? -10000 : ((int)n11)));
                    ++n10;
                }
                if (n10 <= b3) {
                    continue;
                }
                break;
            }
            int n12 = 0;
            while (true) {
                Label_0582: {
                    if (a == 0) {
                        break Label_0582;
                    }
                    final float n13 = n12 / n2 + n9;
                    this.o[n12] = ((n13 > b3) ? 10000 : ((n13 < 0.0) ? -10000 : ((int)n13)));
                    ++n12;
                }
                if (n12 <= b2) {
                    continue;
                }
                break;
            }
        }
        this.q = (Math.abs(n2) <= 1.0f);
        this.p = (n2 < 0.0f);
        if (g.a) {
            q.a = ++a;
        }
    }
    
    protected void e() {
        final int a = q.a;
        final float b = this.g.d.b;
        final float a2 = this.g.d.a;
        final float a3 = this.i.a();
        final float b2 = this.i.b();
        final float c = this.i.c();
        final float n = this.i.l.e * this.i.e;
        int n2 = 0;
        while (true) {
            Label_3334: {
                if (a == 0) {
                    break Label_3334;
                }
                final d d = this.m[n2];
                Label_0191: {
                    if (this.q) {
                        if (d.Q != 0) {
                            final a r = d.r;
                            d.r = d.q;
                            d.q = r;
                        }
                        if (d.Q == 12) {
                            d.Q = 48;
                            if (a == 0) {
                                break Label_0191;
                            }
                        }
                        if (d.Q == 36) {
                            d.Q = 24;
                            if (a == 0) {
                                break Label_0191;
                            }
                        }
                        if (d.Q == 24) {
                            d.Q = 36;
                        }
                    }
                }
                int n3 = 0;
                Label_0313: {
                    if ((d.Q & 0x20) != 0x0) {
                        n3 = (this.q ? (d.w.a - d.p.a) : (d.w.b - d.p.b));
                        if (a == 0) {
                            break Label_0313;
                        }
                    }
                    if ((d.Q & 0x10) != 0x0) {
                        n3 = (this.q ? (d.r.a - d.t.a) : (d.r.b - d.t.b));
                    }
                }
                Label_3331: {
                    Label_1921: {
                        switch (d.Q) {
                            case 48: {
                                d.A.x = d.r.x - d.p.x >> this.a;
                                d.A.y = d.r.y - d.p.y >> this.a;
                                d.B.x = d.s.x - d.q.x >> this.a;
                                d.B.y = d.s.y - d.q.y >> this.a;
                                if (a != 0) {
                                    break Label_1921;
                                }
                                break;
                            }
                            case 40: {
                                d.A.x = (d.w.x - d.p.x) / n3;
                                d.A.y = (d.w.y - d.p.y) / n3;
                                d.B.x = (d.y.x - d.q.x) / n3;
                                d.B.y = (d.y.y - d.q.y) / n3;
                                d.C.x = (d.r.x - d.w.x) / (this.b - n3);
                                d.C.y = (d.r.y - d.w.y) / (this.b - n3);
                                d.J.x = d.y.x - d.w.x >> this.a;
                                d.J.y = d.y.y - d.w.y >> this.a;
                                d.N.x = (d.s.x - d.r.x - d.y.x + d.w.x) / (this.b - n3) >> this.a;
                                d.N.y = (d.s.y - d.r.y - d.y.y + d.w.y) / (this.b - n3) >> this.a;
                                if (a != 0) {
                                    break Label_1921;
                                }
                                break;
                            }
                            case 20: {
                                d.A.x = (d.r.x - d.u.x) / n3;
                                d.A.y = (d.r.y - d.u.y) / n3;
                                d.B.x = (d.s.x - d.t.x) / n3;
                                d.B.y = (d.s.y - d.t.y) / n3;
                                d.z.x = (d.u.x - d.p.x) / (this.b - n3);
                                d.z.y = (d.u.y - d.p.y) / (this.b - n3);
                                d.G.x = d.q.x - d.p.x >> this.a;
                                d.G.y = d.q.y - d.p.y >> this.a;
                                d.K.x = (d.t.x - d.u.x - d.q.x + d.p.x) / (this.b - n3) >> this.a;
                                d.K.y = (d.t.y - d.u.y - d.q.y + d.p.y) / (this.b - n3) >> this.a;
                                if (a != 0) {
                                    break Label_1921;
                                }
                                break;
                            }
                            case 36: {
                                d.A.x = (d.x.x - d.p.x) / n3;
                                d.A.y = (d.x.y - d.p.y) / n3;
                                d.B.x = (d.w.x - d.q.x) / n3;
                                d.B.y = (d.w.y - d.q.y) / n3;
                                d.C.x = (d.r.x - d.x.x) / (this.b - n3);
                                d.C.y = (d.r.y - d.x.y) / (this.b - n3);
                                d.J.x = d.w.x - d.x.x >> this.a;
                                d.J.y = d.w.y - d.x.y >> this.a;
                                d.N.x = (d.s.x - d.r.x - d.w.x + d.x.x) / (this.b - n3) >> this.a;
                                d.N.y = (d.s.y - d.r.y - d.w.y + d.x.y) / (this.b - n3) >> this.a;
                                if (a != 0) {
                                    break Label_1921;
                                }
                                break;
                            }
                            case 24: {
                                d.A.x = (d.r.x - d.t.x) / n3;
                                d.A.y = (d.r.y - d.t.y) / n3;
                                d.B.x = (d.s.x - d.v.x) / n3;
                                d.B.y = (d.s.y - d.v.y) / n3;
                                d.z.x = (d.t.x - d.p.x) / (this.b - n3);
                                d.z.y = (d.t.y - d.p.y) / (this.b - n3);
                                d.G.x = d.q.x - d.p.x >> this.a;
                                d.G.y = d.q.y - d.p.y >> this.a;
                                d.K.x = (d.v.x - d.t.x - d.q.x + d.p.x) / (this.b - n3) >> this.a;
                                d.K.y = (d.v.y - d.t.y - d.q.y + d.p.y) / (this.b - n3) >> this.a;
                                if (a != 0) {
                                    break Label_1921;
                                }
                                break;
                            }
                            case 0: {
                                d.Q = (((d.x + d.width / 2.0f - a2) * a3 + (d.y + d.height / 2.0f - b) * b2 + n * c < 0.0 ^ !this.h.d()) ? 1 : 2);
                                final d d2 = d;
                                final d d3 = d;
                                final int n4 = this.q ? d.width : d.height;
                                d3.O = n4;
                                d2.P = n4;
                                d.z.x = d.r.x - d.p.x >> this.a;
                                d.z.y = d.r.y - d.p.y >> this.a;
                                d.G.x = d.q.x - d.p.x >> this.a;
                                d.G.y = d.q.y - d.p.y >> this.a;
                                d.K.x = d.s.x - d.r.x - d.q.x + d.p.x >> this.a * 2;
                                d.K.y = d.s.y - d.r.y - d.q.y + d.p.y >> this.a * 2;
                                if (a != 0) {
                                    break;
                                }
                                break Label_3331;
                            }
                        }
                    }
                    Label_2448: {
                        if (this.q) {
                            final Point z = d.z;
                            d.z = d.G;
                            d.G = z;
                            final Point c2 = d.C;
                            d.C = d.J;
                            d.J = c2;
                            d.O = n.a(d.t.a - d.x, 0, d.width);
                            d.P = n.a(d.w.a - d.x, 0, d.width);
                            if (a == 0) {
                                break Label_2448;
                            }
                        }
                        d.O = n.a(d.t.b - d.y, 0, d.height);
                        d.P = n.a(d.w.b - d.y, 0, d.height);
                    }
                    d.H.x = d.t.x - d.u.x >> this.a;
                    d.H.y = d.t.y - d.u.y >> this.a;
                    d.I.x = d.v.x - d.t.x >> this.a;
                    d.I.y = d.v.y - d.t.y >> this.a;
                    final int n5 = (d.w.b != d.t.b) ? (d.w.b - d.t.b) : 1;
                    final int n6 = (d.w.a != d.t.a) ? (d.w.a - d.t.a) : 1;
                    final int n7 = this.q ? n6 : n5;
                    d.L.x = (d.w.x - d.x.x - d.t.x + d.u.x) / n7 >> this.a;
                    d.L.y = (d.w.y - d.x.y - d.t.y + d.u.y) / n7 >> this.a;
                    d.M.x = (d.y.x - d.w.x - d.v.x + d.t.x) / n7 >> this.a;
                    d.M.y = (d.y.y - d.w.y - d.v.y + d.t.y) / n7 >> this.a;
                    Label_3066: {
                        switch (d.Q) {
                            case 36:
                            case 40:
                            case 48: {
                                d.D.x = d.p.x;
                                d.D.y = d.p.y;
                                d.E.x = d.q.x;
                                d.E.y = d.q.y;
                                if (a != 0) {
                                    break Label_3066;
                                }
                                break;
                            }
                            case 20: {
                                d.D.x = d.u.x;
                                d.D.y = d.u.y;
                                d.E.x = d.t.x;
                                d.E.y = d.t.y;
                                if (a != 0) {
                                    break Label_3066;
                                }
                                break;
                            }
                            case 24: {
                                d.D.x = d.t.x;
                                d.D.y = d.t.y;
                                d.E.x = d.v.x;
                                d.E.y = d.v.y;
                                break;
                            }
                        }
                    }
                    final int n8 = this.q ? (this.b - d.height) : (this.b - d.width);
                    final Point e = d.E;
                    e.x -= (n8 + 1) * d.I.x;
                    final Point e2 = d.E;
                    e2.y -= (n8 + 1) * d.I.y;
                    Label_3291: {
                        if (this.p) {
                            d.F.x = d.w.x;
                            d.F.y = d.w.y;
                            if (a == 0) {
                                break Label_3291;
                            }
                        }
                        d.F.x = d.x.x;
                        d.F.y = d.x.y;
                    }
                    if (this.q && d.Q != 0) {
                        final a r2 = d.r;
                        d.r = d.q;
                        d.q = r2;
                    }
                }
                ++n2;
            }
            if (n2 >= this.m.length) {
                return;
            }
            continue;
        }
    }
    
    protected void f() {
        int n = 0;
        while (true) {
            Label_0022: {
                if (q.a == 0) {
                    break Label_0022;
                }
                this.a(this.l[n]);
                ++n;
            }
            if (n >= this.l.length) {
                return;
            }
            continue;
        }
    }
    
    boolean g() {
        if (!this.c()) {
            return false;
        }
        this.d();
        this.f();
        this.a();
        this.i();
        this.e();
        return this.h();
    }
    
    boolean h() {
        final int a = q.a;
        final float a2 = this.i.a();
        final float b = this.i.b();
        final boolean b2 = (this.q ? (b < 0.0f) : (a2 < 0.0f)) ^ !this.h.d();
        final boolean b3 = this.p ? (!b2) : b2;
        int n = 0;
        while (true) {
            while (true) {
                Label_0317: {
                    if (a == 0) {
                        break Label_0317;
                    }
                    final int q = this.m[n].Q;
                    Label_0314: {
                        if (q == 1) {
                            this.a(new b(this.m[n], true), true);
                            if (a == 0) {
                                break Label_0314;
                            }
                        }
                        if (this.m[n].Q == 2) {
                            this.a(new b(this.m[n], true), false);
                            if (a == 0) {
                                break Label_0314;
                            }
                        }
                        final b b4 = new b(this.m[n], true);
                        if (b4.height != 0) {
                            this.a(b4, b3);
                        }
                        final c c = new c(this.m[n]);
                        if ((!this.q && c.height != 0) || (this.q && c.width != 0)) {
                            this.a(c, !b2);
                        }
                        final b b5 = new b(this.m[n], false);
                        if (b5.height != 0) {
                            this.a(b5, !b3);
                        }
                    }
                    ++n;
                }
                if (n < this.m.length) {
                    continue;
                }
                break;
            }
            int q;
            final int n2 = q = 1;
            if (a == 0) {
                return n2 != 0;
            }
            continue;
        }
    }
    
    protected float a(final a a) {
        final j j = new j(a.a, a.b);
        this.g.c(j);
        final j i = new j(0.0f, 0.0f);
        final float a2 = this.i.a(j, i);
        this.h.b(i);
        a.x = (int)(i.a * this.t);
        a.y = (int)(i.b * this.u);
        return a2;
    }
    
    static final boolean a(int n) {
        return (~n & --n) == n;
    }
    
    protected void i() {
        final int a = q.a;
        int n = 0;
        while (true) {
            Label_0442: {
                if (a == 0) {
                    break Label_0442;
                }
                Label_0439: {
                    if (this.m[n].Q != 0) {
                        if (this.m[n].t == this.m[n].w) {
                            this.m[n].Q = 0;
                            if (a == 0) {
                                break Label_0439;
                            }
                        }
                        if (this.m[n].t.b == this.m[n].w.b && this.m[n].t.b % this.b == 0) {
                            this.m[n].Q = 0;
                            if (a == 0) {
                                break Label_0439;
                            }
                        }
                        if (this.m[n].t.a == this.m[n].w.a && this.m[n].t.a % this.b == 0) {
                            this.m[n].Q = 0;
                            if (a == 0) {
                                break Label_0439;
                            }
                        }
                        if (!this.q) {
                            if ((this.m[n].t.b % this.b != 0 || this.m[n].t.a % this.b != 0 || (this.m[n].Q & 0x10) == 0x0) && (this.m[n].w.b % this.b != 0 || this.m[n].w.a % this.b != 0 || (this.m[n].Q & 0x20) == 0x0)) {
                                break Label_0439;
                            }
                            this.m[n].Q = 48;
                            if (a == 0) {
                                break Label_0439;
                            }
                        }
                        if (this.q && ((this.m[n].t.b % this.b == 0 && this.m[n].t.a % this.b == 0 && (this.m[n].Q & 0x4) != 0x0) || (this.m[n].w.b % this.b == 0 && this.m[n].w.a % this.b == 0 && (this.m[n].Q & 0x8) != 0x0))) {
                            this.m[n].Q = 12;
                        }
                    }
                }
                ++n;
            }
            if (n >= this.m.length) {
                return;
            }
            continue;
        }
    }
    
    void a(final b b, final boolean b2) {
        final int a = q.a;
        int n = b.y * this.g.c.width + b.x;
        if (!this.h.d() && !this.h.c() && !b2) {
            int n2 = 0;
        Label_0091_Outer:
            while (true) {
                Label_0124: {
                    if (a == 0) {
                        break Label_0124;
                    }
                    int n3 = n;
                    int n4 = 0;
                    while (true) {
                        while (true) {
                            Label_0094: {
                                if (a == 0) {
                                    break Label_0094;
                                }
                                this.g.b[n3] = this.h.b[0];
                                ++n4;
                                ++n3;
                            }
                            if (n4 < b.width) {
                                continue Label_0091_Outer;
                            }
                            break;
                        }
                        n += this.g.c.width;
                        if (a != 0) {
                            continue;
                        }
                        break;
                    }
                    ++n2;
                }
                if (n2 >= b.height) {
                    return;
                }
                continue;
            }
        }
        else {
            final int n5 = (b2 && this.h.d()) ? (this.h.c.width * this.h.c.height / 2) : 0;
            int n6 = 0;
        Label_0235_Outer:
            while (true) {
                Label_0393: {
                    if (a == 0) {
                        break Label_0393;
                    }
                    int x = b.a.x;
                    int y = b.a.y;
                    int n7 = n;
                    int n8 = 0;
                    int n10 = 0;
                    while (true) {
                        while (true) {
                            Label_0287: {
                                if (a == 0) {
                                    break Label_0287;
                                }
                                final int n9 = n5 + (((y & this.s) | (x & this.r)) >>> 12);
                                this.g.b[n7] = this.h.b[n10];
                                ++n7;
                                x += b.c.x;
                                y += b.c.y;
                                ++n8;
                            }
                            if (n8 < b.width) {
                                continue Label_0235_Outer;
                            }
                            break;
                        }
                        final Point a2 = b.a;
                        a2.x += b.b.x;
                        final Point a3 = b.a;
                        a3.y += b.b.y;
                        final Point c = b.c;
                        c.x += b.d.x;
                        final Point c2 = b.c;
                        c2.y += b.d.y;
                        n10 = n + this.g.c.width;
                        if (a != 0) {
                            continue;
                        }
                        break;
                    }
                    n = n10;
                    ++n6;
                }
                if (n6 >= b.height) {
                    return;
                }
                continue;
            }
        }
    }
    
    void a(final c c, final boolean b) {
        final int a = q.a;
        int n = c.y * this.g.c.width + c.x;
        int n2 = 0;
        Label_0069: {
            if (!this.q) {
                n2 = n + c.width - 1;
                if (a == 0) {
                    break Label_0069;
                }
            }
            n2 = n + (c.height - 1) * this.g.c.width;
        }
        int n3 = 0;
        final boolean b2 = false;
        int n4 = -1;
        int n5 = -1;
        Label_0153: {
            if (this.h.d()) {
                n3 = this.h.c.width * this.h.c.height / 2;
                if (a == 0) {
                    break Label_0153;
                }
            }
            if (!this.h.c()) {
                n4 = (b ? -1 : 0);
                n5 = (b ? 0 : -1);
            }
        }
        final int n6 = b ? n3 : b2;
        final int n7 = b ? b2 : n3;
        final int n8 = this.q ? c.x : c.y;
        final int n9 = n8 + (this.q ? c.width : c.height);
        final int n10 = this.q ? this.g.c.width : 1;
        final int n11 = this.q ? 1 : this.g.c.width;
        int n12 = n8;
        int n13;
        int n14;
        int n15;
        int n16 = 0;
        int n17;
        int n18;
        int q = 0;
        int n19;
        int n20;
        int n21 = 0;
        Point a2;
        Point a3;
        Point e;
        Point e2;
        Point c2;
        Point c3;
        Point g;
        Point g2;
        Label_0386_Outer:Label_0509_Outer:Label_0533_Outer:
        while (true) {
            Label_0768: {
                if (a == 0) {
                    break Label_0768;
                }
                n13 = c.a.x;
                n14 = c.a.y;
                n15 = n;
                Label_0354: {
                    if (!this.q) {
                        n16 = n.a(this.o[n12] - c.x, 0, c.width);
                        if (a == 0) {
                            break Label_0354;
                        }
                    }
                    n16 = n.a(this.n[n12] - c.y, 0, c.height);
                }
                n17 = 0;
                while (true) {
                    while (true) {
                        Label_0442: {
                            if (a == 0) {
                                break Label_0442;
                            }
                            n18 = n6 + (((n14 & this.s) | (n13 & this.r)) >>> 12 & n4);
                            this.g.b[n15] = this.h.b[q];
                            n15 += n10;
                            n13 += c.c.x;
                            n14 += c.c.y;
                            ++n17;
                        }
                        if (n17 < n16) {
                            continue Label_0386_Outer;
                        }
                        break;
                    }
                    n13 = c.e.x;
                    n14 = c.e.y;
                    n15 = n2;
                    q = (this.q ? 1 : 0);
                    if (a != 0) {
                        continue Label_0509_Outer;
                    }
                    break;
                }
                n19 = ((q == 0) ? (c.width - 1) : (c.height - 1));
                while (true) {
                    while (true) {
                        Label_0589: {
                            if (a == 0) {
                                break Label_0589;
                            }
                            n20 = n7 + (((n14 & this.s) | (n13 & this.r)) >>> 12 & n5);
                            this.g.b[n15] = this.h.b[n21];
                            n15 -= n10;
                            n13 -= c.g.x;
                            n14 -= c.g.y;
                            --n19;
                        }
                        if (n19 >= n16) {
                            continue Label_0533_Outer;
                        }
                        break;
                    }
                    a2 = c.a;
                    a2.x += c.b.x;
                    a3 = c.a;
                    a3.y += c.b.y;
                    e = c.e;
                    e.x += c.f.x;
                    e2 = c.e;
                    e2.y += c.f.y;
                    c2 = c.c;
                    c2.x += c.d.x;
                    c3 = c.c;
                    c3.y += c.d.y;
                    g = c.g;
                    g.x += c.h.x;
                    g2 = c.g;
                    g2.y += c.h.y;
                    n += n11;
                    n21 = n2 + n11;
                    if (a != 0) {
                        continue;
                    }
                    break;
                }
                n2 = n21;
                ++n12;
            }
            if (n12 >= n9) {
                return;
            }
            continue;
        }
    }
    
    protected final int b(final int n) {
        return n + this.b - 1 & ~(this.b - 1);
    }
}
