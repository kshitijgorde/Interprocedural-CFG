// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.A;

final class O implements M
{
    int B;
    int[] A;
    int[] C;
    String D;
    
    O(final int n) {
        this.A = new int[n];
        this.C = new int[n];
    }
    
    public int B() {
        final int n = this.C[0] - this.A[0];
        return (n > 0) ? n : false;
    }
    
    public int A() {
        return this.A.length;
    }
    
    public String D(final int n) {
        if (n < this.A.length) {
            final int n2 = this.A[n];
            final int n3 = this.C[n];
            final int length = this.D.length();
            if (n2 >= 0 && n3 >= 0) {
                if (n2 < length && n3 <= length && n3 > n2) {
                    return this.D.substring(n2, n3);
                }
                if (n2 <= n3) {
                    return "";
                }
            }
        }
        return null;
    }
    
    public int E(final int n) {
        if (n < this.A.length) {
            final int n2 = this.A[n];
            final int n3 = this.C[n];
            if (n2 >= 0 && n3 >= 0) {
                return n2;
            }
        }
        return -1;
    }
    
    public int A(final int n) {
        if (n < this.A.length) {
            final int n2 = this.A[n];
            final int n3 = this.C[n];
            if (n2 >= 0 && n3 >= 0) {
                return n3;
            }
        }
        return -1;
    }
    
    public int C(final int n) {
        if (n < this.A.length) {
            final int n2 = this.A[n];
            final int n3 = this.C[n];
            if (n2 >= 0 && n3 >= 0) {
                return this.B + n2;
            }
        }
        return -1;
    }
    
    public int B(final int n) {
        if (n < this.C.length) {
            final int n2 = this.A[n];
            final int n3 = this.C[n];
            if (n2 >= 0 && n3 >= 0) {
                return this.B + n3;
            }
        }
        return -1;
    }
    
    public String toString() {
        return this.D(0);
    }
}
