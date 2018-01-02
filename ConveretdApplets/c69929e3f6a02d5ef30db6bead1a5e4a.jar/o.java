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
        int n2 = (int)n;
        if (n2 >= super.f) {
            n2 = super.f - 1;
        }
        int n3 = super.c * (super.f - n2) / super.f / 2 - 1;
        if (n3 < 0) {
            n3 = 0;
        }
        final int n4 = super.c - n3;
        int n5 = super.d * (super.f - n2) / super.f / 2 - 1;
        if (n5 < 0) {
            n5 = 0;
        }
        final int n6 = super.d - n5;
        final float n7 = super.c / (super.c - 2 * n3);
        final float n8 = super.d / (super.d - 2 * n5);
        float n9 = 0.5f;
        if (this.l) {
            this.l = false;
            System.arraycopy(super.a, 0, this.o, 0, this.n);
        }
        int n10 = n5;
    Label_0285_Outer:
        while (true) {
            Label_0310: {
                if (c == 0) {
                    break Label_0310;
                }
                int n11 = n10 * super.c + n3;
                float n12 = 0.5f;
                int n13 = (int)n9;
                if (n13 > super.d - 1) {
                    n13 = super.d - 1;
                }
                int n14 = n13 * super.c;
                int n15 = n3;
                while (true) {
                    while (true) {
                        Label_0288: {
                            if (c == 0) {
                                break Label_0288;
                            }
                            this.o[n11++] = super.b[n14];
                            n12 += n7;
                            n14 += (int)n12;
                            ++n15;
                        }
                        if (n15 < n4) {
                            continue Label_0285_Outer;
                        }
                        break;
                    }
                    n9 += n8;
                    if (c != 0) {
                        continue;
                    }
                    break;
                }
                ++n10;
            }
            if (n10 >= n6) {
                return this.o;
            }
            continue;
        }
    }
}
