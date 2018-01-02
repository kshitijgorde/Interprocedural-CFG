// 
// Decompiled by Procyon v0.5.30
// 

public class c extends b
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
    
    public c(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
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
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = this.t;
        final int u = this.u;
        final int t = this.t;
        if (c == 0) {
            if (u > t) {
                n5 = this.u;
            }
            final int n6 = (int)n * (n5 / 2) * 1414;
            final int n7 = super.f * 1000;
        }
        final int n8 = u / t;
        final int n9 = n8 * 95 / 100;
        final int n10 = n8 * n8;
        final int n11 = n9 * n9;
        final int n12 = this.u / 2;
        final int n13 = this.t / 2;
        int n14 = 0;
        while (true) {
            Label_0407: {
                if (c == 0) {
                    break Label_0407;
                }
                final int n15 = n12 - n14;
                final int n16 = n15 * n15;
                int n17 = 0;
                while (true) {
                    Label_0395: {
                        if (c == 0) {
                            break Label_0395;
                        }
                        final int n18 = n13 - n17;
                        final int n19 = n18 * n18;
                        int n21;
                        final int n20 = n21 = n19 + n16;
                        int n23;
                        final int n22 = n23 = n10;
                        Label_0392: {
                            if (c == 0) {
                                if (n20 > n22) {
                                    this.p[n2++] = super.a[n3++];
                                    ++n4;
                                    if (c == 0) {
                                        break Label_0392;
                                    }
                                }
                                final int n24;
                                n21 = (n24 = n19 + n16);
                                final int n25;
                                n23 = (n25 = n11);
                            }
                            Label_0370: {
                                if (c == 0) {
                                    if (n20 <= n22) {
                                        break Label_0370;
                                    }
                                    n21 = 256 * (n19 + n16 - n11);
                                    n23 = n10 - n11;
                                }
                                final int n26 = n21 / n23;
                                final int n27 = 256 - n26;
                                final int n28 = super.a[n3++] & 0xFFFFFF;
                                final int n29 = super.b[n4++] & 0xFFFFFF;
                                this.p[n2++] = (n26 * (n28 & 0xFF) + n27 * (n29 & 0xFF)) / 256 + ((n26 * (n28 >> 8 & 0xFF) + n27 * (n29 >> 8 & 0xFF)) / 256 << 8) + ((n26 * (n28 >> 16) + n27 * (n29 >> 16)) / 256 << 16);
                                if (c == 0) {
                                    break Label_0392;
                                }
                            }
                            this.p[n2++] = super.b[n4++];
                            ++n3;
                        }
                        ++n17;
                    }
                    if (n17 < this.t) {
                        continue;
                    }
                    break;
                }
                ++n14;
            }
            if (n14 >= this.u) {
                return this.p;
            }
            continue;
        }
    }
}
