// 
// Decompiled by Procyon v0.5.30
// 

package com.impatica.v402;

import java.io.IOException;

public class ImIplayer extends ImBase
{
    boolean append;
    int I;
    ImIrgb arraycopy;
    ImButton charAt;
    int close;
    boolean length;
    ImRecord read;
    StringBuffer setLength;
    int Z;
    public int C;
    StringBuffer start;
    int toString;
    int B;
    boolean D;
    int F;
    int J;
    public ImRect S;
    int A;
    int E;
    int G;
    public ImIstream H;
    boolean K;
    int L;
    public ImIrgb M;
    ImIrgb N;
    public String O;
    ImLmsInterface P;
    boolean Q;
    ImIrgb R;
    ImIrgb T;
    public boolean U;
    int V;
    int[] W;
    ImRecord X;
    int[] Y;
    boolean i;
    int z;
    int c;
    int b;
    int d;
    int f;
    ImRect j;
    ImRect s;
    boolean a;
    int e;
    int g;
    int h;
    boolean k;
    public int l;
    ImSound[] m;
    boolean n;
    ImSprite[] o;
    int p;
    int q;
    int r;
    ImIsys t;
    ImTrans u;
    int v;
    int w;
    public boolean x;
    public int y;
    int II;
    public int ZI;
    int CI;
    int BI;
    ImIrgb DI;
    public int FI;
    int JI;
    private static final int[] SI;
    private static final int[] AI;
    
    private final void append(final StringBuffer sb) {
        if (sb.length() == 0) {
            return;
        }
        final char char1 = sb.charAt(0);
        if (char1 == '&' || char1 == '*') {
            if (char1 == '*') {
                this.setLength();
            }
            ImBase.I(this.start, sb);
        }
        else if (char1 == '#') {
            int length = sb.length();
            for (int i = 1; i < length; length = length * 10 + (sb.charAt(i) - '0'), ++i) {}
            this.C = length;
        }
        else if (char1 == '/' || char1 == ':' || char1 == '^') {
            this.t.I(sb.toString());
        }
        else {
            ImBase.I(this.start, sb);
        }
    }
    
    public final void I() {
        if (this.H != null) {
            try {
                this.H.close();
            }
            catch (IOException ex) {}
            this.H = null;
            this.setLength();
            this.close();
            for (int i = 1; i < this.p; ++i) {
                final ImSprite imSprite = this.o[i];
                if (imSprite != null) {
                    if (imSprite.T != null) {
                        this.t.I(imSprite.T);
                        imSprite.T = null;
                    }
                    this.o[i] = null;
                }
            }
            this.o = null;
            this.p = 0;
            if (ImIrgb.E != null) {
                ImIrgb.E = null;
                ImIrgb.G = 0;
            }
            if (ImIrgb.H != null) {
                ImIrgb.H = null;
            }
            if (this.M != null) {
                final int[] c = this.M.C;
                for (int j = this.M.B - 1; j >= 0; --j) {
                    c[j] = 0;
                }
            }
            if (this.N != null) {
                this.N.I();
                this.N = null;
            }
            if (this.arraycopy != null) {
                this.arraycopy.I();
                this.arraycopy = null;
            }
            if (this.DI != null) {
                this.DI.I();
                this.DI = null;
            }
            this.Y = null;
            this.W = null;
            this.read = null;
            this.X = null;
            while (this.length()) {}
            this.length = false;
            this.start.setLength(0);
            this.l = 0;
            this.C = -1;
            this.f = 0;
            this.ZI = 0;
            this.append = true;
            this.FI = 0;
            this.Z = 0;
        }
    }
    
    final void I(int n) {
        if (!this.n) {
            this.setLength();
        }
        this.k = true;
        if (n == this.Z) {
            this.read.Z(this.F);
        }
        this.J = 0;
        if (n < this.Z) {
            this.Z();
        }
        --n;
        while (this.Z < n && this.C()) {}
        for (int i = this.p - 1; i > 0; --i) {
            final ImSprite imSprite = this.o[i];
            if (imSprite != null) {
                imSprite.V = 0;
            }
        }
        this.k = false;
        this.n = false;
        this.ZI = 0;
        this.start.setLength(0);
    }
    
    final int I(final StringBuffer sb) {
        if (sb.length() == 0) {
            return -1;
        }
        int n = 0;
        this.n = false;
        final char char1 = sb.charAt(0);
        if (char1 == '&') {
            n = 1;
            this.n = true;
        }
        else if (char1 == '*') {
            n = 1;
        }
        final ImRecord c = this.C(3);
        if (c == null) {
            return -1;
        }
        for (int s = c.S(), i = 0; i < s; ++i) {
            if (this.I(c, i, sb, n)) {
                c.Z(4 + i * 8 + 4);
                return c.S();
            }
        }
        return -1;
    }
    
    final int arraycopy(int n) {
        final ImRecord c = this.C(3);
        if (c == null) {
            return -1;
        }
        final int s = c.S();
        int n2 = this.Z;
        int i;
        for (i = 0; i < s; ++i) {
            c.S();
            n2 = c.S();
            if (n2 >= this.Z) {
                break;
            }
        }
        if (n2 > this.Z) {
            --i;
        }
        while (true) {
            if (n <= 0) {
                if (--i < 0) {
                    return -1;
                }
            }
            else if (++i >= s) {
                return -1;
            }
            c.Z(4 + i * 8);
            final int s2 = c.S();
            final int s3 = c.S();
            c.Z(s2 + (c.S() - s2) - 2);
            if (c.F() == 45 && c.F() == 48) {
                if (n >= 0) {
                    return s3;
                }
                n = 0;
            }
        }
    }
    
    final void Z() {
        if (!this.n) {
            this.setLength();
        }
        if (this.K) {
            this.read.I(this.W[1], this.Y[1]);
        }
        this.read.Z(0);
        this.Z = 0;
        this.F = 0;
        this.d = 0;
        this.ZI = 0;
        this.F();
    }
    
