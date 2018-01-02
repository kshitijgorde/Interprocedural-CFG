// 
// Decompiled by Procyon v0.5.30
// 

package com.impatica.v402;

public class ImIrgb extends ImRect
{
    boolean I;
    public int Z;
    public int[] C;
    int B;
    public int D;
    int F;
    int J;
    int S;
    ImIrgb A;
    static byte[] E;
    static int G;
    static int[] H;
    static ImTrans K;
    static int arraycopy;
    
    private final void arraycopy(int n, int n2, final int n3, int n4, int n5, final boolean b) {
        n4 >>= 7;
        if (n4 > 256) {
            n4 = 256;
        }
        n5 >>= 7;
        if (n5 > 256) {
            n5 = 256;
        }
        if (this.F != 256) {
            n4 = n4 * this.F >> 8;
            n5 = n5 * this.F >> 8;
        }
        if (!b) {
            final int n6 = n;
            n = n2;
            n2 = n6;
        }
        final int[] c = this.C;
        int n7 = n2 * this.D + n;
        if (n4 == 256) {
            if (n >= super.L && n < super.M && n2 >= super.N && n2 < super.O) {
                c[n7] = n3;
            }
        }
        else {
            if (n4 != 0 && n >= super.L && n < super.M && n2 >= super.N && n2 < super.O) {
                final int n8 = 256 - n4;
                final int n9 = c[n7];
                c[n7] = (((n3 & 0xFF0000) * n4 + (n9 & 0xFF0000) * n8 & 0xFF000000) | ((n3 & 0xFF00) * n4 + (n9 & 0xFF00) * n8 & 0xFF0000) | (n3 & 0xFF) * n4 + (n9 & 0xFF) * n8) >> 8;
            }
            if (n5 != 0) {
                if (b) {
                    ++n2;
                    n7 += this.D;
                }
                else {
                    ++n;
                    ++n7;
                }
                if (n >= super.L && n < super.M && n2 >= super.N && n2 < super.O) {
                    final int n10 = 256 - n5;
                    final int n11 = c[n7];
                    c[n7] = (((n3 & 0xFF0000) * n5 + (n11 & 0xFF0000) * n10 & 0xFF000000) | ((n3 & 0xFF00) * n5 + (n11 & 0xFF00) * n10 & 0xFF0000) | (n3 & 0xFF) * n5 + (n11 & 0xFF) * n10) >> 8;
                }
            }
        }
    }
    
    private final void I(int n, int n2, final int n3) {
        final int f = this.F;
        final int n4 = (n3 & 0xFF0000) * f;
        final int n5 = (n3 & 0xFF00) * f;
        final int n6 = (n3 & 0xFF) * f;
        final int n7 = 256 - f;
        final int[] c = this.C;
        while (n2-- > 0) {
            final int n8 = c[n];
            final int n9 = ((n4 + (n8 & 0xFF0000) * n7 & 0xFF000000) | (n5 + (n8 & 0xFF00) * n7 & 0xFF0000) | n6 + (n8 & 0xFF) * n7) >> 8;
            c[n++] = n9;
            while (n2 != 0 && c[n] == n8) {
                c[n++] = n9;
                --n2;
            }
        }
    }
    
    private final void I(int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        int n9 = n2 >> 13;
        final int n10 = n8 >> 13;
        int n11 = n10 - n9;
        if (n11 >= -1 && n11 <= 1) {
            n11 = (n4 >> 13) - n9;
        }
        if (n11 >= -1 && n11 <= 1) {
            n11 = (n6 >> 13) - n9;
        }
        if (n11 >= -1 && n11 <= 1) {
            if (ImIrgb.arraycopy != 0) {
                this.I(n, n2, n7, n8);
            }
            else if (n9 != n10) {
                if (n9 > n10) {
                    n9 = n10;
                    n = n7;
                }
                final int n12 = n9 >> 2;
                if (n12 >= super.N && n12 < super.O) {
                    final int n13 = n >> 15;
                    if (n13 < super.L) {
                        final byte[] e = ImIrgb.E;
                        final int n14 = n9 * this.D + super.L;
                        e[n14] ^= 0x1;
                    }
                    else if (n13 < super.M) {
                        final byte[] e2 = ImIrgb.E;
                        final int n15 = n9 * this.D + n13;
                        e2[n15] ^= (byte)(1 << (n >> 12 & 0x7));
                    }
                }
            }
        }
        else {
            final int n16 = n3 + n5 >> 1;
            final int n17 = n4 + n6 >> 1;
            final int n18 = n + n3 >> 1;
            final int n19 = n2 + n4 >> 1;
            final int n20 = n5 + n7 >> 1;
            final int n21 = n6 + n8 >> 1;
            final int n22 = n18 + n16 >> 1;
            final int n23 = n19 + n17 >> 1;
            final int n24 = n16 + n20 >> 1;
            final int n25 = n17 + n21 >> 1;
            final int n26 = n22 + n24 >> 1;
            final int n27 = n23 + n25 >> 1;
            this.I(n, n2, n18, n19, n22, n23, n26, n27);
            this.I(n26, n27, n24, n25, n20, n21, n7, n8);
        }
    }
    
