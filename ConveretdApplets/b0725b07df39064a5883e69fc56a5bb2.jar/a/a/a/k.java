// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.util.Random;

public final class k
{
    private int a;
    private o b;
    private static boolean c;
    private static boolean d;
    private static Random e;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private static boolean m;
    private static int n;
    private static boolean o;
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
    private static int J;
    private static int K;
    private static int L;
    private static int M;
    private static int N;
    private static final short[] O;
    private static final short[] P;
    private static final short[] Q;
    private static final short[] R;
    private static final short[] S;
    private static short[] T;
    private static short[] U;
    private static short[] V;
    private static short[] W;
    private static short[] X;
    private static short[] Y;
    private static short[] Z;
    private static short[] aa;
    
    public k(final int a) {
        this.a = a;
    }
    
    public final int a() {
        return this.a;
    }
    
    public final boolean a(final o b) {
        this.b = b;
        a.a.a.k.U = new short[256];
        a.a.a.k.V = new short[256];
        a.a.a.k.W = new short[256];
        a.a.a.k.X = new short[256];
        a.a.a.k.aa = new short[256];
        for (int i = 0; i < 256; ++i) {
            final int n = ((i & 0x80) != 0x0) ? 128 : 0;
            final int n2 = (i == 0) ? 64 : 0;
            final int n3 = i & 0x20;
            final int n4 = i & 0x8;
            final int n5 = E(i) ? 4 : 0;
            a.a.a.k.U[i] = (short)(n | n2 | n3 | n4);
            a.a.a.k.V[i] = (short)(n | n2 | n3 | n4 | n5);
            a.a.a.k.W[i] = (short)(n | n2 | n3 | n4);
            final short[] w = a.a.a.k.W;
            final int n6 = i;
            w[n6] |= (short)((i == 128) ? 4 : 0);
            final short[] w2 = a.a.a.k.W;
            final int n7 = i;
            w2[n7] |= (short)(((i & 0xF) == 0x0) ? 16 : 0);
            a.a.a.k.X[i] = (short)(n | n2 | n3 | n4 | 0x2);
            final short[] x = a.a.a.k.X;
            final int n8 = i;
            x[n8] |= (short)((i == 127) ? 4 : 0);
            final short[] x2 = a.a.a.k.X;
            final int n9 = i;
            x2[n9] |= (short)(((i & 0xF) == 0xF) ? 16 : 0);
            a.a.a.k.aa[i] = (short)((i != 0) ? (i & 0x80) : 68);
            final short[] aa = a.a.a.k.aa;
            final int n10 = i;
            aa[n10] |= (short)(n3 | n4 | 0x10);
        }
        a.a.a.k.Y = new short[131072];
        a.a.a.k.Z = new short[131072];
        int n11 = 0;
        int n12 = 65536;
        int n13 = 0;
        int n14 = 65536;
        for (int j = 0; j < 256; ++j) {
            for (int k = 0; k < 256; ++k) {
                final int n15 = k - j;
                if (k != 0) {
                    if ((k & 0x80) != 0x0) {
                        a.a.a.k.Y[n11] = 128;
                    }
                    else {
                        a.a.a.k.Y[n11] = 0;
                    }
                }
                else {
                    a.a.a.k.Y[n11] = 64;
                }
                final short[] y = a.a.a.k.Y;
                final int n16 = n11;
                y[n16] |= (short)(k & 0x28);
                if ((k & 0xF) < (j & 0xF)) {
                    final short[] y2 = a.a.a.k.Y;
                    final int n17 = n11;
                    y2[n17] |= 0x10;
                }
                if (k < j) {
                    final short[] y3 = a.a.a.k.Y;
                    final int n18 = n11;
                    y3[n18] |= 0x1;
                }
                if (((n15 ^ j ^ 0x80) & (n15 ^ k) & 0x80) != 0x0) {
                    final short[] y4 = a.a.a.k.Y;
                    final int n19 = n11;
                    y4[n19] |= 0x4;
                }
                ++n11;
                final int n20 = k - j - 1;
                if (k != 0) {
                    if ((k & 0x80) != 0x0) {
                        a.a.a.k.Y[n12] = 128;
                    }
                    else {
                        a.a.a.k.Y[n12] = 0;
                    }
                }
                else {
                    a.a.a.k.Y[n12] = 64;
                }
                final short[] y5 = a.a.a.k.Y;
                final int n21 = n12;
                y5[n21] |= (short)(k & 0x28);
                if ((k & 0xF) <= (j & 0xF)) {
                    final short[] y6 = a.a.a.k.Y;
                    final int n22 = n12;
                    y6[n22] |= 0x10;
                }
                if (k <= j) {
                    final short[] y7 = a.a.a.k.Y;
                    final int n23 = n12;
                    y7[n23] |= 0x1;
                }
                if (((n20 ^ j ^ 0x80) & (n20 ^ k) & 0x80) != 0x0) {
                    final short[] y8 = a.a.a.k.Y;
                    final int n24 = n12;
                    y8[n24] |= 0x4;
                }
                ++n12;
                final int n25 = j - k;
                if (k != 0) {
                    if ((k & 0x80) != 0x0) {
                        a.a.a.k.Z[n13] = 130;
                    }
                    else {
                        a.a.a.k.Z[n13] = 2;
                    }
                }
                else {
                    a.a.a.k.Z[n13] = 66;
                }
                final short[] z = a.a.a.k.Z;
                final int n26 = n13;
                z[n26] |= (short)(k & 0x28);
                if ((k & 0xF) > (j & 0xF)) {
                    final short[] z2 = a.a.a.k.Z;
                    final int n27 = n13;
                    z2[n27] |= 0x10;
                }
                if (k > j) {
                    final short[] z3 = a.a.a.k.Z;
                    final int n28 = n13;
                    z3[n28] |= 0x1;
                }
                if (((n25 ^ j) & (j ^ k) & 0x80) != 0x0) {
                    final short[] z4 = a.a.a.k.Z;
                    final int n29 = n13;
                    z4[n29] |= 0x4;
                }
                ++n13;
                final int n30 = j - k - 1;
                if (k != 0) {
                    if ((k & 0x80) != 0x0) {
                        a.a.a.k.Z[n14] = 130;
                    }
                    else {
                        a.a.a.k.Z[n14] = 2;
                    }
                }
                else {
                    a.a.a.k.Z[n14] = 66;
                }
                final short[] z5 = a.a.a.k.Z;
                final int n31 = n14;
                z5[n31] |= (short)(k & 0x28);
                if ((k & 0xF) >= (j & 0xF)) {
                    final short[] z6 = a.a.a.k.Z;
                    final int n32 = n14;
                    z6[n32] |= 0x10;
                }
                if (k >= j) {
                    final short[] z7 = a.a.a.k.Z;
                    final int n33 = n14;
                    z7[n33] |= 0x1;
                }
                if (((n30 ^ j) & (j ^ k) & 0x80) != 0x0) {
                    final short[] z8 = a.a.a.k.Z;
                    final int n34 = n14;
                    z8[n34] |= 0x4;
                }
                ++n14;
            }
        }
        a.a.a.k.T = new short[2048];
        int n35 = 256;
        while (n35-- != 0) {
            for (int l = 0; l <= 1; ++l) {
                for (int n36 = 0; n36 <= 1; ++n36) {
                    for (int n37 = 0; n37 <= 1; ++n37) {
                        final short[] t = a.a.a.k.T;
                        final int n38 = l << 8 | n37 << 9 | n36 << 10 | n35;
                        final int p = n35;
                        final int m = l | n37 << 1 | n36 << 4;
                        this.p = p;
                        a.a.a.k.J = m;
                        final int p2 = this.p;
                        int n39 = 0;
                        int n40 = m & 0x1;
                        if ((m & 0x10) != 0x0 || (p2 & 0xF) > 9) {
                            n39 = 6;
                        }
                        if (n40 == 1 || p2 > 159 || (p2 > 143 && (p2 & 0xF) > 9)) {
                            n39 |= 0x60;
                            n40 = 1;
                        }
                        if (p2 > 153) {
                            n40 = 1;
                        }
                        if ((m & 0x2) != 0x0) {
                            this.u(n39);
                        }
                        else {
                            this.s(n39);
                        }
                        final int n41 = (a.a.a.k.J & 0xFE) | n40;
                        int n42;
                        if (E(this.p)) {
                            n42 = ((n41 & 0xFB) | 0x4);
                        }
                        else {
                            n42 = (n41 & 0xFB);
                        }
                        t[n38] = (short)(this.p | n42 << 8);
                    }
                }
            }
        }
        this.p = (a.a.a.k.J = 0);
        this.b();
        return true;
    }
    
    public final void b() {
        final boolean b = false;
        this.q = (b ? 1 : 0);
        this.p = (b ? 1 : 0);
        final boolean b2 = false;
        this.u = (b2 ? 1 : 0);
        this.t = (b2 ? 1 : 0);
        this.s = (b2 ? 1 : 0);
        this.r = (b2 ? 1 : 0);
        final boolean b3 = false;
        this.y = (b3 ? 1 : 0);
        this.x = (b3 ? 1 : 0);
        this.w = (b3 ? 1 : 0);
        this.v = (b3 ? 1 : 0);
        final boolean b4 = false;
        this.C = (b4 ? 1 : 0);
        this.B = (b4 ? 1 : 0);
        this.A = (b4 ? 1 : 0);
        this.z = (b4 ? 1 : 0);
        final boolean b5 = false;
        this.E = (b5 ? 1 : 0);
        this.D = (b5 ? 1 : 0);
        final boolean b6 = false;
        this.G = (b6 ? 1 : 0);
        this.F = (b6 ? 1 : 0);
        this.H = 0;
        this.I = 0;
        a.a.a.k.J = 0;
        a.a.a.k.K = 0;
        this.f = 0;
        this.g = 61440;
        a.a.a.k.L = 0;
        a.a.a.k.M = 0;
        a.a.a.k.N = 0;
        this.h = 0;
        this.i = false;
        this.j = false;
        this.l = false;
        a.a.a.k.n = 0;
        this.k = false;
    }
    