    protected final boolean C() {
        int d = 0;
        final ImRecord read = this.read;
        while (!read.I()) {
            final int f = read.F();
            switch (f) {
                case 31: {
                    final int d2 = read.D();
                    int f2 = read.F();
                    final ImSprite f3 = this.F(d2);
                    if (f2 == 255) {
                        f2 = 256;
                    }
                    f3.I = f2;
                    continue;
                }
                case 39: {
                    final int d3 = read.D();
                    final int d4 = read.D();
                    final int d5 = read.D();
                    final ImSprite f4 = this.F(d3);
                    f4.i = 54;
                    f4.z = 0;
                    f4.c = d5 * d4;
                    f4.b = d4;
                    continue;
                }
                case 18: {
                    final int z = read.Z();
                    if (z != this.close) {
                        this.close = z;
                        this.close();
                        continue;
                    }
                    continue;
                }
                case 40: {
                    final ImSprite f5 = this.F(read.D());
                    f5.C = read.D();
                    f5.J = 0;
                    f5.S = 0;
                    f5.E = 0;
                    f5.A = 0;
                    continue;
                }
                case 46: {
                    final ImSprite f6 = this.F(read.D());
                    f6.C = read.D();
                    f6.J = read.D();
                    f6.S = read.D();
                    f6.E = read.D();
                    f6.A = read.D();
                    continue;
                }
                case 0: {
                    final ImSprite f7 = this.F(read.D());
                    final int d6 = read.D();
                    if (f7.B == null) {
                        f7.B = new StringBuffer();
                    }
                    if (d6 == 0) {
                        f7.B.setLength(0);
                        continue;
                    }
                    read.I(f7.B, d6);
                    continue;
                }
                case 26: {
                    final int d7 = read.D();
                    if (d7 == 0) {
                        this.setLength.setLength(0);
                        continue;
                    }
                    read.I(this.setLength, d7);
                    continue;
                }
                case 32: {
                    this.F(read.D()).Z = read.Z();
                    continue;
                }
                case 28: {
                    final int f8 = read.F();
                    read.F();
                    this.f = 4;
                    this.b = f8;
                    continue;
                }
                case 2: {
                    this.D(read.D());
                    continue;
                }
                case 41: {
                    int d8 = read.D();
                    int d9 = read.D();
                    while (d9-- != 0) {
                        this.D(d8++);
                    }
                    continue;
                }
                case 16: {
                    final int d10 = read.D();
                    final int d11 = read.D();
                    if (d10 != this.A || d11 != this.E) {
                        this.read(this.A = d10, this.E = d11);
                        continue;
                    }
                    continue;
                }
                case 3: {
                    this.C = read.D();
                    continue;
                }
                case 30: {
                    this.I(read.D(), read.D(), read.D());
                    continue;
                }
                case 33: {
                    final int d12 = read.D();
                    final int c = read.C();
                    final int c2 = read.C();
                    final ImSprite f9 = this.F(d12);
                    f9.d = c;
                    f9.f = c2;
                    continue;
                }
                case 21: {
                    d = read.D();
                    if (d == 0) {
                        this.f = 2;
                        continue;
                    }
                    d *= this.toString;
                    continue;
                }
                case 7: {
                    final ImSprite f10 = this.F(read.D());
                    final int d13 = read.D();
                    f10.d = read.C();
                    f10.f = read.C();
                    if (f10.B != null) {
                        f10.B.setLength(0);
                    }
                    f10.Q = 0;
                    if (d13 != f10.H && (f10.H = d13) != 0) {
                        final ImRecord c3 = this.C(d13 + 7);
                        f10.U = c3.J();
                        f10.G = c3.J();
                        continue;
                    }
                    continue;
                }
                case 23:
                case 24: {
                    final int d14 = read.D();
                    final int d15 = read.D();
                    final int c4 = read.C();
                    final int c5 = read.C();
                    final ImSprite f11 = this.F(d14);
                    if (f == 23) {
                        f11.Q = 1;
                        f11.U = read.D();
                        f11.G = read.D();
                        f11.Z = read.Z();
                        f11.C = 0;
                    }
                    else {
                        f11.Q = 1;
                        f11.U = read.D();
                        f11.G = read.D();
                        f11.J = read.D();
                        f11.S = read.D();
                        f11.E = read.D();
                        f11.A = read.D();
                    }
                    f11.d = c4;
                    f11.f = c5;
                    f11.H = d15;
                    if (f11.B != null) {
                        f11.B.setLength(0);
                        continue;
                    }
                    continue;
                }
                case 47: {
                    if (this.C(read.D()) != null) {
                        this.read.I(this.X.C, this.X.E());
                        continue;
                    }
                    continue;
                }
                case 34: {
                    final int d16 = read.D();
                    final int c6 = read.C();
                    final ImSprite f12 = this.F(d16);
                    if (f12.M != c6) {
                        f12.M = c6;
                        continue;
                    }
                    continue;
                }
                case 35: {
                    this.I(read.D(), read.D(), read.D(), read.D(), read.D());
                    continue;
                }
                case 36: {
                    final int d17 = read.D();
                    final int n = read.D() * 32768 / read.D();
                    this.Z(d17, n, n);
                    continue;
                }
                case 8: {
                    read.D();
                    read.D();
                    continue;
                }
                case 9:
                case 10: {
                    final int n2 = read.F() - 1;
                    int d18 = read.D();
                    if (d18 != 0) {
                        d18 += 7;
                    }
                    if (!this.k) {
                        this.toString(n2, d18, f == 10);
                        continue;
                    }
                    continue;
                }
                case 42:
                case 44: {
                    final int d19 = read.D();
                    int d20 = read.D();
                    if (d20 != 0) {
                        d20 += 7;
                    }
                    if (f == 44) {
                        d20 = -d20;
                    }
                    this.F(d19).O = d20;
                    continue;
                }
                case 29: {
                    this.setLength();
                    continue;
                }
                case 43: {
                    this.F(read.D()).O = -1;
                    continue;
                }
                case 45: {
                    this.F(read.D()).O = -2;
                    continue;
                }
                case 20: {
                    final int f13 = read.F();
                    this.f = 4;
                    if (f13 == 1) {
                        this.b = 255;
                        continue;
                    }
                    if (f13 == 2) {
                        this.b = 254;
                        continue;
                    }
                    continue;
                }
                case 11: {
                    int d21 = read.D();
                    if (d21 == 0) {
                        d21 = 1;
                    }
                    this.toString = d21;
                    this.B = 1000 / d21;
                    continue;
                }
                case 13: {
                    this.F = read.A() - 1;
                    int d22 = read.D();
                    if (d22 == 0) {
                        d22 = 1;
                    }
                    this.J = d22 - 1 + d;
                    this.Z += d22;
                    return true;
                }
                case 27: {
                    this.F = read.A() - 1;
                    ++this.Z;
                    this.f = 3;
                    if (read.I()) {
                        this.Q = true;
                    }
                    if (this.P != null) {
                        this.P.I(this.Q, Integer.toString(this.Z));
                    }
                    this.J = 0;
                    return true;
                }
                case 17: {
                    this.F = read.A() - 1;
                    ++this.Z;
                    this.J = d;
                    return true;
                }
                case 25: {
                    if (this.x) {
                        this.close();
                    }
                }
                case 19: {
                    final int f14 = read.F();
                    this.CI = read.D();
                    if (this.x) {
                        this.ZI = f14;
                        if (this.CI == 0) {
                            this.CI = 250;
                        }
                        this.CI /= 16;
                        if (this.CI == 0) {
                            this.CI = 1;
                        }
                        this.BI = 0;
                        continue;
                    }
                    continue;
                }
                case 37:
                case 38: {
                    final int d23 = read.D();
                    int d24 = read.D();
                    final int d25 = read.D();
                    if (f == 38) {
                        d24 = -d24;
                    }
                    if (!this.x) {
                        continue;
                    }
                    if (d23 == 0) {
                        for (int i = 1; i < this.p; ++i) {
                            this.append(this.F(i), d24, d25, this.Z);
                        }
                        continue;
                    }
                    this.append(this.F(d23), d24, d25, this.Z);
                    continue;
                }
                default: {
                    this.I("fop");
                    continue;
                }
            }
        }
        return false;
    }
    
    private final int charAt() {
        int n = 0;
        int read;
        do {
            read = this.H.read();
            if (read == -1) {
                throw new IOException("eof");
            }
            n = (n << 7 | (read & 0x7F));
        } while ((read & 0x80) != 0x0);
        return n;
    }
    
    public ImIplayer(final ImIsys t) {
        this.I = -1;
        this.setLength = new StringBuffer();
        this.C = -1;
        this.start = new StringBuffer();
        this.S = new ImRect();
        this.s = new ImRect();
        this.e = 32768;
        this.m = new ImSound[2];
        this.p = 100;
        this.t = t;
        this.u = new ImTrans();
        this.x = true;
    }
    
    public final void Z(final int n) {
        if (n == 43 || n == 49) {
            this.J(1);
        }
        else if (n == 45 || n == 51) {
            this.J(-1);
        }
        else if (n == 111 || n == 55) {
            --this.l;
        }
        else if (n == 112 || n == 57) {
            ++this.l;
        }
        else if (n == 52) {
            this.C(-4, 0);
        }
        else if (n == 54) {
            this.C(4, 0);
        }
        else if (n == 50) {
            this.C(0, -4);
        }
        else if (n == 56) {
            this.C(0, 4);
        }
        else if ((!this.K || this.Y[3] != 0) && this.setLength.length() != 0 && n == 32) {
            ImBase.I(this.start, this.setLength);
        }
        else if (this.f == 5) {
            this.f = 0;
        }
    }
    
    final ImIrgb close(final int n, final ImRecord imRecord) {
        imRecord.Z(0);
        final int j = imRecord.J();
        final int i = imRecord.J();
        final int n2 = 1;
        ImIrgb imIrgb = this.setLength(n, j, i, 0);
        if (imIrgb == null) {
            imRecord.F();
            imIrgb = this.read(n, j, i, 0, imRecord.F() == 0);
            if (imIrgb == null) {
                return null;
            }
            int e = imRecord.E();
            if (e == imRecord.Z) {
                while (!this.t.I(imIrgb, imRecord.I, 12, e, j, i, n2)) {
                    if (!this.length()) {
                        imIrgb.I();
                        return null;
                    }
                }
            }
            else {
                imRecord.Z(12);
                e -= 12;
                final byte[] b = imRecord.B(e);
                if (b == null) {
                    return null;
                }
                while (!this.t.I(imIrgb, b, 0, e, j, i, n2)) {
                    if (!this.length()) {
                        imIrgb.I();
                        return null;
                    }
                }
            }
        }
        return imIrgb;
    }
    
    private final boolean length() {
        ImIrgb imIrgb = this.R;
        if (imIrgb == null) {
            return false;
        }
        ImIrgb imIrgb2 = null;
        while (imIrgb.A != null) {
            imIrgb2 = imIrgb;
            imIrgb = imIrgb.A;
        }
        imIrgb.I();
        if (imIrgb2 == null) {
            this.R = null;
        }
        else {
            imIrgb2.A = null;
        }
        return true;
    }
    
    private final ImIrgb read(final int s, final int d, final int z, final int j, final boolean i) {
        ImIrgb r = this.T;
        if (r != null) {
            final ImIrgb imIrgb = r;
            r = r.A;
            imIrgb.A = r.A;
            this.T = null;
            r.D = d;
            r.Z = z;
        }
        else {
            while (r == null) {
                try {
                    r = new ImIrgb(d, z);
                    if (r != null && r.C == null) {
                        r = null;
                    }
                }
                catch (OutOfMemoryError outOfMemoryError) {
                    r = null;
                }
                if (r == null && !this.length()) {
                    return null;
                }
            }
        }
        r.S = s;
        r.J = j;
        r.I = i;
        r.A = this.R;
        return this.R = r;
    }
    
    private final ImIrgb setLength(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n2 * n3;
        this.T = null;
        int n6 = Integer.MAX_VALUE;
        ImIrgb t = null;
        ImIrgb r = this.R;
        int n7 = 0;
        while (r != null) {
            if (r.S == n && r.D == n2 && r.Z == n3 && r.J == n4) {
                if (t != null) {
                    t.A = r.A;
                    r.A = this.R;
                    this.R = r;
                }
                return r;
            }
            if (++n7 > 40) {
                final int n8 = r.B - n5;
                if (n8 >= 0 && n8 <= n6) {
                    n6 = n8;
                    this.T = t;
                }
            }
            t = r;
            r = r.A;
        }
        return null;
    }
    