    final void I() {
        if (this.C != null) {
            this.C = null;
        }
    }
    
    public ImIrgb() {
        this.F = 256;
    }
    
    public ImIrgb(final int n, final int n2) {
        this.F = 256;
        this.D = n;
        this.Z = n2;
        super.M = n;
        super.O = n2;
        this.B = n * n2;
        this.C = new int[this.B];
    }
    
    private final void Z() {
        final int g = 4 * this.D * this.Z;
        if (ImIrgb.G < g) {
            if (ImIrgb.E != null) {
                ImIrgb.E = null;
            }
            ImIrgb.E = new byte[g];
            ImIrgb.G = g;
        }
    }
    
    private final int Z(final int n, final int n2) {
        int i = ImIrgb.K.C(n, n2);
        int j = ImIrgb.K.D(n, n2);
        int d;
        for (d = this.D; i < 0; i += d) {}
        while (i >= d) {
            i -= d;
        }
        int z;
        for (z = this.Z; j < 0; j += z) {}
        while (j >= z) {
            j -= z;
        }
        return this.C[j * d + i];
    }
    
    private final void I(int n, int n2, int n3, int n4, final int n5, final int n6) {
        int n7 = super.L - n6 << 15;
        int n8 = super.N - n6 << 15;
        int n9 = super.M + n6 + 1 << 15;
        int n10 = super.O + n6 + 1 << 15;
        int n11 = n3 - n;
        int n12 = n4 - n2;
        final boolean b = ((n11 < 0) ? (-n11) : n11) >= ((n12 < 0) ? (-n12) : n12);
        if (!b) {
            final int n13 = n11;
            n11 = n12;
            n12 = n13;
            final int n14 = n;
            n = n2;
            n2 = n14;
            final int n15 = n3;
            n3 = n4;
            n4 = n15;
        }
        if (n11 < 0) {
            final int n16 = n;
            n = n3;
            n3 = n16;
            final int n17 = n2;
            n2 = n4;
            n4 = n17;
        }
        int n18;
        if (n11 == 0) {
            n18 = 32768;
        }
        else {
            n18 = (n12 << 15) / n11;
        }
        if (n11 < 0 && n6 <= 1) {
            n += 32768;
            n2 += n18;
            n3 += 32768;
        }
        if (!b) {
            final int n19 = n7;
            n7 = n8;
            n8 = n19;
            final int n20 = n9;
            n9 = n10;
            n10 = n20;
        }
        if (n < n7) {
            if (n3 < n7) {
                return;
            }
            n2 += ImBase.I(n7 - n, n18);
            n = n7;
        }
        else if (n > n9) {
            return;
        }
        if (n3 > n9) {
            n4 -= ImBase.I(n3 - n9, n18);
            n3 = n9;
        }
        final boolean b2 = n2 > n4;
        if (b2) {
            final int n21 = n;
            n = n3;
            n3 = n21;
            final int n22 = n2;
            n2 = n4;
            n4 = n22;
        }
        if (n2 < n8) {
            if (n4 < n8) {
                return;
            }
            if (n12 != 0) {
                n += (n8 - n2) * n11 / n12;
            }
            n2 = n8;
        }
        else if (n2 > n10) {
            return;
        }
        if (n4 > n10) {
            if (n12 != 0) {
                n3 -= (n4 - n10) * n11 / n12;
            }
            n4 = n10;
        }
        if (n2 > n4) {
            return;
        }
        if (b2) {
            final int n23 = n;
            n = n3;
            n3 = n23;
            final int n24 = n2;
            n2 = n4;
            n4 = n24;
        }
        if (n > n3) {
            return;
        }
        int i = n >> 15;
        if (n6 > 1) {
            n3 >>= 15;
            final int n25 = n6 + 1;
            while (i <= n3) {
                final int n26 = n2 >> 15;
                if (b) {
                    this.I(i, n26, n25, n25, n5, true);
                }
                else {
                    this.I(n26, i, n25, n25, n5, true);
                }
                ++i;
                n2 += n18;
            }
        }
        else {
            int n27 = n2;
            final int n28 = n & 0x7FFF;
            if (n28 != 0) {
                final int n29 = 32768 - n28;
                final int n30 = n27 & 0x7FFF;
                this.arraycopy(i, n27 >> 15, n5, ImBase.I(32768 - n30, n29), ImBase.I(n30, n29), b);
                ++i;
                n27 += ImBase.I(n18, n29);
            }
            final int n31 = n3 & 0x7FFF;
            int n32;
            for (n3 >>= 15; i < n3; ++i, n27 += n18) {
                n32 = (n27 & 0x7FFF);
                this.arraycopy(i, n27 >> 15, n5, 32768 - n32, n32, b);
            }
            if (n31 != 0) {
                final int n33 = n27 & 0x7FFF;
                this.arraycopy(i, n27 >> 15, n5, ImBase.I(32768 - n33, n31), ImBase.I(n33, n31), b);
            }
        }
    }
    
