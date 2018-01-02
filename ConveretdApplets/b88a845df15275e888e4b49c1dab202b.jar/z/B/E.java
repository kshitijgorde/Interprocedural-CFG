// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

class E implements e
{
    long[] C;
    int B;
    int D;
    int A;
    
    public E(final long[] array, final int b, final int d) {
        this.C = array.clone();
        this.B = b;
        this.D = d;
        this.A = d - (array.length - 1) * b;
    }
    
    public Q A(long n, final int n2) {
        final int n3 = (int)(n / this.B);
        final int n4 = (n3 == this.C.length - 1) ? this.A : this.B;
        n -= n3 * this.B;
        int n5 = (int)(n4 - n);
        if (n5 > n2) {
            n5 = n2;
        }
        return new Q(this.C[n3] + n, n5);
    }
    
    public void A(long n, final int n2, final Q q) {
        final int n3 = (int)(n / this.B);
        final int n4 = (n3 == this.C.length - 1) ? this.A : this.B;
        n -= n3 * this.B;
        int n5 = (int)(n4 - n);
        if (n5 > n2) {
            n5 = n2;
        }
        q.A(this.C[n3] + n);
        q.A(n5);
    }
}
