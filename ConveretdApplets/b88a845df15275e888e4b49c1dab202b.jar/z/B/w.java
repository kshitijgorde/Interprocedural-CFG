// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

class w
{
    private int P;
    private int T;
    private byte[] V;
    private int K;
    private int R;
    private int C;
    private int J;
    private int[] F;
    private int[] G;
    private int L;
    private int U;
    private int I;
    private int W;
    private int E;
    static int[] Q;
    static int[] O;
    static byte[] M;
    static short[] H;
    static short[] A;
    static short[] D;
    static short[] B;
    static short[] N;
    static byte[] S;
    
    public w(final int c, final int k, final int r) {
        this.J = 0;
        this.L = 0;
        this.U = 2;
        this.I = 0;
        this.W = 0;
        this.C = c;
        this.K = k;
        this.R = r;
        this.P = 0;
        this.T = 0;
        this.F = new int[k];
        this.G = new int[k];
    }
    
    public void A(final byte[] array, final byte[] v, final int n, final int n2) {
        this.V = v;
        int n3 = 0;
        final int n4 = (this.K + 7) / 8;
        this.P = 0;
        this.T = 0;
        for (int i = 0; i < n2; ++i) {
            this.A(array, n3, n);
            n3 += n4;
        }
    }
    
    public void A(final byte[] array, final int n, int i) {
        int j = 1;
        this.J = 0;
        while (i < this.K) {
            while (j != 0) {
                final int a = this.A(10);
                final short n2 = w.H[a];
                final short n3 = (short)(n2 & 0x1);
                final int n4 = n2 >>> 1 & 0xF;
                if (n4 == 12) {
                    final short n5 = w.A[(a << 2 & 0xC) | this.B(2)];
                    final int n6 = n5 >>> 1 & 0x7;
                    i += (n5 >>> 4 & 0xFFF);
                    this.C(4 - n6);
                }
                else {
                    if (n4 == 0) {
                        throw new Error(m.A("TIFFFaxDecoder0"));
                    }
                    if (n4 == 15) {
                        throw new Error(m.A("TIFFFaxDecoder1"));
                    }
                    i += (n2 >>> 5 & 0x7FF);
                    this.C(10 - n4);
                    if (n3 != 0) {
                        continue;
                    }
                    j = 0;
                    this.G[this.J++] = i;
                }
            }
            if (i == this.K) {
                if (this.U == 2) {
                    this.C();
                    break;
                }
                break;
            }
            else {
                while (j == 0) {
                    final short n7 = w.D[this.B(4)];
                    final int n8 = n7 >>> 1 & 0xF;
                    final int n9 = n7 >>> 5 & 0x7FF;
                    if (n9 == 100) {
                        final short n10 = w.N[this.A(9)];
                        final short n11 = (short)(n10 & 0x1);
                        final int n12 = n10 >>> 1 & 0xF;
                        final int n13 = n10 >>> 5 & 0x7FF;
                        if (n12 == 12) {
                            this.C(5);
                            final short n14 = w.A[this.B(4)];
                            final int n15 = n14 >>> 1 & 0x7;
                            final int n16 = n14 >>> 4 & 0xFFF;
                            this.A(array, n, i, n16);
                            i += n16;
                            this.C(4 - n15);
                        }
                        else {
                            if (n12 == 15) {
                                throw new Error(m.A("TIFFFaxDecoder2"));
                            }
                            this.A(array, n, i, n13);
                            i += n13;
                            this.C(9 - n12);
                            if (n11 != 0) {
                                continue;
                            }
                            j = 1;
                            this.G[this.J++] = i;
                        }
                    }
                    else if (n9 == 200) {
                        final short n17 = w.B[this.B(2)];
                        final int n18 = n17 >>> 5 & 0x7FF;
                        final int n19 = n17 >>> 1 & 0xF;
                        this.A(array, n, i, n18);
                        i += n18;
                        this.C(2 - n19);
                        j = 1;
                        this.G[this.J++] = i;
                    }
                    else {
                        this.A(array, n, i, n9);
                        i += n9;
                        this.C(4 - n8);
                        j = 1;
                        this.G[this.J++] = i;
                    }
                }
                if (i != this.K) {
                    continue;
                }
                if (this.U == 2) {
                    this.C();
                    break;
                }
                break;
            }
        }
        this.G[this.J++] = i;
    }
    
