// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

class D implements e
{
    private long[] E;
    private int[] F;
    
    public D(final long[] array, final int[] array2) {
        this.E = array.clone();
        this.F = array2.clone();
    }
    
    public Q A(long n, final int n2) {
        for (int length = this.F.length, i = 0; i < length; ++i) {
            final int n3 = this.F[i];
            if (n < n3) {
                return new Q(this.E[i] + n, Math.min(n3 - (int)n, n2));
            }
            n -= n3;
        }
        return null;
    }
    
    public void A(long n, final int n2, final Q q) {
        for (int length = this.F.length, i = 0; i < length; ++i) {
            final int n3 = this.F[i];
            if (n < n3) {
                q.A(this.E[i] + n);
                q.A(Math.min(n3 - (int)n, n2));
                return;
            }
            n -= n3;
        }
        q.A(-1L);
        q.A(-1);
    }
}
