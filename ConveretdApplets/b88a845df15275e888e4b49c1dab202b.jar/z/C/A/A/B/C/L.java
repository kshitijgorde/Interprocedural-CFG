// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.C;

import z.C.A.A.B.A.M;

final class L implements M
{
    private int S;
    private int R;
    private String Q;
    
    L(final String q, final int s) {
        this.Q = q;
        this.R = q.length();
        this.S = s;
    }
    
    void F(final int n) {
        this.S += n;
    }
    
    public int B() {
        return this.R;
    }
    
    public int A() {
        return 1;
    }
    
    public String D(final int n) {
        return (n == 0) ? this.Q : null;
    }
    
    public int E(final int n) {
        return (n == 0) ? 0 : -1;
    }
    
    public int A(final int n) {
        return (n == 0) ? this.R : -1;
    }
    
    public int C(final int n) {
        return (n == 0) ? this.S : -1;
    }
    
    public int B(final int n) {
        return (n == 0) ? (this.S + this.R) : -1;
    }
    
    public String toString() {
        return this.D(0);
    }
}
