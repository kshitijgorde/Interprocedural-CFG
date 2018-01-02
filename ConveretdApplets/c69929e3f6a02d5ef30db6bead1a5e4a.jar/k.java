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
        final boolean b = false;
        if (this.l) {
            this.l = false;
            this.n = 0;
        }
        int n2 = (int)n * 16384 / super.f;
        if (b) {
            n2 = 16384 - n2;
        }
        a.a(n2 * 8);
        Label_0112: {
            if (n2 < 8192) {
                a.a = (a.a + n2 * 32) / 2;
                if (c == 0) {
                    break Label_0112;
                }
            }
            a.a = (a.a + (16384 - n2) * 32) / 2;
        }
        a.b = (a.b + (8192 - n2) * 32) / 2;
        int n3 = 1;
        if (a.b < 0) {
            n3 = -1;
            a.b = -a.b;
        }
        final int n4 = this.t / 2 * ((262144 - a.b) / 16) / 16384;
        final int n5 = this.u * (a.a / 16) / 49152;
        final int n6 = this.u * 65536 / (this.u - n5);
        final int n7 = n3 * n5 * n6 / (this.t - 2 * n4 + 1);
        final int n8 = this.t * 65536 / (this.t - 2 * n4 + 1);
        final int n9 = this.t * 65536 / (this.t - 2 * n4 + 1);
        int n10 = this.n - 2;
        if (n10 >= n4) {
            n10 = n4 - 1;
        }
        if (n10 < 0) {
            n10 = 0;
        }
        final int n11 = this.t - n10;
        int n12 = 0;
        while (true) {
            while (true) {
                Label_0615: {
                    if (c == 0) {
                        break Label_0615;
                    }
                    int n13 = 0;
                    Label_0355: {
                        if (n3 > 0) {
                            n13 = n12 * n6;
                            if (c == 0) {
                                break Label_0355;
                            }
                        }
                        n13 = (n12 - n5) * n6;
                    }
                    int n14 = 0;
                    int n15 = this.v * n12 + n10;
                    int n16 = 0;
                    int n17 = n10;
                    while (true) {
                        Label_0605: {
                            if (c == 0) {
                                break Label_0605;
                            }
                            Label_0602: {
                                if (n16 != 0 || n17 <= n4) {
                                    this.p[n15++] = super.e;
                                    if (c == 0) {
                                        break Label_0602;
                                    }
                                }
                                n13 -= n7;
                                n14 += n9;
                                final int n18 = n14 / 65536;
                                if (n18 >= this.t) {
                                    n16 = 1;
                                    this.p[n15++] = super.e;
                                    if (c == 0) {
                                        break Label_0602;
                                    }
                                }
                                if (n13 < 0) {
                                    this.p[n15++] = super.e;
                                    if (c == 0) {
                                        break Label_0602;
                                    }
                                }
                                final int n19 = n13 / 65536;
                                if (n19 >= this.u) {
                                    this.p[n15++] = super.e;
                                    if (c == 0) {
                                        break Label_0602;
                                    }
                                }
                                final int n20 = this.v * n19 + n18;
                                boolean b2 = false;
                                Label_0552: {
                                    if (n3 > 0) {
                                        b2 = true;
                                        if (c == 0) {
                                            break Label_0552;
                                        }
                                    }
                                    b2 = false;
                                }
                                if (b2 ^ b) {
                                    this.p[n15++] = super.a[n20];
                                    if (c == 0) {
                                        break Label_0602;
                                    }
                                }
                                this.p[n15++] = super.b[n20];
                            }
                            ++n17;
                        }
                        if (n17 < n11) {
                            continue;
                        }
                        break;
                    }
                    ++n12;
                }
                if (n12 < this.u) {
                    continue;
                }
                break;
            }
            this.n = n4;
            if (c == 0) {
                return this.p;
            }
            continue;
        }
    }
}
