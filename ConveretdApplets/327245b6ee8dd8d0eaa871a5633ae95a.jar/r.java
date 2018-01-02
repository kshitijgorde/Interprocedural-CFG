// 
// Decompiled by Procyon v0.5.30
// 

final class r
{
    blaze3d a;
    int b;
    int c;
    int[] d;
    int[] e;
    float[] f;
    static int g;
    static int h;
    float[] i;
    int[] j;
    int k;
    int l;
    int m;
    int[] n;
    static boolean p;
    static boolean q;
    static int[] r;
    static int[] s;
    static float[] t;
    float u;
    
    r(final blaze3d a) {
        this.u = 1.4901161E-8f;
        this.a = a;
        final boolean b = true;
        this.k = (b ? 1 : 0);
        this.l = (b ? 1 : 0);
        this.m = 0;
        r.g = 302;
        r.h = 250;
        if (k.br != null) {
            k.br.c();
        }
        if (k.bs != null) {
            k.bs.c();
        }
        if (k.bt != null) {
            k.bt.c();
        }
    }
    
    public float a(final int n) {
        final int n2 = this.d[n];
        final float n3 = this.f[n];
        if (n2 < this.k) {
            return n3;
        }
        for (int i = n2 - this.k; i != -1; i = this.j[i * 3]) {
            final float n4 = this.i[i];
            if (n4 < n3) {
                return n3;
            }
            if (this.j[i * 3 + 2] >> 24 == 255) {
                return n4;
            }
        }
        return n3;
    }
    
    void a() {
        final int m = this.m * 2 + 1;
        final float[] i = new float[m];
        final int[] j = new int[m * 3];
        for (int n = this.l - this.k, k = 0; k < n; ++k) {
            i[k] = this.i[k];
            j[k] = this.j[k];
            j[k + n] = this.j[k + n];
            j[k + n * 2] = this.j[k + n * 2];
        }
        this.m = m;
        this.i = i;
        this.j = j;
    }
    
    void b() {
        this.e = null;
        this.f = null;
        this.d = null;
        this.j = null;
        this.i = null;
        if (k.br != null) {
            k.br.d();
        }
        if (k.bs != null) {
            k.bs.d();
        }
        if (k.bt != null) {
            k.bt.d();
        }
    }
    
    public void c() {
        if (!r.p) {
            return;
        }
        if (this.l > 500000000) {
            p.a(this.d, 0);
            this.l = 1;
        }
        this.k = this.l;
    }
    
    void a(final int b, final int c, final int[] e, final float[] f, final int[] n) {
        if (!r.p) {
            return;
        }
        this.n = n;
        this.e = e;
        this.f = f;
        if (b <= 0 || c <= 0) {
            return;
        }
        if (this.b * this.c < b * c) {
            this.d = new int[b * c];
        }
        this.b = b;
        this.c = c;
        this.c();
    }
    
    public final void a(final int n, float n2, final int n3, final int n4, final int n5, final k k) {
        if (k.ai) {
            final int n6 = r.r[n5 & 0xFF] + r.s[n5 >>> 8];
            n2 += (k.bc * (n6 >> 16) + k.bd * (n6 << 17 >> 17)) * r.t[n3 >> 4];
        }
        final int n7 = this.l - this.k;
        if (n7 == this.m) {
            this.a();
        }
        this.i[n7] = n2;
        this.j[n7 * 3] = -1;
        this.j[n7 * 3 + 2] = n4;
        this.j[n7 * 3 + 1] = (n5 | n3 << 16);
        int n8 = this.d[n] - this.k;
        if (n8 < -1) {
            n8 = -1;
        }
        if (n8 == -1 || n2 > this.i[n8]) {
            this.j[n7 * 3] = n8;
            this.d[n] = this.l;
        }
        else {
            int n9 = n8;
            for (int n10 = this.j[n8 * 3]; n10 != -1 && n2 <= this.i[n10]; n10 = this.j[n10 * 3]) {
                n9 = n10;
            }
            this.j[n7 * 3] = this.j[n9 * 3];
            this.j[n9 * 3] = n7;
        }
        ++this.l;
    }
    
