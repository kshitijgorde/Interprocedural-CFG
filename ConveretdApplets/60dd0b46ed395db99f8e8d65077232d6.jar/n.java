// 
// Decompiled by Procyon v0.5.30
// 

public class n extends b
{
    long k;
    boolean l;
    int m;
    int n;
    int[] o;
    
    public n(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
        super(array, array2, n, n2, n3, n4);
        this.k = 0L;
        this.l = true;
        this.m = 0;
        this.n = super.c * super.d;
        this.o = new int[this.n];
    }
    
    public int[] a(final long n) {
        final int c = a.c;
        final int m = super.c * (int)n / super.f;
        int l;
        final int n2 = l = (this.l ? 1 : 0);
        if (c == 0) {
            if (n2 != 0) {
                this.l = false;
                System.arraycopy(super.a, 0, this.o, 0, this.n);
            }
            l = 0;
        }
        int n3 = l;
        while (true) {
            Label_0114: {
                if (c == 0) {
                    break Label_0114;
                }
                int n4 = n3 * super.c + this.m;
                int i = this.m;
                while (true) {
                    Label_0105: {
                        if (c == 0) {
                            break Label_0105;
                        }
                        this.o[n4] = super.b[n4++];
                        ++i;
                    }
                    if (i < m) {
                        continue;
                    }
                    break;
                }
                ++n3;
            }
            if (n3 >= super.d) {
                this.m = m;
                return this.o;
            }
            continue;
        }
    }
}