    final void I(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.I(n << 15, n2 << 15, n3 << 15, n4 << 15, n5, 1);
    }
    
    final void I(int n, int n2, int n3, int n4, final int n5, final boolean b, final ImIrgb imIrgb) {
        n <<= 15;
        n2 <<= 15;
        n3 <<= 14;
        n4 <<= 14;
        this.Z(n + n3, n2 + n4, n3, n4, n5, b, imIrgb);
    }
    
    private final void I(int n, int n2, int n3, int n4, final int n5, final boolean b) {
        n <<= 15;
        n2 <<= 15;
        n3 <<= 14;
        n4 <<= 14;
        this.Z(n, n2, n3, n4, n5, b, null);
    }
    
    private final void Z(final int n, final int n2, final int n3, int n4, final int j, final boolean b, final ImIrgb imIrgb) {
        this.Z();
        final int n5 = n - n3;
        final int n6 = n + n3;
        n4 += n4 / 3;
        final int n7 = n2 - n4;
        final int n8 = n2 + n4;
        if (b) {
            ImIrgb.arraycopy = 0;
        }
        else {
            this.J = j;
            ImIrgb.arraycopy = 1;
        }
        this.I(n5, n2, n5, n7, n6, n7, n6, n2);
        this.I(n5, n2, n5, n8, n6, n8, n6, n2);
        if (b) {
            this.I(j, imIrgb, null);
        }
    }
    
    final void I(final ImRecord imRecord, final int n, final int n2, final int n3, final int n4, final int n5, boolean b, int d, final boolean b2, final ImTrans imTrans, final ImIrgb imIrgb, final int[] array) {
        boolean b3 = true;
        int z = n5;
        imRecord.Z(12);
        if (d != 0) {
            d = imRecord.D();
            z = imRecord.Z();
            b3 = (imRecord.F() == 1);
            b = (imRecord.F() == 1);
        }
        final int a = imRecord.A();
        if (imIrgb != null) {
            b = true;
        }
        this.Z();
        if (b) {
            this.I(imRecord, b3, b2, imTrans);
            this.I(n5, imIrgb, array);
        }
        if (d != 0 && (d != 1 || z != n5)) {
            imRecord.Z(a);
            ImIrgb.arraycopy = d;
            this.J = z;
            this.I(imRecord, b3, b2, imTrans);
            ImIrgb.arraycopy = 0;
        }
    }
    