    private final void start(final int n, final int n2) {
        int n3 = 0;
        for (int i = this.p - 1; i >= 1; --i) {
            ImSprite k = this.o[i];
            if (k != null && k.L.B(n, n2)) {
                ImSprite j;
                for (j = k; j != null && (j.B == null || j.B.length() == 0); j = j.K) {}
                int o;
                do {
                    o = k.O;
                    if (o != 0) {
                        break;
                    }
                    k = k.K;
                } while (k != null);
                if (j != null || o != 0) {
                    n3 = 1;
                    if (this.i) {
                        if (j != null && j.B.length() != 0) {
                            this.append(j.B);
                        }
                        if (o != 0) {
                            int n4 = 0;
                            if (o < -1) {
                                n4 = 1;
                            }
                            this.start(n4);
                            if (o != -1 && o != -2) {
                                if (o < 0) {
                                    o = -o;
                                    n4 = 1;
                                }
                                this.toString(n4, o, false);
                            }
                        }
                        this.i = false;
                        break;
                    }
                }
            }
        }
        this.t.I(n3);
        if (this.i) {
            this.i = false;
            this.Z(32);
        }
        this.z = 0;
        this.c = 0;
    }
    
    private final ImIrgb append() {
        if (this.DI != null && this.ZI == 0) {
            final ImIrgb di = this.DI;
            this.DI = null;
            return di;
        }
        while (true) {
            ImIrgb imIrgb;
            try {
                imIrgb = new ImIrgb(this.II, this.G);
            }
            catch (OutOfMemoryError outOfMemoryError) {
                imIrgb = null;
            }
            if (imIrgb != null && imIrgb.C != null) {
                return imIrgb;
            }
            if (!this.length()) {
                return null;
            }
        }
    }
    
    public final boolean I(final ImIstream h) {
        this.I();
        this.H = h;
        try {
            if (h.read() != 68) {
                return false;
            }
            final int read = h.read();
            if (read == 75) {
                this.K = false;
            }
            else {
                if (read != 73) {
                    return false;
                }
                this.K = true;
            }
            this.d = 0;
            this.y = (h.read() | h.read() << 8);
            this.o = new ImSprite[this.p];
            int i = h.read();
            this.O = null;
            if (i != 0) {
                final StringBuffer sb = new StringBuffer();
                while (i >= 0) {
                    char c = (char)h.read();
                    if (c < ' ') {
                        c = ' ';
                    }
                    sb.append(c);
                    --i;
                }
                this.O = sb.toString();
            }
            this.length = false;
            this.X = new ImRecord(h);
            if (this.K) {
                this.V = 100;
                this.Y = new int[100];
                this.W = new int[100];
                this.L = this.H.I();
            }
            else {
                h.read();
                this.V = this.charAt() + 1;
                this.Y = new int[this.V];
                this.W = new int[this.V];
                int n = 0;
                while (true) {
                    int n2 = this.charAt();
                    if (n2 == 0) {
                        n2 = this.charAt();
                        if (n2 == 0) {
                            break;
                        }
                    }
                    final byte b = (byte)h.read();
                    final int char1 = this.charAt();
                    this.Y[n2] = char1;
                    this.W[n2] = n;
                    n += char1;
                }
                final int j = this.H.I();
                for (int k = this.V - 1; k > 0; --k) {
                    final int[] w = this.W;
                    final int n3 = k;
                    w[n3] += j;
                }
            }
            this.read = this.C(1);
            if (this.read == null) {
                return false;
            }
            this.X = new ImRecord(h);
            return true;
        }
        catch (IOException ex) {
            this.I("open ");
            return false;
        }
    }
    
    final void I(final boolean b) {
        if (b) {
            if (this.f == 5) {
                return;
            }
            this.d = this.f;
            this.f = 5;
        }
        else {
            if (this.f == this.d) {
                return;
            }
            this.f = this.d;
        }
        for (int i = 0; i < 2; ++i) {
            final ImSound imSound = this.m[i];
            if (imSound != null) {
                if (b) {
                    imSound.S();
                }
                else {
                    imSound.J();
                }
            }
        }
        for (int j = 1; j < this.p; ++j) {
            final ImSprite imSprite = this.o[j];
            if (imSprite != null && imSprite.T != null) {
                imSprite.T.I(b);
            }
        }
    }
    
    final int arraycopy() {
        if (this.length) {
            return -1;
        }
        try {
            this.H.Z(this.L);
            final int char1 = this.charAt();
            if (char1 == 0) {
                this.length = true;
                return -1;
            }
            if (char1 >= this.V) {
                final int v = char1 + 100;
                System.arraycopy(this.Y, 0, this.Y = new int[v], 0, this.V);
                System.arraycopy(this.W, 0, this.W = new int[v], 0, this.V);
                this.V = v;
            }
            final int char2 = this.charAt();
            final int i = this.H.I();
            this.Y[char1] = char2;
            this.W[char1] = i;
            this.L = i + char2;
            return char1;
        }
        catch (IOException ex) {
            this.length = true;
            return -1;
        }
    }
    
    public final ImRecord C(final int n) {
        if (this.K && (n >= this.V || this.Y[n] == 0)) {
            while (this.arraycopy() != n && !this.length) {}
        }
        if (n > 0 && n < this.V) {
            final int n2 = this.Y[n];
            if (n2 != 0) {
                this.X.I(this.W[n], n2);
                return this.X;
            }
        }
        return null;
    }
    
    public final int B() {
        return this.charAt(this.S.L, this.S.N, this.S.M, this.S.O, true);
    }
    
    final int charAt(final int n, final int n2, final int n3, final int n4, final boolean b) {
        ImButton.I(this.charAt);
        for (int p5 = this.p, i = 1; i < p5; ++i) {
            final ImSprite imSprite = this.o[i];
            if (imSprite != null && imSprite.K == null) {
                this.I(imSprite, this.u);
            }
        }
        this.s.C(n, n2, n3, n4);
        if (!this.s.C()) {
            if (this.append) {
                this.M.B(0, 0, this.II, this.G, this.I);
                if (this.ZI != 0) {
                    this.append = false;
                    this.t.I(0, 0, this.II, this.G);
                }
            }
            int n5 = 1;
            ImIrgb imIrgb = this.M;
            final int l = this.s.L;
            final int n6 = this.s.N;
            final int n7 = this.s.M - l;
            final int n8 = this.s.O - n6;
            if (this.ZI != 0) {
                if (this.N == null) {
                    this.N = this.append();
                }
                if (this.N == null) {
                    this.ZI = 0;
                }
                else {
                    this.arraycopy(this.M.C, this.N.C, l, n6, n7, n8);
                    imIrgb = this.N;
                }
            }
            this.D = false;
            this.a = false;
            for (int j = 1; j < this.p; ++j) {
                final ImSprite imSprite2 = this.o[j];
                if (imSprite2 != null && !imSprite2.L.C() && imSprite2.H != 0 && imIrgb.I(this.s, imSprite2.L)) {
                    final ImRecord c = this.C(imSprite2.H + 7);
                    if (c != null) {
                        if (n5 != 0) {
                            n5 = 0;
                            final boolean b2 = c.I(5) == 0 || !imIrgb.I(this.s);
                            if (b2) {
                                imIrgb.B(l, n6, n7, n8, this.close);
                            }
                            if (b2 && c.I(4) == 9 && imSprite2.Z == this.close) {
                                continue;
                            }
                        }
                        final int v = imSprite2.V;
                        if (v != 0) {
                            if (!this.close(imIrgb, imSprite2)) {
                                continue;
                            }
                            this.D = true;
                        }
                        this.I(imIrgb, imSprite2, c);
                        if (imSprite2.i == 54) {
                            this.setLength(imIrgb, imSprite2);
                        }
                        if (v != 0) {
                            if (this.a) {
                                this.length(imIrgb, imSprite2);
                            }
                            else if (this.v != 0 || this.w != 0) {
                                final ImRect k;
                                final ImRect imRect = k = imSprite2.L;
                                k.L -= this.v;
                                final ImRect imRect2 = imRect;
                                imRect2.N -= this.w;
                                final ImRect imRect3 = imRect;
                                imRect3.M -= this.v;
                                final ImRect imRect4 = imRect;
                                imRect4.O -= this.w;
                                final ImTrans r;
                                final ImTrans imTrans = r = imSprite2.R;
                                r.J -= this.v << 15;
                                final ImTrans imTrans2 = imTrans;
                                imTrans2.S -= this.w << 15;
                            }
                        }
                    }
                }
            }
            if (n5 != 0) {
                imIrgb.B(l, n6, n7, n8, this.close);
            }
            if (this.charAt != null && this.FI == 0) {
                ImButton.I(this.charAt, imIrgb, this.s);
            }
            if (b) {
                if (this.ZI != 0) {
                    return this.S();
                }
                this.s.B(0, 0, 0, 0);
                if (this.append) {
                    this.append = false;
                    this.t.I(0, 0, this.II, this.G);
                }
                else {
                    this.t.I(l, n6, n7, n8);
                }
            }
        }
        return this.B;
    }
    
