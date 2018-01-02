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
        int n25;
        while (true) {
            while (true) {
                Label_0436: {
                    if (c == 0) {
                        break Label_0436;
                    }
                    this.z[n22] = this.z[n22 - 1] + this.B[n22 - 1];
                    this.A[n22] = this.A[n22 - 1] + this.C[n22 - 1];
                    n20 = 262144 * (n22 + 1) / n14;
                    final int n23 = n22 & 0x1;
                    int n24;
                    if (c == 0 && n23 != 0) {
                        n24 = n15;
                        if (c != 0) {
                            goto Label_0355;
                        }
                    }
                    else {
                        n24 = n23;
                    }
                    a.a(n20 + n17);
                    this.B[n22] = n11 + (n24 * a.a + 131072) / 262144 - this.z[n22];
                    this.C[n22] = n12 + (n24 * a.b + 131072) / 262144 - this.A[n22];
                    ++n22;
                }
                if (n22 < n14) {
                    continue;
                }
                break;
            }
            n25 = 0;
            if (c != 0) {
                continue;
            }
            break;
        }
        int n27 = 0;
        int n26;
        int n28;
        int n29;
        int n31;
        int n30;
        int n33;
        int n32;
        int n34 = 0;
        int n35 = 0;
        int n36;
        int n37;
        int n38;
        int n39;
        int n40;
        int n41;
        Label_0480_Outer:Label_0508_Outer:Label_0550_Outer:Label_0657_Outer:
        while (true) {
            Label_0760: {
                if (c == 0) {
                    break Label_0760;
                }
                n26 = n27;
                while (true) {
                    while (true) {
                        Label_0483: {
                            if (c == 0) {
                                break Label_0483;
                            }
                            this.y[n25][n26] = this.t + 1;
                            ++n26;
                        }
                        if (n26 < this.x) {
                            continue Label_0480_Outer;
                        }
                        break;
                    }
                    n28 = 0;
                    n29 = 0;
                    if (c != 0) {
                        continue Label_0508_Outer;
                    }
                    break;
                }
                while (true) {
                    while (true) {
                        Label_0637: {
                            if (c == 0) {
                                break Label_0637;
                            }
                            n30 = (n31 = n25);
                            n32 = (n33 = this.A[n29] + this.C[n29]);
                            Label_0634: {
                                Label_0587: {
                                    Label_0567: {
                                        if (c != 0) {
                                            break Label_0567;
                                        }
                                        if (n30 <= n32) {
                                            n34 = n25;
                                            n35 = this.A[n29];
                                            if (c != 0) {
                                                break Label_0567;
                                            }
                                            if (n34 > n35) {
                                                break Label_0587;
                                            }
                                        }
                                        n36 = this.A[n29] + this.C[n29];
                                    }
                                    if (c == 0) {
                                        if (n30 <= n32) {
                                            break Label_0634;
                                        }
                                        n33 = this.A[n29];
                                    }
                                    if (n34 > n35) {
                                        break Label_0634;
                                    }
                                }
                                this.y[n25][n28++] = this.z[n29] + (n25 - this.A[n29]) * this.B[n29] / this.C[n29];
                            }
                            ++n29;
                        }
                        if (n29 < n14) {
                            continue Label_0550_Outer;
                        }
                        break;
                    }
                    n37 = 0;
                    if (c != 0) {
                        continue Label_0657_Outer;
                    }
                    break;
                }
                while (true) {
                    Label_0750: {
                        if (c == 0) {
                            break Label_0750;
                        }
                        n38 = 0;
                        while (true) {
                            Label_0740: {
                                if (c == 0) {
                                    break Label_0740;
                                }
                                n39 = this.y[n25][n37];
                                Label_0737: {
                                    if (c == 0) {
                                        if (n39 >= this.y[n25][n38]) {
                                            break Label_0737;
                                        }
                                        n40 = this.y[n25][n37];
                                    }
                                    n41 = n39;
                                    this.y[n25][n37] = this.y[n25][n38];
                                    this.y[n25][n38] = n41;
                                }
                                ++n38;
                            }
                            if (n38 <= n37) {
                                continue;
                            }
                            break;
                        }
                        ++n37;
                    }
                    if (n37 < n14) {
                        continue;
                    }
                    break;
                }
                ++n25;
            }
            if (n25 < this.u) {
                continue;
            }
            n27 = 0;
            if (c != 0) {
                continue;
            }
            break;
        }
        int n42 = n27;
    Label_0804_Outer:
        while (true) {
            Label_1065: {
                if (c == 0) {
                    break Label_1065;
                }
                int n43 = 1;
                int n44 = 0;
                int n45 = 0;
                int n46 = 0;
                int n50 = 0;
                int n52;
                while (true) {
                    while (true) {
                        Label_0976: {
                            if (c == 0) {
                                break Label_0976;
                            }
                            int n49;
                            final int n48;
                            int n47 = n48 = (n49 = n46);
                            Label_0973: {
                                Label_0970: {
                                    if (c == 0) {
                                        if (n50 <= this.y[n42][n44]) {
                                            break Label_0970;
                                        }
                                        n49 = (n47 = n45);
                                    }
                                    if (c == 0) {
                                        if (n47 != 0) {
                                            Label_0875: {
                                                if (n43 != 0) {
                                                    System.arraycopy(super.a, n3, this.p, n2, n45);
                                                    if (c == 0) {
                                                        break Label_0875;
                                                    }
                                                }
                                                System.arraycopy(super.b, n4, this.p, n2, n45);
                                            }
                                            n2 += n45;
                                            n3 += n45;
                                            n4 += n45;
                                        }
                                        n45 = 0;
                                        n49 = (super.a[n3++] & 0xFEFEFE) >> 1;
                                    }
                                    this.p[n2++] = n49 + ((super.b[n4++] & 0xFEFEFE) >> 1);
                                    final int n51 = n43;
                                    if (c == 0 && n51 != 0) {}
                                    n43 = n51;
                                    ++n44;
                                    if (c == 0) {
                                        break Label_0973;
                                    }
                                }
                                ++n45;
                            }
                            ++n46;
                        }
                        if (n46 < this.t) {
                            continue Label_0804_Outer;
                        }
                        break;
                    }
                    int n49;
                    int n47;
                    n50 = (n47 = (n49 = (n52 = n45)));
                    if (c != 0) {
                        continue;
                    }
                    break;
                }
                Label_1062: {
                    if (c == 0) {
                        if (n50 == 0) {
                            break Label_1062;
                        }
                        n52 = n43;
                    }
                    Label_1043: {
                        if (n52 != 0) {
                            System.arraycopy(super.a, n3, this.p, n2, n45);
                            if (c == 0) {
                                break Label_1043;
                            }
                        }
                        System.arraycopy(super.b, n4, this.p, n2, n45);
                    }
                    n2 += n45;
                    n3 += n45;
                    n4 += n45;
                }
                ++n42;
            }
            if (n42 >= this.u) {
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
