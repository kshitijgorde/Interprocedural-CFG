// 
// Decompiled by Procyon v0.5.30
// 

public class o extends b
{
    long k;
    boolean l;
    int m;
    int n;
    int[] o;
    
    public o(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
        super(array, array2, n, n2, n3, n4);
        this.k = 0L;
        this.l = true;
        this.m = 0;
        this.n = super.c * super.d;
        this.o = new int[this.n];
    }
    
    public int[] a(final long n) {
        final int c = a.c;
        final int n3;
        int n2 = n3 = (int)n;
        final int f = super.f;
        if (c == 0) {
            if (n3 >= f) {
                n2 = super.f - 1;
            }
            final int n4 = super.c * (super.f - n2) / super.f / 2;
        }
        final int n6;
        int n5 = n6 = n3 - f;
        if (c == 0) {
            if (n6 < 0) {
                n5 = 0;
            }
            final int n7 = super.c - n5;
        }
        final int n8 = n6;
        final int n10;
        int n9 = n10 = super.d * (super.f - n2) / super.f / 2 - 1;
        if (c == 0) {
            if (n10 < 0) {
                n9 = 0;
            }
            final int n11 = super.d - n9;
        }
        final int n12 = n10;
        final float n13 = super.c / (super.c - 2 * n5);
        final float n14 = super.d / (super.d - 2 * n9);
        float n15 = 0.5f;
        int l;
        final int n16 = l = (this.l ? 1 : 0);
        if (c == 0) {
            if (n16 != 0) {
                this.l = false;
                System.arraycopy(super.a, 0, this.o, 0, this.n);
            }
            l = n9;
        }
        int n17 = l;
    Label_0310_Outer:
        while (true) {
            Label_0335: {
                if (c == 0) {
                    break Label_0335;
                }
                int n18 = n17 * super.c + n5;
                float n19 = 0.5f;
                final int n20 = (int)n15;
                final int n21 = super.d - 1;
                if (c == 0) {
                    if (n20 > n21) {
                        final int n22 = super.d - 1;
                    }
                    final int c2 = super.c;
                }
                int n23 = n20 * n21;
                int n24 = n5;
                while (true) {
                    while (true) {
                        Label_0313: {
                            if (c == 0) {
                                break Label_0313;
                            }
                            this.o[n18++] = super.b[n23];
                            n19 += n13;
                            n23 += (int)n19;
                            ++n24;
                        }
                        if (n24 < n8) {
                            continue Label_0310_Outer;
                        }
                        break;
                    }
                    n15 += n14;
                    if (c != 0) {
                        continue;
                    }
                    break;
                }
                ++n17;
            }
            if (n17 >= n12) {
                return this.o;
            }
            continue;
        }
    }
}