    private boolean close(final ImIrgb imIrgb, final ImSprite imSprite) {
        int l = this.s.L;
        int n = this.s.N;
        int m = this.s.M;
        int o = this.s.O;
        final int n2 = m - l;
        final int n3 = o - n;
        int v = imSprite.V;
        final int n4 = l + m >> 1;
        final int n5 = n + o >> 1;
        final ImRect i = imSprite.L;
        final int j = i.L;
        final int n6 = i.N;
        final boolean b = v < 0;
        if (b) {
            v = -v;
        }
        int w = imSprite.W + 1;
        final int x = imSprite.X;
        if (w == x) {
            imSprite.V = 0;
        }
        imSprite.W = w;
        if (b) {
            w = x - w;
        }
        final int n7 = n2 * w / x;
        final int n8 = n3 * w / x;
        final int n9 = n7 >> 1;
        final int n10 = n8 >> 1;
        switch (v) {
            case 7: {
                n = n5 - n10;
                o = n5 + n10;
                break;
            }
            case 9: {
                l = n4 - n9;
                m = n4 + n9;
                n = n5 - n10;
                o = n5 + n10;
                break;
            }
            case 5: {
                l = n4 - n9;
                m = n4 + n9;
                break;
            }
            case 29: {
                this.w -= n3 - n8;
                o = n + n8;
                break;
            }
            case 30: {
                this.w -= n3 - n8;
                o = n + n8;
                this.v += n2 - n7;
                l = m - n7;
                break;
            }
            case 31: {
                this.w -= n3 - n8;
                o = n + n8;
                this.v -= n2 - n7;
                m = l + n7;
                break;
            }
            case 32: {
                this.v += n2 - n7;
                l = m - n7;
                break;
            }
            case 33: {
                this.v -= n2 - n7;
                m = l + n7;
                break;
            }
            case 34: {
                this.w += n3 - n8;
                n = o - n8;
                break;
            }
            case 35: {
                this.w += n3 - n8;
                n = o - n8;
                this.v += n2 - n7;
                l = m - n7;
                break;
            }
            case 36: {
                this.w += n3 - n8;
                n = o - n8;
                this.v -= n2 - n7;
                m = l + n7;
                break;
            }
            case 3: {
                o = n + n8;
                break;
            }
            case 2: {
                l = m - n7;
                break;
            }
            case 1: {
                m = l + n7;
                break;
            }
            case 4: {
                n = o - n8;
                break;
            }
            case 47: {
                final int n11 = w * 32768 / x;
                imSprite.j = n11;
                imSprite.s = n11;
                break;
            }
            case 48: {
                final int n12 = (x - w + 1) * 32768 / x;
                imSprite.j = n12;
                imSprite.s = n12;
                break;
            }
            case 53: {
                imSprite.I = w * 256 / x;
                break;
            }
            default: {
                if (this.a) {
                    break;
                }
                if (this.arraycopy == null) {
                    this.arraycopy = this.append();
                }
                if (this.arraycopy != null) {
                    this.a = true;
                    this.arraycopy(this.M.C, this.arraycopy.C, l, n, n2, n3);
                    break;
                }
                break;
            }
        }
        final int v2 = j - i.L;
        this.v = v2;
        final int w2 = n6 - i.N;
        this.w = w2;
        if (v2 != 0 || w2 != 0) {
            final ImRect imRect = i;
            imRect.L += v2;
            final ImRect imRect2 = i;
            imRect2.N += w2;
            final ImRect imRect3 = i;
            imRect3.M += v2;
            final ImRect imRect4 = i;
            imRect4.O += w2;
            final ImTrans r;
            final ImTrans imTrans = r = imSprite.R;
            r.J += v2 << 15;
            final ImTrans imTrans2 = imTrans;
            imTrans2.S += w2 << 15;
            boolean b2 = false;
            if (imIrgb.I(this.s, l, n, m, o) && imIrgb.I(imIrgb, imSprite.L)) {
                b2 = true;
            }
            if (!b2) {
                final ImRect imRect5 = i;
                imRect5.L -= v2;
                final ImRect imRect6 = i;
                imRect6.N -= w2;
                final ImRect imRect7 = i;
                imRect7.M -= v2;
                final ImRect imRect8 = i;
                imRect8.O -= w2;
                final ImTrans imTrans3 = imTrans;
                imTrans3.J -= v2 << 15;
                final ImTrans imTrans4 = imTrans;
                imTrans4.S -= w2 << 15;
            }
            return b2;
        }
        return imIrgb.I(imIrgb, l, n, m, o);
    }
    
    private void length(final ImIrgb imIrgb, final ImSprite imSprite) {
        int w = imSprite.W;
        final int x = imSprite.X;
        int v = imSprite.V;
        final ImIrgb arraycopy = this.arraycopy;
        final int l = imIrgb.L;
        final int n = imIrgb.N;
        final int m = imIrgb.M;
        final int o = imIrgb.O;
        if (v < 0) {
            v = -v;
            w = x - w;
            switch (v) {
                case 55: {
                    v = 56;
                    break;
                }
                case 56: {
                    v = 55;
                    break;
                }
                case 57: {
                    v = 58;
                    break;
                }
                case 58: {
                    v = 57;
                    break;
                }
                case 59: {
                    v = 60;
                    break;
                }
                case 60: {
                    v = 59;
                    break;
                }
            }
        }
        if (w == x) {
            return;
        }
        final ImRect s = this.s;
        int n2 = s.L + s.M >> 1;
        int n3 = s.N + s.O >> 1;
        int n4 = s.M - s.L;
        int n5 = s.O - s.N;
        final ImSprite k = imSprite.K;
        if (k != null) {
            if (this.j == null) {
                this.j = new ImRect();
            }
            final ImRect j = this.j;
            j.Z(k.L);
            for (ImSprite imSprite2 = k.F; imSprite2 != null; imSprite2 = imSprite2.N) {
                if (imSprite2.V != 0) {
                    j.C(imSprite2.L);
                }
            }
            n2 = j.L + j.M >> 1;
            n3 = j.N + j.O >> 1;
            n4 = j.M - j.L;
            n5 = j.O - j.N;
        }
        this.read(v, w, x, arraycopy, imIrgb, l, n, m, o, n2, n3, n4, n5);
    }
    