    public void A(final byte[] array, final byte[] v, final int n, final int n2, final long n3) {
        this.V = v;
        this.U = 3;
        this.P = 0;
        this.T = 0;
        final int n4 = (this.K + 7) / 8;
        final int[] array2 = new int[2];
        this.E = (int)(n3 & 0x1L);
        this.I = (int)((n3 & 0x2L) >> 1);
        this.W = (int)((n3 & 0x4L) >> 2);
        if (this.A(true) != 1) {
            throw new Error(m.A("TIFFFaxDecoder3"));
        }
        final int n5 = 0;
        this.A(array, n5, n);
        int n6 = n5 + n4;
        for (int i = 1; i < n2; ++i) {
            if (this.A(false) == 0) {
                final int[] f = this.F;
                this.F = this.G;
                this.G = f;
                int j = 0;
                int n7 = -1;
                boolean b = true;
                int k = n;
                this.L = 0;
                while (k < this.K) {
                    this.A(n7, b, array2);
                    final int n8 = array2[0];
                    final int n9 = array2[1];
                    final int n10 = w.S[this.B(7)] & 0xFF;
                    final int n11 = (n10 & 0x78) >>> 3;
                    final int n12 = n10 & 0x7;
                    if (n11 == 0) {
                        if (!b) {
                            this.A(array, n6, k, n9 - k);
                        }
                        n7 = (k = n9);
                        this.C(7 - n12);
                    }
                    else if (n11 == 1) {
                        this.C(7 - n12);
                        if (b) {
                            final int n13 = k + this.A();
                            this.G[j++] = n13;
                            final int b2 = this.B();
                            this.A(array, n6, n13, b2);
                            k = n13 + b2;
                            this.G[j++] = k;
                        }
                        else {
                            final int b3 = this.B();
                            this.A(array, n6, k, b3);
                            final int n14 = k + b3;
                            this.G[j++] = n14;
                            k = n14 + this.A();
                            this.G[j++] = k;
                        }
                        n7 = k;
                    }
                    else {
                        if (n11 > 8) {
                            throw new Error(m.A("TIFFFaxDecoder4"));
                        }
                        final int n15 = n8 + (n11 - 5);
                        this.G[j++] = n15;
                        if (!b) {
                            this.A(array, n6, k, n15 - k);
                        }
                        n7 = (k = n15);
                        b = !b;
                        this.C(7 - n12);
                    }
                }
                this.G[j++] = k;
                this.J = j;
            }
            else {
                this.A(array, n6, n);
            }
            n6 += n4;
        }
    }
    