    private final void I(int z, final ImIrgb imIrgb, final int[] array) {
        final int l = super.L;
        final int n = super.N;
        final int m = super.M;
        final int o = super.O;
        final int d = this.D;
        final int f = this.F;
        final int n2 = z & 0xFFFFFF;
        int n3 = z >> 16 & 0xFF;
        int n4 = z >> 8 & 0xFF;
        int n5 = z & 0xFF;
        boolean i = false;
        if (imIrgb != null) {
            i = imIrgb.I;
        }
        int[] h = ImIrgb.H;
        if (h == null) {
            h = (ImIrgb.H = new int[256]);
            for (int j = 1; j < 256; ++j) {
                int n6 = 0;
                boolean b = false;
                int k;
                int n7;
                for (k = j, n7 = 8; k != 0; k >>= 1, --n7) {
                    if ((k & 0x1) != 0x0) {
                        b = !b;
                    }
                    if (b) {
                        n6 += 8;
                    }
                }
                if (b) {
                    if (n7 != 0) {
                        n6 += n7 * 8;
                    }
                    n6 = -n6;
                }
                h[j] = n6;
            }
        }
        final byte[] e = ImIrgb.E;
        final int[] c = this.C;
        int n8 = n * d;
        for (int n9 = n; n9 < o; ++n9) {
            int n10 = 0;
            for (int n11 = l; n11 < m; ++n11) {
                int n12 = (n8 << 2) + n11;
                int n13 = 0;
                int n14 = 1;
                for (int n15 = 0; n15 < 4; ++n15) {
                    final byte b2 = e[n12];
                    if (b2 == 0) {
                        if ((n10 & n14) != 0x0) {
                            n13 += 64;
                        }
                    }
                    else {
                        int n16 = h[b2 & 0xFF];
                        final boolean b3 = (n10 & n14) != 0x0;
                        if (n16 < 0) {
                            n16 = -n16;
                            n10 ^= n14;
                        }
                        if (b3) {
                            n16 = 64 - n16;
                        }
                        n13 += n16;
                        e[n12] = 0;
                    }
                    n12 += d;
                    n14 <<= 1;
                }
                if (n13 != 0) {
                    if (imIrgb != null) {
                        z = imIrgb.Z(n11, n9);
                        if (i && (z >> 24 & 0xFF) <= 128) {
                            z = n2;
                        }
                        n3 = (z >> 16 & 0xFF);
                        n4 = (z >> 8 & 0xFF);
                        n5 = (z & 0xFF);
                    }
                    if (f != 256) {
                        if (n13 >= 255) {
                            n13 = f;
                        }
                        else {
                            n13 = n13 * f >> 8;
                        }
                    }
                    if (n13 >= 255) {
                        c[n8 + n11] = z;
                    }
                    else {
                        final int n17 = n8 + n11;
                        final int n18 = c[n17];
                        final int n19 = n18 >> 16 & 0xFF;
                        final int n20 = n19 + ((n3 - n19) * n13 >> 8);
                        final int n21 = n18 >> 8 & 0xFF;
                        final int n22 = n21 + ((n4 - n21) * n13 >> 8);
                        final int n23 = n18 & 0xFF;
                        c[n17] = (n20 << 16 | n22 << 8 | n23 + ((n5 - n23) * n13 >> 8));
                    }
                }
            }
            n8 += d;
        }
    }
    
    private final void I(int n, int i, int n2, int n3) {
        if (ImIrgb.arraycopy != 0) {
            this.I(n, i, n2, n3, this.J, ImIrgb.arraycopy);
            return;
        }
        i >>= 13;
        n3 >>= 13;
        int n4 = n3 - i;
        if (n4 == 0) {
            return;
        }
        if (n4 < 0) {
            n4 = -n4;
            final int n5 = n;
            n = n2;
            n2 = n5;
            final int n6 = i;
            i = n3;
            n3 = n6;
        }
        final int n7 = super.N << 2;
        final int n8 = super.O << 2;
        if (i >= n8 || n3 <= n7) {
            return;
        }
        int n9 = n2 - n;
        if (n9 != 0) {
            n9 /= n4;
        }
        if (i < n7) {
            n += (n7 - i) * n9;
            i = n7;
        }
        if (n3 > n8) {
            n2 -= (n3 - n8) * n9;
            n3 = n8;
        }
        final byte[] e = ImIrgb.E;
        final int d = this.D;
        int n10 = i * d;
        if (n9 == 0) {
            int l = n >> 15;
            int n11;
            if (l < super.L) {
                l = super.L;
                n11 = 1;
            }
            else {
                n11 = 1 << (n >> 12 & 0x7);
            }
            if (l < super.M) {
                int n12 = n10 + l;
                while (i < n3) {
                    final byte[] array = e;
                    final int n13 = n12;
                    array[n13] ^= (byte)n11;
                    n12 += d;
                    ++i;
                }
            }
        }
        else {
            while (i < n3) {
                final int n14 = n >> 15;
                if (n14 < super.L) {
                    final byte[] array2 = e;
                    final int n15 = n10 + super.L;
                    array2[n15] ^= 0x1;
                }
                else if (n14 < super.M) {
                    final byte[] array3 = e;
                    final int n16 = n10 + n14;
                    array3[n16] ^= (byte)(1 << (n >> 12 & 0x7));
                }
                n += n9;
                ++i;
                n10 += d;
            }
        }
    }
    