    private final void read(final int n, int n2, final int n3, final ImIrgb imIrgb, final ImIrgb imIrgb2, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, int n10, int n11) {
        int[] array = ImIplayer.AI;
        final int[] c = imIrgb2.C;
        final int d = imIrgb2.D;
        int[] c2;
        if (imIrgb != null) {
            c2 = imIrgb.C;
        }
        else {
            c2 = null;
        }
        if (n10 < 2) {
            n10 = 2;
        }
        if (n11 < 2) {
            n11 = 2;
        }
        final int n12 = 32768 / (n10 >> 1);
        final int n13 = 32768 / (n11 >> 1);
        int n14 = 32768 * n2 / n3;
        final int n15 = n2 * 16 / n3;
        int n16 = 0;
        switch (n) {
            case 55: {
                int n17 = n5 * d;
                n14 = 32768 - n14;
                final int n18 = 2 * ImBase.I(n14, n14);
                for (int i = n5; i < n7; ++i) {
                    final int n19 = (i - n9) * n13;
                    final int j = ImBase.I(n19, n19);
                    for (int k = n4; k < n6; ++k) {
                        final int n20 = (k - n8) * n12;
                        if (ImBase.I(n20, n20) + j <= n18) {
                            c[n17 + k] = c2[n17 + k];
                        }
                    }
                    n17 += d;
                }
                break;
            }
            case 56: {
                int n21 = n5 * d;
                final int n22 = 2 * ImBase.I(n14, n14);
                for (int l = n5; l < n7; ++l) {
                    final int n23 = (l - n9) * n13;
                    final int m = ImBase.I(n23, n23);
                    for (int n24 = n4; n24 < n6; ++n24) {
                        final int n25 = (n24 - n8) * n12;
                        if (ImBase.I(n25, n25) + m > n22) {
                            c[n21 + n24] = c2[n21 + n24];
                        }
                    }
                    n21 += d;
                }
                break;
            }
            case 57: {
                int n26 = n5 * d;
                n14 = 32768 - n14;
                final int n27 = 2 * n14;
                for (int n28 = n5; n28 < n7; ++n28) {
                    int n29 = (n28 - n9) * n13;
                    if (n29 < 0) {
                        n29 = -n29;
                    }
                    for (int n30 = n4; n30 < n6; ++n30) {
                        final int n31 = (n30 - n8) * n12;
                        final int n32 = n29;
                        int n33;
                        if (n31 < 0) {
                            n33 = n32 - n31;
                        }
                        else {
                            n33 = n32 + n31;
                        }
                        if (n33 < n27) {
                            c[n26 + n30] = c2[n26 + n30];
                        }
                    }
                    n26 += d;
                }
                break;
            }
            case 58: {
                int n34 = n5 * d;
                final int n35 = 2 * n14;
                for (int n36 = n5; n36 < n7; ++n36) {
                    int n37 = (n36 - n9) * n13;
                    if (n37 < 0) {
                        n37 = -n37;
                    }
                    for (int n38 = n4; n38 < n6; ++n38) {
                        final int n39 = (n38 - n8) * n12;
                        final int n40 = n37;
                        int n41;
                        if (n39 < 0) {
                            n41 = n40 - n39;
                        }
                        else {
                            n41 = n40 + n39;
                        }
                        if (n41 >= n35) {
                            c[n34 + n38] = c2[n34 + n38];
                        }
                    }
                    n34 += d;
                }
                break;
            }
            case 6: {
                n2 = n3 - n2;
                final int n42 = n10 * n2 / n3 / 2;
                if (imIrgb2.I(imIrgb2, n8 - n42, n5, n8 + n42, n7)) {
                    imIrgb2.I(imIrgb, 0, 0);
                    break;
                }
                break;
            }
            case 10: {
                n2 = n3 - n2;
                final int n43 = n10 * n2 / n3 / 2;
                final int n44 = n11 * n2 / n3 / 2;
                if (imIrgb2.I(imIrgb2, n8 - n43, n9 - n44, n8 + n43, n9 + n44)) {
                    imIrgb2.I(imIrgb, 0, 0);
                    break;
                }
                break;
            }
            case 8: {
                n2 = n3 - n2;
                final int n45 = n11 * n2 / n3 / 2;
                imIrgb2.B(n4, n9 - n45, n6, n9 + n45);
                imIrgb2.I(imIrgb, 0, 0);
                break;
            }
            case 23:
            case 25:
            case 51: {
                final int[] si = ImIplayer.SI;
                int n46 = n5 * d;
                for (int n47 = n5; n47 < n7; ++n47) {
                    final int n48 = (n47 & 0x3) << 2;
                    for (int n49 = n4; n49 < n6; ++n49) {
                        if (n15 <= si[n48 + (n49 & 0x3)]) {
                            c[n46 + n49] = c2[n46 + n49];
                        }
                    }
                    n46 += d;
                }
                break;
            }
            case 59: {
                n2 = n3 - n2;
            }
            case 60: {
                final int n50 = n10 * n2 / n3 / 2;
                final int n51 = n11 * n2 / n3 / 2;
                final int n52 = n8 - n50;
                final int n53 = n8 + n50;
                final int n54 = n9 - n51;
                final int n55 = n9 + n51;
                int n56 = n5 * d;
                for (int n57 = n5; n57 < n7; ++n57) {
                    final boolean b = n57 >= n54 && n57 < n55;
                    for (int n58 = n4; n58 < n6; ++n58) {
                        if (b || (n58 >= n52 && n58 < n53)) {
                            if (n == 59) {
                                c[n56 + n58] = c2[n56 + n58];
                            }
                        }
                        else if (n == 60) {
                            c[n56 + n58] = c2[n56 + n58];
                        }
                    }
                    n56 += d;
                }
                break;
            }
            case 28: {
                array = ImIplayer.SI;
            }
            case 49: {
                for (int n59 = n4; n59 < n6; ++n59) {
                    if (n15 <= array[n59 & 0xF]) {
                        int n60 = n5 * d + n59;
                        for (int n61 = n5; n61 < n7; ++n61) {
                            c[n60] = c2[n60];
                            n60 += d;
                        }
                    }
                }
                break;
            }
            case 27: {
                array = ImIplayer.SI;
            }
            case 37: {
                for (int n62 = n5; n62 < n7; ++n62) {
                    if (n15 <= array[n62 & 0xF]) {
                        int n63 = n62 * d + n4;
                        for (int n64 = n4; n64 < n6; ++n64) {
                            c[n63] = c2[n63];
                            ++n63;
                        }
                    }
                }
                break;
            }
            case 61: {
                int n65 = n5 * d;
                final int n66 = ImBase.I(n14, 102943) - 102943;
                for (int n67 = n5; n67 < n7; ++n67) {
                    final int n68 = (n67 - n9) * n13;
                    for (int n69 = n4; n69 < n6; ++n69) {
                        final int string = this.toString(-((n69 - n8) * n12), n68);
                        if (string >= n66 && string < -n66) {
                            final int n70 = n65 + n69;
                            c[n70] = c2[n70];
                        }
                    }
                    n65 += d;
                }
                break;
            }
            case 62: {
                n16 = 65536;
                break;
            }
            case 63: {
                n16 = 32768;
                break;
            }
            case 64: {
                n16 = 21845;
                break;
            }
            case 65: {
                n16 = 16384;
                break;
            }
            case 66: {
                n16 = 8192;
                break;
            }
        }
        if (n16 != 0) {
            final int i2 = ImBase.I(n16, 102943);
            final int n71 = ImBase.I(n14, i2) - 102943;
            int n72 = n5 * d;
            for (int n73 = n5; n73 < n7; ++n73) {
                final int n74 = (n73 - n9) * n13;
                for (int n75 = n4; n75 < n6; ++n75) {
                    int string2;
                    for (string2 = this.toString(-((n75 - n8) * n12), n74); string2 > i2 - 102943; string2 -= i2) {}
                    if (string2 >= n71) {
                        c[n72 + n75] = c2[n72 + n75];
                    }
                }
                n72 += d;
            }
        }
    }
    
    private final void setLength(final ImIrgb imIrgb, final ImSprite imSprite) {
        int z = imSprite.z + 1;
        final int c = imSprite.c;
        if (z == c) {
            imSprite.H -= c / imSprite.b;
            z = 0;
        }
        imSprite.z = z;
        if (z % imSprite.b == 0) {
            ++imSprite.H;
        }
        this.D = true;
    }
    
    private final int toString(final int n, final int n2) {
        final int n3 = 25735;
        final int n4 = 3 * n3;
        int n5 = n;
        if (n5 < 0) {
            n5 = -n5;
        }
        int n7;
        if (n2 >= 0) {
            int n6 = n2 + n5;
            if (n6 == 0) {
                n6 = 1;
            }
            n7 = n3 - ImBase.I(n3, (n2 - n5 << 15) / n6);
        }
        else {
            int n8 = n5 - n2;
            if (n8 == 0) {
                n8 = 1;
            }
            n7 = n4 - ImBase.I(n3, (n2 + n5 << 15) / n8);
        }
        if (n < 0) {
            return -n7;
        }
        return n7;
    }
    
    private final void append(final ImSprite imSprite, final int v, final int x, final int y) {
        imSprite.V = v;
        imSprite.W = 0;
        imSprite.X = x;
        imSprite.Y = y;
        for (ImSprite imSprite2 = imSprite.F; imSprite2 != null; imSprite2 = imSprite2.N) {
            this.append(imSprite2, v, x, y);
        }
    }
    
    private void arraycopy(final int[] array, final int[] array2, final int n, final int n2, final int n3, int n4) {
        final int ii = this.II;
        int n5 = n2 * ii + n;
        if (n3 == ii) {
            System.arraycopy(array, n5, array2, n5, n4 * ii);
        }
        else {
            while (n4-- > 0) {
                System.arraycopy(array, n5, array2, n5, n3);
                n5 += ii;
            }
        }
    }
    
    private final int charAt(int n) {
        if (n != 0) {
            n = n * this.u.I >> 15;
        }
        return n;
    }
    
    public final void Z(int q, int r) {
        if (q < 1) {
            q = 1;
        }
        if (r < 1) {
            r = 1;
        }
        if (q != this.q || r != this.r) {
            this.read(this.q = q, this.r = r);
        }
    }
    
    public final void C(final int n, final int n2) {
        if (this.FI == 0) {
            if (ImButton.I(this.charAt, n + n2) != null) {
                this.close();
            }
        }
        else {
            this.g += -n * this.u.I >> 12;
            this.h += -n2 * this.u.D >> 12;
        }
    }
    
    private final boolean close(int n, int n2, boolean b) {
        this.g = 0;
        this.h = 0;
        final int j = this.u.J;
        final int s = this.u.S;
        if (!this.length(j + (n << 15), s + (n2 << 15))) {
            return false;
        }
        n = this.u.J - j;
        n2 = this.u.S - s;
        if (!b && (n == 0 || n2 == 0)) {
            final int ii = this.II;
            final int g = this.G;
            if (this.N == null) {
                this.N = this.append();
                if (this.N == null) {
                    return false;
                }
            }
            this.arraycopy(this.M.C, this.N.C, 0, 0, ii, g);
            this.M.B(n, n2, n + ii, n2 + g);
            this.M.C(0, 0, ii, g);
            if (this.M.C()) {
                return true;
            }
            this.M.I(this.N, n, n2);
            final ImRect s2 = this.s;
            if (n < 0) {
                s2.B(ii + n, 0, ii, g);
            }
            else if (n > 0) {
                s2.B(0, 0, n, g);
            }
            else if (n2 < 0) {
                s2.B(0, g + n2, ii, g);
            }
            else if (n2 > 0) {
                s2.B(0, 0, ii, n2);
            }
            this.charAt(s2.L, s2.N, s2.M, s2.O, false);
            this.t.I(0, 0, ii, g);
            s2.B(0, 0, 0, 0);
        }
        else {
            b = true;
        }
        return b;
    }
    
    private final boolean length(int j, int s) {
        final int n = this.A * this.u.I;
        final int n2 = this.E * this.u.D;
        final int n3 = j + n;
        final int n4 = s + n2;
        final int n5 = this.II << 15;
        final int n6 = this.G << 15;
        if (n3 < n5 && j < 0) {
            j = n5 - (n3 - j);
        }
        else if (j > 0 && n3 >= n5) {
            j = 0;
        }
        if (n4 < n6 && s < 0) {
            s = n6 - (n4 - s);
        }
        else if (s > 0 && n4 >= n6) {
            s = 0;
        }
        final int n7 = j + n;
        final int n8 = s + n2;
        if (j >= 0 && n7 <= n5) {
            j = n5 - n >> 1;
        }
        if (s >= 0 && n8 <= n6) {
            s = n6 - n2 >> 1;
        }
        if (j != this.u.J || s != this.u.S) {
            this.u.J = j;
            this.start(j >> 15, s >> 15, j + n >> 15, (this.u.S = s) + n2 >> 15);
            return true;
        }
        return false;
    }
    