    public final void a(final int n, final float n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final k k) {
        this.a(n, n2, n3, n4 << 24 | n5 << 16 | n6 << 8 | n7, n8, k);
    }
    
    static final int a(final int n, final int n2) {
        final int n3 = n2 & 0x7F000000;
        if (n3 == 0) {
            return n & 0xFFFFFF;
        }
        if (n3 == 2130706432) {
            return n2 & 0xFFFFFF;
        }
        final int n4 = 255 - ((n3 >>> 23) + ((n2 & 0x40000000) >> 30));
        int n5 = (n2 & 0xFF) + (n4 * (n & 0xFF) >> 8 & 0xFF);
        int n6 = (n2 & 0xFF00) + (n4 * (n & 0xFF00) >> 8 & 0xFF00);
        int n7 = (n2 & 0xFF0000) + (n4 * (n & 0xFF0000) >> 8 & 0xFF0000);
        if (n7 > 16711680) {
            n7 = 16711680;
        }
        if (n6 > 65280) {
            n6 = 65280;
        }
        if (n5 > 255) {
            n5 = 255;
        }
        return n7 | n6 | n5;
    }
    
    public void a(final int[] array, final float[] array2) {
        int n = 0;
        for (int i = 0; i < this.c; ++i) {
            for (int j = 0; j < this.b; ++j, ++n) {
                final int n2 = this.d[n];
                if (n2 >= this.k) {
                    int n4;
                    int n3 = n4 = 0;
                    int n7;
                    int n6;
                    int n5 = n6 = (n7 = 0);
                    int n10;
                    int n9;
                    int n8 = n9 = (n10 = 0);
                    int n13;
                    int n12;
                    int n11 = n12 = (n13 = 0);
                    int n14;
                    float n15;
                    int n16;
                    int n17;
                    int n18;
                    int n19;
                    int n20;
                    int n21;
                    int n22;
                    int n24;
                    int n23;
                    int n25;
                    int n26;
                    int n27;
                    int n28;
                    int n29;
                    int n30;
                    int n31;
                    int n32;
                    int n33;
                    int n34;
                    for (n14 = n2 - this.k, n15 = array2[n]; n14 != -1 && this.i[n14] >= n15; n14 = this.j[n14 * 3]) {
                        n16 = this.j[n14 * 3 + 2];
                        n17 = (n16 >> 24 & 0xFF);
                        n18 = (n16 >> 16 & 0xFF);
                        n19 = (n16 >> 8 & 0xFF);
                        n20 = (n16 & 0xFF);
                        n21 = this.j[n14 * 3 + 1];
                        n22 = n21 >> 16;
                        n23 = (n24 = (n21 & 0xFFFF));
                        if ((n7 & n24) != 0x0) {
                            n24 &= ~n7;
                            if (n24 == 0) {
                                continue;
                            }
                            n25 = bg.e(n24) << 4;
                            n26 = 16384 / n22;
                            n18 = n18 * n25 * n26 >> 14;
                            n19 = n19 * n25 * n26 >> 14;
                            n20 = n20 * n25 * n26 >> 14;
                            n22 = n25;
                            n23 = n24;
                        }
                        if (n17 < 255) {
                            n27 = (n13 & n24);
                            n28 = (~n13 & n24);
                            n29 = bg.e(n27) << 4;
                            n30 = bg.e(n28) << 4;
                            n31 = bg.e(n23) << 4;
                            if (n29 != 0) {
                                if (n30 != 0) {
                                    n32 = 16384 / n31;
                                    n22 = n22 * n29 * n32 >> 14;
                                    n18 = n18 * n29 * n32 >> 14;
                                    n19 = n19 * n29 * n32 >> 14;
                                    n20 = n20 * n29 * n32 >> 14;
                                }
                                if (n9 != 0) {
                                    n33 = 256 - (n8 << 8) / n9;
                                    n8 += n33 * n17 * n22 >> 16;
                                    n10 += n33 * n18 >> 8;
                                    n12 += n33 * n19 >> 8;
                                    n11 += n33 * n20 >> 8;
                                }
                            }
                            if (n30 != 0) {
                                if (n27 == 0) {
                                    n9 += n22;
                                    n8 += n17 * n22 >> 8;
                                    n10 += n18;
                                    n12 += n19;
                                    n11 += n20;
                                }
                                else {
                                    n9 += n30;
                                    n34 = 16384 / n31;
                                    n8 += (n17 * n22 * n30 >> 8) * n34 >> 14;
                                    n10 += n18 * n30 * n34 >> 14;
                                    n12 += n19 * n30 * n34 >> 14;
                                    n11 += n20 * n30 * n34 >> 14;
                                }
                            }
                            n13 |= n24;
                        }
                        else {
                            n4 += n22;
                            n3 += n18;
                            n6 += n19;
                            n5 += n20;
                            n7 |= n24;
                        }
                    }
                    int n35 = n4 + n8;
                    int n36 = n3 + n10;
                    int n37 = n6 + n12;
                    int n38 = n5 + n11;
                    final int n39 = bg.e(n7 & n13) << 4;
                    if (n39 > 0) {
                        final int n40 = n39 * n8 / n9;
                        n35 -= n40;
                        final int n41 = 16384 / n4;
                        n36 -= n3 * n40 * n41 >> 14;
                        n37 -= n6 * n40 * n41 >> 14;
                        n38 -= n5 * n40 * n41 >> 14;
                    }
                    if (n36 > 255) {
                        n36 = 255;
                    }
                    if (n37 > 255) {
                        n37 = 255;
                    }
                    if (n38 > 255) {
                        n38 = 255;
                    }
                    if (n35 >= 255) {
                        n35 = 255;
                    }
                    if (n15 != 0.0f && n35 < 255) {
                        final int n42 = array[n];
                        final int n43 = n42 >> 16 & 0xFF;
                        final int n44 = n42 >> 8 & 0xFF;
                        final int n45 = n42 & 0xFF;
                        final int n46 = n42 >>> 24 & 0x7F;
                        final int n47 = 256 - n35;
                        int n48 = n36 + (n47 * n43 >> 8);
                        int n49 = n37 + (n47 * n44 >> 8);
                        int n50 = n38 + (n47 * n45 >> 8);
                        final int n51 = n35 >> 1;
                        final int n52 = n51 + ((127 - n51) * n46 >> 7);
                        if (n48 > 255) {
                            n48 = 255;
                        }
                        if (n49 > 255) {
                            n49 = 255;
                        }
                        if (n50 > 255) {
                            n50 = 255;
                        }
                        array[n] = (n48 << 16 | n49 << 8 | n50 | n52 << 24);
                    }
                    else {
                        array[n] = (n35 >> 1 << 24 | n36 << 16 | n37 << 8 | n38);
                        final int n53 = n >> 5;
                        final int n54 = 1 << (n & 0x1F);
                        final int[] n55 = this.n;
                        final int n56 = n53;
                        n55[n56] |= n54;
                    }
                }
            }
        }
    }
    