    private final void I(final ImRecord imRecord, final boolean b, final boolean b2, final ImTrans imTrans) {
        int n = 1;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 63;
        final int n9 = super.M + 1 << 15;
        final int n10 = super.N << 15;
        final int n11 = super.O + 1 << 15;
        while (!imRecord.I()) {
            int c = imRecord.C();
            if (b2) {
                if (c == 61) {
                    if (imRecord.I()) {
                        break;
                    }
                    n8 = 61;
                    if (b) {
                        this.I(n4, n5, n2, n3);
                    }
                    n = 1;
                }
                else if (c == 63 || c == 62 || c == 60) {
                    n8 = c;
                    c = imRecord.C() + n6;
                }
                else {
                    c += n6;
                }
            }
            if (n8 == 63) {
                int c2 = imRecord.C();
                if (b2) {
                    c2 += n7;
                    n6 = c;
                    n7 = c2;
                }
                final int z = imTrans.Z(c, c2);
                final int b3 = imTrans.B(c, c2);
                if (n != 0) {
                    n = 0;
                    n2 = z;
                    n3 = b3;
                }
                else if ((n4 < n9 || z < n9) && (n5 >= n10 || b3 >= n10) && (n5 < n11 || b3 < n11)) {
                    this.I(n4, n5, z, b3);
                }
                n4 = z;
                n5 = b3;
            }
            else if (n8 == 62 || n8 == 60) {
                final int n12 = c;
                final int n13 = n7 + imRecord.C();
                final int z2 = imTrans.Z(n12, n13);
                final int b4 = imTrans.B(n12, n13);
                n6 = n12 + imRecord.C();
                n7 = n13 + imRecord.C();
                int n14 = imTrans.Z(n6, n7);
                int n15 = imTrans.B(n6, n7);
                if (n8 == 60) {
                    final int n16 = n14;
                    final int n17 = n15;
                    n6 += imRecord.C();
                    n7 += imRecord.C();
                    n14 = imTrans.Z(n6, n7);
                    n15 = imTrans.B(n6, n7);
                    if ((n4 < n9 || z2 < n9 || n14 < n9) && (n5 >= n10 || b4 >= n10 || n15 >= n10) && (n5 < n11 || b4 < n11 || n15 < n11)) {
                        this.I(n4, n5, z2, b4, n16, n17, n14, n15);
                    }
                }
                else if ((n4 < n9 || z2 < n9 || n14 < n9) && (n5 >= n10 || b4 >= n10 || n15 >= n10) && (n5 < n11 || b4 < n11 || n15 < n11)) {
                    this.Z(n4, n5, z2, b4, n14, n15);
                }
                n4 = n14;
                n5 = n15;
            }
            else {
                n8 = 63;
            }
        }
        if (b) {
            this.I(n4, n5, n2, n3);
        }
    }
    