    public synchronized void B(final byte[] array, final byte[] v, final int n, final int n2, final long n3) {
        this.V = v;
        this.U = 4;
        this.P = 0;
        this.T = 0;
        final int n4 = (this.K + 7) / 8;
        final int[] array2 = new int[2];
        this.I = (int)((n3 & 0x2L) >> 1);
        final int[] g = this.G;
        this.J = 0;
        g[this.J++] = this.K;
        g[this.J++] = this.K;
        int n5 = 0;
        for (int i = 0; i < n2; ++i) {
            int n6 = -1;
            boolean b = true;
            final int[] f = this.F;
            this.F = this.G;
            final int[] g2 = f;
            this.G = g2;
            final int[] array3 = g2;
            int j = 0;
            int k = n;
            this.L = 0;
            while (k < this.K) {
                this.A(n6, b, array2);
                final int n7 = array2[0];
                final int n8 = array2[1];
                final int n9 = w.S[this.B(7)] & 0xFF;
                final int n10 = (n9 & 0x78) >>> 3;
                final int n11 = n9 & 0x7;
                if (n10 == 0) {
                    if (!b) {
                        this.A(array, n5, k, n8 - k);
                    }
                    n6 = (k = n8);
                    this.C(7 - n11);
                }
                else if (n10 == 1) {
                    this.C(7 - n11);
                    if (b) {
                        final int n12 = k + this.A();
                        array3[j++] = n12;
                        final int b2 = this.B();
                        this.A(array, n5, n12, b2);
                        k = n12 + b2;
                        array3[j++] = k;
                    }
                    else {
                        final int b3 = this.B();
                        this.A(array, n5, k, b3);
                        final int n13 = k + b3;
                        array3[j++] = n13;
                        k = n13 + this.A();
                        array3[j++] = k;
                    }
                    n6 = k;
                }
                else if (n10 <= 8) {
                    final int n14 = n7 + (n10 - 5);
                    array3[j++] = n14;
                    if (!b) {
                        this.A(array, n5, k, n14 - k);
                    }
                    n6 = (k = n14);
                    b = !b;
                    this.C(7 - n11);
                }
                else {
                    if (n10 != 11) {
                        throw new Error(m.A("TIFFFaxDecoder5"));
                    }
                    if (this.B(3) != 7) {
                        throw new Error(m.A("TIFFFaxDecoder5"));
                    }
                    int n15 = 0;
                    int l = 0;
                    while (l == 0) {
                        while (this.B(1) != 1) {
                            ++n15;
                        }
                        if (n15 > 5) {
                            n15 -= 6;
                            if (!b && n15 > 0) {
                                array3[j++] = k;
                            }
                            k += n15;
                            if (n15 > 0) {
                                b = true;
                            }
                            if (this.B(1) == 0) {
                                if (!b) {
                                    array3[j++] = k;
                                }
                                b = true;
                            }
                            else {
                                if (b) {
                                    array3[j++] = k;
                                }
                                b = false;
                            }
                            l = 1;
                        }
                        if (n15 == 5) {
                            if (!b) {
                                array3[j++] = k;
                            }
                            k += n15;
                            b = true;
                        }
                        else {
                            k += n15;
                            this.A(array, n5, array3[j++] = k, 1);
                            ++k;
                            b = false;
                        }
                    }
                }
            }
            array3[j++] = k;
            this.J = j;
            n5 += n4;
        }
    }
    
    private void A(final byte[] array, final int n, final int n2, final int n3) {
        int i = 8 * n + n2;
        final int n4 = i + n3;
        final int n5 = i >> 3;
        final int n6 = i & 0x7;
        if (n6 > 0) {
            int n7 = 1 << 7 - n6;
            byte b = array[n5];
            while (n7 > 0 && i < n4) {
                b |= (byte)n7;
                n7 >>= 1;
                ++i;
            }
            array[n5] = b;
        }
        int n8 = i >> 3;
        while (i < n4 - 7) {
            array[n8++] = -1;
            i += 8;
        }
        while (i < n4) {
            final int n9 = i >> 3;
            array[n9] |= (byte)(1 << 7 - (i & 0x7));
            ++i;
        }
    }
    
    private int A() {
        int n = 0;
        int i = 1;
        while (i != 0) {
            final int a = this.A(10);
            final short n2 = w.H[a];
            final short n3 = (short)(n2 & 0x1);
            final int n4 = n2 >>> 1 & 0xF;
            if (n4 == 12) {
                final short n5 = w.A[(a << 2 & 0xC) | this.B(2)];
                final int n6 = n5 >>> 1 & 0x7;
                n += (n5 >>> 4 & 0xFFF);
                this.C(4 - n6);
            }
            else {
                if (n4 == 0) {
                    throw new Error(m.A("TIFFFaxDecoder0"));
                }
                if (n4 == 15) {
                    throw new Error(m.A("TIFFFaxDecoder1"));
                }
                n += (n2 >>> 5 & 0x7FF);
                this.C(10 - n4);
                if (n3 != 0) {
                    continue;
                }
                i = 0;
            }
        }
        return n;
    }
    