    public final char a(final int n) {
        ++a.a.a.k.N;
        a.a.a.k.L += n;
    Label_6148:
        while (a.a.a.k.L > 0) {
            final int l = a.a.a.k.L;
            if (a.a.a.k.o) {
                a.a.a.k.o = false;
                this.j = this.i;
                this.i = false;
                if (a.a.a.k.d) {
                    this.o();
                }
                if (this.k) {
                    ++this.f;
                    this.k = false;
                }
                this.b(this.f);
                this.f = 102;
                a.a.a.k.L -= 11;
            }
            else if (a.a.a.k.m) {
                if (this.i && (!a.a.a.k.c || !this.l)) {
                    if (this.k) {
                        ++this.f;
                        this.k = false;
                    }
                    if (a.a.a.k.d) {
                        this.o();
                    }
                    final boolean b = false;
                    this.j = b;
                    this.i = b;
                    a.a.a.k.m = false;
                    a.a.a.k.o = false;
                    this.b(this.f);
                    if (this.h == 0) {
                        this.f = 56;
                        a.a.a.k.L -= 13;
                    }
                    else if (this.h == 1) {
                        this.f = 56;
                        a.a.a.k.L -= 13;
                    }
                    else {
                        this.f = this.G(this.I << 8);
                        a.a.a.k.L -= 19;
                    }
                }
            }
            this.f &= 0xFFFF;
            final int h = this.H(this.f++);
            if (a.a.a.k.c) {
                this.l = false;
            }
            a.a.a.k.L -= a.a.a.k.O[h];
            if (a.a.a.k.d) {
                this.o();
            }
            switch (h) {
                case 1: {
                    this.s = this.H(this.f++);
                    this.r = this.H(this.f++);
                    break;
                }
                case 2: {
                    this.d(this.e(), this.p);
                    break;
                }
                case 3: {
                    this.s = (this.s + 1 & 0xFF);
                    if (this.s == 0) {
                        this.r = (this.r + 1 & 0xFF);
                    }
                    break;
                }
                case 4: {
                    this.r = A(this.r);
                    break;
                }
                case 5: {
                    this.r = B(this.r);
                    break;
                }
                case 6: {
                    this.r = this.H(this.f++);
                    break;
                }
                case 7: {
                    final int n2 = this.p >> 7;
                    this.p = ((this.p << 1 & 0xFF) | n2);
                    a.a.a.k.J = ((a.a.a.k.J & 0xEC) | n2);
                    break;
                }
                case 8: {
                    final int p = this.p;
                    this.p = this.q;
                    this.q = p;
                    final int j = a.a.a.k.J;
                    a.a.a.k.J = a.a.a.k.K;
                    a.a.a.k.K = j;
                    break;
                }
                case 9: {
                    this.x(b(this.g(), this.e()));
                    break;
                }
                case 10: {
                    this.p = this.H(this.e());
                    break;
                }
                case 11: {
                    this.l();
                    break;
                }
                case 12: {
                    this.s = A(this.s);
                    break;
                }
                case 13: {
                    this.s = B(this.s);
                    break;
                }
                case 14: {
                    this.s = this.H(this.f++);
                    break;
                }
                case 15: {
                    final int n3 = this.p & 0x1;
                    this.p = (this.p >> 1 | n3 << 7);
                    a.a.a.k.J = ((a.a.a.k.J & 0xEC) | n3);
                    break;
                }
                case 16: {
                    this.r = (this.r - 1 & 0xFF);
                    this.c(this.r != 0);
                    break;
                }
                case 17: {
                    this.w = this.H(this.f++);
                    this.v = this.H(this.f++);
                    break;
                }
                case 18: {
                    this.d(this.f(), this.p);
                    break;
                }
                case 19: {
                    this.j();
                    break;
                }
                case 20: {
                    this.v = A(this.v);
                    break;
                }
                case 21: {
                    this.v = B(this.v);
                    break;
                }
                case 22: {
                    this.v = this.H(this.f++);
                    break;
                }
                case 23: {
                    final int n4 = this.p >> 7;
                    this.p = ((this.p << 1 | (a.a.a.k.J & 0x1)) & 0xFF);
                    a.a.a.k.J = ((a.a.a.k.J & 0xEC) | n4);
                    break;
                }
                case 24: {
                    this.f += this.p() + 1;
                    break;
                }
                case 25: {
                    this.x(b(this.g(), this.f()));
                    break;
                }
                case 26: {
                    this.p = this.H(this.f());
                    break;
                }
                case 27: {
                    this.m();
                    break;
                }
                case 28: {
                    this.w = A(this.w);
                    break;
                }
                case 29: {
                    this.w = B(this.w);
                    break;
                }
                case 30: {
                    this.w = this.H(this.f++);
                    break;
                }
                case 31: {
                    final int n5 = this.p & 0x1;
                    this.p = ((this.p >> 1 | (a.a.a.k.J & 0x1) << 7) & 0xFF);
                    a.a.a.k.J = ((a.a.a.k.J & 0xEC) | n5);
                    break;
                }
                case 32: {
                    this.c((a.a.a.k.J & 0x40) == 0x0);
                    break;
                }
                case 33: {
                    this.A = this.H(this.f++);
                    this.z = this.H(this.f++);
                    break;
                }
                case 34: {
                    int g = this.G(this.f);
                    this.d(g, this.A);
                    this.d(++g, this.z);
                    this.f += 2;
                    break;
                }
                case 35: {
                    this.k();
                    break;
                }
                case 36: {
                    this.z = A(this.z);
                    break;
                }
                case 37: {
                    this.z = B(this.z);
                    break;
                }
                case 38: {
                    this.z = this.H(this.f++);
                    break;
                }
                case 39: {
                    final short n6 = a.a.a.k.T[this.p | (a.a.a.k.J & 0x1) << 8 | (a.a.a.k.J & 0x2) << 8 | (a.a.a.k.J & 0x10) << 6];
                    this.p = (n6 & 0xFF);
                    a.a.a.k.J = ((a.a.a.k.J & 0x2) | n6 >> 8);
                    break;
                }
                case 40: {
                    this.c((a.a.a.k.J & 0x40) != 0x0);
                    break;
                }
                case 41: {
                    this.x(b(this.g(), this.g()));
                    break;
                }
                case 42: {
                    final int g2 = this.G(this.f);
                    this.A = this.H(g2);
                    this.z = this.H(g2 + 1);
                    this.f += 2;
                    break;
                }
                case 43: {
                    this.n();
                    break;
                }
                case 44: {
                    this.A = A(this.A);
                    break;
                }
                case 45: {
                    this.A = B(this.A);
                    break;
                }
                case 46: {
                    this.A = this.H(this.f++);
                    break;
                }
                case 47: {
                    this.p ^= 0xFF;
                    a.a.a.k.J |= 0x12;
                    break;
                }
                case 48: {
                    this.c((a.a.a.k.J & 0x1) == 0x0);
                    break;
                }
                case 49: {
                    this.g = this.G(this.f);
                    this.f += 2;
                    break;
                }
                case 50: {
                    this.d(this.G(this.f), this.p);
                    this.f += 2;
                    break;
                }
                case 51: {
                    ++this.g;
                    break;
                }
                case 52: {
                    this.c(this.g());
                    break;
                }
                case 53: {
                    this.d(this.g());
                    break;
                }
                case 54: {
                    this.d(this.g(), this.H(this.f++));
                    break;
                }
                case 55: {
                    a.a.a.k.J = ((a.a.a.k.J = ((a.a.a.k.J |= 0x1) & 0xFFFFFFFD)) & 0xFFFFFFEF);
                    break;
                }
                case 56: {
                    this.c((a.a.a.k.J & 0x1) != 0x0);
                    break;
                }
                case 57: {
                    this.x(b(this.g(), this.g));
                    break;
                }
                case 58: {
                    this.p = this.H(this.G(this.f));
                    this.f += 2;
                    break;
                }
                case 59: {
                    --this.g;
                    break;
                }
                case 60: {
                    this.p = A(this.p);
                    break;
                }
                case 61: {
                    this.p = B(this.p);
                    break;
                }
                case 62: {
                    this.p = this.H(this.f++);
                    break;
                }
                case 63: {
                    if ((a.a.a.k.J & 0x1) != 0x0) {
                        a.a.a.k.J = ((a.a.a.k.J &= 0xFFFFFFFE) | 0x10);
                    }
                    else {
                        a.a.a.k.J = ((a.a.a.k.J |= 0x1) & 0xFFFFFFEF);
                    }
                    a.a.a.k.J &= 0xFFFFFFFD;
                }
                case 65: {
                    this.r = this.s;
                    break;
                }
                case 66: {
                    this.r = this.v;
                    break;
                }
                case 67: {
                    this.r = this.w;
                    break;
                }
                case 68: {
                    this.r = this.z;
                    break;
                }
                case 69: {
                    this.r = this.A;
                    break;
                }
                case 70: {
                    this.r = this.H(this.g());
                    break;
                }
                case 71: {
                    this.r = this.p;
                    break;
                }
                case 72: {
                    this.s = this.r;
                }
                case 74: {
                    this.s = this.v;
                    break;
                }
                case 75: {
                    this.s = this.w;
                    break;
                }
                case 76: {
                    this.s = this.z;
                    break;
                }
                case 77: {
                    this.s = this.A;
                    break;
                }
                case 78: {
                    this.s = this.H(this.g());
                    break;
                }
                case 79: {
                    this.s = this.p;
                    break;
                }
                case 80: {
                    this.v = this.r;
                    break;
                }
                case 81: {
                    this.v = this.s;
                }
                case 83: {
                    this.v = this.w;
                    break;
                }
                case 84: {
                    this.v = this.z;
                    break;
                }
                case 85: {
                    this.v = this.A;
                    break;
                }
                case 86: {
                    this.v = this.H(this.g());
                    break;
                }
                case 87: {
                    this.v = this.p;
                    break;
                }
                case 88: {
                    this.w = this.r;
                    break;
                }
                case 89: {
                    this.w = this.s;
                    break;
                }
                case 90: {
                    this.w = this.v;
                }
                case 92: {
                    this.w = this.z;
                    break;
                }
                case 93: {
                    this.w = this.A;
                    break;
                }
                case 94: {
                    this.w = this.H(this.g());
                    break;
                }
                case 95: {
                    this.w = this.p;
                    break;
                }
                case 96: {
                    this.z = this.r;
                    break;
                }
                case 97: {
                    this.z = this.s;
                    break;
                }
                case 98: {
                    this.z = this.v;
                    break;
                }
                case 99: {
                    this.z = this.w;
                }
                case 101: {
                    this.z = this.A;
                    break;
                }
                case 102: {
                    this.z = this.H(this.g());
                    break;
                }
                case 103: {
                    this.z = this.p;
                    break;
                }
                case 104: {
                    this.A = this.r;
                    break;
                }
                case 105: {
                    this.A = this.s;
                    break;
                }
                case 106: {
                    this.A = this.v;
                    break;
                }
                case 107: {
                    this.A = this.w;
                    break;
                }
                case 108: {
                    this.A = this.z;
                }
                case 110: {
                    this.A = this.H(this.g());
                    break;
                }
                case 111: {
                    this.A = this.p;
                    break;
                }
                case 112: {
                    this.d(this.g(), this.r);
                    break;
                }
                case 113: {
                    this.d(this.g(), this.s);
                    break;
                }
                case 114: {
                    this.d(this.g(), this.v);
                    break;
                }
                case 115: {
                    this.d(this.g(), this.w);
                    break;
                }
                case 116: {
                    this.d(this.g(), this.z);
                    break;
                }
                case 117: {
                    this.d(this.g(), this.A);
                    break;
                }
                case 118: {
                    this.k = true;
                    --this.f;
                    break Label_6148;
                }
                case 119: {
                    this.d(this.g(), this.p);
                    break;
                }
                case 120: {
                    this.p = this.r;
                    break;
                }
                case 121: {
                    this.p = this.s;
                    break;
                }
                case 122: {
                    this.p = this.v;
                    break;
                }
                case 123: {
                    this.p = this.w;
                    break;
                }
                case 124: {
                    this.p = this.z;
                    break;
                }
                case 125: {
                    this.p = this.A;
                    break;
                }
                case 126: {
                    this.p = this.H(this.g());
                }
                case 128: {
                    this.s(this.r);
                    break;
                }
                case 129: {
                    this.s(this.s);
                    break;
                }
                case 130: {
                    this.s(this.v);
                    break;
                }
                case 131: {
                    this.s(this.w);
                    break;
                }
                case 132: {
                    this.s(this.z);
                    break;
                }
                case 133: {
                    this.s(this.A);
                    break;
                }
                case 134: {
                    this.s(this.H(this.g()));
                    break;
                }
                case 135: {
                    this.s(this.p);
                    break;
                }
                case 136: {
                    this.t(this.r);
                    break;
                }
                case 137: {
                    this.t(this.s);
                    break;
                }
                case 138: {
                    this.t(this.v);
                    break;
                }
                case 139: {
                    this.t(this.w);
                    break;
                }
                case 140: {
                    this.t(this.z);
                    break;
                }
                case 141: {
                    this.t(this.A);
                    break;
                }
                case 142: {
                    this.t(this.H(this.g()));
                    break;
                }
                case 143: {
                    this.t(this.p);
                    break;
                }
                case 144: {
                    this.u(this.r);
                    break;
                }
                case 145: {
                    this.u(this.s);
                    break;
                }
                case 146: {
                    this.u(this.v);
                    break;
                }
                case 147: {
                    this.u(this.w);
                    break;
                }
                case 148: {
                    this.u(this.z);
                    break;
                }
                case 149: {
                    this.u(this.A);
                    break;
                }
                case 150: {
                    this.u(this.H(this.g()));
                    break;
                }
                case 151: {
                    this.u(this.p);
                    break;
                }
                case 152: {
                    this.v(this.r);
                    break;
                }
                case 153: {
                    this.v(this.s);
                    break;
                }
                case 154: {
                    this.v(this.v);
                    break;
                }
                case 155: {
                    this.v(this.w);
                    break;
                }
                case 156: {
                    this.v(this.z);
                    break;
                }
                case 157: {
                    this.v(this.A);
                    break;
                }
                case 158: {
                    this.v(this.H(this.g()));
                    break;
                }
                case 159: {
                    this.v(this.p);
                    break;
                }
                case 160: {
                    final short[] v = a.a.a.k.V;
                    final int p2 = this.p & this.r;
                    this.p = p2;
                    a.a.a.k.J = (v[p2] | 0x10);
                    break;
                }
                case 161: {
                    final short[] v2 = a.a.a.k.V;
                    final int p3 = this.p & this.s;
                    this.p = p3;
                    a.a.a.k.J = (v2[p3] | 0x10);
                    break;
                }
                case 162: {
                    final short[] v3 = a.a.a.k.V;
                    final int p4 = this.p & this.v;
                    this.p = p4;
                    a.a.a.k.J = (v3[p4] | 0x10);
                    break;
                }
                case 163: {
                    final short[] v4 = a.a.a.k.V;
                    final int p5 = this.p & this.w;
                    this.p = p5;
                    a.a.a.k.J = (v4[p5] | 0x10);
                    break;
                }
                case 164: {
                    final short[] v5 = a.a.a.k.V;
                    final int p6 = this.p & this.z;
                    this.p = p6;
                    a.a.a.k.J = (v5[p6] | 0x10);
                    break;
                }
                case 165: {
                    final short[] v6 = a.a.a.k.V;
                    final int p7 = this.p & this.A;
                    this.p = p7;
                    a.a.a.k.J = (v6[p7] | 0x10);
                    break;
                }
                case 166: {
                    final short[] v7 = a.a.a.k.V;
                    final int p8 = this.p & this.H(this.g());
                    this.p = p8;
                    a.a.a.k.J = (v7[p8] | 0x10);
                    break;
                }
                case 167: {
                    a.a.a.k.J = (a.a.a.k.V[this.p] | 0x10);
                    break;
                }
                case 168: {
                    final short[] v8 = a.a.a.k.V;
                    final int p9 = this.p ^ this.r;
                    this.p = p9;
                    a.a.a.k.J = v8[p9];
                    break;
                }
                case 169: {
                    final short[] v9 = a.a.a.k.V;
                    final int p10 = this.p ^ this.s;
                    this.p = p10;
                    a.a.a.k.J = v9[p10];
                    break;
                }
                case 170: {
                    final short[] v10 = a.a.a.k.V;
                    final int p11 = this.p ^ this.v;
                    this.p = p11;
                    a.a.a.k.J = v10[p11];
                    break;
                }
                case 171: {
                    final short[] v11 = a.a.a.k.V;
                    final int p12 = this.p ^ this.w;
                    this.p = p12;
                    a.a.a.k.J = v11[p12];
                    break;
                }
                case 172: {
                    final short[] v12 = a.a.a.k.V;
                    final int p13 = this.p ^ this.z;
                    this.p = p13;
                    a.a.a.k.J = v12[p13];
                    break;
                }
                case 173: {
                    final short[] v13 = a.a.a.k.V;
                    final int p14 = this.p ^ this.A;
                    this.p = p14;
                    a.a.a.k.J = v13[p14];
                    break;
                }
                case 174: {
                    final short[] v14 = a.a.a.k.V;
                    final int p15 = this.p ^ this.H(this.g());
                    this.p = p15;
                    a.a.a.k.J = v14[p15];
                    break;
                }
                case 175: {
                    final short[] v15 = a.a.a.k.V;
                    final int p16 = 0;
                    this.p = p16;
                    a.a.a.k.J = v15[p16];
                    break;
                }
                case 176: {
                    final short[] v16 = a.a.a.k.V;
                    final int p17 = this.p | this.r;
                    this.p = p17;
                    a.a.a.k.J = v16[p17];
                    break;
                }
                case 177: {
                    final short[] v17 = a.a.a.k.V;
                    final int p18 = this.p | this.s;
                    this.p = p18;
                    a.a.a.k.J = v17[p18];
                    break;
                }
                case 178: {
                    final short[] v18 = a.a.a.k.V;
                    final int p19 = this.p | this.v;
                    this.p = p19;
                    a.a.a.k.J = v18[p19];
                    break;
                }
                case 179: {
                    final short[] v19 = a.a.a.k.V;
                    final int p20 = this.p | this.w;
                    this.p = p20;
                    a.a.a.k.J = v19[p20];
                    break;
                }
                case 180: {
                    final short[] v20 = a.a.a.k.V;
                    final int p21 = this.p | this.z;
                    this.p = p21;
                    a.a.a.k.J = v20[p21];
                    break;
                }
                case 181: {
                    final short[] v21 = a.a.a.k.V;
                    final int p22 = this.p | this.A;
                    this.p = p22;
                    a.a.a.k.J = v21[p22];
                    break;
                }
                case 182: {
                    final short[] v22 = a.a.a.k.V;
                    final int p23 = this.p | this.H(this.g());
                    this.p = p23;
                    a.a.a.k.J = v22[p23];
                    break;
                }
                case 183: {
                    a.a.a.k.J = a.a.a.k.V[this.p];
                    break;
                }
                case 184: {
                    this.w(this.r);
                    break;
                }
                case 185: {
                    this.w(this.s);
                    break;
                }
                case 186: {
                    this.w(this.v);
                    break;
                }
                case 187: {
                    this.w(this.w);
                    break;
                }
                case 188: {
                    this.w(this.z);
                    break;
                }
                case 189: {
                    this.w(this.A);
                    break;
                }
                case 190: {
                    this.w(this.H(this.g()));
                    break;
                }
                case 191: {
                    this.w(this.p);
                    break;
                }
                case 192: {
                    this.e((a.a.a.k.J & 0x40) == 0x0);
                    break;
                }
                case 193: {
                    final int g3 = this.G(this.g);
                    this.r = g3 >> 8;
                    this.s = (g3 & 0xFF);
                    this.g += 2;
                    break;
                }
                case 194: {
                    this.b((a.a.a.k.J & 0x40) == 0x0);
                    break;
                }
                case 195: {
                    this.f = this.G(this.f);
                    break;
                }
                case 196: {
                    this.d((a.a.a.k.J & 0x40) == 0x0);
                    break;
                }
                case 197: {
                    this.a(this.r, this.s);
                    break;
                }
                case 198: {
                    this.s(this.H(this.f++));
                    break;
                }
                case 199: {
                    this.b(this.f);
                    this.f = 0;
                    break;
                }
                case 200: {
                    this.e((a.a.a.k.J & 0x40) != 0x0);
                    break;
                }
                case 201: {
                    this.f = this.G(this.g);
                    this.g += 2;
                    break;
                }
                case 202: {
                    this.b((a.a.a.k.J & 0x40) != 0x0);
                    break;
                }
                case 203: {
                    this.e(this.H(this.f++));
                    break;
                }
                case 204: {
                    this.d((a.a.a.k.J & 0x40) != 0x0);
                    break;
                }
                case 205: {
                    this.b(this.f + 2);
                    this.f = this.G(this.f);
                    break;
                }
                case 206: {
                    this.t(this.H(this.f++));
                    break;
                }
                case 207: {
                    this.b(this.f);
                    this.f = 8;
                    break;
                }
                case 208: {
                    this.e((a.a.a.k.J & 0x1) == 0x0);
                    break;
                }
                case 209: {
                    final int g4 = this.G(this.g);
                    this.v = g4 >> 8;
                    this.w = (g4 & 0xFF);
                    this.g += 2;
                    break;
                }
                case 210: {
                    this.b((a.a.a.k.J & 0x1) == 0x0);
                    break;
                }
                case 211: {
                    this.c(this.H(this.f++), this.p);
                    break;
                }
                case 212: {
                    this.d((a.a.a.k.J & 0x1) == 0x0);
                    break;
                }
                case 213: {
                    this.a(this.v, this.w);
                    break;
                }
                case 214: {
                    this.u(this.H(this.f++));
                    break;
                }
                case 215: {
                    this.b(this.f);
                    this.f = 16;
                    break;
                }
                case 216: {
                    this.e((a.a.a.k.J & 0x1) != 0x0);
                    break;
                }
                case 217: {
                    final int r = this.r;
                    this.r = this.t;
                    this.t = r;
                    final int s = this.s;
                    this.s = this.u;
                    this.u = s;
                    final int v23 = this.v;
                    this.v = this.x;
                    this.x = v23;
                    final int w = this.w;
                    this.w = this.y;
                    this.y = w;
                    final int z = this.z;
                    this.z = this.B;
                    this.B = z;
                    final int a = this.A;
                    this.A = this.C;
                    this.C = a;
                    break;
                }
                case 218: {
                    this.b((a.a.a.k.J & 0x1) != 0x0);
                    break;
                }
                case 219: {
                    this.p = this.F(this.H(this.f++));
                    break;
                }
                case 220: {
                    this.d((a.a.a.k.J & 0x1) != 0x0);
                    break;
                }
                case 221: {
                    this.o(this.H(this.f++));
                    break;
                }
                case 222: {
                    this.v(this.H(this.f++));
                    break;
                }
                case 223: {
                    this.b(this.f);
                    this.f = 24;
                    break;
                }
                case 224: {
                    this.e((a.a.a.k.J & 0x4) == 0x0);
                    break;
                }
                case 225: {
                    this.x(this.G(this.g));
                    this.g += 2;
                    break;
                }
                case 226: {
                    this.b((a.a.a.k.J & 0x4) == 0x0);
                    break;
                }
                case 227: {
                    final int z2 = this.z;
                    this.z = this.H(this.g + 1);
                    this.d(this.g + 1, z2);
                    final int a2 = this.A;
                    this.A = this.H(this.g);
                    this.d(this.g, a2);
                    break;
                }
                case 228: {
                    this.d((a.a.a.k.J & 0x4) == 0x0);
                    break;
                }
                case 229: {
                    this.a(this.z, this.A);
                    break;
                }
                case 230: {
                    final short[] v24 = a.a.a.k.V;
                    final int p24 = this.p & this.H(this.f++);
                    this.p = p24;
                    a.a.a.k.J = (v24[p24] | 0x10);
                    break;
                }
                case 231: {
                    this.b(this.f);
                    this.f = 32;
                    break;
                }
                case 232: {
                    this.e((a.a.a.k.J & 0x4) != 0x0);
                    break;
                }
                case 233: {
                    this.f = this.g();
                    break;
                }
                case 234: {
                    this.b((a.a.a.k.J & 0x4) != 0x0);
                    break;
                }
                case 235: {
                    final int v25 = this.v;
                    this.v = this.z;
                    this.z = v25;
                    final int w2 = this.w;
                    this.w = this.A;
                    this.A = w2;
                    break;
                }
                case 236: {
                    this.d((a.a.a.k.J & 0x4) != 0x0);
                    break;
                }
                case 237: {
                    this.r(this.H(this.f));
                    break;
                }
                case 238: {
                    final short[] v26 = a.a.a.k.V;
                    final int p25 = this.p ^ this.H(this.f++);
                    this.p = p25;
                    a.a.a.k.J = v26[p25];
                    break;
                }
                case 239: {
                    this.b(this.f);
                    this.f = 40;
                    break;
                }
                case 240: {
                    this.e((a.a.a.k.J & 0x80) == 0x0);
                    break;
                }
                case 241: {
                    a.a.a.k.J = this.H(this.g++);
                    this.p = this.H(this.g++);
                    break;
                }
                case 242: {
                    this.b((a.a.a.k.J & 0x80) == 0x0);
                    break;
                }
                case 243: {
                    final boolean b2 = false;
                    this.j = b2;
                    this.i = b2;
                    this.l = true;
                    break;
                }
                case 244: {
                    this.d((a.a.a.k.J & 0x80) == 0x0);
                    break;
                }
                case 245: {
                    this.a(this.p, a.a.a.k.J);
                    break;
                }
                case 246: {
                    final short[] v27 = a.a.a.k.V;
                    final int p26 = this.p | this.H(this.f++);
                    this.p = p26;
                    a.a.a.k.J = v27[p26];
                    break;
                }
                case 247: {
                    this.b(this.f);
                    this.f = 48;
                    break;
                }
                case 248: {
                    this.e((a.a.a.k.J & 0x80) != 0x0);
                    break;
                }
                case 249: {
                    this.g = this.g();
                    break;
                }
                case 250: {
                    this.b((a.a.a.k.J & 0x80) != 0x0);
                    break;
                }
                case 251: {
                    final boolean i = true;
                    this.l = i;
                    this.j = i;
                    this.i = i;
                    break;
                }
                case 252: {
                    this.d((a.a.a.k.J & 0x80) != 0x0);
                    break;
                }
                case 253: {
                    this.p(this.H(this.f++));
                    break;
                }
                case 254: {
                    this.w(this.H(this.f++));
                    break;
                }
                case 255: {
                    this.b(this.f);
                    this.f = 56;
                    break;
                }
            }
            a.a.a.k.M = l - a.a.a.k.L;
            this.b.b(a.a.a.k.M);
        }
        return (char)this.f;
    }
    