    private final void Z(final int n, final int n2, final int n3, final int n4, int n5, int n6) {
        final int n7 = (n6 >> 13) - (n2 >> 13);
        if (n7 >= -1 && n7 <= 1) {
            if (ImIrgb.arraycopy != 0) {
                this.I(n, n2, n5, n6);
            }
            else if (n7 != 0) {
                if (n7 == 1) {
                    n5 = n;
                    n6 = n2;
                }
                final int n8 = n6 >> 15;
                if (n8 >= super.N && n8 < super.O) {
                    final int n9 = n5 >> 15;
                    if (n9 < super.L) {
                        final byte[] e = ImIrgb.E;
                        final int n10 = (n6 >> 13) * this.D + super.L;
                        e[n10] ^= 0x1;
                    }
                    else if (n9 < super.M) {
                        final byte[] e2 = ImIrgb.E;
                        final int n11 = (n6 >> 13) * this.D + n9;
                        e2[n11] ^= (byte)(1 << (n5 >> 12 & 0x7));
                    }
                }
            }
        }
        else {
            final int n12 = n + n3 >> 1;
            final int n13 = n2 + n4 >> 1;
            final int n14 = n3 + n5 >> 1;
            final int n15 = n4 + n6 >> 1;
            final int n16 = n12 + n14 >> 1;
            final int n17 = n13 + n15 >> 1;
            this.Z(n, n2, n12, n13, n16, n17);
            this.Z(n16, n17, n14, n15, n5, n6);
        }
    }
    
    final void Z(final int n, int n2, final int n3, final int n4, final int n5) {
        final int n6 = n + n3 - 1;
        int n7 = n2 + n4 - 1;
        this.C(n, n2, n3, 1, n5);
        this.C(n, n7, n3, 1, n5);
        ++n2;
        --n7;
        this.C(n, n2, 1, n4, n5);
        this.C(n6, n2, 1, n4, n5);
    }
    
    final void Z(final int n, final int n2, int n3, int n4) {
        --n3;
        --n4;
        for (int i = n; i <= n3; ++i) {
            if ((i & 0x4) != 0x0) {
                this.C(i, n2);
                this.C(i, n4);
            }
        }
        for (int j = n2; j <= n4; ++j) {
            if ((j & 0x4) != 0x0) {
                this.C(n, j);
                this.C(n3, j);
            }
        }
    }
    
    final void C(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.I(n, n2, n3, n4, n5, null);
    }
    
    final void I(int l, int n, int n2, int n3, final int n4, final ImIrgb imIrgb) {
        int m = l + n2;
        int o = n + n3;
        if (l < super.L) {
            l = super.L;
        }
        if (n < super.N) {
            n = super.N;
        }
        if (m > super.M) {
            m = super.M;
        }
        if (o > super.O) {
            o = super.O;
        }
        if (l >= m || n >= o) {
            return;
        }
        if (imIrgb != null) {
            int n5 = n * this.D;
            for (int i = n; i < o; ++i) {
                for (int j = l; j < m; ++j) {
                    this.C[n5 + j] = imIrgb.Z(j, i);
                }
                n5 += this.D;
            }
        }
        else if (this.F < 255) {
            final int d = this.D;
            int n6 = n * d + l;
            n2 = m - l;
            n3 = o - n;
            while (n3-- > 0) {
                this.I(n6, n2, n4);
                n6 += d;
            }
        }
        else {
            this.B(l, n, m - l, o - n, n4);
        }
    }
    
    final void B(final int n, final int n2, final int n3, int n4, final int n5) {
        final int[] c = this.C;
        final int d = this.D;
        final int n6 = n2 * d + n;
        for (int i = 0; i < n3; ++i) {
            c[n6 + i] = n5;
        }
        int n7 = n6;
        while (--n4 > 0) {
            n7 += d;
            System.arraycopy(c, n6, c, n7, n3);
        }
    }
    
    private final int Z(final int n, final int n2, final int n3) {
        int n4 = n >> 24 & 0xFF;
        int n5;
        int n6;
        int n7;
        if (n4 == 0) {
            n5 = 0;
            n6 = 0;
            n7 = 0;
        }
        else {
            n5 = (n >> 16 & 0xFF);
            n6 = (n >> 8 & 0xFF);
            n7 = (n & 0xFF);
        }
        final int n8 = n2 >> 24 & 0xFF;
        int n9;
        int n10;
        int n11;
        if (n8 == 0) {
            n9 = 0;
            n10 = 0;
            n11 = 0;
        }
        else {
            n9 = (n2 >> 16 & 0xFF);
            n10 = (n2 >> 8 & 0xFF);
            n11 = (n2 & 0xFF);
        }
        if (n4 != n8) {
            n4 += (n8 - n4) * n3 >> 15;
        }
        return n4 << 24 | n5 + ((n9 - n5) * n3 >> 15) << 16 | n6 + ((n10 - n6) * n3 >> 15) << 8 | n7 + ((n11 - n7) * n3 >> 15);
    }
    