    private final void read() {
        final int n = this.A * this.u.I >> 15;
        final int n2 = this.E * this.u.D >> 15;
        final int n3 = this.II - n >> 1;
        final int n4 = this.G - n2 >> 1;
        this.u.J = n3 << 15;
        this.u.S = n4 << 15;
        this.start(n3, n4, n3 + n, n4 + n2);
        this.close();
    }
    
    private final void start(final int n, final int n2, final int n3, final int n4) {
        this.S.B(n, n2, n3, n4);
        this.S.C(0, 0, this.II, this.G);
        this.append |= (this.S.M - this.S.L != this.II || this.S.O - this.S.N != this.G);
    }
    
    private final void close() {
        this.s.Z(this.S);
    }
    
    final void read(int ii, int g) {
        if (this.q != 0) {
            if (this.A != 0) {
                ii = this.q;
                g = this.r;
                int e = (ii << 15) / this.A;
                final int n = (g << 15) / this.E;
                if (e > n) {
                    e = n;
                }
                this.FI = 0;
                this.u.I();
                this.u.F(e, e);
                this.e = e;
            }
            else {
                if (ii > this.q) {
                    ii = this.q;
                }
                if (g > this.r) {
                    g = this.r;
                }
            }
        }
        if ((ii != this.II || g != this.G) && this.A != 0) {
            this.ZI = 0;
            this.II = ii;
            this.G = g;
            if (this.M != null) {
                this.M.I();
                this.M = null;
            }
            if (this.N != null) {
                this.N.I();
                this.N = null;
            }
            if (this.arraycopy != null) {
                this.arraycopy.I();
                this.arraycopy = null;
            }
            if (this.DI != null) {
                this.DI.I();
                this.DI = null;
            }
            this.M = this.append();
        }
        this.read();
    }
    
    private final boolean setLength(final int n) {
        final ImSound imSound = this.m[n];
        return imSound != null && imSound.C != 0 && (!imSound.C() || imSound.F() < imSound.J);
    }
    
    private final void start() {
        for (int i = 0; i < 2; ++i) {
            final ImSound imSound = this.m[i];
            if (imSound != null) {
                final int c = imSound.C;
                if (c != 0) {
                    final ImRecord c2 = this.C(c);
                    final int e = c2.E();
                    int b = imSound.B;
                    int n = e - b;
                    if (n > 0) {
                        c2.Z(b);
                        final int z = imSound.Z;
                        final byte[] g = imSound.G;
                        while (n > 0 && imSound.I() >= 320) {
                            int n2 = n;
                            switch (z) {
                                case 1: {
                                    if (n2 > 65) {
                                        n2 = 65;
                                    }
                                    c2.I(g, 0, n2);
                                    imSound.K = 0;
                                    imSound.i = 8;
                                    imSound.C((n2 == 65) ? 320 : 160);
                                    break;
                                }
                                case 8: {
                                    if (n2 > 320) {
                                        n2 = 320;
                                    }
                                    c2.I(g, 0, n2);
                                    imSound.Z(g, n2);
                                    break;
                                }
                                case 16: {
                                    if (n2 > 640) {
                                        n2 = 640;
                                    }
                                    c2.I(g, 0, n2);
                                    imSound.I(g, n2);
                                    break;
                                }
                            }
                            b += n2;
                            n -= n2;
                        }
                        imSound.A();
                        imSound.B = b;
                    }
                    else if (imSound.F) {
                        imSound.B = imSound.D;
                    }
                    else {
                        imSound.A();
                    }
                }
            }
        }
    }
    
    private final void toString(final int n, final int c, final boolean f) {
        ImSound imSound = this.m[n];
        if (imSound == null) {
            imSound = new ImSound(this.t);
            imSound.I = n;
            this.m[n] = imSound;
        }
        int c2 = imSound.C;
        if (c2 != 0 && !imSound.F && !this.setLength(n)) {
            c2 = 0;
        }
        if (c == c2) {
            return;
        }
        final ImRecord c3 = this.C(c);
        if (c3 == null) {
            imSound.C = 0;
            imSound.S();
            return;
        }
        imSound.C = c;
        imSound.F = f;
        imSound.Z = c3.F();
        imSound.J = c3.D();
        imSound.B = c3.A();
        imSound.D = imSound.B;
        imSound.S();
        imSound.E();
        this.start();
        imSound.J();
    }
    
    private final void setLength() {
        for (int i = 0; i < 2; ++i) {
            this.start(i);
        }
    }
    
    private final void start(final int n) {
        final ImSound imSound = this.m[n];
        if (imSound != null) {
            imSound.C = 0;
            imSound.S();
        }
    }
    
    public final void B(final int n) {
        for (int i = 0; i < 2; ++i) {
            final ImSound imSound = this.m[i];
            if (imSound != null) {
                imSound.Z(n);
            }
        }
        for (int j = 1; j < this.p; ++j) {
            final ImSprite imSprite = this.o[j];
            if (imSprite != null && imSprite.T != null) {
                imSprite.T.Z(n);
            }
        }
    }
    
    private final void D() {
        int n = 0;
        if (this.b == 255) {
            n = (this.setLength(0) ? 1 : 0);
        }
        else if (this.b == 254) {
            n = (this.setLength(1) ? 1 : 0);
        }
        else if (this.b == 0) {
            n = ((this.setLength(0) || this.setLength(1)) ? 1 : 0);
            if (n == 0) {
                for (int i = 1; i < this.p; ++i) {
                    final ImSprite imSprite = this.o[i];
                    if (imSprite != null && imSprite.T != null && imSprite.T.Z()) {
                        n = 1;
                        break;
                    }
                }
            }
        }
        else if (this.b < this.p) {
            final ImSprite imSprite2 = this.o[this.b];
            if (imSprite2 != null && imSprite2.T != null) {
                n = (imSprite2.T.Z() ? 1 : 0);
            }
        }
        if (n == 0) {
            this.f = 0;
        }
    }
    
    private final void I(final ImIrgb imIrgb, final ImSprite imSprite, ImRecord c) {
        ImTrans imTrans = imSprite.R;
        final int e = imTrans.E;
        if (e == 0) {
            return;
        }
        imIrgb.F = e;
        int l = imSprite.L.L;
        int n = imSprite.L.N;
        int n2 = imSprite.L.M - l;
        int n3 = imSprite.L.O - n;
        final int z = imSprite.Z;
        final int n4 = imSprite.H + 7;
        int n5 = imSprite.J;
        int n6 = imSprite.S;
        int n7 = imSprite.E;
        int n8 = imSprite.A;
        ImIrgb close = null;
        if (imSprite.C != 0) {
            final ImRecord c2 = this.C(imSprite.C);
            if (c2 != null) {
                close = this.close(imSprite.C, c2);
                if (close != null) {
                    final int d = close.D;
                    final int z2 = close.Z;
                    n5 = this.charAt(n5);
                    n6 = this.charAt(n6);
                    if (n7 == 0) {
                        n7 = d;
                        n8 = z2;
                    }
                    ImTrans k = ImIrgb.K;
                    if (k == null) {
                        k = (ImIrgb.K = new ImTrans());
                    }
                    k.I();
                    n7 = this.charAt(n7);
                    n8 = this.charAt(n8);
                    ++n7;
                    ++n8;
                    k.F((d << 15) / n7, (z2 << 15) / n8);
                    k.S(-(l + n5), -(n + n6));
                    imTrans = imSprite.R;
                }
            }
            c = this.C(n4);
        }
        final int i = c.I(4);
        switch (i) {
            case 1:
            case 2: {
                final ImIrgb close2 = this.close(n4, c);
                if (close2 != null) {
                    if (imSprite.Q != 0) {
                        if (n7 != 0) {
                            l -= n2 * n5 >> 10;
                            n2 = n2 * n7 >> 10;
                        }
                        if (n8 != 0) {
                            n -= n3 * n6 >> 10;
                            n3 = n3 * n8 >> 10;
                        }
                    }
                    imIrgb.I(close2, l, n, n2, n3);
                    break;
                }
                imIrgb.C(l, n, n2, n3, this.close);
                break;
            }
            case 5: {
                c.Z(8);
                if (c.B() == 0) {
                    imIrgb.I(l, n, l + n2 - 1, n + n3 - 1, z);
                    break;
                }
                imIrgb.I(l, n + n3 - 1, l + n2 - 1, n, z);
                break;
            }
            case 6: {
                imIrgb.I(l, n, n2, n3, z, false, close);
                break;
            }
            case 7: {
                imIrgb.I(l, n, n2, n3, z, true, close);
                break;
            }
            case 12:
            case 17: {
                imIrgb.I(c, l, n, n2, n3, z, false, 0, i == 17, imTrans, close, null);
                break;
            }
            case 13: {
                if (c.E() <= 28 && !imTrans.Z()) {
                    c.Z(12);
                    int c3 = c.C();
                    int c4 = c.C();
                    int n9 = 0;
                    while (!c.I()) {
                        final int c5 = c.C();
                        final int c6 = c.C();
                        if (c5 != c3 && c6 != c4) {
                            break;
                        }
                        ++n9;
                        c3 = c5;
                        c4 = c6;
                    }
                    if (n9 == 4) {
                        imIrgb.I(l + 1, n + 1, n2 - 2, n3 - 2, z, close);
                        break;
                    }
                }
                imIrgb.I(c, l, n, n2, n3, z, true, 0, false, imTrans, close, null);
                break;
            }
            case 18: {
                imIrgb.I(c, l, n, n2, n3, z, true, 0, true, imTrans, close, null);
                break;
            }
            case 8: {
                imIrgb.Z(l, n, n2, n3, z);
                break;
            }
            case 9: {
                imIrgb.I(l, n, n2, n3, z, close);
                break;
            }
            case 10: {
                imIrgb.Z(l, n, n2, n3, z);
                break;
            }
            case 11: {
                imIrgb.I(l, n, n2, n3, z, close);
                break;
            }
            case 14: {
                imIrgb.I(c, l, n, n2, n3, z, false, 1, false, imTrans, close, null);
                break;
            }
            case 15: {
                ImVi t = imSprite.T;
                if (t == null) {
                    c.Z(12);
                    final StringBuffer sb = new StringBuffer();
                    c.I(sb, c.F());
                    t = this.t.Z(sb.toString());
                    if (t != null) {
                        imSprite.T = t;
                    }
                }
                if (t != null) {
                    if (!t.Z()) {
                        t.start();
                    }
                    t.I(l, n, n2, n3);
                    break;
                }
                break;
            }
        }
        imIrgb.F = 256;
    }
    
