// 
// Decompiled by Procyon v0.5.30
// 

public class d extends b
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
    
    public d(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
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
        this.r = this.t / 2;
        this.s = this.u / 2;
        final short[] array = { 0, 51, 106, 171, 256, 383, 618, 1287, 9999 };
        final int l = this.l ? 1 : 0;
        if (c == 0) {
            if (l != 0) {
                this.q = 0;
                this.l = false;
                System.arraycopy(super.a, 0, this.p, 0, this.o);
            }
            final int n2 = this.s - 1;
        }
        int n3 = l;
        short n4 = -1;
        int r = this.r;
        int n5 = 1;
        int n6 = 1;
        final int n8;
        final int n7 = n8 = (int)n * 4 / super.f;
        Label_0237: {
            if (c == 0) {
                Label_0211: {
                    switch (n8) {
                        case 0: {
                            if (c != 0) {
                                break Label_0211;
                            }
                            break Label_0237;
                        }
                        case 1: {
                            n4 = 1;
                            n6 = 0;
                            ++n3;
                            if (c != 0) {
                                break Label_0211;
                            }
                            break Label_0237;
                        }
                        case 2: {
                            n5 = -1;
                            n4 = 1;
                            ++n3;
                            --r;
                            if (c != 0) {
                                break;
                            }
                            break Label_0237;
                        }
                    }
                }
                n6 = 0;
            }
            n5 = n8;
            --r;
        }
        int n9 = (int)n * 16384 * 4 / super.f & 0x3FFF;
        final int n10 = n6;
        if (c == 0 && n10 == 0) {
            n9 = 16383 - n9;
            goto Label_0274;
        }
        final int n11 = n10;
        final int n12 = n9 - n11 * 2048;
        final short n13 = (short)((array[n11] * (2048 - n12) + array[n11 + 1] * n12) / 2048);
        short n14 = 0;
    Label_0349_Outer:
        while (true) {
        Label_0613_Outer:
            while (true) {
                Label_0498: {
                    if (c == 0) {
                        break Label_0498;
                    }
                    final int n15 = this.v * (n3 + n14 * n4);
                    final int n17;
                    final int q;
                    int n16 = n17 + q;
                    int r2 = 0;
                    int n18 = this.r;
                    int n20;
                    final int n19 = n20 = n6;
                    if (c == 0) {
                        if (n19 != 0) {
                            final short n21;
                            n18 = (n21 = (short)(n14 * n13 / 256));
                            if (c == 0 && n21 > this.r) {
                                n18 = this.r;
                                if (c == 0) {
                                    goto Label_0452;
                                }
                            }
                            else {
                                int n22 = n21;
                                while (true) {
                                    Label_0489: {
                                        if (c == 0) {
                                            break Label_0489;
                                        }
                                        this.p[n16] = super.b[n16++];
                                        if (n5 == -1) {
                                            n16 -= 2;
                                        }
                                        ++n22;
                                    }
                                    if (n22 >= n18) {
                                        ++n14;
                                        break Label_0498;
                                    }
                                    continue;
                                }
                            }
                        }
                        r2 = n14 * n13 / 256;
                        final int n23;
                        n20 = (n23 = r2);
                    }
                    final int r3 = this.r;
                    if (c == 0) {
                        if (n19 > r3) {
                            r2 = this.r;
                        }
                        n20 = n16;
                    }
                    n16 = n20 + r3;
                    goto Label_0452;
                }
                if (n14 >= this.s) {
                    int n24;
                    final int n17 = n24 = n7;
                    if (c == 0) {
                        final int q = this.q;
                        if (c != 0) {
                            continue Label_0613_Outer;
                        }
                        if (n17 <= q) {
                            return this.p;
                        }
                        n24 = 0;
                    }
                    int n25 = n24;
                    int n26 = 0;
                    final int q2 = this.q;
                    if (c == 0) {
                        Label_0596: {
                            switch (q2) {
                                case 0: {
                                    n25 = this.r;
                                    if (c != 0) {
                                        break Label_0596;
                                    }
                                    break;
                                }
                                case 1: {
                                    n25 = this.r;
                                    n26 = this.s;
                                    if (c != 0) {
                                        break Label_0596;
                                    }
                                    break;
                                }
                                case 2: {
                                    n26 = this.s;
                                    break;
                                }
                            }
                        }
                    }
                    int n27 = q2;
                    while (true) {
                        Label_0650: {
                            if (c == 0) {
                                break Label_0650;
                            }
                            final int n28 = n25 + (n26 + n27) * this.t;
                            System.arraycopy(super.b, n28, this.p, n28, this.r);
                            ++n27;
                        }
                        if (n27 < this.s) {
                            continue;
                        }
                        break;
                    }
                    ++this.q;
                    return this.p;
                }
                break;
            }
            continue Label_0349_Outer;
        }
    }
}
