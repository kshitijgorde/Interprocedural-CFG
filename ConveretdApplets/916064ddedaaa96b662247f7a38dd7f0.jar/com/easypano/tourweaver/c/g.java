// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.c;

import java.awt.Component;

public final class g extends f
{
    private static final double i = 1.4142135623730951;
    private static final int j = 1;
    private static final int k = 2;
    private static final int l = 3;
    private static final int m = 4;
    private static final int n = 5;
    private static final int o = 6;
    private static final int p = 7;
    private static final int q = 8;
    private static final double r = 0.7853981633974483;
    private static final double s = 2.356194490192345;
    private static final double t = 3.9269908169872414;
    private static final double u = 4.71238898038469;
    private static final double v = 5.497787143782138;
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
    private int Q;
    private int R;
    private int S;
    private int T;
    private int U;
    private int V;
    private int W;
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
    private int[] kb;
    private int[] lb;
    private int[] mb;
    private int[] nb;
    private int[] ob;
    private int[] pb;
    private int[] qb;
    private b rb;
    private b sb;
    private b tb;
    private b ub;
    private a[] vb;
    private int[] wb;
    private int[] xb;
    private int[] yb;
    private d[] zb;
    private e[] Ab;
    private c[] Bb;
    private boolean Cb;
    private boolean Db;
    private boolean Eb;
    
    public g() {
        this.P = 0;
        this.wb = new int[16385];
        this.xb = new int[16385];
        this.yb = new int[4097];
        this.Db = true;
    }
    
