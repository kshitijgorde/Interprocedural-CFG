// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class eh extends aO implements bJ
{
    public boolean y;
    
    public final Object q(final String s) {
        return super.q(s);
    }
    
    public final String q() {
        if (this.y) {
            return B.q(be.w("Double-click here to enter a private conversation with %1."), new String[] { super.a });
        }
        return B.q(be.w("%1 is not online at this time."), new String[] { super.a });
    }
    
    public eh(final int n, final String s) {
        super(n, s);
    }
}
