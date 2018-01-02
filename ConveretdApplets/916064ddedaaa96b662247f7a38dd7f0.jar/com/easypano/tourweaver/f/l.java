// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

public class l extends j
{
    public static final String D;
    public static final String E;
    public static final String F;
    double G;
    double H;
    double I;
    double J;
    double K;
    double L;
    o M;
    static int N;
    boolean O;
    boolean P;
    boolean Q;
    long R;
    int S;
    long T;
    long U;
    
    public l() {
        this.G = 0.0;
        this.H = 0.0;
        this.I = 0.0;
        this.J = 0.0;
        this.K = 0.0;
        this.L = 0.0;
        this.O = false;
        this.P = false;
        this.Q = false;
        this.R = 0L;
        this.S = 0;
        this.T = 0L;
        this.U = 0L;
    }
    
    public void a() {
        final boolean i = com.easypano.tourweaver.f.r.i;
        l l = this;
        l j = this;
        if (!i) {
            final o m;
            Label_0053: {
                if (super.x == null) {
                    m = this.M;
                    if (i) {
                        break Label_0053;
                    }
                    if (m == null) {
                        return;
                    }
                }
                this.M.a(super.m);
                this.M.b(this);
                final o k = this.M;
            }
            m.b(super.x);
            this.J = super.r;
            this.K = super.s;
            this.L = super.t;
            super.A = (int)(com.easypano.tourweaver.f.l.N * (super.h / 1000.0));
            this.d();
            l = this;
            j = this;
        }
        Label_0317: {
            if (!i) {
                Label_0302: {
                    if (j.C.equals(com.easypano.tourweaver.f.l.D)) {
                        this.O = true;
                        final double abs = Math.abs(super.u - super.r);
                        double n2;
                        final double n = n2 = dcmpl(super.u, super.r);
                        if (!i) {
                            if (n > 0) {
                                if (abs > 3.141592653589793) {
                                    super.C = com.easypano.tourweaver.f.l.F;
                                    if (!i) {
                                        break Label_0302;
                                    }
                                }
                                super.C = com.easypano.tourweaver.f.l.E;
                                if (!i) {
                                    break Label_0302;
                                }
                            }
                            final int n3;
                            n2 = (n3 = dcmpg(super.u, super.r));
                        }
                        if (!i) {
                            if (n < 0) {
                                if (abs > 3.141592653589793) {
                                    super.C = com.easypano.tourweaver.f.l.E;
                                    if (!i) {
                                        break Label_0302;
                                    }
                                }
                                super.C = com.easypano.tourweaver.f.l.F;
                                if (!i) {
                                    break Label_0302;
                                }
                            }
                            l = this;
                            if (i) {
                                break Label_0317;
                            }
                            n2 = dcmpl(super.u, super.r);
                        }
                        if (n2 == 0) {
                            final y y = (y)this.M.m();
                            Label_0295: {
                                if (!i) {
                                    if (y.o() <= 0.0) {
                                        break Label_0295;
                                    }
                                    super.C = com.easypano.tourweaver.f.l.E;
                                }
                                if (!i) {
                                    break Label_0302;
                                }
                            }
                            super.C = com.easypano.tourweaver.f.l.F;
                        }
                    }
                }
                this.M.b(0.0, 0.0, 0.0);
                this.q();
                l = this;
            }
        }
        l.a(this.M, 0);
    }
    
    private void d() {
        this.M.a(this.J, this.K, this.L);
    }
    
    public void a(final o m) {
        if (m != null) {
            this.M = m;
        }
    }
    
