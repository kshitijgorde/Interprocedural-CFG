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
        final int n5 = (int)n * (this.t / 2) * 1414 / (super.f * 1000);
        final int n6 = n5 * 95 / 100;
        final int n7 = n5 * n5;
        final int n8 = n6 * n6;
        final int n9 = this.u / 2;
        final int n10 = this.t / 2;
        int n11 = 0;
        while (true) {
            Label_0371: {
                if (c == 0) {
                    break Label_0371;
                }
                final int n12 = n9 - n11;
                final int n13 = n12 * n12;
                int n14 = 0;
                while (true) {
                    Label_0359: {
                        if (c == 0) {
                            break Label_0359;
                        }
                        final int n15 = n10 - n14;
                        final int n16 = n15 * n15;
                        Label_0356: {
                            if (n16 + n13 > n7) {
                                this.p[n2++] = super.a[n3++];
                                ++n4;
                                if (c == 0) {
                                    break Label_0356;
                                }
                            }
                            if (n16 + n13 > n8) {
                                final int n17 = 256 * (n16 + n13 - n8) / (n7 - n8);
                                final int n18 = 256 - n17;
                                final int n19 = super.a[n3++] & 0xFFFFFF;
                                final int n20 = super.b[n4++] & 0xFFFFFF;
                                this.p[n2++] = (n17 * (n19 & 0xFF) + n18 * (n20 & 0xFF)) / 256 + ((n17 * (n19 >> 8 & 0xFF) + n18 * (n20 >> 8 & 0xFF)) / 256 << 8) + ((n17 * (n19 >> 16) + n18 * (n20 >> 16)) / 256 << 16);
                                if (c == 0) {
                                    break Label_0356;
                                }
                            }
                            this.p[n2++] = super.b[n4++];
                            ++n3;
                        }
                        ++n14;
                    }
                    if (n14 < this.t) {
                        continue;
                    }
                    break;
                }
                ++n11;
            }
            if (n11 >= this.u) {
                return this.p;
            }
            continue;
        }
    }
}
