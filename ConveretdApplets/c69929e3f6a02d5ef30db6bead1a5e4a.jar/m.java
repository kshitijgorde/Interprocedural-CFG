// 
// Decompiled by Procyon v0.5.30
// 

public class m extends b
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
    
    public m(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
        super(array, array2, n, n2, n3, n4);
        this.k = 0L;
        this.l = true;
        this.o = super.c * super.d;
        this.p = new int[this.o];
        this.t = super.c;
        this.u = super.d;
        this.v = this.t;
    }
    
    public int[] a(final long n) {
        final int c = a.c;
        final int n2 = 16384 * (int)n / super.f;
        if (this.l) {
            this.l = false;
            System.arraycopy(super.a, 0, this.p, 0, this.o);
            this.n = 0;
        }
        int n3 = this.n;
        int n4 = this.n;
        while (true) {
        Label_1145:
            while (true) {
                Label_1137: {
                    if (c == 0) {
                        break Label_1137;
                    }
                    final m m = this;
                    final int t = m.t;
                    final int n5 = n4 * 10240 / this.t - n2 - 512 + 3000;
                    final int n6 = n4 * 10240 / this.t - n2 + 512 + 3000;
                    int n7 = n5 * 2;
                    int n8 = n6 * 2;
                    if (n7 < 0) {
                        n7 = -n7;
                    }
                    if (n8 < 0) {
                        n8 = -n8;
                    }
                    int n9 = 0;
                    Label_0192: {
                        if (n7 < 8192) {
                            n9 = 16384 * n7 - n7 * n7;
                            if (c == 0) {
                                break Label_0192;
                            }
                        }
                        n9 = 67108864;
                    }
                    int n10 = n9 / 8192 * this.u / 8192;
                    if (n10 < 1) {
                        n10 = 1;
                    }
                    int n11 = 0;
                    Label_0249: {
                        if (n8 < 8192) {
                            n11 = 16384 * n8 - n8 * n8;
                            if (c == 0) {
                                break Label_0249;
                            }
                        }
                        n11 = 67108864;
                    }
                    int n12 = n11 / 8192 * this.u / 8192;
                    if (n12 < 1) {
                        n12 = 1;
                    }
                    final int n13 = (this.u - n10) / 2;
                    final int n14 = this.u - n13;
                    final int n15 = (this.u - n12 + 1) / 2;
                    Label_0338: {
                        if (n13 == 0) {
                            if (n5 < 0) {
                                n3 = n4 + 1;
                                if (c == 0) {
                                    break Label_0338;
                                }
                            }
                            if (c == 0) {
                                break Label_1145;
                            }
                        }
                    }
                    final int n16 = 16384 * this.u / n10;
                    int n17 = 0;
                    int n18 = 0;
                    int n19 = n14 - n15;
                    if (n5 < 0) {
                        n19 = n13 - n15;
                    }
                    if (n19 < 0) {
                        n19 = 0;
                    }
                    if (n19 > 0) {
                        n18 = 16384 * this.u / n19;
                    }
                    int n20 = 0;
                    int n21 = 262144;
                    if (n13 < this.u / 4) {
                        n21 -= 262144 * (this.u / 4 - n13) / (this.u / 4);
                    }
                    if (n21 > 262144) {
                        n21 = 262144;
                    }
                    int n22 = -n21 / ((this.u / 2 - n13) / 4 + 1) / 2;
                    int n23 = n21 / ((this.u / 2 - n15) / 4 + 1);
                    int n24 = n21 - 1;
                    int n25 = 0;
                    int n26 = 0;
                    while (true) {
                        Label_1125: {
                            if (c == 0) {
                                break Label_1125;
                            }
                            final int n27 = n4;
                            int n28 = n27 + (this.u - n26 - 1) * this.v;
                            Label_1122: {
                                if ((n26 < n13 && n26 < n15) || n26 > n14) {
                                    this.p[n28++] = super.e;
                                    if (c == 0) {
                                        break Label_1122;
                                    }
                                }
                                int n29 = 0;
                                Label_0767: {
                                    if (n5 < 0 && n26 >= n13) {
                                        n29 = n17++ * n16 / 16384;
                                        n24 += n22;
                                        if (n24 < 0) {
                                            if ((n26 - n13) * 4 <= (n14 - n13) * 3) {
                                                break Label_0767;
                                            }
                                            n24 = 0;
                                            if (n22 < 0) {
                                                n22 = -n22;
                                            }
                                            n22 *= 2;
                                            if (c == 0) {
                                                break Label_0767;
                                            }
                                        }
                                        if (n24 < n21) {
                                            break Label_0767;
                                        }
                                        n22 = -n22;
                                        n24 = n21 - 1;
                                        if (c == 0) {
                                            break Label_0767;
                                        }
                                    }
                                    n29 = n20++ * n18 / 16384;
                                    n25 += n23;
                                    if (n25 < 0) {
                                        if ((n26 - n13) * 4 <= (n14 - n13) * 3) {
                                            break Label_0767;
                                        }
                                        n25 = 0;
                                        if (n23 < 0) {
                                            n23 = -n23;
                                        }
                                        n23 /= 2;
                                        if (c == 0) {
                                            break Label_0767;
                                        }
                                    }
                                    if (n25 >= n21 / 2) {
                                        n23 = -n23;
                                        n25 = n21 / 2 - 1;
                                    }
                                }
                                if (n29 >= this.u) {
                                    n29 = this.u - 1;
                                }
                                final int n30 = n27 + (this.u - n29 - 1) * this.v;
                                if (n5 < 0 && n26 >= n13) {
                                    if (n24 > 10) {
                                        int n31 = n24 * 256;
                                        final int n32 = 262144 - n31 / 256;
                                        if (n26 < this.u / 2) {
                                            n31 = 0;
                                        }
                                        final int n33 = super.b[n30] & 0xFFFFFF;
                                        this.p[n28++] = (n32 * (n33 & 0xFF) + n31) / 262144 + ((n32 * (n33 >> 8 & 0xFF) + n31) / 262144 << 8) + ((n32 * (n33 >> 16) + n31) / 262144 << 16);
                                        if (c == 0) {
                                            break Label_1122;
                                        }
                                    }
                                    this.p[n28++] = super.b[n30];
                                    if (c == 0) {
                                        break Label_1122;
                                    }
                                }
                                if (n25 > 10) {
                                    int n34 = n25 * 256;
                                    final int n35 = 262144 - n34 / 256;
                                    if (n26 > this.u / 2) {
                                        n34 = 0;
                                    }
                                    final int n36 = super.a[n30] & 0xFFFFFF;
                                    this.p[n28++] = (n35 * (n36 & 0xFF) + n34) / 262144 + ((n35 * (n36 >> 8 & 0xFF) + n34) / 262144 << 8) + ((n35 * (n36 >> 16) + n34) / 262144 << 16);
                                    if (c == 0) {
                                        break Label_1122;
                                    }
                                }
                                this.p[n28++] = super.a[n30];
                            }
                            ++n26;
                        }
                        if (n26 < this.u) {
                            continue;
                        }
                        break;
                    }
                    ++n4;
                }
                if (n4 < this.t) {
                    continue;
                }
                break;
            }
            this.n = n3;
            final m m = this;
            if (c == 0) {
                return this.p;
            }
            continue;
        }
    }
}