    public void a(final c[] bb, final int n) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        this.Cb = true;
        final int length = bb.length;
        int n2 = n / 6;
        final c[] array = new c[n2];
        final c[] array2 = new c[length];
        this.Bb = new c[length];
        System.arraycopy(bb, 0, array2, 0, length);
        final int b = this.B;
        final int n3 = 7;
        int n4 = 0;
        Label_0368: {
            Label_0326: {
                if (!h) {
                    if (b == n3) {
                        int i = 0;
                        while (true) {
                            while (i < n2) {
                                System.arraycopy(array2, 3 * n2 + i * n, array, 0, n2);
                                System.arraycopy(array2, n2 + i * n, array2, 3 * n2 + i * n, n2);
                                System.arraycopy(array, 0, array2, n2 + i * n, n2);
                                ++i;
                                if (h) {
                                Label_0236_Outer:
                                    while (i < 6) {
                                        n4 = 0;
                                        if (!h) {
                                            int j = n4;
                                            while (true) {
                                                while (j < n2) {
                                                    System.arraycopy(array2, n2 * i + n * j, this.Bb, n2 * n2 * i + n2 * j, n2);
                                                    ++j;
                                                    if (!h) {
                                                        if (h) {
                                                            break;
                                                        }
                                                        continue Label_0236_Outer;
                                                    }
                                                    else {
                                                        if (h) {
                                                            break Label_0236_Outer;
                                                        }
                                                        continue Label_0163;
                                                    }
                                                }
                                                ++i;
                                                continue;
                                            }
                                        }
                                        break Label_0368;
                                    }
                                    break Label_0326;
                                }
                                if (h) {
                                    int o = com.easypano.tourweaver.b.a.o;
                                    com.easypano.tourweaver.b.a.o = ++o;
                                    break;
                                }
                            }
                            i = 0;
                            continue;
                        }
                    }
                    final int b2 = this.B;
                    if (h) {
                        break Label_0368;
                    }
                }
                if (b == n3) {
                    n2 = n;
                    final int n5 = n2 * n2;
                    final c[] array3 = new c[n5];
                    System.arraycopy(bb, 3 * n5, array3, 0, n5);
                    System.arraycopy(bb, n5, bb, 3 * n5, n5);
                    System.arraycopy(array3, 0, bb, n5, n5);
                    this.Bb = bb;
                }
            }
            this.Q = this.w / n;
            this.R = this.x / (this.Bb.length / n);
            (this.qb = new int[n2])[0] = 0;
        }
        int k = n4;
        while (true) {
            while (k < n2) {
                this.qb[k] = n2 + this.qb[k - 1];
                ++k;
                if (h) {
                    final int s = this.S;
                    if (!h) {
                        if (s == 0) {
                            this.Db = false;
                        }
                        this.T = -1;
                        this.zb = new d[this.S];
                        this.Ab = new e[length];
                    }
                    int l = s;
                    while (true) {
                        while (l < this.S) {
                            this.zb[l] = new d(new int[this.Q * this.R]);
                            ++l;
                            if (h) {
                                while (l < length) {
                                    this.Ab[l] = new e();
                                    ++l;
                                    if (h) {
                                        return;
                                    }
                                    if (h) {
                                        break;
                                    }
                                }
                                this.I = this.b(this.A / n2);
                                this.J = this.I;
                                this.U = this.A / n2 - 1;
                                this.V = this.U;
                                return;
                            }
                            if (h) {
                                break;
                            }
                        }
                        l = 0;
                        continue;
                    }
                }
                if (h) {
                    break;
                }
            }
            this.K = n2 * n2;
            this.L = this.K << 1;
            this.M = this.L + this.K;
            this.N = this.M + this.K;
            this.O = this.N + this.K;
            this.S = 37748736 / (4 * this.Q * this.R);
            continue;
        }
    }
    
    public void a(final int[] array, final int n, final int n2, final int n3, final int n4) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        this.Cb = false;
        ++this.P;
        final int b = this.B;
        if (!h && b == 7) {
            int n5 = n3;
            Label_0158: {
                if (!h) {
                    if (n3 == this.w) {
                        n5 = n4;
                        if (h) {
                            break Label_0158;
                        }
                        if (n4 == this.x) {
                            int i = 0;
                        Label_0147_Outer:
                            while (i < 6) {
                                if (!h) {
                                    int j = 0;
                                    while (true) {
                                        while (j < this.A) {
                                            System.arraycopy(array, j * this.w + i * this.A, this.kb, j * this.A + this.E * i, this.A);
                                            ++j;
                                            if (!h) {
                                                if (h) {
                                                    break;
                                                }
                                                continue Label_0147_Outer;
                                            }
                                            else {
                                                if (h) {
                                                    break Label_0147_Outer;
                                                }
                                                continue Label_0147_Outer;
                                            }
                                        }
                                        ++i;
                                        continue;
                                    }
                                }
                                return;
                            }
                            if (!h) {
                                return;
                            }
                        }
                    }
                    n5 = 0;
                }
            }
            int k = n5;
            while (k < n4) {
                System.arraycopy(array, k * n3, this.kb, n % this.A + this.A * (k + n2 + n / this.A * this.A), n3);
                ++k;
                if (h) {
                    return;
                }
                if (h) {
                    break;
                }
            }
            if (h) {
                goto Label_0228;
            }
        }
        else {
            int l = b;
            while (l < n4) {
                System.arraycopy(array, l * n3, this.kb, n + (l + n2) * this.w, n3);
                ++l;
                if (h) {
                    break;
                }
            }
        }
    }
    
    public void a(final int n, final int n2, final double n3, final double n4, final double n5, final double n6, final double z, final double ab, final double n7, final double n8, final double hb, final double gb, final double n9, final boolean cb, final int n10, final int n11, final Component component) {
        this.fb = this.d(3.141592653589793 - n9);
        this.gb = gb;
        this.hb = hb;
        this.Z = z;
        this.ab = ab;
        this.Cb = cb;
        this.cb = 0.5235987755982988;
        this.bb = 2.6179938779914944;
        this.a(n6, n5);
        this.b(n4, n3);
        this.a(n, n2, cb);
    }
    
    public void a(int y, final int z, final int[] pb, final Component component) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        this.Eb = false;
        final int n = y % 2;
        if (!h) {
            if (n != 0) {
                --y;
                this.Eb = true;
            }
            this.y = y;
            this.z = z;
            this.G = y / 2;
            this.H = z / 2;
            this.vb = new a[9];
            this.pb = pb;
        }
        int i = n;
        while (true) {
        Label_0238_Outer:
            while (i < this.pb.length) {
                this.pb[i] = 0;
                ++i;
                if (h) {
                    int j = 0;
                    while (true) {
                        while (j < 8) {
                            this.vb[j] = new a(this);
                            ++j;
                            if (h) {
                                int k = 75;
                            Label_0557:
                                while (true) {
                                    while (k >= 15) {
                                        final double n2 = this.y / Math.tan(k * 0.017453292519943295) * 0.5;
                                        final double atan = Math.atan(this.z * 0.5 / n2);
                                        final double atan2 = Math.atan(this.y / Math.sqrt(n2 * n2 + this.z * this.z * 0.25) * 0.5);
                                        final double atan3 = Math.atan(0.7071067811865475);
                                        if (!h) {
                                            final double n3 = atan2;
                                            final double n4 = 0.7853981633974483;
                                            Label_0521: {
                                                if (!h && n3 > n4) {
                                                    if (k == 15) {
                                                        this.Db = false;
                                                        if (h) {
                                                            goto Label_0374;
                                                        }
                                                    }
                                                }
                                                else {
                                                    double n5 = n3 - n4;
                                                    final double n6 = dcmpg(n5, 0.0);
                                                    if (!h) {
                                                        if (n6 < 0) {
                                                            n5 = 1.5707963267948966 - atan;
                                                            final double n7 = dcmpl(atan - n5, atan3);
                                                            if (!h) {
                                                                if (n7 > 0) {
                                                                    if (k != 15) {
                                                                        break Label_0521;
                                                                    }
                                                                    this.Db = false;
                                                                    if (!h) {
                                                                        break Label_0521;
                                                                    }
                                                                }
                                                                this.jb = n5;
                                                                this.bb = k * 2 * 0.017453292519943295;
                                                                if (h) {
                                                                    break Label_0557;
                                                                }
                                                            }
                                                            if (n7 != 15) {
                                                                break;
                                                            }
                                                            this.Db = false;
                                                            if (!h) {
                                                                break;
                                                            }
                                                        }
                                                        this.jb = n5;
                                                        this.bb = k * 2 * 0.017453292519943295;
                                                        if (h) {
                                                            break Label_0557;
                                                        }
                                                    }
                                                    if (n6 != 15) {
                                                        break;
                                                    }
                                                    this.Db = false;
                                                    if (!h) {
                                                        break;
                                                    }
                                                }
                                            }
                                            --k;
                                            if (h) {
                                                break;
                                            }
                                            continue Label_0238_Outer;
                                        }
                                        if (com.easypano.tourweaver.b.a.o != 0) {
                                            com.easypano.tourweaver.c.f.h = !h;
                                        }
                                        return;
                                    }
                                    this.c(this.ab, this.Z);
                                    this.a(this.fb, this.gb, this.hb);
                                    continue Label_0557;
                                }
                            }
                            if (h) {
                                break;
                            }
                        }
                        this.vb[8] = new a(this, this.y - 1);
                        this.rb = new b(this);
                        this.sb = new b(this);
                        this.tb = new b(this);
                        this.ub = new b(this);
                        continue;
                    }
                }
                if (h) {
                    break;
                }
            }
            this.W = (int)(this.y / Math.tan(this.hb / 2.0) / 2.0 + 0.5);
            continue;
        }
    }
    
    public boolean a(final int w, final int x, final boolean b) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        double n5;
        double n4;
        double n3;
        final double n2;
        final double n = n2 = (n3 = (n4 = (n5 = dcmpl(this.X, 1.5707963267948966))));
        final double n7;
        final double n8;
        final double n9;
        Label_0144: {
            final double n6;
            Label_0058: {
                if (!h) {
                    if (n == 0) {
                        n6 = (n3 = (n4 = (n5 = dcmpl(this.Y, -1.5707963267948966))));
                        if (h) {
                            break Label_0058;
                        }
                        if (n6 != 0) {
                            this.ib = this.X;
                            if (!h) {
                                break Label_0144;
                            }
                        }
                    }
                    n7 = dcmpl(this.X, 1.5707963267948966);
                }
            }
            Label_0103: {
                if (!h) {
                    if (n != 0) {
                        n8 = (n4 = (n5 = dcmpl(this.Y, -1.5707963267948966)));
                        if (h) {
                            break Label_0103;
                        }
                        if (n8 == 0) {
                            this.ib = this.Y;
                            if (!h) {
                                break Label_0144;
                            }
                        }
                    }
                    n5 = dcmpl(this.X, 1.5707963267948966);
                }
            }
            if (!h) {
                if (n6 != 0) {
                    n9 = (n5 = dcmpl(this.Y, -1.5707963267948966));
                    if (!h) {
                        if (n9 != 0) {
                            this.ib = (this.X + this.Y) * 0.5;
                        }
                    }
                }
            }
        }
        int n11;
        final int n10 = n11 = 6;
        Label_0356: {
            if (!h) {
                if (n8 != n10) {
                    goto Label_0205;
                }
                final double n12 = n4 = (n5 = w % x);
                if (!h && n12 == 0) {
                    this.A = x;
                    this.F = this.E >> 6;
                    this.w = w;
                    this.x = x;
                    this.B = 7;
                    if (h) {
                        goto Label_0205;
                    }
                    break Label_0356;
                }
                else {
                    final int n13;
                    n11 = (n13 = 6);
                }
            }
            g g = null;
            boolean db = false;
            Label_0353: {
                int n15 = 0;
                Label_0337: {
                    if (!h) {
                        Label_0256: {
                            if (n9 == n10) {
                                final int n14 = (int)(n5 = (n15 = x % w));
                                if (h) {
                                    break Label_0256;
                                }
                                if (n14 == 0) {
                                    this.A = w;
                                    this.w = w;
                                    this.x = x;
                                    this.B = 8;
                                    if (!h) {
                                        break Label_0356;
                                    }
                                }
                            }
                            n15 = x;
                        }
                        if (h) {
                            break Label_0337;
                        }
                        n11 = w;
                    }
                    if (n7 >= n11) {
                        this.A = w;
                        this.w = w;
                        this.x = w * 6;
                        if (!h) {
                            if (!b) {
                                this.B = 8;
                                if (!h) {
                                    break Label_0356;
                                }
                            }
                            this.Db = false;
                        }
                        if (!h) {
                            break Label_0356;
                        }
                    }
                    this.A = x;
                    this.w = x * 6;
                    g = this;
                    db = (x != 0);
                    if (h) {
                        break Label_0353;
                    }
                    this.x = x;
                    n15 = (b ? 1 : 0);
                }
                if (n15 == 0) {
                    this.B = 7;
                    if (!h) {
                        break Label_0356;
                    }
                }
                g = this;
                db = false;
            }
            g.Db = db;
        }
        this.E = this.A * this.A;
        this.D = this.A / 2;
        boolean b2 = b;
        if (!h) {
            if (!b) {
                this.kb = new int[this.w * this.x];
            }
            this.g();
            b2 = true;
        }
        return b2;
    }
    
    public double a(double d) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        d = this.d(3.141592653589793 - d);
        final double fb = this.fb;
        if (!h) {
            g g = null;
            final double n;
            final double gb;
            Label_0087: {
                if (fb != d) {
                    g = this;
                    n = d;
                    gb = this.gb;
                    if (h) {
                        break Label_0087;
                    }
                    if (this.a(n, gb, this.hb, this.y, this.z, this.X, this.Y, this.db, this.eb)) {
                        this.fb = d;
                    }
                }
                g = this;
                final double fb2 = this.fb;
            }
            g.d(n - gb);
        }
        return fb;
    }
    
    public double b(double gb) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final double gb2 = this.gb;
        if (!h) {
            g g = null;
            Label_0103: {
                if (gb2 != gb) {
                    double a;
                    final double n = a = dcmpg(gb, this.Y);
                    if (!h) {
                        if (n < 0) {
                            gb = this.Y;
                        }
                        final int n2;
                        a = (n2 = dcmpl(gb, this.X));
                    }
                    if (!h) {
                        if (n > 0) {
                            gb = this.X;
                        }
                        g = this;
                        if (h) {
                            break Label_0103;
                        }
                        a = (this.a(this.fb, gb, this.hb, this.y, this.z, this.X, this.Y, this.db, this.eb) ? 1 : 0);
                    }
                    if (a != 0) {
                        this.gb = gb;
                    }
                }
                g = this;
            }
            final double gb3 = g.gb;
        }
        return gb2;
    }
    
    public double c(double n) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final double hb = this.hb;
        if (!h) {
            g g = null;
            Label_0351: {
                Label_0350: {
                    if (hb != n) {
                        int z;
                        int a;
                        final int n2 = a = (z = dcmpg(n, this.ab));
                        if (!h) {
                            if (n2 < 0) {
                                n = this.ab;
                            }
                            final double n3;
                            a = (int)(n3 = (z = dcmpl(n, this.Z)));
                        }
                        if (!h) {
                            if (n2 > 0) {
                                n = this.Z;
                            }
                            z = (a = (this.a(this.fb, this.gb, n, this.y, this.z, this.X, this.Y, this.db, this.eb) ? 1 : 0));
                        }
                        if (!h) {
                            if (a != 0) {
                                this.hb = n;
                                this.W = (int)(this.y / Math.tan(this.hb * 0.5) * 0.5 + 0.5);
                                if (!h) {
                                    break Label_0350;
                                }
                            }
                            z = this.z;
                        }
                        final double atan = Math.atan(z * Math.tan(n * 0.5) / this.y);
                        final double atan2 = Math.atan(this.z * Math.tan(this.hb * 0.5) / this.y);
                        double gb = this.gb;
                        double a2;
                        final double n4 = a2 = dcmpl(this.gb, this.ib);
                        Label_0309: {
                            Label_0269: {
                                if (!h) {
                                    if (n4 > 0) {
                                        gb = this.gb - (atan - atan2) * 2.0;
                                        if (!h) {
                                            break Label_0269;
                                        }
                                    }
                                    final int n5;
                                    a2 = (n5 = dcmpg(this.gb, this.ib));
                                }
                                if (h) {
                                    break Label_0309;
                                }
                                if (n4 < 0) {
                                    gb = this.gb + (atan - atan2) * 2.0;
                                }
                            }
                            g = this;
                            if (h) {
                                break Label_0351;
                            }
                            a2 = (this.a(this.fb, gb, n, this.y, this.z, this.X, this.Y, this.db, this.eb) ? 1 : 0);
                        }
                        if (a2 != 0) {
                            this.gb = gb;
                            this.hb = n;
                            this.W = (int)(this.y / Math.tan(n * 0.5) * 0.5 + 0.5);
                        }
                    }
                }
                g = this;
            }
            final double hb2 = g.hb;
        }
        return hb;
    }
    
    public void a() {
    }
    
    public double b() {
        return this.gb;
    }
    
    public double c() {
        return this.d(3.141592653589793 - this.fb);
    }
    
    private double d(double n) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        double n2 = 0.0;
        while (n > 6.283185307179586) {
            n -= 6.283185307179586;
            if (h) {
                return n2;
            }
            if (h) {
                break;
            }
        }
        while (n < 0.0) {
            n2 = n + 6.283185307179586;
            if (h) {
                break;
            }
            n = n2;
            if (h) {
                break;
            }
        }
        return n2;
    }
    
    public double d() {
        return this.hb;
    }
    
    public void a(int n, int n2) {
        final int b = this.b(n, n2);
        if (this.B == 7) {
            n %= this.A;
        }
        else if (this.B == 8) {
            n2 %= this.A;
        }
        if (!this.a(this.gb, this.fb, this.W, b, n, n2, this.D, this.H, this.G)) {
            super.f = this.y + 1000;
            super.g = this.z + 1000;
        }
    }
    
    public void a(final int c) {
        this.C = c;
    }
    
    public void a(final boolean b) {
        if (this.Db) {
            if (this.Cb) {
                if (this.C == 1) {
                    if (!b) {
                        this.a(this.D, this.y, this.z, this.fb, this.gb, this.A, this.W, this.vb, this.mb, this.lb, this.J, this.I, this.U, this.V, this.K, this.L, this.M, this.N, this.O, this.Ab, this.Bb, this.zb, this.qb, this.S);
                    }
                    else {
                        this.b(this.D, this.y, this.z, this.fb, this.gb, this.A, this.W, this.vb, this.mb, this.lb, this.J, this.I, this.U, this.V, this.K, this.L, this.M, this.N, this.O, this.Ab, this.Bb, this.zb, this.qb, this.S);
                    }
                }
                else {
                    this.a(this.D, this.y, this.z, this.fb, this.gb, this.A, this.W, this.vb, this.mb, this.J, this.I, this.U, this.V, this.K, this.L, this.M, this.N, this.O, this.Ab, this.Bb, this.zb, this.qb, this.S);
                }
            }
            else if (this.C == 1) {
                this.b(this.D, this.y, this.z, this.fb, this.gb, this.A, this.E, this.W, this.vb);
            }
            else {
                this.a(this.D, this.y, this.z, this.fb, this.gb, this.A, this.E, this.W, this.vb);
            }
        }
    }
    
    private void a(double eb, double db) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final double n = dcmpl(db, 6.283185307179586);
        Label_0035: {
            final double n2;
            Label_0034: {
                if (!h) {
                    if (n > 0) {
                        db = 6.283185307179586;
                    }
                    n2 = eb;
                    if (h) {
                        break Label_0034;
                    }
                    final double n3 = dcmpg(n2, 0.0);
                }
                if (n >= 0) {
                    break Label_0035;
                }
            }
            eb = n2;
        }
        this.eb = eb;
        this.db = db;
    }
    
    private void b(double y, double x) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final double n = dcmpg(y, -1.5707963267948966);
        Label_0039: {
            final double n2;
            Label_0038: {
                if (!h) {
                    if (n < 0) {
                        y = -1.5707963267948966;
                    }
                    n2 = x;
                    if (h) {
                        break Label_0038;
                    }
                    final double n3 = dcmpl(n2, 1.5707963267948966);
                }
                if (n <= 0) {
                    break Label_0039;
                }
            }
            x = n2;
        }
        this.Y = y;
        this.X = x;
    }
    
    private void c(double cb, double bb) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final double n = dcmpl(this.cb, cb);
        g g = null;
        Label_0049: {
            if (!h) {
                if (n > 0) {
                    cb = this.cb;
                }
                g = this;
                if (h) {
                    break Label_0049;
                }
                final double n2 = dcmpg(this.bb, bb);
            }
            if (n < 0) {
                bb = this.bb;
            }
            this.ab = cb;
            g = this;
        }
        g.Z = bb;
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
    
    private void a(double n, double n2, double n3) {
        final boolean h = com.easypano.tourweaver.c.f.h;
    Label_0070_Outer:
        while (true) {
        Label_0070:
            while (true) {
                while (n > 6.283185307179586) {
                    n -= 6.283185307179586;
                    if (h) {
                        double a;
                        int n8;
                        double n7;
                        double n6;
                        int n5;
                        final int n4 = n5 = (int)(n6 = (n7 = (n8 = (int)(a = dcmpg(n, this.eb)))));
                        if (!h) {
                            if (n4 < 0) {
                                n = this.eb;
                            }
                            final int n9;
                            n5 = (n9 = (int)(n6 = (n7 = (n8 = (int)(a = dcmpl(n, this.db))))));
                        }
                        if (!h) {
                            if (n4 > 0) {
                                n = this.db;
                            }
                            n6 = (n5 = (int)(n7 = (n8 = (int)(a = dcmpg(n2, this.Y)))));
                        }
                        if (!h) {
                            if (n5 < 0) {
                                n2 = this.Y;
                            }
                            n7 = (n6 = (n8 = (int)(a = dcmpl(n2, this.X))));
                        }
                        if (!h) {
                            if (n6 > 0) {
                                n2 = this.X;
                            }
                            n8 = (int)(n7 = (a = dcmpg(n3, this.ab)));
                        }
                        if (!h) {
                            if (n7 < 0) {
                                n3 = this.ab;
                            }
                            a = (n8 = dcmpl(n3, this.Z));
                        }
                        g g = null;
                        final double n10;
                        final double n11;
                        final double n12;
                        Label_0274: {
                            if (!h) {
                                if (n8 > 0) {
                                    n3 = this.Z;
                                }
                                g = this;
                                n10 = n;
                                n11 = n2;
                                n12 = n3;
                                if (h) {
                                    break Label_0274;
                                }
                                a = (this.a(n10, n11, n12, this.y, this.z, this.X, this.Y, this.db, this.eb) ? 1 : 0);
                            }
                            if (a != 0) {
                                this.fb = n;
                                this.gb = n2;
                                this.hb = n3;
                                this.W = (int)(this.y / Math.tan(n3 * 0.5) * 0.5 + 0.5);
                                if (!h) {
                                    return;
                                }
                            }
                            g = this;
                            final double n13 = this.db + this.eb;
                        }
                        n = g.d(n10 - n11 * n12);
                        n2 = this.ib;
                        n3 = Math.atan(this.y * Math.tan((this.X - this.Y) * 0.5 - 0.013962634015954637) / this.z) * 2.0;
                        double n15;
                        final double n14 = n15 = dcmpg(n3, this.ab);
                        if (!h) {
                            if (n14 < 0) {
                                n3 = this.ab;
                            }
                            final int n16;
                            n15 = (n16 = dcmpl(n3, this.Z));
                        }
                        if (!h) {
                            if (n14 > 0) {
                                n3 = this.Z;
                            }
                            n15 = (int)(this.y / Math.tan(n3 * 0.5) * 0.5 + 0.5);
                        }
                        final double w = n15;
                        g g2 = this;
                        if (!h) {
                            if (this.a(n, n2, n3, this.y, this.z, this.X, this.Y, this.db, this.eb)) {
                                this.fb = n;
                                this.gb = n2;
                                this.hb = n3;
                                this.W = (int)w;
                                if (!h) {
                                    return;
                                }
                            }
                            g2 = this;
                        }
                        g2.Db = false;
                        return;
                    }
                    if (h) {
                        break;
                    }
                }
                while (n < 0.0) {
                    n += 6.283185307179586;
                    if (h) {
                        continue Label_0070;
                    }
                    if (h) {
                        break;
                    }
                }
                break;
            }
            continue Label_0070_Outer;
        }
    }
    
    private boolean a(double d, final double n, final double n2, final int n3, final int n4, final double n5, final double n6, final double n7, final double n8) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final double n9 = n2 * 0.5;
        d = this.d(3.141592653589793 - d);
        double n10 = d + n9;
        double n11 = d - n9;
        final double atan = Math.atan(n4 * Math.tan(n9) / n3);
        final double n12 = n + atan;
        final double n13 = n - atan;
    Label_0144_Outer:
        while (true) {
        Label_0144:
            while (true) {
                while (n10 > 6.283185307179586) {
                    n10 -= 6.283185307179586;
                    if (h) {
                        double n15 = 0.0;
                        final double n14;
                        final double n17;
                        Label_0201: {
                            Label_0196: {
                                Label_0169: {
                                    while (n11 > 6.283185307179586) {
                                        n11 -= 6.283185307179586;
                                        if (h) {
                                            break Label_0169;
                                        }
                                        if (h) {
                                            break;
                                        }
                                    }
                                    while (n11 < 0.0) {
                                        n11 += 6.283185307179586;
                                        if (h) {
                                            break Label_0196;
                                        }
                                        if (h) {
                                            break;
                                        }
                                    }
                                }
                                final double n16;
                                n14 = (n15 = (n16 = dcmpg(n12, n5)));
                                if (h) {
                                    break Label_0201;
                                }
                                if (n14 >= 0) {
                                    n17 = dcmpl(n5, 1.5707963267948966);
                                    if (h) {
                                        return n17 != 0.0;
                                    }
                                    if (n17 != 0.0) {
                                        return n17 != 0.0;
                                    }
                                }
                            }
                            double n16;
                            final double n18;
                            n15 = (n18 = (n16 = dcmpl(n13, n6)));
                        }
                        if (!h) {
                            if (n14 <= 0) {
                                final double n19 = dcmpl(n6, -1.5707963267948966);
                                if (h) {
                                    return n17 != 0.0;
                                }
                                if (n19 != 0) {
                                    return n17 != 0.0;
                                }
                            }
                            n15 = dcmpl(n11, n8);
                        }
                        if (!h) {
                            if (n15 > 0) {
                                final double n20 = dcmpg(n10, n7);
                                if (!h) {
                                    if (n20 < 0) {
                                        final double n21 = dcmpg(n11, n10);
                                        if (h || n21 < 0) {
                                            return n21 != 0.0;
                                        }
                                        final double n22 = dcmpl(n7 - n8, 6.283185307179586);
                                        if (!h) {
                                            if (n22 == 0) {
                                                goto Label_0279;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        return n17 != 0.0;
                    }
                    if (h) {
                        break;
                    }
                }
                while (n10 < 0.0) {
                    n10 += 6.283185307179586;
                    if (h) {
                        continue Label_0144;
                    }
                    if (h) {
                        break;
                    }
                }
                break;
            }
            continue Label_0144_Outer;
        }
    }
    
    private final void a(final int n, final int n2, final int n3, final double n4, final double n5, final int n6, final int n7, final a[] array, final int[] array2, final int n8, final int n9, final int n10, final int n11, final int n12, final int n13, final int n14, final int n15, final int n16, final e[] array3, final c[] array4, final d[] array5, final int[] array6, final int n17) {
        int n18 = 0;
        final int n19 = n << 8;
        final int n20 = n6 << 8;
        final int n21 = n20 - 256;
        double n22 = Math.cos(n5);
        final double sin = Math.sin(n5);
        final double cos = Math.cos(n4);
        double n23 = Math.sin(n4);
        if (sin == 1.0 || sin == -1.0) {
            n22 = Math.cos(1.5729779883598896);
        }
        if (cos == 1.0 || cos == -1.0) {
            n23 = Math.sin(8.726646259971648E-4);
        }
        final int n24 = this.F << 1;
        final int n25 = n24 + this.F;
        final int n26 = n24 << 1;
        final int n27 = n26 + this.F;
        final int n28 = -n2 / 2;
        final int n29 = n3 / 2;
        int n30 = -n29;
        if (n3 % 2 != 0) {
            --n30;
        }
        final double n31 = n19 * n23;
        final double n32 = n19 * cos;
        this.a(n7, n3, n6, n4, n5, n22, sin, cos, n23, array, this.rb, this.sb, this.tb, this.ub);
        final double n33 = -n32;
        final double n34 = -n31;
        final int n35 = this.Eb ? (n2 + 1) : n2;
        double n36 = n7 * sin + n29 * n22;
        final double n37 = -n22;
        double n38 = n7 * n22 - n29 * sin;
        final double n39 = sin;
        final double n40 = -cos;
        final double n41 = n23;
        double n42 = n38 * n23;
        double n43 = n38 * cos;
        final double n44 = sin * n23;
        final double n45 = sin * cos;
        int n46 = 0;
        double n47 = n42 - (n28 + 1) * cos;
        double n48 = n43 + (n28 + 1) * n23;
        double n49 = n42 - n28 * cos;
        double n50 = n43 + n28 * n23;
        double n51 = n31 * n36;
        double n52 = n19 * n38;
        double n53 = n32 * n36;
        double n54 = n19 * n36;
        final double n55 = n31 * n37;
        final double n56 = n19 * n39;
        final double n57 = n32 * n37;
        final double n58 = n19 * n37;
        for (int i = n29; i > n30; --i) {
            double n59 = n49;
            double n60 = n50;
            final double n61 = n19 / n36;
            for (int j = 0; j < 8; ++j) {
                array[j].a((int)array[j].c(i));
            }
            this.a(array, 9);
            for (int k = 0; k < 8; ++k) {
                if (array[k].a > 0) {
                    n18 = k;
                    break;
                }
            }
            final int n62 = (int)(n33 / n36);
            final int n63 = (int)(n34 / n36);
            final int n64 = -n62;
            final int n65 = n63;
            final double n66 = (n36 < 0.0) ? (-n36) : n36;
            final double n67 = (n47 < 0.0) ? (-n47) : n47;
            final double n68 = (n48 < 0.0) ? (-n48) : n48;
            int b;
            if (n66 <= n68 && n67 <= n68) {
                if (n48 < 0.0) {
                    b = 1;
                }
                else {
                    b = 3;
                }
            }
            else if (n66 <= n67) {
                if (n47 < 0.0) {
                    b = 2;
                }
                else {
                    b = 4;
                }
            }
            else if (n36 > 0.0) {
                b = 5;
            }
            else {
                b = 6;
            }
            double n69 = n43 + n28 * n23;
            double n70 = n42 - n28 * cos;
            int l = 0;
            while (l < n2) {
                int n71 = 0;
                final int n72 = array[n18].a - l + 1;
                final int n73 = (n72 >> 4) + (((n72 & 0xF) > 0) ? 1 : 0);
                if (b == 1) {
                    int n74 = 16;
                    while (n71 < n73) {
                        final double n75 = n69 * n69;
                        int n76 = (int)(n19 - n19 * n59 / n60);
                        int n77 = (int)(n19 + n54 / n60);
                        final int n78 = (int)(n52 / n75);
                        final int n79 = (int)(-n51 / n75);
                        int n80 = (n74 <= n72) ? 16 : (n72 & 0xF);
                        n74 += n80;
                        while (n80-- > 0) {
                            if (n77 > n20) {
                                n77 = n20;
                            }
                            if (n77 < 0) {
                                n77 = 0;
                            }
                            if (n76 > n21) {
                                n76 = n21;
                            }
                            if (n76 < 0) {
                                n76 = 0;
                            }
                            final int n81 = array2[n77 >> 8] / n6;
                            final int n82 = n76 >> 8;
                            final int a = this.a(n81, n82, n8, n9, array6, 0);
                            if (array4[a].h()) {
                                if (array3[a].a() == null) {
                                    this.a(a, array4[a], array5, array3, n17);
                                }
                                this.pb[n46 + l] = array3[a].a(n9, n81, n82, n10, n11, true);
                            }
                            else {
                                this.pb[n46 + l] = this.kb[this.ob[n77 >> 3 >> 8] + (n82 >> 3)];
                            }
                            n76 += n78;
                            n77 += n79;
                            ++l;
                            n70 -= cos;
                            n69 += n23;
                            n59 += n40;
                            n60 += n41;
                        }
                        ++n71;
                    }
                }
                else if (b == 3) {
                    int n83 = 16;
                    while (n71 < n73) {
                        final double n84 = n69 * n69;
                        int n85 = (int)(n19 - n19 * n59 / n60);
                        int n86 = (int)(n19 - n54 / n60);
                        final int n87 = (int)(n52 / n84);
                        final int n88 = (int)(n51 / n84);
                        int n89 = (n83 <= n72) ? 16 : (n72 & 0xF);
                        n83 += n89;
                        while (n89-- > 0) {
                            if (n86 > n20) {
                                n86 = n20;
                            }
                            if (n86 < 0) {
                                n86 = 0;
                            }
                            if (n85 > n21) {
                                n85 = n21;
                            }
                            if (n85 < 0) {
                                n85 = 0;
                            }
                            final int n90 = array2[n86 >> 8] / n6;
                            final int n91 = n85 >> 8;
                            final int a2 = this.a(n90, n91, n8, n9, array6, n13);
                            if (array4[a2].h()) {
                                if (array3[a2].a() == null) {
                                    this.a(a2, array4[a2], array5, array3, n17);
                                }
                                this.pb[n46 + l] = array3[a2].a(n9, n90, n91, n10, n11, true);
                            }
                            else {
                                this.pb[n46 + l] = this.kb[this.ob[n86 >> 3 >> 8] + (n91 >> 3) + n24];
                            }
                            n85 += n87;
                            n86 += n88;
                            ++l;
                            n70 -= cos;
                            n69 += n23;
                            n59 += n40;
                            n60 += n41;
                        }
                        ++n71;
                    }
                }
                else if (b == 2) {
                    int n92 = 16;
                    while (n71 < n73) {
                        final double n93 = n70 * n70;
                        int n94 = (int)(n19 + n19 * n60 / n59);
                        int n95 = (int)(n19 + n54 / n59);
                        final int n96 = (int)(n52 / n93);
                        final int n97 = (int)(n53 / n93);
                        int n98 = (n92 <= n72) ? 16 : (n72 & 0xF);
                        n92 += n98;
                        while (n98-- > 0) {
                            if (n95 > n20) {
                                n95 = n20;
                            }
                            if (n95 < 0) {
                                n95 = 0;
                            }
                            if (n94 > n21) {
                                n94 = n21;
                            }
                            if (n94 < 0) {
                                n94 = 0;
                            }
                            final int n99 = array2[n95 >> 8] / n6;
                            final int n100 = n94 >> 8;
                            final int a3 = this.a(n99, n100, n8, n9, array6, n12);
                            if (array4[a3].h()) {
                                if (array3[a3].a() == null) {
                                    this.a(a3, array4[a3], array5, array3, n17);
                                }
                                this.pb[n46 + l] = array3[a3].a(n9, n99, n100, n10, n11, true);
                            }
                            else {
                                this.pb[n46 + l] = this.kb[this.ob[n95 >> 3 >> 8] + (n100 >> 3) + n25];
                            }
                            n94 += n96;
                            n95 += n97;
                            ++l;
                            n70 -= cos;
                            n69 += n23;
                            n59 += n40;
                            n60 += n41;
                        }
                        ++n71;
                    }
                }
                else if (b == 4) {
                    int n101 = 16;
                    while (n71 < n73) {
                        final double n102 = n70 * n70;
                        int n103 = (int)(n19 + n19 * n60 / n59);
                        int n104 = (int)(n19 - n54 / n59);
                        final int n105 = (int)(n52 / n102);
                        final int n106 = (int)(-n53 / n102);
                        int n107 = (n101 <= n72) ? 16 : (n72 & 0xF);
                        n101 += n107;
                        while (n107-- > 0) {
                            if (n104 > n20) {
                                n104 = n20;
                            }
                            if (n104 < 0) {
                                n104 = 0;
                            }
                            if (n103 > n21) {
                                n103 = n21;
                            }
                            if (n103 < 0) {
                                n103 = 0;
                            }
                            final int n108 = array2[n104 >> 8] / n6;
                            final int n109 = n103 >> 8;
                            final int a4 = this.a(n108, n109, n8, n9, array6, n14);
                            if (array4[a4].h()) {
                                if (array3[a4].a() == null) {
                                    this.a(a4, array4[a4], array5, array3, n17);
                                }
                                this.pb[n46 + l] = array3[a4].a(n9, n108, n109, n10, n11, true);
                            }
                            else {
                                this.pb[n46 + l] = this.kb[this.ob[n104 >> 3 >> 8] + (n109 >> 3) + this.F];
                            }
                            n103 += n105;
                            n104 += n106;
                            ++l;
                            n70 -= cos;
                            n69 += n23;
                            n59 += n40;
                            n60 += n41;
                        }
                        ++n71;
                    }
                }
                else if (b == 5) {
                    int n110 = 16;
                    while (n71 < n73) {
                        int n111 = (int)(n19 + n61 * n59);
                        int n112 = (int)(n19 - n61 * n60);
                        int n113 = (n110 <= n72) ? 16 : (n72 & 0xF);
                        n110 += n113;
                        while (n113-- > 0) {
                            if (n112 > n20) {
                                n112 = n20;
                            }
                            if (n112 < 0) {
                                n112 = 0;
                            }
                            if (n111 > n21) {
                                n111 = n21;
                            }
                            if (n111 < 0) {
                                n111 = 0;
                            }
                            final int n114 = array2[n112 >> 8] / n6;
                            final int n115 = n111 >> 8;
                            final int a5 = this.a(n114, n115, n8, n9, array6, n15);
                            if (array4[a5].h()) {
                                if (array3[a5].a() == null) {
                                    this.a(a5, array4[a5], array5, array3, n17);
                                }
                                this.pb[n46 + l] = array3[a5].a(n9, n114, n115, n10, n11, true);
                            }
                            else {
                                this.pb[n46 + l] = this.kb[this.ob[n112 >> 3 >> 8] + (n115 >> 3) + n26];
                            }
                            n111 += n62;
                            n112 += n63;
                            ++l;
                            n70 -= cos;
                            n69 += n23;
                            n59 += n40;
                            n60 += n41;
                        }
                        ++n71;
                    }
                }
                else if (b == 6) {
                    int n116 = 16;
                    while (n71 < n73) {
                        int n117 = (int)(n19 - n61 * n59);
                        int n118 = (int)(n19 - n61 * n60);
                        int n119 = (n116 <= n72) ? 16 : (n72 & 0xF);
                        n116 += n119;
                        while (n119-- > 0) {
                            if (n118 > n20) {
                                n118 = n20;
                            }
                            if (n118 < 0) {
                                n118 = 0;
                            }
                            if (n117 > n21) {
                                n117 = n21;
                            }
                            if (n117 < 0) {
                                n117 = 0;
                            }
                            final int n120 = array2[n118 >> 8] / n6;
                            final int n121 = n117 >> 8;
                            final int a6 = this.a(n120, n121, n8, n9, array6, n16);
                            if (array4[a6].h()) {
                                if (array3[a6].a() == null) {
                                    this.a(a6, array4[a6], array5, array3, n17);
                                }
                                this.pb[n46 + l] = array3[a6].a(n9, n120, n121, n10, n11, true);
                            }
                            else {
                                this.pb[n46 + l] = this.kb[this.ob[n118 >> 3 >> 8] + (n121 >> 3) + n27];
                            }
                            n117 += n64;
                            n118 += n65;
                            ++l;
                            n70 -= cos;
                            n69 += n23;
                            n59 += n40;
                            n60 += n41;
                        }
                        ++n71;
                    }
                }
                b = array[n18].b(b);
                ++n18;
            }
            n46 += n35;
            n38 += n39;
            n42 += n44;
            n43 += n45;
            n36 += n37;
            n47 += n44;
            n48 += n45;
            n49 += n44;
            n50 += n45;
            n51 += n55;
            n52 += n56;
            n53 += n57;
            n54 += n58;
        }
    }
    
    private final void a(final int n, final int n2, final int n3, final double n4, final double n5, final int n6, final int n7, final a[] array, final int[] array2, final int[] array3, final int n8, final int n9, final int n10, final int n11, final int n12, final int n13, final int n14, final int n15, final int n16, final e[] array4, final c[] array5, final d[] array6, final int[] array7, final int n17) {
        int n18 = 0;
        final int n19 = n << 8;
        final int n20 = (n6 << 8) - 256;
        double n21 = Math.cos(n5);
        final double sin = Math.sin(n5);
        final double cos = Math.cos(n4);
        double n22 = Math.sin(n4);
        if (sin == 1.0 || sin == -1.0) {
            n21 = Math.cos(1.5729779883598896);
        }
        if (cos == 1.0 || cos == -1.0) {
            n22 = Math.sin(8.726646259971648E-4);
        }
        final int n23 = this.F << 1;
        final int n24 = n23 + this.F;
        final int n25 = n23 << 1;
        final int n26 = n25 + this.F;
        final int n27 = -n2 / 2;
        final int n28 = n3 / 2;
        int n29 = -n28;
        if (n3 % 2 != 0) {
            --n29;
        }
        final int[] kb = this.kb;
        this.a(n7, n3, n6, n4, n5, n21, sin, cos, n22, array, this.rb, this.sb, this.tb, this.ub);
        final int n30 = this.Eb ? (n2 + 1) : n2;
        double n31 = n7 * sin + n28 * n21;
        final double n32 = -n21;
        double n33 = n7 * n21 - n28 * sin;
        final double n34 = sin;
        final double n35 = -cos;
        final double n36 = n22;
        double n37 = n33 * n22;
        double n38 = n33 * cos;
        final double n39 = sin * n22;
        final double n40 = sin * cos;
        int n41 = 0;
        double n42 = n37 - (n27 + 1) * cos;
        double n43 = n38 + (n27 + 1) * n22;
        double n44 = n37 - n27 * cos;
        double n45 = n38 + n27 * n22;
        double n46 = n19 * n31;
        final double n47 = n19 * n32;
        for (int i = n28; i > n29; --i) {
            double n48 = n44;
            double n49 = n45;
            final double n50 = n19 / n31;
            for (int j = 0; j < 8; ++j) {
                array[j].a((int)array[j].c(i));
            }
            this.a(array, 9);
            for (int k = 0; k < 8; ++k) {
                if (array[k].a > 0) {
                    n18 = k;
                    break;
                }
            }
            final double n51 = (n31 < 0.0) ? (-n31) : n31;
            final double n52 = (n42 < 0.0) ? (-n42) : n42;
            final double n53 = (n43 < 0.0) ? (-n43) : n43;
            int b;
            if (n51 <= n53 && n52 <= n53) {
                if (n43 < 0.0) {
                    b = 1;
                }
                else {
                    b = 3;
                }
            }
            else if (n51 <= n52) {
                if (n42 < 0.0) {
                    b = 2;
                }
                else {
                    b = 4;
                }
            }
            else if (n31 > 0.0) {
                b = 5;
            }
            else {
                b = 6;
            }
            int l = 0;
            while (l < n2) {
                int n54 = 32;
                int n55 = 0;
                final int n56 = array[n18].a - l + 1;
                final int n57 = (n56 >> 5) + (((n56 & 0x1F) > 0) ? 1 : 0);
                if (b == 1) {
                    int n58 = (int)(n19 - n19 * n48 / n49);
                    int n59 = (int)(n19 + n46 / n49);
                    while (n55 < n57) {
                        int n60 = (n54 <= n56) ? 32 : (n56 & 0x1F);
                        n48 += n60 * n35;
                        n49 += n60 * n36;
                        final int n61 = (int)(n19 - n19 * n48 / n49);
                        final int n62 = (int)(n19 + n46 / n49);
                        final double n63 = (n61 - n58) / n60;
                        final double n64 = (n62 - n59) / n60;
                        n54 += n60;
                        while (n60-- > 0) {
                            if (n59 > n20) {
                                n59 = n20;
                            }
                            if (n59 < 0) {
                                n59 = 0;
                            }
                            if (n58 > n20) {
                                n58 = n20;
                            }
                            if (n58 < 0) {
                                n58 = 0;
                            }
                            final int n65 = n59 & 0xFF;
                            final int n66 = 256 - n65;
                            final int n67 = n58 & 0xFF;
                            final int n68 = 256 - n67;
                            int n69 = n58 >> 8;
                            int n70 = n59 >> 8;
                            final int n71 = array2[n70++] / n6;
                            final int n72 = array2[n70] / n6;
                            final int n73 = array3[n69++];
                            final int n74 = array3[n69];
                            final int a = this.a(n71, n73, n8, n9, array7, 0);
                            int a2;
                            if (array5[a].h()) {
                                if (array4[a].a() == null) {
                                    this.a(a, array5[a], array6, array4, n17);
                                }
                                a2 = array4[a].a(n9, n71, n73, n10, n11, true);
                            }
                            else {
                                a2 = kb[this.ob[n59 >> 11] + this.nb[n58 >> 11]];
                            }
                            final int a3 = this.a(n71, n74, n8, n9, array7, 0);
                            int a4;
                            if (array5[a3].h()) {
                                if (array4[a3].a() == null) {
                                    this.a(a3, array5[a3], array6, array4, n17);
                                }
                                a4 = array4[a3].a(n9, n71, n74, n10, n11, true);
                            }
                            else {
                                a4 = kb[this.ob[n59 >> 11] + this.nb[(n58 >> 11) + 1]];
                            }
                            final int a5 = this.a(n72, n73, n8, n9, array7, 0);
                            int a6;
                            if (array5[a5].h()) {
                                if (array4[a5].a() == null) {
                                    this.a(a5, array5[a5], array6, array4, n17);
                                }
                                a6 = array4[a5].a(n9, n72, n73, n10, n11, true);
                            }
                            else {
                                a6 = kb[this.ob[(n59 >> 11) + 1] + this.nb[n58 >> 11]];
                            }
                            final int a7 = this.a(n72, n74, n8, n9, array7, 0);
                            int a8;
                            if (array5[a7].h()) {
                                if (array4[a7].a() == null) {
                                    this.a(a7, array5[a7], array6, array4, n17);
                                }
                                a8 = array4[a7].a(n9, n72, n74, n10, n11, true);
                            }
                            else {
                                a8 = kb[this.ob[(n59 >> 11) + 1] + this.nb[(n58 >> 11) + 1]];
                            }
                            this.pb[n41 + l] = ((((a2 & 0xFF00FF) * n68 + (a4 & 0xFF00FF) * n67 >> 8 & 0xFF00FF) * n66 + ((a6 & 0xFF00FF) * n68 + (a8 & 0xFF00FF) * n67 >> 8 & 0xFF00FF) * n65 >> 8 & 0xFF00FF) | (((a2 & 0xFF00) * n68 + (a4 & 0xFF00) * n67) * n66 + ((a6 & 0xFF00) * n68 + (a8 & 0xFF00) * n67) * n65 >> 16 & 0xFF00) | 0xFF000000);
                            n58 += (int)n63;
                            n59 += (int)n64;
                            ++l;
                        }
                        n58 = n61;
                        n59 = n62;
                        ++n55;
                    }
                }
                else if (b == 3) {
                    int n75 = (int)(n19 - n19 * n48 / n49);
                    int n76 = (int)(n19 - n46 / n49);
                    while (n55 < n57) {
                        int n77 = (n54 <= n56) ? 32 : (n56 & 0x1F);
                        n48 += n77 * n35;
                        n49 += n77 * n36;
                        final int n78 = (int)(n19 - n19 * n48 / n49);
                        final int n79 = (int)(n19 - n46 / n49);
                        final double n80 = (n78 - n75) / n77;
                        final double n81 = (n79 - n76) / n77;
                        n54 += n77;
                        while (n77-- > 0) {
                            if (n76 > n20) {
                                n76 = n20;
                            }
                            if (n76 < 0) {
                                n76 = 0;
                            }
                            if (n75 > n20) {
                                n75 = n20;
                            }
                            if (n75 < 0) {
                                n75 = 0;
                            }
                            final int n82 = n76 & 0xFF;
                            final int n83 = 256 - n82;
                            final int n84 = n75 & 0xFF;
                            final int n85 = 256 - n84;
                            int n86 = n75 >> 8;
                            int n87 = n76 >> 8;
                            final int n88 = array2[n87++] / n6;
                            final int n89 = array2[n87] / n6;
                            final int n90 = array3[n86++];
                            final int n91 = array3[n86];
                            final int a9 = this.a(n88, n90, n8, n9, array7, n13);
                            int a10;
                            if (array5[a9].h()) {
                                if (array4[a9].a() == null) {
                                    this.a(a9, array5[a9], array6, array4, n17);
                                }
                                a10 = array4[a9].a(n9, n88, n90, n10, n11, true);
                            }
                            else {
                                a10 = kb[this.ob[n76 >> 11] + this.nb[n75 >> 11] + n23];
                            }
                            final int a11 = this.a(n88, n91, n8, n9, array7, n13);
                            int a12;
                            if (array5[a11].h()) {
                                if (array4[a11].a() == null) {
                                    this.a(a11, array5[a11], array6, array4, n17);
                                }
                                a12 = array4[a11].a(n9, n88, n91, n10, n11, true);
                            }
                            else {
                                a12 = kb[this.ob[n76 >> 11] + this.nb[(n75 >> 11) + 1] + n23];
                            }
                            final int a13 = this.a(n89, n90, n8, n9, array7, n13);
                            int a14;
                            if (array5[a13].h()) {
                                if (array4[a13].a() == null) {
                                    this.a(a13, array5[a13], array6, array4, n17);
                                }
                                a14 = array4[a13].a(n9, n89, n90, n10, n11, true);
                            }
                            else {
                                a14 = kb[this.ob[(n76 >> 11) + 1] + this.nb[n75 >> 11] + n23];
                            }
                            final int a15 = this.a(n89, n91, n8, n9, array7, n13);
                            int a16;
                            if (array5[a15].h()) {
                                if (array4[a15].a() == null) {
                                    this.a(a15, array5[a15], array6, array4, n17);
                                }
                                a16 = array4[a15].a(n9, n89, n91, n10, n11, true);
                            }
                            else {
                                a16 = kb[this.ob[(n76 >> 11) + 1] + this.nb[(n75 >> 11) + 1] + n23];
                            }
                            this.pb[n41 + l] = ((((a10 & 0xFF00FF) * n85 + (a12 & 0xFF00FF) * n84 >> 8 & 0xFF00FF) * n83 + ((a14 & 0xFF00FF) * n85 + (a16 & 0xFF00FF) * n84 >> 8 & 0xFF00FF) * n82 >> 8 & 0xFF00FF) | (((a10 & 0xFF00) * n85 + (a12 & 0xFF00) * n84) * n83 + ((a14 & 0xFF00) * n85 + (a16 & 0xFF00) * n84) * n82 >> 16 & 0xFF00) | 0xFF000000);
                            n75 += (int)n80;
                            n76 += (int)n81;
                            ++l;
                        }
                        n75 = n78;
                        n76 = n79;
                        ++n55;
                    }
                }
                else if (b == 2) {
                    int n92 = (int)(n19 + n19 * n49 / n48);
                    int n93 = (int)(n19 + n46 / n48);
                    while (n55 < n57) {
                        int n94 = (n54 <= n56) ? 32 : (n56 & 0x1F);
                        n48 += n94 * n35;
                        n49 += n94 * n36;
                        final int n95 = (int)(n19 + n19 * n49 / n48);
                        final int n96 = (int)(n19 + n46 / n48);
                        final double n97 = (n95 - n92) / n94;
                        final double n98 = (n96 - n93) / n94;
                        n54 += n94;
                        while (n94-- > 0) {
                            if (n93 > n20) {
                                n93 = n20;
                            }
                            if (n93 < 0) {
                                n93 = 0;
                            }
                            if (n92 > n20) {
                                n92 = n20;
                            }
                            if (n92 < 0) {
                                n92 = 0;
                            }
                            final int n99 = n93 & 0xFF;
                            final int n100 = 256 - n99;
                            final int n101 = n92 & 0xFF;
                            final int n102 = 256 - n101;
                            int n103 = n92 >> 8;
                            int n104 = n93 >> 8;
                            final int n105 = array2[n104++] / n6;
                            final int n106 = array2[n104] / n6;
                            final int n107 = array3[n103++];
                            final int n108 = array3[n103];
                            final int a17 = this.a(n105, n107, n8, n9, array7, n12);
                            int a18;
                            if (array5[a17].h()) {
                                if (array4[a17].a() == null) {
                                    this.a(a17, array5[a17], array6, array4, n17);
                                }
                                a18 = array4[a17].a(n9, n105, n107, n10, n11, true);
                            }
                            else {
                                a18 = kb[this.ob[n93 >> 11] + this.nb[n92 >> 11] + n24];
                            }
                            final int a19 = this.a(n105, n108, n8, n9, array7, n12);
                            int a20;
                            if (array5[a19].h()) {
                                if (array4[a19].a() == null) {
                                    this.a(a19, array5[a19], array6, array4, n17);
                                }
                                a20 = array4[a19].a(n9, n105, n108, n10, n11, true);
                            }
                            else {
                                a20 = kb[this.ob[n93 >> 11] + this.nb[(n92 >> 11) + 1] + n24];
                            }
                            final int a21 = this.a(n106, n107, n8, n9, array7, n12);
                            int a22;
                            if (array5[a21].h()) {
                                if (array4[a21].a() == null) {
                                    this.a(a21, array5[a21], array6, array4, n17);
                                }
                                a22 = array4[a21].a(n9, n106, n107, n10, n11, true);
                            }
                            else {
                                a22 = kb[this.ob[(n93 >> 11) + 1] + this.nb[n92 >> 11] + n24];
                            }
                            final int a23 = this.a(n106, n108, n8, n9, array7, n12);
                            int a24;
                            if (array5[a23].h()) {
                                if (array4[a23].a() == null) {
                                    this.a(a23, array5[a23], array6, array4, n17);
                                }
                                a24 = array4[a23].a(n9, n106, n108, n10, n11, true);
                            }
                            else {
                                a24 = kb[this.ob[(n93 >> 11) + 1] + this.nb[(n92 >> 11) + 1] + n24];
                            }
                            this.pb[n41 + l] = ((((a18 & 0xFF00FF) * n102 + (a20 & 0xFF00FF) * n101 >> 8 & 0xFF00FF) * n100 + ((a22 & 0xFF00FF) * n102 + (a24 & 0xFF00FF) * n101 >> 8 & 0xFF00FF) * n99 >> 8 & 0xFF00FF) | (((a18 & 0xFF00) * n102 + (a20 & 0xFF00) * n101) * n100 + ((a22 & 0xFF00) * n102 + (a24 & 0xFF00) * n101) * n99 >> 16 & 0xFF00) | 0xFF000000);
                            n92 += (int)n97;
                            n93 += (int)n98;
                            ++l;
                        }
                        n92 = n95;
                        n93 = n96;
                        ++n55;
                    }
                }
                else if (b == 4) {
                    int n109 = (int)(n19 + n19 * n49 / n48);
                    int n110 = (int)(n19 - n46 / n48);
                    while (n55 < n57) {
                        int n111 = (n54 <= n56) ? 32 : (n56 & 0x1F);
                        n48 += n111 * n35;
                        n49 += n111 * n36;
                        final int n112 = (int)(n19 + n19 * n49 / n48);
                        final int n113 = (int)(n19 - n46 / n48);
                        final double n114 = (n112 - n109) / n111;
                        final double n115 = (n113 - n110) / n111;
                        n54 += n111;
                        while (n111-- > 0) {
                            if (n110 > n20) {
                                n110 = n20;
                            }
                            if (n110 < 0) {
                                n110 = 0;
                            }
                            if (n109 > n20) {
                                n109 = n20;
                            }
                            if (n109 < 0) {
                                n109 = 0;
                            }
                            final int n116 = n110 & 0xFF;
                            final int n117 = 256 - n116;
                            final int n118 = n109 & 0xFF;
                            final int n119 = 256 - n118;
                            int n120 = n109 >> 8;
                            int n121 = n110 >> 8;
                            final int n122 = array2[n121++] / n6;
                            final int n123 = array2[n121] / n6;
                            final int n124 = array3[n120++];
                            final int n125 = array3[n120];
                            final int a25 = this.a(n122, n124, n8, n9, array7, n14);
                            int a26;
                            if (array5[a25].h()) {
                                if (array4[a25].a() == null) {
                                    this.a(a25, array5[a25], array6, array4, n17);
                                }
                                a26 = array4[a25].a(n9, n122, n124, n10, n11, true);
                            }
                            else {
                                a26 = kb[this.ob[n110 >> 11] + this.nb[n109 >> 11] + this.F];
                            }
                            final int a27 = this.a(n122, n125, n8, n9, array7, n14);
                            int a28;
                            if (array5[a27].h()) {
                                if (array4[a27].a() == null) {
                                    this.a(a27, array5[a27], array6, array4, n17);
                                }
                                a28 = array4[a27].a(n9, n122, n125, n10, n11, true);
                            }
                            else {
                                a28 = kb[this.ob[(n110 >> 11) + 1] + this.nb[n109 >> 11] + this.F];
                            }
                            final int a29 = this.a(n123, n124, n8, n9, array7, n14);
                            int a30;
                            if (array5[a29].h()) {
                                if (array4[a29].a() == null) {
                                    this.a(a29, array5[a29], array6, array4, n17);
                                }
                                a30 = array4[a29].a(n9, n123, n124, n10, n11, true);
                            }
                            else {
                                a30 = kb[this.ob[n110 >> 11] + this.nb[(n109 >> 11) + 1] + this.F];
                            }
                            final int a31 = this.a(n123, n125, n8, n9, array7, n14);
                            int a32;
                            if (array5[a31].h()) {
                                if (array4[a31].a() == null) {
                                    this.a(a31, array5[a31], array6, array4, n17);
                                }
                                a32 = array4[a31].a(n9, n123, n125, n10, n11, true);
                            }
                            else {
                                a32 = kb[this.ob[(n110 >> 11) + 1] + this.nb[(n109 >> 11) + 1] + this.F];
                            }
                            this.pb[n41 + l] = ((((a26 & 0xFF00FF) * n119 + (a28 & 0xFF00FF) * n118 >> 8 & 0xFF00FF) * n117 + ((a30 & 0xFF00FF) * n119 + (a32 & 0xFF00FF) * n118 >> 8 & 0xFF00FF) * n116 >> 8 & 0xFF00FF) | (((a26 & 0xFF00) * n119 + (a28 & 0xFF00) * n118) * n117 + ((a30 & 0xFF00) * n119 + (a32 & 0xFF00) * n118) * n116 >> 16 & 0xFF00) | 0xFF000000);
                            n109 += (int)n114;
                            n110 += (int)n115;
                            ++l;
                        }
                        n109 = n112;
                        n110 = n113;
                        ++n55;
                    }
                }
                else if (b == 5) {
                    int n126 = (int)(n19 + n50 * n48);
                    int n127 = (int)(n19 - n50 * n49);
                    while (n55 < n57) {
                        int n128 = (n54 <= n56) ? 32 : (n56 & 0x1F);
                        n48 += n128 * n35;
                        n49 += n128 * n36;
                        final int n129 = (int)(n19 + n50 * n48);
                        final int n130 = (int)(n19 - n50 * n49);
                        final double n131 = n35 * n50;
                        final double n132 = -n36 * n50;
                        n54 += n128;
                        while (n128-- > 0) {
                            if (n127 > n20) {
                                n127 = n20;
                            }
                            if (n127 < 0) {
                                n127 = 0;
                            }
                            if (n126 > n20) {
                                n126 = n20;
                            }
                            if (n126 < 0) {
                                n126 = 0;
                            }
                            final int n133 = n127 & 0xFF;
                            final int n134 = 256 - n133;
                            final int n135 = n126 & 0xFF;
                            final int n136 = 256 - n135;
                            int n137 = n126 >> 8;
                            int n138 = n127 >> 8;
                            final int n139 = array2[n138++] / n6;
                            final int n140 = array2[n138] / n6;
                            final int n141 = array3[n137++];
                            final int n142 = array3[n137];
                            final int a33 = this.a(n139, n141, n8, n9, array7, n15);
                            int a34;
                            if (array5[a33].h()) {
                                if (array4[a33].a() == null) {
                                    this.a(a33, array5[a33], array6, array4, n17);
                                }
                                a34 = array4[a33].a(n9, n139, n141, n10, n11, true);
                            }
                            else {
                                a34 = kb[this.ob[n127 >> 11] + this.nb[n126 >> 11] + n25];
                            }
                            final int a35 = this.a(n139, n142, n8, n9, array7, n15);
                            int a36;
                            if (array5[a35].h()) {
                                if (array4[a35].a() == null) {
                                    this.a(a35, array5[a35], array6, array4, n17);
                                }
                                a36 = array4[a35].a(n9, n139, n142, n10, n11, true);
                            }
                            else {
                                a36 = kb[this.ob[n127 >> 11] + this.nb[(n126 >> 11) + 1] + n25];
                            }
                            final int a37 = this.a(n140, n141, n8, n9, array7, n15);
                            int a38;
                            if (array5[a37].h()) {
                                if (array4[a37].a() == null) {
                                    this.a(a37, array5[a37], array6, array4, n17);
                                }
                                a38 = array4[a37].a(n9, n140, n141, n10, n11, true);
                            }
                            else {
                                a38 = kb[this.ob[(n127 >> 11) + 1] + this.nb[n126 >> 11] + n25];
                            }
                            final int a39 = this.a(n140, n142, n8, n9, array7, n15);
                            int a40;
                            if (array5[a39].h()) {
                                if (array4[a39].a() == null) {
                                    this.a(a39, array5[a39], array6, array4, n17);
                                }
                                a40 = array4[a39].a(n9, n140, n142, n10, n11, true);
                            }
                            else {
                                a40 = kb[this.ob[(n127 >> 11) + 1] + this.nb[(n126 >> 11) + 1] + n25];
                            }
                            this.pb[n41 + l] = ((((a34 & 0xFF00FF) * n136 + (a36 & 0xFF00FF) * n135 >> 8 & 0xFF00FF) * n134 + ((a38 & 0xFF00FF) * n136 + (a40 & 0xFF00FF) * n135 >> 8 & 0xFF00FF) * n133 >> 8 & 0xFF00FF) | (((a34 & 0xFF00) * n136 + (a36 & 0xFF00) * n135) * n134 + ((a38 & 0xFF00) * n136 + (a40 & 0xFF00) * n135) * n133 >> 16 & 0xFF00) | 0xFF000000);
                            n126 += (int)n131;
                            n127 += (int)n132;
                            ++l;
                        }
                        n126 = n129;
                        n127 = n130;
                        ++n55;
                    }
                }
                else if (b == 6) {
                    int n143 = (int)(n19 - n50 * n48);
                    int n144 = (int)(n19 - n50 * n49);
                    while (n55 < n57) {
                        int n145 = (n54 <= n56) ? 32 : (n56 & 0x1F);
                        n48 += n145 * n35;
                        n49 += n145 * n36;
                        final int n146 = (int)(n19 - n50 * n48);
                        final int n147 = (int)(n19 - n50 * n49);
                        final double n148 = -n35 * n50;
                        final double n149 = -n36 * n50;
                        n54 += n145;
                        while (n145-- > 0) {
                            if (n144 > n20) {
                                n144 = n20;
                            }
                            if (n144 < 0) {
                                n144 = 0;
                            }
                            if (n143 > n20) {
                                n143 = n20;
                            }
                            if (n143 < 0) {
                                n143 = 0;
                            }
                            final int n150 = n144 & 0xFF;
                            final int n151 = 256 - n150;
                            final int n152 = n143 & 0xFF;
                            final int n153 = 256 - n152;
                            int n154 = n143 >> 8;
                            int n155 = n144 >> 8;
                            final int n156 = array2[n155++] / n6;
                            final int n157 = array2[n155] / n6;
                            final int n158 = array3[n154++];
                            final int n159 = array3[n154];
                            final int a41 = this.a(n156, n158, n8, n9, array7, n16);
                            int a42;
                            if (array5[a41].h()) {
                                if (array4[a41].a() == null) {
                                    this.a(a41, array5[a41], array6, array4, n17);
                                }
                                a42 = array4[a41].a(n9, n156, n158, n10, n11, true);
                            }
                            else {
                                a42 = kb[this.ob[n144 >> 11] + this.nb[n143 >> 11] + n26];
                            }
                            final int a43 = this.a(n156, n159, n8, n9, array7, n16);
                            int a44;
                            if (array5[a43].h()) {
                                if (array4[a43].a() == null) {
                                    this.a(a43, array5[a43], array6, array4, n17);
                                }
                                a44 = array4[a43].a(n9, n156, n159, n10, n11, true);
                            }
                            else {
                                a44 = kb[this.ob[n144 >> 11] + this.nb[(n143 >> 11) + 1] + n26];
                            }
                            final int a45 = this.a(n157, n158, n8, n9, array7, n16);
                            int a46;
                            if (array5[a45].h()) {
                                if (array4[a45].a() == null) {
                                    this.a(a45, array5[a45], array6, array4, n17);
                                }
                                a46 = array4[a45].a(n9, n157, n158, n10, n11, true);
                            }
                            else {
                                a46 = kb[this.ob[(n144 >> 11) + 1] + this.nb[n143 >> 11] + n26];
                            }
                            final int a47 = this.a(n157, n159, n8, n9, array7, n16);
                            int a48;
                            if (array5[a47].h()) {
                                if (array4[a47].a() == null) {
                                    this.a(a47, array5[a47], array6, array4, n17);
                                }
                                a48 = array4[a47].a(n9, n157, n159, n10, n11, true);
                            }
                            else {
                                a48 = kb[this.ob[(n144 >> 11) + 1] + this.nb[(n143 >> 11) + 1] + n26];
                            }
                            this.pb[n41 + l] = ((((a42 & 0xFF00FF) * n153 + (a44 & 0xFF00FF) * n152 >> 8 & 0xFF00FF) * n151 + ((a46 & 0xFF00FF) * n153 + (a48 & 0xFF00FF) * n152 >> 8 & 0xFF00FF) * n150 >> 8 & 0xFF00FF) | (((a42 & 0xFF00) * n153 + (a44 & 0xFF00) * n152) * n151 + ((a46 & 0xFF00) * n153 + (a48 & 0xFF00) * n152) * n150 >> 16 & 0xFF00) | 0xFF000000);
                            n143 += (int)n148;
                            n144 += (int)n149;
                            ++l;
                        }
                        n143 = n146;
                        n144 = n147;
                        ++n55;
                    }
                }
                b = array[n18].b(b);
                ++n18;
            }
            n41 += n30;
            n33 += n34;
            n37 += n39;
            n38 += n40;
            n31 += n32;
            n42 += n39;
            n43 += n40;
            n44 += n39;
            n45 += n40;
            n46 += n47;
        }
    }
    
    private final void b(final int n, final int n2, final int n3, final double n4, final double n5, final int n6, final int n7, final a[] array, final int[] array2, final int[] array3, final int n8, final int n9, final int n10, final int n11, final int n12, final int n13, final int n14, final int n15, final int n16, final e[] array4, final c[] array5, final d[] array6, final int[] array7, final int n17) {
        int n18 = 0;
        final int n19 = n << 8;
        final int n20 = (n6 << 8) - 256;
        double n21 = Math.cos(n5);
        final double sin = Math.sin(n5);
        final double cos = Math.cos(n4);
        double n22 = Math.sin(n4);
        if (sin == 1.0 || sin == -1.0) {
            n21 = Math.cos(1.5729779883598896);
        }
        if (cos == 1.0 || cos == -1.0) {
            n22 = Math.sin(8.726646259971648E-4);
        }
        final int n23 = -n2 / 2;
        final int n24 = n3 / 2;
        int n25 = -n24;
        if (n3 % 2 != 0) {
            --n25;
        }
        this.a(n7, n3, n6, n4, n5, n21, sin, cos, n22, array, this.rb, this.sb, this.tb, this.ub);
        final int n26 = this.Eb ? (n2 + 1) : n2;
        double n27 = n7 * sin + n24 * n21;
        final double n28 = -n21;
        double n29 = n7 * n21 - n24 * sin;
        final double n30 = sin;
        final double n31 = -cos;
        final double n32 = n22;
        double n33 = n29 * n22;
        double n34 = n29 * cos;
        final double n35 = sin * n22;
        final double n36 = sin * cos;
        int n37 = 0;
        double n38 = n33 - (n23 + 1) * cos;
        double n39 = n34 + (n23 + 1) * n22;
        double n40 = n33 - n23 * cos;
        double n41 = n34 + n23 * n22;
        double n42 = n19 * n27;
        final double n43 = n19 * n28;
        for (int i = n24; i > n25; --i) {
            double n44 = n40;
            double n45 = n41;
            final double n46 = n19 / n27;
            for (int j = 0; j < 8; ++j) {
                array[j].a((int)array[j].c(i));
            }
            this.a(array, 9);
            for (int k = 0; k < 8; ++k) {
                if (array[k].a > 0) {
                    n18 = k;
                    break;
                }
            }
            final double n47 = (n27 < 0.0) ? (-n27) : n27;
            final double n48 = (n38 < 0.0) ? (-n38) : n38;
            final double n49 = (n39 < 0.0) ? (-n39) : n39;
            int b;
            if (n47 <= n49 && n48 <= n49) {
                if (n39 < 0.0) {
                    b = 1;
                }
                else {
                    b = 3;
                }
            }
            else if (n47 <= n48) {
                if (n38 < 0.0) {
                    b = 2;
                }
                else {
                    b = 4;
                }
            }
            else if (n27 > 0.0) {
                b = 5;
            }
            else {
                b = 6;
            }
            int l = 0;
            while (l < n2) {
                int n50 = 32;
                int n51 = 0;
                final int n52 = array[n18].a - l + 1;
                final int n53 = (n52 >> 5) + (((n52 & 0x1F) > 0) ? 1 : 0);
                if (b == 1) {
                    int n54 = (int)(n19 - n19 * n44 / n45);
                    int n55 = (int)(n19 + n42 / n45);
                    while (n51 < n53) {
                        int n56 = (n50 <= n52) ? 32 : (n52 & 0x1F);
                        n44 += n56 * n31;
                        n45 += n56 * n32;
                        final int n57 = (int)(n19 - n19 * n44 / n45);
                        final int n58 = (int)(n19 + n42 / n45);
                        final double n59 = (n57 - n54) / n56;
                        final double n60 = (n58 - n55) / n56;
                        n50 += n56;
                        while (n56-- > 0) {
                            if (n55 > n20) {
                                n55 = n20;
                            }
                            if (n55 < 0) {
                                n55 = 0;
                            }
                            if (n54 > n20) {
                                n54 = n20;
                            }
                            if (n54 < 0) {
                                n54 = 0;
                            }
                            final int n61 = n55 & 0xFF;
                            final int n62 = 256 - n61;
                            final int n63 = n54 & 0xFF;
                            final int n64 = 256 - n63;
                            int n65 = n54 >> 8;
                            int n66 = n55 >> 8;
                            final int n67 = array2[n66++] / n6;
                            final int n68 = array2[n66] / n6;
                            final int n69 = array3[n65++];
                            final int n70 = array3[n65];
                            final int a = this.a(n67, n69, n8, n9, array7, 0);
                            if (array4[a].a() == null) {
                                this.a(a, array5[a], array6, array4, n17);
                            }
                            final int a2 = array4[a].a(n9, n67, n69, n10, n11, true);
                            final int a3 = this.a(n67, n70, n8, n9, array7, 0);
                            if (array4[a3].a() == null) {
                                this.a(a3, array5[a3], array6, array4, n17);
                            }
                            final int a4 = array4[a3].a(n9, n67, n70, n10, n11, true);
                            final int a5 = this.a(n68, n69, n8, n9, array7, 0);
                            if (array4[a5].a() == null) {
                                this.a(a5, array5[a5], array6, array4, n17);
                            }
                            final int a6 = array4[a5].a(n9, n68, n69, n10, n11, true);
                            final int a7 = this.a(n68, n70, n8, n9, array7, 0);
                            if (array4[a7].a() == null) {
                                this.a(a7, array5[a7], array6, array4, n17);
                            }
                            final int a8 = array4[a7].a(n9, n68, n70, n10, n11, true);
                            this.pb[n37 + l] = ((((a2 & 0xFF00FF) * n64 + (a4 & 0xFF00FF) * n63 >> 8 & 0xFF00FF) * n62 + ((a6 & 0xFF00FF) * n64 + (a8 & 0xFF00FF) * n63 >> 8 & 0xFF00FF) * n61 >> 8 & 0xFF00FF) | (((a2 & 0xFF00) * n64 + (a4 & 0xFF00) * n63) * n62 + ((a6 & 0xFF00) * n64 + (a8 & 0xFF00) * n63) * n61 >> 16 & 0xFF00) | 0xFF000000);
                            n54 += (int)n59;
                            n55 += (int)n60;
                            ++l;
                        }
                        n54 = n57;
                        n55 = n58;
                        ++n51;
                    }
                }
                else if (b == 3) {
                    int n71 = (int)(n19 - n19 * n44 / n45);
                    int n72 = (int)(n19 - n42 / n45);
                    while (n51 < n53) {
                        int n73 = (n50 <= n52) ? 32 : (n52 & 0x1F);
                        n44 += n73 * n31;
                        n45 += n73 * n32;
                        final int n74 = (int)(n19 - n19 * n44 / n45);
                        final int n75 = (int)(n19 - n42 / n45);
                        final double n76 = (n74 - n71) / n73;
                        final double n77 = (n75 - n72) / n73;
                        n50 += n73;
                        while (n73-- > 0) {
                            if (n72 > n20) {
                                n72 = n20;
                            }
                            if (n72 < 0) {
                                n72 = 0;
                            }
                            if (n71 > n20) {
                                n71 = n20;
                            }
                            if (n71 < 0) {
                                n71 = 0;
                            }
                            final int n78 = n72 & 0xFF;
                            final int n79 = 256 - n78;
                            final int n80 = n71 & 0xFF;
                            final int n81 = 256 - n80;
                            int n82 = n71 >> 8;
                            int n83 = n72 >> 8;
                            final int n84 = array2[n83++] / n6;
                            final int n85 = array2[n83] / n6;
                            final int n86 = array3[n82++];
                            final int n87 = array3[n82];
                            final int a9 = this.a(n84, n86, n8, n9, array7, n13);
                            if (array4[a9].a() == null) {
                                this.a(a9, array5[a9], array6, array4, n17);
                            }
                            final int a10 = array4[a9].a(n9, n84, n86, n10, n11, true);
                            final int a11 = this.a(n84, n87, n8, n9, array7, n13);
                            if (array4[a11].a() == null) {
                                this.a(a11, array5[a11], array6, array4, n17);
                            }
                            final int a12 = array4[a11].a(n9, n84, n87, n10, n11, true);
                            final int a13 = this.a(n85, n86, n8, n9, array7, n13);
                            if (array4[a13].a() == null) {
                                this.a(a13, array5[a13], array6, array4, n17);
                            }
                            final int a14 = array4[a13].a(n9, n85, n86, n10, n11, true);
                            final int a15 = this.a(n85, n87, n8, n9, array7, n13);
                            if (array4[a15].a() == null) {
                                this.a(a15, array5[a15], array6, array4, n17);
                            }
                            final int a16 = array4[a15].a(n9, n85, n87, n10, n11, true);
                            this.pb[n37 + l] = ((((a10 & 0xFF00FF) * n81 + (a12 & 0xFF00FF) * n80 >> 8 & 0xFF00FF) * n79 + ((a14 & 0xFF00FF) * n81 + (a16 & 0xFF00FF) * n80 >> 8 & 0xFF00FF) * n78 >> 8 & 0xFF00FF) | (((a10 & 0xFF00) * n81 + (a12 & 0xFF00) * n80) * n79 + ((a14 & 0xFF00) * n81 + (a16 & 0xFF00) * n80) * n78 >> 16 & 0xFF00) | 0xFF000000);
                            n71 += (int)n76;
                            n72 += (int)n77;
                            ++l;
                        }
                        n71 = n74;
                        n72 = n75;
                        ++n51;
                    }
                }
                else if (b == 2) {
                    int n88 = (int)(n19 + n19 * n45 / n44);
                    int n89 = (int)(n19 + n42 / n44);
                    while (n51 < n53) {
                        int n90 = (n50 <= n52) ? 32 : (n52 & 0x1F);
                        n44 += n90 * n31;
                        n45 += n90 * n32;
                        final int n91 = (int)(n19 + n19 * n45 / n44);
                        final int n92 = (int)(n19 + n42 / n44);
                        final double n93 = (n91 - n88) / n90;
                        final double n94 = (n92 - n89) / n90;
                        n50 += n90;
                        while (n90-- > 0) {
                            if (n89 > n20) {
                                n89 = n20;
                            }
                            if (n89 < 0) {
                                n89 = 0;
                            }
                            if (n88 > n20) {
                                n88 = n20;
                            }
                            if (n88 < 0) {
                                n88 = 0;
                            }
                            final int n95 = n89 & 0xFF;
                            final int n96 = 256 - n95;
                            final int n97 = n88 & 0xFF;
                            final int n98 = 256 - n97;
                            int n99 = n88 >> 8;
                            int n100 = n89 >> 8;
                            final int n101 = array2[n100++] / n6;
                            final int n102 = array2[n100] / n6;
                            final int n103 = array3[n99++];
                            final int n104 = array3[n99];
                            final int a17 = this.a(n101, n103, n8, n9, array7, n12);
                            if (array4[a17].a() == null) {
                                this.a(a17, array5[a17], array6, array4, n17);
                            }
                            final int a18 = array4[a17].a(n9, n101, n103, n10, n11, true);
                            final int a19 = this.a(n101, n104, n8, n9, array7, n12);
                            if (array4[a19].a() == null) {
                                this.a(a19, array5[a19], array6, array4, n17);
                            }
                            final int a20 = array4[a19].a(n9, n101, n104, n10, n11, true);
                            final int a21 = this.a(n102, n103, n8, n9, array7, n12);
                            if (array4[a21].a() == null) {
                                this.a(a21, array5[a21], array6, array4, n17);
                            }
                            final int a22 = array4[a21].a(n9, n102, n103, n10, n11, true);
                            final int a23 = this.a(n102, n104, n8, n9, array7, n12);
                            if (array4[a23].a() == null) {
                                this.a(a23, array5[a23], array6, array4, n17);
                            }
                            final int a24 = array4[a23].a(n9, n102, n104, n10, n11, true);
                            this.pb[n37 + l] = ((((a18 & 0xFF00FF) * n98 + (a20 & 0xFF00FF) * n97 >> 8 & 0xFF00FF) * n96 + ((a22 & 0xFF00FF) * n98 + (a24 & 0xFF00FF) * n97 >> 8 & 0xFF00FF) * n95 >> 8 & 0xFF00FF) | (((a18 & 0xFF00) * n98 + (a20 & 0xFF00) * n97) * n96 + ((a22 & 0xFF00) * n98 + (a24 & 0xFF00) * n97) * n95 >> 16 & 0xFF00) | 0xFF000000);
                            n88 += (int)n93;
                            n89 += (int)n94;
                            ++l;
                        }
                        n88 = n91;
                        n89 = n92;
                        ++n51;
                    }
                }
                else if (b == 4) {
                    int n105 = (int)(n19 + n19 * n45 / n44);
                    int n106 = (int)(n19 - n42 / n44);
                    while (n51 < n53) {
                        int n107 = (n50 <= n52) ? 32 : (n52 & 0x1F);
                        n44 += n107 * n31;
                        n45 += n107 * n32;
                        final int n108 = (int)(n19 + n19 * n45 / n44);
                        final int n109 = (int)(n19 - n42 / n44);
                        final double n110 = (n108 - n105) / n107;
                        final double n111 = (n109 - n106) / n107;
                        n50 += n107;
                        while (n107-- > 0) {
                            if (n106 > n20) {
                                n106 = n20;
                            }
                            if (n106 < 0) {
                                n106 = 0;
                            }
                            if (n105 > n20) {
                                n105 = n20;
                            }
                            if (n105 < 0) {
                                n105 = 0;
                            }
                            final int n112 = n106 & 0xFF;
                            final int n113 = 256 - n112;
                            final int n114 = n105 & 0xFF;
                            final int n115 = 256 - n114;
                            int n116 = n105 >> 8;
                            int n117 = n106 >> 8;
                            final int n118 = array2[n117++] / n6;
                            final int n119 = array2[n117] / n6;
                            final int n120 = array3[n116++];
                            final int n121 = array3[n116];
                            final int a25 = this.a(n118, n120, n8, n9, array7, n14);
                            if (array4[a25].a() == null) {
                                this.a(a25, array5[a25], array6, array4, n17);
                            }
                            final int a26 = array4[a25].a(n9, n118, n120, n10, n11, true);
                            final int a27 = this.a(n118, n121, n8, n9, array7, n14);
                            if (array4[a27].a() == null) {
                                this.a(a27, array5[a27], array6, array4, n17);
                            }
                            final int a28 = array4[a27].a(n9, n118, n121, n10, n11, true);
                            final int a29 = this.a(n119, n120, n8, n9, array7, n14);
                            if (array4[a29].a() == null) {
                                this.a(a29, array5[a29], array6, array4, n17);
                            }
                            final int a30 = array4[a29].a(n9, n119, n120, n10, n11, true);
                            final int a31 = this.a(n119, n121, n8, n9, array7, n14);
                            if (array4[a31].a() == null) {
                                this.a(a31, array5[a31], array6, array4, n17);
                            }
                            final int a32 = array4[a31].a(n9, n119, n121, n10, n11, true);
                            this.pb[n37 + l] = ((((a26 & 0xFF00FF) * n115 + (a28 & 0xFF00FF) * n114 >> 8 & 0xFF00FF) * n113 + ((a30 & 0xFF00FF) * n115 + (a32 & 0xFF00FF) * n114 >> 8 & 0xFF00FF) * n112 >> 8 & 0xFF00FF) | (((a26 & 0xFF00) * n115 + (a28 & 0xFF00) * n114) * n113 + ((a30 & 0xFF00) * n115 + (a32 & 0xFF00) * n114) * n112 >> 16 & 0xFF00) | 0xFF000000);
                            n105 += (int)n110;
                            n106 += (int)n111;
                            ++l;
                        }
                        n105 = n108;
                        n106 = n109;
                        ++n51;
                    }
                }
                else if (b == 5) {
                    int n122 = (int)(n19 + n46 * n44);
                    int n123 = (int)(n19 - n46 * n45);
                    while (n51 < n53) {
                        int n124 = (n50 <= n52) ? 32 : (n52 & 0x1F);
                        n44 += n124 * n31;
                        n45 += n124 * n32;
                        final int n125 = (int)(n19 + n46 * n44);
                        final int n126 = (int)(n19 - n46 * n45);
                        final double n127 = n31 * n46;
                        final double n128 = -n32 * n46;
                        n50 += n124;
                        while (n124-- > 0) {
                            if (n123 > n20) {
                                n123 = n20;
                            }
                            if (n123 < 0) {
                                n123 = 0;
                            }
                            if (n122 > n20) {
                                n122 = n20;
                            }
                            if (n122 < 0) {
                                n122 = 0;
                            }
                            final int n129 = n123 & 0xFF;
                            final int n130 = 256 - n129;
                            final int n131 = n122 & 0xFF;
                            final int n132 = 256 - n131;
                            int n133 = n122 >> 8;
                            int n134 = n123 >> 8;
                            final int n135 = array2[n134++] / n6;
                            final int n136 = array2[n134] / n6;
                            final int n137 = array3[n133++];
                            final int n138 = array3[n133];
                            final int a33 = this.a(n135, n137, n8, n9, array7, n15);
                            if (array4[a33].a() == null) {
                                this.a(a33, array5[a33], array6, array4, n17);
                            }
                            final int a34 = array4[a33].a(n9, n135, n137, n10, n11, true);
                            final int a35 = this.a(n135, n138, n8, n9, array7, n15);
                            if (array4[a35].a() == null) {
                                this.a(a35, array5[a35], array6, array4, n17);
                            }
                            final int a36 = array4[a35].a(n9, n135, n138, n10, n11, true);
                            final int a37 = this.a(n136, n137, n8, n9, array7, n15);
                            if (array4[a37].a() == null) {
                                this.a(a37, array5[a37], array6, array4, n17);
                            }
                            final int a38 = array4[a37].a(n9, n136, n137, n10, n11, true);
                            final int a39 = this.a(n136, n138, n8, n9, array7, n15);
                            if (array4[a39].a() == null) {
                                this.a(a39, array5[a39], array6, array4, n17);
                            }
                            final int a40 = array4[a39].a(n9, n136, n138, n10, n11, true);
                            this.pb[n37 + l] = ((((a34 & 0xFF00FF) * n132 + (a36 & 0xFF00FF) * n131 >> 8 & 0xFF00FF) * n130 + ((a38 & 0xFF00FF) * n132 + (a40 & 0xFF00FF) * n131 >> 8 & 0xFF00FF) * n129 >> 8 & 0xFF00FF) | (((a34 & 0xFF00) * n132 + (a36 & 0xFF00) * n131) * n130 + ((a38 & 0xFF00) * n132 + (a40 & 0xFF00) * n131) * n129 >> 16 & 0xFF00) | 0xFF000000);
                            n122 += (int)n127;
                            n123 += (int)n128;
                            ++l;
                        }
                        n122 = n125;
                        n123 = n126;
                        ++n51;
                    }
                }
                else if (b == 6) {
                    int n139 = (int)(n19 - n46 * n44);
                    int n140 = (int)(n19 - n46 * n45);
                    while (n51 < n53) {
                        int n141 = (n50 <= n52) ? 32 : (n52 & 0x1F);
                        n44 += n141 * n31;
                        n45 += n141 * n32;
                        final int n142 = (int)(n19 - n46 * n44);
                        final int n143 = (int)(n19 - n46 * n45);
                        final double n144 = -n31 * n46;
                        final double n145 = -n32 * n46;
                        n50 += n141;
                        while (n141-- > 0) {
                            if (n140 > n20) {
                                n140 = n20;
                            }
                            if (n140 < 0) {
                                n140 = 0;
                            }
                            if (n139 > n20) {
                                n139 = n20;
                            }
                            if (n139 < 0) {
                                n139 = 0;
                            }
                            final int n146 = n140 & 0xFF;
                            final int n147 = 256 - n146;
                            final int n148 = n139 & 0xFF;
                            final int n149 = 256 - n148;
                            int n150 = n139 >> 8;
                            int n151 = n140 >> 8;
                            final int n152 = array2[n151++] / n6;
                            final int n153 = array2[n151] / n6;
                            final int n154 = array3[n150++];
                            final int n155 = array3[n150];
                            final int a41 = this.a(n152, n154, n8, n9, array7, n16);
                            if (array4[a41].a() == null) {
                                this.a(a41, array5[a41], array6, array4, n17);
                            }
                            final int a42 = array4[a41].a(n9, n152, n154, n10, n11, true);
                            final int a43 = this.a(n152, n155, n8, n9, array7, n16);
                            if (array4[a43].a() == null) {
                                this.a(a43, array5[a43], array6, array4, n17);
                            }
                            final int a44 = array4[a43].a(n9, n152, n155, n10, n11, true);
                            final int a45 = this.a(n153, n154, n8, n9, array7, n16);
                            if (array4[a45].a() == null) {
                                this.a(a45, array5[a45], array6, array4, n17);
                            }
                            final int a46 = array4[a45].a(n9, n153, n154, n10, n11, true);
                            final int a47 = this.a(n153, n155, n8, n9, array7, n16);
                            if (array4[a47].a() == null) {
                                this.a(a47, array5[a47], array6, array4, n17);
                            }
                            final int a48 = array4[a47].a(n9, n153, n155, n10, n11, true);
                            this.pb[n37 + l] = ((((a42 & 0xFF00FF) * n149 + (a44 & 0xFF00FF) * n148 >> 8 & 0xFF00FF) * n147 + ((a46 & 0xFF00FF) * n149 + (a48 & 0xFF00FF) * n148 >> 8 & 0xFF00FF) * n146 >> 8 & 0xFF00FF) | (((a42 & 0xFF00) * n149 + (a44 & 0xFF00) * n148) * n147 + ((a46 & 0xFF00) * n149 + (a48 & 0xFF00) * n148) * n146 >> 16 & 0xFF00) | 0xFF000000);
                            n139 += (int)n144;
                            n140 += (int)n145;
                            ++l;
                        }
                        n139 = n142;
                        n140 = n143;
                        ++n51;
                    }
                }
                b = array[n18].b(b);
                ++n18;
            }
            n37 += n26;
            n29 += n30;
            n33 += n35;
            n34 += n36;
            n27 += n28;
            n38 += n35;
            n39 += n36;
            n40 += n35;
            n41 += n36;
            n42 += n43;
        }
    }
    
    private final void a(final int n, final int n2, final int n3, final double n4, final double n5, final int n6, final int n7, final double n8, final a[] array) {
        int n9 = 0;
        final int n10 = n << 8;
        final int n11 = n6 << 8;
        final int n12 = n11 - 256;
        double n13 = Math.cos(n5);
        final double sin = Math.sin(n5);
        final double cos = Math.cos(n4);
        double n14 = Math.sin(n4);
        if (sin == 1.0 || sin == -1.0) {
            n13 = Math.cos(1.5729779883598896);
        }
        if (cos == 1.0 || cos == -1.0) {
            n14 = Math.sin(8.726646259971648E-4);
        }
        final int n15 = n7 << 1;
        final int n16 = n15 + n7;
        final int n17 = n15 << 1;
        final int n18 = n17 + n7;
        final int n19 = -n2 / 2;
        final int n20 = n3 / 2;
        int n21 = -n20;
        if (n3 % 2 != 0) {
            --n21;
        }
        final int[] mb = this.mb;
        final int[] kb = this.kb;
        final double n22 = n10 * n14;
        final double n23 = n10 * cos;
        this.a(n8, n3, n6, n4, n5, n13, sin, cos, n14, array, this.rb, this.sb, this.tb, this.ub);
        final double n24 = -n23;
        final double n25 = -n22;
        final int n26 = this.Eb ? (n2 + 1) : n2;
        double n27 = n8 * sin + n20 * n13;
        final double n28 = -n13;
        double n29 = n8 * n13 - n20 * sin;
        final double n30 = sin;
        final double n31 = -cos;
        final double n32 = n14;
        double n33 = n29 * n14;
        double n34 = n29 * cos;
        final double n35 = sin * n14;
        final double n36 = sin * cos;
        int n37 = 0;
        double n38 = n33 - (n19 + 1) * cos;
        double n39 = n34 + (n19 + 1) * n14;
        double n40 = n33 - n19 * cos;
        double n41 = n34 + n19 * n14;
        double n42 = n22 * n27;
        double n43 = n10 * n29;
        double n44 = n23 * n27;
        double n45 = n10 * n27;
        final double n46 = n22 * n28;
        final double n47 = n10 * n30;
        final double n48 = n23 * n28;
        final double n49 = n10 * n28;
        for (int i = n20; i > n21; --i) {
            double n50 = n40;
            double n51 = n41;
            final double n52 = n10 / n27;
            for (int j = 0; j < 8; ++j) {
                array[j].a((int)array[j].c(i));
            }
            this.a(array, 9);
            for (int k = 0; k < 8; ++k) {
                if (array[k].a > 0) {
                    n9 = k;
                    break;
                }
            }
            final int n53 = (int)(n24 / n27);
            final int n54 = (int)(n25 / n27);
            final int n55 = -n53;
            final int n56 = n54;
            final double n57 = (n27 < 0.0) ? (-n27) : n27;
            final double n58 = (n38 < 0.0) ? (-n38) : n38;
            final double n59 = (n39 < 0.0) ? (-n39) : n39;
            int b;
            if (n57 <= n59 && n58 <= n59) {
                if (n39 < 0.0) {
                    b = 1;
                }
                else {
                    b = 3;
                }
            }
            else if (n57 <= n58) {
                if (n38 < 0.0) {
                    b = 2;
                }
                else {
                    b = 4;
                }
            }
            else if (n27 > 0.0) {
                b = 5;
            }
            else {
                b = 6;
            }
            double n60 = n34 + n19 * n14;
            double n61 = n33 - n19 * cos;
            int l = 0;
            while (l < n2) {
                int n62 = 0;
                final int n63 = array[n9].a - l + 1;
                final int n64 = (n63 >> 4) + (((n63 & 0xF) > 0) ? 1 : 0);
                if (b == 1) {
                    int n65 = 16;
                    while (n62 < n64) {
                        final double n66 = n60 * n60;
                        int n67 = (int)(n10 - n10 * n50 / n51);
                        int n68 = (int)(n10 + n45 / n51);
                        final int n69 = (int)(n43 / n66);
                        final int n70 = (int)(-n42 / n66);
                        int n71 = (n65 <= n63) ? 16 : (n63 & 0xF);
                        n65 += n71;
                        while (n71-- > 0) {
                            if (n68 > n11) {
                                n68 = n11;
                            }
                            if (n68 < 0) {
                                n68 = 0;
                            }
                            if (n67 >= n11) {
                                n67 = n12;
                            }
                            if (n67 < 0) {
                                n67 = 0;
                            }
                            this.pb[n37 + l] = kb[mb[n68 >> 8] + (n67 >> 8)];
                            n67 += n69;
                            n68 += n70;
                            ++l;
                            n61 -= cos;
                            n60 += n14;
                            n50 += n31;
                            n51 += n32;
                        }
                        ++n62;
                    }
                }
                else if (b == 3) {
                    int n72 = 16;
                    while (n62 < n64) {
                        final double n73 = n60 * n60;
                        int n74 = (int)(n10 - n10 * n50 / n51);
                        int n75 = (int)(n10 - n45 / n51);
                        final int n76 = (int)(n43 / n73);
                        final int n77 = (int)(n42 / n73);
                        int n78 = (n72 <= n63) ? 16 : (n63 & 0xF);
                        n72 += n78;
                        while (n78-- > 0) {
                            if (n75 > n11) {
                                n75 = n11;
                            }
                            if (n75 < 0) {
                                n75 = 0;
                            }
                            if (n74 >= n11) {
                                n74 = n12;
                            }
                            if (n74 < 0) {
                                n74 = 0;
                            }
                            this.pb[n37 + l] = kb[mb[n75 >> 8] + (n74 >> 8) + n15];
                            n74 += n76;
                            n75 += n77;
                            ++l;
                            n61 -= cos;
                            n60 += n14;
                            n50 += n31;
                            n51 += n32;
                        }
                        ++n62;
                    }
                }
                else if (b == 2) {
                    int n79 = 16;
                    while (n62 < n64) {
                        final double n80 = n61 * n61;
                        int n81 = (int)(n10 + n10 * n51 / n50);
                        int n82 = (int)(n10 + n45 / n50);
                        final int n83 = (int)(n43 / n80);
                        final int n84 = (int)(n44 / n80);
                        int n85 = (n79 <= n63) ? 16 : (n63 & 0xF);
                        n79 += n85;
                        while (n85-- > 0) {
                            if (n82 > n11) {
                                n82 = n11;
                            }
                            if (n82 < 0) {
                                n82 = 0;
                            }
                            if (n81 >= n11) {
                                n81 = n12;
                            }
                            if (n81 < 0) {
                                n81 = 0;
                            }
                            this.pb[n37 + l] = kb[mb[n82 >> 8] + (n81 >> 8) + n16];
                            n81 += n83;
                            n82 += n84;
                            ++l;
                            n61 -= cos;
                            n60 += n14;
                            n50 += n31;
                            n51 += n32;
                        }
                        ++n62;
                    }
                }
                else if (b == 4) {
                    int n86 = 16;
                    while (n62 < n64) {
                        final double n87 = n61 * n61;
                        int n88 = (int)(n10 + n10 * n51 / n50);
                        int n89 = (int)(n10 - n45 / n50);
                        final int n90 = (int)(n43 / n87);
                        final int n91 = (int)(-n44 / n87);
                        int n92 = (n86 <= n63) ? 16 : (n63 & 0xF);
                        n86 += n92;
                        while (n92-- > 0) {
                            if (n89 > n11) {
                                n89 = n11;
                            }
                            if (n89 < 0) {
                                n89 = 0;
                            }
                            if (n88 >= n11) {
                                n88 = n12;
                            }
                            if (n88 < 0) {
                                n88 = 0;
                            }
                            this.pb[n37 + l] = kb[mb[n89 >> 8] + (n88 >> 8) + n7];
                            n88 += n90;
                            n89 += n91;
                            ++l;
                            n61 -= cos;
                            n60 += n14;
                            n50 += n31;
                            n51 += n32;
                        }
                        ++n62;
                    }
                }
                else if (b == 5) {
                    int n93 = 16;
                    while (n62 < n64) {
                        int n94 = (int)(n10 + n52 * n50);
                        int n95 = (int)(n10 - n52 * n51);
                        int n96 = (n93 <= n63) ? 16 : (n63 & 0xF);
                        n93 += n96;
                        while (n96-- > 0) {
                            if (n95 > n11) {
                                n95 = n11;
                            }
                            if (n95 < 0) {
                                n95 = 0;
                            }
                            if (n94 >= n11) {
                                n94 = n12;
                            }
                            if (n94 < 0) {
                                n94 = 0;
                            }
                            this.pb[n37 + l] = kb[mb[n95 >> 8] + (n94 >> 8) + n17];
                            n94 += n53;
                            n95 += n54;
                            ++l;
                            n61 -= cos;
                            n60 += n14;
                            n50 += n31;
                            n51 += n32;
                        }
                        ++n62;
                    }
                }
                else if (b == 6) {
                    int n97 = 16;
                    while (n62 < n64) {
                        int n98 = (int)(n10 - n52 * n50);
                        int n99 = (int)(n10 - n52 * n51);
                        int n100 = (n97 <= n63) ? 16 : (n63 & 0xF);
                        n97 += n100;
                        while (n100-- > 0) {
                            if (n99 > n11) {
                                n99 = n11;
                            }
                            if (n99 < 0) {
                                n99 = 0;
                            }
                            if (n98 >= n11) {
                                n98 = n12;
                            }
                            if (n98 < 0) {
                                n98 = 0;
                            }
                            this.pb[n37 + l] = kb[mb[n99 >> 8] + (n98 >> 8) + n18];
                            n98 += n55;
                            n99 += n56;
                            ++l;
                            n61 -= cos;
                            n60 += n14;
                            n50 += n31;
                            n51 += n32;
                        }
                        ++n62;
                    }
                }
                b = array[n9].b(b);
                ++n9;
            }
            n37 += n26;
            n29 += n30;
            n33 += n35;
            n34 += n36;
            n27 += n28;
            n38 += n35;
            n39 += n36;
            n40 += n35;
            n41 += n36;
            n42 += n46;
            n43 += n47;
            n44 += n48;
            n45 += n49;
        }
    }
    
    private final void b(final int n, final int n2, final int n3, final double n4, final double n5, final int n6, final int n7, final double n8, final a[] array) {
        int n9 = 0;
        final int n10 = n << 8;
        final int n11 = n6 - 1;
        double n12 = Math.cos(n5);
        final double sin = Math.sin(n5);
        final double cos = Math.cos(n4);
        double n13 = Math.sin(n4);
        if (sin == 1.0 || sin == -1.0) {
            n12 = Math.cos(1.5729779883598896);
        }
        if (cos == 1.0 || cos == -1.0) {
            n13 = Math.sin(8.726646259971648E-4);
        }
        final int n14 = n7 << 1;
        final int n15 = n14 + n7;
        final int n16 = n14 << 1;
        final int n17 = n16 + n7;
        final int n18 = -n2 / 2;
        final int n19 = n3 / 2;
        int n20 = -n19;
        if (n3 % 2 != 0) {
            --n20;
        }
        final int[] mb = this.mb;
        final int[] lb = this.lb;
        final int[] kb = this.kb;
        this.a(n8, n3, n6, n4, n5, n12, sin, cos, n13, array, this.rb, this.sb, this.tb, this.ub);
        double n21 = n8 * sin + n19 * n12;
        final int n22 = this.Eb ? (n2 + 1) : n2;
        final double n23 = -n12;
        double n24 = n8 * n12 - n19 * sin;
        final double n25 = sin;
        final double n26 = -cos;
        final double n27 = n13;
        double n28 = n24 * n13;
        double n29 = n24 * cos;
        final double n30 = sin * n13;
        final double n31 = sin * cos;
        int n32 = 0;
        double n33 = n28 - (n18 + 1) * cos;
        double n34 = n29 + (n18 + 1) * n13;
        double n35 = n28 - n18 * cos;
        double n36 = n29 + n18 * n13;
        double n37 = n10 * n21;
        final double n38 = n10 * n23;
        for (int i = n19; i > n20; --i) {
            double n39 = n35;
            double n40 = n36;
            final double n41 = n10 / n21;
            for (int j = 0; j < 8; ++j) {
                array[j].a((int)array[j].c(i));
            }
            this.a(array, 9);
            for (int k = 0; k < 8; ++k) {
                if (array[k].a > 0) {
                    n9 = k;
                    break;
                }
            }
            final double n42 = (n21 < 0.0) ? (-n21) : n21;
            final double n43 = (n33 < 0.0) ? (-n33) : n33;
            final double n44 = (n34 < 0.0) ? (-n34) : n34;
            int b;
            if (n42 <= n44 && n43 <= n44) {
                if (n34 < 0.0) {
                    b = 1;
                }
                else {
                    b = 3;
                }
            }
            else if (n42 <= n43) {
                if (n33 < 0.0) {
                    b = 2;
                }
                else {
                    b = 4;
                }
            }
            else if (n21 > 0.0) {
                b = 5;
            }
            else {
                b = 6;
            }
            int l = 0;
            while (l < n2) {
                int n45 = 32;
                int n46 = 0;
                final int n47 = array[n9].a - l + 1;
                final int n48 = (n47 >> 5) + (((n47 & 0x1F) > 0) ? 1 : 0);
                if (b == 1) {
                    int n49 = (int)(n10 - n10 * n39 / n40);
                    int n50 = (int)(n10 + n37 / n40);
                    while (n46 < n48) {
                        int n51 = (n45 <= n47) ? 32 : (n47 & 0x1F);
                        n39 += n51 * n26;
                        n40 += n51 * n27;
                        final int n52 = (int)(n10 - n10 * n39 / n40);
                        final int n53 = (int)(n10 + n37 / n40);
                        final double n54 = (n52 - n49) / n51;
                        final double n55 = (n53 - n50) / n51;
                        n45 += n51;
                        while (n51-- > 0) {
                            final int n56 = n50;
                            final int n57 = n49;
                            final int n58 = n56 & 0xFF;
                            final int n59 = 256 - n58;
                            final int n60 = n57 & 0xFF;
                            final int n61 = 256 - n60;
                            int n62 = n57 >> 8;
                            int n63 = n56 >> 8;
                            if (n63 >= n6) {
                                n63 = n11;
                            }
                            if (n63 < 0) {
                                n63 = 0;
                            }
                            if (n62 >= n6) {
                                n62 = n11;
                            }
                            if (n62 < 0) {
                                n62 = 0;
                            }
                            final int n64 = mb[n63++];
                            final int n65 = mb[n63];
                            final int n66 = lb[n62++];
                            final int n67 = lb[n62];
                            final int n68 = kb[n64 + n66];
                            final int n69 = kb[n64 + n67];
                            final int n70 = kb[n65 + n66];
                            final int n71 = kb[n65 + n67];
                            this.pb[n32 + l] = ((((n68 & 0xFF00FF) * n61 + (n69 & 0xFF00FF) * n60 >> 8 & 0xFF00FF) * n59 + ((n70 & 0xFF00FF) * n61 + (n71 & 0xFF00FF) * n60 >> 8 & 0xFF00FF) * n58 >> 8 & 0xFF00FF) | (((n68 & 0xFF00) * n61 + (n69 & 0xFF00) * n60) * n59 + ((n70 & 0xFF00) * n61 + (n71 & 0xFF00) * n60) * n58 >> 16 & 0xFF00) | 0xFF000000);
                            n49 += (int)n54;
                            n50 += (int)n55;
                            ++l;
                        }
                        n49 = n52;
                        n50 = n53;
                        ++n46;
                    }
                }
                else if (b == 3) {
                    int n72 = (int)(n10 - n10 * n39 / n40);
                    int n73 = (int)(n10 - n37 / n40);
                    while (n46 < n48) {
                        int n74 = (n45 <= n47) ? 32 : (n47 & 0x1F);
                        n39 += n74 * n26;
                        n40 += n74 * n27;
                        final int n75 = (int)(n10 - n10 * n39 / n40);
                        final int n76 = (int)(n10 - n37 / n40);
                        final double n77 = (n75 - n72) / n74;
                        final double n78 = (n76 - n73) / n74;
                        n45 += n74;
                        while (n74-- > 0) {
                            final int n79 = n73;
                            final int n80 = n72;
                            final int n81 = n79 & 0xFF;
                            final int n82 = 256 - n81;
                            final int n83 = n80 & 0xFF;
                            final int n84 = 256 - n83;
                            int n85 = n80 >> 8;
                            int n86 = n79 >> 8;
                            if (n86 > n11) {
                                n86 = n11;
                            }
                            if (n86 < 0) {
                                n86 = 0;
                            }
                            if (n85 > n11) {
                                n85 = n11;
                            }
                            if (n85 < 0) {
                                n85 = 0;
                            }
                            final int n87 = mb[n86++];
                            final int n88 = mb[n86];
                            final int n89 = lb[n85++];
                            final int n90 = lb[n85];
                            final int n91 = n89 + n14;
                            final int n92 = n90 + n14;
                            final int n93 = kb[n87 + n91];
                            final int n94 = kb[n87 + n92];
                            final int n95 = kb[n88 + n91];
                            final int n96 = kb[n88 + n92];
                            this.pb[n32 + l] = ((((n93 & 0xFF00FF) * n84 + (n94 & 0xFF00FF) * n83 >> 8 & 0xFF00FF) * n82 + ((n95 & 0xFF00FF) * n84 + (n96 & 0xFF00FF) * n83 >> 8 & 0xFF00FF) * n81 >> 8 & 0xFF00FF) | (((n93 & 0xFF00) * n84 + (n94 & 0xFF00) * n83) * n82 + ((n95 & 0xFF00) * n84 + (n96 & 0xFF00) * n83) * n81 >> 16 & 0xFF00) | 0xFF000000);
                            n72 += (int)n77;
                            n73 += (int)n78;
                            ++l;
                        }
                        n72 = n75;
                        n73 = n76;
                        ++n46;
                    }
                }
                else if (b == 2) {
                    int n97 = (int)(n10 + n10 * n40 / n39);
                    int n98 = (int)(n10 + n37 / n39);
                    while (n46 < n48) {
                        int n99 = (n45 <= n47) ? 32 : (n47 & 0x1F);
                        n39 += n99 * n26;
                        n40 += n99 * n27;
                        final int n100 = (int)(n10 + n10 * n40 / n39);
                        final int n101 = (int)(n10 + n37 / n39);
                        final double n102 = (n100 - n97) / n99;
                        final double n103 = (n101 - n98) / n99;
                        n45 += n99;
                        while (n99-- > 0) {
                            final int n104 = n98;
                            final int n105 = n97;
                            final int n106 = n104 & 0xFF;
                            final int n107 = 256 - n106;
                            final int n108 = n105 & 0xFF;
                            final int n109 = 256 - n108;
                            int n110 = n105 >> 8;
                            int n111 = n104 >> 8;
                            if (n111 > n11) {
                                n111 = n11;
                            }
                            if (n111 < 0) {
                                n111 = 0;
                            }
                            if (n110 > n11) {
                                n110 = n11;
                            }
                            if (n110 < 0) {
                                n110 = 0;
                            }
                            final int n112 = mb[n111++];
                            final int n113 = mb[n111];
                            final int n114 = lb[n110++];
                            final int n115 = lb[n110];
                            final int n116 = n114 + n15;
                            final int n117 = n115 + n15;
                            final int n118 = kb[n112 + n116];
                            final int n119 = kb[n112 + n117];
                            final int n120 = kb[n113 + n116];
                            final int n121 = kb[n113 + n117];
                            this.pb[n32 + l] = ((((n118 & 0xFF00FF) * n109 + (n119 & 0xFF00FF) * n108 >> 8 & 0xFF00FF) * n107 + ((n120 & 0xFF00FF) * n109 + (n121 & 0xFF00FF) * n108 >> 8 & 0xFF00FF) * n106 >> 8 & 0xFF00FF) | (((n118 & 0xFF00) * n109 + (n119 & 0xFF00) * n108) * n107 + ((n120 & 0xFF00) * n109 + (n121 & 0xFF00) * n108) * n106 >> 16 & 0xFF00) | 0xFF000000);
                            n97 += (int)n102;
                            n98 += (int)n103;
                            ++l;
                        }
                        n97 = n100;
                        n98 = n101;
                        ++n46;
                    }
                }
                else if (b == 4) {
                    int n122 = (int)(n10 + n10 * n40 / n39);
                    int n123 = (int)(n10 - n37 / n39);
                    while (n46 < n48) {
                        int n124 = (n45 <= n47) ? 32 : (n47 & 0x1F);
                        n39 += n124 * n26;
                        n40 += n124 * n27;
                        final int n125 = (int)(n10 + n10 * n40 / n39);
                        final int n126 = (int)(n10 - n37 / n39);
                        final double n127 = (n125 - n122) / n124;
                        final double n128 = (n126 - n123) / n124;
                        n45 += n124;
                        while (n124-- > 0) {
                            final int n129 = n123;
                            final int n130 = n122;
                            final int n131 = n129 & 0xFF;
                            final int n132 = 256 - n131;
                            final int n133 = n130 & 0xFF;
                            final int n134 = 256 - n133;
                            int n135 = n130 >> 8;
                            int n136 = n129 >> 8;
                            if (n136 > n11) {
                                n136 = n11;
                            }
                            if (n136 < 0) {
                                n136 = 0;
                            }
                            if (n135 > n11) {
                                n135 = n11;
                            }
                            if (n135 < 0) {
                                n135 = 0;
                            }
                            final int n137 = mb[n136++];
                            final int n138 = mb[n136];
                            final int n139 = lb[n135++];
                            final int n140 = lb[n135];
                            final int n141 = n139 + n7;
                            final int n142 = n140 + n7;
                            final int n143 = kb[n137 + n141];
                            final int n144 = kb[n137 + n142];
                            final int n145 = kb[n138 + n141];
                            final int n146 = kb[n138 + n142];
                            this.pb[n32 + l] = ((((n143 & 0xFF00FF) * n134 + (n144 & 0xFF00FF) * n133 >> 8 & 0xFF00FF) * n132 + ((n145 & 0xFF00FF) * n134 + (n146 & 0xFF00FF) * n133 >> 8 & 0xFF00FF) * n131 >> 8 & 0xFF00FF) | (((n143 & 0xFF00) * n134 + (n144 & 0xFF00) * n133) * n132 + ((n145 & 0xFF00) * n134 + (n146 & 0xFF00) * n133) * n131 >> 16 & 0xFF00) | 0xFF000000);
                            n122 += (int)n127;
                            n123 += (int)n128;
                            ++l;
                        }
                        n122 = n125;
                        n123 = n126;
                        ++n46;
                    }
                }
                else if (b == 5) {
                    int n147 = (int)(n10 + n41 * n39);
                    int n148 = (int)(n10 - n41 * n40);
                    while (n46 < n48) {
                        int n149 = (n45 <= n47) ? 32 : (n47 & 0x1F);
                        n39 += n149 * n26;
                        n40 += n149 * n27;
                        final int n150 = (int)(n10 + n41 * n39);
                        final int n151 = (int)(n10 - n41 * n40);
                        final double n152 = n26 * n41;
                        final double n153 = -n27 * n41;
                        n45 += n149;
                        while (n149-- > 0) {
                            final int n154 = n148;
                            final int n155 = n147;
                            final int n156 = n154 & 0xFF;
                            final int n157 = 256 - n156;
                            final int n158 = n155 & 0xFF;
                            final int n159 = 256 - n158;
                            int n160 = n155 >> 8;
                            int n161 = n154 >> 8;
                            if (n161 > n11) {
                                n161 = n11;
                            }
                            if (n161 < 0) {
                                n161 = 0;
                            }
                            if (n160 > n11) {
                                n160 = n11;
                            }
                            if (n160 < 0) {
                                n160 = 0;
                            }
                            final int n162 = mb[n161++];
                            final int n163 = mb[n161];
                            final int n164 = lb[n160++];
                            final int n165 = lb[n160];
                            final int n166 = n164 + n16;
                            final int n167 = n165 + n16;
                            final int n168 = kb[n162 + n166];
                            final int n169 = kb[n162 + n167];
                            final int n170 = kb[n163 + n166];
                            final int n171 = kb[n163 + n167];
                            this.pb[n32 + l] = ((((n168 & 0xFF00FF) * n159 + (n169 & 0xFF00FF) * n158 >> 8 & 0xFF00FF) * n157 + ((n170 & 0xFF00FF) * n159 + (n171 & 0xFF00FF) * n158 >> 8 & 0xFF00FF) * n156 >> 8 & 0xFF00FF) | (((n168 & 0xFF00) * n159 + (n169 & 0xFF00) * n158) * n157 + ((n170 & 0xFF00) * n159 + (n171 & 0xFF00) * n158) * n156 >> 16 & 0xFF00) | 0xFF000000);
                            n147 += (int)n152;
                            n148 += (int)n153;
                            ++l;
                        }
                        n147 = n150;
                        n148 = n151;
                        ++n46;
                    }
                }
                else if (b == 6) {
                    int n172 = (int)(n10 - n41 * n39);
                    int n173 = (int)(n10 - n41 * n40);
                    while (n46 < n48) {
                        int n174 = (n45 <= n47) ? 32 : (n47 & 0x1F);
                        n39 += n174 * n26;
                        n40 += n174 * n27;
                        final int n175 = (int)(n10 - n41 * n39);
                        final int n176 = (int)(n10 - n41 * n40);
                        final double n177 = -n26 * n41;
                        final double n178 = -n27 * n41;
                        n45 += n174;
                        while (n174-- > 0) {
                            final int n179 = n173;
                            final int n180 = n172;
                            final int n181 = n179 & 0xFF;
                            final int n182 = 256 - n181;
                            final int n183 = n180 & 0xFF;
                            final int n184 = 256 - n183;
                            int n185 = n180 >> 8;
                            int n186 = n179 >> 8;
                            if (n186 > n11) {
                                n186 = n11;
                            }
                            if (n186 < 0) {
                                n186 = 0;
                            }
                            if (n185 > n11) {
                                n185 = n11;
                            }
                            if (n185 < 0) {
                                n185 = 0;
                            }
                            final int n187 = mb[n186++];
                            final int n188 = mb[n186];
                            final int n189 = lb[n185++];
                            final int n190 = lb[n185];
                            final int n191 = n189 + n17;
                            final int n192 = n190 + n17;
                            final int n193 = kb[n187 + n191];
                            final int n194 = kb[n187 + n192];
                            final int n195 = kb[n188 + n191];
                            final int n196 = kb[n188 + n192];
                            this.pb[n32 + l] = ((((n193 & 0xFF00FF) * n184 + (n194 & 0xFF00FF) * n183 >> 8 & 0xFF00FF) * n182 + ((n195 & 0xFF00FF) * n184 + (n196 & 0xFF00FF) * n183 >> 8 & 0xFF00FF) * n181 >> 8 & 0xFF00FF) | (((n193 & 0xFF00) * n184 + (n194 & 0xFF00) * n183) * n182 + ((n195 & 0xFF00) * n184 + (n196 & 0xFF00) * n183) * n181 >> 16 & 0xFF00) | 0xFF000000);
                            n172 += (int)n177;
                            n173 += (int)n178;
                            ++l;
                        }
                        n172 = n175;
                        n173 = n176;
                        ++n46;
                    }
                }
                b = array[n9].b(b);
                ++n9;
            }
            n32 += n22;
            n24 += n25;
            n28 += n30;
            n29 += n31;
            n21 += n23;
            n33 += n30;
            n34 += n31;
            n35 += n30;
            n36 += n31;
            n37 += n38;
        }
    }
    
    private int b(final int n, final int n2) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        int n3 = 0;
        int n4 = -1;
        final int b = this.B;
        final int n5 = 7;
        int b2 = 0;
        int n7 = 0;
        Label_0111: {
            Label_0110: {
                if (!h) {
                    if (b == n5) {
                        int n6 = n2;
                        if (!h) {
                            if (n2 > 0) {
                                n6 = n2;
                                if (h) {
                                    return n6;
                                }
                                if (n2 < this.x) {
                                    n3 = n / this.x;
                                    if (!h) {
                                        break Label_0110;
                                    }
                                }
                            }
                            n6 = -1;
                        }
                        return n6;
                    }
                    final int n8;
                    n7 = (n8 = (b2 = this.B));
                    if (h) {
                        break Label_0111;
                    }
                }
                if (b == n5) {
                    int n9 = n;
                    if (!h) {
                        if (n > 0) {
                            n9 = n;
                            if (h) {
                                return n9;
                            }
                            if (n < this.w) {
                                n3 = n2 / this.w;
                                if (!h) {
                                    break Label_0110;
                                }
                            }
                        }
                        n9 = -1;
                    }
                    return n9;
                }
            }
            b2 = (n7 = n3);
        }
        if (!h) {
            Label_0196: {
                switch (n7) {
                    case 0: {
                        n4 = 1;
                        if (h) {
                            break Label_0196;
                        }
                        break;
                    }
                    case 1: {
                        n4 = 4;
                        if (h) {
                            break Label_0196;
                        }
                        break;
                    }
                    case 2: {
                        n4 = 3;
                        if (h) {
                            break Label_0196;
                        }
                        break;
                    }
                    case 3: {
                        n4 = 2;
                        if (h) {
                            break Label_0196;
                        }
                        break;
                    }
                    case 4: {
                        n4 = 5;
                        if (h) {
                            break Label_0196;
                        }
                        break;
                    }
                    case 5: {
                        n4 = 6;
                        break;
                    }
                }
            }
            b2 = n4;
        }
        return b2;
    }
    
    private boolean a(final double n, final double n2, final double n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        double n10 = Math.cos(n);
        final double sin = Math.sin(n);
        final double cos = Math.cos(n2);
        double n11 = Math.sin(n2);
        if (sin == 1.0 || sin == -1.0) {
            n10 = Math.cos(1.5729779883598896);
        }
        if (n2 == 3.141592653589793 || n2 == 0.0) {
            n11 = Math.sin(0.002181661564992912);
        }
        switch (n4) {
            case 1: {
                final double n12 = (n7 - n5) * 1.0 / n7;
                final double n13 = -(n7 - n6) * 1.0 / n7;
                final double n14 = cos + (n11 - cos * n12) * n11 / (cos + n11 * n12);
                final double n15 = n14 * (n3 * n10 + n3 * sin * sin / n10) / (1.0 + n14 * n13 * sin / n10);
                final double n16 = n15 * n13;
                if (n15 < 0.0) {
                    final double n17 = (n16 - n3 * sin) / n10;
                    final double n18 = (n15 - n3 * n10 * cos + n17 * sin * cos) / n11;
                    final double n19 = n8 - n17 + 0.5;
                    super.f = (int)(n9 + n18 + 0.5);
                    super.g = (int)n19;
                    return true;
                }
                return false;
            }
            case 3: {
                final double n20 = (n7 - n5) * 1.0 / n7;
                final double n21 = (n7 - n6) * 1.0 / n7;
                final double n22 = cos + (n11 - cos * n20) * n11 / (cos + n11 * n20);
                final double n23 = n22 * (n3 * n10 + n3 * sin * sin / n10) / (1.0 + n22 * n21 * sin / n10);
                final double n24 = n23 * n21;
                if (n23 > 0.0) {
                    final double n25 = (n24 - n3 * sin) / n10;
                    final double n26 = (n23 - n3 * n10 * cos + n25 * sin * cos) / n11;
                    final double n27 = n8 - n25 + 0.5;
                    super.f = (int)(n9 + n26 + 0.5);
                    super.g = (int)n27;
                    return true;
                }
                return false;
            }
            case 2: {
                final double n28 = -(n7 - n5) * 1.0 / n7;
                final double n29 = -(n7 - n6) * 1.0 / n7;
                final double n30 = (n3 * n10 * n11 + n3 * sin / n10 * sin * n11 + n3 * n10 * cos / n11 * cos + n3 * sin / n10 * sin * cos / n11 * cos) / (1.0 + n29 / n10 * sin * cos / n11 * cos + n29 / n10 * sin * n11 + n28 / n11 * cos);
                final double n31 = n30 * n29;
                final double n32 = n30 * n28;
                if (n30 < 0.0) {
                    final double n33 = (n31 - n3 * sin) / n10;
                    final double n34 = (n32 - n3 * n10 * cos + n33 * sin * cos) / n11;
                    final double n35 = n8 - n33 + 0.5;
                    super.f = (int)(n9 + n34 + 0.5);
                    super.g = (int)n35;
                    return true;
                }
                return false;
            }
            case 4: {
                final double n36 = -(n7 - n5) * 1.0 / n7;
                final double n37 = (n7 - n6) * 1.0 / n7;
                final double n38 = (n3 * n10 * n11 + n3 * sin / n10 * sin * n11 + n3 * n10 * cos / n11 * cos + n3 * sin / n10 * sin * cos / n11 * cos) / (1.0 + n37 / n10 * sin * cos / n11 * cos + n37 / n10 * sin * n11 + n36 / n11 * cos);
                final double n39 = n38 * n37;
                final double n40 = n38 * n36;
                if (n38 > 0.0) {
                    final double n41 = (n39 - n3 * sin) / n10;
                    final double n42 = (n40 - n3 * n10 * cos + n41 * sin * cos) / n11;
                    final double n43 = n8 - n41 + 0.5;
                    super.f = (int)(n9 + n42 + 0.5);
                    super.g = (int)n43;
                    return true;
                }
                return false;
            }
            case 5: {
                final double n44 = (n7 - n6) * 1.0 / n7;
                final double n45 = (n3 * n10 * cos + n3 * sin / n10 * sin * cos + n3 * n10 * n11 / cos * n11 + n3 * sin / n10 * sin * n11 / cos * n11) / (n44 + 1.0 / n10 * sin * cos + -(n7 - n5) * 1.0 / n7 / cos * n11 + 1.0 / n10 * sin * n11 / cos * n11);
                final double n46 = n45 * n44;
                if (n45 > 0.0) {
                    final double n47 = (n45 - n3 * sin) / n10;
                    final double n48 = (n46 - n3 * n10 * cos + n47 * sin * cos) / n11;
                    final double n49 = n8 - n47 + 0.5;
                    super.f = (int)(n9 + n48 + 0.5);
                    super.g = (int)n49;
                    return true;
                }
                return false;
            }
            case 6: {
                final double n50 = (n7 - n6) * 1.0 / n7;
                final double n51 = (n3 * n10 * cos + n3 * sin / n10 * sin * cos + n3 * n10 * n11 / cos * n11 + n3 * sin / n10 * sin * n11 / cos * n11) / (n50 + 1.0 / n10 * sin * cos + (n7 - n5) * 1.0 / n7 / cos * n11 + 1.0 / n10 * sin * n11 / cos * n11);
                final double n52 = n51 * n50;
                if (n51 < 0.0) {
                    final double n53 = (n51 - n3 * sin) / n10;
                    final double n54 = (n52 - n3 * n10 * cos + n53 * sin * cos) / n11;
                    final double n55 = n8 - n53 + 0.5;
                    super.f = (int)(n9 + n54 + 0.5);
                    super.g = (int)n55;
                    return true;
                }
                return false;
            }
            default: {
                return false;
            }
        }
    }
    
    private a[] a(final a[] array, final int n) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        int n2 = 1;
        do {
            int i = 0;
            int a = 0;
        Label_0008:
            while (i < a) {
                final a[] array2 = array;
                if (!h) {
                    final a a2 = array[n2];
                    int j = n2;
                    while (true) {
                        while (j > 0) {
                            final a[] array3 = array;
                            final int n3 = j - 1;
                            if (h) {
                                array3[n3] = a2;
                                ++n2;
                                continue Label_0008;
                            }
                            i = array[n3].a;
                            a = a2.a;
                            if (h) {
                                continue Label_0008;
                            }
                            if (i <= a) {
                                break;
                            }
                            array[j] = array[j - 1];
                            --j;
                            if (h) {
                                break;
                            }
                        }
                        final a[] array3 = array;
                        continue;
                    }
                }
                return array2;
            }
            break;
        } while (!h);
        return array;
    }
    
    private void a(final double n, final int n2, final int n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9, final a[] array, final b b, final b b2, final b b3, final b b4) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        b.c = true;
        b2.c = true;
        b3.c = true;
        b4.c = true;
        final double n10 = n3 * 0.5;
        final double n11 = n2 * 0.6;
        final double n12 = -n11;
        double n15;
        double n14;
        final double n13 = n14 = (n15 = dcmpg(n5, -this.jb));
        if (!h) {
            if (n13 < 0) {
                final double n16 = n * n6 * n8 + n * n7 / n6 * n7 * n8 + n * n6 * n9 / n8 * n9 + n * n7 / n6 * n7 * n9 / n8 * n9;
                boolean c;
                double n18;
                final double n17 = n18 = ((c = dcmpl(n4, 1.5707963267948966)) ? 1 : 0);
                Label_2257: {
                    final double n19;
                    Label_1599: {
                        if (!h) {
                            if (n17 > 0) {
                                n19 = ((c = dcmpg(n4, 4.71238898038469)) ? 1 : 0);
                                if (h) {
                                    break Label_1599;
                                }
                                if (n19 < 0) {
                                    this.a(n10, n, -n10, n10, 6, b, n6, n7, n8, n9, n16);
                                    this.a(n10, n, n10, n10, 6, b2, n6, n7, n8, n9, n16);
                                    this.a(n10, n, n10, -n10, 6, b3, n6, n7, n8, n9, n16);
                                    this.a(n10, n, -n10, -n10, 6, b4, n6, n7, n8, n9, n16);
                                    array[3].a(b.a, b.b, b2.a, b2.b);
                                    array[3].a(1, 6);
                                    array[5].a(b2.a, b2.b, b3.a, b3.b);
                                    array[5].a(4, 6);
                                    array[4].a(b3.a, b3.b, b4.a, b4.b);
                                    array[4].a(3, 6);
                                    array[2].a(b4.a, b4.b, b.a, b.b);
                                    array[2].a(2, 6);
                                    array[0].a(b3.a, b3.b, b.a, b.b);
                                    array[0].a(1, 2);
                                    array[6].a(b4.a, b4.b, b2.a, b2.b);
                                    array[6].a(1, 4);
                                    array[7].a(b.a, b.b, b3.a, b3.b);
                                    array[7].a(3, 4);
                                    array[1].a(b2.a, b2.b, b4.a, b4.b);
                                    array[1].a(2, 3);
                                    boolean c2;
                                    double n21;
                                    final double n20 = n21 = ((c2 = dcmpl(n4, 2.356194490192345)) ? 1 : 0);
                                    final double n22;
                                    final double n27;
                                    Label_0781: {
                                        if (!h) {
                                            if (n20 > 0) {
                                                n22 = ((c2 = dcmpg(n4, 3.9269908169872414)) ? 1 : 0);
                                                if (h) {
                                                    break Label_0781;
                                                }
                                                if (n22 < 0) {
                                                    b b5 = b;
                                                    double b6 = 0.0;
                                                    Label_0603: {
                                                        if (!h) {
                                                            if (!b.c) {
                                                                b6 = n11;
                                                                break Label_0603;
                                                            }
                                                            b5 = b;
                                                        }
                                                        b6 = b5.b;
                                                    }
                                                    final double n23 = b6;
                                                    b b7 = b2;
                                                    double b8 = 0.0;
                                                    Label_0628: {
                                                        if (!h) {
                                                            if (!b2.c) {
                                                                b8 = n11;
                                                                break Label_0628;
                                                            }
                                                            b7 = b2;
                                                        }
                                                        b8 = b7.b;
                                                    }
                                                    final double n24 = b8;
                                                    b b9 = b3;
                                                    double b10 = 0.0;
                                                    Label_0653: {
                                                        if (!h) {
                                                            if (!b3.c) {
                                                                b10 = n12;
                                                                break Label_0653;
                                                            }
                                                            b9 = b3;
                                                        }
                                                        b10 = b9.b;
                                                    }
                                                    final double n25 = b10;
                                                    b b11 = b4;
                                                    double b12 = 0.0;
                                                    Label_0678: {
                                                        if (!h) {
                                                            if (!b4.c) {
                                                                b12 = n12;
                                                                break Label_0678;
                                                            }
                                                            b11 = b4;
                                                        }
                                                        b12 = b11.b;
                                                    }
                                                    final double n26 = b12;
                                                    array[3].a(n23, n24);
                                                    array[5].a(n24, n25);
                                                    array[4].a(n25, n26);
                                                    array[2].a(n26, n23);
                                                    array[0].a(n11, n23);
                                                    array[6].a(n11, n24);
                                                    array[7].a(n12, n25);
                                                    array[1].a(n12, n26);
                                                    if (!h) {
                                                        break Label_2257;
                                                    }
                                                }
                                            }
                                            n27 = dcmpl(n4, 3.9269908169872414);
                                        }
                                    }
                                    if (!h) {
                                        if (n20 > 0) {
                                            b b13 = b;
                                            double b14 = 0.0;
                                            Label_0812: {
                                                if (!h) {
                                                    if (!b.c) {
                                                        b14 = n11;
                                                        break Label_0812;
                                                    }
                                                    b13 = b;
                                                }
                                                b14 = b13.b;
                                            }
                                            final double n28 = b14;
                                            b b15 = b2;
                                            double b16 = 0.0;
                                            Label_0837: {
                                                if (!h) {
                                                    if (!b2.c) {
                                                        b16 = n12;
                                                        break Label_0837;
                                                    }
                                                    b15 = b2;
                                                }
                                                b16 = b15.b;
                                            }
                                            final double n29 = b16;
                                            b b17 = b3;
                                            double b18 = 0.0;
                                            Label_0862: {
                                                if (!h) {
                                                    if (!b3.c) {
                                                        b18 = n12;
                                                        break Label_0862;
                                                    }
                                                    b17 = b3;
                                                }
                                                b18 = b17.b;
                                            }
                                            final double n30 = b18;
                                            b b19 = b4;
                                            double b20 = 0.0;
                                            Label_0887: {
                                                if (!h) {
                                                    if (!b4.c) {
                                                        b20 = n11;
                                                        break Label_0887;
                                                    }
                                                    b19 = b4;
                                                }
                                                b20 = b19.b;
                                            }
                                            final double n31 = b20;
                                            array[3].a(n28, n29);
                                            array[5].a(n29, n30);
                                            array[4].a(n30, n31);
                                            array[2].a(n31, n28);
                                            array[0].a(n11, n28);
                                            array[6].a(n12, n29);
                                            array[7].a(n12, n30);
                                            array[1].a(n11, n31);
                                            if (!h) {
                                                break Label_2257;
                                            }
                                        }
                                        n21 = dcmpg(n4, 2.356194490192345);
                                    }
                                    double b22 = 0.0;
                                    Label_1021: {
                                        b b21 = null;
                                        Label_1013: {
                                            if (!h) {
                                                if (n22 >= 0) {
                                                    break Label_2257;
                                                }
                                                b21 = b;
                                                if (h) {
                                                    break Label_1013;
                                                }
                                                c2 = b.c;
                                            }
                                            if (n27 == 0) {
                                                b22 = n12;
                                                break Label_1021;
                                            }
                                            b21 = b;
                                        }
                                        b22 = b21.b;
                                    }
                                    final double n32 = b22;
                                    b b23 = b2;
                                    double b24 = 0.0;
                                    Label_1046: {
                                        if (!h) {
                                            if (!b2.c) {
                                                b24 = n11;
                                                break Label_1046;
                                            }
                                            b23 = b2;
                                        }
                                        b24 = b23.b;
                                    }
                                    final double n33 = b24;
                                    b b25 = b3;
                                    double b26 = 0.0;
                                    Label_1071: {
                                        if (!h) {
                                            if (!b3.c) {
                                                b26 = n11;
                                                break Label_1071;
                                            }
                                            b25 = b3;
                                        }
                                        b26 = b25.b;
                                    }
                                    final double n34 = b26;
                                    b b27 = b4;
                                    double b28 = 0.0;
                                    Label_1096: {
                                        if (!h) {
                                            if (!b4.c) {
                                                b28 = n12;
                                                break Label_1096;
                                            }
                                            b27 = b4;
                                        }
                                        b28 = b27.b;
                                    }
                                    final double n35 = b28;
                                    array[3].a(n32, n33);
                                    array[5].a(n33, n34);
                                    array[4].a(n34, n35);
                                    array[2].a(n35, n32);
                                    array[0].a(n12, n32);
                                    array[6].a(n11, n33);
                                    array[7].a(n11, n34);
                                    array[1].a(n12, n35);
                                    if (!h) {
                                        break Label_2257;
                                    }
                                }
                            }
                            this.a(n10, n, -n10, n10, 6, b3, n6, n7, n8, n9, n16);
                            this.a(n10, n, n10, n10, 6, b4, n6, n7, n8, n9, n16);
                            this.a(n10, n, n10, -n10, 6, b, n6, n7, n8, n9, n16);
                            this.a(n10, n, -n10, -n10, 6, b2, n6, n7, n8, n9, n16);
                            array[3].a(b.a, b.b, b2.a, b2.b);
                            array[3].a(3, 6);
                            array[5].a(b2.a, b2.b, b3.a, b3.b);
                            array[5].a(2, 6);
                            array[4].a(b3.a, b3.b, b4.a, b4.b);
                            array[4].a(1, 6);
                            array[2].a(b4.a, b4.b, b.a, b.b);
                            array[2].a(4, 6);
                            array[0].a(b3.a, b3.b, b.a, b.b);
                            array[0].a(3, 4);
                            array[6].a(b4.a, b4.b, b2.a, b2.b);
                            array[6].a(3, 2);
                            array[7].a(b.a, b.b, b3.a, b3.b);
                            array[7].a(2, 1);
                            array[1].a(b2.a, b2.b, b4.a, b4.b);
                            array[1].a(1, 4);
                            final double n36 = dcmpl(n4, 4.71238898038469);
                        }
                    }
                    final double n37;
                    Label_1822: {
                        if (!h) {
                            if (n17 > 0) {
                                n37 = dcmpg(n4, 5.497787143782138);
                                if (h) {
                                    break Label_1822;
                                }
                                if (n37 < 0) {
                                    b b29 = b;
                                    double b30 = 0.0;
                                    Label_1644: {
                                        if (!h) {
                                            if (!b.c) {
                                                b30 = n12;
                                                break Label_1644;
                                            }
                                            b29 = b;
                                        }
                                        b30 = b29.b;
                                    }
                                    final double n38 = b30;
                                    b b31 = b2;
                                    double b32 = 0.0;
                                    Label_1669: {
                                        if (!h) {
                                            if (!b2.c) {
                                                b32 = n11;
                                                break Label_1669;
                                            }
                                            b31 = b2;
                                        }
                                        b32 = b31.b;
                                    }
                                    final double n39 = b32;
                                    b b33 = b3;
                                    double b34 = 0.0;
                                    Label_1694: {
                                        if (!h) {
                                            if (!b3.c) {
                                                b34 = n11;
                                                break Label_1694;
                                            }
                                            b33 = b3;
                                        }
                                        b34 = b33.b;
                                    }
                                    final double n40 = b34;
                                    b b35 = b4;
                                    double b36 = 0.0;
                                    Label_1719: {
                                        if (!h) {
                                            if (!b4.c) {
                                                b36 = n12;
                                                break Label_1719;
                                            }
                                            b35 = b4;
                                        }
                                        b36 = b35.b;
                                    }
                                    final double n41 = b36;
                                    array[3].a(n38, n39);
                                    array[5].a(n39, n40);
                                    array[4].a(n40, n41);
                                    array[2].a(n41, n38);
                                    array[0].a(n12, n38);
                                    array[6].a(n11, n39);
                                    array[7].a(n11, n40);
                                    array[1].a(n12, n41);
                                    if (!h) {
                                        break Label_2257;
                                    }
                                }
                            }
                            n18 = dcmpl(n4, 5.497787143782138);
                        }
                    }
                    double c3 = 0.0;
                    int n43 = 0;
                    final double n42;
                    Label_2045: {
                        Label_2039: {
                            double b38 = 0.0;
                            Label_1867: {
                                b b37 = null;
                                Label_1859: {
                                    if (!h) {
                                        if (n19 <= 0) {
                                            n42 = (n43 = (int)(c3 = dcmpg(n4, 0.7853981633974483)));
                                            if (h) {
                                                break Label_2045;
                                            }
                                            if (n42 >= 0) {
                                                break Label_2039;
                                            }
                                        }
                                        b37 = b;
                                        if (h) {
                                            break Label_1859;
                                        }
                                        c = b.c;
                                    }
                                    if (n37 == 0) {
                                        b38 = n11;
                                        break Label_1867;
                                    }
                                    b37 = b;
                                }
                                b38 = b37.b;
                            }
                            final double n44 = b38;
                            b b39 = b2;
                            double b40 = 0.0;
                            Label_1892: {
                                if (!h) {
                                    if (!b2.c) {
                                        b40 = n11;
                                        break Label_1892;
                                    }
                                    b39 = b2;
                                }
                                b40 = b39.b;
                            }
                            final double n45 = b40;
                            b b41 = b3;
                            double b42 = 0.0;
                            Label_1917: {
                                if (!h) {
                                    if (!b3.c) {
                                        b42 = n12;
                                        break Label_1917;
                                    }
                                    b41 = b3;
                                }
                                b42 = b41.b;
                            }
                            final double n46 = b42;
                            b b43 = b4;
                            double b44 = 0.0;
                            Label_1942: {
                                if (!h) {
                                    if (!b4.c) {
                                        b44 = n12;
                                        break Label_1942;
                                    }
                                    b43 = b4;
                                }
                                b44 = b43.b;
                            }
                            final double n47 = b44;
                            array[3].a(n44, n45);
                            array[5].a(n45, n46);
                            array[4].a(n46, n47);
                            array[2].a(n47, n44);
                            array[0].a(n11, n44);
                            array[6].a(n11, n45);
                            array[7].a(n12, n46);
                            array[1].a(n12, n47);
                            if (!h) {
                                break Label_2257;
                            }
                        }
                        final double n48;
                        n43 = (int)(n48 = (c3 = dcmpl(n4, 0.7853981633974483)));
                    }
                    if (!h) {
                        if (n42 <= 0) {
                            break Label_2257;
                        }
                        c3 = (n43 = dcmpg(n4, 1.5707963267948966));
                    }
                    double b46 = 0.0;
                    Label_2090: {
                        b b45 = null;
                        Label_2082: {
                            if (!h) {
                                if (n43 >= 0) {
                                    break Label_2257;
                                }
                                b45 = b;
                                if (h) {
                                    break Label_2082;
                                }
                                c3 = (b.c ? 1 : 0);
                            }
                            if (c3 == 0) {
                                b46 = n11;
                                break Label_2090;
                            }
                            b45 = b;
                        }
                        b46 = b45.b;
                    }
                    final double n49 = b46;
                    b b47 = b2;
                    double b48 = 0.0;
                    Label_2115: {
                        if (!h) {
                            if (!b2.c) {
                                b48 = n12;
                                break Label_2115;
                            }
                            b47 = b2;
                        }
                        b48 = b47.b;
                    }
                    final double n50 = b48;
                    b b49 = b3;
                    double b50 = 0.0;
                    Label_2140: {
                        if (!h) {
                            if (!b3.c) {
                                b50 = n12;
                                break Label_2140;
                            }
                            b49 = b3;
                        }
                        b50 = b49.b;
                    }
                    final double n51 = b50;
                    b b51 = b4;
                    double b52 = 0.0;
                    Label_2165: {
                        if (!h) {
                            if (!b4.c) {
                                b52 = n11;
                                break Label_2165;
                            }
                            b51 = b4;
                        }
                        b52 = b51.b;
                    }
                    final double n52 = b52;
                    array[3].a(n49, n50);
                    array[5].a(n50, n51);
                    array[4].a(n51, n52);
                    array[2].a(n52, n49);
                    array[0].a(n11, n49);
                    array[6].a(n12, n50);
                    array[7].a(n12, n51);
                    array[1].a(n11, n52);
                }
                if (!h) {
                    return;
                }
            }
            final double n53;
            n14 = (n53 = (n15 = dcmpl(n5, this.jb)));
        }
        if (!h) {
            if (n13 > 0) {
                final double n54 = n * n6 * n8 + n * n7 / n6 * n7 * n8 + n * n6 * n9 / n8 * n9 + n * n7 / n6 * n7 * n9 / n8 * n9;
                boolean c4;
                double n56;
                final double n55 = n56 = ((c4 = dcmpl(n4, 1.5707963267948966)) ? 1 : 0);
                Label_4451: {
                    final double n57;
                    Label_3793: {
                        if (!h) {
                            if (n55 > 0) {
                                n57 = ((c4 = dcmpg(n4, 4.71238898038469)) ? 1 : 0);
                                if (h) {
                                    break Label_3793;
                                }
                                if (n57 < 0) {
                                    this.a(n10, n, -n10, n10, 5, b, n6, n7, n8, n9, n54);
                                    this.a(n10, n, n10, n10, 5, b2, n6, n7, n8, n9, n54);
                                    this.a(n10, n, n10, -n10, 5, b3, n6, n7, n8, n9, n54);
                                    this.a(n10, n, -n10, -n10, 5, b4, n6, n7, n8, n9, n54);
                                    array[3].a(b.a, b.b, b2.a, b2.b);
                                    array[3].a(3, 5);
                                    array[5].a(b2.a, b2.b, b3.a, b3.b);
                                    array[5].a(4, 5);
                                    array[4].a(b3.a, b3.b, b4.a, b4.b);
                                    array[4].a(1, 5);
                                    array[2].a(b4.a, b4.b, b.a, b.b);
                                    array[2].a(2, 5);
                                    array[0].a(b3.a, b3.b, b.a, b.b);
                                    array[0].a(2, 3);
                                    array[6].a(b4.a, b4.b, b2.a, b2.b);
                                    array[6].a(3, 4);
                                    array[7].a(b.a, b.b, b3.a, b3.b);
                                    array[7].a(4, 1);
                                    array[1].a(b2.a, b2.b, b4.a, b4.b);
                                    array[1].a(1, 2);
                                    boolean c5;
                                    double n59;
                                    final double n58 = n59 = ((c5 = dcmpl(n4, 2.356194490192345)) ? 1 : 0);
                                    final double n60;
                                    final double n65;
                                    Label_2983: {
                                        if (!h) {
                                            if (n58 > 0) {
                                                n60 = ((c5 = dcmpg(n4, 3.9269908169872414)) ? 1 : 0);
                                                if (h) {
                                                    break Label_2983;
                                                }
                                                if (n60 < 0) {
                                                    b b53 = b;
                                                    double b54 = 0.0;
                                                    Label_2805: {
                                                        if (!h) {
                                                            if (!b.c) {
                                                                b54 = n11;
                                                                break Label_2805;
                                                            }
                                                            b53 = b;
                                                        }
                                                        b54 = b53.b;
                                                    }
                                                    final double n61 = b54;
                                                    b b55 = b2;
                                                    double b56 = 0.0;
                                                    Label_2830: {
                                                        if (!h) {
                                                            if (!b2.c) {
                                                                b56 = n11;
                                                                break Label_2830;
                                                            }
                                                            b55 = b2;
                                                        }
                                                        b56 = b55.b;
                                                    }
                                                    final double n62 = b56;
                                                    b b57 = b3;
                                                    double b58 = 0.0;
                                                    Label_2855: {
                                                        if (!h) {
                                                            if (!b3.c) {
                                                                b58 = n12;
                                                                break Label_2855;
                                                            }
                                                            b57 = b3;
                                                        }
                                                        b58 = b57.b;
                                                    }
                                                    final double n63 = b58;
                                                    b b59 = b4;
                                                    double b60 = 0.0;
                                                    Label_2880: {
                                                        if (!h) {
                                                            if (!b4.c) {
                                                                b60 = n12;
                                                                break Label_2880;
                                                            }
                                                            b59 = b4;
                                                        }
                                                        b60 = b59.b;
                                                    }
                                                    final double n64 = b60;
                                                    array[3].a(n61, n62);
                                                    array[5].a(n62, n63);
                                                    array[4].a(n63, n64);
                                                    array[2].a(n64, n61);
                                                    array[0].a(n11, n61);
                                                    array[6].a(n11, n62);
                                                    array[7].a(n12, n63);
                                                    array[1].a(n12, n64);
                                                    if (!h) {
                                                        break Label_4451;
                                                    }
                                                }
                                            }
                                            n65 = dcmpl(n4, 3.9269908169872414);
                                        }
                                    }
                                    if (!h) {
                                        if (n58 > 0) {
                                            b b61 = b;
                                            double b62 = 0.0;
                                            Label_3014: {
                                                if (!h) {
                                                    if (!b.c) {
                                                        b62 = n12;
                                                        break Label_3014;
                                                    }
                                                    b61 = b;
                                                }
                                                b62 = b61.b;
                                            }
                                            final double n66 = b62;
                                            b b63 = b2;
                                            double b64 = 0.0;
                                            Label_3039: {
                                                if (!h) {
                                                    if (!b2.c) {
                                                        b64 = n11;
                                                        break Label_3039;
                                                    }
                                                    b63 = b2;
                                                }
                                                b64 = b63.b;
                                            }
                                            final double n67 = b64;
                                            b b65 = b3;
                                            double b66 = 0.0;
                                            Label_3064: {
                                                if (!h) {
                                                    if (!b3.c) {
                                                        b66 = n11;
                                                        break Label_3064;
                                                    }
                                                    b65 = b3;
                                                }
                                                b66 = b65.b;
                                            }
                                            final double n68 = b66;
                                            b b67 = b4;
                                            double b68 = 0.0;
                                            Label_3089: {
                                                if (!h) {
                                                    if (!b4.c) {
                                                        b68 = n12;
                                                        break Label_3089;
                                                    }
                                                    b67 = b4;
                                                }
                                                b68 = b67.b;
                                            }
                                            final double n69 = b68;
                                            array[3].a(n66, n67);
                                            array[5].a(n67, n68);
                                            array[4].a(n68, n69);
                                            array[2].a(n69, n66);
                                            array[0].a(n12, n66);
                                            array[6].a(n11, n67);
                                            array[7].a(n11, n68);
                                            array[1].a(n12, n69);
                                            if (!h) {
                                                break Label_4451;
                                            }
                                        }
                                        n59 = dcmpg(n4, 2.356194490192345);
                                    }
                                    double b70 = 0.0;
                                    Label_3223: {
                                        b b69 = null;
                                        Label_3215: {
                                            if (!h) {
                                                if (n60 >= 0) {
                                                    break Label_4451;
                                                }
                                                b69 = b;
                                                if (h) {
                                                    break Label_3215;
                                                }
                                                c5 = b.c;
                                            }
                                            if (n65 == 0) {
                                                b70 = n11;
                                                break Label_3223;
                                            }
                                            b69 = b;
                                        }
                                        b70 = b69.b;
                                    }
                                    final double n70 = b70;
                                    b b71 = b2;
                                    double b72 = 0.0;
                                    Label_3248: {
                                        if (!h) {
                                            if (!b2.c) {
                                                b72 = n12;
                                                break Label_3248;
                                            }
                                            b71 = b2;
                                        }
                                        b72 = b71.b;
                                    }
                                    final double n71 = b72;
                                    b b73 = b3;
                                    double b74 = 0.0;
                                    Label_3273: {
                                        if (!h) {
                                            if (!b3.c) {
                                                b74 = n12;
                                                break Label_3273;
                                            }
                                            b73 = b3;
                                        }
                                        b74 = b73.b;
                                    }
                                    final double n72 = b74;
                                    b b75 = b4;
                                    double b76 = 0.0;
                                    Label_3298: {
                                        if (!h) {
                                            if (!b4.c) {
                                                b76 = n11;
                                                break Label_3298;
                                            }
                                            b75 = b4;
                                        }
                                        b76 = b75.b;
                                    }
                                    final double n73 = b76;
                                    array[3].a(n70, n71);
                                    array[5].a(n71, n72);
                                    array[4].a(n72, n73);
                                    array[2].a(n73, n70);
                                    array[0].a(n11, n70);
                                    array[6].a(n12, n71);
                                    array[7].a(n12, n72);
                                    array[1].a(n11, n73);
                                    if (!h) {
                                        break Label_4451;
                                    }
                                }
                            }
                            this.a(n10, n, -n10, n10, 5, b3, n6, n7, n8, n9, n54);
                            this.a(n10, n, n10, n10, 5, b4, n6, n7, n8, n9, n54);
                            this.a(n10, n, n10, -n10, 5, b, n6, n7, n8, n9, n54);
                            this.a(n10, n, -n10, -n10, 5, b2, n6, n7, n8, n9, n54);
                            array[3].a(b.a, b.b, b2.a, b2.b);
                            array[3].a(1, 5);
                            array[5].a(b2.a, b2.b, b3.a, b3.b);
                            array[5].a(2, 5);
                            array[4].a(b3.a, b3.b, b4.a, b4.b);
                            array[4].a(3, 5);
                            array[2].a(b4.a, b4.b, b.a, b.b);
                            array[2].a(4, 5);
                            array[0].a(b3.a, b3.b, b.a, b.b);
                            array[0].a(4, 1);
                            array[6].a(b4.a, b4.b, b2.a, b2.b);
                            array[6].a(1, 2);
                            array[7].a(b.a, b.b, b3.a, b3.b);
                            array[7].a(2, 3);
                            array[1].a(b2.a, b2.b, b4.a, b4.b);
                            array[1].a(3, 4);
                            final double n74 = dcmpl(n4, 4.71238898038469);
                        }
                    }
                    final double n75;
                    Label_4016: {
                        if (!h) {
                            if (n55 > 0) {
                                n75 = dcmpg(n4, 5.497787143782138);
                                if (h) {
                                    break Label_4016;
                                }
                                if (n75 < 0) {
                                    b b77 = b;
                                    double b78 = 0.0;
                                    Label_3838: {
                                        if (!h) {
                                            if (!b.c) {
                                                b78 = n11;
                                                break Label_3838;
                                            }
                                            b77 = b;
                                        }
                                        b78 = b77.b;
                                    }
                                    final double n76 = b78;
                                    b b79 = b2;
                                    double b80 = 0.0;
                                    Label_3863: {
                                        if (!h) {
                                            if (!b2.c) {
                                                b80 = n12;
                                                break Label_3863;
                                            }
                                            b79 = b2;
                                        }
                                        b80 = b79.b;
                                    }
                                    final double n77 = b80;
                                    b b81 = b3;
                                    double b82 = 0.0;
                                    Label_3888: {
                                        if (!h) {
                                            if (!b3.c) {
                                                b82 = n12;
                                                break Label_3888;
                                            }
                                            b81 = b3;
                                        }
                                        b82 = b81.b;
                                    }
                                    final double n78 = b82;
                                    b b83 = b4;
                                    double b84 = 0.0;
                                    Label_3913: {
                                        if (!h) {
                                            if (!b4.c) {
                                                b84 = n11;
                                                break Label_3913;
                                            }
                                            b83 = b4;
                                        }
                                        b84 = b83.b;
                                    }
                                    final double n79 = b84;
                                    array[3].a(n76, n77);
                                    array[5].a(n77, n78);
                                    array[4].a(n78, n79);
                                    array[2].a(n79, n76);
                                    array[0].a(n11, n76);
                                    array[6].a(n12, n77);
                                    array[7].a(n12, n78);
                                    array[1].a(n11, n79);
                                    if (!h) {
                                        break Label_4451;
                                    }
                                }
                            }
                            n56 = dcmpl(n4, 5.497787143782138);
                        }
                    }
                    double c6 = 0.0;
                    int n81 = 0;
                    final double n80;
                    Label_4239: {
                        Label_4233: {
                            double b86 = 0.0;
                            Label_4061: {
                                b b85 = null;
                                Label_4053: {
                                    if (!h) {
                                        if (n57 <= 0) {
                                            n80 = (n81 = (int)(c6 = dcmpg(n4, 0.7853981633974483)));
                                            if (h) {
                                                break Label_4239;
                                            }
                                            if (n80 >= 0) {
                                                break Label_4233;
                                            }
                                        }
                                        b85 = b;
                                        if (h) {
                                            break Label_4053;
                                        }
                                        c4 = b.c;
                                    }
                                    if (n75 == 0) {
                                        b86 = n11;
                                        break Label_4061;
                                    }
                                    b85 = b;
                                }
                                b86 = b85.b;
                            }
                            final double n82 = b86;
                            b b87 = b2;
                            double b88 = 0.0;
                            Label_4086: {
                                if (!h) {
                                    if (!b2.c) {
                                        b88 = n11;
                                        break Label_4086;
                                    }
                                    b87 = b2;
                                }
                                b88 = b87.b;
                            }
                            final double n83 = b88;
                            b b89 = b3;
                            double b90 = 0.0;
                            Label_4111: {
                                if (!h) {
                                    if (!b3.c) {
                                        b90 = n12;
                                        break Label_4111;
                                    }
                                    b89 = b3;
                                }
                                b90 = b89.b;
                            }
                            final double n84 = b90;
                            b b91 = b4;
                            double b92 = 0.0;
                            Label_4136: {
                                if (!h) {
                                    if (!b4.c) {
                                        b92 = n12;
                                        break Label_4136;
                                    }
                                    b91 = b4;
                                }
                                b92 = b91.b;
                            }
                            final double n85 = b92;
                            array[3].a(n82, n83);
                            array[5].a(n83, n84);
                            array[4].a(n84, n85);
                            array[2].a(n85, n82);
                            array[0].a(n11, n82);
                            array[6].a(n11, n83);
                            array[7].a(n12, n84);
                            array[1].a(n12, n85);
                            if (!h) {
                                break Label_4451;
                            }
                        }
                        final double n86;
                        n81 = (int)(n86 = (c6 = dcmpl(n4, 0.7853981633974483)));
                    }
                    if (!h) {
                        if (n80 <= 0) {
                            break Label_4451;
                        }
                        c6 = (n81 = dcmpg(n4, 1.5707963267948966));
                    }
                    double b94 = 0.0;
                    Label_4284: {
                        b b93 = null;
                        Label_4276: {
                            if (!h) {
                                if (n81 >= 0) {
                                    break Label_4451;
                                }
                                b93 = b;
                                if (h) {
                                    break Label_4276;
                                }
                                c6 = (b.c ? 1 : 0);
                            }
                            if (c6 == 0) {
                                b94 = n12;
                                break Label_4284;
                            }
                            b93 = b;
                        }
                        b94 = b93.b;
                    }
                    final double n87 = b94;
                    b b95 = b2;
                    double b96 = 0.0;
                    Label_4309: {
                        if (!h) {
                            if (!b2.c) {
                                b96 = n11;
                                break Label_4309;
                            }
                            b95 = b2;
                        }
                        b96 = b95.b;
                    }
                    final double n88 = b96;
                    b b97 = b3;
                    double b98 = 0.0;
                    Label_4334: {
                        if (!h) {
                            if (!b3.c) {
                                b98 = n11;
                                break Label_4334;
                            }
                            b97 = b3;
                        }
                        b98 = b97.b;
                    }
                    final double n89 = b98;
                    b b99 = b4;
                    double b100 = 0.0;
                    Label_4359: {
                        if (!h) {
                            if (!b4.c) {
                                b100 = n12;
                                break Label_4359;
                            }
                            b99 = b4;
                        }
                        b100 = b99.b;
                    }
                    final double n90 = b100;
                    array[3].a(n87, n88);
                    array[5].a(n88, n89);
                    array[4].a(n89, n90);
                    array[2].a(n90, n87);
                    array[0].a(n12, n87);
                    array[6].a(n11, n88);
                    array[7].a(n11, n89);
                    array[1].a(n12, n90);
                }
                if (!h) {
                    return;
                }
            }
            n14 = dcmpg(n4, 3.9269908169872414);
        }
        final double n99;
        final double n107;
        Label_5732: {
            Label_5726: {
                double n97 = 0.0;
                final double n98;
                Label_5133: {
                    final double n91;
                    Label_5102: {
                        if (!h) {
                            if (n14 <= 0) {
                                n91 = dcmpl(n4, 2.356194490192345);
                                if (h) {
                                    break Label_5102;
                                }
                                if (n91 > 0) {
                                    final double n92 = n * n6 + n * n7 * n7 / n6;
                                    this.a(n10, n, -n10, n10, 1, b, n6, n7, n8, n9, n92);
                                    this.a(n10, n, n10, n10, 1, b2, n6, n7, n8, n9, n92);
                                    this.a(n10, n, n10, -n10, 1, b3, n6, n7, n8, n9, n92);
                                    this.a(n10, n, -n10, -n10, 1, b4, n6, n7, n8, n9, n92);
                                    b b101 = b;
                                    double b102 = 0.0;
                                    Label_4624: {
                                        if (!h) {
                                            if (!b.c) {
                                                b102 = n11;
                                                break Label_4624;
                                            }
                                            b101 = b;
                                        }
                                        b102 = b101.b;
                                    }
                                    final double n93 = b102;
                                    b b103 = b2;
                                    double b104 = 0.0;
                                    Label_4649: {
                                        if (!h) {
                                            if (!b2.c) {
                                                b104 = n11;
                                                break Label_4649;
                                            }
                                            b103 = b2;
                                        }
                                        b104 = b103.b;
                                    }
                                    final double n94 = b104;
                                    b b105 = b3;
                                    double b106 = 0.0;
                                    Label_4674: {
                                        if (!h) {
                                            if (!b3.c) {
                                                b106 = n12;
                                                break Label_4674;
                                            }
                                            b105 = b3;
                                        }
                                        b106 = b105.b;
                                    }
                                    final double n95 = b106;
                                    b b107 = b4;
                                    double b108 = 0.0;
                                    Label_4699: {
                                        if (!h) {
                                            if (!b4.c) {
                                                b108 = n12;
                                                break Label_4699;
                                            }
                                            b107 = b4;
                                        }
                                        b108 = b107.b;
                                    }
                                    final double n96 = b108;
                                    array[3].a(b.a, b.b, b2.a, b2.b);
                                    array[3].a(n93, n94);
                                    array[3].a(1, 5);
                                    array[5].a(b2.a, b2.b, b3.a, b3.b);
                                    array[5].a(n94, n95);
                                    array[5].a(1, 4);
                                    array[4].a(b3.a, b3.b, b4.a, b4.b);
                                    array[4].a(n95, n96);
                                    array[4].a(1, 6);
                                    array[2].a(b4.a, b4.b, b.a, b.b);
                                    array[2].a(n96, n93);
                                    array[2].a(1, 2);
                                    array[0].a(b3.a, b3.b, b.a, b.b);
                                    array[0].a(n11, n93);
                                    array[0].a(2, 5);
                                    array[6].a(b4.a, b4.b, b2.a, b2.b);
                                    array[6].a(n11, n94);
                                    array[6].a(4, 5);
                                    array[7].a(b.a, b.b, b3.a, b3.b);
                                    array[7].a(n12, n95);
                                    array[7].a(4, 6);
                                    array[1].a(b2.a, b2.b, b4.a, b4.b);
                                    array[1].a(n12, n96);
                                    array[1].a(2, 6);
                                    if (!h) {
                                        return;
                                    }
                                }
                            }
                            n97 = n4;
                            n98 = 0.7853981633974483;
                            if (h) {
                                break Label_5133;
                            }
                            n15 = dcmpg(n4, n98);
                        }
                    }
                    if (n91 > 0) {
                        final double n101;
                        final double n100;
                        n99 = (n100 = (n101 = dcmpl(n4, 5.497787143782138)));
                        if (h) {
                            break Label_5732;
                        }
                        if (n99 <= 0) {
                            break Label_5726;
                        }
                    }
                    n97 = n * n6;
                }
                final double n102 = n97 + n98;
                this.a(n10, n, -n10, n10, 3, b, n6, n7, n8, n9, n102);
                this.a(n10, n, n10, n10, 3, b2, n6, n7, n8, n9, n102);
                this.a(n10, n, n10, -n10, 3, b3, n6, n7, n8, n9, n102);
                this.a(n10, n, -n10, -n10, 3, b4, n6, n7, n8, n9, n102);
                b b109 = b;
                double b110 = 0.0;
                Label_5259: {
                    if (!h) {
                        if (!b.c) {
                            b110 = n11;
                            break Label_5259;
                        }
                        b109 = b;
                    }
                    b110 = b109.b;
                }
                final double n103 = b110;
                b b111 = b2;
                double b112 = 0.0;
                Label_5284: {
                    if (!h) {
                        if (!b2.c) {
                            b112 = n11;
                            break Label_5284;
                        }
                        b111 = b2;
                    }
                    b112 = b111.b;
                }
                final double n104 = b112;
                b b113 = b3;
                double b114 = 0.0;
                Label_5309: {
                    if (!h) {
                        if (!b3.c) {
                            b114 = n12;
                            break Label_5309;
                        }
                        b113 = b3;
                    }
                    b114 = b113.b;
                }
                final double n105 = b114;
                b b115 = b4;
                double b116 = 0.0;
                Label_5334: {
                    if (!h) {
                        if (!b4.c) {
                            b116 = n12;
                            break Label_5334;
                        }
                        b115 = b4;
                    }
                    b116 = b115.b;
                }
                final double n106 = b116;
                array[3].a(b.a, b.b, b2.a, b2.b);
                array[3].a(n103, n104);
                array[3].a(3, 5);
                array[5].a(b2.a, b2.b, b3.a, b3.b);
                array[5].a(n104, n105);
                array[5].a(3, 2);
                array[4].a(b3.a, b3.b, b4.a, b4.b);
                array[4].a(n105, n106);
                array[4].a(3, 6);
                array[2].a(b4.a, b4.b, b.a, b.b);
                array[2].a(n106, n103);
                array[2].a(3, 4);
                array[0].a(b3.a, b3.b, b.a, b.b);
                array[0].a(n11, n103);
                array[0].a(4, 5);
                array[6].a(b4.a, b4.b, b2.a, b2.b);
                array[6].a(n11, n104);
                array[6].a(2, 5);
                array[7].a(b.a, b.b, b3.a, b3.b);
                array[7].a(n12, n105);
                array[7].a(2, 6);
                array[1].a(b2.a, b2.b, b4.a, b4.b);
                array[1].a(n12, n106);
                array[1].a(4, 6);
                if (!h) {
                    return;
                }
            }
            n107 = dcmpg(n4, 5.497787143782138);
        }
        final double n108;
        Label_6407: {
            if (!h) {
                if (n99 <= 0) {
                    final double n101;
                    n108 = (n101 = dcmpl(n4, 3.9269908169872414));
                    if (h) {
                        break Label_6407;
                    }
                    if (n108 > 0) {
                        final double n109 = n * n6 * n9 + n * n7 / n6 * n7 * n9 + n * n6 * n8 / n9 * n8 + n * n7 / n6 * n7 * n8 / n9 * n8;
                        this.a(n10, n, -n10, n10, 2, b, n6, n7, n8, n9, n109);
                        this.a(n10, n, n10, n10, 2, b2, n6, n7, n8, n9, n109);
                        this.a(n10, n, n10, -n10, 2, b3, n6, n7, n8, n9, n109);
                        this.a(n10, n, -n10, -n10, 2, b4, n6, n7, n8, n9, n109);
                        b b117 = b;
                        double b118 = 0.0;
                        Label_5934: {
                            if (!h) {
                                if (!b.c) {
                                    b118 = n11;
                                    break Label_5934;
                                }
                                b117 = b;
                            }
                            b118 = b117.b;
                        }
                        final double n110 = b118;
                        b b119 = b2;
                        double b120 = 0.0;
                        Label_5959: {
                            if (!h) {
                                if (!b2.c) {
                                    b120 = n11;
                                    break Label_5959;
                                }
                                b119 = b2;
                            }
                            b120 = b119.b;
                        }
                        final double n111 = b120;
                        b b121 = b3;
                        double b122 = 0.0;
                        Label_5984: {
                            if (!h) {
                                if (!b3.c) {
                                    b122 = n12;
                                    break Label_5984;
                                }
                                b121 = b3;
                            }
                            b122 = b121.b;
                        }
                        final double n112 = b122;
                        b b123 = b4;
                        double b124 = 0.0;
                        Label_6009: {
                            if (!h) {
                                if (!b4.c) {
                                    b124 = n12;
                                    break Label_6009;
                                }
                                b123 = b4;
                            }
                            b124 = b123.b;
                        }
                        final double n113 = b124;
                        array[3].a(b.a, b.b, b2.a, b2.b);
                        array[3].a(n110, n111);
                        array[3].a(2, 5);
                        array[5].a(b2.a, b2.b, b3.a, b3.b);
                        array[5].a(n111, n112);
                        array[5].a(1, 2);
                        array[4].a(b3.a, b3.b, b4.a, b4.b);
                        array[4].a(n112, n113);
                        array[4].a(2, 6);
                        array[2].a(b4.a, b4.b, b.a, b.b);
                        array[2].a(n113, n110);
                        array[2].a(3, 2);
                        array[0].a(b3.a, b3.b, b.a, b.b);
                        array[0].a(n11, n110);
                        array[0].a(3, 5);
                        array[6].a(b4.a, b4.b, b2.a, b2.b);
                        array[6].a(n11, n111);
                        array[6].a(1, 5);
                        array[7].a(b.a, b.b, b3.a, b3.b);
                        array[7].a(n12, n112);
                        array[7].a(1, 6);
                        array[1].a(b2.a, b2.b, b4.a, b4.b);
                        array[1].a(n12, n113);
                        array[1].a(3, 6);
                        if (!h) {
                            return;
                        }
                    }
                }
                final double n100 = dcmpg(n4, 2.356194490192345);
            }
        }
        double n114 = 0.0;
        final double n115;
        Label_6483: {
            if (!h) {
                if (n108 > 0) {
                    return;
                }
                n114 = n4;
                n115 = 0.7853981633974483;
                if (h) {
                    break Label_6483;
                }
                final double n101 = dcmpl(n4, n115);
            }
            if (n107 <= 0) {
                return;
            }
            n114 = n * n6 * n9 + n * n7 * n7 / n6 * n9 + n * n6 * n8 * n8 / n9;
        }
        final double n116 = n114 + n115;
        this.a(n10, n, -n10, n10, 4, b, n6, n7, n8, n9, n116);
        this.a(n10, n, n10, n10, 4, b2, n6, n7, n8, n9, n116);
        this.a(n10, n, n10, -n10, 4, b3, n6, n7, n8, n9, n116);
        this.a(n10, n, -n10, -n10, 4, b4, n6, n7, n8, n9, n116);
        b b125 = b;
        double b126 = 0.0;
        Label_6609: {
            if (!h) {
                if (!b.c) {
                    b126 = n11;
                    break Label_6609;
                }
                b125 = b;
            }
            b126 = b125.b;
        }
        final double n117 = b126;
        b b127 = b2;
        double b128 = 0.0;
        Label_6634: {
            if (!h) {
                if (!b2.c) {
                    b128 = n11;
                    break Label_6634;
                }
                b127 = b2;
            }
            b128 = b127.b;
        }
        final double n118 = b128;
        b b129 = b3;
        double b130 = 0.0;
        Label_6659: {
            if (!h) {
                if (!b3.c) {
                    b130 = n12;
                    break Label_6659;
                }
                b129 = b3;
            }
            b130 = b129.b;
        }
        final double n119 = b130;
        b b131 = b4;
        double b132 = 0.0;
        Label_6684: {
            if (!h) {
                if (!b4.c) {
                    b132 = n12;
                    break Label_6684;
                }
                b131 = b4;
            }
            b132 = b131.b;
        }
        final double n120 = b132;
        array[3].a(b.a, b.b, b2.a, b2.b);
        array[3].a(n117, n118);
        array[3].a(4, 5);
        array[5].a(b2.a, b2.b, b3.a, b3.b);
        array[5].a(n118, n119);
        array[5].a(4, 3);
        array[4].a(b3.a, b3.b, b4.a, b4.b);
        array[4].a(n119, n120);
        array[4].a(4, 6);
        array[2].a(b4.a, b4.b, b.a, b.b);
        array[2].a(n120, n117);
        array[2].a(1, 4);
        array[0].a(b3.a, b3.b, b.a, b.b);
        array[0].a(n11, n117);
        array[0].a(1, 5);
        array[6].a(b4.a, b4.b, b2.a, b2.b);
        array[6].a(n11, n118);
        array[6].a(3, 5);
        array[7].a(b.a, b.b, b3.a, b3.b);
        array[7].a(n12, n119);
        array[7].a(3, 6);
        array[1].a(b2.a, b2.b, b4.a, b4.b);
        array[1].a(n12, n120);
        array[1].a(1, 6);
    }
    
    private void a(final double n, final double n2, final double n3, final double n4, final int n5, final b b, final double n6, final double n7, final double n8, final double n9, final double n10) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        Label_0723: {
            switch (n5) {
                case 1: {
                    final double n11 = -n3 / n;
                    final double n12 = -n4 / n;
                    final double n13 = n8 + (n9 - n8 * n11) * n9 / (n8 + n9 * n11);
                    final double n14 = n13 * n10 / (1.0 + n13 * n12 * n7 / n6);
                    final double n15 = n14 * n12;
                    if (!h) {
                        if (n14 > 0.0) {
                            b.c = false;
                        }
                        b.b = (n15 - n2 * n7) / n6;
                        b.a = (n14 - n2 * n6 * n8 + b.b * n7 * n8) / n9;
                    }
                    if (h) {
                        break Label_0723;
                    }
                    break;
                }
                case 3: {
                    final double n16 = -n3 / n;
                    final double n17 = n4 / n;
                    final double n18 = n8 + (n9 - n8 * n16) * n9 / (n8 + n9 * n16);
                    final double n19 = n18 * n10 / (1.0 + n18 * n17 * n7 / n6);
                    final double n20 = n19 * n17;
                    if (!h) {
                        if (n19 < 0.0) {
                            b.c = false;
                        }
                        b.b = (n20 - n2 * n7) / n6;
                        b.a = (n19 - n2 * n6 * n8 + b.b * n7 * n8) / n9;
                    }
                    if (h) {
                        break Label_0723;
                    }
                    break;
                }
                case 2: {
                    final double n21 = n3 / n;
                    final double n22 = -n4 / n;
                    final double n23 = n10 / (1.0 + n22 / n6 * n7 * n8 / n9 * n8 + n22 / n6 * n7 * n9 + n21 / n9 * n8);
                    final double n24 = n23 * n22;
                    final double n25 = n23 * n21;
                    if (!h) {
                        if (n23 > 0.0) {
                            b.c = false;
                        }
                        b.b = (n24 - n2 * n7) / n6;
                        b.a = (n25 - n2 * n6 * n8 + b.b * n7 * n8) / n9;
                    }
                    if (h) {
                        break Label_0723;
                    }
                    break;
                }
                case 4: {
                    final double n26 = n3 / n;
                    final double n27 = n4 / n;
                    final double n28 = n10 / (1.0 + n27 / n6 * n7 * n8 / n9 * n8 + n27 / n6 * n7 * n9 + n26 / n9 * n8);
                    final double n29 = n28 * n27;
                    final double n30 = n28 * n26;
                    if (!h) {
                        if (n28 < 0.0) {
                            b.c = false;
                        }
                        b.b = (n29 - n2 * n7) / n6;
                        b.a = (n30 - n2 * n6 * n8 + b.b * n7 * n8) / n9;
                    }
                    if (h) {
                        break Label_0723;
                    }
                    break;
                }
                case 5: {
                    final double n31 = n4 / n;
                    final double n32 = n10 / (n31 + 1.0 / n6 * n7 * n8 + n3 / n / n8 * n9 + 1.0 / n6 * n7 * n9 / n8 * n9);
                    final double n33 = n32 * n31;
                    if (!h) {
                        if (n32 < 0.0) {
                            b.c = false;
                        }
                        b.b = (n32 - n2 * n7) / n6;
                        b.a = (n33 - n2 * n6 * n8 + b.b * n7 * n8) / n9;
                    }
                    if (h) {
                        break Label_0723;
                    }
                    break;
                }
                case 6: {
                    final double n34 = n4 / n;
                    final double n35 = n10 / (n34 + 1.0 / n6 * n7 * n8 + -n3 / n / n8 * n9 + 1.0 / n6 * n7 * n9 / n8 * n9);
                    final double n36 = n35 * n34;
                    if (!h) {
                        if (n35 > 0.0) {
                            b.c = false;
                        }
                        b.b = (n35 - n2 * n7) / n6;
                    }
                    b.a = (n36 - n2 * n6 * n8 + b.b * n7 * n8) / n9;
                    break;
                }
            }
        }
    }
    
    private void g() {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final double n = 2.44140625E-4;
        double n2 = 0.0;
        this.yb = new int[4097];
        int i = 0;
        while (true) {
            while (i < 4096) {
                this.yb[i] = (int)(Math.sqrt(1.0 + n2 * n2) * 4096.0 + 0.5);
                n2 += n;
                ++i;
                if (h) {
                    int j = 0;
                    while (true) {
                        while (j < this.A) {
                            this.lb[j] = j;
                            ++j;
                            if (h) {
                                int k = 0;
                                while (true) {
                                    while (k < this.A / 8) {
                                        this.nb[k] = k;
                                        ++k;
                                        if (h) {
                                            int l = 0;
                                            while (true) {
                                                while (l < this.A) {
                                                    this.mb[l] = k;
                                                    k += this.A;
                                                    ++l;
                                                    if (h) {
                                                        int n3 = 0;
                                                        while (true) {
                                                            while (n3 < this.A / 8) {
                                                                this.ob[n3] = k;
                                                                k += this.A / 8;
                                                                ++n3;
                                                                if (h) {
                                                                    int n4 = 0;
                                                                    while (n4 < n3) {
                                                                        this.wb[n4] = (int)(4096.0 * Math.sin(6.283185307179586 * n4 / n3) + 0.5);
                                                                        this.xb[n4] = (int)(4096.0 * Math.cos(6.283185307179586 * n4 / n3) + 0.5);
                                                                        ++n4;
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
                                                            this.ob[this.A >> 3] = k - this.A / 8;
                                                            n3 = 16385;
                                                            continue;
                                                        }
                                                    }
                                                    if (h) {
                                                        break;
                                                    }
                                                }
                                                this.mb[this.A] = k - this.A;
                                                k = 0;
                                                continue;
                                            }
                                        }
                                        if (h) {
                                            break;
                                        }
                                    }
                                    this.nb[this.A >> 3] = this.A / 8 - 1;
                                    k = 0;
                                    continue;
                                }
                            }
                            if (h) {
                                break;
                            }
                        }
                        this.lb[this.A] = this.A - 1;
                        continue;
                    }
                }
                if (h) {
                    break;
                }
            }
            this.yb[4096] = (int)(Math.sqrt(2.0) * 4096.0);
            this.lb = new int[this.A + 1];
            this.mb = new int[this.A + 1];
            this.nb = new int[this.A + 1];
            this.ob = new int[this.A + 1];
            continue;
        }
    }
    
    private int a(final int n, final int n2, final int n3, final int n4, final int[] array, final int n5) {
        return array[n >> n3] + (n2 >> n4) + n5;
    }
    
    private void a(final int n, final c c, final d[] array, final e[] array2, final int n2) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        final int n3 = n2 - 1;
        if (!h) {
            if (n3 == this.T) {
                array2[array[0].a()].a(null);
                this.T = 0;
                array2[n].a(array[0].a(c));
                array[0].a(n);
                if (!h) {
                    return;
                }
            }
            array[++this.T].a();
        }
        final int n4 = n3;
        if (!h) {
            if (n4 != -1) {
                array2[n4].a(null);
            }
            array2[n].a(array[this.T].a(c));
        }
        array[this.T].a(n);
    }
    
    static int a(final g g) {
        return g.y;
    }
}