    private final void D(final int n) {
        if (n >= this.p) {
            return;
        }
        final ImSprite imSprite = this.o[n];
        if (imSprite != null) {
            imSprite.Z();
            if (imSprite.T != null) {
                this.t.I(imSprite.T);
                imSprite.T = null;
            }
            while (imSprite.F != null) {
                imSprite.F.Z();
            }
        }
    }
    
    private final void F() {
        for (int i = 1; i < this.p; ++i) {
            final ImSprite imSprite = this.o[i];
            if (imSprite != null) {
                imSprite.I();
                imSprite.K = null;
                imSprite.F = null;
                imSprite.N = null;
                if (imSprite.T != null) {
                    this.t.I(imSprite.T);
                    imSprite.T = null;
                }
            }
        }
    }
    
    final ImSprite F(final int p) {
        if (p >= this.p) {
            final int p2 = p + 50;
            final ImSprite[] o = new ImSprite[p2];
            System.arraycopy(this.o, 0, o, 0, this.p);
            this.o = o;
            this.p = p2;
        }
        ImSprite imSprite = this.o[p];
        if (imSprite == null) {
            imSprite = new ImSprite();
            imSprite.P = p;
            this.o[p] = imSprite;
        }
        return imSprite;
    }
    
    private final void I(final int n, int n2, int n3) {
        final ImSprite f = this.F(n);
        ImSprite f2 = f.F;
        while (n3-- != 0) {
            final ImSprite imSprite = this.o[n2];
            if (imSprite != null) {
                imSprite.N = f2;
                imSprite.K = f;
                f2 = imSprite;
            }
            ++n2;
        }
        f.F = f2;
    }
    
    final void Z(final int n, final int j, final int s) {
        final ImSprite f = this.F(n);
        f.j = j;
        f.s = s;
    }
    
    final void I(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.Z(n, (n2 << 15) / n3, (n4 << 15) / n5);
    }
    
    private final void I(final ImSprite imSprite, final ImTrans imTrans) {
        final ImTrans r = imSprite.R;
        ImSprite imSprite2 = imSprite.F;
        if (imSprite2 != null) {
            r.I(imTrans);
            r.S(imSprite.d, imSprite.f);
            r.F(imSprite.j, imSprite.s);
            r.B(imSprite.M);
            r.C(imSprite.I);
            do {
                this.I(imSprite2, r);
                imSprite2 = imSprite2.N;
            } while (imSprite2 != null);
        }
        final int i = imSprite.I(imTrans);
        if (i != imSprite.D || imSprite.V != 0 || imSprite.i != 0) {
            imSprite.D = i;
            this.s.C(imSprite.L);
            if (imSprite.H == 0) {
                imSprite.L.B(0, 0, 0, 0);
            }
            else {
                final ImRecord c = this.C(imSprite.H + 7);
                if (c != null) {
                    final int j = c.J();
                    final int k = c.J();
                    final int f = c.F();
                    c.F();
                    int b = c.B();
                    int b2 = c.B();
                    if (imSprite.Q != 0) {
                        if (j != 0) {
                            b = imSprite.U * b / j;
                        }
                        if (k != 0 && f != 5) {
                            b2 = imSprite.G * b2 / k;
                        }
                    }
                    r.I(imTrans);
                    r.C(imSprite.I);
                    if (r.E == 0) {
                        imSprite.L.B(0, 0, 0, 0);
                        return;
                    }
                    r.S(imSprite.d, imSprite.f);
                    r.F(imSprite.j, imSprite.s);
                    r.B(imSprite.M);
                    r.S(-b, -b2);
                    final boolean b3 = f == 17 || f == 18;
                    final boolean b4 = b3 || f == 12 || f == 13 || f == 14;
                    final int u = imSprite.U;
                    int g = imSprite.G;
                    if (r.I == 32768 || r.S == 32768) {
                        final int l = r.J;
                        final int s = r.S;
                        r.J = (l & 0xFFFF8000);
                        r.S = (s & 0xFFFF8000);
                    }
                    int n = 0;
                    int n2 = 0;
                    int n3 = u;
                    int n4 = g;
                    if (b4) {
                        --n;
                        --n2;
                        ++n3;
                        ++n4;
                    }
                    if (g == 0) {
                        g = 1;
                    }
                    final ImRect m = imSprite.L;
                    m.D(r.C(n, n2), r.D(n, n2), r.C(n3, n4), r.D(n3, n4));
                    if (b4) {
                        if (r.Z()) {
                            m.F(r.C(n3, n2), r.D(n3, n2), r.C(n, n4), r.D(n, n4));
                        }
                        int n5 = (u << 15) / j;
                        int n6 = (g << 15) / k;
                        if (b3) {
                            n5 >>= 3;
                            n6 >>= 3;
                        }
                        r.F(n5, n6);
                    }
                    this.s.C(m);
                }
            }
        }
    }
    
    public final int J() {
        this.start();
        if (this.U) {
            while (this.length()) {}
            this.U = false;
        }
        if (this.length) {
            return this.B;
        }
        if (this.z != 0 || this.c != 0) {
            this.start(this.z, this.c);
        }
        boolean b = this.read != null;
        boolean close = true;
        if (this.l != 0) {
            this.C = this.arraycopy(this.l);
            this.l = 0;
        }
        if (this.start.length() != 0) {
            this.C = this.I(this.start);
            this.start.setLength(0);
        }
        if (this.C != -1) {
            this.I(this.C);
            this.C = -1;
            this.f = 0;
            this.J = 0;
        }
        if (this.ZI != 0) {
            return this.S();
        }
        if (this.J != 0) {
            --this.J;
            b = false;
            close = (this.D || !this.s.C());
        }
        if (this.f != 0) {
            if (this.f == 4) {
                this.D();
            }
            if (this.f != 0) {
                b = false;
                if ((!this.D && this.s.C()) || this.f == 5) {
                    close = false;
                }
            }
        }
        int i = this.FI - this.JI;
        if (i != 0) {
            close = true;
            this.JI = this.FI;
            while (i > 0) {
                this.S(49152);
                --i;
            }
            while (i < 0) {
                this.S(21845);
                ++i;
            }
            if (this.FI == 0) {
                this.u.I();
                this.u.F(this.e, this.e);
                this.read();
            }
        }
        if (this.g != 0 || this.h != 0) {
            close = this.close(this.g, this.h, close);
        }
        if (!b || this.C() || this.C == -1) {}
        if (!this.x && this.f == 0 && this.J < 20) {
            return 1;
        }
        if (close) {
            return this.B();
        }
        return this.B;
    }
    
    final boolean I(final ImRecord imRecord, final int n, final StringBuffer sb, final int n2) {
        imRecord.Z(4 + n * 8);
        final int s = imRecord.S();
        imRecord.C(4);
        final int n3 = imRecord.S() - s;
        final int length = sb.length();
        if (n3 == length - n2) {
            imRecord.Z(s);
            int n4;
            for (n4 = n2; n4 < length && sb.charAt(n4) == (char)imRecord.F(); ++n4) {}
            if (n4 == length) {
                return true;
            }
        }
        return false;
    }
    