    public final char c() {
        return (char)this.f;
    }
    
    public static void d() {
        k.o = true;
    }
    
    public static void a(final boolean m) {
        k.m = m;
    }
    
    private final void b(final boolean b) {
        if (b) {
            this.f = this.G(this.f);
            return;
        }
        this.f += 2;
    }
    
    private final void c(final boolean b) {
        if (b) {
            this.f += this.p() + 1;
            a.a.a.k.L -= 5;
            return;
        }
        ++this.f;
    }
    
    private final void d(final boolean b) {
        if (b) {
            this.b(this.f + 2);
            this.f = this.G(this.f);
            a.a.a.k.L -= 7;
            return;
        }
        this.f += 2;
    }
    
    private final void e(final boolean b) {
        if (b) {
            this.f = this.G(this.g);
            this.g += 2;
            a.a.a.k.L -= 6;
        }
    }
    
    private final void b(final int n) {
        this.d(--this.g, n >> 8);
        this.d(--this.g, n & 0xFF);
    }
    
    private final void a(final int n, final int n2) {
        this.d(--this.g, n);
        this.d(--this.g, n2);
    }
    
    private final void c(final int n) {
        this.d(n, A(this.H(n)));
    }
    
    private final void d(final int n) {
        this.d(n, B(this.H(n)));
    }
    