    private int B() {
        int n = 0;
        int i = 0;
        while (i == 0) {
            final short n2 = w.D[this.B(4)];
            final int n3 = n2 >>> 1 & 0xF;
            final int n4 = n2 >>> 5 & 0x7FF;
            if (n4 == 100) {
                final short n5 = w.N[this.A(9)];
                final short n6 = (short)(n5 & 0x1);
                final int n7 = n5 >>> 1 & 0xF;
                final int n8 = n5 >>> 5 & 0x7FF;
                if (n7 == 12) {
                    this.C(5);
                    final short n9 = w.A[this.B(4)];
                    final int n10 = n9 >>> 1 & 0x7;
                    n += (n9 >>> 4 & 0xFFF);
                    this.C(4 - n10);
                }
                else {
                    if (n7 == 15) {
                        throw new Error(m.A("TIFFFaxDecoder2"));
                    }
                    n += n8;
                    this.C(9 - n7);
                    if (n6 != 0) {
                        continue;
                    }
                    i = 1;
                }
            }
            else if (n4 == 200) {
                final short n11 = w.B[this.B(2)];
                n += (n11 >>> 5 & 0x7FF);
                this.C(2 - (n11 >>> 1 & 0xF));
                i = 1;
            }
            else {
                n += n4;
                this.C(4 - n3);
                i = 1;
            }
        }
        return n;
    }
    
    private int A(final boolean b) {
        if (this.W == 0) {
            final int a = this.A(12);
            if (b && a == 0 && this.A(4) == 1) {
                return this.W = 1;
            }
            if (a != 1) {
                throw new Error(m.A("TIFFFaxDecoder6"));
            }
        }
        else if (this.W == 1) {
            final int n = 8 - this.P;
            if (this.A(n) != 0) {
                throw new Error(m.A("TIFFFaxDecoder8"));
            }
            if (n < 4 && this.A(8) != 0) {
                throw new Error(m.A("TIFFFaxDecoder8"));
            }
            int a2;
            while ((a2 = this.A(8)) != 1) {
                if (a2 != 0) {
                    throw new Error(m.A("TIFFFaxDecoder8"));
                }
            }
        }
        if (this.E == 0) {
            return 1;
        }
        return this.B(1);
    }
    
    private void A(final int n, final boolean b, final int[] array) {
        final int[] f = this.F;
        final int j = this.J;
        final int n2 = (this.L > 0) ? (this.L - 1) : 0;
        int n3;
        if (b) {
            n3 = (n2 & 0xFFFFFFFE);
        }
        else {
            n3 = (n2 | 0x1);
        }
        int i;
        for (i = n3; i < j; i += 2) {
            final int n4 = f[i];
            if (n4 > n) {
                this.L = i;
                array[0] = n4;
                break;
            }
        }
        if (i + 1 < j) {
            array[1] = f[i + 1];
        }
    }
    
    private int A(final int n) {
        final int n2 = this.V.length - 1;
        final int t = this.T;
        byte b;
        byte b2;
        byte b3;
        if (this.C == 1) {
            b = this.V[t];
            if (t == n2) {
                b2 = 0;
                b3 = 0;
            }
            else if (t + 1 == n2) {
                b2 = this.V[t + 1];
                b3 = 0;
            }
            else {
                b2 = this.V[t + 1];
                b3 = this.V[t + 2];
            }
        }
        else {
            if (this.C != 2) {
                throw new Error(m.A("TIFFFaxDecoder7"));
            }
            b = w.M[this.V[t] & 0xFF];
            if (t == n2) {
                b2 = 0;
                b3 = 0;
            }
            else if (t + 1 == n2) {
                b2 = w.M[this.V[t + 1] & 0xFF];
                b3 = 0;
            }
            else {
                b2 = w.M[this.V[t + 1] & 0xFF];
                b3 = w.M[this.V[t + 2] & 0xFF];
            }
        }
        final int n3 = 8 - this.P;
        int p = n - n3;
        int p2 = 0;
        if (p > 8) {
            p2 = p - 8;
            p = 8;
        }
        ++this.T;
        final int n4 = (b & w.Q[n3]) << n - n3;
        int n5 = (b2 & w.O[p]) >>> 8 - p;
        if (p2 != 0) {
            n5 = (n5 << p2 | (b3 & w.O[p2]) >>> 8 - p2);
            ++this.T;
            this.P = p2;
        }
        else if (p == 8) {
            this.P = 0;
            ++this.T;
        }
        else {
            this.P = p;
        }
        return n4 | n5;
    }
    
