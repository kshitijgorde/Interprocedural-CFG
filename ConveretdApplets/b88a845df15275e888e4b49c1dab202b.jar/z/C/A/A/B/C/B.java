// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.C;

class B extends I
{
    char F;
    
    B(final char f, final int n) {
        super(n);
        this.F = f;
    }
    
    boolean A(final char c) {
        return this.F == c;
    }
    
    P A(final int[] array) {
        return new B(this.F, array[0]++);
    }
}
