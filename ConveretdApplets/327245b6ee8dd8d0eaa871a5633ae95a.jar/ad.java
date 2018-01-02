import java.util.Vector;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class ad extends m
{
    int a;
    w b;
    f c;
    z d;
    ac e;
    int f;
    boolean g;
    boolean h;
    ai[] i;
    int j;
    int k;
    ai l;
    ai m;
    boolean n;
    ai[] p;
    int[] q;
    int r;
    ai s;
    int t;
    int u;
    int v;
    ae w;
    boolean x;
    int y;
    Point z;
    Point aa;
    Point ab;
    Point ac;
    Point ad;
    Point ae;
    Point af;
    Point ag;
    Point ah;
    Point ai;
    Point aj;
    Point ak;
    Point al;
    int am;
    int an;
    int ao;
    int ap;
    int[] aq;
    
    ad(final int a) {
        this.h = false;
        this.n = false;
        super.a = 2;
        this.a = a;
        this.b = new w();
        this.c = new f();
        this.w = new ae();
        this.g = false;
        this.ad = new Point(0, 0);
        this.ae = new Point(0, 0);
        this.af = new Point(0, 0);
        this.z = new Point(0, 0);
        this.aa = new Point(0, 0);
        this.ab = new Point(0, 0);
        this.ac = new Point(0, 0);
        this.ag = new Point(0, 0);
        this.ah = new Point(0, 0);
        this.ai = new Point(0, 0);
        this.aj = new Point(0, 0);
        this.ak = new Point(0, 0);
        this.al = new Point(0, 0);
        this.i = new ai[1];
        this.p = new ai[1];
        this.q = new int[1];
    }
    
    int a(final x b) {
        this.b = b;
        super.d = b.e();
        this.c = b.k();
        this.c(20);
        this.aq[this.ao++] = 0;
        this.aq[this.ao++] = 16;
        this.c();
        this.b();
        return super.d;
    }
    
    void b() {
        final boolean l = c.l;
        this.b.c();
        this.u = this.b.a(4);
        this.v = this.b.a(4);
        while (true) {
            this.c(8);
            final int n;
            Label_0339: {
                if (this.b.a(1) == 0) {
                    this.aq[this.ao++] = 0;
                    final int a = this.b.a(5);
                    this.aq[this.ao++] = a;
                    n = a;
                    if (l) {
                        break Label_0339;
                    }
                    if (n == 0) {
                        break;
                    }
                    if ((a & 0x1) != 0x0) {
                        final int a2 = this.b.a(5);
                        this.aq[this.ao++] = this.b.b(a2);
                        this.aq[this.ao++] = this.b.b(a2);
                    }
                    if ((a & 0x2) != 0x0) {
                        this.aq[this.ao++] = this.b.a(this.u);
                    }
                    if ((a & 0x4) != 0x0) {
                        this.aq[this.ao++] = this.b.a(this.u);
                    }
                    if ((a & 0x8) != 0x0) {
                        this.aq[this.ao++] = this.b.a(this.v);
                    }
                    if ((a & 0x10) == 0x0) {
                        continue;
                    }
                    this.c();
                    this.b.c();
                    this.u = this.b.a(4);
                    this.v = this.b.a(4);
                    if (!l) {
                        continue;
                    }
                }
                this.aq[this.ao++] = 1;
                this.b.a(1);
            }
            if (n != 0) {
                this.aq[this.ao++] = 1;
                final int n2 = this.b.a(4) + 2;
                if (this.b.a(1) != 0) {
                    this.aq[this.ao++] = this.b.b(n2);
                    this.aq[this.ao++] = this.b.b(n2);
                    if (!l) {
                        continue;
                    }
                }
                if (this.b.a(1) != 0) {
                    this.aq[this.ao++] = 0;
                    this.aq[this.ao++] = this.b.b(n2);
                    if (!l) {
                        continue;
                    }
                }
                this.aq[this.ao++] = this.b.b(n2);
                this.aq[this.ao++] = 0;
                if (!l) {
                    continue;
                }
            }
            this.aq[this.ao++] = 0;
            final int n3 = this.b.a(4) + 2;
            this.aq[this.ao++] = this.b.b(n3);
            this.aq[this.ao++] = this.b.b(n3);
            this.aq[this.ao++] = this.b.b(n3);
            this.aq[this.ao++] = this.b.b(n3);
        }
    }
    
    boolean c() {
        final boolean l = c.l;
        int n = this.b.d();
        if (n == 255 && this.a >= 2) {
            n = this.b.e();
        }
        this.c((this.aq[this.ao++] = n) * 24 + 1);
        int n2 = 1;
        int n3;
        int d = 0;
        int n4 = 0;
        while (true) {
            while (true) {
                Label_0535: {
                    if (!l) {
                        break Label_0535;
                    }
                    n3 = this.b.d();
                    this.aq[this.ao++] = n3;
                    Label_0532: {
                        if ((d & n4) != 0x0) {
                            final ac i = this.b.l();
                            this.aq[this.ao++] = i.a;
                            this.aq[this.ao++] = i.b;
                            this.aq[this.ao++] = i.c;
                            this.aq[this.ao++] = i.d;
                            this.aq[this.ao++] = i.e;
                            this.aq[this.ao++] = i.f;
                            final int d2 = this.b.d();
                            this.aq[this.ao++] = d2;
                            int n5 = 0;
                            while (true) {
                                Label_0325: {
                                    if (!l) {
                                        break Label_0325;
                                    }
                                    this.aq[this.ao++] = this.b.d();
                                    this.aq[this.ao++] = this.b.c(this.a);
                                    ++n5;
                                }
                                if (n5 < d2) {
                                    continue;
                                }
                                break;
                            }
                        }
                        else {
                            if ((n3 & 0x40) != 0x0) {
                                this.aq[this.ao++] = this.b.e();
                                final ac j = this.b.l();
                                this.aq[this.ao++] = j.a;
                                this.aq[this.ao++] = j.b;
                                this.aq[this.ao++] = j.c;
                                this.aq[this.ao++] = j.d;
                                this.aq[this.ao++] = j.e;
                                this.aq[this.ao++] = j.f;
                                if (!l) {
                                    break Label_0532;
                                }
                            }
                            this.aq[this.ao++] = this.b.c(this.a);
                        }
                    }
                    ++n2;
                }
                if (n2 <= n) {
                    continue;
                }
                break;
            }
            n3 = (d = this.b.d());
            n4 = 255;
            if (l) {
                continue;
            }
            break;
        }
        if (d == n4 && this.a >= 2) {
            n3 = this.b.e();
        }
        this.c((this.aq[this.ao++] = n3) * 2);
        int n6 = 1;
        while (true) {
            Label_0661: {
                if (!l) {
                    break Label_0661;
                }
                this.aq[this.ao++] = this.b.e();
                this.aq[this.ao++] = this.b.c(this.a);
                ++n6;
            }
            if (n6 > n3) {
                return true;
            }
            continue;
        }
    }
    
    void a(final b b, final af af, final int n) {
        b.b.a(new z(b.a, b.b, this, super.d << 16 | af.d, af, n));
    }
    
    void a(final z z) {
        if (this.aq == null) {
            return;
        }
        this.a(z, z.j);
        this.d();
    }
    
    void a(final z d, final ac e) {
        this.d = d;
        this.e = e;
        this.f = d.b << 16;
        this.ap = 0;
        this.ad.setLocation(0, 0);
        this.ae.setLocation(0, 0);
        this.af.setLocation(0, 0);
        this.z.setLocation(0, 0);
        this.e.a(this.z, this.aa);
        this.ab.setLocation(0, 0);
        this.ac.setLocation(0, 0);
        this.ag.setLocation(0, 0);
        this.ah.setLocation(0, 0);
        this.ai.setLocation(0, 0);
        this.aj.setLocation(0, 0);
        this.h = false;
        this.n = false;
        final ai ai = null;
        this.m = ai;
        this.l = ai;
        this.r = 0;
        this.j = 0;
        this.k = 0;
    }
    
    void d() {
        final boolean l = c.l;
        while (true) {
            final int n2;
            Label_0280: {
                if (this.aq[this.ap++] == 0) {
                    final int n = n2 = this.aq[this.ap++];
                    if (l) {
                        break Label_0280;
                    }
                    if (n2 == 0) {
                        break;
                    }
                    if ((n & 0x1) != 0x0) {
                        this.a(this.aq[this.ap++], this.aq[this.ap++]);
                    }
                    if ((n & 0x2) != 0x0) {
                        this.j = this.aq[this.ap++];
                    }
                    if ((n & 0x4) != 0x0) {
                        this.k = this.aq[this.ap++];
                    }
                    if ((n & 0x8) != 0x0) {
                        this.r = this.aq[this.ap++];
                    }
                    if ((n & 0x10) != 0x0) {
                        ++this.f;
                        this.e();
                    }
                    if ((n & 0x6) != 0x0) {
                        this.a(this.i[this.j], this.i[this.k]);
                    }
                    if ((n & 0x9) == 0x0) {
                        continue;
                    }
                    this.b(this.q[this.r], this.p[this.r]);
                    if (!l) {
                        continue;
                    }
                }
                final int n3 = this.aq[this.ap++];
            }
            if (n2 != 0) {
                this.b(this.aq[this.ap++], this.aq[this.ap++]);
                if (!l) {
                    continue;
                }
            }
            this.a(this.aq[this.ap++], this.aq[this.ap++], this.aq[this.ap++], this.aq[this.ap++]);
        }
        if (this.n) {
            this.f();
        }
    }
    
    void e() {
        final boolean l = c.l;
        final int n = this.aq[this.ap++];
        while (true) {
            Label_1030: {
                if (n == 0) {
                    break Label_1030;
                }
                this.i = new ai[n + 1];
                int n2 = 1;
                while (true) {
                    Label_1025: {
                        if (!l) {
                            break Label_1025;
                        }
                        final int n3 = this.aq[this.ap++];
                        final int n4;
                        final int b = n4;
                        ai ai = null;
                        Label_1004: {
                            if ((b & 0x10) != 0x0) {
                                final ac ac = new ac();
                                ac.a = this.aq[this.ap++];
                                ac.b = this.aq[this.ap++];
                                ac.c = this.aq[this.ap++];
                                ac.d = this.aq[this.ap++];
                                ac.e = this.aq[this.ap++];
                                ac.f = this.aq[this.ap++];
                                final int n5 = this.aq[this.ap++];
                                final int[] array = new int[n5];
                                final int[] array2 = new int[n5];
                                int n6 = 0;
                                while (true) {
                                    Label_0287: {
                                        if (!l) {
                                            break Label_0287;
                                        }
                                        array2[n6] = this.aq[this.ap++];
                                        array[n6] = this.aq[this.ap++];
                                        ++n6;
                                    }
                                    if (n6 < n5) {
                                        continue;
                                    }
                                    break;
                                }
                                Label_0322: {
                                    if (b == 16) {
                                        ai = new bi();
                                        if (!l) {
                                            break Label_0322;
                                        }
                                    }
                                    ai = new bj();
                                }
                                ai.a(n5, array, array2, ac, this.e);
                                if (this.d.n == null) {
                                    break Label_1004;
                                }
                                this.d.n.b(ai);
                                if (!l) {
                                    break Label_1004;
                                }
                            }
                            if ((b & 0x40) != 0x0) {
                                final int n7 = this.aq[this.ap++];
                                final ac ac2 = new ac();
                                ac2.a = this.aq[this.ap++];
                                ac2.b = this.aq[this.ap++];
                                ac2.c = this.aq[this.ap++];
                                ac2.d = this.aq[this.ap++];
                                ac2.e = this.aq[this.ap++];
                                ac2.f = this.aq[this.ap++];
                                ai = new bk();
                                final m a = super.b.a(n7);
                                if (a == null || a.a != 1) {
                                    break Label_1004;
                                }
                                ai.af = (l)a;
                                if (this.d.ab == null) {
                                    this.d.ab = new Vector(1, 5);
                                }
                                if (this.d.ab.indexOf(ai.af) == -1) {
                                    this.d.ab.addElement(ai.af);
                                    ai.af.e();
                                }
                                ai.af.d();
                                ai.b = b;
                                final ac ac4;
                                final ac ac3 = ac4 = new ac(this.e);
                                ac4.e <<= 16;
                                final ac ac5 = ac3;
                                ac5.f <<= 16;
                                final ac ac6 = ac2;
                                ac6.e <<= 16;
                                final ac ac7 = ac2;
                                ac7.f <<= 16;
                                final ac a2 = ac.a(ac2, ac3);
                                final ac ac8 = new ac();
                                ac8.a(16384, 16384);
                                final ac a3 = ac.a(a2, ac8);
                                ai.ae = a3.b();
                                a3.a();
                                final float g = a3.g;
                                if ((this.d.a.a7 == 1 || !this.d.ac) && ((g != 0.0f && g != 180.0f) || a3.a < 65535 || a3.a > 65537)) {
                                    ai.ag = true;
                                }
                                ai.ah = ai.ae.a;
                                ai.ai = ai.ae.b;
                                if (this.d.n != null && this.d.n.j != 0) {
                                    ai.ad = this.d.n;
                                }
                                if (ai.ad != null || ai.af.l) {
                                    ai.r = true;
                                }
                                if (ai.ae.b != 0 || ai.ae.a < 65535 || ai.ae.a > 65537 || ai.ad != null) {
                                    break Label_1004;
                                }
                                ai.aj = true;
                                if (!l) {
                                    break Label_1004;
                                }
                            }
                            ai = new ai(this.aq[this.ap++]);
                            if (this.d.n != null) {
                                this.d.n.a(ai);
                            }
                        }
                        this.i[n2] = ai;
                        this.d.aa.addElement(ai);
                        ++n2;
                    }
                    if (n2 <= n) {
                        continue;
                    }
                    break;
                }
            }
            final int n4;
            int n2 = n4 = this.aq[this.ap++];
            if (!l) {
                if (n4 != 0) {
                    this.p = new ai[n2 + 1];
                    this.q = new int[n2 + 1];
                    int n8 = 1;
                    while (true) {
                        Label_1191: {
                            if (!l) {
                                break Label_1191;
                            }
                            this.q[n8] = this.e.a(this.aq[this.ap++]);
                            final ai ai2 = new ai(this.aq[this.ap++]);
                            ai2.i = true;
                            if (this.d.n != null) {
                                this.d.n.a(ai2);
                            }
                            this.p[n8] = ai2;
                            this.d.aa.addElement(ai2);
                            ++n8;
                        }
                        if (n8 <= n2) {
                            continue;
                        }
                        break;
                    }
                }
                return;
            }
            continue;
        }
    }
    
    final void a(final int n, final ai s) {
        this.x = false;
        this.t = Math.max(4, n);
        this.s = s;
        final Point ad = this.ad;
        final Point ad2 = this.ad;
        final int n2 = Integer.MIN_VALUE;
        ad2.y = n2;
        ad.x = n2;
    }
    
    final void a(final ae ae) {
        this.ad.x = ae.e;
        this.ad.y = ae.f;
        if (ae.a == ae.e && ae.b == ae.f && ae.a == ae.c && ae.b == ae.d) {
            return;
        }
        this.b(this.c(ae));
    }
    
    final void b(final ae ae) {
        final boolean l = c.l;
        if (!ae.g && this.y < 5) {
            final int b = ae.b();
            if (b > 6 && 2 * b > ac.b(ae.a - ae.e, ae.b - ae.f)) {
                final ae ae2 = new ae(ae);
                final ae a = ae2.a(32768);
                ++this.y;
                this.b(ae2);
                this.b(a);
                --this.y;
                return;
            }
        }
        final int n = this.t / 2;
        int n2 = ae.d - ae.b;
        int n3 = ae.a - ae.c;
        if (n2 == 0 && n3 == 0) {
            n2 = ae.f - ae.b;
            n3 = ae.a - ae.e;
        }
        final int c = ac.c(n2, n3);
        if (c > 0) {
            final int n4 = (n << 16) / c;
            n2 = (int)(n4 * n2 + 32768L >> 16);
            n3 = (int)(n4 * n3 + 32768L >> 16);
        }
        int n5 = 0;
        int n6 = 0;
        Label_0360: {
            if (ae.g) {
                n5 = n2;
                n6 = n3;
                if (!l) {
                    break Label_0360;
                }
            }
            n5 = ae.f - ae.d;
            n6 = ae.c - ae.e;
            if (n5 == 0 && n6 == 0) {
                n5 = ae.f - ae.b;
                n6 = ae.a - ae.e;
            }
            final int c2 = ac.c(n5, n6);
            if (c2 > 0) {
                final int n7 = (n << 16) / c2;
                n5 = (int)(n7 * n5 + 32768L >> 16);
                n6 = (int)(n7 * n6 + 32768L >> 16);
            }
        }
        final Point point = new Point(ae.a + n2, ae.b + n3);
        final Point point2 = new Point(ae.e + n5, ae.f + n6);
        final Point point3 = new Point(ae.a - n2, ae.b - n3);
        final Point point4 = new Point(ae.e - n5, ae.f - n6);
        Label_0517: {
            if (ae.g) {
                this.a(point2, point);
                this.a(point3, point4);
                if (!l) {
                    break Label_0517;
                }
            }
            this.a(new ae(ae, point, point2).a(), true, 0);
            this.a(new ae(ae, point3, point4), true, 0);
        }
        Label_0632: {
            if (!this.x) {
                this.ai.x = point.x;
                this.ai.y = point.y;
                this.aj.x = ae.a;
                this.aj.y = ae.b;
                this.ah.x = point3.x;
                this.ah.y = point3.y;
                this.x = true;
                if (!l) {
                    break Label_0632;
                }
            }
            this.a(point, this.af, this.ag);
            this.a(this.ae, point3, this.ag);
        }
        this.af.x = point2.x;
        this.af.y = point2.y;
        this.ag.x = ae.e;
        this.ag.y = ae.f;
        this.ae.x = point4.x;
        this.ae.y = point4.y;
    }
    
    final void a(final Point point, final Point point2, final Point point3) {
        final boolean l = c.l;
        if (ac.b(point.x - point2.x, point.y - point2.y) > 4) {
            double atan2 = Math.atan2(point.y - point3.y, point.x - point3.x);
            final double atan3 = Math.atan2(point2.y - point3.y, point2.x - point3.x);
            double n = 0.0;
            double n2 = 0.0;
            double n3;
            while (true) {
                Label_0097: {
                    if (!l) {
                        break Label_0097;
                    }
                    atan2 = n + n2;
                }
                if (atan2 < atan3) {
                    continue;
                }
                n3 = (n = atan2 - atan3);
                n2 = 0.10000000149011612;
                if (l) {
                    continue;
                }
                break;
            }
            if (n > n2 && n3 <= 3.1415926535898) {
                final double n4 = this.t / 2;
                int n5 = (int)(n4 * n3) / 3;
                final Point point4 = new Point(point.x, point.y);
                final Point location = new Point(0, 0);
                if (n5 > 1) {
                    if (n5 > 16) {
                        n5 = 16;
                    }
                    final double n6 = -n3 / n5;
                    double n7 = atan2 + n6;
                    --n5;
                    while (true) {
                        Label_0283: {
                            if (!l) {
                                break Label_0283;
                            }
                            location.x = (int)(n4 * Math.cos(n7)) + point3.x;
                            location.y = (int)(n4 * Math.sin(n7)) + point3.y;
                            this.a(point4, location);
                            point4.setLocation(location);
                            n7 += n6;
                        }
                        if (n5-- > 0) {
                            continue;
                        }
                        break;
                    }
                }
                this.a(point4, point2);
                return;
            }
        }
        this.a(point, point2);
    }
    
    final void f() {
        if (!this.x) {
            if (this.ad.x != Integer.MIN_VALUE) {
                final int n = this.t / 2;
                final Point point = new Point(this.ad.x, this.ad.y - n);
                final Point point2 = new Point(this.ad.x, this.ad.y + this.t - n);
                this.a(point, point2, this.ad);
                this.a(point2, point, this.ad);
            }
            return;
        }
        if (this.aj.x == this.ag.x && this.aj.y == this.ag.y) {
            this.a(this.ai, this.af, this.ag);
            this.a(this.ae, this.ah, this.ag);
            return;
        }
        this.a(this.ai, this.ah, this.aj);
        this.a(this.ae, this.af, this.ag);
    }
    
    void a(final ae ae, final boolean b, int n) {
        final boolean l = c.l;
        final int c = ae.c;
        int d = ae.d;
        int a = 0;
        int b2 = 0;
        int e = 0;
        int f = 0;
        Label_0081: {
            if (ae.b <= ae.f) {
                a = ae.a;
                b2 = ae.b;
                e = ae.e;
                f = ae.f;
                if (!l) {
                    break Label_0081;
                }
            }
            a = ae.e;
            b2 = ae.f;
            e = ae.a;
            f = ae.b;
        }
        if (!ae.g) {
            Label_0232: {
                if (d < b2 || d > f) {
                    if (d < b2 && b2 - d < 3) {
                        d = b2;
                        if (!l) {
                            break Label_0232;
                        }
                    }
                    if (d > f && d - f < 3) {
                        d = f;
                        if (!l) {
                            break Label_0232;
                        }
                    }
                    if (++n <= 16) {
                        final int n2 = ae.b - d;
                        final int n3 = ae.b - 2 * d + ae.f;
                        final ae ae2 = new ae(ae);
                        final ae a2 = ae2.a((n2 << 16) / n3);
                        this.a(ae2, b, n);
                        this.a(a2, b, n);
                    }
                    return;
                }
            }
            if (f - b2 > 256) {
                if (++n <= 16) {
                    final ae ae3 = new ae(ae);
                    final ae a3 = ae3.a(32768);
                    this.a(ae3, b, n);
                    this.a(a3, b, n);
                }
                return;
            }
        }
        if (b2 == f) {
            return;
        }
        final bd z = new bd();
        z.g = ae.g;
        z.c = c;
        z.d = d;
        z.a = a;
        z.b = b2;
        z.e = e;
        z.f = f;
        z.b = ((ae.b <= ae.f) ? 1 : -1);
        Label_0413: {
            if (b) {
                z.e = this.s;
                if (!l) {
                    break Label_0413;
                }
            }
            z.e = this.l;
            z.f = this.m;
        }
        z.a = this.d.z;
        this.d.z = z;
    }
    
    final void a(final Point point, final Point point2) {
        if (point.y == point2.y) {
            return;
        }
        final bd z = new bd();
        Label_0059: {
            if (point.y > point2.y) {
                z.b = -1;
                z.a(point2, point);
                if (!c.l) {
                    break Label_0059;
                }
            }
            z.b = 1;
            z.a(point, point2);
        }
        z.e = this.s;
        z.a = this.d.z;
        this.d.z = z;
    }
    
    void a(final int x, final int y) {
        this.z.x = x;
        this.z.y = y;
        this.e.a(this.z, this.aa);
    }
    
    void b(final int n, final int n2) {
        this.w.g = true;
        final Point z = this.z;
        z.x += n;
        final Point z2 = this.z;
        z2.y += n2;
        this.e.a(this.z, this.ac);
        this.w.a(this.aa, this.ac);
        this.aa.x = this.ac.x;
        this.aa.y = this.ac.y;
        this.g();
    }
    
    void a(final int n, final int n2, final int n3, final int n4) {
        this.w.g = false;
        final Point z = this.z;
        z.x += n;
        final Point z2 = this.z;
        z2.y += n2;
        this.e.a(this.z, this.ab);
        final Point z3 = this.z;
        z3.x += n3;
        final Point z4 = this.z;
        z4.y += n4;
        this.e.a(this.z, this.ac);
        this.w.a(this.aa, this.ab, this.ac);
        this.aa.x = this.ac.x;
        this.aa.y = this.ac.y;
        this.g();
    }
    
    void g() {
        if (this.n) {
            this.a(this.w);
        }
        if (this.h) {
            this.a(this.w, false, 0);
        }
    }
    
    void a(final ai l, final ai m) {
        this.l = l;
        this.m = m;
        if (this.l == null && this.m != null) {
            this.l = this.m;
            this.m = null;
        }
        this.h = (this.l != null);
    }
    
    void b(final int n, final ai ai) {
        if (this.n) {
            this.f();
        }
        if (ai != null) {
            this.a(n, ai);
            this.n = true;
            if (!c.l) {
                return;
            }
        }
        this.n = false;
    }
    
    void c(final int n, final int n2) {
        this.c(4);
        this.aq[this.ao++] = 0;
        this.aq[this.ao++] = 1;
        this.aq[this.ao++] = n;
        this.aq[this.ao++] = n2;
        this.ak.x = n;
        this.ak.y = n2;
        this.al.x = n;
        this.al.y = n2;
        this.c.a(new f(n - this.am, n2 - this.am, n + this.am, n2 + this.am));
    }
    
    void b(final int n) {
        this.c(15);
        this.aq[this.ao++] = 0;
        this.aq[this.ao++] = 16;
        this.aq[this.ao++] = 1;
        this.aq[this.ao++] = 65;
        this.aq[this.ao++] = n;
        this.aq[this.ao++] = 1310720;
        this.aq[this.ao++] = 0;
        this.aq[this.ao++] = 0;
        this.aq[this.ao++] = 1310720;
        this.aq[this.ao++] = 0;
        this.aq[this.ao++] = 0;
        this.aq[this.ao++] = 0;
        this.aq[this.ao++] = 0;
        this.aq[this.ao++] = 2;
        this.aq[this.ao++] = 1;
    }
    
    void d(final int n, final int n2) {
        this.c(9);
        this.aq[this.ao++] = 0;
        this.aq[this.ao++] = 16;
        this.aq[this.ao++] = 0;
        this.aq[this.ao++] = 1;
        this.aq[this.ao++] = n * 20;
        this.aq[this.ao++] = n2;
        this.aq[this.ao++] = 0;
        this.aq[this.ao++] = 8;
        this.aq[this.ao++] = 1;
        this.am = n * 20;
    }
    
    void e(final int x, final int y) {
        this.c(4);
        this.aq[this.ao++] = 1;
        this.aq[this.ao++] = 1;
        this.aq[this.ao++] = x - this.ak.x;
        this.aq[this.ao++] = y - this.ak.y;
        this.ak.x = x;
        this.ak.y = y;
        this.c.a(new f(x - this.am, y - this.am, x + this.am, y + this.am));
    }
    
    ae c(ae ae) {
        final boolean l = c.l;
        if (ae.g) {
            if (this.t == 4 || this.t == 12) {
                ae = new ae(ae);
                final int n = ae.b - ae.f;
                if (ae.a == ae.e && ((n <= 0) ? (-n) : n) > 12) {
                    final ae ae2 = ae;
                    final ae ae3 = ae;
                    final int n2 = (ae.a & 0xFFFFFFFC) + 2;
                    ae3.e = n2;
                    ae2.a = n2;
                    if (!l) {
                        return ae;
                    }
                }
                if (n != 0) {
                    return ae;
                }
                final int n3 = ae.a - ae.e;
                if (((n3 <= 0) ? (-n3) : n3) <= 12) {
                    return ae;
                }
                final ae ae4 = ae;
                final ae ae5 = ae;
                final int n4 = (ae.b & 0xFFFFFFFC) + 2;
                ae5.f = n4;
                ae4.b = n4;
                if (!l) {
                    return ae;
                }
            }
            if (this.t == 8) {
                ae = new ae(ae);
                final int n5 = ae.b - ae.f;
                if (ae.a == ae.e && ((n5 <= 0) ? (-n5) : n5) > 12) {
                    final ae ae6 = ae;
                    final ae ae7 = ae;
                    final int n6 = ae.a + 2 & 0xFFFFFFFC;
                    ae7.e = n6;
                    ae6.a = n6;
                    if (!l) {
                        return ae;
                    }
                }
                if (n5 == 0) {
                    final int n7 = ae.a - ae.e;
                    if (((n7 <= 0) ? (-n7) : n7) > 12) {
                        final ae ae8 = ae;
                        final ae ae9 = ae;
                        final int n8 = ae.b + 2 & 0xFFFFFFFC;
                        ae9.f = n8;
                        ae8.b = n8;
                    }
                }
            }
        }
        return ae;
    }
    
    void c(final int n) {
        if (this.aq == null) {
            this.an = 50;
            this.ao = 0;
            this.aq = new int[this.an];
        }
        if (this.an - this.ao < n + 2) {
            this.an = ((2 * this.an < n + 2) ? (n + 2) : (2 * this.an));
            final int[] aq = new int[this.an];
            System.arraycopy(this.aq, 0, aq, 0, this.ao);
            this.aq = aq;
        }
    }
    
    static bd a(final int n, final int n2, final int n3, final int n4, final ai ai) {
        final bd bd = new bd();
        final bd a = new bd();
        bd.a = a;
        final Point point = new Point(n, n2);
        final Point point2 = new Point(n, n4);
        bd.a(point, point2);
        final Point point3 = point;
        point2.x = n3;
        point3.x = n3;
        a.a(point, point2);
        final bd bd2 = bd;
        a.e = ai;
        bd2.e = ai;
        return bd;
    }
}