    private final void e(final int n) {
        if (a.a.a.k.d) {
            this.o();
        }
        a.a.a.k.L -= a.a.a.k.P[n];
        switch (n) {
            case 0: {
                this.r = f(this.r);
            }
            case 1: {
                this.s = f(this.s);
            }
            case 2: {
                this.v = f(this.v);
            }
            case 3: {
                this.w = f(this.w);
            }
            case 4: {
                this.z = f(this.z);
            }
            case 5: {
                this.A = f(this.A);
            }
            case 6: {
                this.d(this.g(), f(this.H(this.g())));
            }
            case 7: {
                this.p = f(this.p);
            }
            case 8: {
                this.r = g(this.r);
            }
            case 9: {
                this.s = g(this.s);
            }
            case 10: {
                this.v = g(this.v);
            }
            case 11: {
                this.w = g(this.w);
            }
            case 12: {
                this.z = g(this.z);
            }
            case 13: {
                this.A = g(this.A);
            }
            case 14: {
                this.d(this.g(), g(this.H(this.g())));
            }
            case 15: {
                this.p = g(this.p);
            }
            case 16: {
                this.r = h(this.r);
            }
            case 17: {
                this.s = h(this.s);
            }
            case 18: {
                this.v = h(this.v);
            }
            case 19: {
                this.w = h(this.w);
            }
            case 20: {
                this.z = h(this.z);
            }
            case 21: {
                this.A = h(this.A);
            }
            case 22: {
                this.d(this.g(), h(this.H(this.g())));
            }
            case 23: {
                this.p = h(this.p);
            }
            case 24: {
                this.r = i(this.r);
            }
            case 25: {
                this.s = i(this.s);
            }
            case 26: {
                this.v = i(this.v);
            }
            case 27: {
                this.w = i(this.w);
            }
            case 28: {
                this.z = i(this.z);
            }
            case 29: {
                this.A = i(this.A);
            }
            case 30: {
                this.d(this.g(), i(this.H(this.g())));
            }
            case 31: {
                this.p = i(this.p);
            }
            case 32: {
                this.r = j(this.r);
            }
            case 33: {
                this.s = j(this.s);
            }
            case 34: {
                this.v = j(this.v);
            }
            case 35: {
                this.w = j(this.w);
            }
            case 36: {
                this.z = j(this.z);
            }
            case 37: {
                this.A = j(this.A);
            }
            case 38: {
                this.d(this.g(), j(this.H(this.g())));
            }
            case 39: {
                this.p = j(this.p);
            }
            case 40: {
                this.r = l(this.r);
            }
            case 41: {
                this.s = l(this.s);
            }
            case 42: {
                this.v = l(this.v);
            }
            case 43: {
                this.w = l(this.w);
            }
            case 44: {
                this.z = l(this.z);
            }
            case 45: {
                this.A = l(this.A);
            }
            case 46: {
                this.d(this.g(), l(this.H(this.g())));
            }
            case 47: {
                this.p = l(this.p);
            }
            case 48: {
                this.r = k(this.r);
            }
            case 49: {
                this.s = k(this.s);
            }
            case 50: {
                this.v = k(this.v);
            }
            case 51: {
                this.w = k(this.w);
            }
            case 52: {
                this.z = k(this.z);
            }
            case 53: {
                this.A = k(this.A);
            }
            case 54: {
                this.d(this.g(), k(this.H(this.g())));
            }
            case 55: {
                this.p = k(this.p);
            }
            case 56: {
                this.r = m(this.r);
            }
            case 57: {
                this.s = m(this.s);
            }
            case 58: {
                this.v = m(this.v);
            }
            case 59: {
                this.w = m(this.w);
            }
            case 60: {
                this.z = m(this.z);
            }
            case 61: {
                this.A = m(this.A);
            }
            case 62: {
                this.d(this.g(), m(this.H(this.g())));
            }
            case 63: {
                this.p = m(this.p);
            }
            case 64: {
                n(this.r & 0x1);
            }
            case 65: {
                n(this.s & 0x1);
            }
            case 66: {
                n(this.v & 0x1);
            }
            case 67: {
                n(this.w & 0x1);
            }
            case 68: {
                n(this.z & 0x1);
            }
            case 69: {
                n(this.A & 0x1);
            }
            case 70: {
                n(this.H(this.g()) & 0x1);
            }
            case 71: {
                n(this.p & 0x1);
            }
            case 72: {
                n(this.r & 0x2);
            }
            case 73: {
                n(this.s & 0x2);
            }
            case 74: {
                n(this.v & 0x2);
            }
            case 75: {
                n(this.w & 0x2);
            }
            case 76: {
                n(this.z & 0x2);
            }
            case 77: {
                n(this.A & 0x2);
            }
            case 78: {
                n(this.H(this.g()) & 0x2);
            }
            case 79: {
                n(this.p & 0x2);
            }
            case 80: {
                n(this.r & 0x4);
            }
            case 81: {
                n(this.s & 0x4);
            }
            case 82: {
                n(this.v & 0x4);
            }
            case 83: {
                n(this.w & 0x4);
            }
            case 84: {
                n(this.z & 0x4);
            }
            case 85: {
                n(this.A & 0x4);
            }
            case 86: {
                n(this.H(this.g()) & 0x4);
            }
            case 87: {
                n(this.p & 0x4);
            }
            case 88: {
                n(this.r & 0x8);
            }
            case 89: {
                n(this.s & 0x8);
            }
            case 90: {
                n(this.v & 0x8);
            }
            case 91: {
                n(this.w & 0x8);
            }
            case 92: {
                n(this.z & 0x8);
            }
            case 93: {
                n(this.A & 0x8);
            }
            case 94: {
                n(this.H(this.g()) & 0x8);
            }
            case 95: {
                n(this.p & 0x8);
            }
            case 96: {
                n(this.r & 0x10);
            }
            case 97: {
                n(this.s & 0x10);
            }
            case 98: {
                n(this.v & 0x10);
            }
            case 99: {
                n(this.w & 0x10);
            }
            case 100: {
                n(this.z & 0x10);
            }
            case 101: {
                n(this.A & 0x10);
            }
            case 102: {
                n(this.H(this.g()) & 0x10);
            }
            case 103: {
                n(this.p & 0x10);
            }
            case 104: {
                n(this.r & 0x20);
            }
            case 105: {
                n(this.s & 0x20);
            }
            case 106: {
                n(this.v & 0x20);
            }
            case 107: {
                n(this.w & 0x20);
            }
            case 108: {
                n(this.z & 0x20);
            }
            case 109: {
                n(this.A & 0x20);
            }
            case 110: {
                n(this.H(this.g()) & 0x20);
            }
            case 111: {
                n(this.p & 0x20);
            }
            case 112: {
                n(this.r & 0x40);
            }
            case 113: {
                n(this.s & 0x40);
            }
            case 114: {
                n(this.v & 0x40);
            }
            case 115: {
                n(this.w & 0x40);
            }
            case 116: {
                n(this.z & 0x40);
            }
            case 117: {
                n(this.A & 0x40);
            }
            case 118: {
                n(this.H(this.g()) & 0x40);
            }
            case 119: {
                n(this.p & 0x40);
            }
            case 120: {
                n(this.r & 0x80);
            }
            case 121: {
                n(this.s & 0x80);
            }
            case 122: {
                n(this.v & 0x80);
            }
            case 123: {
                n(this.w & 0x80);
            }
            case 124: {
                n(this.z & 0x80);
            }
            case 125: {
                n(this.A & 0x80);
            }
            case 126: {
                n(this.H(this.g()) & 0x80);
            }
            case 127: {
                n(this.p & 0x80);
            }
            case 128: {
                this.r &= 0xFFFFFFFE;
            }
            case 129: {
                this.s &= 0xFFFFFFFE;
            }
            case 130: {
                this.v &= 0xFFFFFFFE;
            }
            case 131: {
                this.w &= 0xFFFFFFFE;
            }
            case 132: {
                this.z &= 0xFFFFFFFE;
            }
            case 133: {
                this.A &= 0xFFFFFFFE;
            }
            case 134: {
                this.d(this.g(), this.H(this.g()) & 0xFFFFFFFE);
            }
            case 135: {
                this.p &= 0xFFFFFFFE;
            }
            case 136: {
                this.r &= 0xFFFFFFFD;
            }
            case 137: {
                this.s &= 0xFFFFFFFD;
            }
            case 138: {
                this.v &= 0xFFFFFFFD;
            }
            case 139: {
                this.w &= 0xFFFFFFFD;
            }
            case 140: {
                this.z &= 0xFFFFFFFD;
            }
            case 141: {
                this.A &= 0xFFFFFFFD;
            }
            case 142: {
                this.d(this.g(), this.H(this.g()) & 0xFFFFFFFD);
            }
            case 143: {
                this.p &= 0xFFFFFFFD;
            }
            case 144: {
                this.r &= 0xFFFFFFFB;
            }
            case 145: {
                this.s &= 0xFFFFFFFB;
            }
            case 146: {
                this.v &= 0xFFFFFFFB;
            }
            case 147: {
                this.w &= 0xFFFFFFFB;
            }
            case 148: {
                this.z &= 0xFFFFFFFB;
            }
            case 149: {
                this.A &= 0xFFFFFFFB;
            }
            case 150: {
                this.d(this.g(), this.H(this.g()) & 0xFFFFFFFB);
            }
            case 151: {
                this.p &= 0xFFFFFFFB;
            }
            case 152: {
                this.r &= 0xFFFFFFF7;
            }
            case 153: {
                this.s &= 0xFFFFFFF7;
            }
            case 154: {
                this.v &= 0xFFFFFFF7;
            }
            case 155: {
                this.w &= 0xFFFFFFF7;
            }
            case 156: {
                this.z &= 0xFFFFFFF7;
            }
            case 157: {
                this.A &= 0xFFFFFFF7;
            }
            case 158: {
                this.d(this.g(), this.H(this.g()) & 0xFFFFFFF7);
            }
            case 159: {
                this.p &= 0xFFFFFFF7;
            }
            case 160: {
                this.r &= 0xFFFFFFEF;
            }
            case 161: {
                this.s &= 0xFFFFFFEF;
            }
            case 162: {
                this.v &= 0xFFFFFFEF;
            }
            case 163: {
                this.w &= 0xFFFFFFEF;
            }
            case 164: {
                this.z &= 0xFFFFFFEF;
            }
            case 165: {
                this.A &= 0xFFFFFFEF;
            }
            case 166: {
                this.d(this.g(), this.H(this.g()) & 0xFFFFFFEF);
            }
            case 167: {
                this.p &= 0xFFFFFFEF;
            }
            case 168: {
                this.r &= 0xFFFFFFDF;
            }
            case 169: {
                this.s &= 0xFFFFFFDF;
            }
            case 170: {
                this.v &= 0xFFFFFFDF;
            }
            case 171: {
                this.w &= 0xFFFFFFDF;
            }
            case 172: {
                this.z &= 0xFFFFFFDF;
            }
            case 173: {
                this.A &= 0xFFFFFFDF;
            }
            case 174: {
                this.d(this.g(), this.H(this.g()) & 0xFFFFFFDF);
            }
            case 175: {
                this.p &= 0xFFFFFFDF;
            }
            case 176: {
                this.r &= 0xFFFFFFBF;
            }
            case 177: {
                this.s &= 0xFFFFFFBF;
            }
            case 178: {
                this.v &= 0xFFFFFFBF;
            }
            case 179: {
                this.w &= 0xFFFFFFBF;
            }
            case 180: {
                this.z &= 0xFFFFFFBF;
            }
            case 181: {
                this.A &= 0xFFFFFFBF;
            }
            case 182: {
                this.d(this.g(), this.H(this.g()) & 0xFFFFFFBF);
            }
            case 183: {
                this.p &= 0xFFFFFFBF;
            }
            case 184: {
                this.r &= 0xFFFFFF7F;
            }
            case 185: {
                this.s &= 0xFFFFFF7F;
            }
            case 186: {
                this.v &= 0xFFFFFF7F;
            }
            case 187: {
                this.w &= 0xFFFFFF7F;
            }
            case 188: {
                this.z &= 0xFFFFFF7F;
            }
            case 189: {
                this.A &= 0xFFFFFF7F;
            }
            case 190: {
                this.d(this.g(), this.H(this.g()) & 0xFFFFFF7F);
            }
            case 191: {
                this.p &= 0xFFFFFF7F;
            }
            case 192: {
                this.r |= 0x1;
            }
            case 193: {
                this.s |= 0x1;
            }
            case 194: {
                this.v |= 0x1;
            }
            case 195: {
                this.w |= 0x1;
            }
            case 196: {
                this.z |= 0x1;
            }
            case 197: {
                this.A |= 0x1;
            }
            case 198: {
                this.d(this.g(), this.H(this.g()) | 0x1);
            }
            case 199: {
                this.p |= 0x1;
            }
            case 200: {
                this.r |= 0x2;
            }
            case 201: {
                this.s |= 0x2;
            }
            case 202: {
                this.v |= 0x2;
            }
            case 203: {
                this.w |= 0x2;
            }
            case 204: {
                this.z |= 0x2;
            }
            case 205: {
                this.A |= 0x2;
            }
            case 206: {
                this.d(this.g(), this.H(this.g()) | 0x2);
            }
            case 207: {
                this.p |= 0x2;
            }
            case 208: {
                this.r |= 0x4;
            }
            case 209: {
                this.s |= 0x4;
            }
            case 210: {
                this.v |= 0x4;
            }
            case 211: {
                this.w |= 0x4;
            }
            case 212: {
                this.z |= 0x4;
            }
            case 213: {
                this.A |= 0x4;
            }
            case 214: {
                this.d(this.g(), this.H(this.g()) | 0x4);
            }
            case 215: {
                this.p |= 0x4;
            }
            case 216: {
                this.r |= 0x8;
            }
            case 217: {
                this.s |= 0x8;
            }
            case 218: {
                this.v |= 0x8;
            }
            case 219: {
                this.w |= 0x8;
            }
            case 220: {
                this.z |= 0x8;
            }
            case 221: {
                this.A |= 0x8;
            }
            case 222: {
                this.d(this.g(), this.H(this.g()) | 0x8);
            }
            case 223: {
                this.p |= 0x8;
            }
            case 224: {
                this.r |= 0x10;
            }
            case 225: {
                this.s |= 0x10;
            }
            case 226: {
                this.v |= 0x10;
            }
            case 227: {
                this.w |= 0x10;
            }
            case 228: {
                this.z |= 0x10;
            }
            case 229: {
                this.A |= 0x10;
            }
            case 230: {
                this.d(this.g(), this.H(this.g()) | 0x10);
            }
            case 231: {
                this.p |= 0x10;
            }
            case 232: {
                this.r |= 0x20;
            }
            case 233: {
                this.s |= 0x20;
            }
            case 234: {
                this.v |= 0x20;
            }
            case 235: {
                this.w |= 0x20;
            }
            case 236: {
                this.z |= 0x20;
            }
            case 237: {
                this.A |= 0x20;
            }
            case 238: {
                this.d(this.g(), this.H(this.g()) | 0x20);
            }
            case 239: {
                this.p |= 0x20;
            }
            case 240: {
                this.r |= 0x40;
            }
            case 241: {
                this.s |= 0x40;
            }
            case 242: {
                this.v |= 0x40;
            }
            case 243: {
                this.w |= 0x40;
            }
            case 244: {
                this.z |= 0x40;
            }
            case 245: {
                this.A |= 0x40;
            }
            case 246: {
                this.d(this.g(), this.H(this.g()) | 0x40);
            }
            case 247: {
                this.p |= 0x40;
            }
            case 248: {
                this.r |= 0x80;
            }
            case 249: {
                this.s |= 0x80;
            }
            case 250: {
                this.v |= 0x80;
            }
            case 251: {
                this.w |= 0x80;
            }
            case 252: {
                this.z |= 0x80;
            }
            case 253: {
                this.A |= 0x80;
            }
            case 254: {
                this.d(this.g(), this.H(this.g()) | 0x80);
            }
            case 255: {
                this.p |= 0x80;
                break;
            }
        }
    }
    