    private int B(final int n) {
        final int n2 = this.V.length - 1;
        final int t = this.T;
        byte b;
        byte b2;
        if (this.C == 1) {
            b = this.V[t];
            if (t == n2) {
                b2 = 0;
            }
            else {
                b2 = this.V[t + 1];
            }
        }
        else {
            if (this.C != 2) {
                throw new Error(m.A("TIFFFaxDecoder7"));
            }
            b = w.M[this.V[t] & 0xFF];
            if (t == n2) {
                b2 = 0;
            }
            else {
                b2 = w.M[this.V[t + 1] & 0xFF];
            }
        }
        final int n3 = 8 - this.P;
        final int p = n - n3;
        final int n4 = n3 - n;
        int n5;
        if (n4 >= 0) {
            n5 = (b & w.Q[n3]) >>> n4;
            this.P += n;
            if (this.P == 8) {
                this.P = 0;
                ++this.T;
            }
        }
        else {
            n5 = ((b & w.Q[n3]) << -n4 | (b2 & w.O[p]) >>> 8 - p);
            ++this.T;
            this.P = p;
        }
        return n5;
    }
    
    private void C(final int n) {
        final int p = this.P - n;
        if (p < 0) {
            --this.T;
            this.P = 8 + p;
        }
        else {
            this.P = p;
        }
    }
    
    private boolean C() {
        if (this.P != 0) {
            ++this.T;
            this.P = 0;
        }
        return true;
    }
    
