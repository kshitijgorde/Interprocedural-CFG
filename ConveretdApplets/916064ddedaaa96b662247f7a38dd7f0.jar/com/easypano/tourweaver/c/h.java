// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.c;

import java.awt.Component;

public final class h extends f
{
    private final double i = 0.3141592653589793;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private int H;
    private int I;
    private int J;
    private int K;
    private int L;
    private int M;
    private int N;
    private double O;
    private double P;
    private double Q;
    private double R;
    private double S;
    private double T;
    private double U;
    private double V;
    private double W;
    private double X;
    private double Y;
    private double Z;
    private double ab;
    private double bb;
    private double cb;
    private double db;
    private double eb;
    private double fb;
    private double gb;
    private double hb;
    private double ib;
    private int[] jb;
    private int[] kb;
    private int[] lb;
    private int[] mb;
    private int[] nb;
    private int[] ob;
    private int[] pb;
    private int[] qb;
    private int[] rb;
    private int[] sb;
    private int[] tb;
    private int[] ub;
    private int[] vb;
    private int[] wb;
    private int[] xb;
    private int[] yb;
    private int[] zb;
    private int[] Ab;
    private int[] Bb;
    private int[] Cb;
    private int[] Db;
    private boolean Eb;
    private boolean Fb;
    private e[] Gb;
    private d[] Hb;
    private c[] Ib;
    
    public h() {
        this.j = 0;
        this.K = -1;
        this.W = 0.0;
        this.Bb = null;
        this.Eb = false;
        this.Fb = true;
        this.i();
    }
    
