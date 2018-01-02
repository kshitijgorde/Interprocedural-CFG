// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.A;

public final class B
{
    String B;
    char[] D;
    char[] H;
    char[] E;
    int G;
    int F;
    int A;
    int C;
    int I;
    
    public B(final String s, final int n, final int n2) {
        this.C = -1;
        this.I = -1;
        this.A(s, n, n2);
    }
    
    public B(final String s) {
        this(s, 0, s.length());
    }
    
    public B(final char[] array, final int n, final int n2) {
        this.C = -1;
        this.I = -1;
        this.A(array, n, n2);
    }
    
    public B(final char[] array) {
        this(array, 0, array.length);
    }
    
    public int L() {
        return this.F - this.G;
    }
    
    public void A(final String b, final int n, final int n2) {
        this.B = b;
        this.D = null;
        this.E = null;
        this.H = b.toCharArray();
        this.C(n);
        this.B(n);
        this.E(this.G + n2);
    }
    
    public void A(final String s) {
        this.A(s, 0, s.length());
    }
    
    public void A(final char[] array, final int n, final int n2) {
        this.B = null;
        this.E = null;
        this.D = array;
        this.H = array;
        this.C(n);
        this.B(n);
        this.E(this.G + n2);
    }
    
    public void A(final char[] array) {
        this.A(array, 0, array.length);
    }
    
    public char D(final int n) {
        return this.H[this.G + n];
    }
    
    public String A(final int n, final int n2) {
        return new String(this.H, this.G + n, n2 - n);
    }
    
    public String A(int n) {
        n += this.G;
        return new String(this.H, n, this.F - n);
    }
    
    public Object C() {
        if (this.B == null) {
            return this.D;
        }
        return this.B;
    }
    
    public char[] J() {
        return this.H;
    }
    
    public boolean H() {
        return this.A >= this.F;
    }
    
    public int F() {
        return this.G;
    }
    
    public int D() {
        return this.F;
    }
    
    public int B() {
        return this.A;
    }
    
    public void B(final int g) {
        this.G = g;
    }
    
    public void E(final int f) {
        this.F = f;
    }
    
    public void C(final int a) {
        this.A = a;
        this.B(-1, -1);
    }
    
    public String toString() {
        return new String(this.H, this.G, this.L());
    }
    
    public String A() {
        return new String(this.H, this.G, this.C - this.G);
    }
    
    public String K() {
        return new String(this.H, this.I, this.F - this.I);
    }
    
    public String I() {
        return new String(this.H, this.C, this.I - this.C);
    }
    
    public void B(final int c, final int i) {
        this.C = c;
        this.I = i;
    }
    
    public int G() {
        return this.C;
    }
    
    public int E() {
        return this.I;
    }
}