    private static int f(int n) {
        final int n2 = (n & 0x80) >> 7;
        n = ((n << 1 | n >> 7) & 0xFF);
        k.J = (n2 | k.V[n]);
        return n;
    }
    
    private static int g(int n) {
        final int n2 = n & 0x1;
        n = ((n >> 1 | n << 7) & 0xFF);
        k.J = (n2 | k.V[n]);
        return n;
    }
    
    private static int h(int n) {
        final int n2 = (n & 0x80) >> 7;
        n = ((n << 1 | (k.J & 0x1)) & 0xFF);
        k.J = (n2 | k.V[n]);
        return n;
    }
    
    private static int i(int n) {
        final int n2 = n & 0x1;
        n = ((n >> 1 | k.J << 7) & 0xFF);
        k.J = (n2 | k.V[n]);
        return n;
    }
    
    private static int j(int n) {
        final int n2 = (n & 0x80) >> 7;
        n = (n << 1 & 0xFF);
        k.J = (n2 | k.V[n]);
        return n;
    }
    
    private static int k(int n) {
        final int n2 = (n & 0x80) >> 7;
        n = ((n << 1 | 0x1) & 0xFF);
        k.J = (n2 | k.V[n]);
        return n;
    }
    
    private static int l(int n) {
        final int n2 = n & 0x1;
        n = (n >> 1 | (n & 0x80));
        k.J = (n2 | k.V[n]);
        return n;
    }
    
    private static int m(int n) {
        final int n2 = n & 0x1;
        n = (n >> 1 & 0xFF);
        k.J = (n2 | k.V[n]);
        return n;
    }
    
    private static void n(final int n) {
        k.J = ((k.J & 0x1) | k.aa[n]);
    }
    
    private final void o(int n) {
        a.a.a.k.L -= a.a.a.k.Q[n];
        if (a.a.a.k.d) {
            this.o();
        }
        switch (n) {
            case 9: {
                this.y(b(this.h(), this.e()));
            }
            case 25: {
                this.y(b(this.h(), this.f()));
            }
            case 33: {
                this.y(this.G(this.f));
                final k k = this;
                k.f += 2;
            }
            case 34: {
                n = this.G(this.f);
                this.d(n++, this.D);
                this.d(n, this.E);
                final k i = this;
                i.f += 2;
            }
            case 35: {
                final k j = this = this;
                j.D = (this.D + 1 & 0xFF);
                if (this.D == 0) {
                    this.E = (this.E + 1 & 0xFF);
                }
            }
            case 36: {
                this.E = A(this.E);
            }
            case 37: {
                this.E = B(this.E);
            }
            case 38: {
                this.E = this.H(this.f++);
            }
            case 41: {
                this.y(b(this.h(), this.h()));
            }
            case 42: {
                n = this.G(this.f);
                this.D = this.H(n);
                this.E = this.H(++n);
                final k l = this;
                l.f += 2;
            }
            case 43: {
                final k m = this = this;
                m.D = (this.D - 1 & 0xFF);
                if (this.D == 255) {
                    this.E = (this.E - 1 & 0xFF);
                }
            }
            case 44: {
                this.D = A(this.D);
            }
            case 45: {
                this.D = B(this.D);
            }
            case 46: {
                this.D = this.H(this.f++);
            }
            case 52: {
                this.c(this.h() + this.p());
                final k k2 = this;
                ++k2.f;
            }
            case 53: {
                this.d(this.h() + this.p());
                final k k3 = this;
                ++k3.f;
            }
            case 54: {
                this.d(this.h() + this.p(), this.H(++this.f));
                final k k4 = this;
                ++k4.f;
            }
            case 57: {
                this.y(b(this.h(), this.g));
            }
            case 68: {
                this.r = this.E;
            }
            case 69: {
                this.r = this.D;
            }
            case 70: {
                this.r = this.H(this.h() + this.p());
                final k k5 = this;
                ++k5.f;
            }
            case 76: {
                this.s = this.E;
            }
            case 77: {
                this.s = this.D;
            }
            case 78: {
                this.s = this.H(this.h() + this.p());
                final k k6 = this;
                ++k6.f;
            }
            case 84: {
                this.v = this.E;
            }
            case 85: {
                this.v = this.D;
            }
            case 86: {
                this.v = this.H(this.h() + this.p());
                final k k7 = this;
                ++k7.f;
            }
            case 92: {
                this.w = this.E;
            }
            case 93: {
                this.w = this.D;
            }
            case 94: {
                this.w = this.H(this.h() + this.p());
                final k k8 = this;
                ++k8.f;
            }
            case 96: {
                this.E = this.r;
            }
            case 97: {
                this.E = this.s;
            }
            case 98: {
                this.E = this.v;
            }
            case 99: {
                this.E = this.w;
            }
            case 100: {}
            case 101: {
                this.E = this.D;
            }
            case 102: {
                this.z = this.H(this.h() + this.p());
                final k k9 = this;
                ++k9.f;
            }
            case 103: {
                this.E = this.p;
            }
            case 104: {
                this.D = this.r;
            }
            case 105: {
                this.D = this.s;
            }
            case 106: {
                this.D = this.v;
            }
            case 107: {
                this.D = this.w;
            }
            case 108: {
                this.D = this.E;
            }
            case 109: {}
            case 110: {
                this.A = this.H(this.h() + this.p());
                final k k10 = this;
                ++k10.f;
            }
            case 111: {
                this.D = this.p;
            }
            case 112: {
                this.d(this.h() + this.p(), this.r);
                final k k11 = this;
                ++k11.f;
            }
            case 113: {
                this.d(this.h() + this.p(), this.s);
                final k k12 = this;
                ++k12.f;
            }
            case 114: {
                this.d(this.h() + this.p(), this.v);
                final k k13 = this;
                ++k13.f;
            }
            case 115: {
                this.d(this.h() + this.p(), this.w);
                final k k14 = this;
                ++k14.f;
            }
            case 116: {
                this.d(this.h() + this.p(), this.z);
                final k k15 = this;
                ++k15.f;
            }
            case 117: {
                this.d(this.h() + this.p(), this.A);
                final k k16 = this;
                ++k16.f;
            }
            case 119: {
                this.d(this.h() + this.p(), this.p);
                final k k17 = this;
                ++k17.f;
            }
            case 124: {
                this.p = this.E;
            }
            case 125: {
                this.p = this.D;
            }
            case 126: {
                this.p = this.H(this.h() + this.p());
                final k k18 = this;
                ++k18.f;
            }
            case 132: {
                this.s(this.E);
            }
            case 133: {
                this.s(this.D);
            }
            case 134: {
                this.s(this.H(this.h() + this.p()));
                final k k19 = this;
                ++k19.f;
            }
            case 140: {
                this.t(this.E);
            }
            case 141: {
                this.t(this.D);
            }
            case 142: {
                this.t(this.H(this.h() + this.p()));
                final k k20 = this;
                ++k20.f;
            }
            case 148: {
                this.u(this.E);
            }
            case 149: {
                this.u(this.D);
            }
            case 150: {
                this.u(this.H(this.h() + this.p()));
                final k k21 = this;
                ++k21.f;
            }
            case 156: {
                this.v(this.E);
            }
            case 157: {
                this.v(this.D);
            }
            case 158: {
                this.v(this.H(this.h() + this.p()));
                final k k22 = this;
                ++k22.f;
            }
            case 164: {
                final short[] v = a.a.a.k.V;
                final k k23 = this;
                final int p = k23.p & this.E;
                k23.p = p;
                a.a.a.k.J = (v[p] | 0x10);
            }
            case 165: {
                final short[] v2 = a.a.a.k.V;
                final k k24 = this;
                final int p2 = k24.p & this.D;
                k24.p = p2;
                a.a.a.k.J = (v2[p2] | 0x10);
            }
            case 166: {
                final short[] v3 = a.a.a.k.V;
                final k k25 = this;
                final int p3 = k25.p & this.H(this.h() + this.p());
                k25.p = p3;
                a.a.a.k.J = (v3[p3] | 0x10);
                final k k26 = this;
                ++k26.f;
            }
            case 172: {
                final short[] v4 = a.a.a.k.V;
                final k k27 = this;
                final int p4 = k27.p ^ this.E;
                k27.p = p4;
                a.a.a.k.J = v4[p4];
            }
            case 173: {
                final short[] v5 = a.a.a.k.V;
                final k k28 = this;
                final int p5 = k28.p ^ this.D;
                k28.p = p5;
                a.a.a.k.J = v5[p5];
            }
            case 174: {
                final short[] v6 = a.a.a.k.V;
                final k k29 = this;
                final int p6 = k29.p ^ this.H(this.h() + this.p());
                k29.p = p6;
                a.a.a.k.J = v6[p6];
                final k k30 = this;
                ++k30.f;
            }
            case 180: {
                final short[] v7 = a.a.a.k.V;
                final k k31 = this;
                final int p7 = k31.p | this.E;
                k31.p = p7;
                a.a.a.k.J = v7[p7];
            }
            case 181: {
                final short[] v8 = a.a.a.k.V;
                final k k32 = this;
                final int p8 = k32.p | this.D;
                k32.p = p8;
                a.a.a.k.J = v8[p8];
            }
            case 182: {
                final short[] v9 = a.a.a.k.V;
                final k k33 = this;
                final int p9 = k33.p | this.H(this.h() + this.p());
                k33.p = p9;
                a.a.a.k.J = v9[p9];
                final k k34 = this;
                ++k34.f;
            }
            case 188: {
                this.w(this.E);
            }
            case 189: {
                this.w(this.D);
            }
            case 190: {
                this.w(this.H(this.h() + this.p()));
                final k k35 = this;
                ++k35.f;
            }
            case 203: {
                this.q(this.h());
            }
            case 225: {
                this.y(this.G(this.g));
                final k k36 = this;
                k36.g += 2;
            }
            case 227: {
                n = this.h();
                this.y(this.G(this.g));
                this.d(this.g, n & 0xFF);
                this.d(this.g + 1, n >> 8);
            }
            case 229: {
                this.a(this.E, this.D);
            }
            case 233: {
                this.f = this.h();
            }
            case 249: {
                this.g = this.h();
            }
            default: {
                final k k37 = this;
                --k37.f;
            }
        }
    }
    
