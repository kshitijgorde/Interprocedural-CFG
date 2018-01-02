import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class y extends z
{
    b a;
    boolean b;
    ah c;
    z[] d;
    z e;
    e f;
    f g;
    f h;
    int i;
    ai j;
    private bd[] k;
    private bd l;
    private bc[] m;
    private int[] n;
    private int[] p;
    bc[] q;
    ai[] r;
    int s;
    int t;
    static int[] u;
    static int[] v;
    static int[] w;
    static int[] x;
    static int[] y;
    static int[] z;
    
    y(final blaze3d blaze3d, final y y, final b a, final int n, final af af, final int n2) {
        super(blaze3d, y, new ad(3), n, af, n2);
        this.b = false;
        this.e = null;
        this.i = 0;
        this.t = 0;
        this.a = a;
        this.f = new e();
        this.d = new z[64];
    }
    
    void b() {
        super.b();
        this.a = null;
        for (int i = 0; i < 64; ++i) {
            z d;
            for (z z = this.d[i]; z != null; z = d) {
                d = z.d;
                z.b();
            }
        }
    }
    
    void a(final int n, final boolean b) {
        if (this.a.d((c)b.a1, this.a.d).ag()) {
            if ((n & 0x2) != 0x0) {
                super.a.as.a((z)null);
            }
            this.a.a(n, b);
        }
    }
    
    int c() {
        if (super.f != 0 && this.a.d((c)b.a0, this.a.d).ag()) {
            return 12;
        }
        return 0;
    }
    
    c a() {
        return this.a;
    }
    
    void a(final z e) {
        e.d = this.d[e.b & 0x3F];
        if (e.d != null) {
            e.d.e = e;
        }
        this.d[e.b & 0x3F] = e;
    }
    
    void a(final z z, final e e) {
        z.a(e);
        if (z.d != null) {
            z.d.e = z.e;
        }
        if (z.e != null) {
            z.e.d = z.d;
        }
        else {
            this.d[z.b & 0x3F] = z.d;
        }
        z.b();
    }
    
    void a(final e e) {
        if (this.a.h != null) {
            e.a(this.a.g.e);
        }
        else {
            for (int i = 0; i < 64; ++i) {
                for (z d = this.d[i]; d != null; d = d.d) {
                    this.a(d, e);
                }
            }
        }
        this.a.d();
    }
    
    void a(final boolean b, ag n) {
        final boolean b2 = b | super.v;
        if (b2) {
            if (!super.r) {
                this.f.a(super.l);
            }
            super.z = null;
            this.j();
            if (super.m == null) {
                super.n = n;
            }
            else {
                super.n = this.a(n != null);
            }
            super.v = false;
        }
        n = super.n;
        super.l.a();
        if (super.r || this.b || super.q != null) {
            if (this.a.h != null) {
                this.a.g.d = super.j;
                this.a.g.a(this.a.h, b2);
                super.l.a(this.a.g.e);
                if (super.z == null) {
                    this.d();
                    super.w = blaze3d.c;
                }
            }
            else {
                for (int i = 0; i < 64; ++i) {
                    for (z d = this.d[i]; d != null; d = d.d) {
                        d.a(b2, n);
                        super.l.a(d.l);
                    }
                }
                super.aa.removeAllElements();
            }
        }
        super.c.f.a(this.f);
        this.f.a();
    }
    
    int a(int n, final ah ah, ah q) {
        ah c = ah;
        if (this.c != null) {
            this.c.a = c;
            c = this.c;
        }
        n = super.a(n, c, q);
        int n2 = -1;
        ah q2 = c;
        int p3 = -1;
        int n3 = 64;
        boolean b;
        do {
            int n4 = Integer.MAX_VALUE;
            b = false;
            for (int i = 0; i < 64; ++i) {
                for (z d = this.d[i]; d != null; d = d.d) {
                    final int n5 = d.b & 0xFFFF;
                    if (n5 > n2) {
                        if (n5 < n3) {
                            if (d.q != null) {
                                q = d.q;
                                q.d = null;
                            }
                            if (p3 != -1 && n5 > p3) {
                                q2 = c;
                                p3 = -1;
                            }
                            n = d.a(n, q2, q);
                            n2 = n5;
                            if (d.q != null) {
                                q = null;
                                p3 = d.p;
                                if (p3 != 0) {
                                    d.q.a = q2;
                                }
                                q2 = d.q;
                            }
                        }
                        else {
                            if (n5 < n4) {
                                n4 = n5;
                            }
                            b = true;
                        }
                    }
                }
            }
            n3 = (n4 + 64 & 0xFFFFFFC0);
        } while (b);
        return n;
    }
    
    void d() {
        if (super.z != null) {
            return;
        }
        super.aa.removeAllElements();
        final bh bh = new bh();
        super.aa.addElement(bh);
        bh.b = 30;
        bh.af = this.a.g.b;
        bh.ae = new ac();
        bh.ah = bh.ae.a;
        bh.ai = bh.ae.b;
        final int a = super.l.a;
        final int b = super.l.b;
        int c = super.l.c;
        int d = super.l.d;
        if (c == a) {
            c = a + 1;
        }
        if (d == b) {
            d = b + 1;
        }
        super.z = ad.a(a, b, c, d, bh);
    }
    
    int e() {
        for (int i = 0; i < 64; ++i) {
            for (z d = this.d[i]; d != null; d = d.d) {
                final int e = d.e();
                if (e > super.w) {
                    super.w = e;
                }
            }
        }
        return super.w;
    }
    
    f f() {
        final f f = new f();
        for (int i = 0; i < 64; ++i) {
            for (z d = this.d[i]; d != null; d = d.d) {
                f.a(d.i.a(d.f()));
            }
        }
        return f;
    }
    
    f g() {
        final f f = new f();
        if (this.a.h != null) {
            final f e = this.a.g.e;
            if (e != null) {
                f.a(e);
            }
        }
        else {
            for (int i = 0; i < 64; ++i) {
                for (z d = this.d[i]; d != null; d = d.d) {
                    f.a(d.g());
                }
            }
        }
        return f;
    }
    
    boolean a(final Point point, final boolean b) {
        if (this.g().b(point)) {
            if (!b) {
                return true;
            }
            if (this.a.h != null) {
                return this.a.d(point.x >> 2, point.y >> 2) != 0.0f;
            }
            for (int i = 0; i < 64; ++i) {
                for (z d = this.d[i]; d != null; d = d.d) {
                    if (d.a(point, b)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    void b(final int i) {
        if (this.i == i) {
            return;
        }
        this.k();
        this.i = i;
        super.aa.removeAllElements();
        this.j = new ai(this.i);
        super.aa.addElement(this.j);
    }
    
    void a(final bd[] array, final f h) {
        this.h = h;
        for (int i = 0; i < 64; ++i) {
            for (z d = this.d[i]; d != null; d = d.d) {
                if (d.l.b(h) && (d.r || d.q != null)) {
                    d.a(array, h);
                }
            }
        }
        if (super.c != null) {
            super.c.a(array, super.z);
        }
        if (this.i != 0) {
            this.a(array, ad.a(this.h.a, this.h.b, this.h.c, this.h.d, this.j));
        }
    }
    
    final void a(final bd[] array, bd a) {
        while (a != null) {
            if (a.b <= this.h.d && a.f > this.h.b) {
                int n = a.b;
                if (n < this.h.b) {
                    n = this.h.b;
                }
                if (n > array.length - 1) {
                    n = array.length - 1;
                }
                a.c = array[n];
                array[n] = a;
            }
            a = a.a;
        }
    }
    
    void a(final l l) {
        this.f.c();
        if (!this.f.b()) {
            final blaze3d a = super.a;
            a.ab |= 0x8;
            this.a(l, true);
        }
    }
    
    void a(final l l, final boolean b) {
        this.a(1, null, (ah)null);
        if (this.q == null) {
            this.q = new bc[10000];
            for (int i = 0; i < 10000; ++i) {
                this.q[i] = new bc();
            }
        }
        while (this.f.a.size() != 0 || !b) {
            if (!b) {
                this.g = new f(0, 0, l.c, l.d);
            }
            else {
                this.g = new f(this.f.a.elementAt(0));
                this.g.a = Math.max((this.g.a >> 2) - 2, 0);
                this.g.c = Math.min((this.g.c >> 2) + 2, l.c);
                this.g.b = Math.max((this.g.b >> 2) - 2, 0);
                this.g.d = Math.min((this.g.d >> 2) + 2, l.d);
                if (this.g.a >= this.g.c || this.g.b >= this.g.d) {
                    this.g.a();
                }
            }
            if (!this.g.b()) {
                this.h = new f(this.g);
                final f h = this.h;
                h.a <<= 2;
                final f h2 = this.h;
                h2.c <<= 2;
                final f h3 = this.h;
                h3.b <<= 2;
                final f h4 = this.h;
                h4.d <<= 2;
                this.k = new bd[l.d * 4];
                this.m = new bc[l.c];
                this.n = new int[l.c];
                this.p = new int[l.c];
                this.r = new ai[100];
                this.a(this.k, this.h);
                this.b(l);
                super.a.a(this.g.a, this.g.b, this.g.c - this.g.a, this.g.d - this.g.b);
            }
            l.k = blaze3d.c;
            if (!b) {
                break;
            }
            this.f.a.removeElementAt(0);
        }
    }
    
    private void b(final l l) {
        this.l = null;
        final Point point = new Point();
        int i = this.h.b;
        while (i < this.h.d) {
            final int n = i + 4;
            final int n2 = i >> 2;
            int n3 = 0;
            for (int j = 0; j < l.c; ++j) {
                this.m[j] = null;
            }
            int n4 = 0;
            while (i < n) {
                final int n5 = i + 1;
                for (bd c = this.k[i]; c != null; c = c.c) {
                    c.b(i);
                    c.d = this.l;
                    this.l = c;
                }
                bd k = this.l;
                bd bd = null;
                while (k != null) {
                    int n6 = k.k;
                    if (n6 < this.h.a) {
                        n6 = this.h.a;
                    }
                    final int n7 = n6 >> 2;
                    if (n7 < this.g.c) {
                        final int n8 = n6 & 0x3;
                        bc a2;
                        bc a;
                        ai e;
                        for (a = (a2 = this.m[n7]), e = k.e; a != null && a.c != e; a = a.a) {}
                        if (a == null) {
                            final bc bc = this.q[n3++];
                            bc.a(e);
                            bc.a = a2;
                            this.m[n7] = bc;
                            a = bc;
                        }
                        if (e.i) {
                            final int[] d = a.d;
                            final int n9 = n4 >> 2;
                            d[n9] += y.z[n8] * -k.b;
                        }
                        else {
                            final bc bc2 = a;
                            bc2.b ^= 1 << n4 + n8;
                        }
                        if (k.f != null) {
                            bc a4;
                            bc a3;
                            ai f;
                            for (a3 = (a4 = this.m[n7]), f = k.f; a3 != null && a3.c != f; a3 = a3.a) {}
                            if (a3 == null) {
                                final bc bc3 = this.q[n3++];
                                bc3.a(f);
                                bc3.a = a4;
                                this.m[n7] = bc3;
                                a3 = bc3;
                            }
                            final bc bc4 = a3;
                            bc4.b ^= 1 << n4 + n8;
                        }
                    }
                    final bd d2 = k.d;
                    if (k.f > n5) {
                        k.c(n5);
                        bd = k;
                    }
                    else if (bd == null) {
                        this.l = d2;
                    }
                    else {
                        bd.d = d2;
                    }
                    k = d2;
                }
                n4 += 4;
                ++i;
            }
            ++this.t;
            ai f2 = null;
            int a5 = this.g.a;
            final int c2 = this.g.c;
            final int n10 = (n - 1 >> 2) * l.c;
            ah b = null;
            while (a5 < c2) {
                for (bc a6 = this.m[a5]; a6 != null; a6 = a6.a) {
                    final ai c3 = a6.c;
                    Label_0856: {
                        if (f2 == null) {
                            f2 = c3;
                            c3.g = null;
                            c3.f = null;
                            c3.n = 0;
                            c3.j = true;
                            c3.c = this.t;
                            if (c3.i) {
                                final int[] q = c3.q;
                                final int n11 = 0;
                                final int[] q2 = c3.q;
                                final int n12 = 1;
                                final int[] q3 = c3.q;
                                final int n13 = 2;
                                final int[] q4 = c3.q;
                                final int n14 = 3;
                                final int n15 = -2139062144;
                                q3[n13] = (q4[n14] = n15);
                                q[n11] = (q2[n12] = n15);
                            }
                        }
                        else {
                            final int e2 = c3.e;
                            ai f3 = f2;
                            ai g = null;
                            if (c3.c == this.t) {
                                if (c3.j) {
                                    break Label_0856;
                                }
                            }
                            while (f3 != null && e2 < f3.e) {
                                g = f3;
                                f3 = f3.f;
                            }
                            if (f3 == null || e2 > f3.e) {
                                if (g != null) {
                                    g.f = c3;
                                }
                                else {
                                    f2 = c3;
                                }
                                if (f3 != null) {
                                    f3.g = c3;
                                }
                                c3.g = g;
                                c3.f = f3;
                                c3.n = 0;
                                c3.j = true;
                                c3.c = this.t;
                                if (c3.i) {
                                    final int[] q5 = c3.q;
                                    final int n16 = 0;
                                    final int[] q6 = c3.q;
                                    final int n17 = 1;
                                    final int[] q7 = c3.q;
                                    final int n18 = 2;
                                    final int[] q8 = c3.q;
                                    final int n19 = 3;
                                    final int n20 = -2139062144;
                                    q7[n18] = (q8[n19] = n20);
                                    q5[n16] = (q6[n17] = n20);
                                }
                            }
                        }
                    }
                    int n21 = 0;
                    int m = 0;
                    int n22 = 0;
                    if (c3.i) {
                        for (int n23 = 0; n23 < 4; ++n23) {
                            final int n24 = a6.d[n23] + c3.q[n23];
                            final int n25 = (n24 & 0x40404040) >>> 6 | (n24 & 0x20202020) >>> 5 | (n24 & 0x10101010) >>> 4 | (n24 & 0x8080808) >>> 3 | (n24 & 0x4040404) >>> 2 | (n24 & 0x2020202) >>> 1 | (n24 & 0x1010101);
                            final int n26 = (n25 & 0x1) + ((n25 & 0x100) >>> 7) + ((n25 & 0x10000) >>> 14) + ((n25 & 0x1000000) >>> 21);
                            m |= n26 << n21;
                            n22 |= (n26 >> 3) * 15 << n21;
                            final int n27 = n24 & 0xFF000000;
                            c3.q[n23] = n27 + (n27 >>> 8) + (n27 >>> 16) + (n27 >>> 24);
                            n21 += 4;
                        }
                    }
                    else {
                        final int n28 = c3.n;
                        int b2 = a6.b;
                        int n29 = 15;
                        for (int n30 = 0; n30 < 4; ++n30) {
                            int n31 = y.v[b2 & 0xF];
                            if ((n28 & n29) != 0x0) {
                                n31 ^= 0xF;
                            }
                            m |= n31 << n21;
                            n22 |= (n31 >> 3) * 15 << n21;
                            n29 <<= 4;
                            n21 += 4;
                            b2 >>= 4;
                        }
                    }
                    c3.k = m;
                    c3.n = n22;
                    c3.p = 2;
                    final ah u = c3.u;
                    if (u != null) {
                        u.a();
                        if (u.e != this.t) {
                            u.b = b;
                            u.e = this.t;
                            b = u;
                        }
                    }
                }
                ai f4 = f2;
                int n32 = 0;
                while (f4 != null) {
                    if (f4.p == 2) {
                        n32 = 1;
                    }
                    if (f4.p == 1) {
                        f4.k = f4.n;
                        if (f4.u != null) {
                            f4.u.a();
                        }
                    }
                    if (f4.p > 0) {
                        final ai ai = f4;
                        --ai.p;
                    }
                    if (f4.k == 0 && !f4.i) {
                        if (f4.g == null) {
                            f2 = f4.f;
                        }
                        else {
                            f4.g.f = f4.f;
                        }
                        if (f4.f != null) {
                            f4.f.g = f4.g;
                        }
                        f4.j = false;
                    }
                    f4 = f4.f;
                }
                if (n32 == 0) {
                    int n33 = a5;
                    while (n33 < c2 && this.m[n33++] == null) {
                        ++n32;
                    }
                }
                ai f5 = f2;
                ai ai2 = null;
                int n34 = 0;
                int n35 = 1;
                this.s = 0;
                while (f5 != null && n34 != 65535) {
                    f5.v = this.r;
                    f5.w = this.s;
                    if (f5.u == null) {
                        int k2 = f5.k;
                        for (ah ah = f5.t; ah != null; ah = ah.a) {
                            k2 &= ah.c;
                        }
                        if (k2 == 0) {
                            f5.s = false;
                        }
                        else {
                            f5.s = true;
                            if (k2 != 65535) {
                                n35 = 0;
                            }
                            final int m2 = k2 & (n34 ^ 0xFFFF);
                            if (!f5.r) {
                                n34 |= m2;
                            }
                            else {
                                this.r[this.s] = f5;
                                ++this.s;
                            }
                            f5.m = m2;
                            f5.l = y.y[m2];
                        }
                        ai2 = f5;
                    }
                    f5 = f5.f;
                }
                ai ai3 = ai2;
                int n36 = n10 + a5;
                if (n35 == 1) {
                    while (ai3 != null) {
                        if (ai3.u == null && ai3.s) {
                            point.x = a5;
                            point.y = n2;
                            ai3.a(l.b, n36, n32, point);
                        }
                        ai3 = ai3.g;
                    }
                }
                else {
                    for (int n37 = 0; n37 < n32; ++n37) {
                        this.n[n37] = (this.p[n37] = 0);
                    }
                    int a7 = 0;
                    while (ai3 != null) {
                        if (ai3.u == null && ai3.s && !ai3.r) {
                            point.x = a5;
                            point.y = n2;
                            a7 = ai3.a(this.n, this.p, n32, a7, point);
                        }
                        ai3 = ai3.g;
                    }
                    for (int n38 = 0; n38 < n32; ++n38) {
                        final int n39 = this.n[n38];
                        l.b[n36++] = (0xFF000000 | (n39 >> 4 & 0xFF) | n39 >> 20 << 8 | (this.p[n38] << 12 & 0xFF0000));
                    }
                }
                a5 += n32;
            }
            for (ah b3 = b; b3 != null; b3 = b3.b) {
                b3.b();
            }
        }
    }
    
    static {
        y.u = new int[] { 0, 4, 3, 1, 2, 2, 1, 3, 1, 3, 2, 2, 1, 3, 2, 2 };
        y.v = new int[] { 0, 15, 14, 1, 12, 3, 2, 13, 8, 7, 6, 9, 4, 11, 10, 5 };
        y.w = new int[] { 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0 };
        y.x = new int[] { 0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4 };
        y.y = new int[65536];
        for (int i = 0; i < 65536; ++i) {
            int n = i;
            int n2 = 0;
            for (int j = 0; j < 4; ++j) {
                n2 += y.x[n & 0xF];
                n >>= 4;
            }
            y.y[i] = n2;
        }
        y.z = new int[] { 16843009, 16843008, 16842752, 16777216 };
    }
}
