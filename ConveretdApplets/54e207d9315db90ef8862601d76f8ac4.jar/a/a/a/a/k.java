// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class k
{
    int u;
    int ad;
    aq byte;
    int long;
    byte[][] try;
    byte[][] N;
    short[][] Q;
    int R;
    int D;
    int[] I;
    int[] T;
    int[] s;
    int[] d;
    int[] U;
    int[] if;
    int r;
    int[] o;
    int[] case;
    int[] F;
    int int;
    int l;
    int ae;
    int S;
    int ac;
    int y;
    int aa;
    int a;
    int f;
    int do;
    int[] g;
    int else;
    int Z;
    int h;
    int O;
    int E;
    byte[][] af;
    b[] V;
    ax[] t;
    ax[] c;
    int b;
    int[] m;
    int q;
    int X;
    int i;
    boolean[] w;
    byte[] z;
    int P;
    long new;
    int e;
    int M;
    int G;
    int p;
    int C;
    int j;
    int[] L;
    b[] A;
    b[] goto;
    short[] ab;
    int[] n;
    byte[] v;
    int[] char;
    int[] B;
    int for;
    long[] k;
    long[] x;
    byte[] Y;
    byte[] W;
    short[] void;
    int K;
    boolean J;
    int H;
    
    int do(final int n, final int n2) {
        return n + (1 << n2 - 1) >> n2;
    }
    
    short a(final short n) {
        if ((n & 0xFF00) != 0x0) {
            return (short)(~n >> 15 & 0xFF);
        }
        return n;
    }
    
    void a(final short[] array, final byte[] array2, final int n) {
        int n2 = 0;
        for (int i = 7; i >= 0; --i) {
            if ((array[1 + n2] | array[2 + n2] | array[3 + n2] | array[4 + n2] | array[5 + n2] | array[6 + n2] | array[7 + n2]) == 0x0) {
                final short n3 = (short)(array[n2] << 2);
                array[1 + n2] = (array[0 + n2] = n3);
                array[3 + n2] = (array[2 + n2] = n3);
                array[5 + n2] = (array[4 + n2] = n3);
                array[7 + n2] = (array[6 + n2] = n3);
                n2 += 8;
            }
            else {
                final short n4 = array[2 + n2];
                final short n5 = array[6 + n2];
                final short n6 = (short)((n4 + n5) * 4433);
                final short n7 = (short)(n6 + n5 * -15137);
                final short n8 = (short)(n6 + n4 * 6270);
                final int n9 = array[n2] + array[4 + n2] << 13;
                final int n10 = array[n2] - array[4 + n2] << 13;
                final short n11 = (short)(n9 + n8);
                final short n12 = (short)(n9 - n8);
                final short n13 = (short)(n10 + n7);
                final short n14 = (short)(n10 - n7);
                final short n15 = array[7 + n2];
                final short n16 = array[5 + n2];
                final short n17 = array[3 + n2];
                final short n18 = array[1 + n2];
                final short n19 = (short)(n15 + n18);
                final short n20 = (short)(n16 + n17);
                final short n21 = (short)(n15 + n17);
                final short n22 = (short)(n16 + n18);
                final short n23 = (short)((n21 + n22) * 9633);
                final short n24 = (short)(n15 * 2446);
                final short n25 = (short)(n16 * 16819);
                final short n26 = (short)(n17 * 25172);
                final short n27 = (short)(n18 * 12299);
                final short n28 = (short)(n19 * -7373);
                final short n29 = (short)(n20 * -20995);
                final short n30 = (short)(n21 * -16069);
                final short n31 = (short)(n22 * -3196);
                final short n32 = (short)(n30 + n23);
                final short n33 = (short)(n31 + n23);
                final short n34 = (short)(n24 + (n28 + n32));
                final short n35 = (short)(n25 + (n29 + n33));
                final short n36 = (short)(n26 + (n29 + n32));
                final short n37 = (short)(n27 + (n28 + n33));
                array[0 + n2] = (short)this.do(n11 + n37, 11);
                array[7 + n2] = (short)this.do(n11 - n37, 11);
                array[1 + n2] = (short)this.do(n13 + n36, 11);
                array[6 + n2] = (short)this.do(n13 - n36, 11);
                array[2 + n2] = (short)this.do(n14 + n35, 11);
                array[5 + n2] = (short)this.do(n14 - n35, 11);
                array[3 + n2] = (short)this.do(n12 + n34, 11);
                array[4 + n2] = (short)this.do(n12 - n34, 11);
                n2 += 8;
            }
        }
        int n38 = 0;
        int n39 = 0;
        for (int j = 7; j >= 0; --j) {
            if ((array[8 + n38] | array[16 + n38] | array[24 + n38] | array[32 + n38] | array[40 + n38] | array[48 + n38] | array[56 + n38]) == 0x0) {
                int n40;
                if ((n40 = (short)((short)this.do(array[n38], 5) + 128)) < 0) {
                    n40 = 0;
                }
                else if (n40 > 255) {
                    n40 = 255;
                }
                array2[0 + n39 + n] = (byte)n40;
                array2[8 + n39 + n] = (byte)n40;
                array2[16 + n39 + n] = (byte)n40;
                array2[24 + n39 + n] = (byte)n40;
                array2[32 + n39 + n] = (byte)n40;
                array2[40 + n39 + n] = (byte)n40;
                array2[48 + n39 + n] = (byte)n40;
                array2[56 + n39 + n] = (byte)n40;
                ++n38;
                ++n39;
            }
            else {
                final short n41 = array[16 + n38];
                final short n42 = array[48 + n38];
                final short n43 = (short)((n41 + n42) * 4433);
                final short n44 = (short)(n43 + n42 * -15137);
                final short n45 = (short)(n43 + n41 * 6270);
                final int n46 = array[0 + n38] + array[32 + n38] << 13;
                final int n47 = array[0 + n38] - array[32 + n38] << 13;
                final short n48 = (short)(n46 + n45);
                final short n49 = (short)(n46 - n45);
                final short n50 = (short)(n47 + n44);
                final short n51 = (short)(n47 - n44);
                final short n52 = array[56 + n38];
                final short n53 = array[40 + n38];
                final short n54 = array[24 + n38];
                final short n55 = array[8 + n38];
                final short n56 = (short)(n52 + n55);
                final short n57 = (short)(n53 + n54);
                final short n58 = (short)(n52 + n54);
                final short n59 = (short)(n53 + n55);
                final short n60 = (short)((n58 + n59) * 9633);
                final short n61 = (short)(n52 * 2446);
                final short n62 = (short)(n53 * 16819);
                final short n63 = (short)(n54 * 25172);
                final short n64 = (short)(n55 * 12299);
                final short n65 = (short)(n56 * -7373);
                final short n66 = (short)(n57 * -20995);
                final short n67 = (short)(n58 * -16069);
                final short n68 = (short)(n59 * -3196);
                final short n69 = (short)(n67 + n60);
                final short n70 = (short)(n68 + n60);
                final short n71 = (short)(n61 + (n65 + n69));
                final short n72 = (short)(n62 + (n66 + n70));
                final short n73 = (short)(n63 + (n66 + n69));
                final short n74 = (short)(n64 + (n65 + n70));
                array2[0 + n39 + n] = (byte)this.a((short)(this.do(n48 + n74, 18) + 128));
                array2[56 + n39 + n] = (byte)this.a((short)(this.do(n48 - n74, 18) + 128));
                array2[8 + n39 + n] = (byte)this.a((short)(this.do(n50 + n73, 18) + 128));
                array2[48 + n39 + n] = (byte)this.a((short)(this.do(n50 - n73, 18) + 128));
                array2[16 + n39 + n] = (byte)this.a((short)(this.do(n51 + n72, 18) + 128));
                array2[40 + n39 + n] = (byte)this.a((short)(this.do(n51 - n72, 18) + 128));
                array2[24 + n39 + n] = (byte)this.a((short)(this.do(n49 + n71, 18) + 128));
                array2[32 + n39 + n] = (byte)this.a((short)(this.do(n49 - n71, 18) + 128));
                ++n38;
                ++n39;
            }
        }
    }
    
    public void long() {
        if (this.byte != null) {
            this.byte = null;
        }
        for (int i = 0; i < 100; ++i) {
            this.af[i] = null;
        }
    }
    
    public void do(final int k) throws Exception {
        this.K = k;
        this.long();
        throw new Exception();
    }
    
    public byte[] int(final int n) throws Exception {
        int n2;
        for (n2 = 0; n2 < 100 && this.af[n2] != null; ++n2) {}
        if (n2 == 100) {
            this.do(-221);
        }
        final byte[] array = new byte[n];
        if (array == null) {
            this.do(-233);
        }
        return this.af[n2] = array;
    }
    
    public void a(final byte[] array, final int n, final short n2, int i) {
        final byte b = (byte)(n2 & 0xFF);
        final byte b2 = (byte)(n2 >> 8 & 0xFF);
        while (i > 0) {
            array[2 * i - 1 + n] = b2;
            array[2 * i - 2 + n] = b;
            --i;
        }
    }
    
    public void goto() throws Exception {
        this.X = 0;
        this.q = 0;
        if (this.w[0]) {
            return;
        }
        do {
            final int a = this.byte.a(this.z, this.X + 128, 4096 - this.X, this.w);
            if (a == -1) {
                this.do(-232);
            }
            this.X += a;
        } while (this.X < 4096 && !this.w[0]);
        this.H += this.X;
        this.a(this.z, this.X + this.q + 128, (short)(-9729), 64);
    }
    
    public void m() throws Exception {
        final byte[] array = new byte[17];
        final byte[] array2 = new byte[256];
        int i = this.for(16);
        if (i < 2) {
            this.do(-202);
        }
        i -= 2;
        while (i != 0) {
            final int for1 = this.for(8);
            array[0] = 0;
            byte b = 0;
            for (int j = 1; j <= 16; ++j) {
                array[j] = (byte)this.for(8);
                b += array[j];
            }
            if (b > 255) {
                this.do(-200);
            }
            for (byte b2 = 0; b2 < b; ++b2) {
                array2[b2] = (byte)this.for(8);
            }
            final byte b3 = (byte)(17 + b);
            if (i < b3) {
                this.do(-202);
            }
            i -= b3;
            if ((for1 & 0x10) > 16) {
                this.do(-201);
            }
            final int n = (for1 & 0xF) + ((for1 & 0x10) >> 4) * 4;
            if (n >= 8) {
                this.do(-201);
            }
            if (this.try[n] == null) {
                this.try[n] = this.int(17);
            }
            if (this.N[n] == null) {
                this.N[n] = this.int(256);
            }
            System.arraycopy(array, 0, this.try[n], 0, 17);
            System.arraycopy(array2, 0, this.N[n], 0, 256);
        }
    }
    
    public void e() throws Exception {
        int i = this.for(16);
        if (i < 2) {
            this.do(-203);
        }
        int n3;
        for (i -= 2; i != 0; i -= n3) {
            final int for1 = this.for(8);
            final int n = for1 >> 4;
            final int n2 = for1 & 0xF;
            if (n2 >= 4) {
                this.do(-204);
            }
            if (this.Q[n2] == null) {
                this.Q[n2] = new short[64];
            }
            for (int j = 0; j < 64; ++j) {
                int for2 = this.for(8);
                if (n != 0) {
                    for2 = (for2 << 8) + this.for(8);
                }
                this.Q[n2][j] = (short)for2;
            }
            n3 = 65;
            if (n != 0) {
                n3 += 64;
            }
            if (i < n3) {
                this.do(-219);
            }
        }
    }
    
    public void r() throws Exception {
        final int for1 = this.for(16);
        if (this.for(8) != 8) {
            this.do(-205);
        }
        this.ad = this.for(16);
        if (this.ad < 1 || this.ad > 32696) {
            this.do(-206);
        }
        this.u = this.for(16);
        if (this.u < 1 || this.u > 32696) {
            this.do(-207);
        }
        this.D = this.for(8);
        if (this.D > 4) {
            this.do(-208);
        }
        if (for1 != this.D * 3 + 8) {
            this.do(-209);
        }
        for (int i = 0; i < this.D; ++i) {
            this.d[i] = this.for(8);
            this.I[i] = this.for(4);
            this.T[i] = this.for(4);
            this.s[i] = this.for(8);
        }
    }
    
    public void int() throws Exception {
        int i = this.for(16);
        if (i < 2) {
            this.do(-210);
        }
        for (i -= 2; i != 0; --i) {
            this.for(8);
        }
    }
    
    public void for() throws Exception {
        if (this.for(16) != 4) {
            this.do(-211);
        }
        this.e = this.for(16);
    }
    
    public void f() throws Exception {
        int i = this.for(16);
        final int for1 = this.for(8);
        this.r = for1;
        i -= 3;
        if (i != for1 * 2 + 3 || for1 < 1 || for1 > 4) {
            this.do(-212);
        }
        for (int j = 0; j < for1; ++j) {
            final int for2 = this.for(8);
            final int for3 = this.for(8);
            i -= 2;
            int n;
            for (n = 0; n < this.D && for2 != this.d[n]; ++n) {}
            if (n >= this.D) {
                this.do(-213);
            }
            this.o[j] = n;
            this.case[n] = (for3 >> 4 & 0xF);
            this.F[n] = (for3 & 0xF) + 4;
        }
        this.int = this.for(8);
        this.l = this.for(8);
        this.S = this.for(4);
        this.ae = this.for(4);
        if (this.long == 0) {
            this.int = 0;
            this.l = 63;
        }
        for (i -= 3; i != 0; --i) {
            this.for(8);
        }
    }
    
    public int C() throws Exception {
        int n = 0;
        int i;
        while (true) {
            ++n;
            if (this.for(8) == 255) {
                do {
                    i = this.for(8);
                } while (i == 255);
                if (i != 0) {
                    break;
                }
                continue;
            }
        }
        return i;
    }
    
    public int h() throws Exception {
        int c = 0;
    Label_0256:
        while (true) {
            c = this.C();
            switch (c) {
                case 192:
                case 193:
                case 194:
                case 195:
                case 197:
                case 198:
                case 199:
                case 201:
                case 202:
                case 203:
                case 205:
                case 206:
                case 207:
                case 216:
                case 217:
                case 218: {
                    break Label_0256;
                }
                case 196: {
                    this.m();
                    continue;
                }
                case 204: {
                    this.do(-215);
                    continue;
                }
                case 219: {
                    this.e();
                    continue;
                }
                case 221: {
                    this.for();
                    continue;
                }
                case 1:
                case 200:
                case 208:
                case 209:
                case 210:
                case 211:
                case 212:
                case 213:
                case 214:
                case 215: {
                    this.do(-216);
                    continue;
                }
                default: {
                    this.int();
                    continue;
                }
            }
        }
        return c;
    }
    
    public void new() throws Exception {
        final int for1 = this.for(8);
        int n = this.for(8);
        if (for1 == 255 && n == 216) {
            return;
        }
        int n2 = 512;
        int n3;
        do {
            if (--n2 == 0) {
                this.do(-217);
            }
            n3 = n;
            n = this.for(8);
        } while (n3 != 255 || n != 216);
        if ((int)(this.new >> 8 & 0xFFL) != 255) {
            this.do(-217);
        }
    }
    
    public void z() throws Exception {
        this.new();
        switch (this.h()) {
            case 194: {
                this.long = 1;
            }
            case 192:
            case 193: {
                this.r();
                break;
            }
            case 201: {
                this.do(-215);
                break;
            }
            default: {
                this.do(-218);
                break;
            }
        }
    }
    
    public int t() throws Exception {
        final int h = this.h();
        if (h == 217) {
            return 0;
        }
        if (h != 218) {
            this.do(-216);
        }
        this.f();
        return 1;
    }
    
    public void if(final aq byte1) throws Exception {
        this.K = 0;
        this.J = false;
        final boolean b = false;
        this.ad = (b ? 1 : 0);
        this.u = (b ? 1 : 0);
        this.byte = byte1;
        this.long = 0;
        this.R = 0;
        this.D = 0;
        this.r = 0;
        this.int = 0;
        this.l = 0;
        this.ae = 0;
        this.S = 0;
        this.ac = 0;
        this.y = 0;
        this.aa = 0;
        this.a = 0;
        this.f = 0;
        this.do = 0;
        this.else = 0;
        this.Z = 0;
        this.h = 0;
        this.O = 0;
        this.E = 0;
        this.b = 0;
        this.q = 0;
        this.X = 0;
        this.w[0] = false;
        this.i = 0;
        this.e = 0;
        this.M = 0;
        this.G = 0;
        this.p = 0;
        this.C = 0;
        this.j = 0;
        this.v = null;
        this.H = 0;
        this.goto();
        this.P = 16;
        this.new = 0L;
        this.for(16);
        this.for(16);
        for (int i = 0; i < 12288; ++i) {
            this.n[i] = 64;
        }
    }
    
    public int a(final double n) {
        return (int)(n * 65536.0 + 0.5);
    }
    
    public void d() {
        for (int i = 0; i <= 255; ++i) {
            final int n = i * 2 - 256;
            this.char[i] = this.a(0.701) * n + 32768 >> 16;
            this.B[i] = this.a(0.886) * n + 32768 >> 16;
            this.k[i] = -this.a(0.35707) * n;
            this.x[i] = -this.a(0.17207) * n + 32768;
        }
    }
    
    public void v() throws Exception {
        if (this.P == 16) {
            this.a((byte)(this.new >> 16 & 0xFFL));
        }
        if (this.P >= 8) {
            this.a((byte)(this.new >> 24 & 0xFFL));
        }
        this.a((byte)(this.new & 0xFFL));
        this.a((byte)(this.new >> 8 & 0xFFL));
        this.if(this.P = 16);
        this.if(16);
    }
    
    public void c() {
        final short[] ab = this.ab;
        final byte[] v = this.v;
        int n = 0;
        int n2 = 0;
        for (int i = this.a; i > 0; --i) {
            System.arraycopy(ab, n, this.void, 0, 64);
            this.a(this.void, v, n2);
            n += 64;
            n2 += 64;
        }
    }
    
    public ax a(final int do1, final int if1, final int a, final int new1) throws Exception {
        final ax ax = new ax();
        ax.do = do1;
        ax.if = if1;
        ax.a = a;
        ax.new = new1;
        ax.int = a * new1 * 2;
        ax.for = this.int(ax.int * do1 * if1);
        return ax;
    }
    
    public int a(final ax ax, final int n, final int n2) throws Exception {
        if (n >= ax.do) {
            this.do(-229);
        }
        if (n2 >= ax.if) {
            this.do(-229);
        }
        return n * ax.int + n2 * (ax.int * ax.do);
    }
    
    public void q() throws Exception {
        int n = 0;
        final int[] array = new int[4];
        for (int i = 0; i < 4; ++i) {
            array[i] = 0;
        }
        for (int j = 0; j < this.f; ++j) {
            int n2 = 0;
            int n3 = 0;
            int k = 0;
        Label_0410:
            while (k < this.aa) {
                final int n4 = this.g[k];
                final short[] ab = this.ab;
                final int n5 = n * 64;
                final short[] array2 = this.Q[this.s[n4]];
                final byte[] for1 = this.c[n4].for;
                final int a = this.a(this.c[n4], array[n4] + n2, this.m[n4] + n3);
                final byte[] for2 = this.t[n4].for;
                final int a2 = this.a(this.t[n4], array[n4] + n2, this.m[n4] + n3);
                ab[0 + n5] = (short)((for2[0 + a2] & 0xFF) + ((for2[1 + a2] & 0xFF) << 8));
                for (int l = 0; l < 63; ++l) {
                    ab[1 + n5 + l] = (short)(((for1[2 * (1 + l) + a + 1] & 0xFF) << 8) + (for1[2 * (1 + l) + a] & 0xFF));
                }
                while (true) {
                    for (int n6 = 63; n6 > 0; --n6) {
                        if (ab[cb.o[n6] + n5] != 0) {
                            while (n6 >= 0) {
                                if (ab[cb.o[n6] + n5] != 0) {
                                    final short[] array3 = ab;
                                    final int n7 = cb.o[n6] + n5;
                                    array3[n7] *= array2[n6];
                                }
                                --n6;
                            }
                            ++n;
                            if (this.r == 1) {
                                final int[] array4 = array;
                                final int n8 = n4;
                                ++array4[n8];
                            }
                            else if (++n2 == this.I[n4]) {
                                n2 = 0;
                                if (++n3 == this.T[n4]) {
                                    n3 = 0;
                                    final int[] array5 = array;
                                    final int n9 = n4;
                                    array5[n9] += this.I[n4];
                                }
                            }
                            ++k;
                            continue Label_0410;
                        }
                    }
                    continue;
                }
            }
        }
        if (this.r == 1) {
            final int[] m = this.m;
            final int n10 = this.o[0];
            ++m[n10];
        }
        else {
            for (int n11 = 0; n11 < this.r; ++n11) {
                final int n12 = this.o[n11];
                final int[] m2 = this.m;
                final int n13 = n12;
                m2[n13] += this.T[n12];
            }
        }
    }
    
    int if(final int n, final int n2) {
        return (n < cb.E[n2]) ? (n + cb.byte[n2]) : n;
    }
    
    int a(final int n, final int n2) {
        return (n < cb.E[n2]) ? (n + cb.byte[n2]) : n;
    }
    
    public void try() throws Exception {
        int n = 0;
        for (int i = 0; i < this.f; ++i) {
            if (this.e != 0 && this.M == 0) {
                this.j();
            }
            for (int j = 0; j < this.aa; ++j) {
                final int n2 = this.g[j];
                final short[] ab = this.ab;
                final int n3 = n * 64;
                final short[] array = this.Q[this.s[n2]];
                int n4;
                if ((n4 = this.a(this.V[this.case[n2]])) != 0) {
                    n4 = this.if(this.if(n4), n4);
                }
                ab[0 + n3] = (short)((this.L[n2] += n4) * array[0]);
                final int n5 = this.n[n];
                final b b = this.V[this.F[n2]];
                int k;
                for (k = 1; k < 64; ++k) {
                    final int a = this.a(b);
                    final int n6 = a >> 4;
                    final int n7 = a & 0xF;
                    if (n7 != 0) {
                        if (n6 != 0) {
                            if (k + n6 > 63) {
                                this.do(-227);
                            }
                            if (k < n5) {
                                for (int l = (n6 < n5 - k) ? n6 : (n5 - k), n8 = k; l != 0; --l, ab[cb.o[n8++] + n3] = 0) {}
                            }
                            k += n6;
                        }
                        ab[cb.o[k] + n3] = (short)(this.if(this.if(n7), n7) * array[k]);
                    }
                    else {
                        if (n6 != 15) {
                            break;
                        }
                        if (k + 15 > 63) {
                            this.do(-227);
                        }
                        if (k < n5) {
                            for (int n9 = (16 < n5 - k) ? 16 : (n5 - k), n10 = k; n9 != 0; --n9, ab[cb.o[n10++] + n3] = 0) {}
                        }
                        k += 15;
                    }
                }
                if (k < n5) {
                    for (int n11 = k; n11 < n5; ab[cb.o[n11++] + n3] = 0) {}
                }
                this.n[n] = k;
                ++n;
            }
            --this.M;
        }
    }
    
    public void a(final int n, final b b) {
        final byte[] array = new byte[257];
        final int[] array2 = new int[257];
        int n2 = 0;
        for (int i = 1; i <= 16; ++i) {
            for (byte b2 = 1; b2 <= this.try[n][i]; ++b2) {
                array[n2++] = (byte)i;
            }
        }
        array[n2] = 0;
        final int n3 = n2;
        int n4 = 0;
        byte b3 = array[0];
        int n5 = 0;
        while (array[n5] != 0) {
            while (array[n5] == b3) {
                array2[n5++] = n4;
                ++n4;
            }
            n4 <<= 1;
            ++b3;
        }
        int n6 = 0;
        for (int j = 0; j < 256; ++j) {
            b.if[j] = 0;
            b.do[j] = 0;
            b.a[n6++] = 0;
            b.a[n6++] = 0;
        }
        int n7 = -1;
        for (int k = 0; k < n3; ++k) {
            final int n8 = this.N[n][k] & 0xFF;
            final int n9 = array2[k];
            final byte b4 = array[k];
            b.do[n8] = b4;
            if (b4 <= 8) {
                int n10 = n9 << 8 - b4;
                for (int l = 1 << 8 - b4; l > 0; --l) {
                    b.if[n10] = n8;
                    ++n10;
                }
            }
            else {
                final int n11 = n9 >> b4 - 8 & 0xFF;
                int n12 = b.if[n11];
                if (n12 == 0) {
                    n12 = (b.if[n11] = n7);
                    n7 -= 2;
                }
                int n13 = n9 << 16 - (b4 - 8);
                for (int n14 = b4; n14 > 9; --n14) {
                    if ((n13 & 0x8000) == 0x0) {
                        --n12;
                    }
                    if (b.a[-n12 - 1] == 0) {
                        b.a[-n12 - 1] = n7;
                        n12 = n7;
                        n7 -= 2;
                    }
                    else {
                        n12 = b.a[-n12 - 1];
                    }
                    n13 <<= 1;
                }
                if ((n13 & 0x8000) == 0x0) {
                    --n12;
                }
                b.a[-n12 - 1] = n8;
            }
        }
    }
    
    public void B() throws Exception {
        for (int i = 0; i < this.r; ++i) {
            if (this.Q[this.s[this.o[i]]] == null) {
                this.do(-222);
            }
        }
    }
    
    public void A() throws Exception {
        for (int i = 0; i < this.r; ++i) {
            if (this.int == 0 && this.try[this.case[this.o[i]]] == null) {
                this.do(-223);
            }
            if (this.l > 0 && this.try[this.F[this.o[i]]] == null) {
                this.do(-223);
            }
        }
        for (int j = 0; j < 8; ++j) {
            if (this.try[j] != null) {
                if (this.V[j] == null) {
                    this.V[j] = new b();
                }
                this.a(j, this.V[j]);
            }
        }
        for (int k = 0; k < this.aa; ++k) {
            this.A[k] = this.V[this.case[this.g[k]]];
            this.goto[k] = this.V[this.F[this.g[k]]];
        }
    }
    
    public void g() {
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < this.D; ++i) {
            if (this.I[i] > n) {
                n = this.I[i];
            }
            if (this.T[i] > n2) {
                n2 = this.T[i];
            }
        }
        for (int j = 0; j < this.D; ++j) {
            this.U[j] = ((this.u * this.I[j] + (n - 1)) / n + 7) / 8;
            this.if[j] = ((this.ad * this.T[j] + (n2 - 1)) / n2 + 7) / 8;
        }
        if (this.r == 1) {
            this.f = this.U[this.o[0]];
            this.do = this.if[this.o[0]];
        }
        else {
            this.f = ((this.u + 7) / 8 + (n - 1)) / n;
            this.do = ((this.ad + 7) / 8 + (n2 - 1)) / n2;
        }
        if (this.r == 1) {
            this.g[0] = this.o[0];
            this.aa = 1;
        }
        else {
            this.aa = 0;
            for (int k = 0; k < this.r; ++k) {
                for (int n3 = this.o[k], l = this.I[n3] * this.T[n3]; l != 0; --l, this.g[this.aa++] = n3) {}
            }
        }
    }
    
    public int l() throws Exception {
        if (this.t() == 0) {
            return 0;
        }
        this.g();
        this.A();
        this.B();
        for (int i = 0; i < this.D; ++i) {
            this.L[i] = 0;
        }
        this.b = 0;
        if (this.e != 0) {
            this.M = this.e;
            this.G = 0;
        }
        this.v();
        return 1;
    }
    
    public void b() throws Exception {
        if (this.D == 1) {
            this.R = 0;
            this.C = 1;
            this.ac = 8;
            this.y = 8;
        }
        else if (this.D == 3) {
            if (this.I[1] != 1 || this.T[1] != 1 || this.I[2] != 1 || this.T[2] != 1) {
                this.do(-226);
            }
            if (this.I[0] == 1 && this.T[0] == 1) {
                this.R = 1;
                this.C = 3;
                this.ac = 8;
                this.y = 8;
            }
            else if (this.I[0] == 2 && this.T[0] == 1) {
                this.R = 2;
                this.C = 4;
                this.ac = 16;
                this.y = 8;
            }
            else if (this.I[0] == 1 && this.T[0] == 2) {
                this.R = 3;
                this.C = 4;
                this.ac = 8;
                this.y = 16;
            }
            else if (this.I[0] == 2 && this.T[0] == 2) {
                this.R = 4;
                this.C = 6;
                this.ac = 16;
                this.y = 16;
            }
            else {
                this.do(-226);
            }
        }
        else {
            this.do(-225);
        }
        this.p = (this.u + (this.ac - 1)) / this.ac;
        this.j = (this.ad + (this.y - 1)) / this.y;
        if (this.R == 0) {
            this.E = 1;
        }
        else {
            this.E = 4;
        }
        this.O = (this.u + 15 & 0xFFF0) * this.E;
        this.h = this.u * this.E;
        this.Y = this.int(this.O + 8);
        this.W = this.int(this.O + 8);
        this.a = this.p * this.C;
        if (this.a > 12288) {
            this.do(-229);
        }
        this.ab = new short[this.a * 64 + 4];
        for (int i = 0; i < this.a; ++i) {
            this.n[i] = 64;
        }
        this.v = this.int(this.a * 64 + 8);
        this.else = this.ad;
        this.Z = 0;
        this.d();
    }
    
    public void j() throws Exception {
        int byte1 = 0;
        int n;
        for (n = 1536; n > 0 && this.byte() != 255; --n) {}
        if (n == 0) {
            this.do(-228);
        }
        while (n > 0 && (byte1 = this.byte()) == 255) {
            --n;
        }
        if (n == 0) {
            this.do(-228);
        }
        if (byte1 != this.G + 208) {
            this.do(-228);
        }
        for (int i = 0; i < this.D; ++i) {
            this.L[i] = 0;
        }
        this.b = 0;
        this.M = this.e;
        this.G = (this.G + 1 & 0x7);
        this.if(this.P = 16);
        this.if(16);
    }
    
    public void n() throws Exception {
        if (this.l() == 0) {
            this.do(-216);
        }
    }
    
    public void if() throws Exception {
        this.b();
        if (this.long != 0) {
            this.K = -234;
            throw new Exception();
        }
        this.n();
    }
    
    public void a(final aq aq) throws Exception {
        this.if(aq);
        this.z();
    }
    
    public void k() {
        final int n = this.y - this.Z;
        final byte[] y = this.Y;
        final byte[] w = this.W;
        final byte[] v = this.v;
        final byte[] v2 = this.v;
        int n2 = 0;
        int n3 = 0;
        int n4;
        if (n < 8) {
            n4 = n * 8;
        }
        else {
            n4 = 128 + (n & 0x7) * 8;
        }
        int n5 = 256 + (n >> 1) * 8;
        for (int i = this.p; i > 0; --i) {
            for (int j = 0; j < 2; ++j) {
                for (int k = 0; k < 8; k += 2) {
                    final int n6 = v2[0 + n5] & 0xFF;
                    final int n7 = v2[64 + n5] & 0xFF;
                    final int n8 = this.char[n7];
                    final int n9 = (int)(this.k[n7] + this.x[n6] >> 16);
                    final int n10 = this.B[n6];
                    final int n11 = v[k + n4] & 0xFF;
                    y[0 + n2] = this.a(n11 + n8);
                    y[1 + n2] = this.a(n11 + n9);
                    y[2 + n2] = this.a(n11 + n10);
                    final int n12 = v[k + 1 + n4] & 0xFF;
                    y[4 + n2] = this.a(n12 + n8);
                    y[5 + n2] = this.a(n12 + n9);
                    y[6 + n2] = this.a(n12 + n10);
                    final int n13 = v[k + 8 + n4] & 0xFF;
                    w[0 + n3] = this.a(n13 + n8);
                    w[1 + n3] = this.a(n13 + n9);
                    w[2 + n3] = this.a(n13 + n10);
                    final int n14 = v[k + 8 + 1 + n4] & 0xFF;
                    w[4 + n3] = this.a(n14 + n8);
                    w[5 + n3] = this.a(n14 + n9);
                    w[6 + n3] = this.a(n14 + n10);
                    n2 += 8;
                    n3 += 8;
                    ++n5;
                }
                n4 += 64;
            }
            n4 += 256;
            n5 += 376;
        }
    }
    
    public void do() {
        final int n = this.y - this.Z;
        final byte[] y = this.Y;
        final byte[] v = this.v;
        final byte[] v2 = this.v;
        int n2 = n * 8;
        int n3 = 128 + n * 8;
        int n4 = 0;
        for (int i = this.p; i > 0; --i) {
            for (int j = 0; j < 2; ++j) {
                for (int k = 0; k < 4; ++k) {
                    final int n5 = v2[0 + n3] & 0xFF;
                    final int n6 = v2[64 + n3] & 0xFF;
                    final int n7 = this.char[n6];
                    final int n8 = (int)(this.k[n6] + this.x[n5] >> 16);
                    final int n9 = this.B[n5];
                    final int n10 = v[(k << 1) + n2] & 0xFF;
                    y[0 + n4] = this.a(n10 + n7);
                    y[1 + n4] = this.a(n10 + n8);
                    y[2 + n4] = this.a(n10 + n9);
                    final int n11 = v[(k << 1) + 1 + n2] & 0xFF;
                    y[4 + n4] = this.a(n11 + n7);
                    y[5 + n4] = this.a(n11 + n8);
                    y[6 + n4] = this.a(n11 + n9);
                    n4 += 8;
                    ++n3;
                }
                n2 += 64;
            }
            n2 += 128;
            n3 += 248;
        }
    }
    
    public void x() {
        final int n = this.y - this.Z;
        final byte[] y = this.Y;
        final byte[] w = this.W;
        int n2 = 0;
        int n3 = 0;
        final byte[] v = this.v;
        final byte[] v2 = this.v;
        int n4;
        if (n < 8) {
            n4 = n * 8;
        }
        else {
            n4 = 64 + (n & 0x7) * 8;
        }
        int n5 = 128 + (n >> 1) * 8;
        for (int i = this.p; i > 0; --i) {
            for (int j = 0; j < 8; ++j) {
                final int n6 = v2[0 + j + n5] & 0xFF;
                final int n7 = v2[64 + j + n5] & 0xFF;
                final int n8 = this.char[n7];
                final int n9 = (int)(this.k[n7] + this.x[n6] >> 16);
                final int n10 = this.B[n6];
                final int n11 = v[j + n4] & 0xFF;
                y[0 + n2] = this.a(n11 + n8);
                y[1 + n2] = this.a(n11 + n9);
                y[2 + n2] = this.a(n11 + n10);
                final int n12 = v[8 + j + n4] & 0xFF;
                w[0 + n3] = this.a(n12 + n8);
                w[1 + n3] = this.a(n12 + n9);
                w[2 + n3] = this.a(n12 + n10);
                n2 += 4;
                n3 += 4;
            }
            n4 += 256;
            n5 += 256;
        }
    }
    
    public void void() {
        final int n = this.y - this.Z;
        final byte[] y = this.Y;
        int n2 = n * 8;
        int n3 = 0;
        final byte[] v = this.v;
        for (int i = this.p; i > 0; --i) {
            for (int j = 0; j < 8; ++j) {
                final int n4 = v[j + n2] & 0xFF;
                final int n5 = v[64 + j + n2] & 0xFF;
                final int n6 = v[128 + j + n2] & 0xFF;
                y[0 + n3] = this.a(n4 + this.char[n6]);
                y[1 + n3] = this.a((int)(n4 + (this.k[n6] + this.x[n5] >> 16)));
                y[2 + n3] = this.a(n4 + this.B[n5]);
                n3 += 4;
            }
            n2 += 192;
        }
    }
    
    public void p() {
        final int n = this.y - this.Z;
        final byte[] y = this.Y;
        final byte[] v = this.v;
        int n2 = 0;
        int n3 = n * 8;
        for (int i = this.p; i > 0; --i) {
            y[0 + n2] = v[0 + n3];
            y[1 + n2] = v[1 + n3];
            y[2 + n2] = v[2 + n3];
            y[3 + n2] = v[3 + n3];
            y[4 + n2] = v[4 + n3];
            y[5 + n2] = v[5 + n3];
            y[6 + n2] = v[6 + n3];
            y[7 + n2] = v[7 + n3];
            n3 += 64;
            n2 += 8;
        }
    }
    
    public void case() throws Exception {
        if (this.long == 0) {
            this.for(this.P = 16);
            this.for(16);
            this.h();
        }
        this.H -= this.X;
    }
    
    public int a(final long n, final byte b) {
        return (int)(n << b | n >> 32 - b);
    }
    
    public int byte() throws Exception {
        if (this.X == 0) {
            this.goto();
            if (this.X == 0) {
                final int i = this.i;
                this.i ^= 0x1;
                if (i != 0) {
                    return 217;
                }
                return 255;
            }
        }
        final int n = this.z[this.q + 128] & 0xFF;
        ++this.q;
        --this.X;
        return n;
    }
    
    public int a(final boolean[] array) throws Exception {
        if (this.X == 0) {
            this.goto();
            if (this.X == 0) {
                array[0] = true;
                final int i = this.i;
                this.i ^= 0x1;
                if (i != 0) {
                    return 217;
                }
                return 255;
            }
        }
        array[0] = false;
        final int n = this.z[this.q + 128] & 0xFF;
        ++this.q;
        --this.X;
        return n;
    }
    
    public void a(final byte b) {
        --this.q;
        this.z[this.q + 128] = b;
        ++this.X;
    }
    
    public byte char() throws Exception {
        final boolean[] array = { false };
        final int a = this.a(array);
        if (a != 255) {
            return (byte)a;
        }
        if (array[0]) {
            return -1;
        }
        final int a2 = this.a(array);
        if (array[0]) {
            this.a((byte)(-1));
            return -1;
        }
        if (a2 == 0) {
            return -1;
        }
        this.a((byte)a2);
        this.a((byte)(-1));
        return -1;
    }
    
    public int for(int n) throws Exception {
        final int n2 = (int)(this.new >> 16 - n & (1 << n) - 1);
        final int p = this.P - n;
        this.P = p;
        if (p <= 0) {
            this.new = this.a(this.new, (byte)(n += this.P));
            this.new = ((this.new & 0xFFFFL) | this.byte() << 24 | this.byte() << 16);
            this.new = this.a(this.new << 32 >>> 32, (byte)(-this.P));
            this.P += 16;
        }
        else {
            this.new = this.a(this.new << 32 >>> 32, (byte)n);
        }
        this.new = this.new << 32 >>> 32;
        return n2;
    }
    
    public int if(int n) throws Exception {
        final int n2 = (int)this.new >> 16 - n & (1 << n) - 1;
        final int p = this.P - n;
        this.P = p;
        if (p <= 0) {
            this.new = this.a(this.new, (byte)(n += this.P));
            this.new = ((this.new & 0xFFFFL) | (this.char() & 0xFF) << 24 | (this.char() & 0xFF) << 16);
            this.new = this.a(this.new << 32 >>> 32, (byte)(-this.P));
            this.P += 16;
        }
        else {
            this.new = this.a(this.new << 32 >>> 32, (byte)n);
        }
        this.new = this.new << 32 >>> 32;
        return n2;
    }
    
    public int a(final b b) throws Exception {
        int i = b.if[(int)(this.new >> 8 & 0xFFL)];
        if (i < 0) {
            this.if(8);
            do {
                i = b.a[~i + (1 - this.if(1))];
            } while (i < 0);
        }
        else {
            this.if(b.do[i]);
        }
        return i;
    }
    
    public byte a(int n) {
        if ((n & 0xFFFFFF00) != 0x0) {
            n = (~n >> 31 & 0xFF);
        }
        return (byte)n;
    }
    
    k(final aq aq) {
        this.try = new byte[8][];
        this.N = new byte[8][];
        this.Q = new short[4][];
        this.I = new int[4];
        this.T = new int[4];
        this.s = new int[4];
        this.d = new int[4];
        this.U = new int[4];
        this.if = new int[4];
        this.o = new int[4];
        this.case = new int[4];
        this.F = new int[4];
        this.g = new int[10];
        this.af = new byte[100][];
        this.V = new b[8];
        this.t = new ax[4];
        this.c = new ax[4];
        this.m = new int[4];
        this.w = new boolean[1];
        this.z = new byte[4480];
        this.L = new int[4];
        this.A = new b[10];
        this.goto = new b[10];
        this.n = new int[12288];
        this.char = new int[256];
        this.B = new int[256];
        this.k = new long[256];
        this.x = new long[256];
        this.void = new short[64];
        try {
            this.a(aq);
        }
        catch (Exception ex) {}
    }
    
    int u() {
        if (this.J) {
            return 0;
        }
        if (this.K != 0) {
            return -1;
        }
        try {
            this.if();
        }
        catch (Exception ex) {
            return -1;
        }
        this.J = true;
        return 0;
    }
    
    int a(final byte[][] array, final int[] array2) {
        if (this.K != 0 || !this.J) {
            return -1;
        }
        if (this.else == 0) {
            return 1;
        }
        if (this.Z == 0) {
            try {
                if (this.long != 0) {
                    this.q();
                }
                else {
                    this.try();
                }
                if (this.else <= this.y) {
                    this.case();
                }
                this.c();
                this.Z = this.y;
            }
            catch (Exception ex) {}
        }
        switch (this.R) {
            case 4: {
                if ((this.Z & 0x1) == 0x0) {
                    this.k();
                    array[0] = this.Y;
                    break;
                }
                array[0] = this.W;
                break;
            }
            case 2: {
                this.do();
                array[0] = this.Y;
                break;
            }
            case 3: {
                if ((this.Z & 0x1) == 0x0) {
                    this.x();
                    array[0] = this.Y;
                    break;
                }
                array[0] = this.W;
                break;
            }
            case 1: {
                this.void();
                array[0] = this.Y;
                break;
            }
            case 0: {
                this.p();
                array[0] = this.Y;
                break;
            }
        }
        array2[0] = this.h;
        --this.Z;
        --this.else;
        return 0;
    }
    
    int i() {
        return this.K;
    }
    
    int o() {
        return this.u;
    }
    
    int a() {
        return this.ad;
    }
    
    int w() {
        return this.D;
    }
    
    int s() {
        return this.E;
    }
    
    int y() {
        return this.u * this.s();
    }
    
    int else() {
        return this.H;
    }
}