    private final void p(int n) {
        a.a.a.k.L -= a.a.a.k.Q[n];
        if (a.a.a.k.d) {
            this.o();
        }
        switch (n) {
            case 9: {
                this.z(b(this.i(), this.e()));
            }
            case 25: {
                this.z(b(this.i(), this.f()));
            }
            case 33: {
                this.z(this.G(this.f));
                final k k = this;
                k.f += 2;
            }
            case 34: {
                n = this.G(this.f);
                this.d(n++, this.F);
                this.d(n, this.G);
                final k i = this;
                i.f += 2;
            }
            case 35: {
                final k j = this = this;
                j.F = (this.F + 1 & 0xFF);
                if (this.F == 0) {
                    this.G = (this.G + 1 & 0xFF);
                }
            }
            case 36: {
                this.G = A(this.G);
            }
            case 37: {
                this.G = B(this.G);
            }
            case 38: {
                this.G = this.H(this.f++);
            }
            case 41: {
                this.z(b(this.i(), this.i()));
            }
            case 42: {
                n = this.G(this.f);
                this.F = this.H(n);
                this.G = this.H(++n);
                final k l = this;
                l.f += 2;
            }
            case 43: {
                final k m = this = this;
                m.F = (this.F - 1 & 0xFF);
                if (this.F == 255) {
                    this.G = (this.G - 1 & 0xFF);
                }
            }
            case 44: {
                this.F = A(this.F);
            }
            case 45: {
                this.F = B(this.F);
            }
            case 46: {
                this.F = this.H(this.f++);
            }
            case 52: {
                this.c(this.i() + this.p());
                final k k2 = this;
                ++k2.f;
            }
            case 53: {
                this.d(this.i() + this.p());
                final k k3 = this;
                ++k3.f;
            }
            case 54: {
                this.d(this.i() + this.p(), this.H(++this.f));
                final k k4 = this;
                ++k4.f;
            }
            case 57: {
                this.z(b(this.i(), this.g));
            }
            case 68: {
                this.r = this.G;
            }
            case 69: {
                this.r = this.F;
            }
            case 70: {
                this.r = this.H(this.i() + this.p());
                final k k5 = this;
                ++k5.f;
            }
            case 76: {
                this.s = this.G;
            }
            case 77: {
                this.s = this.F;
            }
            case 78: {
                this.s = this.H(this.i() + this.p());
                final k k6 = this;
                ++k6.f;
            }
            case 84: {
                this.v = this.G;
            }
            case 85: {
                this.v = this.F;
            }
            case 86: {
                this.v = this.H(this.i() + this.p());
                final k k7 = this;
                ++k7.f;
            }
            case 92: {
                this.w = this.G;
            }
            case 93: {
                this.w = this.F;
            }
            case 94: {
                this.w = this.H(this.i() + this.p());
                final k k8 = this;
                ++k8.f;
            }
            case 96: {
                this.G = this.r;
            }
            case 97: {
                this.G = this.s;
            }
            case 98: {
                this.G = this.v;
            }
            case 99: {
                this.G = this.w;
            }
            case 100: {}
            case 101: {
                this.G = this.F;
            }
            case 102: {
                this.z = this.H(this.i() + this.p());
                final k k9 = this;
                ++k9.f;
            }
            case 103: {
                this.G = this.p;
            }
            case 104: {
                this.F = this.r;
            }
            case 105: {
                this.F = this.s;
            }
            case 106: {
                this.F = this.v;
            }
            case 107: {
                this.F = this.w;
            }
            case 108: {
                this.F = this.G;
            }
            case 109: {}
            case 110: {
                this.A = this.H(this.i() + this.p());
                final k k10 = this;
                ++k10.f;
            }
            case 111: {
                this.F = this.p;
            }
            case 112: {
                this.d(this.i() + this.p(), this.r);
                final k k11 = this;
                ++k11.f;
            }
            case 113: {
                this.d(this.i() + this.p(), this.s);
                final k k12 = this;
                ++k12.f;
            }
            case 114: {
                this.d(this.i() + this.p(), this.v);
                final k k13 = this;
                ++k13.f;
            }
            case 115: {
                this.d(this.i() + this.p(), this.w);
                final k k14 = this;
                ++k14.f;
            }
            case 116: {
                this.d(this.i() + this.p(), this.z);
                final k k15 = this;
                ++k15.f;
            }
            case 117: {
                this.d(this.i() + this.p(), this.A);
                final k k16 = this;
                ++k16.f;
            }
            case 119: {
                this.d(this.i() + this.p(), this.p);
                final k k17 = this;
                ++k17.f;
            }
            case 124: {
                this.p = this.G;
            }
            case 125: {
                this.p = this.F;
            }
            case 126: {
                this.p = this.H(this.i() + this.p());
                final k k18 = this;
                ++k18.f;
            }
            case 132: {
                this.s(this.G);
            }
            case 133: {
                this.s(this.F);
            }
            case 134: {
                this.s(this.H(this.i() + this.p()));
                final k k19 = this;
                ++k19.f;
            }
            case 140: {
                this.t(this.G);
            }
            case 141: {
                this.t(this.F);
            }
            case 142: {
                this.t(this.H(this.i() + this.p()));
                final k k20 = this;
                ++k20.f;
            }
            case 148: {
                this.u(this.G);
            }
            case 149: {
                this.u(this.F);
            }
            case 150: {
                this.u(this.H(this.i() + this.p()));
                final k k21 = this;
                ++k21.f;
            }
            case 156: {
                this.v(this.G);
            }
            case 157: {
                this.v(this.F);
            }
            case 158: {
                this.v(this.H(this.i() + this.p()));
                final k k22 = this;
                ++k22.f;
            }
            case 164: {
                final short[] v = a.a.a.k.V;
                final k k23 = this;
                final int p = k23.p & this.G;
                k23.p = p;
                a.a.a.k.J = (v[p] | 0x10);
            }
            case 165: {
                final short[] v2 = a.a.a.k.V;
                final k k24 = this;
                final int p2 = k24.p & this.F;
                k24.p = p2;
                a.a.a.k.J = (v2[p2] | 0x10);
            }
            case 166: {
                final short[] v3 = a.a.a.k.V;
                final k k25 = this;
                final int p3 = k25.p & this.H(this.i() + this.p());
                k25.p = p3;
                a.a.a.k.J = (v3[p3] | 0x10);
                final k k26 = this;
                ++k26.f;
            }
            case 172: {
                final short[] v4 = a.a.a.k.V;
                final k k27 = this;
                final int p4 = k27.p ^ this.G;
                k27.p = p4;
                a.a.a.k.J = v4[p4];
            }
            case 173: {
                final short[] v5 = a.a.a.k.V;
                final k k28 = this;
                final int p5 = k28.p ^ this.F;
                k28.p = p5;
                a.a.a.k.J = v5[p5];
            }
            case 174: {
                final short[] v6 = a.a.a.k.V;
                final k k29 = this;
                final int p6 = k29.p ^ this.H(this.i() + this.p());
                k29.p = p6;
                a.a.a.k.J = v6[p6];
                final k k30 = this;
                ++k30.f;
            }
            case 180: {
                final short[] v7 = a.a.a.k.V;
                final k k31 = this;
                final int p7 = k31.p | this.G;
                k31.p = p7;
                a.a.a.k.J = v7[p7];
            }
            case 181: {
                final short[] v8 = a.a.a.k.V;
                final k k32 = this;
                final int p8 = k32.p | this.F;
                k32.p = p8;
                a.a.a.k.J = v8[p8];
            }
            case 182: {
                final short[] v9 = a.a.a.k.V;
                final k k33 = this;
                final int p9 = k33.p | this.H(this.i() + this.p());
                k33.p = p9;
                a.a.a.k.J = v9[p9];
                final k k34 = this;
                ++k34.f;
            }
            case 188: {
                this.w(this.G);
            }
            case 189: {
                this.w(this.F);
            }
            case 190: {
                this.w(this.H(this.i() + this.p()));
                final k k35 = this;
                ++k35.f;
            }
            case 203: {
                this.q(this.i());
            }
            case 225: {
                this.z(this.G(this.g));
                final k k36 = this;
                k36.g += 2;
            }
            case 227: {
                n = this.i();
                this.z(this.G(this.g));
                this.d(this.g, n & 0xFF);
                this.d(this.g + 1, n >> 8);
            }
            case 229: {
                this.a(this.G, this.F);
            }
            case 233: {
                this.f = this.i();
            }
            case 249: {
                this.g = this.i();
            }
            default: {
                final k k37 = this;
                --k37.f;
            }
        }
    }
    
