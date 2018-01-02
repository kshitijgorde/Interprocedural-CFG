// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.c;

import java.awt.Component;

public final class j extends f
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
    private int O;
    private int P;
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
    private double jb;
    private double kb;
    private double lb;
    private double mb;
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
    private int[] Eb;
    private int[] Fb;
    private int[] Gb;
    private int[] Hb;
    private int[] Ib;
    private int[] Jb;
    private int[] Kb;
    private int[] Lb;
    private e[] Mb;
    private d[] Nb;
    private c[] Ob;
    private boolean Pb;
    private boolean Qb;
    
    public j() {
        this.j = 0;
        this.N = -1;
        this.Y = 0.0;
        this.ab = 1.5707963267948966;
        this.bb = -1.5707963267948966;
        this.Gb = new int[6];
        this.Hb = new int[6];
        this.Kb = null;
        this.Pb = false;
        this.Qb = true;
        this.g();
    }
    
    public void a(final int n, final int n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9, final double n10, final double y, final double v, final double s, final boolean b, final int n11, final int n12, final Component component) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        this.k(n9);
        j j = this;
        j i = null;
        Label_0116: {
            final double n13;
            Label_0091: {
                Label_0047: {
                    if (!h) {
                        this.j(n10);
                        if (n5 != n6) {
                            n13 = dcmpl(n3, n4);
                            if (h) {
                                break Label_0091;
                            }
                            if (n13 != 0) {
                                break Label_0047;
                            }
                        }
                        j = this;
                    }
                    j.Qb = false;
                }
                this.f(n3);
                this.g(n4);
                this.d(n5);
                this.e(n6);
                this.h(n7);
                this.i(n8);
                i = this;
                if (h) {
                    break Label_0116;
                }
                final boolean qb = this.Qb;
            }
            if (n13 != 0) {
                this.a(n, n2, b);
            }
            this.S = s;
            this.V = v;
            i = this;
        }
        i.Y = y;
    }
    
    public void a(final int j) {
        this.j = j;
    }
    
    public void a(final c[] ob, final int n) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        this.Ob = ob;
        this.Pb = true;
        this.B = ob.length / n;
        this.C = this.k / n;
        this.D = this.l / this.B;
        this.O = this.C - 1;
        this.P = this.D - 1;
        (this.Lb = new int[this.B])[0] = 0;
        int i = 1;
        while (true) {
            while (i < this.B) {
                this.Lb[i] = n + this.Lb[i - 1];
                ++i;
                if (h) {
                    final int m = this.M;
                    if (!h) {
                        if (m == 0) {
                            this.Qb = false;
                        }
                        this.Nb = new d[this.M];
                    }
                    int j = m;
                    while (true) {
                        while (j < this.M) {
                            this.Nb[j] = new d(new int[this.C * this.D]);
                            ++j;
                            if (h) {
                                int k = 0;
                                while (k < i) {
                                    this.Mb[k] = new e();
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
                        this.Mb = new e[i];
                        continue;
                    }
                }
                if (h) {
                    break;
                }
            }
            this.E = this.b(this.C);
            this.F = this.b(this.D);
            i = ob.length;
            this.M = 16777216 / (4 * this.C * this.D);
            continue;
        }
    }
    
    public void a(final int[] jb, final int n, final int n2, final int n3, final int n4) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        this.Pb = false;
        int n5 = n3;
        Label_0049: {
            if (!h) {
                if (n3 == this.k) {
                    n5 = n4;
                    if (h) {
                        break Label_0049;
                    }
                    if (n4 == this.l) {
                        this.Jb = jb;
                        if (!h) {
                            return;
                        }
                    }
                }
                n5 = 0;
            }
        }
        int i = n5;
        while (i < n4) {
            System.arraycopy(jb, i * n3, this.Jb, n + (i + n2) * this.k, n3);
            ++i;
            if (h) {
                break;
            }
        }
    }
    
    public void a(final int o, final int p4, final int[] ib, final Component component) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        this.o = o;
        this.p = p4;
        j j = this;
        final int p5;
        Label_0052: {
            Label_0046: {
                if (!h) {
                    if (this.o >= 17) {
                        p5 = this.p;
                        if (h) {
                            break Label_0052;
                        }
                        if (p5 >= 0) {
                            break Label_0046;
                        }
                    }
                    j = this;
                }
                j.Qb = false;
            }
            this.Ib = ib;
        }
        int i = p5;
        while (true) {
            while (i < this.Ib.length) {
                this.Ib[i] = 61455;
                ++i;
                if (h) {
                    final int p6 = this.p;
                    j k = null;
                    Label_0171: {
                        Label_0167: {
                            if (!h) {
                                if (p6 > 0) {
                                    final boolean b2;
                                    final boolean b = b2 = this.Qb;
                                    if (h) {
                                        break Label_0167;
                                    }
                                    if (b) {
                                        this.a(this.ib, this.o, this.p);
                                    }
                                }
                                k = this;
                                if (h) {
                                    break Label_0171;
                                }
                                boolean b2 = this.Qb;
                            }
                        }
                        if (p6 == 0) {
                            return;
                        }
                        k = this;
                    }
                    k.a(this.S, this.V, this.Y);
                    return;
                }
                if (h) {
                    break;
                }
            }
            this.v = this.o + 1 >> 1;
            this.q = this.o / 2;
            this.w = this.p / 2;
            continue;
        }
    }
    
    public boolean a(final int k, final int l, final boolean b) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final boolean c = this.c(k, l);
        if (!h && c) {
            this.k = k;
            this.l = l;
            j j = this;
            Label_0060: {
                if (!h) {
                    this.h();
                    if (b) {
                        this.Pb = true;
                        if (!h) {
                            break Label_0060;
                        }
                    }
                    j = this;
                }
                j.Pb = false;
            }
            this.lb = this.l * this.mb / this.k;
            this.jb = this.m / 6.283185307179586;
            this.kb = this.jb * 256.0;
            this.b(this.m, this.n);
            this.ib = 262144.0 / Math.max(this.m, this.n);
            this.jb = this.m / 6.283185307179586;
            this.kb = this.jb * 256.0;
            this.H = (int)(this.n / 2 - this.n * this.Z / 3.141592653589793 + 0.5) - 1;
            this.L = -this.u * (this.H + 1);
            this.I = this.l + this.H;
            this.J = (int)(this.n * 0.5 - this.n * this.T / 3.141592653589793) - 1;
            this.K = (int)(this.n * 0.5 - this.n * this.U / 3.141592653589793) - 1;
            j i = this;
            int x = 0;
            Label_0315: {
                if (!h) {
                    if (this.J > this.H) {
                        x = this.J;
                        break Label_0315;
                    }
                    i = this;
                }
                x = i.H;
            }
            this.x = x;
            j m = this;
            int y = 0;
            Label_0346: {
                if (!h) {
                    if (this.K < this.I) {
                        y = this.K;
                        break Label_0346;
                    }
                    m = this;
                }
                y = m.I;
            }
            this.y = y;
            final int n = this.y + 1;
            final int n2 = this.n;
            Label_0513: {
                j j2 = null;
                Label_0478: {
                    Label_0428: {
                        if (!h) {
                            Label_0422: {
                                if (n == n2) {
                                    final int x2 = this.x;
                                    final int n3 = -1;
                                    if (h) {
                                        break Label_0428;
                                    }
                                    if (x2 != n3) {
                                        final double y2;
                                        final double n4 = y2 = dcmpl(this.Q - this.R, 6.283185307179586);
                                        if (h) {
                                            break Label_0422;
                                        }
                                        if (n4 == 0) {
                                            this.fb = -1.5707963267948966;
                                            if (!h) {
                                                break Label_0513;
                                            }
                                        }
                                    }
                                }
                                j2 = this;
                                if (h) {
                                    break Label_0478;
                                }
                                double y2 = this.y;
                            }
                            final int n5 = this.n - 1;
                        }
                    }
                    if (n != n2) {
                        j2 = this;
                        if (h) {
                            break Label_0478;
                        }
                        if (this.x == -1) {
                            j2 = this;
                            if (h) {
                                break Label_0478;
                            }
                            if (this.Q - this.R == 6.283185307179586) {
                                this.fb = 1.5707963267948966;
                                if (!h) {
                                    break Label_0513;
                                }
                            }
                        }
                    }
                    j2 = this;
                }
                j2.fb = (Math.min(this.Z, this.T) + Math.max(this.Z - this.lb, this.U)) * 0.5;
            }
            this.z = (int)(this.m * this.R / 6.283185307179586 + 0.5);
            this.A = (int)(this.m * this.Q / 6.283185307179586 + 0.5) - 1;
            j j3 = this;
            int a = 0;
            Label_0589: {
                if (!h) {
                    if (this.A < this.k) {
                        a = this.A;
                        break Label_0589;
                    }
                    j3 = this;
                }
                a = j3.k;
            }
            this.A = a;
            return true;
        }
        return c;
    }
    
    public double b() {
        return this.V;
    }
    
    public double d() {
        return this.Y;
    }
    
    public double c() {
        return this.S;
    }
    
    public double a(double s) {
        final boolean h = com.easypano.tourweaver.c.f.h;
    Label_0143_Outer:
        while (true) {
        Label_0143:
            while (true) {
                while (s > 6.283185307179586) {
                    s -= 6.283185307179586;
                    if (h) {
                        final double s2 = this.S;
                        if (!h) {
                            j j = null;
                            Label_0144: {
                                if (s2 != s) {
                                    j = this;
                                    if (h) {
                                        break Label_0144;
                                    }
                                    if (this.Qb) {
                                        this.a(this.p, this.w, this.ib, this.m, this.eb, this.V, this.db, this.cb, this.gb, s, this.L, this.Cb);
                                        j = this;
                                        if (h) {
                                            break Label_0144;
                                        }
                                        if (this.a(this.gb, this.V)) {
                                            this.S = s;
                                        }
                                    }
                                }
                                j = this;
                            }
                            final double s3 = j.S;
                        }
                        return s2;
                    }
                    if (h) {
                        break;
                    }
                }
                while (s < 0.0) {
                    s += 6.283185307179586;
                    if (h) {
                        continue Label_0143;
                    }
                    if (h) {
                        break;
                    }
                }
                break;
            }
            continue Label_0143_Outer;
        }
    }
    
    public double b(final double v) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final double v2 = this.V;
        if (!h) {
            j j = null;
            Label_0218: {
                Label_0217: {
                    if (v2 != v) {
                        j = this;
                        if (h) {
                            break Label_0218;
                        }
                        if (this.Qb) {
                            final double n = dcmpl(v, this.ab);
                            double cos = 0.0;
                            Label_0133: {
                                if (!h) {
                                    if (n > 0) {
                                        this.V = this.ab;
                                        this.cb = Math.cos(this.V);
                                        this.db = Math.sin(this.V);
                                        if (!h) {
                                            break Label_0217;
                                        }
                                    }
                                    cos = v;
                                    if (h) {
                                        break Label_0133;
                                    }
                                    final double n2 = dcmpg(v, this.bb);
                                }
                                if (n < 0) {
                                    this.V = this.bb;
                                    this.cb = Math.cos(this.V);
                                    this.db = Math.sin(this.V);
                                    if (!h) {
                                        break Label_0217;
                                    }
                                }
                                cos = Math.cos(v);
                            }
                            final double cb = cos;
                            final double sin = Math.sin(v);
                            this.a(this.p, this.w, this.ib, this.m, this.eb, v, sin, cb, this.gb, this.S, this.L, this.Cb);
                            j = this;
                            if (h) {
                                break Label_0218;
                            }
                            if (this.a(this.gb, v)) {
                                this.V = v;
                                this.cb = cb;
                                this.db = sin;
                            }
                        }
                    }
                }
                j = this;
            }
            final double v3 = j.V;
        }
        return v2;
    }
    
    public double c(double n) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final double y = this.Y;
        if (!h) {
            j j = null;
            Label_0656: {
                Label_0655: {
                    if (y != n) {
                        j = this;
                        if (h) {
                            break Label_0656;
                        }
                        if (this.Qb) {
                            double q;
                            final double n2 = q = dcmpl(n, this.W);
                            if (!h) {
                                if (n2 > 0) {
                                    n = this.W;
                                    final double atan = Math.atan(this.p * Math.tan(n * 0.5) / this.o);
                                    final double eb = this.q / Math.tan(n * 0.5);
                                    this.a(this.p, this.w, this.ib, this.m, eb, this.V, this.db, this.cb, atan, this.S, this.L, this.Cb);
                                    j = this;
                                    if (h) {
                                        break Label_0656;
                                    }
                                    if (!this.a(atan, this.V)) {
                                        break Label_0655;
                                    }
                                    this.eb = eb;
                                    this.Y = n;
                                    this.gb = atan;
                                    if (!h) {
                                        break Label_0655;
                                    }
                                }
                                final int n3;
                                q = (n3 = dcmpg(n, this.X));
                            }
                            if (!h) {
                                if (n2 < 0) {
                                    n = this.X;
                                    final double atan2 = Math.atan(this.p * Math.tan(n * 0.5) / this.o);
                                    final double eb2 = this.q / Math.tan(n * 0.5);
                                    this.a(this.p, this.w, this.ib, this.m, eb2, this.V, this.db, this.cb, atan2, this.S, this.L, this.Cb);
                                    j = this;
                                    if (h) {
                                        break Label_0656;
                                    }
                                    if (!this.a(atan2, this.V)) {
                                        break Label_0655;
                                    }
                                    this.eb = eb2;
                                    this.Y = n;
                                    this.gb = atan2;
                                    if (!h) {
                                        break Label_0655;
                                    }
                                }
                                q = this.q;
                            }
                            final double eb3 = q / Math.tan(n * 0.5);
                            final double atan3 = Math.atan(this.p * Math.tan(n * 0.5) / this.o);
                            this.a(this.p, this.w, this.ib, this.m, eb3, this.V, this.db, this.cb, atan3, this.S, this.L, this.Cb);
                            j i = this;
                            if (!h) {
                                if (this.a(atan3, this.V)) {
                                    this.eb = eb3;
                                    this.Y = n;
                                    this.gb = atan3;
                                    if (!h) {
                                        break Label_0655;
                                    }
                                }
                                i = this;
                            }
                            double v = i.V;
                            final double n4 = dcmpl(v, this.fb);
                            final double n5;
                            Label_0531: {
                                Label_0526: {
                                    if (!h) {
                                        if (n4 > 0) {
                                            v -= (atan3 - this.gb) * 2.0;
                                            if (!h) {
                                                break Label_0526;
                                            }
                                        }
                                        n5 = v;
                                        if (h) {
                                            break Label_0531;
                                        }
                                        final double n6 = dcmpg(n5, this.fb);
                                    }
                                    if (n4 < 0) {
                                        v += (atan3 - this.gb) * 2.0;
                                    }
                                }
                                Math.cos(v);
                            }
                            final double cb = n5;
                            final double sin = Math.sin(v);
                            final double eb4 = this.o * 0.5 / Math.tan(n * 0.5);
                            this.a(this.p, this.w, this.ib, this.m, eb4, v, sin, cb, atan3, this.S, this.L, this.Cb);
                            j k = this;
                            final double gb = atan3;
                            if (!h) {
                                if (!this.a(gb, v) && !h) {
                                    break Label_0655;
                                }
                                this.eb = eb4;
                                this.Y = n;
                                this.V = v;
                                this.cb = cb;
                                this.db = sin;
                                k = this;
                            }
                            k.gb = gb;
                        }
                    }
                }
                j = this;
            }
            final double y2 = j.Y;
        }
        return y;
    }
    
    public void a(final boolean b) {
        if (this.Qb) {
            if (this.Pb) {
                if (this.j == 0) {
                    this.a(this.p, this.w, this.ib, this.m, this.l, this.eb, this.V, this.db, this.cb, this.gb, this.S, this.L, this.E, this.Db, this.Cb, this.Mb, this.Ob, this.Nb, this.F, this.k, this.Lb, this.P, this.O, this.M);
                }
                else if (!b) {
                    this.b(this.p, this.w, this.ib, this.m, this.l, this.eb, this.V, this.db, this.cb, this.gb, this.S, this.L, this.E, this.Db, this.Cb, this.Mb, this.Ob, this.Nb, this.F, this.k, this.Lb, this.P, this.O, this.M);
                }
                else {
                    this.c(this.p, this.w, this.ib, this.m, this.l, this.eb, this.V, this.db, this.cb, this.gb, this.S, this.L, this.E, this.Db, this.Cb, this.Mb, this.Ob, this.Nb, this.F, this.k, this.Lb, this.P, this.O, this.M);
                }
            }
            else {
                this.a(this.p, this.w, this.ib, this.m, this.l, this.eb, this.V, this.db, this.cb, this.gb, this.S, this.L, this.Db, this.Cb);
            }
        }
    }
    
    public void a() {
        this.Jb = null;
        this.Ib = null;
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
        this.zb = null;
        this.Ab = null;
        this.Bb = null;
        this.Cb = null;
        this.Db = null;
        this.Gb = null;
        this.Hb = null;
        this.Gb = new int[6];
        this.Hb = new int[6];
        this.Nb = null;
        this.Mb = null;
        System.gc();
    }
    
    public void a(final int n, final int n2) {
        this.a(n, n2 + this.H + 1, this.S, this.V, this.k, this.l, this.mb, this.lb, this.eb, this.n, this.o, this.p);
    }
    
    private void a(final int n, final int n2, double n3, final double n4, final int n5, final int n6, final double n7, final double n8, final double n9, final int n10, final int n11, final int n12) {
        n3 -= 3.141592653589793;
        final double cos = Math.cos(n3);
        final double sin = Math.sin(n3);
        double n13 = Math.cos(n4);
        double n14 = Math.sin(n4);
        if (n14 == 1.0) {
            n14 = Math.sin(n4 - 0.017453292519943295);
            n13 = Math.cos(n4 - 0.017453292519943295);
        }
        if (n14 == -1.0) {
            n14 = Math.sin(n4 + 0.017453292519943295);
            n13 = Math.cos(n4 + 0.017453292519943295);
        }
        final double n15 = (n - n5 / 2.0) / n5 * n7;
        final double n16 = (n10 / 2.0 - n2) / n6 * n8;
        final double cos2 = Math.cos(n15);
        final double sin2 = Math.sin(n15);
        final double cos3 = Math.cos(n16);
        final double sin3 = Math.sin(n16);
        final double n17 = cos * n13 * cos2 * cos3 + sin * n13 * sin2 * cos3 + n14 * sin3;
        if (n17 >= 0.0) {
            final double n18 = n9 / n17;
            final double n19 = n18 * sin3;
            super.f = (int)(n18 * cos3 * Math.sin(n15 - n3) + n11 / 2.0);
            super.g = (int)(n12 / 2.0 - (int)((n19 - n9 * n14) / n13));
        }
        else {
            super.f = n11 + 10000;
            super.g = n12 + 10000;
        }
    }
    
    private void d(double q) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        double n2;
        final double n = n2 = dcmpl(q, 6.283185307179586);
        if (!h) {
            if (n > 0) {
                q = 6.283185307179586;
            }
            final double n3;
            n2 = (n3 = dcmpg(q, 0.0));
        }
        Label_0050: {
            final double n4;
            Label_0049: {
                if (!h) {
                    if (n < 0) {
                        q = 0.0;
                    }
                    n4 = q;
                    if (h) {
                        break Label_0049;
                    }
                    n2 = dcmpl(n4, this.mb);
                }
                if (n2 <= 0) {
                    break Label_0050;
                }
                final double mb = this.mb;
            }
            q = n4;
        }
        this.Q = q;
    }
    
    private void e(double r) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final double n = dcmpl(r, 6.283185307179586);
        Label_0032: {
            final double n2;
            Label_0031: {
                if (!h) {
                    if (n > 0) {
                        r = 6.283185307179586;
                    }
                    n2 = r;
                    if (h) {
                        break Label_0031;
                    }
                    final double n3 = dcmpg(n2, 0.0);
                }
                if (n >= 0) {
                    break Label_0032;
                }
            }
            r = n2;
        }
        this.R = r;
    }
    
    private void f(final double t) {
        j j = this;
        double t2 = t;
        if (!com.easypano.tourweaver.c.f.h) {
            this.T = t;
            if (t <= 1.5707963267948966) {
                return;
            }
            j = this;
            t2 = 1.5707963267948966;
        }
        j.T = t2;
    }
    
    private void g(final double u) {
        j j = this;
        double u2 = u;
        if (!com.easypano.tourweaver.c.f.h) {
            this.U = u;
            if (u >= -1.5707963267948966) {
                return;
            }
            j = this;
            u2 = -1.5707963267948966;
        }
        j.U = u2;
    }
    
    private void h(final double w) {
        this.W = w;
        final double min = Math.min(2.6179938779914944, this.Q - this.R);
        if (w > min) {
            this.W = min;
        }
    }
    
    private void i(final double x) {
        j j = this;
        double x2 = x;
        if (!com.easypano.tourweaver.c.f.h) {
            this.X = x;
            if (x >= 0.5235987755982988) {
                return;
            }
            j = this;
            x2 = 0.5235987755982988;
        }
        j.X = x2;
    }
    
    private void j(final double z) {
        this.Z = z;
    }
    
    private void k(final double mb) {
        this.mb = mb;
    }
    
    private void a(double s, double v, double y) {
        final boolean h = com.easypano.tourweaver.c.f.h;
    Label_0072_Outer:
        while (true) {
        Label_0072:
            while (true) {
                while (s > 6.283185307179586) {
                    s -= 6.283185307179586;
                    if (h) {
                        double p3;
                        final double n = p3 = dcmpg(y, this.X);
                        if (!h) {
                            if (n < 0) {
                                y = this.X;
                            }
                            final int n2;
                            p3 = (n2 = dcmpl(y, this.W));
                        }
                        if (!h) {
                            if (n > 0) {
                                y = this.W;
                            }
                            p3 = this.p;
                        }
                        double n3 = Math.atan(p3 * Math.tan(y * 0.5) / this.o);
                        double eb = this.q / Math.tan(y * 0.5);
                        final double n4 = dcmpl(v, this.T);
                        final double n5;
                        Label_0183: {
                            Label_0179: {
                                if (!h) {
                                    if (n4 > 0) {
                                        v = this.T;
                                        if (!h) {
                                            break Label_0179;
                                        }
                                    }
                                    n5 = v;
                                    if (h) {
                                        break Label_0183;
                                    }
                                    final double n6 = dcmpg(n5, this.U);
                                }
                                if (n4 < 0) {
                                    v = this.U;
                                }
                            }
                            Math.cos(v);
                        }
                        double cos = n5;
                        double db = Math.sin(v);
                        this.a(this.p, this.w, this.ib, this.m, eb, v, db, cos, n3, s, this.L, this.Cb);
                        int a;
                        int n8;
                        final int n7 = n8 = (a = (this.a(n3, v) ? 1 : 0));
                        if (!h) {
                            if (n7 == 0) {
                                final double n9 = dcmpl(this.fb, 1.5707963267948966);
                                Label_0396: {
                                    final double fb;
                                    final double n11;
                                    Label_0393: {
                                        if (!h) {
                                            if (n9 == 0) {
                                                final double n10 = Math.atan(this.p * Math.tan(Math.min(this.W * 0.5, 1.5707963267948966 - this.U))) - 0.008726646259971648;
                                            }
                                            fb = this.fb;
                                            n11 = -1.5707963267948966;
                                            if (h) {
                                                break Label_0393;
                                            }
                                            final double n12 = dcmpl(fb, n11);
                                        }
                                        if (n9 == 0) {
                                            n3 = Math.atan(this.p * Math.tan(Math.min(this.W * 0.5, 1.5707963267948966 + this.T))) - 0.008726646259971648;
                                            if (!h) {
                                                break Label_0396;
                                            }
                                        }
                                        final double n13 = Math.abs(Math.min(this.Z, this.T) - Math.max(this.Z - this.lb, this.U)) * 0.5;
                                    }
                                    n3 = fb - n11;
                                }
                                v = this.fb;
                                cos = Math.cos(v);
                                db = Math.sin(v);
                                y = Math.atan(this.o * Math.tan(n3) / this.p) * 2.0;
                                int n16;
                                int x;
                                double n15;
                                final int n14 = (int)(n15 = (x = (n16 = (this.Pb ? 1 : 0))));
                                if (!h) {
                                    if (n14 != 0) {
                                        y = this.X;
                                    }
                                    final int n17;
                                    n15 = (n17 = (x = (n16 = dcmpl(y, this.W))));
                                }
                                if (!h) {
                                    if (n14 > 0) {
                                        y = this.W;
                                    }
                                    x = (int)(n15 = (n16 = dcmpg(y, 0.3141592653589793)));
                                }
                                int q = 0;
                                Label_0577: {
                                    Label_0547: {
                                        if (!h) {
                                            if (n15 < 0) {
                                                this.Qb = false;
                                                if (!h) {
                                                    break Label_0547;
                                                }
                                            }
                                            n16 = (x = this.x);
                                        }
                                        final int n18 = this.n / 2 - 1;
                                        Label_0542: {
                                            if (!h) {
                                                if (x > n18) {
                                                    break Label_0542;
                                                }
                                                q = (n16 = this.y);
                                                if (h) {
                                                    break Label_0577;
                                                }
                                                final int n19 = this.n / 2 - 1;
                                            }
                                            if (n16 >= n18) {
                                                break Label_0547;
                                            }
                                        }
                                        y = 0.5235987755982988;
                                    }
                                    n3 = Math.atan(this.p * Math.tan(y * 0.5) / this.o);
                                    q = this.q;
                                }
                                eb = q / Math.tan(y * 0.5);
                            }
                            this.a(this.p, this.w, this.ib, this.m, eb, v, db, cos, n3, s, this.L, this.Cb);
                            n8 = this.Gb[0];
                        }
                        final int a2 = this.A;
                        j j = null;
                        final double gb;
                        Label_0944: {
                            final int n29;
                            Label_0899: {
                                Label_0849: {
                                    final int n20;
                                    Label_0832: {
                                        if (!h) {
                                            if (n8 < a2) {
                                                n20 = this.Gb[0];
                                                final int z = this.z;
                                                if (h) {
                                                    break Label_0832;
                                                }
                                                if (n20 > z) {
                                                    final int n21 = this.Gb[3];
                                                    final int a3 = this.A;
                                                    if (h) {
                                                        break Label_0832;
                                                    }
                                                    if (n21 < a3) {
                                                        final int n22 = this.Gb[3];
                                                        final int z2 = this.z;
                                                        if (h) {
                                                            break Label_0832;
                                                        }
                                                        if (n22 > z2) {
                                                            final int n23 = this.Gb[2];
                                                            final int a4 = this.A;
                                                            if (h) {
                                                                break Label_0832;
                                                            }
                                                            if (n23 < a4) {
                                                                final int n24 = this.Gb[2];
                                                                final int z3 = this.z;
                                                                if (h) {
                                                                    break Label_0832;
                                                                }
                                                                if (n24 > z3) {
                                                                    final int n25 = this.Gb[5];
                                                                    final int a5 = this.A;
                                                                    if (h) {
                                                                        break Label_0832;
                                                                    }
                                                                    if (n25 < a5) {
                                                                        final int n26 = this.Gb[5];
                                                                        final int z4 = this.z;
                                                                        if (h) {
                                                                            break Label_0832;
                                                                        }
                                                                        if (n26 > z4) {
                                                                            final int n27 = this.Gb[0];
                                                                            final int n28 = this.Gb[2];
                                                                            if (h) {
                                                                                break Label_0832;
                                                                            }
                                                                            if (n27 < n28) {
                                                                                n29 = this.Gb[3];
                                                                                if (h) {
                                                                                    break Label_0899;
                                                                                }
                                                                                if (n29 < this.Gb[5]) {
                                                                                    break Label_0849;
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
                                            a = this.A - this.z;
                                            if (h) {
                                                break Label_0899;
                                            }
                                            final int n30 = this.m - 1;
                                        }
                                    }
                                    if (n20 != a2) {
                                        s = (this.Q + this.R) * 0.5;
                                    }
                                }
                                this.a(this.p, this.w, this.ib, this.m, eb, v, db, cos, n3, s, this.L, this.Cb);
                                j = this;
                                gb = n3;
                                if (h) {
                                    break Label_0944;
                                }
                                this.a(gb, v);
                            }
                            if (n29 == 0) {
                                this.Qb = false;
                            }
                            this.Y = y;
                            this.S = s;
                            this.V = v;
                            this.db = db;
                            this.cb = cos;
                            this.eb = eb;
                            j = this;
                        }
                        j.gb = gb;
                        return;
                    }
                    if (h) {
                        break;
                    }
                }
                while (s < 0.0) {
                    s += 6.283185307179586;
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
    
    private void a(final double n, final int n2, final int n3) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        this.nb = new int[n3];
        this.ob = new int[n3];
        int q = this.q;
        final int n4 = (int)(n + 0.5);
        int n5 = 1;
        final int n6 = n2 % 2;
        if (!h && n6 == 0) {
            n5 = n4 + 1 >> 1;
            q = this.q - 1;
            goto Label_0065;
        }
        final int n7 = n6;
        this.nb[0] = this.q;
        this.ob[0] = q;
        int i = 1;
        while (true) {
            while (i < n3) {
                this.nb[i] = n2 + this.nb[i - 1];
                this.ob[i] = n2 + this.ob[i - 1];
                ++i;
                if (h) {
                    int n8 = 1 << 2;
                    int j = 0;
                    while (true) {
                        while (j < this.v) {
                            ++i;
                            j += n8;
                            if (h) {
                                int k = 0;
                                while (true) {
                                    while (k < i) {
                                        this.vb[k] = -n8;
                                        j += n8;
                                        if (!h) {
                                            final int n9 = j;
                                            final int v = this.v;
                                            if (h) {
                                                final int n10 = n9 * v;
                                                this.pb[0] = n5 + n10;
                                                this.qb[0] = this.pb[0] << 12;
                                                int l = 1;
                                                while (true) {
                                                    while (l < i) {
                                                        this.pb[l] = n10 + this.pb[l - 1];
                                                        this.qb[l] = this.pb[l] << 12;
                                                        ++l;
                                                        if (h) {
                                                            int n11 = 0;
                                                            while (true) {
                                                                while (n11 < this.v) {
                                                                    ++i;
                                                                    n11 += n8;
                                                                    if (h) {
                                                                        int n12 = 0;
                                                                        while (true) {
                                                                            while (n12 < i) {
                                                                                this.wb[n12] = -n8;
                                                                                j += n8;
                                                                                if (!h) {
                                                                                    final int n13 = j;
                                                                                    final int v2 = this.v;
                                                                                    if (h) {
                                                                                        final int n14 = n13 * v2;
                                                                                        this.rb[0] = n5 + n14;
                                                                                        this.sb[0] = this.rb[0] << 12;
                                                                                        int n15 = 1;
                                                                                        while (true) {
                                                                                            while (n15 < i) {
                                                                                                this.rb[n15] = n14 + this.rb[n15 - 1];
                                                                                                this.sb[n15] = this.rb[n15] << 12;
                                                                                                ++n15;
                                                                                                if (h) {
                                                                                                    int n16 = 0;
                                                                                                    while (true) {
                                                                                                        while (n16 < this.v) {
                                                                                                            ++i;
                                                                                                            n16 += n8;
                                                                                                            if (h) {
                                                                                                                int n17 = 0;
                                                                                                                while (true) {
                                                                                                                    while (n17 < i) {
                                                                                                                        this.xb[n17] = -n8;
                                                                                                                        j += n8;
                                                                                                                        if (!h) {
                                                                                                                            final int n18 = j;
                                                                                                                            final int v3 = this.v;
                                                                                                                            if (h) {
                                                                                                                                final int n19 = n18 * v3;
                                                                                                                                this.tb[0] = n5 + n19;
                                                                                                                                this.ub[0] = this.tb[0] << 12;
                                                                                                                                int n20 = 1;
                                                                                                                                while (n20 < i) {
                                                                                                                                    this.tb[n20] = n19 + this.tb[n20 - 1];
                                                                                                                                    this.ub[n20] = this.tb[n20] << 12;
                                                                                                                                    ++n20;
                                                                                                                                    if (h) {
                                                                                                                                        return;
                                                                                                                                    }
                                                                                                                                    if (h) {
                                                                                                                                        break;
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                --this.t;
                                                                                                                                return;
                                                                                                                            }
                                                                                                                            if (n18 > v3) {
                                                                                                                                this.xb[n17] = -this.v + (j - n8);
                                                                                                                            }
                                                                                                                            ++n17;
                                                                                                                        }
                                                                                                                        if (h) {
                                                                                                                            break;
                                                                                                                        }
                                                                                                                    }
                                                                                                                    this.t = i + 1;
                                                                                                                    this.tb = new int[this.t];
                                                                                                                    this.ub = new int[this.t];
                                                                                                                    this.tb[i] = n5;
                                                                                                                    this.ub[i] = n7;
                                                                                                                    continue;
                                                                                                                }
                                                                                                            }
                                                                                                            if (h) {
                                                                                                                break;
                                                                                                            }
                                                                                                        }
                                                                                                        this.xb = new int[i];
                                                                                                        j = 0;
                                                                                                        continue;
                                                                                                    }
                                                                                                }
                                                                                                if (h) {
                                                                                                    break;
                                                                                                }
                                                                                            }
                                                                                            --this.s;
                                                                                            i = 0;
                                                                                            n8 = 1 << 4;
                                                                                            continue;
                                                                                        }
                                                                                    }
                                                                                    if (n13 > v2) {
                                                                                        this.wb[n12] = -this.v + (j - n8);
                                                                                    }
                                                                                    ++n12;
                                                                                }
                                                                                if (h) {
                                                                                    break;
                                                                                }
                                                                            }
                                                                            this.s = i + 1;
                                                                            this.rb = new int[this.s];
                                                                            this.sb = new int[this.s];
                                                                            this.rb[i] = n5;
                                                                            this.sb[i] = n7;
                                                                            continue;
                                                                        }
                                                                    }
                                                                    if (h) {
                                                                        break;
                                                                    }
                                                                }
                                                                this.wb = new int[i];
                                                                j = 0;
                                                                continue;
                                                            }
                                                        }
                                                        if (h) {
                                                            break;
                                                        }
                                                    }
                                                    --this.r;
                                                    i = 0;
                                                    n8 = 1 << 3;
                                                    continue;
                                                }
                                            }
                                            if (n9 > v) {
                                                this.vb[k] = -this.v + (j - n8);
                                            }
                                            ++k;
                                        }
                                        if (h) {
                                            break;
                                        }
                                    }
                                    this.r = i + 1;
                                    this.pb = new int[this.r];
                                    this.qb = new int[this.r];
                                    this.pb[i] = n5;
                                    this.qb[i] = n7;
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
            i = 0;
            continue;
        }
    }
    
    private void g() {
        final double n = 2.44140625E-4;
        double n2 = 0.0;
        if (this.Kb == null) {
            this.Kb = new int[4097];
            for (int i = 0; i < 4096; ++i) {
                this.Kb[i] = (int)(Math.sqrt(1.0 + n2 * n2) * 4096.0 + 0.5);
                n2 += n;
            }
            this.Kb[4096] = (int)(Math.sqrt(2.0) * 4096.0);
        }
    }
    
    private boolean b(final int n, final int n2) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final double n3 = 2.44140625E-4;
        double n4 = 0.0;
        this.yb = new int[4097];
        this.zb = new int[4097];
        this.Ab = new int[4097];
        this.Bb = new int[4097];
        this.Cb = new int[n + 1];
        this.Db = new int[this.l + 1];
        int pb;
        final int n5 = pb = (this.Pb ? 1 : 0);
        if (!h) {
            if (n5 != 0) {
                this.Eb = new int[n / 8 + 1];
                this.Fb = new int[this.l / 8 + 1];
            }
            pb = 0;
        }
        int i = pb;
        while (true) {
            while (i < n) {
                this.Cb[i] = i;
                ++i;
                if (h) {
                    int pb2;
                    final int n6 = pb2 = (this.Pb ? 1 : 0);
                    if (!h) {
                        Label_0218: {
                            if (n6 != 0) {
                                i = 0;
                                while (i < n / 8) {
                                    this.Eb[i] = i;
                                    ++i;
                                    if (h) {
                                        break Label_0218;
                                    }
                                    if (h) {
                                        break;
                                    }
                                }
                                this.Eb[this.m / 8] = n / 8 - 1;
                            }
                            i = 0;
                        }
                        pb2 = 0;
                    }
                    int j = pb2;
                    while (true) {
                        while (j < this.l) {
                            this.Db[j] = i;
                            i += this.k;
                            ++j;
                            if (h) {
                                int pb3;
                                final int n7 = pb3 = (this.Pb ? 1 : 0);
                                if (!h) {
                                    Label_0355: {
                                        if (n7 != 0) {
                                            int k = 0;
                                            while (k < this.l / 8) {
                                                this.Fb[k] = i;
                                                i += this.k >> 3;
                                                ++k;
                                                if (h) {
                                                    break Label_0355;
                                                }
                                                if (h) {
                                                    break;
                                                }
                                            }
                                            this.Fb[this.l >> 3] = i - n / 8;
                                        }
                                    }
                                    pb3 = 8;
                                }
                                final int n8 = pb3;
                                this.u = 1 << n8;
                                final int n9 = n8;
                                if (!h && n9 <= 0) {}
                                final int n10 = n9;
                                final int n11 = (n << n8) / 2;
                                this.hb = n / 6.283185307179586 * this.u;
                                final int n12 = (int)(n2 * 0.5) * this.u + n10;
                                final int n13 = n2 << n8;
                                int l = 0;
                                while (true) {
                                    while (l < 4096) {
                                        final int n14 = (int)(Math.atan(n4 / (1.0 - n4)) * this.hb + 0.5);
                                        this.yb[l] = n14;
                                        this.zb[l] = n11 - n14;
                                        this.Ab[l] = n12 - n14;
                                        this.Bb[l] = n12 + n14;
                                        int n21;
                                        int n20;
                                        int n19;
                                        final int n18;
                                        int n17;
                                        int n16;
                                        final int n15 = n16 = (n17 = (n18 = (n19 = (n20 = (n21 = this.Ab[l])))));
                                        int n28;
                                        int n27;
                                        int n26;
                                        final int n25;
                                        int n24;
                                        int n23;
                                        final int n22 = n23 = (n24 = (n25 = (n26 = (n27 = (n28 = n10)))));
                                        if (h) {
                                            if (!h) {
                                                if (n18 < n25) {
                                                    this.Ab[4096] = n10;
                                                }
                                                n20 = (n19 = (n21 = this.Bb[4096]));
                                                n27 = (n26 = (n28 = n10));
                                            }
                                            if (!h) {
                                                if (n19 < n26) {
                                                    this.Bb[4096] = n10;
                                                }
                                                n21 = (n20 = this.Ab[4096]);
                                                n28 = (n27 = n13);
                                            }
                                            if (!h) {
                                                if (n20 >= n27) {
                                                    this.Ab[4096] = n13 - 1;
                                                }
                                                final int n29 = n21 = this.Bb[4096];
                                                if (h) {
                                                    return n29 != 0;
                                                }
                                                n28 = n13;
                                            }
                                            if (n21 >= n28) {
                                                this.Bb[4096] = n13 - 1;
                                            }
                                            final int n29 = 1;
                                            return n29 != 0;
                                        }
                                        if (!h) {
                                            if (n15 < n22) {
                                                this.Ab[l] = n10;
                                            }
                                            final int n30;
                                            n16 = (n30 = (n17 = this.Bb[l]));
                                            final int n31;
                                            n23 = (n31 = (n24 = n10));
                                        }
                                        if (!h) {
                                            if (n15 < n22) {
                                                this.Bb[l] = n10;
                                            }
                                            n17 = (n16 = this.Ab[l]);
                                            n24 = (n23 = n13);
                                        }
                                        Label_0631: {
                                            final int[] bb;
                                            final int n32;
                                            Label_0626: {
                                                if (!h) {
                                                    if (n16 >= n23) {
                                                        this.Ab[l] = n13 - 1;
                                                    }
                                                    bb = this.Bb;
                                                    n32 = l;
                                                    if (h) {
                                                        break Label_0626;
                                                    }
                                                    n17 = bb[n32];
                                                    n24 = n13;
                                                }
                                                if (n17 < n24) {
                                                    break Label_0631;
                                                }
                                                final int[] bb2 = this.Bb;
                                            }
                                            bb[n32] = n13 - 1;
                                        }
                                        n4 += n3;
                                        ++l;
                                        if (h) {
                                            break;
                                        }
                                    }
                                    this.yb[4096] = (int)(this.hb * 1.5707963267948966 + 0.5);
                                    this.zb[4096] = n11 - this.yb[4096];
                                    this.Ab[4096] = n12 - this.yb[4096];
                                    this.Bb[4096] = n12 + this.yb[4096];
                                    int n21;
                                    int n20;
                                    int n18;
                                    int n19 = n18 = (n20 = (n21 = this.Ab[4096]));
                                    int n28;
                                    int n27;
                                    int n25;
                                    int n26 = n25 = (n27 = (n28 = n10));
                                    continue;
                                }
                            }
                            if (h) {
                                break;
                            }
                        }
                        this.Db[this.l] = i - n;
                        i = 0;
                        continue;
                    }
                }
                if (h) {
                    break;
                }
            }
            this.Cb[this.m] = n - 1;
            continue;
        }
    }
    
    private final void a(final int n, final int n2, final double n3, final int n4, final int n5, final double n6, final double n7, final double n8, final double n9, final double n10, final double n11, final int n12, final int n13, final int[] array, final int[] array2, final e[] array3, final c[] array4, final d[] array5, final int n14, final int n15, final int[] array6, final int n16, final int n17, final int n18) {
        final double n19 = ((n7 < 0.0) ? (-n7) : n7) + n10;
        int n20;
        int n21;
        int[] array7;
        int[] array8;
        int[] array9;
        if (n19 < 0.7853981633974483) {
            n20 = 4;
            n21 = this.t;
            array7 = this.xb;
            array8 = this.tb;
            array9 = this.ub;
        }
        else if (n19 < 1.5707963267948966) {
            n20 = 3;
            n21 = this.s;
            array7 = this.wb;
            array8 = this.rb;
            array9 = this.sb;
        }
        else {
            n20 = 2;
            n21 = this.r;
            array7 = this.vb;
            array8 = this.pb;
            array9 = this.qb;
        }
        double n22 = n3 * (n6 * n9 - n2 * n8 + 0.5);
        double n23 = n3 * (n6 * n8 + n2 * n9 - 0.5);
        final double n24 = n3 * n8;
        final double n25 = -n3 * n9;
        final int[] jb = this.Jb;
        final int n26 = ((int)(this.kb * n11 + this.G / 2.0) % this.G + this.G) % this.G - 128;
        int[] array10 = this.Ab;
        for (int i = 0; i < n; ++i) {
            int n27 = (int)n22;
            int n28 = (int)n23;
            n22 += n24;
            n23 += n25;
            int[] array11;
            if (n27 >= 0) {
                array11 = this.zb;
                if (n27 == 0) {
                    n27 = 1;
                }
            }
            else {
                array11 = this.yb;
                n27 = -n27;
            }
            int n29 = array11[array9[n21] / (n27 + array8[n21])];
            if (n28 < 0) {
                n28 = -n28;
                array10 = this.Bb;
            }
            final int n30 = n28 << 12;
            int n31 = array10[n30 / (n28 + Math.max(1, ((array8[n21] < n27) ? (n27 * this.Kb[array9[n21] / n27]) : (array8[n21] * this.Kb[(n27 << 12) / array8[n21]])) >> 12))] + n12;
            int n32 = this.nb[i];
            int n33 = this.ob[i];
            for (int j = 0; j < n21; ++j) {
                final int n34 = array11[array9[j] / (n27 + array8[j])];
                final int n35 = array10[n30 / (n28 + Math.max(1, ((array8[j] < n27) ? (n27 * this.Kb[array9[j] / n27]) : (array8[j] * this.Kb[(n27 << 12) / array8[j]])) >> 12))] + n12;
                final int n36 = n34 - n29 >> n20;
                final int n37 = n35 - n31 >> n20;
                int k = array7[j];
                while (k < 0) {
                    ++k;
                    int n38 = n31 - 128 >> 8;
                    if (n38 < 0) {
                        n38 += n5;
                    }
                    else if (n38 >= n5) {
                        n38 -= n5;
                    }
                    final int n39 = array[n38];
                    int n40 = n26 + n29 + 128 >> 8;
                    if (n40 >= n4) {
                        n40 -= n4;
                    }
                    final int n41 = array2[n40];
                    final int a = this.a(n39, n41, n14, n13, n15, array6);
                    if (array4[a].h()) {
                        if (array3[a].a() == null) {
                            this.a(a, array4[a], array5, array3, n18);
                        }
                        this.Ib[n33] = array3[a].a(n13, n39, n41, n15, n16, n17);
                    }
                    else {
                        this.Ib[n33] = jb[this.Fb[n38 >> 3] + this.Eb[n40 >> 3]];
                    }
                    --n33;
                    int n42 = n26 - n29 - 128 >> 8;
                    if (n42 < 0) {
                        n42 += n4;
                    }
                    final int n43 = array2[n42];
                    final int a2 = this.a(n39, n43, n14, n13, n15, array6);
                    if (array4[a2].h()) {
                        if (array3[a2].a() == null) {
                            this.a(a2, array4[a2], array5, array3, n18);
                        }
                        this.Ib[n32] = array3[a2].a(n13, n39, n43, n15, n16, n17);
                    }
                    else {
                        this.Ib[n32] = jb[this.Fb[n38 >> 3] + this.Eb[n42 >> 3]];
                    }
                    ++n32;
                    n29 += n36;
                    n31 += n37;
                }
                n29 = n34;
                n31 = n35;
            }
        }
    }
    
    private final void a(final int n, final int n2, final double n3, final int n4, final int n5, final double n6, final double n7, final double n8, final double n9, final double n10, final double n11, final int n12, final int[] array, final int[] array2) {
        final double n13 = ((n7 < 0.0) ? (-n7) : n7) + n10;
        int n14;
        int n15;
        int[] array3;
        int[] array4;
        int[] array5;
        if (n13 < 0.7853981633974483) {
            n14 = 4;
            n15 = this.t;
            array3 = this.xb;
            array4 = this.tb;
            array5 = this.ub;
        }
        else if (n13 < 1.5707963267948966) {
            n14 = 3;
            n15 = this.s;
            array3 = this.wb;
            array4 = this.rb;
            array5 = this.sb;
        }
        else {
            n14 = 2;
            n15 = this.r;
            array3 = this.vb;
            array4 = this.pb;
            array5 = this.qb;
        }
        double n16 = n3 * (n6 * n9 - n2 * n8 + 0.5);
        double n17 = n3 * (n6 * n8 + n2 * n9 - 0.5);
        final double n18 = n3 * n8;
        final double n19 = -n3 * n9;
        final int[] jb = this.Jb;
        final int n20 = ((int)(this.kb * n11 + this.G / 2.0) % this.G + this.G) % this.G - 128;
        int[] array6 = this.Ab;
        if (this.j != 0) {
            for (int i = 0; i < n; ++i) {
                int n21 = (int)n16;
                int n22 = (int)n17;
                n16 += n18;
                n17 += n19;
                int[] array7;
                if (n21 >= 0) {
                    array7 = this.zb;
                    if (n21 == 0) {
                        n21 = 1;
                    }
                }
                else {
                    array7 = this.yb;
                    n21 = -n21;
                }
                int n23 = array7[array5[n15] / (n21 + array4[n15])];
                if (n22 < 0) {
                    n22 = -n22;
                    array6 = this.Bb;
                }
                final int n24 = n22 << 12;
                int n25 = array6[n24 / (n22 + Math.max(1, ((array4[n15] < n21) ? (n21 * this.Kb[array5[n15] / n21]) : (array4[n15] * this.Kb[(n21 << 12) / array4[n15]])) >> 12))] + n12;
                int n26 = this.nb[i];
                int n27 = this.ob[i];
                for (int j = 0; j < n15; ++j) {
                    final int n28 = array7[array5[j] / (n21 + array4[j])];
                    final int n29 = array6[n24 / (n22 + Math.max(1, ((array4[j] < n21) ? (n21 * this.Kb[array5[j] / n21]) : (array4[j] * this.Kb[(n21 << 12) / array4[j]])) >> 12))] + n12;
                    final int n30 = n28 - n23 >> n14;
                    final int n31 = n29 - n25 >> n14;
                    int k = array3[j];
                    while (k < 0) {
                        ++k;
                        final int n32 = n25 - 128;
                        final int n33 = n32 & 0xFF;
                        final int n34 = 256 - n33;
                        int n35 = n32 >> 8;
                        if (n35 < 0) {
                            n35 += n5;
                        }
                        else if (n35 >= n5) {
                            n35 -= n5;
                        }
                        final int n36 = array[n35];
                        final int n37 = array[n35 + 1];
                        final int n38 = n20 + n23 + 128;
                        final int n39 = n38 & 0xFF;
                        final int n40 = 256 - n39;
                        int n41 = n38 >> 8;
                        if (n41 >= n4) {
                            n41 -= n4;
                        }
                        final int n42 = array2[n41];
                        final int n43 = array2[n41 + 1];
                        final int n44 = jb[n36 + n42];
                        final int n45 = jb[n36 + n43];
                        final int n46 = jb[n37 + n42];
                        final int n47 = jb[n37 + n43];
                        this.Ib[n27] = ((((n44 & 0xFF00FF) * n40 + (n45 & 0xFF00FF) * n39 >> 8 & 0xFF00FF) * n34 + ((n46 & 0xFF00FF) * n40 + (n47 & 0xFF00FF) * n39 >> 8 & 0xFF00FF) * n33 >> 8 & 0xFF00FF) | (((n44 & 0xFF00) * n40 + (n45 & 0xFF00) * n39) * n34 + ((n46 & 0xFF00) * n40 + (n47 & 0xFF00) * n39) * n33 >> 16 & 0xFF00) | 0xFF000000);
                        --n27;
                        final int n48 = n20 - n23 - 128;
                        final int n49 = n48 & 0xFF;
                        final int n50 = 256 - n49;
                        int n51 = n48 >> 8;
                        if (n51 < 0) {
                            n51 += n4;
                        }
                        final int n52 = array2[n51];
                        final int n53 = array2[n51 + 1];
                        final int n54 = jb[n36 + n52];
                        final int n55 = jb[n36 + n53];
                        final int n56 = jb[n37 + n52];
                        final int n57 = jb[n37 + n53];
                        this.Ib[n26] = ((((n54 & 0xFF00FF) * n50 + (n55 & 0xFF00FF) * n49 >> 8 & 0xFF00FF) * n34 + ((n56 & 0xFF00FF) * n50 + (n57 & 0xFF00FF) * n49 >> 8 & 0xFF00FF) * n33 >> 8 & 0xFF00FF) | (((n54 & 0xFF00) * n50 + (n55 & 0xFF00) * n49) * n34 + ((n56 & 0xFF00) * n50 + (n57 & 0xFF00) * n49) * n33 >> 16 & 0xFF00) | 0xFF000000);
                        ++n26;
                        n23 += n30;
                        n25 += n31;
                    }
                    n23 = n28;
                    n25 = n29;
                }
            }
        }
        else {
            for (int l = 0; l < n; ++l) {
                int n58 = (int)n16;
                int n59 = (int)n17;
                n16 += n18;
                n17 += n19;
                int[] array8;
                if (n58 >= 0) {
                    array8 = this.zb;
                    if (n58 == 0) {
                        n58 = 1;
                    }
                }
                else {
                    array8 = this.yb;
                    n58 = -n58;
                }
                int n60 = array8[array5[n15] / (n58 + array4[n15])];
                if (n59 < 0) {
                    n59 = -n59;
                    array6 = this.Bb;
                }
                final int n61 = n59 << 12;
                int n62 = array6[n61 / (n59 + Math.max(1, ((array4[n15] < n58) ? (n58 * this.Kb[array5[n15] / n58]) : (array4[n15] * this.Kb[(n58 << 12) / array4[n15]])) >> 12))] + n12;
                int n63 = this.nb[l];
                int n64 = this.ob[l];
                for (int n65 = 0; n65 < n15; ++n65) {
                    final int n66 = array8[array5[n65] / (n58 + array4[n65])];
                    final int n67 = array6[n61 / (n59 + Math.max(1, ((array4[n65] < n58) ? (n58 * this.Kb[array5[n65] / n58]) : (array4[n65] * this.Kb[(n58 << 12) / array4[n65]])) >> 12))] + n12;
                    final int n68 = n66 - n60 >> n14;
                    final int n69 = n67 - n62 >> n14;
                    int n70 = array3[n65];
                    while (n70 < 0) {
                        ++n70;
                        int n71 = n62 - 128 >> 8;
                        if (n71 < 0) {
                            n71 += n5;
                        }
                        else if (n71 >= n5) {
                            n71 -= n5;
                        }
                        final int n72 = array[n71];
                        int n73 = n20 + n60 + 128 >> 8;
                        if (n73 >= n4) {
                            n73 -= n4;
                        }
                        this.Ib[n64] = jb[n72 + array2[n73]];
                        --n64;
                        int n74 = n20 - n60 - 128 >> 8;
                        if (n74 < 0) {
                            n74 += n4;
                        }
                        this.Ib[n63] = jb[n72 + array2[n74]];
                        ++n63;
                        n60 += n68;
                        n62 += n69;
                    }
                    n60 = n66;
                    n62 = n67;
                }
            }
        }
    }
    
    private final void b(final int n, final int n2, final double n3, final int n4, final int n5, final double n6, final double n7, final double n8, final double n9, final double n10, final double n11, final int n12, final int n13, final int[] array, final int[] array2, final e[] array3, final c[] array4, final d[] array5, final int n14, final int n15, final int[] array6, final int n16, final int n17, final int n18) {
        final double n19 = ((n7 < 0.0) ? (-n7) : n7) + n10;
        int n20;
        int n21;
        int[] array7;
        int[] array8;
        int[] array9;
        if (n19 < 0.7853981633974483) {
            n20 = 4;
            n21 = this.t;
            array7 = this.xb;
            array8 = this.tb;
            array9 = this.ub;
        }
        else if (n19 < 1.5707963267948966) {
            n20 = 3;
            n21 = this.s;
            array7 = this.wb;
            array8 = this.rb;
            array9 = this.sb;
        }
        else {
            n20 = 2;
            n21 = this.r;
            array7 = this.vb;
            array8 = this.pb;
            array9 = this.qb;
        }
        double n22 = n3 * (n6 * n9 - n2 * n8 + 0.5);
        double n23 = n3 * (n6 * n8 + n2 * n9 - 0.5);
        final double n24 = n3 * n8;
        final double n25 = -n3 * n9;
        final int[] jb = this.Jb;
        final int n26 = ((int)(this.kb * n11 + this.G / 2.0) % this.G + this.G) % this.G - 128;
        int[] array10 = this.Ab;
        for (int i = 0; i < n; ++i) {
            int n27 = (int)n22;
            int n28 = (int)n23;
            n22 += n24;
            n23 += n25;
            int[] array11;
            if (n27 >= 0) {
                array11 = this.zb;
                if (n27 == 0) {
                    n27 = 1;
                }
            }
            else {
                array11 = this.yb;
                n27 = -n27;
            }
            int n29 = array11[array9[n21] / (n27 + array8[n21])];
            if (n28 < 0) {
                n28 = -n28;
                array10 = this.Bb;
            }
            final int n30 = n28 << 12;
            int n31 = array10[n30 / (n28 + Math.max(1, ((array8[n21] < n27) ? (n27 * this.Kb[array9[n21] / n27]) : (array8[n21] * this.Kb[(n27 << 12) / array8[n21]])) >> 12))] + n12;
            int n32 = this.nb[i];
            int n33 = this.ob[i];
            for (int j = 0; j < n21; ++j) {
                final int n34 = array11[array9[j] / (n27 + array8[j])];
                final int n35 = array10[n30 / (n28 + Math.max(1, ((array8[j] < n27) ? (n27 * this.Kb[array9[j] / n27]) : (array8[j] * this.Kb[(n27 << 12) / array8[j]])) >> 12))] + n12;
                final int n36 = n34 - n29 >> n20;
                final int n37 = n35 - n31 >> n20;
                int k = array7[j];
                while (k < 0) {
                    ++k;
                    final int n38 = n31 - 128;
                    final int n39 = n38 & 0xFF;
                    final int n40 = 256 - n39;
                    int n41 = n38 >> 8;
                    if (n41 < 0) {
                        n41 += n5;
                    }
                    else if (n41 >= n5) {
                        n41 -= n5;
                    }
                    final int n42 = array[n41];
                    final int n43 = array[n41 + 1];
                    final int n44 = n26 + n29 + 128;
                    final int n45 = n44 & 0xFF;
                    final int n46 = 256 - n45;
                    int n47 = n44 >> 8;
                    if (n47 >= n4) {
                        n47 -= n4;
                    }
                    final int n48 = array2[n47];
                    final int n49 = array2[n47 + 1];
                    final int a = this.a(n42, n48, n14, n13, n15, array6);
                    int a2;
                    if (array4[a].h()) {
                        if (array3[a].a() == null) {
                            this.a(a, array4[a], array5, array3, n18);
                        }
                        a2 = array3[a].a(n13, n42, n48, n15, n16, n17);
                    }
                    else {
                        a2 = jb[this.Fb[n41 >> 3] + this.Eb[n47 >> 3]];
                    }
                    final int a3 = this.a(n42, n49, n14, n13, n15, array6);
                    int a4;
                    if (array4[a3].h()) {
                        if (array3[a3].a() == null) {
                            this.a(a3, array4[a3], array5, array3, n18);
                        }
                        a4 = array3[a3].a(n13, n42, n49, n15, n16, n17);
                    }
                    else {
                        a4 = jb[this.Fb[n41 >> 3] + this.Eb[n47 + 1 >> 3]];
                    }
                    final int a5 = this.a(n43, n48, n14, n13, n15, array6);
                    int a6;
                    if (array4[a5].h()) {
                        if (array3[a5].a() == null) {
                            this.a(a5, array4[a5], array5, array3, n18);
                        }
                        a6 = array3[a5].a(n13, n43, n48, n15, n16, n17);
                    }
                    else {
                        a6 = jb[this.Fb[n41 + 1 >> 3] + this.Eb[n47 >> 3]];
                    }
                    final int a7 = this.a(n43, n49, n14, n13, n15, array6);
                    int a8;
                    if (array4[a7].h()) {
                        if (array3[a7].a() == null) {
                            this.a(a7, array4[a7], array5, array3, n18);
                        }
                        a8 = array3[a7].a(n13, n43, n49, n15, n16, n17);
                    }
                    else {
                        a8 = jb[this.Fb[n41 + 1 >> 3] + this.Eb[n47 + 1 >> 3]];
                    }
                    this.Ib[n33] = ((((a2 & 0xFF00FF) * n46 + (a4 & 0xFF00FF) * n45 >> 8 & 0xFF00FF) * n40 + ((a6 & 0xFF00FF) * n46 + (a8 & 0xFF00FF) * n45 >> 8 & 0xFF00FF) * n39 >> 8 & 0xFF00FF) | (((a2 & 0xFF00) * n46 + (a4 & 0xFF00) * n45) * n40 + ((a6 & 0xFF00) * n46 + (a8 & 0xFF00) * n45) * n39 >> 16 & 0xFF00) | 0xFF000000);
                    --n33;
                    final int n50 = n26 - n29 - 128;
                    final int n51 = n50 & 0xFF;
                    final int n52 = 256 - n51;
                    int n53 = n50 >> 8;
                    if (n53 < 0) {
                        n53 += n4;
                    }
                    final int n54 = array2[n53];
                    final int n55 = array2[n53 + 1];
                    final int a9 = this.a(n42, n54, n14, n13, n15, array6);
                    int a10;
                    if (array4[a9].h()) {
                        if (array3[a9].a() == null) {
                            this.a(a9, array4[a9], array5, array3, n18);
                        }
                        a10 = array3[a9].a(n13, n42, n54, n15, n16, n17);
                    }
                    else {
                        a10 = jb[this.Fb[n41 >> 3] + this.Eb[n53 >> 3]];
                    }
                    final int a11 = this.a(n42, n55, n14, n13, n15, array6);
                    int a12;
                    if (array4[a11].h()) {
                        if (array3[a11].a() == null) {
                            this.a(a11, array4[a11], array5, array3, n18);
                        }
                        a12 = array3[a11].a(n13, n42, n55, n15, n16, n17);
                    }
                    else {
                        a12 = jb[this.Fb[n41 >> 3] + this.Eb[n53 + 1 >> 3]];
                    }
                    final int a13 = this.a(n43, n54, n14, n13, n15, array6);
                    int a14;
                    if (array4[a13].h()) {
                        if (array3[a13].a() == null) {
                            this.a(a13, array4[a13], array5, array3, n18);
                        }
                        a14 = array3[a13].a(n13, n43, n54, n15, n16, n17);
                    }
                    else {
                        a14 = jb[this.Fb[n41 + 1 >> 3] + this.Eb[n53 >> 3]];
                    }
                    final int a15 = this.a(n43, n55, n14, n13, n15, array6);
                    int a16;
                    if (array4[a15].h()) {
                        if (array3[a15].a() == null) {
                            this.a(a15, array4[a15], array5, array3, n18);
                        }
                        a16 = array3[a15].a(n13, n43, n55, n15, n16, n17);
                    }
                    else {
                        a16 = jb[this.Fb[n41 + 1 >> 3] + this.Eb[n53 + 1 >> 3]];
                    }
                    this.Ib[n32] = ((((a10 & 0xFF00FF) * n52 + (a12 & 0xFF00FF) * n51 >> 8 & 0xFF00FF) * n40 + ((a14 & 0xFF00FF) * n52 + (a16 & 0xFF00FF) * n51 >> 8 & 0xFF00FF) * n39 >> 8 & 0xFF00FF) | (((a10 & 0xFF00) * n52 + (a12 & 0xFF00) * n51) * n40 + ((a14 & 0xFF00) * n52 + (a16 & 0xFF00) * n51) * n39 >> 16 & 0xFF00) | 0xFF000000);
                    ++n32;
                    n29 += n36;
                    n31 += n37;
                }
                n29 = n34;
                n31 = n35;
            }
        }
    }
    
    private final void c(final int n, final int n2, final double n3, final int n4, final int n5, final double n6, final double n7, final double n8, final double n9, final double n10, final double n11, final int n12, final int n13, final int[] array, final int[] array2, final e[] array3, final c[] array4, final d[] array5, final int n14, final int n15, final int[] array6, final int n16, final int n17, final int n18) {
        final double n19 = ((n7 < 0.0) ? (-n7) : n7) + n10;
        int n20;
        int n21;
        int[] array7;
        int[] array8;
        int[] array9;
        if (n19 < 0.7853981633974483) {
            n20 = 4;
            n21 = this.t;
            array7 = this.xb;
            array8 = this.tb;
            array9 = this.ub;
        }
        else if (n19 < 1.5707963267948966) {
            n20 = 3;
            n21 = this.s;
            array7 = this.wb;
            array8 = this.rb;
            array9 = this.sb;
        }
        else {
            n20 = 2;
            n21 = this.r;
            array7 = this.vb;
            array8 = this.pb;
            array9 = this.qb;
        }
        double n22 = n3 * (n6 * n9 - n2 * n8 + 0.5);
        double n23 = n3 * (n6 * n8 + n2 * n9 - 0.5);
        final double n24 = n3 * n8;
        final double n25 = -n3 * n9;
        final int[] jb = this.Jb;
        final int n26 = ((int)(this.kb * n11 + this.G / 2.0) % this.G + this.G) % this.G - 128;
        int[] array10 = this.Ab;
        for (int i = 0; i < n; ++i) {
            int n27 = (int)n22;
            int n28 = (int)n23;
            n22 += n24;
            n23 += n25;
            int[] array11;
            if (n27 >= 0) {
                array11 = this.zb;
                if (n27 == 0) {
                    n27 = 1;
                }
            }
            else {
                array11 = this.yb;
                n27 = -n27;
            }
            int n29 = array11[array9[n21] / (n27 + array8[n21])];
            if (n28 < 0) {
                n28 = -n28;
                array10 = this.Bb;
            }
            final int n30 = n28 << 12;
            int n31 = array10[n30 / (n28 + Math.max(1, ((array8[n21] < n27) ? (n27 * this.Kb[array9[n21] / n27]) : (array8[n21] * this.Kb[(n27 << 12) / array8[n21]])) >> 12))] + n12;
            int n32 = this.nb[i];
            int n33 = this.ob[i];
            for (int j = 0; j < n21; ++j) {
                final int n34 = array11[array9[j] / (n27 + array8[j])];
                final int n35 = array10[n30 / (n28 + Math.max(1, ((array8[j] < n27) ? (n27 * this.Kb[array9[j] / n27]) : (array8[j] * this.Kb[(n27 << 12) / array8[j]])) >> 12))] + n12;
                final int n36 = n34 - n29 >> n20;
                final int n37 = n35 - n31 >> n20;
                int k = array7[j];
                while (k < 0) {
                    ++k;
                    final int n38 = n31 - 128;
                    final int n39 = n38 & 0xFF;
                    final int n40 = 256 - n39;
                    int n41 = n38 >> 8;
                    if (n41 < 0) {
                        n41 += n5;
                    }
                    else if (n41 >= n5) {
                        n41 -= n5;
                    }
                    final int n42 = array[n41];
                    final int n43 = array[n41 + 1];
                    final int n44 = n26 + n29 + 128;
                    final int n45 = n44 & 0xFF;
                    final int n46 = 256 - n45;
                    int n47 = n44 >> 8;
                    if (n47 >= n4) {
                        n47 -= n4;
                    }
                    final int n48 = array2[n47];
                    final int n49 = array2[n47 + 1];
                    final int a = this.a(n42, n48, n14, n13, n15, array6);
                    if (array3[a].a() == null) {
                        this.a(a, array4[a], array5, array3, n18);
                    }
                    final int a2 = array3[a].a(n13, n42, n48, n15, n16, n17);
                    final int a3 = this.a(n42, n49, n14, n13, n15, array6);
                    if (array3[a3].a() == null) {
                        this.a(a3, array4[a3], array5, array3, n18);
                    }
                    final int a4 = array3[a3].a(n13, n42, n49, n15, n16, n17);
                    final int a5 = this.a(n43, n48, n14, n13, n15, array6);
                    if (array3[a5].a() == null) {
                        this.a(a5, array4[a5], array5, array3, n18);
                    }
                    final int a6 = array3[a5].a(n13, n43, n48, n15, n16, n17);
                    final int a7 = this.a(n43, n49, n14, n13, n15, array6);
                    if (array3[a7].a() == null) {
                        this.a(a7, array4[a7], array5, array3, n18);
                    }
                    final int a8 = array3[a7].a(n13, n43, n49, n15, n16, n17);
                    this.Ib[n33] = ((((a2 & 0xFF00FF) * n46 + (a4 & 0xFF00FF) * n45 >> 8 & 0xFF00FF) * n40 + ((a6 & 0xFF00FF) * n46 + (a8 & 0xFF00FF) * n45 >> 8 & 0xFF00FF) * n39 >> 8 & 0xFF00FF) | (((a2 & 0xFF00) * n46 + (a4 & 0xFF00) * n45) * n40 + ((a6 & 0xFF00) * n46 + (a8 & 0xFF00) * n45) * n39 >> 16 & 0xFF00) | 0xFF000000);
                    --n33;
                    final int n50 = n26 - n29 - 128;
                    final int n51 = n50 & 0xFF;
                    final int n52 = 256 - n51;
                    int n53 = n50 >> 8;
                    if (n53 < 0) {
                        n53 += n4;
                    }
                    final int n54 = array2[n53];
                    final int n55 = array2[n53 + 1];
                    final int a9 = this.a(n42, n54, n14, n13, n15, array6);
                    if (array3[a9].a() == null) {
                        this.a(a9, array4[a9], array5, array3, n18);
                    }
                    final int a10 = array3[a9].a(n13, n42, n54, n15, n16, n17);
                    final int a11 = this.a(n42, n55, n14, n13, n15, array6);
                    if (array3[a11].a() == null) {
                        this.a(a11, array4[a11], array5, array3, n18);
                    }
                    final int a12 = array3[a11].a(n13, n42, n55, n15, n16, n17);
                    final int a13 = this.a(n43, n54, n14, n13, n15, array6);
                    if (array3[a13].a() == null) {
                        this.a(a13, array4[a13], array5, array3, n18);
                    }
                    final int a14 = array3[a13].a(n13, n43, n54, n15, n16, n17);
                    final int a15 = this.a(n43, n55, n14, n13, n15, array6);
                    if (array3[a15].a() == null) {
                        this.a(a15, array4[a15], array5, array3, n18);
                    }
                    final int a16 = array3[a15].a(n13, n43, n55, n15, n16, n17);
                    this.Ib[n32] = ((((a10 & 0xFF00FF) * n52 + (a12 & 0xFF00FF) * n51 >> 8 & 0xFF00FF) * n40 + ((a14 & 0xFF00FF) * n52 + (a16 & 0xFF00FF) * n51 >> 8 & 0xFF00FF) * n39 >> 8 & 0xFF00FF) | (((a10 & 0xFF00) * n52 + (a12 & 0xFF00) * n51) * n40 + ((a14 & 0xFF00) * n52 + (a16 & 0xFF00) * n51) * n39 >> 16 & 0xFF00) | 0xFF000000);
                    ++n32;
                    n29 += n36;
                    n31 += n37;
                }
                n29 = n34;
                n31 = n35;
            }
        }
    }
    
    private final void a(final int n, final int n2, final double n3, final int n4, final double n5, final double n6, final double n7, final double n8, final double n9, final double n10, final int n11, final int[] array) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        int qb;
        final int n12 = qb = (this.Qb ? 1 : 0);
        if (!h) {
            if (n12 == 0) {
                return;
            }
            qb = 4;
        }
        int n13 = qb;
        double n14 = n6;
        if (!h) {
            if (n6 < 0.0) {
                n14 = -n6;
            }
            else {
                n14 = n6;
            }
        }
        final double n15 = n14 + n9;
        double r;
        final double n16 = r = dcmpg(n15, 0.7853981633974483);
        double n17 = 0.0;
        int[] array2 = null;
        int[] array3 = null;
        int[] array4 = null;
        Label_0165: {
            if (!h) {
                if (n16 < 0) {
                    n13 = 4;
                    n17 = this.t;
                    array2 = this.xb;
                    array3 = this.tb;
                    array4 = this.ub;
                    if (!h) {
                        break Label_0165;
                    }
                }
                final int n18;
                r = (n18 = dcmpg(n15, 1.5707963267948966));
            }
            if (!h) {
                if (n16 < 0) {
                    n13 = 3;
                    n17 = this.s;
                    array2 = this.wb;
                    array3 = this.rb;
                    array4 = this.sb;
                    if (!h) {
                        break Label_0165;
                    }
                }
                n13 = 2;
                r = this.r;
            }
            n17 = r;
            array2 = this.vb;
            array3 = this.pb;
            array4 = this.qb;
        }
        final double n19 = n3 * (n5 * n8 - n2 * n7 + 0.5);
        final double n20 = n3 * (n5 * n7 + n2 * n8 - 0.5);
        final double n21 = n3 * n7;
        final double n22 = -n3 * n8;
        final int n23 = ((int)(this.kb * n10 + this.G / 2.0) % this.G + this.G) % this.G - 128;
        int[] array5 = this.Ab;
        int n24 = (int)n19;
        int n25 = (int)n20;
        int[] array6 = null;
        final int n26;
        Label_0335: {
            Label_0318: {
                if (n24 >= 0) {
                    array6 = this.zb;
                    n26 = n24;
                    if (h) {
                        break Label_0335;
                    }
                    if (n26 != 0) {
                        break Label_0318;
                    }
                    n24 = 1;
                    if (!h) {
                        break Label_0318;
                    }
                }
                array6 = this.yb;
                n24 = -n24;
            }
            final int n27 = array6[array4[n17] / (n24 + array3[n17])];
        }
        int n28 = n26;
        final int n29 = n25;
        if (!h && n29 < 0) {
            n25 = -n25;
            array5 = this.Bb;
            goto Label_0358;
        }
        int n30 = n29;
        final int n31 = array3[n17];
        final int n32 = n24;
        int n33 = 0;
        Label_0421: {
            if (!h) {
                if (n31 < n32) {
                    n33 = n24 * this.Kb[array4[n17] / n24];
                    break Label_0421;
                }
                final int n34 = array3[n17];
                final int n35 = this.Kb[(n24 << 12) / array3[n17]];
            }
            n33 = n31 * n32;
        }
        final int n36 = array5[n30 / (n25 + Math.max(1, n33 >> 12))] + n11;
        final int n37 = array6[array4[0] / (n24 + array3[0])];
        final int n38 = array3[0];
        final int n39 = n24;
        int n40 = 0;
        Label_0515: {
            if (!h) {
                if (n38 < n39) {
                    n40 = n24 * this.Kb[array4[0] / n24];
                    break Label_0515;
                }
                final int n41 = array3[0];
                final int n42 = this.Kb[(n24 << 12) / array3[0]];
            }
            n40 = n38 * n39;
        }
        final int n43 = array5[n30 / (n25 + Math.max(1, n40 >> 12))] + n11 - n36 >> n13;
        this.Hb[1] = (n36 - 128 >> 8) + this.H + 1;
        final int n44 = n23 + n28 + 128 >> 8;
        int n45 = n4;
        if (!h) {
            if (n44 >= n4) {}
            n45 = 8;
        }
        int n46 = n44 >> n45;
        int n48;
        final int n47 = n48 = n46;
        if (!h) {
            if (n47 < 0) {
                n46 += n4;
            }
            this.Gb[1] = array[n46];
            n28 = array6[array4[n17 - 2] / (n24 + array3[n17 - 2])];
            final int n49;
            n48 = (n49 = array3[n17 - 2]);
        }
        final int n50 = n24;
        int n51 = 0;
        Label_0760: {
            if (!h) {
                if (n47 < n50) {
                    n51 = n24 * this.Kb[array4[n17 - 2] / n24];
                    break Label_0760;
                }
                n48 = array3[n17 - 2];
                final int n52 = this.Kb[(n24 << 12) / array3[n17 - 2]];
            }
            n51 = n48 * n50;
        }
        final int n53 = array5[n30 / (n25 + Math.max(1, n51 >> 12))] + n11;
        final int n54 = array6[array4[n17 - 1] / (n24 + array3[n17 - 1])];
        final int n55 = array3[n17 - 1];
        final int n56 = n24;
        int n57 = 0;
        Label_0872: {
            if (!h) {
                if (n55 < n56) {
                    n57 = n24 * this.Kb[array4[n17 - 1] / n24];
                    break Label_0872;
                }
                final int n58 = array3[n17 - 1];
                final int n59 = this.Kb[(n24 << 12) / array3[n17 - 1]];
            }
            n57 = n55 * n56;
        }
        final int n60 = array5[n30 / (n25 + Math.max(1, n57 >> 12))] + n11;
        final int n61 = n54 - n28 >> n13;
        final int n62 = n60 - n53 >> n13;
        final int n63 = array2[n17 - 1];
        int n64 = n28 - n61 * (n63 + 1);
        final int n65 = n53 - n62 * (n63 + 1) - 128 >> 8;
        this.Hb[2] = n65 + this.H + 1;
        this.Hb[0] = n65 + this.H + 1;
        final int n67;
        int n66 = n67 = n23 + n64 + 128 >> 8;
        int n68 = n4;
        if (!h) {
            if (n67 >= n4) {
                n66 -= n4;
            }
            this.Gb[0] = array[n66];
            n68 = 8;
        }
        int n69 = n67 >> n68;
        int n71;
        final int n70 = n71 = n69;
        if (!h) {
            if (n70 < 0) {
                n69 += n4;
            }
            this.Gb[2] = array[n69];
            n24 = (int)(n19 + n21 * (n - 1));
            n25 = (int)(n20 + n22 * (n - 1));
            final int n72;
            n71 = (n72 = n24);
        }
        int n75 = 0;
        int n74 = 0;
        final int n73;
        Label_1189: {
            Label_1168: {
                if (!h) {
                    if (n70 >= 0) {
                        array6 = this.zb;
                        n73 = (n74 = (n75 = n24));
                        if (h) {
                            break Label_1189;
                        }
                        if (n73 != 0) {
                            break Label_1168;
                        }
                        n24 = 1;
                        if (!h) {
                            break Label_1168;
                        }
                    }
                    array6 = this.yb;
                    n71 = -n24;
                }
                n24 = n71;
            }
            n64 = array6[array4[n17] / (n24 + array3[n17])];
            final int n76;
            n74 = (n76 = (n75 = n25));
        }
        if (!h) {
            if (n73 < 0) {
                n25 = -n25;
                array5 = this.Bb;
            }
            n30 = n25 << 12;
            n75 = (n74 = array3[n17]);
        }
        final int n77 = n24;
        int n78 = 0;
        Label_1271: {
            if (!h) {
                if (n74 < n77) {
                    n78 = n24 * this.Kb[array4[n17] / n24];
                    break Label_1271;
                }
                n75 = array3[n17];
                final int n79 = this.Kb[(n24 << 12) / array3[n17]];
            }
            n78 = n75 * n77;
        }
        final int n80 = array5[n30 / (n25 + Math.max(1, n78 >> 12))] + n11;
        final int n81 = array6[array4[0] / (n24 + array3[0])];
        final int n82 = array3[0];
        final int n83 = n24;
        int n84 = 0;
        Label_1365: {
            if (!h) {
                if (n82 < n83) {
                    n84 = n24 * this.Kb[array4[0] / n24];
                    break Label_1365;
                }
                final int n85 = array3[0];
                final int n86 = this.Kb[(n24 << 12) / array3[0]];
            }
            n84 = n82 * n83;
        }
        final int n87 = array5[n30 / (n25 + Math.max(1, n84 >> 12))] + n11 - n80 >> n13;
        this.Hb[4] = (n80 - 128 >> 8) + this.H + 1;
        final int n88 = n23 + n64 + 128 >> 8;
        int n89 = n4;
        if (!h) {
            if (n88 >= n4) {}
            n89 = 8;
        }
        int n90 = n88 >> n89;
        int n92;
        final int n91 = n92 = n90;
        if (!h) {
            if (n91 < 0) {
                n90 += n4;
            }
            this.Gb[4] = array[n90];
            n64 = array6[array4[n17 - 2] / (n24 + array3[n17 - 2])];
            final int n93;
            n92 = (n93 = array3[n17 - 2]);
        }
        final int n94 = n24;
        int n95 = 0;
        Label_1618: {
            if (!h) {
                if (n91 < n94) {
                    n95 = n24 * this.Kb[array4[n17 - 2] / n24];
                    break Label_1618;
                }
                n92 = array3[n17 - 2];
                final int n96 = this.Kb[(n24 << 12) / array3[n17 - 2]];
            }
            n95 = n92 * n94;
        }
        final int n97 = array5[n30 / (n25 + Math.max(1, n95 >> 12))] + n11;
        final int n98 = array6[array4[n17 - 1] / (n24 + array3[n17 - 1])];
        final int n99 = array3[n17 - 1];
        final int n100 = n24;
        int n101 = 0;
        Label_1730: {
            if (!h) {
                if (n99 < n100) {
                    n101 = n24 * this.Kb[array4[n17 - 1] / n24];
                    break Label_1730;
                }
                final int n102 = array3[n17 - 1];
                final int n103 = this.Kb[(n24 << 12) / array3[n17 - 1]];
            }
            n101 = n99 * n100;
        }
        final int n104 = array5[n30 / (n25 + Math.max(1, n101 >> 12))] + n11;
        final int n105 = n98 - n64 >> n13;
        final int n106 = n104 - n97 >> n13;
        final int n107 = array2[n17 - 1];
        final int n108 = n64 - n105 * (n107 + 1);
        final int n109 = n97 - n106 * (n107 + 1) - 128 >> 8;
        this.Hb[3] = n109 + this.H + 1;
        this.Hb[5] = n109 + this.H + 1;
        final int n111;
        int n110 = n111 = n23 + n108 + 128 >> 8;
        int n112 = n4;
        if (!h) {
            if (n111 >= n4) {
                n110 -= n4;
            }
            this.Gb[3] = array[n110];
            n112 = 8;
        }
        int n113 = n111 >> n112;
        if (!h) {
            if (n113 < 0) {
                n113 += n4;
            }
            this.Gb[5] = array[n113];
        }
    }
    
    private boolean c(final int n, final int n2) {
        return true;
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
    
    private void h() {
        this.m = (int)(6.283185307179586 * this.k / this.mb + 0.5);
        this.n = this.m >> 1;
        this.G = this.m * 256;
    }
    
    private int a(final int n, final int n2, final int n3, final int n4, final int n5, final int[] array) {
        return array[n / n5 >> n3] + (n2 >> n4);
    }
    
    private boolean a(final double n, final double n2) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        boolean i;
        final boolean b = i = this.i();
        if (!h) {
            if (!b) {
                return false;
            }
            final boolean b2;
            i = (b2 = this.b(n, n2));
        }
        if (!h) {
            if (!b) {
                return false;
            }
            i = true;
        }
        return i;
        i = false;
        return i;
    }
    
    private boolean i() {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final int n = this.Hb[0];
        if (!h) {
            if (n > this.x) {
                final int n2 = this.Hb[0];
                if (!h) {
                    if (n2 <= this.y) {
                        final int n3 = this.Gb[0];
                        if (!h) {
                            if (n3 <= this.A) {
                                final int n4 = this.Gb[0];
                                if (!h) {
                                    if (n4 >= this.z) {
                                        final int n5 = this.Hb[1];
                                        if (!h) {
                                            if (n5 > this.x) {
                                                final int n6 = this.Hb[1];
                                                if (!h) {
                                                    if (n6 <= this.y) {
                                                        final int n7 = this.Gb[1];
                                                        if (!h) {
                                                            if (n7 <= this.A) {
                                                                final int n8 = this.Gb[1];
                                                                if (!h) {
                                                                    if (n8 >= this.z) {
                                                                        final int n9 = this.Hb[2];
                                                                        if (!h) {
                                                                            if (n9 > this.x) {
                                                                                final int n10 = this.Hb[2];
                                                                                if (!h) {
                                                                                    if (n10 <= this.y) {
                                                                                        final int n11 = this.Gb[2];
                                                                                        if (!h) {
                                                                                            if (n11 <= this.A) {
                                                                                                final int n12 = this.Gb[2];
                                                                                                if (!h) {
                                                                                                    if (n12 >= this.z) {
                                                                                                        final int n13 = this.Hb[3];
                                                                                                        if (!h) {
                                                                                                            if (n13 > this.x) {
                                                                                                                final int n14 = this.Hb[3];
                                                                                                                if (!h) {
                                                                                                                    if (n14 <= this.y) {
                                                                                                                        final int n15 = this.Gb[3];
                                                                                                                        if (!h) {
                                                                                                                            if (n15 <= this.A) {
                                                                                                                                final int n16 = this.Gb[3];
                                                                                                                                if (!h) {
                                                                                                                                    if (n16 >= this.z) {
                                                                                                                                        final int n17 = this.Hb[4];
                                                                                                                                        if (!h) {
                                                                                                                                            if (n17 > this.x) {
                                                                                                                                                final int n18 = this.Hb[4];
                                                                                                                                                if (!h) {
                                                                                                                                                    if (n18 <= this.y) {
                                                                                                                                                        final int n19 = this.Gb[4];
                                                                                                                                                        if (!h) {
                                                                                                                                                            if (n19 <= this.A) {
                                                                                                                                                                final int n20 = this.Gb[4];
                                                                                                                                                                if (!h) {
                                                                                                                                                                    if (n20 >= this.z) {
                                                                                                                                                                        final int n21 = this.Hb[5];
                                                                                                                                                                        if (!h) {
                                                                                                                                                                            if (n21 > this.x) {
                                                                                                                                                                                final int n22 = this.Hb[5];
                                                                                                                                                                                if (!h) {
                                                                                                                                                                                    if (n22 <= this.y) {
                                                                                                                                                                                        final int n23 = this.Gb[5];
                                                                                                                                                                                        if (!h) {
                                                                                                                                                                                            if (n23 <= this.A) {
                                                                                                                                                                                                final int n24 = this.Gb[5];
                                                                                                                                                                                                if (!h) {
                                                                                                                                                                                                    if (n24 >= this.z) {
                                                                                                                                                                                                        final double n26;
                                                                                                                                                                                                        final double n25 = n26 = dcmpl(this.Q - this.R, 6.283185307179586);
                                                                                                                                                                                                        if (!h) {
                                                                                                                                                                                                            if (n25 == 0) {
                                                                                                                                                                                                                return true;
                                                                                                                                                                                                            }
                                                                                                                                                                                                            final int n27 = this.Gb[0];
                                                                                                                                                                                                        }
                                                                                                                                                                                                        if (!h) {
                                                                                                                                                                                                            if (n25 < this.Gb[1]) {
                                                                                                                                                                                                                final int n28 = this.Gb[1];
                                                                                                                                                                                                                if (!h) {
                                                                                                                                                                                                                    if (n28 < this.Gb[2]) {
                                                                                                                                                                                                                        final int n29 = this.Gb[3];
                                                                                                                                                                                                                        if (!h) {
                                                                                                                                                                                                                            if (n29 < this.Gb[4]) {
                                                                                                                                                                                                                                final int n30 = this.Gb[4];
                                                                                                                                                                                                                                if (!h) {
                                                                                                                                                                                                                                    if (n30 < this.Gb[5]) {
                                                                                                                                                                                                                                        return true;
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
            }
        }
        return n != 0;
    }
    
    private boolean b(final double n, final double n2) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final double n3 = 4096.0 * (Math.min(this.Z, this.T) - 0.008726646259971648);
        final double n4 = 4096.0 * (Math.max(this.Z - this.lb, this.U) + 0.008726646259971648);
        final int n5 = this.Hb[0];
        if (!h) {
            if (n5 > this.x) {
                final int n6 = this.Hb[0];
                if (!h) {
                    if (n6 <= this.y) {
                        final int n7 = this.Gb[0];
                        if (!h) {
                            if (n7 <= this.A) {
                                final int n8 = this.Gb[0];
                                if (!h) {
                                    if (n8 >= this.z) {
                                        final int n9 = this.Hb[1];
                                        if (!h) {
                                            if (n9 > this.x) {
                                                final int n10 = this.Hb[1];
                                                if (!h) {
                                                    if (n10 <= this.y) {
                                                        final int n11 = this.Gb[1];
                                                        if (!h) {
                                                            if (n11 <= this.A) {
                                                                final int n12 = this.Gb[1];
                                                                if (!h) {
                                                                    if (n12 >= this.z) {
                                                                        final int n13 = this.Hb[2];
                                                                        if (!h) {
                                                                            if (n13 > this.x) {
                                                                                final int n14 = this.Hb[2];
                                                                                if (!h) {
                                                                                    if (n14 <= this.y) {
                                                                                        final int n15 = this.Gb[2];
                                                                                        if (!h) {
                                                                                            if (n15 <= this.A) {
                                                                                                final int n16 = this.Gb[2];
                                                                                                if (!h) {
                                                                                                    if (n16 >= this.z) {
                                                                                                        final int n17 = this.Hb[3];
                                                                                                        if (!h) {
                                                                                                            if (n17 > this.x) {
                                                                                                                final int n18 = this.Hb[3];
                                                                                                                if (!h) {
                                                                                                                    if (n18 <= this.y) {
                                                                                                                        final int n19 = this.Gb[3];
                                                                                                                        if (!h) {
                                                                                                                            if (n19 <= this.A) {
                                                                                                                                final int n20 = this.Gb[3];
                                                                                                                                if (!h) {
                                                                                                                                    if (n20 >= this.z) {
                                                                                                                                        final int n21 = this.Hb[4];
                                                                                                                                        if (!h) {
                                                                                                                                            if (n21 > this.x) {
                                                                                                                                                final int n22 = this.Hb[4];
                                                                                                                                                if (!h) {
                                                                                                                                                    if (n22 <= this.y) {
                                                                                                                                                        final int n23 = this.Gb[4];
                                                                                                                                                        if (!h) {
                                                                                                                                                            if (n23 <= this.A) {
                                                                                                                                                                final int n24 = this.Gb[4];
                                                                                                                                                                if (!h) {
                                                                                                                                                                    if (n24 >= this.z) {
                                                                                                                                                                        final int n25 = this.Hb[5];
                                                                                                                                                                        if (!h) {
                                                                                                                                                                            if (n25 > this.x) {
                                                                                                                                                                                final int n26 = this.Hb[5];
                                                                                                                                                                                if (!h) {
                                                                                                                                                                                    if (n26 <= this.y) {
                                                                                                                                                                                        final int n27 = this.Gb[5];
                                                                                                                                                                                        if (!h) {
                                                                                                                                                                                            if (n27 <= this.A) {
                                                                                                                                                                                                final int n28 = this.Gb[5];
                                                                                                                                                                                                if (!h) {
                                                                                                                                                                                                    if (n28 >= this.z) {
                                                                                                                                                                                                        final int n29 = this.y - this.x;
                                                                                                                                                                                                        final int l = this.l;
                                                                                                                                                                                                        if (!h) {
                                                                                                                                                                                                            if (n29 == l) {
                                                                                                                                                                                                                return true;
                                                                                                                                                                                                            }
                                                                                                                                                                                                            final int n30 = (int)((n2 + n) * 4096.0);
                                                                                                                                                                                                            if (h) {
                                                                                                                                                                                                                return n5 != 0;
                                                                                                                                                                                                            }
                                                                                                                                                                                                            final int n31 = (int)n3;
                                                                                                                                                                                                        }
                                                                                                                                                                                                        if (n29 <= l) {
                                                                                                                                                                                                            final int n32 = (int)((n2 - n) * 4096.0);
                                                                                                                                                                                                            if (!h) {
                                                                                                                                                                                                                if (n32 >= (int)n4) {
                                                                                                                                                                                                                    return true;
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
        return n5 != 0;
    }
    
    private void a(final int n, final c c, final d[] array, final e[] array2, final int n2) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final int n3 = n2 - 1;
        if (!h) {
            if (n3 == this.N) {
                array2[array[0].a()].a(null);
                this.N = 0;
                array2[n].a(array[0].a(c));
                array[0].a(n);
                if (!h) {
                    return;
                }
            }
            array[++this.N].a();
        }
        final int n4 = n3;
        if (!h) {
            if (n4 != -1) {
                array2[n4].a(null);
            }
            array2[n].a(array[this.N].a(c));
        }
        array[this.N].a(n);
    }
}
