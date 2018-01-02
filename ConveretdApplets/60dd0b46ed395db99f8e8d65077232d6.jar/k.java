// 
// Decompiled by Procyon v0.5.30
// 

public class k extends b
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
    
    public k(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
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
        final int n2 = 0;
        int l;
        final int n3 = l = (this.l ? 1 : 0);
        if (c == 0) {
            if (n3 != 0) {
                this.l = false;
                this.n = 0;
            }
            l = (int)n * 16384 / super.f;
        }
        int n4 = l;
        int n6;
        final int n5 = n6 = n2;
        if (c == 0) {
            if (n5 != 0) {
                n4 = 16384 - n4;
            }
            a.a(n4 * 8);
            final int n7;
            n6 = (n7 = n4);
        }
        final int n8 = 8192;
        Label_0127: {
            if (c == 0) {
                if (n5 < n8) {
                    a.a = (a.a + n4 * 32) / 2;
                    if (c == 0) {
                        break Label_0127;
                    }
                }
                n6 = a.a + (16384 - n4) * 32;
            }
            a.a = n6 / n8;
        }
        a.b = (a.b + (8192 - n4) * 32) / 2;
        int n9 = 1;
        final int b = a.b;
        if (c == 0) {
            if (b < 0) {
                n9 = -1;
                a.b = -a.b;
            }
            final int n10 = this.t / 2 * ((262144 - a.b) / 16) / 16384;
        }
        final int n11 = b;
        final int n12 = this.u * (a.a / 16) / 49152;
        final int n13 = this.u * 65536 / (this.u - n12);
        final int n14 = n9 * n12 * n13 / (this.t - 2 * n11 + 1);
        final int n15 = this.t * 65536 / (this.t - 2 * n11 + 1);
        final int n16 = this.t * 65536 / (this.t - 2 * n11 + 1);
        int n17 = this.n - 2;
        int n19;
        final int n18 = n19 = n17;
        if (c == 0) {
            if (n18 >= n11) {
                n17 = n11 - 1;
            }
            final int n20;
            n19 = (n20 = n17);
        }
        if (c == 0) {
            if (n18 < 0) {
                n17 = 0;
            }
            n19 = this.t - n17;
        }
        final int n21 = n19;
        int n22 = 0;
        while (true) {
            Label_0685: {
                if (c == 0) {
                    break Label_0685;
                }
                final int n23 = n9;
                int n24;
                if (c == 0 && n23 > 0) {
                    n24 = n22 * n13;
                    if (c != 0) {
                        goto Label_0380;
                    }
                }
                else {
                    n24 = n23;
                }
                int n25 = 0;
                int n26 = this.v * n22 + n17;
                int n27 = 0;
                int n28 = n17;
                while (true) {
                    Label_0675: {
                        if (c == 0) {
                            break Label_0675;
                        }
                        int n30;
                        final int n29 = n30 = n27;
                        Label_0672: {
                            final int n32;
                            Label_0476: {
                                Label_0458: {
                                    Label_0439: {
                                        if (c == 0) {
                                            if (n29 != 0) {
                                                break Label_0439;
                                            }
                                            final int n31;
                                            n30 = (n31 = n28);
                                        }
                                        n32 = n11;
                                        if (c != 0) {
                                            break Label_0476;
                                        }
                                        if (n29 > n32) {
                                            break Label_0458;
                                        }
                                    }
                                    this.p[n26++] = super.e;
                                    if (c == 0) {
                                        break Label_0672;
                                    }
                                }
                                n24 -= n14;
                                n25 = (n30 = n25 + n16);
                            }
                            int n34;
                            final int n33 = n34 = n30 / n32;
                            if (c == 0) {
                                if (n33 >= this.t) {
                                    n27 = 1;
                                    this.p[n26++] = super.e;
                                    if (c == 0) {
                                        break Label_0672;
                                    }
                                }
                                final int n35;
                                n34 = (n35 = n24);
                            }
                            if (c == 0) {
                                if (n33 < 0) {
                                    this.p[n26++] = super.e;
                                    if (c == 0) {
                                        break Label_0672;
                                    }
                                }
                                n34 = n24 / 65536;
                            }
                            final int n37;
                            final int n36 = n37 = n34;
                            final int u = this.u;
                            if (c == 0) {
                                if (n37 >= u) {
                                    this.p[n26++] = super.e;
                                    if (c == 0) {
                                        break Label_0672;
                                    }
                                }
                                final int n38 = this.v * n36;
                            }
                            final int n39 = n37 + u;
                            final int n40 = n9;
                            int n41;
                            if (c == 0 && n40 > 0) {
                                n41 = 1;
                                if (c != 0) {
                                    goto Label_0614;
                                }
                            }
                            else {
                                n41 = n40;
                            }
                            final int n42 = n41 ^ n2;
                            Label_0655: {
                                if (c == 0) {
                                    if (n42 == 0) {
                                        break Label_0655;
                                    }
                                    this.p[n26++] = super.a[n39];
                                }
                                if (c == 0) {
                                    break Label_0672;
                                }
                            }
                            this.p[n26++] = super.b[n39];
                        }
                        ++n28;
                    }
                    if (n28 < n21) {
                        continue;
                    }
                    break;
                }
                ++n22;
            }
            if (n22 < this.u) {
                continue;
            }
            break;
        }
        this.n = n11;
        if (c == 0) {
            return this.p;
        }
        goto Label_0380;
    }
}