    private final void q(int n) {
        n = (n + this.p() & 0xFFFF);
        final int h = this.H(++this.f);
        a.a.a.k.L -= a.a.a.k.R[h];
        switch (h) {
            case 6: {
                this.d(n, f(this.H(n)));
                break;
            }
            case 14: {
                this.d(n, g(this.H(n)));
                break;
            }
            case 22: {
                this.d(n, h(this.H(n)));
                break;
            }
            case 30: {
                this.d(n, i(this.H(n)));
                break;
            }
            case 38: {
                this.d(n, j(this.H(n)));
                break;
            }
            case 46: {
                this.d(n, l(this.H(n)));
                break;
            }
            case 54: {
                this.d(n, k(this.H(n)));
                break;
            }
            case 62: {
                this.d(n, m(this.H(n)));
                break;
            }
            case 70: {
                n(this.H(n) & 0x1);
                break;
            }
            case 78: {
                n(this.H(n) & 0x2);
                break;
            }
            case 86: {
                n(this.H(n) & 0x4);
                break;
            }
            case 94: {
                n(this.H(n) & 0x8);
                break;
            }
            case 102: {
                n(this.H(n) & 0x10);
                break;
            }
            case 110: {
                n(this.H(n) & 0x20);
                break;
            }
            case 118: {
                n(this.H(n) & 0x40);
                break;
            }
            case 126: {
                n(this.H(n) & 0x80);
                break;
            }
            case 134: {
                this.d(n, this.H(n) & 0xFFFFFFFE);
                break;
            }
            case 142: {
                this.d(n, this.H(n) & 0xFFFFFFFD);
                break;
            }
            case 150: {
                this.d(n, this.H(n) & 0xFFFFFFFB);
                break;
            }
            case 158: {
                this.d(n, this.H(n) & 0xFFFFFFF7);
                break;
            }
            case 166: {
                this.d(n, this.H(n) & 0xFFFFFFEF);
                break;
            }
            case 174: {
                this.d(n, this.H(n) & 0xFFFFFFDF);
                break;
            }
            case 182: {
                this.d(n, this.H(n) & 0xFFFFFFBF);
                break;
            }
            case 190: {
                this.d(n, this.H(n) & 0xFFFFFF7F);
                break;
            }
            case 198: {
                this.d(n, this.H(n) | 0x1);
                break;
            }
            case 206: {
                this.d(n, this.H(n) | 0x2);
                break;
            }
            case 214: {
                this.d(n, this.H(n) | 0x4);
                break;
            }
            case 222: {
                this.d(n, this.H(n) | 0x8);
                break;
            }
            case 230: {
                this.d(n, this.H(n) | 0x10);
                break;
            }
            case 238: {
                this.d(n, this.H(n) | 0x20);
                break;
            }
            case 246: {
                this.d(n, this.H(n) | 0x40);
                break;
            }
            case 254: {
                this.d(n, this.H(n) | 0x80);
                break;
            }
        }
        ++this.f;
    }
    
    private final void r(int n) {
        a.a.a.k.L -= a.a.a.k.S[n];
        if (a.a.a.k.d) {
            this.o();
        }
        switch (n) {
            case 64: {
                this.r = this.F(this.s);
                a.a.a.k.J = ((a.a.a.k.J & 0x1) | a.a.a.k.V[this.r]);
                ++this.f;
            }
            case 65: {
                this.c(this.s, this.r);
                ++this.f;
            }
            case 66: {
                this.D(this.e());
                ++this.f;
            }
            case 67: {
                n = this.G(this.f + 1);
                this.d(n++, this.s);
                this.d(n, this.r);
                this.f += 3;
            }
            case 68:
            case 76:
            case 84:
            case 92:
            case 100:
            case 108:
            case 116:
            case 124: {
                n = this.p;
                this.p = 0;
                this.u(n);
                ++this.f;
            }
            case 69:
            case 77:
            case 85:
            case 93:
            case 101:
            case 109:
            case 117:
            case 125: {
                this.f = this.G(this.g);
                this.g += 2;
                this.i = this.j;
            }
            case 70:
            case 78:
            case 102:
            case 110: {
                this.h = 0;
                ++this.f;
            }
            case 71: {
                this.I = this.p;
                ++this.f;
            }
            case 72: {
                this.s = this.F(this.s);
                a.a.a.k.J = ((a.a.a.k.J & 0x1) | a.a.a.k.V[this.s]);
                ++this.f;
            }
            case 73: {
                this.c(this.s, this.s);
                ++this.f;
            }
            case 74: {
                this.C(this.e());
                ++this.f;
            }
            case 75: {
                n = this.G(this.f + 1);
                this.s = this.H(n++);
                this.r = this.H(n);
                this.f += 3;
            }
            case 79: {
                this.H = this.p;
                ++this.f;
            }
            case 80: {
                this.v = this.F(this.s);
                a.a.a.k.J = ((a.a.a.k.J & 0x1) | a.a.a.k.V[this.v]);
                ++this.f;
            }
            case 81: {
                this.c(this.s, this.v);
                ++this.f;
            }
            case 82: {
                this.D(this.f());
                ++this.f;
            }
            case 83: {
                n = this.G(this.f + 1);
                this.d(n++, this.w);
                this.d(n, this.v);
                this.f += 3;
            }
            case 86:
            case 118: {
                this.h = 1;
                ++this.f;
            }
            case 87: {
                this.p = this.I;
                a.a.a.k.J = ((a.a.a.k.J & 0x1) | a.a.a.k.U[this.p] | (this.j ? 4 : 0));
                ++this.f;
            }
            case 88: {
                this.w = this.F(this.s);
                a.a.a.k.J = ((a.a.a.k.J & 0x1) | a.a.a.k.V[this.w]);
                ++this.f;
            }
            case 89: {
                this.c(this.s, this.w);
                ++this.f;
            }
            case 90: {
                this.C(this.f());
                ++this.f;
            }
            case 91: {
                n = this.G(this.f + 1);
                this.w = this.H(n++);
                this.v = this.H(n);
                this.f += 3;
            }
            case 95: {
                int p;
                if (a.a.a.k.d) {
                    p = this.H;
                }
                else {
                    n = 255;
                    p = Math.abs(a.a.a.k.e.nextInt() % 255);
                }
                this.p = p;
                a.a.a.k.J = ((a.a.a.k.J & 0x1) | a.a.a.k.U[this.p] | (this.j ? 4 : 0));
                ++this.f;
            }
            case 96: {
                this.z = this.F(this.s);
                a.a.a.k.J = ((a.a.a.k.J & 0x1) | a.a.a.k.V[this.z]);
                ++this.f;
            }
            case 97: {
                this.c(this.s, this.z);
                ++this.f;
            }
            case 98: {
                this.D(this.g());
                ++this.f;
            }
            case 99: {
                n = this.G(this.f + 1);
                this.d(n++, this.A);
                this.d(n, this.z);
                this.f += 3;
            }
            case 103: {
                n = this.g();
                final int h = this.H(n);
                this.d(n, h >> 4 | (this.p & 0xF) << 4);
                this.p = ((this.p & 0xF0) | (h & 0xF));
                a.a.a.k.J = ((a.a.a.k.J & 0x1) | a.a.a.k.V[this.p]);
                ++this.f;
            }
            case 104: {
                this.A = this.F(this.s);
                a.a.a.k.J = ((a.a.a.k.J & 0x1) | a.a.a.k.V[this.A]);
                ++this.f;
            }
            case 105: {
                this.c(this.s, this.A);
                ++this.f;
            }
            case 106: {
                this.C(this.g());
                ++this.f;
            }
            case 107: {
                n = this.G(this.f + 1);
                this.A = this.H(n++);
                this.z = this.H(n);
                this.f += 3;
            }
            case 111: {
                n = this.g();
                final int h2 = this.H(n);
                this.d(n, (h2 & 0xF) << 4 | (this.p & 0xF));
                this.p = ((this.p & 0xF0) | h2 >> 4);
                a.a.a.k.J = ((a.a.a.k.J & 0x1) | a.a.a.k.V[this.p]);
                ++this.f;
            }
            case 113: {
                this.c(this.s, 0);
                ++this.f;
            }
            case 114: {
                this.D(this.g);
                ++this.f;
            }
            case 115: {
                n = this.G(this.f + 1);
                this.d(n++, this.g & 0xFF);
                this.d(n, this.g >> 8);
                this.f += 3;
            }
            case 120: {
                this.p = this.F(this.s);
                a.a.a.k.J = ((a.a.a.k.J & 0x1) | a.a.a.k.V[this.p]);
                ++this.f;
            }
            case 121: {
                this.c(this.s, this.p);
                ++this.f;
            }
            case 122: {
                this.C(this.g);
                ++this.f;
            }
            case 123: {
                this.g = this.G(this.G(this.f + 1));
                this.f += 3;
            }
            case 160: {
                this.d(this.f(), this.H(this.g()));
                this.j();
                this.k();
                this.l();
                a.a.a.k.J = ((a.a.a.k.J & 0xC1) | ((this.e() != 0) ? 4 : 0));
                ++this.f;
            }
            case 161: {
                n = ((a.a.a.k.J & 0x1) | 0x2);
                this.w(this.H(this.g()));
                this.k();
                this.l();
                n |= ((this.e() == 0) ? 0 : 4);
                a.a.a.k.J = ((a.a.a.k.J & 0xF8) | n);
                ++this.f;
            }
            case 162: {
                n = this.F(this.s);
                this.d(this.g(), n);
                this.r = B(this.r);
                this.k();
                if ((n & 0x80) == 0x80) {
                    a.a.a.k.J |= 0x2;
                }
                else {
                    a.a.a.k.J &= 0xFFFFFFFD;
                }
                ++this.f;
            }
            case 163: {
                n = this.H(this.g());
                this.c(this.s, n);
                this.k();
                this.r = B(this.r);
                if (this.A + n > 255) {
                    a.a.a.k.J = ((a.a.a.k.J |= 0x1) | 0x10);
                }
                else {
                    a.a.a.k.J = ((a.a.a.k.J &= 0xFFFFFFFE) & 0xFFFFFFEF);
                }
                if ((n & 0x80) == 0x80) {
                    a.a.a.k.J |= 0x2;
                }
                else {
                    a.a.a.k.J &= 0xFFFFFFFD;
                }
                ++this.f;
            }
            case 168: {
                this.d(this.f(), this.H(this.g()));
                this.m();
                this.n();
                this.l();
                a.a.a.k.J = ((a.a.a.k.J & 0xC1) | ((this.e() != 0) ? 4 : 0));
                ++this.f;
            }
            case 169: {
                n = ((a.a.a.k.J & 0x1) | 0x2);
                this.w(this.H(this.g()));
                this.n();
                this.l();
                n |= ((this.e() == 0) ? 0 : 4);
                a.a.a.k.J = ((a.a.a.k.J & 0xF8) | n);
                ++this.f;
            }
            case 170: {
                n = this.F(this.s);
                this.d(this.g(), n);
                this.r = B(this.r);
                this.n();
                if ((n & 0x80) != 0x0) {
                    a.a.a.k.J |= 0x2;
                }
                else {
                    a.a.a.k.J &= 0xFFFFFFFD;
                }
                ++this.f;
            }
            case 171: {
                n = this.H(this.g());
                this.c(this.s, n);
                this.n();
                this.r = B(this.r);
                if (this.A + n > 255) {
                    a.a.a.k.J = ((a.a.a.k.J |= 0x1) | 0x10);
                }
                else {
                    a.a.a.k.J = ((a.a.a.k.J &= 0xFFFFFFFE) & 0xFFFFFFEF);
                }
                if ((n & 0x80) == 0x80) {
                    a.a.a.k.J |= 0x2;
                }
                else {
                    a.a.a.k.J &= 0xFFFFFFFD;
                }
                ++this.f;
            }
            case 176: {
                this.d(this.f(), this.H(this.g()));
                this.j();
                this.k();
                this.l();
                if (this.e() != 0) {
                    a.a.a.k.J |= 0x4;
                    a.a.a.k.L -= 5;
                    --this.f;
                }
                else {
                    a.a.a.k.J &= 0xFFFFFFFB;
                    ++this.f;
                }
                a.a.a.k.J = ((a.a.a.k.J &= 0xFFFFFFFD) & 0xFFFFFFEF);
            }
            case 177: {
                n = ((a.a.a.k.J & 0x1) | 0x2);
                this.w(this.H(this.g()));
                this.k();
                this.l();
                if (((n |= ((this.e() == 0) ? 0 : 4)) & 0x4) != 0x0 && (a.a.a.k.J & 0x40) == 0x0) {
                    a.a.a.k.L -= 5;
                    --this.f;
                }
                else {
                    ++this.f;
                }
                a.a.a.k.J = ((a.a.a.k.J & 0xF8) | n);
            }
            case 178: {
                n = this.F(this.s);
                this.d(this.g(), n);
                this.r = B(this.r);
                this.k();
                if (this.r != 0) {
                    a.a.a.k.L -= 5;
                    --this.f;
                }
                else {
                    ++this.f;
                }
                if ((n & 0x80) == 0x80) {
                    a.a.a.k.J |= 0x2;
                    return;
                }
                a.a.a.k.J &= 0xFFFFFFFD;
            }
            case 179: {
                n = this.H(this.g());
                this.c(this.s, n);
                this.r = B(this.r);
                this.k();
                if (this.r != 0) {
                    a.a.a.k.L -= 5;
                    --this.f;
                }
                else {
                    ++this.f;
                }
                if (this.A + n > 255) {
                    a.a.a.k.J = ((a.a.a.k.J |= 0x1) | 0x10);
                }
                else {
                    a.a.a.k.J = ((a.a.a.k.J &= 0xFFFFFFFE) & 0xFFFFFFEF);
                }
                if ((n & 0x80) != 0x0) {
                    a.a.a.k.J |= 0x2;
                    return;
                }
                a.a.a.k.J &= 0xFFFFFFFD;
            }
            case 184: {
                this.d(this.f(), this.H(this.g()));
                this.m();
                this.n();
                this.l();
                if (this.e() != 0) {
                    a.a.a.k.J |= 0x4;
                    a.a.a.k.L -= 5;
                    --this.f;
                }
                else {
                    a.a.a.k.J &= 0xFFFFFFFB;
                    ++this.f;
                }
                a.a.a.k.J = ((a.a.a.k.J &= 0xFFFFFFFD) & 0xFFFFFFEF);
            }
            case 185: {
                n = ((a.a.a.k.J & 0x1) | 0x2);
                this.w(this.H(this.g()));
                this.n();
                this.l();
                if (((n |= ((this.e() == 0) ? 0 : 4)) & 0x4) != 0x0 && (a.a.a.k.J & 0x40) == 0x0) {
                    a.a.a.k.L -= 5;
                    --this.f;
                }
                else {
                    ++this.f;
                }
                a.a.a.k.J = ((a.a.a.k.J & 0xF8) | n);
            }
            case 186: {
                n = this.F(this.s);
                this.d(this.g(), n);
                this.r = B(this.r);
                this.n();
                if (this.r != 0) {
                    a.a.a.k.L -= 5;
                    --this.f;
                }
                else {
                    ++this.f;
                }
                if ((n & 0x80) != 0x0) {
                    a.a.a.k.J |= 0x2;
                    return;
                }
                a.a.a.k.J &= 0xFFFFFFFD;
            }
            case 187: {
                n = this.H(this.g());
                this.c(this.s, n);
                this.r = B(this.r);
                this.n();
                if (this.r != 0) {
                    a.a.a.k.L -= 5;
                    --this.f;
                }
                else {
                    ++this.f;
                }
                if (this.A + n > 255) {
                    a.a.a.k.J = ((a.a.a.k.J |= 0x1) | 0x10);
                }
                else {
                    a.a.a.k.J = ((a.a.a.k.J &= 0xFFFFFFFE) & 0xFFFFFFEF);
                }
                if ((n & 0x80) != 0x0) {
                    a.a.a.k.J |= 0x2;
                    return;
                }
                a.a.a.k.J &= 0xFFFFFFFD;
                break;
            }
        }
    }
    