    static {
        w.Q = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255 };
        w.O = new int[] { 0, 128, 192, 224, 240, 248, 252, 254, 255 };
        w.M = new byte[] { 0, -128, 64, -64, 32, -96, 96, -32, 16, -112, 80, -48, 48, -80, 112, -16, 8, -120, 72, -56, 40, -88, 104, -24, 24, -104, 88, -40, 56, -72, 120, -8, 4, -124, 68, -60, 36, -92, 100, -28, 20, -108, 84, -44, 52, -76, 116, -12, 12, -116, 76, -52, 44, -84, 108, -20, 28, -100, 92, -36, 60, -68, 124, -4, 2, -126, 66, -62, 34, -94, 98, -30, 18, -110, 82, -46, 50, -78, 114, -14, 10, -118, 74, -54, 42, -86, 106, -22, 26, -102, 90, -38, 58, -70, 122, -6, 6, -122, 70, -58, 38, -90, 102, -26, 22, -106, 86, -42, 54, -74, 118, -10, 14, -114, 78, -50, 46, -82, 110, -18, 30, -98, 94, -34, 62, -66, 126, -2, 1, -127, 65, -63, 33, -95, 97, -31, 17, -111, 81, -47, 49, -79, 113, -15, 9, -119, 73, -55, 41, -87, 105, -23, 25, -103, 89, -39, 57, -71, 121, -7, 5, -123, 69, -59, 37, -91, 101, -27, 21, -107, 85, -43, 53, -75, 117, -11, 13, -115, 77, -51, 45, -83, 109, -19, 29, -99, 93, -35, 61, -67, 125, -3, 3, -125, 67, -61, 35, -93, 99, -29, 19, -109, 83, -45, 51, -77, 115, -13, 11, -117, 75, -53, 43, -85, 107, -21, 27, -101, 91, -37, 59, -69, 123, -5, 7, -121, 71, -57, 39, -89, 103, -25, 23, -105, 87, -41, 55, -73, 119, -9, 15, -113, 79, -49, 47, -81, 111, -17, 31, -97, 95, -33, 63, -65, 127, -1 };
        w.H = new short[] { 6430, 6400, 6400, 6400, 3225, 3225, 3225, 3225, 944, 944, 944, 944, 976, 976, 976, 976, 1456, 1456, 1456, 1456, 1488, 1488, 1488, 1488, 718, 718, 718, 718, 718, 718, 718, 718, 750, 750, 750, 750, 750, 750, 750, 750, 1520, 1520, 1520, 1520, 1552, 1552, 1552, 1552, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 654, 654, 654, 654, 654, 654, 654, 654, 1072, 1072, 1072, 1072, 1104, 1104, 1104, 1104, 1136, 1136, 1136, 1136, 1168, 1168, 1168, 1168, 1200, 1200, 1200, 1200, 1232, 1232, 1232, 1232, 622, 622, 622, 622, 622, 622, 622, 622, 1008, 1008, 1008, 1008, 1040, 1040, 1040, 1040, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 1712, 1712, 1712, 1712, 1744, 1744, 1744, 1744, 846, 846, 846, 846, 846, 846, 846, 846, 1264, 1264, 1264, 1264, 1296, 1296, 1296, 1296, 1328, 1328, 1328, 1328, 1360, 1360, 1360, 1360, 1392, 1392, 1392, 1392, 1424, 1424, 1424, 1424, 686, 686, 686, 686, 686, 686, 686, 686, 910, 910, 910, 910, 910, 910, 910, 910, 1968, 1968, 1968, 1968, 2000, 2000, 2000, 2000, 2032, 2032, 2032, 2032, 16, 16, 16, 16, 10257, 10257, 10257, 10257, 12305, 12305, 12305, 12305, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 878, 878, 878, 878, 878, 878, 878, 878, 1904, 1904, 1904, 1904, 1936, 1936, 1936, 1936, -18413, -18413, -16365, -16365, -14317, -14317, -10221, -10221, 590, 590, 590, 590, 590, 590, 590, 590, 782, 782, 782, 782, 782, 782, 782, 782, 1584, 1584, 1584, 1584, 1616, 1616, 1616, 1616, 1648, 1648, 1648, 1648, 1680, 1680, 1680, 1680, 814, 814, 814, 814, 814, 814, 814, 814, 1776, 1776, 1776, 1776, 1808, 1808, 1808, 1808, 1840, 1840, 1840, 1840, 1872, 1872, 1872, 1872, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, 14353, 14353, 14353, 14353, 16401, 16401, 16401, 16401, 22547, 22547, 24595, 24595, 20497, 20497, 20497, 20497, 18449, 18449, 18449, 18449, 26643, 26643, 28691, 28691, 30739, 30739, -32749, -32749, -30701, -30701, -28653, -28653, -26605, -26605, -24557, -24557, -22509, -22509, -20461, -20461, 8207, 8207, 8207, 8207, 8207, 8207, 8207, 8207, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232 };
        w.A = new short[] { 28679, 28679, 31752, -32759, -31735, -30711, -29687, -28663, 29703, 29703, 30727, 30727, -27639, -26615, -25591, -24567 };
        w.D = new short[] { 3226, 6412, 200, 168, 38, 38, 134, 134, 100, 100, 100, 100, 68, 68, 68, 68 };
        w.B = new short[] { 292, 260, 226, 226 };
        w.N = new short[] { 62, 62, 30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 588, 588, 588, 588, 588, 588, 588, 588, 1680, 1680, 20499, 22547, 24595, 26643, 1776, 1776, 1808, 1808, -24557, -22509, -20461, -18413, 1904, 1904, 1936, 1936, -16365, -14317, 782, 782, 782, 782, 814, 814, 814, 814, -12269, -10221, 10257, 10257, 12305, 12305, 14353, 14353, 16403, 18451, 1712, 1712, 1744, 1744, 28691, 30739, -32749, -30701, -28653, -26605, 2061, 2061, 2061, 2061, 2061, 2061, 2061, 2061, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 750, 750, 750, 750, 1616, 1616, 1648, 1648, 1424, 1424, 1456, 1456, 1488, 1488, 1520, 1520, 1840, 1840, 1872, 1872, 1968, 1968, 8209, 8209, 524, 524, 524, 524, 524, 524, 524, 524, 556, 556, 556, 556, 556, 556, 556, 556, 1552, 1552, 1584, 1584, 2000, 2000, 2032, 2032, 976, 976, 1008, 1008, 1040, 1040, 1072, 1072, 1296, 1296, 1328, 1328, 718, 718, 718, 718, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 4113, 4113, 6161, 6161, 848, 848, 880, 880, 912, 912, 944, 944, 622, 622, 622, 622, 654, 654, 654, 654, 1104, 1104, 1136, 1136, 1168, 1168, 1200, 1200, 1232, 1232, 1264, 1264, 686, 686, 686, 686, 1360, 1360, 1392, 1392, 12, 12, 12, 12, 12, 12, 12, 12, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390 };
        w.S = new byte[] { 80, 88, 23, 71, 30, 30, 62, 62, 4, 4, 4, 4, 4, 4, 4, 4, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41 };
    }
}
