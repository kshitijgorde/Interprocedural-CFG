// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.A;

final class K
{
    static final char B = '\uffff';
    int A;
    char[] C;
    
    K(final char[] c, final int a) {
        this.C = c;
        this.A = a;
    }
    
    K(final char[] array) {
        this(array, 0);
    }
    
    char B() {
        return this.A(this.A);
    }
    
    char A(final int n) {
        if (n < this.C.length && n >= 0) {
            return this.C[n];
        }
        return '\uffff';
    }
    
    char C(final int n) {
        return this.A(this.A + n);
    }
    
    int D() {
        return this.C.length;
    }
    
    int C() {
        return this.A;
    }
    
    void D(final int a) {
        this.A = a;
    }
    
    boolean A() {
        return this.A >= this.C.length;
    }
    
    char B(final int n) {
        this.A += n;
        if (this.A()) {
            this.A = this.C.length;
            return '\uffff';
        }
        return this.C[this.A];
    }
    
    char H() {
        return this.B(1);
    }
    
    char F(final int n) {
        this.A -= n;
        if (this.A < 0) {
            this.A = 0;
        }
        return this.C[this.A];
    }
    
    char G() {
        return this.F(1);
    }
    
    char E() {
        final char b = this.B();
        this.H();
        return b;
    }
    
    char F() {
        final char b = this.B();
        this.G();
        return b;
    }
    
    String E(final int n) {
        return new String(this.C, n, this.C.length - n);
    }
    
    public String toString() {
        return this.E(0);
    }
}