    private final void s(int p) {
        p = (this.p + p & 0xFF);
        a.a.a.k.J = a.a.a.k.Y[this.p << 8 | p];
        this.p = p;
    }
    
    private final void t(int p) {
        final int n = a.a.a.k.J & 0x1;
        p = (this.p + p + n & 0xFF);
        a.a.a.k.J = a.a.a.k.Y[n << 16 | this.p << 8 | p];
        this.p = p;
    }
    
    private final void u(int p) {
        p = (this.p - p & 0xFF);
        a.a.a.k.J = a.a.a.k.Z[this.p << 8 | p];
        this.p = p;
    }
    
    private final void v(int p) {
        final int n = a.a.a.k.J & 0x1;
        p = (this.p - p - n & 0xFF);
        a.a.a.k.J = a.a.a.k.Z[n << 16 | this.p << 8 | p];
        this.p = p;
    }
    
    private final void w(final int n) {
        a.a.a.k.J = a.a.a.k.Z[this.p << 8 | (this.p - n & 0xFF)];
    }
    
    private final int e() {
        return this.r << 8 | this.s;
    }
    
    private final int f() {
        return this.v << 8 | this.w;
    }
    
    private final int g() {
        return this.z << 8 | this.A;
    }
    
    private final int h() {
        return this.E << 8 | this.D;
    }
    
    private final int i() {
        return this.G << 8 | this.F;
    }
    
    private final void x(final int n) {
        this.z = n >> 8;
        this.A = (n & 0xFF);
    }
    
    private final void y(final int n) {
        this.E = n >> 8;
        this.D = (n & 0xFF);
    }
    
    private final void z(final int n) {
        this.G = n >> 8;
        this.F = (n & 0xFF);
    }
    
    private final void j() {
        this.w = (this.w + 1 & 0xFF);
        if (this.w == 0) {
            this.v = (this.v + 1 & 0xFF);
        }
    }
    
    private final void k() {
        this.A = (this.A + 1 & 0xFF);
        if (this.A == 0) {
            this.z = (this.z + 1 & 0xFF);
        }
    }
    
    private final void l() {
        this.s = (this.s - 1 & 0xFF);
        if (this.s == 255) {
            this.r = (this.r - 1 & 0xFF);
        }
    }
    
    private final void m() {
        this.w = (this.w - 1 & 0xFF);
        if (this.w == 255) {
            this.v = (this.v - 1 & 0xFF);
        }
    }
    
    private final void n() {
        this.A = (this.A - 1 & 0xFF);
        if (this.A == 255) {
            this.z = (this.z - 1 & 0xFF);
        }
    }
    
    private static int A(int n) {
        n = (n + 1 & 0xFF);
        k.J = ((k.J & 0x1) | k.W[n]);
        return n;
    }
    
    private static int B(int n) {
        n = (n - 1 & 0xFF);
        k.J = ((k.J & 0x1) | k.X[n]);
        return n;
    }
    
    private static int b(final int n, final int n2) {
        final int n3 = n + n2;
        k.J = ((k.J & 0xC4) | ((n ^ n3 ^ n2) >> 8 & 0x10) | (n3 >> 16 & 0x1));
        return n3 & 0xFFFF;
    }
    
    private final void C(final int n) {
        final int n3;
        final int n2 = (n3 = (this.z << 8 | this.A)) + n + (a.a.a.k.J & 0x1);
        a.a.a.k.J = (((n3 ^ n2 ^ n) >> 8 & 0x10) | (n2 >> 16 & 0x1) | (n2 >> 8 & 0x80) | (((n2 & 0xFFFF) != 0x0) ? 0 : 64) | ((n ^ n3 ^ 0x8000) & (n ^ n2) & 0x8000) >> 13);
        this.z = (n2 >> 8 & 0xFF);
        this.A = (n2 & 0xFF);
    }
    
    private final void D(final int n) {
        final int n3;
        final int n2 = (n3 = (this.z << 8 | this.A)) - n - (a.a.a.k.J & 0x1);
        a.a.a.k.J = (((n3 ^ n2 ^ n) >> 8 & 0x10) | 0x2 | (n2 >> 16 & 0x1) | (n2 >> 8 & 0x80) | (((n2 & 0xFFFF) != 0x0) ? 0 : 64) | ((n ^ n3) & (n3 ^ n2) & 0x8000) >> 13);
        this.z = (n2 >> 8 & 0xFF);
        this.A = (n2 & 0xFF);
    }
    
    private final void o() {
        this.H = ((this.H & 0x80) | (this.H + 1 & 0x7F));
    }
    
    private static boolean E(final int n) {
        boolean b = true;
        for (int i = 0; i < 8; ++i) {
            if ((n & 1 << i) != 0x0) {
                b = !b;
            }
        }
        return b;
    }
    
    private void c(final int n, final int n2) {
        this.b.a((char)n, (char)n2);
    }
    
    private int F(final int n) {
        return this.b.a((char)n) & '\u00ff';
    }
    
    private int G(final int n) {
        return this.H(n) | this.H(n + 1) << 8;
    }
    
    private int H(final int n) {
        return this.b.b((char)n);
    }
    
    private void d(final int n, final int n2) {
        this.b.b((char)n, (char)(n2 & 0xFF));
    }
    
    private final int p() {
        final int h = this.H(this.f);
        return h - ((h & 0x80) << 1) & 0xFFFF;
    }
    
    static {
        k.c = true;
        k.d = true;
        k.e = new Random();
        O = new short[] { 4, 10, 7, 6, 4, 4, 7, 4, 4, 11, 7, 6, 4, 4, 7, 4, 8, 10, 7, 6, 4, 4, 7, 4, 12, 11, 7, 6, 4, 4, 7, 4, 7, 10, 16, 6, 4, 4, 7, 4, 7, 11, 16, 6, 4, 4, 7, 4, 7, 10, 13, 6, 11, 11, 10, 4, 7, 11, 13, 6, 4, 4, 7, 4, 4, 4, 4, 4, 4, 4, 7, 4, 4, 4, 4, 4, 4, 4, 7, 4, 4, 4, 4, 4, 4, 4, 7, 4, 4, 4, 4, 4, 4, 4, 7, 4, 4, 4, 4, 4, 4, 4, 7, 4, 4, 4, 4, 4, 4, 4, 7, 4, 7, 7, 7, 7, 7, 7, 4, 7, 4, 4, 4, 4, 4, 4, 7, 4, 4, 4, 4, 4, 4, 4, 7, 4, 4, 4, 4, 4, 4, 4, 7, 4, 4, 4, 4, 4, 4, 4, 7, 4, 4, 4, 4, 4, 4, 4, 7, 4, 4, 4, 4, 4, 4, 4, 7, 4, 4, 4, 4, 4, 4, 4, 7, 4, 4, 4, 4, 4, 4, 4, 7, 4, 4, 4, 4, 4, 4, 4, 7, 4, 5, 10, 10, 10, 10, 11, 7, 11, 5, 10, 10, 0, 10, 17, 7, 11, 5, 10, 10, 11, 10, 11, 7, 11, 5, 4, 10, 11, 10, 0, 7, 11, 5, 10, 10, 19, 10, 11, 7, 11, 5, 4, 10, 4, 10, 0, 7, 11, 5, 10, 10, 4, 10, 11, 7, 11, 5, 6, 10, 4, 10, 0, 7, 11 };
        P = new short[] { 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 12, 8, 8, 8, 8, 8, 8, 8, 12, 8, 8, 8, 8, 8, 8, 8, 12, 8, 8, 8, 8, 8, 8, 8, 12, 8, 8, 8, 8, 8, 8, 8, 12, 8, 8, 8, 8, 8, 8, 8, 12, 8, 8, 8, 8, 8, 8, 8, 12, 8, 8, 8, 8, 8, 8, 8, 12, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8, 8, 8, 8, 8, 8, 8, 15, 8 };
        Q = new short[] { 4, 4, 4, 4, 4, 4, 4, 4, 4, 15, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 15, 4, 4, 4, 4, 4, 4, 4, 14, 20, 10, 8, 8, 11, 4, 4, 15, 20, 10, 8, 8, 11, 4, 4, 4, 4, 4, 23, 23, 19, 4, 4, 15, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 8, 8, 19, 4, 4, 4, 4, 4, 8, 8, 19, 4, 4, 4, 4, 4, 8, 8, 19, 4, 4, 4, 4, 4, 8, 8, 19, 4, 8, 8, 8, 8, 8, 8, 19, 8, 8, 8, 8, 8, 8, 8, 19, 8, 19, 19, 19, 19, 19, 19, 4, 19, 4, 4, 4, 4, 8, 8, 19, 4, 4, 4, 4, 4, 8, 8, 19, 4, 4, 4, 4, 4, 8, 8, 19, 4, 4, 4, 4, 4, 8, 8, 19, 4, 4, 4, 4, 4, 8, 8, 19, 4, 4, 4, 4, 4, 8, 8, 19, 4, 4, 4, 4, 4, 8, 8, 19, 4, 4, 4, 4, 4, 8, 8, 19, 4, 4, 4, 4, 4, 8, 8, 19, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 14, 4, 23, 4, 15, 4, 4, 4, 8, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 10, 4, 4, 4, 4, 4, 4 };
        R = new short[] { 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0 };
        S = new short[] { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12, 12, 15, 20, 8, 14, 8, 9, 12, 12, 15, 20, 8, 14, 8, 9, 12, 12, 15, 20, 8, 14, 8, 9, 12, 12, 15, 20, 8, 14, 8, 9, 12, 12, 15, 20, 8, 14, 8, 18, 12, 12, 15, 20, 8, 14, 8, 18, 8, 12, 15, 20, 8, 14, 8, 8, 12, 12, 15, 20, 8, 14, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 16, 16, 16, 16, 8, 8, 8, 8, 16, 16, 16, 16, 8, 8, 8, 8, 16, 16, 16, 16, 8, 8, 8, 8, 16, 16, 16, 16, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 };
    }
}
