// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class cL extends C
{
    public final Object q(final String s) {
        return super.q(s);
    }
    
    public final String q() {
        return B.q(be.w("Double-click here to edit %1."), new String[] { super.a });
    }
    
    public cL(final C c) {
        this(c.s, c.a);
        this.q(c.w());
        super.q = c.q;
        super.w = c.w;
    }
    
    public cL(final int n, final String s) {
        super(n, s);
    }
}
