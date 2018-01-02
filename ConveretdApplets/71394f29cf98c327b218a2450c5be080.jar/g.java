// 
// Decompiled by Procyon v0.5.30
// 

public class g extends b
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
    boolean y;
    boolean z;
    int A;
    
    public g(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
        super(array, array2, n, n2, n3, n4);
        this.k = 0L;
        this.l = true;
        this.o = super.c * super.d;
        this.p = new int[this.o];
        this.t = super.c;
        this.u = super.d;
        this.v = this.t;
        this.A = 0;
    }
    
    public int[] a(final long n) {
        final int c = a.c;
        if (this.l) {
            this.l = false;
            this.x = this.t / 2 - 1;
            this.y = false;
            this.z = false;
            System.arraycopy(super.a, 0, this.p, 0, this.o);
        }
        final int n2 = 32;
        int n3 = (65536 - n2) * (int)n / super.f + n2;
        boolean b = false;
        if (!this.y && n3 >= 16384) {
            n3 = 16383;
        }
        if (!this.z && n3 >= 32768) {
            this.z = true;
            n3 = 32767;
        }
        int n4 = 0;
        Label_0144: {
            if (n3 < 16384) {
                n4 = 1;
                if (c == 0) {
                    break Label_0144;
                }
            }
            n4 = -1;
        }
        if (this.A != 0) {
            n4 = -n4;
        }
        if (n3 >= 65536) {
            n3 = 65535;
        }
        if (n3 >= 32768) {
            b = true;
            n4 = -1;
            n3 = (n3 & 0x7FFF) / 2;
            if (n3 >= 16382) {
                n3 = 16382;
            }
        }
        int n5 = n3 & 0x3FFF;
        if (n5 == 0) {
            n5 = 1;
        }
        int n6 = 0;
        Label_0239: {
            if (b) {
                n6 = 2;
                if (c == 0) {
                    break Label_0239;
                }
            }
            n6 = 1;
        }
        int x = 0;
    Label_1056_Outer:
        while (true) {
            Label_1187: {
                if (c == 0) {
                    break Label_1187;
                }
                final int n8;
                int n7 = n8;
                x = 0;
                int n9 = -1;
                final int n10 = 229376;
                int n11 = n10 * 20 / this.t;
                if (b) {
                    n11 = n11 * 1024 / (1024 - n5 / 16 + 1);
                }
                int n12 = -n10 / 9999;
                int n13 = 0;
                int x2 = 0;
                while (true) {
                    Label_0999: {
                        if (c == 0) {
                            break Label_0999;
                        }
                        int n14 = 0;
                        Label_0363: {
                            if (x2 != 0) {
                                n14 = 16 * n13 * 16384 / this.t;
                                if (c == 0) {
                                    break Label_0363;
                                }
                            }
                            n14 = 16 * n13 * n5 / this.t;
                        }
                        a.a(n14);
                        final int a = a.a;
                        final int b2 = a.b;
                        int n15 = this.t * 41721 / n5;
                        if (b) {
                            n15 = this.t * 41721 / 16384 * (16384 - n5) / 16384;
                        }
                        int n16 = 11469;
                        if (b) {
                            n16 = 16384 - 4915 * (16384 - n5) / 16384;
                        }
                        final int n17 = n15 / 16 * (262144 - b2) / 262144;
                        int n18 = n15 / 16 * a / 262144;
                        final int n19 = n16 * n17 / 16384;
                        if (n18 != n9) {
                            Label_0540: {
                                if (n18 > n9 + 1) {
                                    n18 = n9 + 1;
                                    if (c == 0) {
                                        break Label_0540;
                                    }
                                }
                                if (n18 < n9 - 1) {
                                    n18 = n9 - 1;
                                }
                            }
                            n9 = n18;
                            int n20 = this.u - 1 - n19;
                            if (b) {
                                n20 -= this.u * n5 / 32768;
                            }
                            final int n22;
                            int n21 = n22 = this.t / 2 - 1 - n13 * n4;
                            int n23 = this.t / 2 - 1 - n18 * n4;
                            int n24 = this.t / 2 - 1 - n18 * n4;
                            Label_0646: {
                                if (x <= n18) {
                                    x = n18;
                                    if (c == 0) {
                                        break Label_0646;
                                    }
                                }
                                n7 = 1;
                            }
                            int n25 = 0;
                            if (n4 == -1 && n7 != 0) {
                                n12 += n11;
                                if (n12 >= n10) {
                                    n12 = n10 - 1;
                                    n11 = -n11;
                                }
                            }
                            int n26 = this.u - 1;
                            while (true) {
                                Label_0991: {
                                    if (c == 0) {
                                        break Label_0991;
                                    }
                                    Label_0968: {
                                        if (n21 >= this.o || n26 > n20) {
                                            if (n7 != 0) {
                                                ++n23;
                                                if (c == 0) {
                                                    break Label_0968;
                                                }
                                            }
                                            this.p[n23++] = super.b[n24];
                                            if (c == 0) {
                                                break Label_0968;
                                            }
                                        }
                                        Label_0891: {
                                            if (n12 > 10) {
                                                final int n27 = n12 * 256;
                                                final int n28 = 262144 - n12;
                                                final int n29 = super.a[n21++] & 0xFFFFFF;
                                                this.p[n23++] = (n28 * (n29 & 0xFF) + n27) / 262144 + ((n28 * (n29 >> 8 & 0xFF) + n27) / 262144 << 8) + ((n28 * (n29 >> 16) + n27) / 262144 << 16);
                                                if (c == 0) {
                                                    break Label_0891;
                                                }
                                            }
                                            this.p[n23++] = super.a[n21++];
                                        }
                                        if (b) {
                                            final int n30 = 16384 - n5;
                                            int u = ++n25 * 16384 / n30 * 16384 / n30;
                                            if (u > this.u) {
                                                u = this.u;
                                            }
                                            n21 = n22 + this.v * u;
                                            if (c == 0) {
                                                break Label_0968;
                                            }
                                        }
                                        n21 += this.v - 1;
                                    }
                                    n23 += this.v - 1;
                                    n24 += this.v;
                                    --n26;
                                }
                                if (n26 >= 0) {
                                    continue Label_1056_Outer;
                                }
                                break;
                            }
                        }
                        ++n13;
                    }
                    if (n13 < this.t / 2) {
                        continue Label_1056_Outer;
                    }
                    x2 = this.x;
                    if (c != 0) {
                        continue Label_1056_Outer;
                    }
                    break;
                }
                while (true) {
                    Label_1169: {
                        if (x2 < 0 || this.x == x) {
                            break Label_1169;
                        }
                        n13 = x + 1;
                        while (true) {
                            Label_1158: {
                                if (c == 0) {
                                    break Label_1158;
                                }
                                final int n31 = this.t / 2 - 1 - n13 * n4;
                                final int x3;
                                int n32 = x3;
                                if (n32 < 0) {
                                    n32 = 0;
                                }
                                if (n32 > this.t - 1) {
                                    n32 = this.t - 1;
                                }
                                int n33 = n32;
                                int n34 = n32;
                                int n35 = 0;
                                while (true) {
                                    Label_1146: {
                                        if (c == 0) {
                                            break Label_1146;
                                        }
                                        this.p[n33++] = super.b[n34++];
                                        n34 += this.v - 1;
                                        n33 += this.v - 1;
                                        ++n35;
                                    }
                                    if (n35 < this.u) {
                                        continue;
                                    }
                                    break;
                                }
                                ++n13;
                            }
                            if (n13 <= this.x + 1) {
                                continue Label_1056_Outer;
                            }
                            break;
                        }
                    }
                    n4 = -n4;
                    --n6;
                    final int x3 = this.x;
                    if (c != 0) {
                        continue;
                    }
                    break;
                }
            }
            if (n6 > 0) {
                continue;
            }
            this.x = x;
            final int n8 = n5;
            if (c == 0) {
                if (n8 == 16383 && !this.y) {
                    this.y = true;
                    this.x = this.t / 2;
                }
                return this.p;
            }
            continue;
        }
    }
}
