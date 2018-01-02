// 
// Decompiled by Procyon v0.5.30
// 

public class j extends b
{
    long k;
    boolean l;
    int m;
    int n;
    int o;
    int[] p;
    int q;
    int r;
    int s;
    int t;
    int u;
    int v;
    a w;
    int x;
    int[] y;
    public static final int z = 0;
    public static final int A = 1;
    public static final int B = 2;
    public static final int C = 3;
    public static final int D = 4;
    public static final int E = 5;
    
    public j(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
        super(array, array2, n, n2, n3, n4);
        this.k = 0L;
        this.l = true;
        this.o = super.c * super.d;
        this.p = new int[this.o];
        this.t = super.c;
        this.u = super.d;
        this.v = this.t;
        this.y = new int[4000];
    }
    
    public int[] a(final long n) {
        final int c = a.c;
        int n2 = -1;
        int n3 = 1000000000;
        int n4 = 0;
        int u = this.u;
        if (this.l) {
            this.l = false;
            System.arraycopy(super.a, 0, this.p, 0, this.o);
            this.x = 0;
        }
        final int n5 = 16384 * (int)n / super.f;
        final int n6 = 16383;
        int n7 = 0;
        final int n8 = 262144 / this.t * 20480 / (n5 + 4096);
        int x = 0;
        int n9 = 0;
        int n11;
        int n10;
        int n13;
        int n12;
        int n14;
        int n17;
        int n18;
        int n19;
        while (true) {
            while (true) {
                Label_0137: {
                    if (c == 0) {
                        break Label_0137;
                    }
                    int j = b.j;
                    b.j = ++j;
                    this.y[n9] = 0;
                    ++n9;
                }
                if (n9 < 4000) {
                    continue;
                }
                break;
            }
            n10 = (n11 = n6 * (-n5 + 18432) / 18432);
            n12 = (n13 = n10 * 32 / this.t);
            n14 = 16000 / this.t;
            final int n15 = -n12 * (n5 - 1000) / n14;
            final int n16 = -n13 * (n5 - 1000) / n14;
            n17 = 400;
            n18 = n16 + n13 * n17 / n14;
            n19 = n15 - n12 * n17 / n14;
            if (c != 0) {
                continue;
            }
            break;
        }
        if (n19 >= n10) {
            n19 = 2 * n10 - n19;
            n12 = -n12;
        }
        if (n18 >= n11) {
            n18 = 2 * n11 - n18;
            n13 = -n13;
        }
        final int n21;
        final int n20 = n21 = n10;
        int n23;
        int n22 = n23 = n20 * 32 / this.t;
        final int n24 = -n22 * (n5 * 4 / 3 - 3500) / n14;
        int n25 = -n23 * (n5 * 4 / 3 - 3500) / n14 + n23 * n17 / n14;
        int n26 = n24 - n22 * n17 / n14;
        if (n26 >= n20) {
            n26 = 2 * n20 - n26;
            n22 = -n22;
        }
        if (n25 >= n21) {
            n25 = 2 * n21 - n25;
            n23 = -n23;
        }
        int n27 = 0;
        int n30;
        int n31;
        long n32;
        long n33;
        long n34;
        long n35;
        long n36;
        int t;
        int n37;
        int n38;
        while (true) {
            while (true) {
                Label_0727: {
                    if (c == 0) {
                        break Label_0727;
                    }
                    n19 += n12;
                    if (n19 >= n10) {
                        n19 = n10 - 1;
                        n12 = -n12;
                    }
                    n18 += n13;
                    if (n18 >= n11) {
                        n18 = n11 - 1;
                        n13 = -n13;
                    }
                    n26 += n22;
                    if (n26 >= n20) {
                        n26 = n20 - 1;
                        n22 = -n22;
                    }
                    n25 += n23;
                    if (n25 >= n21) {
                        n25 = n21 - 1;
                        n23 = -n23;
                    }
                    int n28 = 0;
                    int n29 = 0;
                    if (n18 > 0) {
                        n28 += n18;
                    }
                    if (n25 > 0) {
                        n28 += n25;
                    }
                    if (n19 > 0) {
                        n29 += n19;
                    }
                    if (n26 > 0) {
                        n29 += n26;
                    }
                    if (n29 > 16383) {
                        n29 = 16383;
                    }
                    if (n28 > 16383) {
                        n28 = 16383;
                    }
                    Label_0659: {
                        if (n29 > 0 && n28 > 0) {
                            if (n29 > n28) {
                                n29 -= n28;
                                n28 = 0;
                                if (c == 0) {
                                    break Label_0659;
                                }
                            }
                            n28 -= n29;
                            n29 = 0;
                        }
                    }
                    Label_0701: {
                        if (n29 > 0) {
                            this.y[n27] = n29 / 128;
                            if (c == 0) {
                                break Label_0701;
                            }
                        }
                        if (n28 > 0) {
                            this.y[n27] = -n28 / 128;
                        }
                    }
                    if (this.y[n27] != 0) {
                        n7 = n27;
                        if (x == 0) {
                            x = n27;
                        }
                    }
                    ++n27;
                }
                if (n27 < 4000) {
                    continue;
                }
                break;
            }
            n30 = this.u * 1 / 4;
            n31 = this.t * 3 / 4;
            n32 = this.t * (n5 + 300) >> 11;
            n33 = 0L;
            n34 = n32 * 3L / 8L;
            n35 = n32 * 3L / 4L;
            n36 = n32 * 7L / 8L;
            t = this.t;
            n37 = this.t * this.u - this.t;
            n38 = 0;
            n27 = 589824 / this.t;
            if (c != 0) {
                continue;
            }
            break;
        }
        long n39;
        while (true) {
            while (true) {
                Label_0864: {
                    if (c == 0) {
                        break Label_0864;
                    }
                    n27 >>= 1;
                    ++n38;
                }
                if (n27 != 0) {
                    continue;
                }
                break;
            }
            n39 = 0L;
            if (c != 0) {
                continue;
            }
            break;
        }
        while (true) {
            Label_1755: {
                if (c == 0) {
                    break Label_1755;
                }
                int n40 = 0;
                Label_0944: {
                    if (n39 == n33) {
                        n40 = 1;
                        if (c == 0) {
                            break Label_0944;
                        }
                    }
                    if (n39 == n34) {
                        n40 = 3;
                        if (c == 0) {
                            break Label_0944;
                        }
                    }
                    if (n39 == n35) {
                        n40 = 4;
                        if (c == 0) {
                            break Label_0944;
                        }
                    }
                    if (n39 == n36) {
                        n40 = 5;
                    }
                }
                a.a((int)(n39 * 262144L / n32));
                final int a = a.a;
                final int n41 = a.b * 5734 / 8192;
                final int n42 = a.a - a.b;
                final boolean b = n42 < 0;
                final int n43 = n42 / 16;
                int n44 = n43 / 91;
                if (n44 < 0) {
                    n44 = -n44;
                }
                int n45 = (n44 + n43 * n43 / 2097152) / 2;
                if (n45 >= 256) {
                    n45 = 255;
                }
                int n46 = 0;
                int n47 = 16384;
                int x2 = this.x;
            Label_1749:
                while (true) {
                    Label_1741: {
                        if (c == 0) {
                            break Label_1741;
                        }
                        final int n48 = n31 + a * x2 / 262144;
                        if (n48 < 0) {
                            break Label_1749;
                        }
                        if (n48 >= this.t) {
                            break Label_1749;
                        }
                        final int n49 = n30 + n41 * x2 / 262144;
                        if (n49 < 0) {
                            break Label_1749;
                        }
                        if (n49 >= this.u) {
                            break Label_1749;
                        }
                        if (n40 != 0) {
                            Label_1226: {
                                switch (n40) {
                                    case 1: {
                                        if (n49 <= n2) {
                                            break;
                                        }
                                        n2 = n49;
                                        if (c != 0) {
                                            break Label_1226;
                                        }
                                        break;
                                    }
                                    case 3: {
                                        if (c != 0) {
                                            break Label_1226;
                                        }
                                        break;
                                    }
                                    case 4: {
                                        if (n48 >= n3) {
                                            break;
                                        }
                                        n3 = n48;
                                        if (c != 0) {
                                            break Label_1226;
                                        }
                                        break;
                                    }
                                    case 5: {
                                        if (n48 > n4) {
                                            n4 = n48;
                                        }
                                        if (n49 < u) {
                                            u = n49;
                                        }
                                        n40 = 0;
                                        break;
                                    }
                                }
                            }
                        }
                        if (n49 == 0 && n48 > n4) {
                            n4 = n48;
                        }
                        if (n48 == this.t - 1 && n49 < u) {
                            u = n49;
                        }
                        int n51;
                        final int n50 = n51 = n48 + this.v * (this.u - n49 - 1);
                        Label_1738: {
                            if (x2 < n7) {
                                int n52 = n50;
                                if (c == 0) {
                                    int n53 = this.y[x2] * n45;
                                    if (b) {
                                        n53 = -n53;
                                    }
                                    if (n53 == 0) {
                                        this.p[n51++] = super.b[n52++];
                                        if (c == 0) {
                                            break Label_1738;
                                        }
                                    }
                                    int n58 = 0;
                                    int n59 = 0;
                                    int n60 = 0;
                                    Label_1717: {
                                        if (n53 > 0) {
                                            final int n54 = n53 * 512;
                                            final int n55 = 65536 - n53 * 2;
                                            int n56 = n52;
                                            if (n56 > t) {
                                                n56 -= n53 >> n38;
                                            }
                                            final int n57 = super.b[n56] & 0xFFFFFF;
                                            n58 = (n55 * (n57 >> 16) + n54) / 65536;
                                            n59 = (n55 * (n57 >> 8 & 0xFF) + n54) / 65536;
                                            n60 = (n55 * (n57 & 0xFF) + n54) / 65536;
                                            if (c == 0) {
                                                break Label_1717;
                                            }
                                        }
                                        int n61 = n52;
                                        if (n61 < n37) {
                                            n61 -= n53 >> n38;
                                        }
                                        final int n62 = 65536 + n53;
                                        final int n63 = super.b[n61] & 0xFFFFFF;
                                        n58 = n62 * (n63 >> 16) / 65536;
                                        n59 = n62 * (n63 >> 8 & 0xFF) / 65536;
                                        n60 = n62 * (n63 & 0xFF) / 65536;
                                    }
                                    this.p[n50] = n60 + (n59 << 8) + (n58 << 16);
                                    break Label_1738;
                                }
                            }
                            if (n47 <= 0) {
                                break Label_1749;
                            }
                            final int n64 = super.a[n50] & 0xFFFFFF;
                            final int n65 = super.b[n50] & 0xFFFFFF;
                            this.p[n50] = (n46 * (n64 & 0xFF) + n47 * (n65 & 0xFF)) / 16384 + ((n46 * (n64 >> 8 & 0xFF) + n47 * (n65 >> 8 & 0xFF)) / 16384 << 8) + ((n46 * (n64 >> 16) + n47 * (n65 >> 16)) / 16384 << 16);
                            n46 += n8;
                            n47 -= n8;
                        }
                        ++x2;
                    }
                    if (x2 < 4000) {
                        continue;
                    }
                    break;
                }
                ++n39;
            }
            if (n39 >= n32) {
                this.x = x;
                return this.p;
            }
            continue;
        }
    }
}