    private void q() {
        final boolean i = com.easypano.tourweaver.f.r.i;
        boolean b;
        final int n = (b = (super.A != 0)) ? 1 : 0;
        if (!i) {
            if (n == 0) {
                this.G = 0.0;
                this.H = 0.0;
                this.I = 0.0;
                return;
            }
            this.I = (super.w - this.L) / super.A;
            this.H = (super.v - this.K) / super.A;
            final boolean equals;
            b = (equals = super.C.equals(l.F));
        }
        if (!i) {
            if (n != 0) {
                double n2 = 0.0;
                double n4;
                final double n3 = n4 = dcmpl(super.r, super.u);
                l l = null;
                Label_0292: {
                    Label_0291: {
                        if (!i) {
                            if (n3 > 0) {
                                n2 = this.J - super.u;
                                if (!i) {
                                    break Label_0291;
                                }
                            }
                            final int n5;
                            n4 = (n5 = dcmpg(super.r, super.u));
                        }
                        if (!i) {
                            if (n3 < 0) {
                                final double n6 = dcmpl(this.J, super.r);
                                final double j;
                                final double r;
                                Label_0214: {
                                    Label_0185: {
                                        if (!i) {
                                            if (n6 > 0) {
                                                final double n7 = dcmpg(this.J, super.u);
                                                if (i) {
                                                    break Label_0185;
                                                }
                                                if (n7 < 0) {
                                                    n2 = 0.0;
                                                    if (!i) {
                                                        break Label_0291;
                                                    }
                                                }
                                            }
                                            j = this.J;
                                            r = super.r;
                                            if (i) {
                                                break Label_0214;
                                            }
                                            final double n8 = dcmpg(j, r);
                                        }
                                    }
                                    if (n6 <= 0) {
                                        n2 = this.J + 6.283185307179586 - super.u;
                                        if (!i) {
                                            break Label_0291;
                                        }
                                    }
                                    final double k = this.J;
                                    final double u = super.u;
                                }
                                n2 = j - r;
                                if (!i) {
                                    break Label_0291;
                                }
                            }
                            l = this;
                            if (i) {
                                break Label_0292;
                            }
                            n4 = dcmpl(super.r, super.u);
                        }
                        if (n4 == 0) {
                            l = this;
                            if (i) {
                                break Label_0292;
                            }
                            if (!this.O) {
                                final double m = this.J;
                                final double r2 = super.r;
                                if (!i) {
                                    if (m <= r2) {
                                        n2 = 6.283185307179586 - (super.r - this.J);
                                        if (!i) {
                                            break Label_0291;
                                        }
                                    }
                                    final double j2 = this.J;
                                    final double r3 = super.r;
                                }
                                n2 = m - r2;
                            }
                        }
                    }
                    l = this;
                }
                l.G = -n2 / super.A;
                if (!i) {
                    return;
                }
            }
            b = super.C.equals(l.E);
        }
        if (b) {
            double n9 = 0.0;
            double n11;
            final double n10 = n11 = dcmpg(super.r, super.u);
            l l2 = null;
            Label_0526: {
                Label_0525: {
                    if (!i) {
                        if (n10 < 0) {
                            n9 = super.u - this.J;
                            if (!i) {
                                break Label_0525;
                            }
                        }
                        final int n12;
                        n11 = (n12 = dcmpl(super.r, super.u));
                    }
                    if (!i) {
                        if (n10 > 0) {
                            final double n13 = dcmpl(this.J, super.u);
                            final double j3;
                            final double u2;
                            Label_0448: {
                                Label_0419: {
                                    if (!i) {
                                        if (n13 > 0) {
                                            final double n14 = dcmpg(this.J, super.r);
                                            if (i) {
                                                break Label_0419;
                                            }
                                            if (n14 < 0) {
                                                n9 = 0.0;
                                                if (!i) {
                                                    break Label_0525;
                                                }
                                            }
                                        }
                                        j3 = this.J;
                                        u2 = super.u;
                                        if (i) {
                                            break Label_0448;
                                        }
                                        final double n15 = dcmpl(j3, u2);
                                    }
                                }
                                if (n13 > 0) {
                                    n9 = 6.283185307179586 - this.J + super.u;
                                    if (!i) {
                                        break Label_0525;
                                    }
                                }
                                final double u3 = super.u;
                                final double j4 = this.J;
                            }
                            n9 = j3 - u2;
                            if (!i) {
                                break Label_0525;
                            }
                        }
                        l2 = this;
                        if (i) {
                            break Label_0526;
                        }
                        n11 = dcmpl(super.r, super.u);
                    }
                    if (n11 == 0) {
                        l2 = this;
                        if (i) {
                            break Label_0526;
                        }
                        if (!this.O) {
                            final double j5 = this.J;
                            final double r4 = super.r;
                            if (!i) {
                                if (j5 >= r4) {
                                    n9 = 6.283185307179586 - (this.J - super.r);
                                    if (!i) {
                                        break Label_0525;
                                    }
                                }
                                final double r5 = super.r;
                                final double j6 = this.J;
                            }
                            n9 = j5 - r4;
                        }
                    }
                }
                l2 = this;
            }
            l2.G = n9 / super.A;
        }
    }
    