    private final void I(final ImIrgb imIrgb, final int n, final int n2, final int n3, int n4, final int n5, final int n6) {
        final int[] c = imIrgb.C;
        final int[] c2 = this.C;
        final int d = imIrgb.D;
        final int d2 = this.D;
        int n7 = n2 * d + n;
        int n8 = n6 * d2 + n5;
        while (n4-- > 0) {
            System.arraycopy(c, n7, c2, n8, n3);
            n7 += d;
            n8 += d2;
        }
    }
    
    final void I(final ImIrgb imIrgb, final int n, final int n2) {
        if (imIrgb.I || this.F != 256) {
            this.Z(imIrgb, n, n2, 32768, 32768);
        }
        else {
            final int l = super.L;
            final int n3 = super.N;
            this.I(imIrgb, l - n, n3 - n2, super.M - l, super.O - n3, l, n3);
        }
    }
    
    final void I(final ImIrgb imIrgb, final int n, final int n2, final int n3, final int n4) {
        final int d = imIrgb.D;
        final int z = imIrgb.Z;
        if (d == n3 && z == n4) {
            this.I(imIrgb, n, n2);
        }
        else {
            this.Z(imIrgb, n, n2, (d << 15) / n3, (z << 15) / n4);
        }
    }
    
    private final void Z(final ImIrgb imIrgb, final int n, final int n2, final int n3, final int n4) {
        final int[] c = this.C;
        final int[] c2 = imIrgb.C;
        final int d = this.D;
        final int d2 = imIrgb.D;
        final int z = imIrgb.Z;
        final int l = super.L;
        final int n5 = super.N;
        final int m = super.M;
        final int o = super.O;
        int n6 = n5 * this.D;
        final int n7 = (l - n) * n3;
        int n8 = (n5 - n2) * n4;
        final int f = this.F;
        final boolean b = n3 != 32768 || n4 != 32768;
        final boolean b2 = f < 255 || imIrgb.I || b;
        for (int i = n5; i < o; ++i) {
            final int n9 = n8 & 0x7FFF;
            int n10 = n8 >> 15;
            final int n11 = n10 * d2;
            ++n10;
            int n12 = n7;
            if (b2) {
                for (int j = l; j < m; ++j) {
                    int n16;
                    if (b) {
                        int n13 = n12 >> 15;
                        final int n14 = n12 & 0x7FFF;
                        final int n15 = n11 + n13;
                        n16 = c2[n15];
                        if (++n13 < d2) {
                            final int n17 = c2[n15 + 1];
                            if (n16 != n17) {
                                n16 = this.Z(n16, n17, n14);
                            }
                        }
                        if (n10 < z) {
                            final int n18 = n15 + d2;
                            int z2 = c2[n18];
                            if (n13 < d2) {
                                final int n19 = c2[n18 + 1];
                                if (z2 != n19) {
                                    z2 = this.Z(z2, n19, n14);
                                }
                            }
                            n16 = this.Z(n16, z2, n9);
                        }
                    }
                    else {
                        n16 = c2[n11 + (n12 >> 15)];
                    }
                    int n20 = n16 >> 24 & 0xFF;
                    if (n20 != 0) {
                        if (f < 255) {
                            n16 = this.Z(0, n16, f << 7);
                            n20 = n20 * f >> 8;
                        }
                        if (n20 >= 255) {
                            c[n6 + j] = n16;
                        }
                        else {
                            final int n21 = n6 + j;
                            final int n22 = c[n21];
                            final int n23 = 256 - n20;
                            c[n21] = (n16 & 0xFFFFFF) + ((n22 & 0xFF0000) * n23 >> 8 & 0xFF0000) + ((n22 & 0xFF00) * n23 >> 8 & 0xFF00) + ((n22 & 0xFF) * n23 >> 8);
                        }
                    }
                    n12 += n3;
                }
            }
            else {
                for (int k = l; k < m; ++k) {
                    c[n6 + k] = c2[n11 + (n12 >> 15)];
                    n12 += n3;
                }
            }
            n8 += n4;
            n6 += d;
        }
    }
    
    private final void C(final int n, final int n2) {
        if (n >= super.L && n < super.M && n2 >= super.N && n2 < super.O) {
            final int n3 = n2 * this.D + n;
            this.C[n3] ^= 0xFFFFFF;
        }
    }
}