    final int S() {
        int[] array = ImIplayer.SI;
        final int ii = this.II;
        final int[] c = this.N.C;
        final int[] c2 = this.M.C;
        final int l = this.s.L;
        final int n = this.s.N;
        final int n2 = this.s.M - l;
        final int n3 = this.s.O - n;
        final int bi = this.BI;
        if (bi == 16) {
            this.arraycopy(c, c2, l, n, n2, n3);
            this.s.B(0, 0, 0, 0);
            this.t.I(l, n, n2, n3);
            this.ZI = 0;
            return this.B;
        }
        switch (this.ZI) {
            case 7: {
                final int n4 = n3 / 2;
                final int n5 = (n4 + 16 - 1) / 16;
                final int n6 = n5 * bi;
                this.I(l, n + n4 - n6, n2, n5);
                this.I(l, n + n4 + n6 + 1, n2, n5);
                break;
            }
            case 9:
            case 47: {
                final int n7 = n2 * (bi + 1) / 16;
                final int n8 = n3 * (bi + 1) / 16;
                this.I(l + (n2 - n7) / 2, n + (n3 - n8) / 2, n7, n8);
                break;
            }
            case 5: {
                final int n9 = n2 / 2;
                final int n10 = (n9 + 16 - 1) / 16;
                final int n11 = n10 * bi;
                this.I(l + n9 - n11, n, n10, n3);
                this.I(l + n9 + n11 + 1, n, n10, n3);
                break;
            }
            case 67: {
                int n12 = -1;
                for (int i = n; i < n + n3; i += 24) {
                    this.I(bi, l, i, n2, 24, n12, 0, false, true);
                    n12 = -n12;
                }
                break;
            }
            case 68: {
                int n13 = -1;
                for (int j = l; j < l + n2; j += 24) {
                    this.I(bi, j, n, 24, n3, 0, n13, false, true);
                    n13 = -n13;
                }
                break;
            }
            case 29: {
                this.I(bi, l, n, n2, n3, 0, 1, false, false);
                break;
            }
            case 30: {
                this.I(bi, l, n, n2, n3, -1, 1, false, false);
                break;
            }
            case 31: {
                this.I(bi, l, n, n2, n3, 1, 1, false, false);
                break;
            }
            case 32: {
                this.I(bi, l, n, n2, n3, -1, 0, false, false);
                break;
            }
            case 33: {
                this.I(bi, l, n, n2, n3, 1, 0, false, false);
                break;
            }
            case 34: {
                this.I(bi, l, n, n2, n3, 0, -1, false, false);
                break;
            }
            case 35: {
                this.I(bi, l, n, n2, n3, -1, -1, false, false);
                break;
            }
            case 36: {
                this.I(bi, l, n, n2, n3, 1, -1, false, false);
                break;
            }
            case 3: {
                this.I(bi, l, n, n2, n3, 0, 1, true, false);
                break;
            }
            case 6: {
                final int n14 = (n2 / 2 + 16 - 1) / 16;
                final int n15 = n14 * bi;
                this.I(l + n15, n, n14, n3);
                this.I(l + n2 - n15, n, n14, n3);
                break;
            }
            case 10: {
                final int n16 = 32;
                final int n17 = n2 / n16 + 1;
                final int n18 = n3 / n16 + 1;
                final int n19 = n2 * bi / n16;
                final int n20 = n3 * bi / n16;
                this.I(l + n19, n, n17, n3);
                this.I(l + n2 - n19, n, n17, n3);
                this.I(l, n + n20, n2, n18);
                this.I(l, n + n3 - n20, n2, n18);
                break;
            }
            case 8: {
                final int n21 = (n3 / 2 + 16 - 1) / 16;
                final int n22 = n21 * bi;
                this.I(l, n + n22, n2, n21);
                this.I(l, n + n3 - n22, n2, n21);
                break;
            }
            case 2: {
                this.I(bi, l, n, n2, n3, -1, 0, true, false);
                break;
            }
            case 11: {
                this.I(bi, l, n, n2, n3, -1, 0, false, true);
                break;
            }
            case 12: {
                this.I(bi, l, n, n2, n3, 1, 0, false, true);
                break;
            }
            case 13: {
                this.I(bi, l, n, n2, n3, 0, 1, false, true);
                break;
            }
            case 14: {
                this.I(bi, l, n, n2, n3, 0, -1, false, true);
                break;
            }
            case 15: {
                this.I(bi, l, n, n2, n3, 0, -1, true, true);
                break;
            }
            case 22: {
                this.I(bi, l, n, n2, n3, -1, -1, true, true);
                break;
            }
            case 16: {
                this.I(bi, l, n, n2, n3, 1, -1, true, true);
                break;
            }
            case 17: {
                this.I(bi, l, n, n2, n3, 1, 0, true, true);
                break;
            }
            case 18: {
                this.I(bi, l, n, n2, n3, 1, 1, true, true);
                break;
            }
            case 19: {
                this.I(bi, l, n, n2, n3, 0, 1, true, true);
                break;
            }
            case 20: {
                this.I(bi, l, n, n2, n3, -1, 1, true, true);
                break;
            }
            case 21: {
                this.I(bi, l, n, n2, n3, -1, 0, true, true);
                break;
            }
            case 1: {
                this.I(bi, l, n, n2, n3, 1, 0, true, false);
                break;
            }
            case 4: {
                this.I(bi, l, n, n2, n3, 0, -1, true, false);
                break;
            }
            case 49: {
                array = ImIplayer.AI;
            }
            case 28: {
                for (int k = array[bi]; k < n2; k += 16) {
                    this.arraycopy(c, c2, l + k, n, 1, n3);
                }
                break;
            }
            case 37: {
                array = ImIplayer.AI;
            }
            case 27: {
                for (int n23 = array[bi]; n23 < n3; n23 += 16) {
                    this.arraycopy(c, c2, l, n + n23, n2, 1);
                }
                break;
            }
            default: {
                int n24 = bi;
                final int n25 = n + n3;
                final int n26 = l + n2;
                for (int n27 = n; n27 + 3 < n25; n27 += 4) {
                    for (int n28 = l; n28 + 3 < n26; n28 += 4) {
                        final int n29 = array[n24];
                        final int n30 = (n27 + (n29 >> 2)) * ii + (n28 + (n29 & 0x3));
                        c2[n30] = c[n30];
                        n24 = (n24 + 1 & 0xF);
                    }
                }
                break;
            }
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66: {
                if (bi == 0) {
                    if (this.DI == null) {
                        this.DI = this.append();
                    }
                    if (this.DI != null) {
                        this.arraycopy(c2, this.DI.C, l, n, n2, n3);
                    }
                }
                if (this.DI != null) {
                    this.arraycopy(c, c2, l, n, n2, n3);
                    this.read(this.ZI, bi, 16, this.DI, this.M, l, n, l + n2, n + n3, l + (n2 >> 1), n + (n3 >> 1), n2, n3);
                    break;
                }
                break;
            }
        }
        this.t.I(l, n, n2, n3);
        ++this.BI;
        return this.CI;
    }
    
    private final void I(final int n, final int n2, final int n3, final int n4) {
        this.I(n, n2, n3, n4, 0, 0);
    }
    
    private final void I(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.M.B(n, n2, n + n3, n2 + n4);
        if (this.M.I(this.M, this.s)) {
            this.M.I(this.N, n5, n6);
        }
    }
    
    private final void I(final int n, final int n2, final int n3, int n4, int n5, final int n6, final int n7, final boolean b, final boolean b2) {
        if (n2 + n4 > this.II) {
            n4 = this.II - n2;
        }
        if (n3 + n5 > this.G) {
            n5 = this.G - n3;
        }
        int n8;
        int n9;
        int n10;
        if (n6 == 0) {
            n8 = n4;
            n9 = n2;
            n10 = 0;
        }
        else {
            n8 = (n + 1) * n4 / 16;
            if (n6 > 0) {
                n9 = n2;
                n10 = n8 - n4;
            }
            else {
                n9 = n2 + n4 - n8;
                n10 = n4 - n8;
            }
        }
        int n11;
        int n12;
        int n13;
        if (n7 == 0) {
            n11 = n5;
            n12 = n3;
            n13 = 0;
        }
        else {
            n11 = (n + 1) * n5 / 16;
            if (n7 > 0) {
                n12 = n3;
                n13 = n11 - n5;
            }
            else {
                n12 = n3 + n5 - n11;
                n13 = n5 - n11;
            }
        }
        if (b2) {
            final boolean b3 = b && n6 != 0 && n7 != 0;
            if (b3) {
                if (n6 > 0) {
                    this.I(n2, n3, n8, n5);
                }
                else {
                    this.I(n9, n3, n8, n5);
                }
                if (n7 > 0) {
                    this.I(n2, n3, n4, n11);
                }
                else {
                    this.I(n2, n12, n4, n11);
                }
            }
            if (n == 0) {
                if (this.DI == null) {
                    this.DI = this.append();
                }
                if (this.DI != null) {
                    this.arraycopy(this.M.C, this.DI.C, n2, n3, n4, n5);
                }
            }
            final int ii = this.II;
            if (n2 + n4 > ii) {
                n4 = ii - n2;
            }
            final int g = this.G;
            if (n3 + n5 > g) {
                n5 = g - n3;
            }
            this.M.B(n2, n3, n4, n5);
            final int n14 = n10 + n6 * n4;
            final int n15 = n13 + n7 * n5;
            if (this.DI != null && this.M.I(this.M, n14, n15, n14 + n2 + n4, n15 + n3 + n5)) {
                this.M.I(this.DI, n14, n15);
            }
            if (b3) {
                return;
            }
        }
        if (b) {
            n10 = 0;
            n13 = 0;
        }
        this.I(n9, n12, n8, n11, n10, n13);
    }
    
    public final void J(final int n) {
        this.FI += n;
        if (this.FI > 10) {
            this.FI = 10;
        }
        else if (this.FI < 0) {
            this.FI = 0;
        }
    }
    
    private final void S(final int n) {
        final int n2 = this.II << 14;
        final int n3 = this.G << 14;
        final int n4 = (this.u.J - n2) / this.u.I;
        final int n5 = (this.u.S - n3) / this.u.D;
        this.u.F(n, n);
        this.length(n2 + n4 * this.u.I, n3 + n5 * this.u.D);
    }
    
    static {
        SI = new int[] { 0, 11, 2, 6, 14, 7, 4, 9, 15, 1, 10, 5, 12, 3, 8, 13 };
        AI = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
    }
}