    public void a(final int n, final int n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9, final double n10, final double w, final double t, final double q, final boolean b, final int n11, final int n12, final Component component) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        this.k(n9);
        h h2 = this;
        h h3 = null;
        Label_0116: {
            final double n13;
            Label_0091: {
                Label_0047: {
                    if (!h) {
                        this.j(n10);
                        if (n3 != n4) {
                            n13 = dcmpl(n5, n6);
                            if (h) {
                                break Label_0091;
                            }
                            if (n13 != 0) {
                                break Label_0047;
                            }
                        }
                        h2 = this;
                    }
                    h2.Fb = false;
                }
                this.f(n3);
                this.g(n4);
                this.d(n5);
                this.e(n6);
                this.h(n7);
                this.i(n8);
                h3 = this;
                if (h) {
                    break Label_0116;
                }
                final boolean fb = this.Fb;
            }
            if (n13 != 0) {
                this.a(n, n2, b);
            }
            this.Q = q;
            this.T = t;
            h3 = this;
        }
        h3.W = w;
    }
    
    public void a(final c[] ib, final int n) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        int fb;
        final int n2 = fb = (this.Fb ? 1 : 0);
        if (!h) {
            if (n2 == 0) {
                return;
            }
            this.Ib = ib;
            this.Eb = true;
            this.C = this.l / n;
            this.D = this.m;
            this.k = this.C - 1;
            this.E = this.b(this.C);
            fb = n;
        }
        final int n3 = fb;
        this.J = 37748736 / (4 * this.C * this.D);
        final int j = this.J;
        if (!h) {
            if (j == 0) {
                this.Fb = false;
            }
            this.Hb = new d[this.J];
        }
        int i = j;
        while (true) {
            while (i < this.J) {
                this.Hb[i] = new d(new int[this.C * this.D]);
                ++i;
                if (h) {
                    int k = 0;
                    while (k < n3) {
                        this.Gb[k] = new e();
                        ++k;
                        if (h) {
                            break;
                        }
                    }
                    return;
                }
                if (h) {
                    break;
                }
            }
            this.Gb = new e[n3];
            continue;
        }
    }
    
    public void a(final int[] db, final int n, final int n2, final int n3, final int n4) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        int fb;
        int n6;
        final int n5 = n6 = (fb = (this.Fb ? 1 : 0));
        if (!h) {
            if (n5 == 0) {
                return;
            }
            this.Eb = false;
            fb = n3;
            n6 = n3;
        }
        Label_0062: {
            if (!h) {
                if (n6 == this.l) {
                    fb = n4;
                    if (h) {
                        break Label_0062;
                    }
                    if (n4 == this.m) {
                        this.Db = db;
                        if (!h) {
                            return;
                        }
                    }
                }
                fb = 0;
            }
        }
        int i = fb;
        while (i < n4) {
            System.arraycopy(db, i * n3, this.Db, (n2 + i) * this.l + n, n3);
            ++i;
            if (h) {
                break;
            }
        }
    }
    
    public void a(final int p4, final int q, final int[] cb, final Component component) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        this.p = p4;
        this.q = q;
        h h2 = this;
        final int q2;
        Label_0061: {
            Label_0046: {
                if (!h) {
                    if (this.p >= 64) {
                        q2 = this.q;
                        if (h) {
                            break Label_0061;
                        }
                        if (q2 >= 0) {
                            break Label_0046;
                        }
                    }
                    h2 = this;
                }
                h2.Fb = false;
            }
            this.Cb = cb;
            this.t = this.Cb.length;
        }
        int i = q2;
        while (true) {
            while (i < this.t) {
                this.Cb[i] = 61615;
                ++i;
                if (h) {
                    double q3;
                    final int n = (int)(q3 = this.q);
                    h h3 = null;
                    Label_0211: {
                        if (!h) {
                            if (n > 0) {
                                this.a(this.fb, this.p, this.q);
                            }
                            h3 = this;
                            if (h) {
                                break Label_0211;
                            }
                            q3 = dcmpl(this.U, this.V);
                        }
                        if (q3 >= 0) {
                            h3 = this;
                            if (h) {
                                break Label_0211;
                            }
                            if (this.Fb) {
                                this.a(this.Q, this.T, this.W);
                                if (!h) {
                                    return;
                                }
                            }
                        }
                        h3 = this;
                    }
                    h3.Fb = false;
                    return;
                }
                if (h) {
                    break;
                }
            }
            this.jb = new int[6];
            this.kb = new int[6];
            this.z = this.p + 1 >> 1;
            this.u = this.p / 2;
            continue;
        }
    }
    
    public boolean a(final int l, final int m, final boolean b) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final boolean c = this.c(l, m);
        if (!h && c) {
            this.l = l;
            this.m = m;
            this.gb = this.l / this.Y;
            h h2 = this;
            Label_0074: {
                if (!h) {
                    this.j();
                    if (b) {
                        this.Eb = true;
                        if (!h) {
                            break Label_0074;
                        }
                    }
                    h2 = this;
                }
                h2.Eb = false;
            }
            this.eb = this.gb * 256.0;
            this.B = this.n * 256;
            this.b(this.n, this.o);
            this.M = -this.y * this.L;
            this.N = this.m + this.L;
            this.fb = 262144.0 / Math.max(this.n, this.o);
            final double n = dcmpl(this.R, this.X);
            final double s;
            final double n2;
            Label_0265: {
                if (!h) {
                    if (n > 0) {
                        this.R = this.X;
                    }
                    s = this.S;
                    n2 = this.X - this.ib;
                    if (h) {
                        break Label_0265;
                    }
                    final double n3 = dcmpg(s, n2);
                }
                if (n < 0) {
                    this.S = this.X - this.ib;
                }
                this.hb = (Math.min(this.X, this.R) + Math.max(this.X - this.ib, this.S)) * 0.5;
                Math.tan(this.R);
                final double gb = this.gb;
            }
            final double n4 = s * n2;
            final double n5 = Math.tan(this.S) * this.gb;
            this.G = (int)(this.o * 0.5 - n4 + 0.5) - 1;
            this.F = (int)(this.o * 0.5 - n5 + 0.5) - 1;
            h h3 = this;
            int i = 0;
            Label_0356: {
                if (!h) {
                    if (this.G > this.L) {
                        i = this.G;
                        break Label_0356;
                    }
                    h3 = this;
                }
                i = h3.L;
            }
            this.I = i;
            h h4 = this;
            int h5 = 0;
            Label_0387: {
                if (!h) {
                    if (this.F < this.N) {
                        h5 = this.F;
                        break Label_0387;
                    }
                    h4 = this;
                }
                h5 = h4.N;
            }
            this.H = h5;
            this.r = (int)(this.n * this.P / 6.283185307179586 + 0.5);
            this.s = (int)(this.n * this.O / 6.283185307179586 + 0.5);
            h h6 = this;
            int s2 = 0;
            Label_0464: {
                if (!h) {
                    if (this.s < this.l) {
                        s2 = this.s;
                        break Label_0464;
                    }
                    h6 = this;
                }
                s2 = h6.l;
            }
            this.s = s2;
            return true;
        }
        return c;
    }
    
    public double b() {
        return this.T;
    }
    
    public double d() {
        return this.W;
    }
    
    public double c() {
        return this.Q;
    }
    
    public double a(double q) {
        final boolean h = com.easypano.tourweaver.c.f.h;
    Label_0138_Outer:
        while (true) {
        Label_0138:
            while (true) {
                while (q > 6.283185307179586) {
                    q -= 6.283185307179586;
                    if (h) {
                        final double q2 = this.Q;
                        if (!h) {
                            h h2 = null;
                            Label_0139: {
                                if (q2 != q) {
                                    h2 = this;
                                    if (h) {
                                        break Label_0139;
                                    }
                                    if (this.Fb) {
                                        h2 = this;
                                        if (h) {
                                            break Label_0139;
                                        }
                                        if (this.a(this.q, this.fb, this.n, this.m, this.bb, this.T, this.ab, this.Z, this.cb, q, this.A)) {
                                            h2 = this;
                                            if (h) {
                                                break Label_0139;
                                            }
                                            if (this.g()) {
                                                this.Q = q;
                                            }
                                        }
                                    }
                                }
                                h2 = this;
                            }
                            final double q3 = h2.Q;
                        }
                        return q2;
                    }
                    if (h) {
                        break;
                    }
                }
                while (q < 0.0) {
                    q += 6.283185307179586;
                    if (h) {
                        continue Label_0138;
                    }
                    if (h) {
                        break;
                    }
                }
                break;
            }
            continue Label_0138_Outer;
        }
    }
    
    public double b(final double t) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final double t2 = this.T;
        if (!h) {
            h h2 = null;
            Label_0172: {
                Label_0171: {
                    if (t2 != t) {
                        h2 = this;
                        if (h) {
                            break Label_0172;
                        }
                        if (this.Fb) {
                            final double n = dcmpl(t, this.X - this.cb);
                            double cos = 0.0;
                            Label_0088: {
                                if (!h) {
                                    if (n > 0 && !h) {
                                        break Label_0171;
                                    }
                                    cos = t;
                                    if (h) {
                                        break Label_0088;
                                    }
                                    final double n2 = dcmpg(t, this.X - this.ib + this.cb);
                                }
                                if (n < 0 && !h) {
                                    break Label_0171;
                                }
                                cos = Math.cos(t);
                            }
                            final double z = cos;
                            final double sin = Math.sin(t);
                            h2 = this;
                            if (h) {
                                break Label_0172;
                            }
                            if (this.a(this.q, this.fb, this.n, this.m, this.bb, t, sin, z, this.cb, this.Q, this.A)) {
                                h2 = this;
                                if (h) {
                                    break Label_0172;
                                }
                                if (this.g()) {
                                    this.T = t;
                                    this.Z = z;
                                    this.ab = sin;
                                }
                            }
                        }
                    }
                }
                h2 = this;
            }
            final double t3 = h2.T;
        }
        return t2;
    }
    
    public double c(double n) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final double w = this.W;
        if (!h) {
            h h2 = null;
            Label_0632: {
                Label_0631: {
                    if (w != n) {
                        h2 = this;
                        if (h) {
                            break Label_0632;
                        }
                        if (this.Fb) {
                            double p;
                            final double n2 = p = dcmpl(n, this.U);
                            if (!h) {
                                if (n2 > 0) {
                                    n = this.U;
                                    final double bb = this.p * 0.5 / Math.tan(n * 0.5);
                                    final double atan = Math.atan(this.q * Math.tan(n * 0.5) / this.p);
                                    h2 = this;
                                    if (h) {
                                        break Label_0632;
                                    }
                                    if (!this.a(this.q, this.fb, this.n, this.m, bb, this.T, this.ab, this.Z, atan, this.Q, this.A)) {
                                        break Label_0631;
                                    }
                                    h2 = this;
                                    if (h) {
                                        break Label_0632;
                                    }
                                    if (!this.g()) {
                                        break Label_0631;
                                    }
                                    this.bb = bb;
                                    this.W = n;
                                    this.cb = atan;
                                    if (!h) {
                                        break Label_0631;
                                    }
                                }
                                final int n3;
                                p = (n3 = dcmpg(n, this.V));
                            }
                            if (!h) {
                                if (n2 < 0) {
                                    n = this.V;
                                    final double bb2 = this.p * 0.5 / Math.tan(n * 0.5);
                                    final double atan2 = Math.atan(this.q * Math.tan(n * 0.5) / this.p);
                                    h2 = this;
                                    if (h) {
                                        break Label_0632;
                                    }
                                    if (!this.a(this.q, this.fb, this.n, this.m, bb2, this.T, this.ab, this.Z, atan2, this.Q, this.A)) {
                                        break Label_0631;
                                    }
                                    h2 = this;
                                    if (h) {
                                        break Label_0632;
                                    }
                                    if (!this.g()) {
                                        break Label_0631;
                                    }
                                    this.bb = bb2;
                                    this.W = n;
                                    this.cb = atan2;
                                    if (!h) {
                                        break Label_0631;
                                    }
                                }
                                p = this.p;
                            }
                            final double n4 = p * 0.5 / Math.tan(n * 0.5);
                            final double atan3 = Math.atan(this.q * Math.tan(n * 0.5) / this.p);
                            h h3 = this;
                            Label_0454: {
                                if (!h) {
                                    if (this.a(this.q, this.fb, this.n, this.m, n4, this.T, this.ab, this.Z, atan3, this.Q, this.A)) {
                                        h3 = this;
                                        if (h) {
                                            break Label_0454;
                                        }
                                        if (this.g()) {
                                            this.bb = n4;
                                            this.W = n;
                                            this.cb = atan3;
                                            if (!h) {
                                                break Label_0631;
                                            }
                                        }
                                    }
                                    h3 = this;
                                }
                            }
                            double t = h3.T;
                            final double n5 = dcmpl(t, this.hb);
                            final double n6;
                            Label_0531: {
                                Label_0526: {
                                    if (!h) {
                                        if (n5 > 0) {
                                            t -= (atan3 - this.cb) * 2.0;
                                            if (!h) {
                                                break Label_0526;
                                            }
                                        }
                                        n6 = t;
                                        if (h) {
                                            break Label_0531;
                                        }
                                        final double n7 = dcmpg(n6, this.hb);
                                    }
                                    if (n5 < 0) {
                                        t += (atan3 - this.cb) * 2.0;
                                    }
                                }
                                Math.cos(t);
                            }
                            final double z = n6;
                            final double sin = Math.sin(t);
                            h2 = this;
                            if (h) {
                                break Label_0632;
                            }
                            if (this.a(this.q, this.fb, this.n, this.m, n4, t, sin, z, atan3, this.Q, this.A)) {
                                h2 = this;
                                if (h) {
                                    break Label_0632;
                                }
                                if (this.g()) {
                                    this.bb = n4;
                                    this.W = n;
                                    this.T = t;
                                    this.Z = z;
                                    this.ab = sin;
                                    this.cb = atan3;
                                }
                            }
                        }
                    }
                }
                h2 = this;
            }
            final double w2 = h2.W;
        }
        return w;
    }
    
    public void a(final int j) {
        this.j = j;
    }
    
    public void a(final boolean b) {
        if (this.Fb && this.g()) {
            this.a(this.q, this.fb, this.n, this.bb, this.T, this.ab, this.Z, this.cb, this.Q, this.A, this.j, this.Eb, this.E, this.yb, this.xb, this.Gb, this.Ib, this.Hb, this.k);
        }
    }
    
    public void a() {
        this.Db = null;
        this.Cb = null;
        this.lb = null;
        this.mb = null;
        this.nb = null;
        this.ob = null;
        this.pb = null;
        this.qb = null;
        this.rb = null;
        this.sb = null;
        this.tb = null;
        this.ub = null;
        this.vb = null;
        this.wb = null;
        this.xb = null;
        this.yb = null;
        this.jb = null;
        this.kb = null;
        this.jb = new int[6];
        this.kb = new int[6];
        this.Hb = null;
        this.Gb = null;
        System.gc();
    }
    
    public void a(final int n, final int n2) {
        this.a(this.l, this.p, this.q, this.bb, this.Q, this.T, n, n2 + this.L + 1, this.Y);
    }
    
    private void a(final int n, final int n2, final int n3, final double n4, double n5, final double n6, final int n7, final int n8, final double n9) {
        n5 -= 3.141592653589793;
        final double cos = Math.cos(n5);
        final double sin = Math.sin(n5);
        final double cos2 = Math.cos(n6);
        final double sin2 = Math.sin(n6);
        final double n10 = (n7 - n / 2.0) / n * n9;
        final double n11 = (this.o / 2.0 - n8) / this.n * 6.283185307179586;
        final double cos3 = Math.cos(n10);
        final double sin3 = Math.sin(n10);
        final double cos4 = Math.cos(n11);
        final double sin4 = Math.sin(n11);
        if (cos * cos2 * cos3 * cos4 + sin * cos2 * sin3 * cos4 + sin2 * sin4 > 0.0) {
            final double n12 = n4 / (cos * cos2 * cos3 * cos4 + sin * cos2 * sin3 * cos4 + sin2 * sin4);
            final double n13 = n12 * sin4;
            super.f = (int)(n12 * cos4 * Math.sin(n10 - n5) + n2 / 2.0);
            super.g = (int)(n3 / 2.0 - (int)((n13 - n4 * sin2) / cos2));
        }
        else {
            super.f = n2 + 100000;
            super.g = n3 + 100000;
        }
    }
    
    private final boolean a(final int n, final double n2, final int n3, final int n4, final double n5, final double n6, final double n7, final double n8, final double n9, final double n10, final int n11) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final double n12 = dcmpl(n9 + n6, 1.5707963267948966);
        if (!h && n12 <= 0) {
            double n14;
            final double n13 = n14 = dcmpg(n6 - n9, -1.5707963267948966);
            if (!h) {
                if (n13 < 0) {
                    goto Label_0039;
                }
                final int n15;
                n14 = (n15 = dcmpl(this.O, this.P));
            }
            if (!h) {
                Label_0075: {
                    if (n13 != 0) {
                        final double n17;
                        final double n16 = n17 = dcmpl(this.R, this.S);
                        if (!h) {
                            if (n16 == 0) {
                                break Label_0075;
                            }
                            final boolean fb = this.Fb;
                        }
                        if (!h && n16 == 0) {
                            return false;
                        }
                        int n18 = 2;
                        double n19 = n6;
                        if (!h) {
                            if (n6 < 0.0) {
                                n19 = -n6;
                            }
                            else {
                                n19 = n6;
                            }
                        }
                        final double n20 = n19 + n9;
                        double v;
                        final double n21 = v = dcmpg(n20, 0.7853981633974483);
                        double n22 = 0.0;
                        int[] array = null;
                        int[] array2 = null;
                        int[] array3 = null;
                        Label_0246: {
                            if (!h) {
                                if (n21 < 0) {
                                    n18 = 4;
                                    n22 = this.x;
                                    array = this.vb;
                                    array2 = this.rb;
                                    array3 = this.sb;
                                    if (!h) {
                                        break Label_0246;
                                    }
                                }
                                final int n23;
                                v = (n23 = dcmpg(n20, 1.5707963267948966));
                            }
                            if (!h) {
                                if (n21 < 0) {
                                    n18 = 3;
                                    n22 = this.w;
                                    array = this.ub;
                                    array2 = this.pb;
                                    array3 = this.qb;
                                    if (!h) {
                                        break Label_0246;
                                    }
                                }
                                v = this.v;
                            }
                            n22 = v;
                            array = this.tb;
                            array2 = this.nb;
                            array3 = this.ob;
                        }
                        final int n24 = ((int)(this.eb * n10 + this.B / 2.0) % this.B + this.B) % this.B - 128;
                        final int[] wb = this.wb;
                        final int[] bb = this.Bb;
                        final int[] xb = this.xb;
                        final double n25 = n2 * (n5 * n8 - (n >> 1) * n7 + 0.5);
                        final double n26 = n2 * (n5 * n7 + (n >> 1) * n8 - 0.5);
                        final double n27 = n2 * n7;
                        final double n28 = -n2 * n8;
                        int n29 = (int)n25;
                        int n30 = (int)n26;
                        int[] array4 = wb;
                        final int n31 = n29;
                        if (!h) {
                            if (n31 == 0) {
                                n29 = 1;
                            }
                            final int n32 = array4[array3[n22] / (n29 + array2[n22])];
                        }
                        int n33 = n31;
                        final int n34 = array2[n22];
                        final int n35 = n29;
                        int n36 = 0;
                        Label_0480: {
                            if (!h) {
                                if (n34 < n35) {
                                    n36 = Math.max(1, n29 * bb[array3[n22] / n29] >> 12);
                                    if (!h) {
                                        break Label_0480;
                                    }
                                }
                                final int n37 = array2[n22] * bb[(n29 << 12) / array2[n22]] >> 12;
                            }
                            n36 = Math.max(n34, n35);
                        }
                        final int n38 = n30;
                        int n39;
                        double n40;
                        int n41;
                        if (!h && n38 < 0) {
                            n30 = -n30;
                            n39 = 0;
                            n40 = (n30 << 12) * this.db;
                            n41 = n11 + (int)((n40 / n36 + 0.5) / 4096.0);
                            if (h) {
                                goto Label_0540;
                            }
                        }
                        else {
                            n39 = n38;
                            n40 = (n30 << 12) * this.db;
                            n41 = n11 - (int)((n40 / n36 + 0.5) / 4096.0);
                        }
                        final int n42 = this.M + n41;
                        final int n43 = array4[array3[0] / (n29 + array2[0])];
                        final int n44 = array2[0];
                        final int n45 = n29;
                        Label_0674: {
                            if (!h) {
                                if (n44 < n45) {
                                    Math.max(1, n29 * bb[array3[0] / n29] >> 12);
                                    if (!h) {
                                        break Label_0674;
                                    }
                                }
                                final int n46 = array2[0] * bb[(n29 << 12) / array2[0]] >> 12;
                            }
                            Math.max(n44, n45);
                        }
                        final int n47 = n39;
                        int n48 = 0;
                        Label_0729: {
                            if (!h) {
                                if (n47 != 0) {
                                    n48 = n11 - (int)((n40 / n36 + 0.5) / 4096.0);
                                    if (!h) {
                                        break Label_0729;
                                    }
                                }
                                final int n49 = n11 + (int)((n40 / n36 + 0.5) / 4096.0);
                            }
                            n48 = n47;
                        }
                        final int n50 = this.M + n48 - n42 >> n18;
                        final int n51 = array[0];
                        final int n53;
                        final int n52 = n53 = n42 - 128 >> 8;
                        if (h || n53 < 0) {
                            return n53 != 0;
                        }
                        final int n54 = n52;
                        int n55 = n4;
                        if (!h) {
                            if (n54 >= n4) {
                                goto Label_0797;
                            }
                            this.kb[1] = n52;
                            n55 = 128;
                        }
                        int n56 = n54 - n55 >> 8;
                        int n59;
                        int n58;
                        final int n57 = n58 = (n59 = n56);
                        if (!h) {
                            if (n57 < 0) {
                                n56 += n3;
                            }
                            this.jb[1] = xb[n56];
                            n56 = n24 + n33 - 128 >> 8;
                            final int n60;
                            n58 = (n60 = (n59 = n56));
                        }
                        int n61 = n3;
                        int n62 = n3;
                        if (!h) {
                            if (n57 >= n3) {
                                n56 -= n3;
                            }
                            this.jb[1] = xb[n56];
                            n33 = array4[array3[n22 - 2] / (n29 + array2[n22 - 2])];
                            n59 = (n58 = array2[n22 - 2]);
                            n61 = (n62 = n29);
                        }
                        Label_1004: {
                            if (!h) {
                                if (n58 < n62) {
                                    Math.max(1, n29 * bb[array3[n22 - 2] / n29] >> 12);
                                    if (!h) {
                                        break Label_1004;
                                    }
                                }
                                n59 = 1;
                                n61 = array2[n22 - 2] * bb[(n29 << 12) / array2[n22 - 2]] >> 12;
                            }
                            Math.max(n59, n61);
                        }
                        final int n63 = n39;
                        int n64 = 0;
                        Label_1059: {
                            if (!h) {
                                if (n63 != 0) {
                                    n64 = n11 - (int)((n40 / n36 + 0.5) / 4096.0);
                                    if (!h) {
                                        break Label_1059;
                                    }
                                }
                                final int n65 = n11 + (int)((n40 / n36 + 0.5) / 4096.0);
                            }
                            n64 = n63;
                        }
                        final int n66 = this.M + n64;
                        final int n67 = array4[array3[n22 - 1] / (n29 + array2[n22 - 1])];
                        final int n68 = array2[n22 - 1];
                        final int n69 = n29;
                        Label_1171: {
                            if (!h) {
                                if (n68 < n69) {
                                    Math.max(1, n29 * bb[array3[n22 - 1] / n29] >> 12);
                                    if (!h) {
                                        break Label_1171;
                                    }
                                }
                                final int n70 = array2[n22 - 1] * bb[(n29 << 12) / array2[n22 - 1]] >> 12;
                            }
                            Math.max(n68, n69);
                        }
                        final int n71 = n39;
                        int n72 = 0;
                        Label_1226: {
                            if (!h) {
                                if (n71 != 0) {
                                    n72 = n11 - (int)((n40 / n36 + 0.5) / 4096.0);
                                    if (!h) {
                                        break Label_1226;
                                    }
                                }
                                final int n73 = n11 + (int)((n40 / n36 + 0.5) / 4096.0);
                            }
                            n72 = n71;
                        }
                        final int n74 = this.M + n72;
                        final int n75 = n67 - n33 >> n18;
                        final int n76 = n74 - n66 >> n18;
                        final int n77 = array[n22 - 1];
                        int n78 = n33 + n75 * (n77 - 1);
                        final int n80;
                        final int n79 = n80 = n66 + n76 * (n77 - 1) - 128 >> 8;
                        if (h || n80 < 0) {
                            return n80 != 0;
                        }
                        final int n81 = n79;
                        int n82 = n4;
                        if (!h) {
                            if (n81 >= n4) {
                                goto Label_1321;
                            }
                            this.kb[0] = n79;
                            this.kb[2] = n79;
                            n82 = 8;
                        }
                        int n83 = n81 >> n82;
                        int n87;
                        int n86;
                        int n85;
                        final int n84 = n85 = (n86 = (n87 = n83));
                        if (!h) {
                            if (n84 < 0) {
                                n83 += n3;
                            }
                            this.jb[2] = xb[n83];
                            n83 = n24 + n78 - 128 >> 8;
                            final int n88;
                            n85 = (n88 = (n86 = (n87 = n83)));
                        }
                        if (!h) {
                            if (n84 >= n3) {
                                n83 -= n3;
                            }
                            this.jb[0] = xb[n83];
                            n29 = (int)(n25 + n27 * (n - 1));
                            n30 = (int)(n26 + n28 * (n - 1));
                            array4 = wb;
                            n86 = (n85 = (n87 = n29));
                        }
                        if (!h) {
                            if (n85 == 0) {
                                n29 = 1;
                            }
                            n78 = array4[array3[n22] / (n29 + array2[n22])];
                            n87 = (n86 = array2[n22]);
                        }
                        final int n89 = n29;
                        int n90 = 0;
                        Label_1567: {
                            if (!h) {
                                if (n86 < n89) {
                                    n90 = Math.max(1, n29 * bb[array3[n22] / n29] >> 12);
                                    if (!h) {
                                        break Label_1567;
                                    }
                                }
                                n87 = 1;
                                final int n91 = array2[n22] * bb[(n29 << 12) / array2[n22]] >> 12;
                            }
                            n90 = Math.max(n87, n89);
                        }
                        final int n92 = n30;
                        int n93 = 0;
                        Label_1667: {
                            if (!h) {
                                if (n92 < 0) {
                                    n30 = -n30;
                                    n39 = 0;
                                    n40 = (n30 << 12) * this.db;
                                    n93 = n11 + (int)((n40 / n90 + 0.5) / 4096.0);
                                    if (!h) {
                                        break Label_1667;
                                    }
                                }
                                n39 = 1;
                                n40 = (n30 << 12) * this.db;
                                final int n94 = n11 - (int)((n40 / n90 + 0.5) / 4096.0);
                            }
                            n93 = n92;
                        }
                        final int n95 = this.M + n93;
                        final int n96 = array4[array3[0] / (n29 + array2[0])];
                        final int n97 = array2[0];
                        final int n98 = n29;
                        Label_1761: {
                            if (!h) {
                                if (n97 < n98) {
                                    Math.max(1, n29 * bb[array3[0] / n29] >> 12);
                                    if (!h) {
                                        break Label_1761;
                                    }
                                }
                                final int n99 = array2[0] * bb[(n29 << 12) / array2[0]] >> 12;
                            }
                            Math.max(n97, n98);
                        }
                        final int n100 = n39;
                        int n101 = 0;
                        Label_1816: {
                            if (!h) {
                                if (n100 != 0) {
                                    n101 = n11 - (int)((n40 / n90 + 0.5) / 4096.0);
                                    if (!h) {
                                        break Label_1816;
                                    }
                                }
                                final int n102 = n11 + (int)((n40 / n90 + 0.5) / 4096.0);
                            }
                            n101 = n100;
                        }
                        final int n103 = this.M + n101 - n95 >> n18;
                        final int n104 = array[0];
                        final int n106;
                        final int n105 = n106 = n95 - 128 >> 8;
                        if (h || n106 < 0) {
                            return n106 != 0;
                        }
                        final int n107 = n105;
                        int n108 = n4;
                        if (!h) {
                            if (n107 >= n4) {
                                goto Label_1884;
                            }
                            this.kb[4] = n105;
                            n108 = 8;
                        }
                        int n109 = n107 >> n108;
                        int n112;
                        int n111;
                        final int n110 = n111 = (n112 = n109);
                        if (!h) {
                            if (n110 < 0) {
                                n109 += n3;
                            }
                            this.jb[4] = xb[n109];
                            n109 = n24 + n78 - 128 >> 8;
                            final int n113;
                            n111 = (n113 = (n112 = n109));
                        }
                        int n114 = n3;
                        int n115 = n3;
                        if (!h) {
                            if (n110 >= n3) {
                                n109 -= n3;
                            }
                            this.jb[4] = xb[n109];
                            n78 = array4[array3[n22 - 2] / (n29 + array2[n22 - 2])];
                            n112 = (n111 = array2[n22 - 2]);
                            n114 = (n115 = n29);
                        }
                        Label_2091: {
                            if (!h) {
                                if (n111 < n115) {
                                    Math.max(1, n29 * bb[array3[n22 - 2] / n29] >> 12);
                                    if (!h) {
                                        break Label_2091;
                                    }
                                }
                                n112 = 1;
                                n114 = array2[n22 - 2] * bb[(n29 << 12) / array2[n22 - 2]] >> 12;
                            }
                            Math.max(n112, n114);
                        }
                        final int n116 = n39;
                        int n117 = 0;
                        Label_2146: {
                            if (!h) {
                                if (n116 != 0) {
                                    n117 = n11 - (int)((n40 / n90 + 0.5) / 4096.0);
                                    if (!h) {
                                        break Label_2146;
                                    }
                                }
                                final int n118 = n11 + (int)((n40 / n90 + 0.5) / 4096.0);
                            }
                            n117 = n116;
                        }
                        final int n119 = this.M + n117;
                        final int n120 = array4[array3[n22 - 1] / (n29 + array2[n22 - 1])];
                        final int n121 = array2[n22 - 1];
                        final int n122 = n29;
                        Label_2258: {
                            if (!h) {
                                if (n121 < n122) {
                                    Math.max(1, n29 * bb[array3[n22 - 1] / n29] >> 12);
                                    if (!h) {
                                        break Label_2258;
                                    }
                                }
                                final int n123 = array2[n22 - 1] * bb[(n29 << 12) / array2[n22 - 1]] >> 12;
                            }
                            Math.max(n121, n122);
                        }
                        final int n124 = n39;
                        int n125 = 0;
                        Label_2313: {
                            if (!h) {
                                if (n124 != 0) {
                                    n125 = n11 - (int)((n40 / n90 + 0.5) / 4096.0);
                                    if (!h) {
                                        break Label_2313;
                                    }
                                }
                                final int n126 = n11 + (int)((n40 / n90 + 0.5) / 4096.0);
                            }
                            n125 = n124;
                        }
                        final int n127 = this.M + n125;
                        final int n128 = n120 - n78 >> n18;
                        final int n129 = n127 - n119 >> n18;
                        final int n130 = array[n22 - 1];
                        final int n131 = n78 + n128 * (n130 - 1);
                        final int n133;
                        final int n132 = n133 = n119 + n129 * (n130 - 1) - 128 >> 8;
                        if (!h && n133 >= 0) {
                            final int n134 = n132;
                            int n135 = n4;
                            if (!h) {
                                if (n134 >= n4) {
                                    goto Label_2408;
                                }
                                this.kb[3] = n132;
                                this.kb[5] = n132;
                                n135 = 8;
                            }
                            int n136 = n134 >> n135;
                            double n140;
                            int n139;
                            double n138;
                            final int n137 = (int)(n138 = (n139 = (int)(n140 = n136)));
                            if (!h) {
                                if (n137 < 0) {
                                    n136 += n3;
                                }
                                this.jb[5] = xb[n136];
                                n136 = n24 + n131 - 128 >> 8;
                                final int n141;
                                n138 = (n141 = (n139 = (int)(n140 = n136)));
                            }
                            if (!h) {
                                if (n137 >= n3) {
                                    n136 -= n3;
                                }
                                this.jb[3] = xb[n136];
                                n139 = (int)(n138 = (n140 = dcmpl(n6, 0.0)));
                            }
                            Label_2578: {
                                if (!h) {
                                    if (n138 > 0) {
                                        final int n142 = n139 = (int)(n140 = this.kb[0]);
                                        if (h) {
                                            break Label_2578;
                                        }
                                        if (n142 < this.kb[1]) {
                                            final int n143 = n139 = (int)(n140 = this.kb[3]);
                                            if (h) {
                                                break Label_2578;
                                            }
                                            if (n143 < this.kb[4]) {
                                                return false;
                                            }
                                        }
                                    }
                                    n140 = (n139 = dcmpg(n6, 0.0));
                                }
                            }
                            if (!h) {
                                if (n139 < 0) {
                                    final int n144 = (int)(n140 = this.kb[0]);
                                    if (h) {
                                        return n140 != 0.0;
                                    }
                                    if (n144 > this.kb[1]) {
                                        final int n145 = (int)(n140 = this.kb[3]);
                                        if (h) {
                                            return n140 != 0.0;
                                        }
                                        if (n145 > this.kb[4]) {
                                            return false;
                                        }
                                    }
                                }
                                n140 = 1;
                            }
                            return n140 != 0.0;
                        }
                        return n133 != 0;
                    }
                }
                n14 = 0;
            }
            return n14 != 0.0;
        }
        return n12 != 0.0;
    }
    
    private boolean g() {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final int n = this.kb[0] + this.L;
        if (!h) {
            if (n > this.I) {
                final int n2 = this.kb[0] + this.L;
                if (!h) {
                    if (n2 < this.H) {
                        final int n3 = this.kb[1] + this.L;
                        if (!h) {
                            if (n3 > this.I) {
                                final int n4 = this.kb[1] + this.L;
                                if (!h) {
                                    if (n4 < this.H) {
                                        final int n5 = this.kb[3] + this.L;
                                        if (!h) {
                                            if (n5 < this.H) {
                                                final int n6 = this.kb[3] + this.L;
                                                if (!h) {
                                                    if (n6 > this.I) {
                                                        final int n7 = this.kb[4] + this.L;
                                                        if (!h) {
                                                            if (n7 < this.H) {
                                                                final int n8 = this.kb[4] + this.L;
                                                                if (!h) {
                                                                    if (n8 > this.I) {
                                                                        int n10;
                                                                        final int n9 = n10 = this.s - this.r;
                                                                        int n12;
                                                                        final int n11 = n12 = this.n;
                                                                        if (!h) {
                                                                            if (n9 != n11) {
                                                                                final int n13 = this.jb[1];
                                                                                if (h) {
                                                                                    return n != 0;
                                                                                }
                                                                                if (n13 > this.jb[2]) {
                                                                                    return n != 0;
                                                                                }
                                                                                final int n14 = this.jb[1];
                                                                                if (h) {
                                                                                    return n != 0;
                                                                                }
                                                                                if (n14 < this.jb[0]) {
                                                                                    return n != 0;
                                                                                }
                                                                            }
                                                                            final int n15;
                                                                            n10 = (n15 = this.s - this.r);
                                                                            final int n16;
                                                                            n12 = (n16 = this.n);
                                                                        }
                                                                        if (!h) {
                                                                            if (n9 != n11) {
                                                                                final int n17 = this.jb[4];
                                                                                if (h) {
                                                                                    return n != 0;
                                                                                }
                                                                                if (n17 > this.jb[5]) {
                                                                                    return n != 0;
                                                                                }
                                                                                final int n18 = this.jb[4];
                                                                                if (h) {
                                                                                    return n != 0;
                                                                                }
                                                                                if (n18 < this.jb[3]) {
                                                                                    return n != 0;
                                                                                }
                                                                            }
                                                                            n10 = this.jb[0];
                                                                            if (h) {
                                                                                return n != 0;
                                                                            }
                                                                            n12 = this.r;
                                                                        }
                                                                        if (n10 >= n12) {
                                                                            final int n19 = this.jb[0];
                                                                            if (!h) {
                                                                                if (n19 <= this.s) {
                                                                                    final int n20 = this.jb[2];
                                                                                    if (!h) {
                                                                                        if (n20 <= this.s) {
                                                                                            final int n21 = this.jb[3];
                                                                                            if (!h) {
                                                                                                if (n21 >= this.r) {
                                                                                                    final int n22 = this.jb[3];
                                                                                                    if (!h) {
                                                                                                        if (n22 <= this.s) {
                                                                                                            final int n23 = this.jb[5];
                                                                                                            if (!h) {
                                                                                                                if (n23 <= this.s) {
                                                                                                                    boolean b;
                                                                                                                    final int n24 = (b = (this.kb[1] != 0)) ? 1 : 0;
                                                                                                                    if (!h) {
                                                                                                                        if (n24 < this.kb[4]) {
                                                                                                                            return true;
                                                                                                                        }
                                                                                                                        b = false;
                                                                                                                    }
                                                                                                                    return b;
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return n != 0;
    }
    
    public boolean h() {
        return this.Db == null;
    }
    
    private void d(double o) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final double n = dcmpl(o, 6.283185307179586);
        Label_0032: {
            final double n2;
            Label_0031: {
                if (!h) {
                    if (n > 0) {
                        o = 6.283185307179586;
                    }
                    n2 = o;
                    if (h) {
                        break Label_0031;
                    }
                    final double n3 = dcmpg(n2, 0.0);
                }
                if (n >= 0) {
                    break Label_0032;
                }
            }
            o = n2;
        }
        this.O = o;
    }
    
    private void e(double p) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final double n = dcmpl(p, 6.283185307179586);
        Label_0032: {
            final double n2;
            Label_0031: {
                if (!h) {
                    if (n > 0) {
                        p = 6.283185307179586;
                    }
                    n2 = p;
                    if (h) {
                        break Label_0031;
                    }
                    final double n3 = dcmpg(n2, 0.0);
                }
                if (n >= 0) {
                    break Label_0032;
                }
            }
            p = n2;
        }
        this.P = p;
    }
    
    private void f(final double r) {
        h h = this;
        double r2 = r;
        if (!com.easypano.tourweaver.c.f.h) {
            this.R = r;
            if (r <= 1.5707963267948966) {
                return;
            }
            h = this;
            r2 = 1.5707963267948966;
        }
        h.R = r2;
    }
    
    private void g(final double s) {
        h h = this;
        double s2 = s;
        if (!com.easypano.tourweaver.c.f.h) {
            this.S = s;
            if (s >= -1.5707963267948966) {
                return;
            }
            h = this;
            s2 = -1.5707963267948966;
        }
        h.S = s2;
    }
    
    private void h(final double u) {
        this.U = u;
        final double min = Math.min(2.6179938779914944, this.O - this.P - 0.017453292519943295);
        if (u > min) {
            this.U = min;
        }
    }
    
    private void i(final double v) {
        h h = this;
        double v2 = v;
        if (!com.easypano.tourweaver.c.f.h) {
            this.V = v;
            if (v >= 0.3141592653589793) {
                return;
            }
            h = this;
            v2 = 0.3141592653589793;
        }
        h.V = v2;
    }
    
    private void j(final double x) {
        this.X = x;
    }
    
    private void k(final double y) {
        this.Y = y;
    }
    
    private void a(double q, double hb, double w) {
        final boolean h = com.easypano.tourweaver.c.f.h;
    Label_0072_Outer:
        while (true) {
        Label_0072:
            while (true) {
                while (q > 6.283185307179586) {
                    q -= 6.283185307179586;
                    if (h) {
                        double q2;
                        final double n = q2 = dcmpg(w, this.V);
                        if (!h) {
                            if (n < 0) {
                                w = this.V;
                            }
                            final int n2;
                            q2 = (n2 = dcmpl(w, this.U));
                        }
                        if (!h) {
                            if (n > 0) {
                                w = this.U;
                            }
                            q2 = this.q;
                        }
                        double cb = Math.atan(q2 * Math.tan(w * 0.5) / this.p);
                        double bb = this.u / Math.tan(w * 0.5);
                        final double n3 = dcmpl(hb, this.R - cb);
                        final double n4;
                        Label_0195: {
                            Label_0191: {
                                if (!h) {
                                    if (n3 > 0) {
                                        hb = this.R - cb;
                                        if (!h) {
                                            break Label_0191;
                                        }
                                    }
                                    n4 = hb;
                                    if (h) {
                                        break Label_0195;
                                    }
                                    final double n5 = dcmpg(n4, this.S + cb);
                                }
                                if (n3 < 0) {
                                    hb = this.S + cb;
                                }
                            }
                            Math.cos(hb);
                        }
                        double cos = n4;
                        double ab = Math.sin(hb);
                        int n6 = this.a(this.q, this.fb, this.n, this.m, bb, hb, ab, cos, cb, q, this.A) ? 1 : 0;
                        int n10;
                        int n9;
                        double n8;
                        final int n7 = (int)(n8 = (n9 = (n10 = n6)));
                        Label_0622: {
                            Label_0599: {
                                int n16 = 0;
                                int h2 = 0;
                                int n15 = 0;
                                int n14 = 0;
                                int n13 = 0;
                                int n12 = 0;
                                final int n11;
                                Label_0383: {
                                    Label_0271: {
                                        if (!h) {
                                            if (n7 != 0) {
                                                n11 = (n12 = (n13 = (n14 = (n15 = (h2 = (n16 = (this.g() ? 1 : 0)))))));
                                                if (h) {
                                                    break Label_0383;
                                                }
                                                if (n11 == 0) {
                                                    break Label_0271;
                                                }
                                            }
                                            final int n17;
                                            n8 = (n17 = (n9 = (n10 = n6)));
                                        }
                                        if (h) {
                                            break Label_0622;
                                        }
                                        if (n7 != 0) {
                                            break Label_0599;
                                        }
                                    }
                                    hb = this.hb;
                                    cos = Math.cos(hb);
                                    ab = Math.sin(hb);
                                    cb = Math.abs(Math.min(this.X, this.R) - Math.max(this.X - this.ib, this.S)) * 0.5 - 0.013962634015954637;
                                    w = Math.atan(this.p * Math.tan(cb) / this.q) * 2.0;
                                    bb = this.p * 0.5 / Math.tan(w * 0.5);
                                    final int n18;
                                    n12 = (n18 = (n13 = (n14 = (n15 = (h2 = (n16 = dcmpl(w, this.U)))))));
                                }
                                Label_0464: {
                                    Label_0448: {
                                        if (!h) {
                                            if (n11 > 0) {
                                                w = this.U;
                                                if (!h) {
                                                    break Label_0448;
                                                }
                                            }
                                            n13 = (n12 = (n14 = (n15 = (h2 = (n16 = dcmpg(w, 0.3141592653589793))))));
                                        }
                                        if (!h) {
                                            if (n12 < 0) {
                                                this.Fb = false;
                                                if (!h) {
                                                    break Label_0448;
                                                }
                                            }
                                            n14 = (n13 = (n15 = (h2 = (n16 = dcmpg(w, this.V)))));
                                        }
                                        if (h) {
                                            break Label_0464;
                                        }
                                        if (n13 < 0) {
                                            this.V = 0.3141592653589793;
                                        }
                                    }
                                    n15 = (n14 = (h2 = (n16 = dcmpl(cb * 2.0, this.R - this.S))));
                                }
                                int r = 0;
                                Label_0582: {
                                    Label_0527: {
                                        if (!h) {
                                            if (n14 > 0) {
                                                this.Fb = false;
                                                if (!h) {
                                                    break Label_0527;
                                                }
                                            }
                                            h2 = (n15 = (n16 = this.I));
                                        }
                                        final int n19 = r = this.o / 2 - 1;
                                        Label_0522: {
                                            if (!h) {
                                                if (n15 > n19) {
                                                    break Label_0522;
                                                }
                                                n16 = (h2 = this.H);
                                                final int n20;
                                                r = (n20 = this.o / 2 - 1);
                                            }
                                            if (h) {
                                                break Label_0582;
                                            }
                                            if (h2 >= n19) {
                                                break Label_0527;
                                            }
                                        }
                                        w = 0.3141592653589793;
                                    }
                                    bb = this.p * 0.5 / Math.tan(w * 0.5);
                                    cb = Math.atan(this.q * Math.tan(w * 0.5) / this.p);
                                    n16 = this.s;
                                    r = this.r;
                                }
                                q = (n16 + r) * 6.283185307179586 / this.n * 0.5;
                            }
                            n9 = (int)(n8 = (n10 = dcmpg((this.s - this.r) * 6.283185307179586 / this.n, w)));
                        }
                        if (!h) {
                            if (n8 <= 0) {
                                this.Fb = false;
                            }
                            n6 = (this.a(this.q, this.fb, this.n, this.m, bb, hb, ab, cos, cb, q, this.A) ? 1 : 0);
                            n10 = (n9 = n6);
                        }
                        Label_0703: {
                            h h3 = null;
                            Label_0699: {
                                Label_0698: {
                                    if (!h) {
                                        if (n9 != 0) {
                                            h3 = this;
                                            if (h) {
                                                break Label_0699;
                                            }
                                            if (!this.g()) {
                                                break Label_0698;
                                            }
                                        }
                                        n10 = n6;
                                    }
                                    if (n10 != 0) {
                                        break Label_0703;
                                    }
                                }
                                h3 = this;
                            }
                            h3.Fb = false;
                        }
                        this.W = w;
                        this.Q = q;
                        this.T = hb;
                        this.ab = ab;
                        this.Z = cos;
                        this.bb = bb;
                        this.cb = cb;
                        return;
                    }
                    if (h) {
                        break;
                    }
                }
                while (q < 0.0) {
                    q += 6.283185307179586;
                    if (h) {
                        continue Label_0072;
                    }
                    if (h) {
                        break;
                    }
                }
                break;
            }
            continue Label_0072_Outer;
        }
    }
    
    private int b(final int n) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        int i = n;
        int n2 = 0;
        int n3 = 0;
        while (i > 1) {
            n3 = i >> 1;
            if (h) {
                return n3;
            }
            i = n3;
            ++n2;
            if (h) {
                break;
            }
        }
        return n3;
    }
    
    private void a(final double n, final int n2, final int n3) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        this.lb = new int[n3];
        this.mb = new int[n3];
        int u = this.u;
        final int n4 = (int)(n + 0.5);
        int n5 = 1;
        final int n6 = n2 % 2;
        if (!h && n6 == 0) {
            n5 = n4 + 1 >> 1;
            u = this.u - 1;
            goto Label_0065;
        }
        final int n7 = n6;
        this.lb[0] = this.u;
        this.mb[0] = u;
        int i = 1;
        while (true) {
            while (i < n3) {
                this.lb[i] = n2 + this.lb[i - 1];
                this.mb[i] = n2 + this.mb[i - 1];
                ++i;
                if (h) {
                    int n8 = 1 << 2;
                    int j = 0;
                    while (true) {
                        while (j < this.z) {
                            ++i;
                            j += n8;
                            if (h) {
                                int k = 0;
                                while (true) {
                                    while (k < i) {
                                        this.tb[k] = n8;
                                        j += n8;
                                        if (!h) {
                                            final int n9 = j;
                                            final int z = this.z;
                                            if (h) {
                                                final int n10 = n9 * z;
                                                this.nb[0] = n5 + n10;
                                                this.ob[0] = this.nb[0] << 12;
                                                int l = 1;
                                                while (true) {
                                                    while (l < i) {
                                                        this.nb[l] = n10 + this.nb[l - 1];
                                                        this.ob[l] = this.nb[l] << 12;
                                                        ++l;
                                                        if (h) {
                                                            int n11 = 0;
                                                            while (true) {
                                                                while (n11 < this.z) {
                                                                    ++i;
                                                                    n11 += n8;
                                                                    if (h) {
                                                                        int n12 = 0;
                                                                        while (true) {
                                                                            while (n12 < i) {
                                                                                this.ub[n12] = n8;
                                                                                j += n8;
                                                                                if (!h) {
                                                                                    final int n13 = j;
                                                                                    final int z2 = this.z;
                                                                                    if (h) {
                                                                                        final int n14 = n13 * z2;
                                                                                        this.pb[0] = n5 + n14;
                                                                                        this.qb[0] = this.pb[0] << 12;
                                                                                        int n15 = 1;
                                                                                        while (true) {
                                                                                            while (n15 < i) {
                                                                                                this.pb[n15] = n14 + this.pb[n15 - 1];
                                                                                                this.qb[n15] = this.pb[n15] << 12;
                                                                                                ++n15;
                                                                                                if (h) {
                                                                                                    int n16 = 0;
                                                                                                    while (true) {
                                                                                                        while (n16 < this.z) {
                                                                                                            ++i;
                                                                                                            n16 += n8;
                                                                                                            if (h) {
                                                                                                                int n17 = 0;
                                                                                                                while (true) {
                                                                                                                    while (n17 < i) {
                                                                                                                        this.vb[n17] = n8;
                                                                                                                        j += n8;
                                                                                                                        if (!h) {
                                                                                                                            final int n18 = j;
                                                                                                                            final int z3 = this.z;
                                                                                                                            if (h) {
                                                                                                                                final int n19 = n18 * z3;
                                                                                                                                this.rb[0] = n5 + n19;
                                                                                                                                this.sb[0] = this.rb[0] << 12;
                                                                                                                                int n20 = 1;
                                                                                                                                while (n20 < i) {
                                                                                                                                    this.rb[n20] = n19 + this.rb[n20 - 1];
                                                                                                                                    this.sb[n20] = this.rb[n20] << 12;
                                                                                                                                    ++n20;
                                                                                                                                    if (h) {
                                                                                                                                        return;
                                                                                                                                    }
                                                                                                                                    if (h) {
                                                                                                                                        break;
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                --this.x;
                                                                                                                                return;
                                                                                                                            }
                                                                                                                            if (n18 > z3) {
                                                                                                                                this.vb[n17] = this.z - (j - n8);
                                                                                                                            }
                                                                                                                            ++n17;
                                                                                                                        }
                                                                                                                        if (h) {
                                                                                                                            break;
                                                                                                                        }
                                                                                                                    }
                                                                                                                    this.x = i + 1;
                                                                                                                    this.rb = new int[this.x];
                                                                                                                    this.sb = new int[this.x];
                                                                                                                    this.rb[i] = n5;
                                                                                                                    this.sb[i] = n7;
                                                                                                                    continue;
                                                                                                                }
                                                                                                            }
                                                                                                            if (h) {
                                                                                                                break;
                                                                                                            }
                                                                                                        }
                                                                                                        this.vb = new int[i];
                                                                                                        j = 0;
                                                                                                        continue;
                                                                                                    }
                                                                                                }
                                                                                                if (h) {
                                                                                                    break;
                                                                                                }
                                                                                            }
                                                                                            --this.w;
                                                                                            i = 0;
                                                                                            n8 = 1 << 4;
                                                                                            continue;
                                                                                        }
                                                                                    }
                                                                                    if (n13 > z2) {
                                                                                        this.ub[n12] = this.z - (j - n8);
                                                                                    }
                                                                                    ++n12;
                                                                                }
                                                                                if (h) {
                                                                                    break;
                                                                                }
                                                                            }
                                                                            this.w = i + 1;
                                                                            this.pb = new int[this.w];
                                                                            this.qb = new int[this.w];
                                                                            this.pb[i] = n5;
                                                                            this.qb[i] = n7;
                                                                            continue;
                                                                        }
                                                                    }
                                                                    if (h) {
                                                                        break;
                                                                    }
                                                                }
                                                                this.ub = new int[i];
                                                                j = 0;
                                                                continue;
                                                            }
                                                        }
                                                        if (h) {
                                                            break;
                                                        }
                                                    }
                                                    --this.v;
                                                    i = 0;
                                                    n8 = 1 << 3;
                                                    continue;
                                                }
                                            }
                                            if (n9 > z) {
                                                this.tb[k] = this.z - (j - n8);
                                            }
                                            ++k;
                                        }
                                        if (h) {
                                            break;
                                        }
                                    }
                                    this.v = i + 1;
                                    this.nb = new int[this.v];
                                    this.ob = new int[this.v];
                                    this.nb[i] = n5;
                                    this.ob[i] = n7;
                                    continue;
                                }
                            }
                            if (h) {
                                break;
                            }
                        }
                        this.tb = new int[i];
                        j = 0;
                        continue;
                    }
                }
                if (h) {
                    break;
                }
            }
            i = 0;
            continue;
        }
    }
    
    private void i() {
        final double n = 2.44140625E-4;
        double n2 = 0.0;
        if (this.Bb == null) {
            this.Bb = new int[4097];
            for (int i = 0; i < 4096; ++i) {
                this.Bb[i] = (int)(Math.sqrt(1.0 + n2 * n2) * 4096.0 + 0.5);
                n2 += n;
            }
            this.Bb[4096] = (int)(Math.sqrt(2.0) * 4096.0);
        }
    }
    
    private boolean b(final int n, final int n2) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final double n3 = 2.44140625E-4;
        double n4 = 0.0;
        this.wb = new int[4097];
        this.xb = new int[this.n + 1];
        this.yb = new int[this.m + 1];
        this.zb = new int[this.n / 8 + 1];
        this.Ab = new int[this.m / 8 + 1];
        int i = 0;
        while (true) {
            while (i < this.n) {
                this.xb[i] = i;
                ++i;
                if (h) {
                    int j = 0;
                    while (true) {
                        while (j < this.n / 8) {
                            this.zb[j] = j;
                            ++j;
                            if (h) {
                                int k = 0;
                                while (true) {
                                    while (k < this.m) {
                                        this.yb[k] = j;
                                        j += this.l;
                                        ++k;
                                        if (h) {
                                            int l = 0;
                                            while (true) {
                                                while (l < this.m / 8) {
                                                    this.Ab[l] = j;
                                                    j += this.l / 8;
                                                    ++l;
                                                    if (h) {
                                                        final int n5 = 8;
                                                        this.y = 1 << n5;
                                                        final int n6 = n5;
                                                        if (!h && n6 <= 0) {}
                                                        final int n7 = n6;
                                                        final int n8 = (n << n5) / 2;
                                                        this.db = n / 6.283185307179586 * this.y;
                                                        this.A = (int)(n2 * this.y * 0.5) + n7;
                                                        int n9 = 0;
                                                        while (n9 < 4096) {
                                                            this.wb[n9] = n8 - (int)(Math.atan(n4 / (1.0 - n4)) * this.db + 0.5);
                                                            n4 += n3;
                                                            ++n9;
                                                            if (h) {
                                                                return true;
                                                            }
                                                            if (h) {
                                                                break;
                                                            }
                                                        }
                                                        this.wb[4096] = n8 - (int)(this.db * 1.5707963267948966 + 0.5);
                                                        return true;
                                                    }
                                                    if (h) {
                                                        break;
                                                    }
                                                }
                                                this.Ab[this.m >> 3] = j - this.l / 8;
                                                continue;
                                            }
                                        }
                                        if (h) {
                                            break;
                                        }
                                    }
                                    this.yb[this.m] = j - this.l;
                                    j = 0;
                                    continue;
                                }
                            }
                            if (h) {
                                break;
                            }
                        }
                        this.zb[this.n >> 3] = this.n / 8 - 1;
                        j = 0;
                        continue;
                    }
                }
                if (h) {
                    break;
                }
            }
            this.xb[this.n] = this.n - 1;
            continue;
        }
    }
    
    private final void a(final int n, final double n2, final int n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9, final int n10, final int n11, final boolean b, final int n12, final int[] array, final int[] array2, final e[] array3, final c[] array4, final d[] array5, final int n13) {
        int n14 = 2;
        final double n15 = ((n5 < 0.0) ? (-n5) : n5) + n8;
        int n16;
        int[] array6;
        int[] array7;
        int[] array8;
        if (n15 < 0.7853981633974483) {
            n14 = 4;
            n16 = this.x;
            array6 = this.vb;
            array7 = this.rb;
            array8 = this.sb;
        }
        else if (n15 < 1.5707963267948966) {
            n14 = 3;
            n16 = this.w;
            array6 = this.ub;
            array7 = this.pb;
            array8 = this.qb;
        }
        else {
            n16 = this.v;
            array6 = this.tb;
            array7 = this.nb;
            array8 = this.ob;
        }
        final int n17 = ((int)(this.eb * n9 + this.B / 2.0) % this.B + this.B) % this.B - 128;
        final int[] wb = this.wb;
        final int[] bb = this.Bb;
        final int[] db = this.Db;
        final int[] lb = this.lb;
        final int[] mb = this.mb;
        double n18 = n2 * (n4 * n7 - (n >> 1) * n6 + 0.5);
        double n19 = n2 * (n4 * n6 + (n >> 1) * n7 - 0.5);
        final double n20 = n2 * n6;
        final double n21 = -n2 * n7;
        if (n11 == 1) {
            for (int i = 0; i < n; ++i) {
                int n22 = (int)n18;
                final int n23 = (int)n19;
                n18 += n20;
                n19 += n21;
                final int[] array9 = wb;
                if (n22 == 0) {
                    n22 = 1;
                }
                int n24 = array9[array8[n16] / (n22 + array7[n16])];
                int n25;
                if (array7[n16] < n22) {
                    n25 = Math.max(1, n22 * bb[array8[n16] / n22] >> 12);
                }
                else {
                    n25 = Math.max(1, array7[n16] * bb[(n22 << 12) / array7[n16]] >> 12);
                }
                double n26;
                int n27;
                boolean b2;
                if (n23 < 0) {
                    n26 = (-n23 << 12) * this.db;
                    n27 = n10 + (int)((n26 / n25 + 0.5) / 4096.0);
                    b2 = false;
                }
                else {
                    n26 = (n23 << 12) * this.db;
                    n27 = n10 - (int)((n26 / n25 + 0.5) / 4096.0);
                    b2 = true;
                }
                int n28 = this.M + n27;
                int n29 = lb[i];
                int n30 = mb[i];
                for (int j = 0; j < n16; ++j) {
                    final int n31 = array9[array8[j] / (n22 + array7[j])];
                    int n32;
                    if (array7[j] < n22) {
                        n32 = Math.max(1, n22 * bb[array8[j] / n22] >> 12);
                    }
                    else {
                        n32 = Math.max(1, array7[j] * bb[(n22 << 12) / array7[j]] >> 12);
                    }
                    int n33;
                    if (b2) {
                        n33 = n10 - (int)((n26 / n32 + 0.5) / 4096.0);
                    }
                    else {
                        n33 = n10 + (int)((n26 / n32 + 0.5) / 4096.0);
                    }
                    final int n34 = this.M + n33;
                    final int n35 = n31 - n24 >> n14;
                    final int n36 = n34 - n28 >> n14;
                    int n37 = array6[j];
                    while (n37-- > 0) {
                        final int n38 = n28 - 128;
                        final int n39 = n38 & 0xFF;
                        final int n40 = 256 - n39;
                        int n41 = n38 >> 8;
                        if (n41 < 0) {
                            n41 = 0;
                        }
                        else if (n41 >= this.m) {
                            n41 = this.m - 1;
                        }
                        final int n42 = array[n41];
                        final int n43 = array[n41 + 1];
                        final int n44 = n17 + n24 - 128;
                        final int n45 = n44 & 0xFF;
                        final int n46 = 256 - n45;
                        int n47 = n44 >> 8;
                        if (n47 >= n3) {
                            n47 -= n3;
                        }
                        final int n48 = array2[n47];
                        final int n49 = array2[n47 + 1];
                        int a;
                        int a2;
                        int a3;
                        int a4;
                        if (b) {
                            final int d = this.d(n48, n12);
                            if (array4[d].h()) {
                                if (array3[d].a() == null) {
                                    this.a(d, array4[d], array5, array3);
                                }
                                a = array3[d].a(n42, n48, n3, n12, n13);
                                a2 = array3[d].a(n43, n48, n3, n12, n13);
                            }
                            else {
                                a = db[this.Ab[n41 >> 3] + this.zb[n47 >> 3]];
                                a2 = db[this.Ab[n41 + 1 >> 3] + this.zb[n47 >> 3]];
                            }
                            final int d2 = this.d(n49, n12);
                            if (array4[d2].h()) {
                                if (array3[d2].a() == null) {
                                    this.a(d2, array4[d2], array5, array3);
                                }
                                a3 = array3[d2].a(n42, n49, n3, n12, n13);
                                a4 = array3[d2].a(n43, n49, n3, n12, n13);
                            }
                            else {
                                a3 = db[this.Ab[n41 >> 3] + this.zb[n47 + 1 >> 3]];
                                a4 = db[this.Ab[n41 + 1 >> 3] + this.zb[n47 + 1 >> 3]];
                            }
                        }
                        else {
                            a = db[n42 + n48];
                            a3 = db[n42 + n49];
                            a2 = db[n43 + n48];
                            a4 = db[n43 + n49];
                        }
                        this.Cb[n30--] = ((((a & 0xFF00FF) * n46 + (a3 & 0xFF00FF) * n45 >> 8 & 0xFF00FF) * n40 + ((a2 & 0xFF00FF) * n46 + (a4 & 0xFF00FF) * n45 >> 8 & 0xFF00FF) * n39 >> 8 & 0xFF00FF) | (((a & 0xFF00) * n46 + (a3 & 0xFF00) * n45) * n40 + ((a2 & 0xFF00) * n46 + (a4 & 0xFF00) * n45) * n39 >> 16 & 0xFF00) | 0xFF000000);
                        final int n50 = n17 - n24 - 128;
                        final int n51 = n50 & 0xFF;
                        final int n52 = 256 - n51;
                        int n53 = n50 >> 8;
                        if (n53 < 0) {
                            n53 += n3;
                        }
                        final int n54 = array2[n53];
                        final int n55 = array2[n53 + 1];
                        int a5;
                        int a6;
                        int a7;
                        int a8;
                        if (b) {
                            final int d3 = this.d(n54, n12);
                            if (array4[d3].h()) {
                                if (array3[d3].a() == null) {
                                    this.a(d3, array4[d3], array5, array3);
                                }
                                a5 = array3[d3].a(n42, n54, n3, n12, n13);
                                a6 = array3[d3].a(n43, n54, n3, n12, n13);
                            }
                            else {
                                a5 = db[this.Ab[n41 >> 3] + this.zb[n53 >> 3]];
                                a6 = db[this.Ab[n41 + 1 >> 3] + this.zb[n53 >> 3]];
                            }
                            final int d4 = this.d(n55, n12);
                            if (array4[d4].h()) {
                                if (array3[d4].a() == null) {
                                    this.a(d4, array4[d4], array5, array3);
                                }
                                a7 = array3[d4].a(n42, n55, n3, n12, n13);
                                a8 = array3[d4].a(n43, n55, n3, n12, n13);
                            }
                            else {
                                a7 = db[this.Ab[n41 >> 3] + this.zb[n53 + 1 >> 3]];
                                a8 = db[this.Ab[n41 + 1 >> 3] + this.zb[n53 + 1 >> 3]];
                            }
                        }
                        else {
                            a5 = db[n42 + n54];
                            a7 = db[n42 + n55];
                            a6 = db[n43 + n54];
                            a8 = db[n43 + n55];
                        }
                        this.Cb[n29++] = ((((a5 & 0xFF00FF) * n52 + (a7 & 0xFF00FF) * n51 >> 8 & 0xFF00FF) * n40 + ((a6 & 0xFF00FF) * n52 + (a8 & 0xFF00FF) * n51 >> 8 & 0xFF00FF) * n39 >> 8 & 0xFF00FF) | (((a5 & 0xFF00) * n52 + (a7 & 0xFF00) * n51) * n40 + ((a6 & 0xFF00) * n52 + (a8 & 0xFF00) * n51) * n39 >> 16 & 0xFF00) | 0xFF000000);
                        n24 += n35;
                        n28 += n36;
                    }
                    n24 = n31;
                    n28 = n34;
                }
            }
        }
        else {
            for (int k = 0; k < n; ++k) {
                int n56 = (int)n18;
                final int n57 = (int)n19;
                n18 += n20;
                n19 += n21;
                final int[] array10 = wb;
                if (n56 == 0) {
                    n56 = 1;
                }
                int n58 = array10[array8[n16] / (n56 + array7[n16])];
                int n59;
                if (array7[n16] < n56) {
                    n59 = Math.max(1, n56 * bb[array8[n16] / n56] >> 12);
                }
                else {
                    n59 = Math.max(1, array7[n16] * bb[(n56 << 12) / array7[n16]] >> 12);
                }
                double n60;
                int n61;
                boolean b3;
                if (n57 < 0) {
                    n60 = (-n57 << 12) * this.db;
                    n61 = n10 + (int)((n60 / n59 + 0.5) / 4096.0);
                    b3 = false;
                }
                else {
                    n60 = (n57 << 12) * this.db;
                    n61 = n10 - (int)((n60 / n59 + 0.5) / 4096.0);
                    b3 = true;
                }
                int n62 = this.M + n61;
                int n63 = lb[k];
                int n64 = mb[k];
                for (int l = 0; l < n16; ++l) {
                    final int n65 = array10[array8[l] / (n56 + array7[l])];
                    int n66;
                    if (array7[l] < n56) {
                        n66 = Math.max(1, n56 * bb[array8[l] / n56] >> 12);
                    }
                    else {
                        n66 = Math.max(1, array7[l] * bb[(n56 << 12) / array7[l]] >> 12);
                    }
                    int n67;
                    if (b3) {
                        n67 = n10 - (int)((n60 / n66 + 0.5) / 4096.0);
                    }
                    else {
                        n67 = n10 + (int)((n60 / n66 + 0.5) / 4096.0);
                    }
                    final int n68 = this.M + n67;
                    final int n69 = n65 - n58 >> n14;
                    final int n70 = n68 - n62 >> n14;
                    int n71 = array6[l];
                    while (n71-- > 0) {
                        int n72 = n62 - 128 >> 8;
                        if (n72 < 0) {
                            n72 = 0;
                        }
                        else if (n72 >= this.m) {
                            n72 = this.m - 1;
                        }
                        final int n73 = array[n72];
                        int n74 = n17 + n58 - 128 >> 8;
                        if (n74 >= n3) {
                            n74 -= n3;
                        }
                        final int n75 = array2[n74];
                        int a9;
                        if (b) {
                            final int d5 = this.d(n75, n12);
                            if (array4[d5].h()) {
                                if (array3[d5].a() == null) {
                                    this.a(d5, array4[d5], array5, array3);
                                }
                                a9 = array3[d5].a(n73, n75, n3, n12, n13);
                            }
                            else {
                                a9 = db[this.Ab[n72 >> 3] + this.zb[n74 >> 3]];
                            }
                        }
                        else {
                            a9 = db[n73 + n75];
                        }
                        this.Cb[n64--] = a9;
                        int n76 = n17 - n58 - 128 >> 8;
                        if (n76 < 0) {
                            n76 += n3;
                        }
                        final int n77 = array2[n76];
                        int a10;
                        if (b) {
                            final int d6 = this.d(n77, n12);
                            if (array4[d6].h()) {
                                if (array3[d6].a() == null) {
                                    this.a(d6, array4[d6], array5, array3);
                                }
                                a10 = array3[d6].a(n73, n77, n3, n12, n13);
                            }
                            else {
                                a10 = db[this.Ab[n72 >> 3] + this.zb[n76 >> 3]];
                            }
                        }
                        else {
                            a10 = db[n73 + n77];
                        }
                        this.Cb[n63++] = a10;
                        n58 += n69;
                        n62 += n70;
                    }
                    n58 = n65;
                    n62 = n68;
                }
            }
        }
    }
    
    private boolean c(final int n, final int n2) {
        return true;
    }
    
    private void j() {
        this.n = (int)(6.283185307179586 * this.gb + 0.5);
        final double n = Math.tan(this.X) * this.n / 6.283185307179586;
        final double n2 = n - this.m;
        this.ib = this.X - Math.atan(n2 * 6.283185307179586 / this.n);
        this.o = (int)(2.0 * Math.max(Math.abs(n), Math.abs(n2)) + 0.5);
        this.L = (int)(Math.max(Math.abs(n), Math.abs(n2)) - n + 0.5) - 1;
    }
    
    private int d(final int n, final int n2) {
        return n >> n2;
    }
    
    private void a(final int n, final c c, final d[] array, final e[] array2) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final int n2 = this.J - 1;
        if (!h) {
            if (n2 == this.K) {
                array2[array[0].a()].a(null);
                this.K = 0;
                array2[n].a(array[0].a(c));
                array[0].a(n);
                return;
            }
            array[++this.K].a();
        }
        final int n3 = n2;
        if (!h) {
            if (n3 != -1) {
                array2[n3].a(null);
            }
            array2[n].a(array[this.K].a(c));
            array[this.K].a(n);
        }
    }
}