    static {
        r.p = true;
        r.q = true;
        r.r = new int[256];
        r.s = new int[256];
        r.t = new float[17];
        for (int i = 0; i <= 255; ++i) {
            int n = 0;
            int n2 = 0;
            for (int j = 0; j <= 7; ++j) {
                if ((i & 1 << j) != 0x0) {
                    final float n3 = ((j & 0x3) - 1.5f) / 4.0f;
                    final float n4 = ((j >> 2) - 1.5f) / 4.0f;
                    n = (n + ((int)((n3 * 0.97f - n4 * 0.242f) * 1024.0f) << 16 | ((int)((n4 * 0.97f + n3 * 0.242f) * 1024.0f) & 0x7FFF)) & 0xFFFF7FFF);
                    final float n5 = ((j & 0x3) - 1.5f) / 4.0f;
                    final float n6 = (2 + (j >> 2) - 1.5f) / 4.0f;
                    n2 = (n2 + ((int)((n5 * 0.97f - n6 * 0.242f) * 1024.0f) << 16 | ((int)((n6 * 0.97f + n5 * 0.242f) * 1024.0f) & 0x7FFF)) & 0xFFFF7FFF);
                }
            }
            r.r[i] = n;
            r.s[i] = n2;
        }
        for (int k = 1; k <= 16; ++k) {
            r.t[k] = 1.0f / (1024.0f * k);
        }
    }
}