    public void b() {
        final boolean i = com.easypano.tourweaver.f.r.i;
        l l = this;
        if (!i) {
            if (super.k) {
                this.P = true;
                l j = this;
                if (!i) {
                    if (this.M.m() != super.x) {
                        this.M.b(super.x);
                        this.M.a();
                    }
                    super.o = System.currentTimeMillis() - super.i;
                    this.T = super.o;
                    j = this;
                }
                j.d();
            }
            super.b();
            l = this;
        }
        l.M.b();
    }
    
    public long g() {
        return super.i;
    }
    
    private double c(double n) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        double n2 = 0.0;
        while (n < 0.0) {
            n += 6.283185307179586;
            if (i) {
                return n2;
            }
            if (i) {
                break;
            }
        }
        while (n > 6.283185307179586) {
            n2 = n - 6.283185307179586;
            if (i) {
                break;
            }
            n = n2;
            if (i) {
                break;
            }
        }
        return n2;
    }
    
    public void f() {
        final boolean i = com.easypano.tourweaver.f.r.i;
        final boolean k = super.k;
        Label_0186: {
            l l = null;
            Label_0179: {
                if (!i) {
                    if (k) {
                        return;
                    }
                    l = this;
                    if (i) {
                        break Label_0179;
                    }
                    final boolean p = this.P;
                }
                if (!k) {
                    final long currentTimeMillis = System.currentTimeMillis();
                    super.i = currentTimeMillis - super.o;
                    this.T = currentTimeMillis - this.U;
                    long n = this.T / this.S;
                    long q;
                    final boolean b = (q = (this.Q ? 1 : 0)) != 0L;
                    Label_0135: {
                        final long n2;
                        Label_0134: {
                            if (!i) {
                                if (b) {
                                    this.Q = false;
                                    this.S = 1;
                                    n = 50L;
                                    super.i = this.R + 50L;
                                    super.o = currentTimeMillis - super.i;
                                    this.U = currentTimeMillis - 50L;
                                }
                                n2 = n;
                                if (i) {
                                    break Label_0134;
                                }
                                q = lcmp(n2, 0L);
                            }
                            if (q != 0) {
                                break Label_0135;
                            }
                        }
                        n = n2;
                    }
                    super.A = (int)((super.h - super.i) / n);
                    if (!i) {
                        break Label_0186;
                    }
                }
                this.P = false;
                this.Q = true;
                super.o = System.currentTimeMillis() - super.i;
                l = this;
            }
            l.R = super.i;
        }
        this.J = this.M.q();
        this.K = this.M.r();
        this.L = this.M.s();
        this.q();
        this.M.b(this.G, this.H, this.I);
        ++super.B;
        ++this.S;
        l j = this;
        if (!i) {
            if (super.i < super.h) {
                return;
            }
            this.M.b(0.0, 0.0, 0.0);
            j = this;
        }
        j.e();
    }
    
    public void a(final double n) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        super.i = (long)(n * super.h);
        super.o = System.currentTimeMillis() - super.i;
        this.U = System.currentTimeMillis() - 50L;
        this.S = 1;
        final int n2 = (int)(l.N * (super.h / 1000.0));
        super.B = (int)(n * n2);
        super.A = n2 - super.B;
        this.K = super.s + (super.v - super.s) * n;
        this.L = super.t + (super.w - super.t) * n;
        double equals;
        int n4;
        final int n3 = n4 = (int)(equals = (super.C.equals(l.F) ? 1 : 0));
        l j = null;
        Label_0329: {
            Label_0328: {
                if (!i) {
                    if (n3 != 0) {
                        l l = this;
                        if (!i) {
                            if (super.r > super.u) {
                                this.J = (super.r - super.u) * n + super.r;
                                if (!i) {
                                    break Label_0328;
                                }
                            }
                            l = this;
                        }
                        l.J = super.r - (super.r + 6.283185307179586 - super.u) * n;
                        if (!i) {
                            break Label_0328;
                        }
                    }
                    final double n5;
                    n4 = (int)(n5 = (equals = dcmpl(super.r, super.u)));
                }
                if (!i) {
                    if (n3 > 0) {
                        this.J = super.r + (6.283185307179586 - super.r + super.u) * n;
                        if (!i) {
                            break Label_0328;
                        }
                    }
                    equals = (n4 = dcmpg(super.r, super.u));
                }
                if (!i) {
                    if (n4 < 0) {
                        this.J = (super.u - super.r) * n + super.r;
                        if (!i) {
                            break Label_0328;
                        }
                    }
                    j = this;
                    if (i) {
                        break Label_0329;
                    }
                    equals = dcmpl(super.r, super.u);
                }
                if (equals == 0) {
                    this.J = super.r + 6.283185307179586 * n;
                }
            }
            j = this;
        }
        j.d();
        if (com.easypano.tourweaver.b.a.o != 0) {
            com.easypano.tourweaver.f.r.i = !i;
        }
    }
    
    public void c() {
        super.c();
        final o m = this.M;
        if (!com.easypano.tourweaver.f.r.i) {
            if (m == null) {
                return;
            }
            final o i = this.M;
        }
        m.c();
    }
    
    public void e() {
        this.M.c();
        super.B = 0;
        super.i = 0L;
        super.e();
    }
    
    static {
        final char[] charArray = "{%pe o lc".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0094: {
                if (n > 1) {
                    break Label_0094;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '8';
                            break;
                        }
                        case 1: {
                            c2 = 'I';
                            break;
                        }
                        case 2: {
                            c2 = '\u001f';
                            break;
                        }
                        case 3: {
                            c2 = '\u0006';
                            break;
                        }
                        default: {
                            c2 = 'K';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n > n3) {
                continue;
            }
            break;
        }
        E = new String(charArray).intern();
        final char[] charArray2 = "y<ki".toCharArray();
        int length2;
        int n5;
        final int n4 = n5 = (length2 = charArray2.length);
        int n6 = 0;
        while (true) {
            Label_0210: {
                if (n4 > 1) {
                    break Label_0210;
                }
                length2 = (n5 = n6);
                do {
                    final char c3 = charArray2[n5];
                    char c4 = '\0';
                    switch (n6 % 5) {
                        case 0: {
                            c4 = '8';
                            break;
                        }
                        case 1: {
                            c4 = 'I';
                            break;
                        }
                        case 2: {
                            c4 = '\u001f';
                            break;
                        }
                        case 3: {
                            c4 = '\u0006';
                            break;
                        }
                        default: {
                            c4 = 'K';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n6;
                } while (n4 == 0);
            }
            if (n4 > n6) {
                continue;
            }
            break;
        }
        D = new String(charArray2).intern();
        final char[] charArray3 = "y'ko\bT&|m\u001cQ:z".toCharArray();
        int length3;
        int n8;
        final int n7 = n8 = (length3 = charArray3.length);
        int n9 = 0;
        while (true) {
            Label_0326: {
                if (n7 > 1) {
                    break Label_0326;
                }
                length3 = (n8 = n9);
                do {
                    final char c5 = charArray3[n8];
                    char c6 = '\0';
                    switch (n9 % 5) {
                        case 0: {
                            c6 = '8';
                            break;
                        }
                        case 1: {
                            c6 = 'I';
                            break;
                        }
                        case 2: {
                            c6 = '\u001f';
                            break;
                        }
                        case 3: {
                            c6 = '\u0006';
                            break;
                        }
                        default: {
                            c6 = 'K';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n9;
                } while (n7 == 0);
            }
            if (n7 <= n9) {
                F = new String(charArray3).intern();
                l.N = 25;
                return;
            }
            continue;
        }
    }
}
