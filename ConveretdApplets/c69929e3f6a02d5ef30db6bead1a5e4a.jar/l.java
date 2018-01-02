// 
// Decompiled by Procyon v0.5.30
// 

public class l extends b
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
    int[][] y;
    int[] z;
    int[] A;
    int[] B;
    int[] C;
    
    public l(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
        super(array, array2, n, n2, n3, n4);
        this.k = 0L;
        this.l = true;
        this.o = super.c * super.d;
        this.p = new int[this.o];
        this.t = super.c;
        this.u = super.d;
        this.v = this.t;
        this.x = 10;
        this.y = new int[this.u][this.x];
        this.z = new int[this.x];
        this.A = new int[this.x];
        this.B = new int[this.x];
        this.C = new int[this.x];
    }
    
    public int[] a(final long n) {
        int c = a.c;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        final int n5 = 10;
        final int n6 = 45 * this.u / 64;
        final int n7 = 16;
        final int n8 = 6;
        final int n9 = -196608;
        final int n10 = 0;
        final int n11 = this.t / 2;
        final int n12 = this.u / 2;
        final int n13 = 1;
        final int n14 = n5;
        final int n15 = n6 * (int)n * ((int)n / n13) / (super.f * (super.f / n13));
        final int n16 = n15 * n7 / n8;
        final int n17 = n9 * (int)n / super.f + n10;
        final int n18 = 0;
        final int n19 = n15;
        a.a(n18 + n17);
        this.z[0] = n11 + (n19 * a.a + 131072) / 262144;
        this.A[0] = n12 + (n19 * a.b + 131072) / 262144;
        int n20 = 262144 / n14;
        final int n21 = n16;
        a.a(n20 + n17);
        this.B[0] = n11 + (n21 * a.a + 131072) / 262144 - this.z[0];
        this.C[0] = n12 + (n21 * a.b + 131072) / 262144 - this.A[0];
        int n22 = 1;
        int n24;
        while (true) {
            while (true) {
                Label_0431: {
                    if (c == 0) {
                        break Label_0431;
                    }
                    this.z[n22] = this.z[n22 - 1] + this.B[n22 - 1];
                    this.A[n22] = this.A[n22 - 1] + this.C[n22 - 1];
                    n20 = 262144 * (n22 + 1) / n14;
                    int n23 = 0;
                    Label_0354: {
                        if ((n22 & 0x1) != 0x0) {
                            n23 = n15;
                            if (c == 0) {
                                break Label_0354;
                            }
                        }
                        n23 = n16;
                    }
                    a.a(n20 + n17);
                    this.B[n22] = n11 + (n23 * a.a + 131072) / 262144 - this.z[n22];
                    this.C[n22] = n12 + (n23 * a.b + 131072) / 262144 - this.A[n22];
                    ++n22;
                }
                if (n22 < n14) {
                    continue;
                }
                break;
            }
            n24 = 0;
            if (c != 0) {
                continue;
            }
            break;
        }
        int n26 = 0;
        int n25;
        int n27;
        int n28;
        int n29;
        int n30;
        int n31;
        Label_0475_Outer:Label_0503_Outer:Label_0535_Outer:Label_0637_Outer:
        while (true) {
            Label_0735: {
                if (c == 0) {
                    break Label_0735;
                }
                n25 = n26;
                while (true) {
                    while (true) {
                        Label_0478: {
                            if (c == 0) {
                                break Label_0478;
                            }
                            this.y[n24][n25] = this.t + 1;
                            ++n25;
                        }
                        if (n25 < this.x) {
                            continue Label_0475_Outer;
                        }
                        break;
                    }
                    n27 = 0;
                    n28 = 0;
                    if (c != 0) {
                        continue Label_0503_Outer;
                    }
                    break;
                }
                while (true) {
                    while (true) {
                        Label_0617: {
                            if (c == 0) {
                                break Label_0617;
                            }
                            Label_0614: {
                                Label_0567: {
                                    if (n24 <= this.A[n28] + this.C[n28] && n24 > this.A[n28]) {
                                        break Label_0567;
                                    }
                                    if (n24 <= this.A[n28] + this.C[n28] || n24 > this.A[n28]) {
                                        break Label_0614;
                                    }
                                }
                                this.y[n24][n27++] = this.z[n28] + (n24 - this.A[n28]) * this.B[n28] / this.C[n28];
                            }
                            ++n28;
                        }
                        if (n28 < n14) {
                            continue Label_0535_Outer;
                        }
                        break;
                    }
                    n29 = 0;
                    if (c != 0) {
                        continue Label_0637_Outer;
                    }
                    break;
                }
                while (true) {
                    Label_0725: {
                        if (c == 0) {
                            break Label_0725;
                        }
                        n30 = 0;
                        while (true) {
                            Label_0715: {
                                if (c == 0) {
                                    break Label_0715;
                                }
                                if (this.y[n24][n29] < this.y[n24][n30]) {
                                    n31 = this.y[n24][n29];
                                    this.y[n24][n29] = this.y[n24][n30];
                                    this.y[n24][n30] = n31;
                                }
                                ++n30;
                            }
                            if (n30 <= n29) {
                                continue;
                            }
                            break;
                        }
                        ++n29;
                    }
                    if (n29 < n14) {
                        continue;
                    }
                    break;
                }
                ++n24;
            }
            if (n24 < this.u) {
                continue;
            }
            n26 = 0;
            if (c != 0) {
                continue;
            }
            break;
        }
        int n32 = n26;
        while (true) {
            Label_1020: {
                if (c == 0) {
                    break Label_1020;
                }
                boolean b = true;
                int n33 = 0;
                int n34 = 0;
                int n35 = 0;
                int n36 = 0;
                while (true) {
                    Label_0936: {
                        if (c == 0) {
                            break Label_0936;
                        }
                        Label_0933: {
                            if (n36 > this.y[n32][n33]) {
                                if (n34 != 0) {
                                    Label_0840: {
                                        if (b) {
                                            System.arraycopy(super.a, n3, this.p, n2, n34);
                                            if (c == 0) {
                                                break Label_0840;
                                            }
                                        }
                                        System.arraycopy(super.b, n4, this.p, n2, n34);
                                    }
                                    n2 += n34;
                                    n3 += n34;
                                    n4 += n34;
                                }
                                n34 = 0;
                                this.p[n2++] = ((super.a[n3++] & 0xFEFEFE) >> 1) + ((super.b[n4++] & 0xFEFEFE) >> 1);
                                b = !b;
                                ++n33;
                                if (c == 0) {
                                    break Label_0933;
                                }
                            }
                            ++n34;
                        }
                        ++n35;
                    }
                    if (n35 < this.t) {
                        continue;
                    }
                    n36 = n34;
                    if (c != 0) {
                        continue;
                    }
                    break;
                }
                if (n36 != 0) {
                    Label_0998: {
                        if (b) {
                            System.arraycopy(super.a, n3, this.p, n2, n34);
                            if (c == 0) {
                                break Label_0998;
                            }
                        }
                        System.arraycopy(super.b, n4, this.p, n2, n34);
                    }
                    n2 += n34;
                    n3 += n34;
                    n4 += n34;
                }
                ++n32;
            }
            if (n32 >= this.u) {
                final int[] p = this.p;
                if (b.j != 0) {
                    a.c = ++c;
                }
                return p;
            }
            continue;
        }
    }
}
