// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class bZ extends bV implements aq
{
    public boolean t;
    
    public final Object q(final String s) {
        return super.q(s);
    }
    
    public final String q() {
        if (this.t) {
            return cv.q(cv.q("Double-click here to enter a private conversation with %1."), new String[] { this.getName() });
        }
        return cv.q(cv.q("%1 is not online at this time."), new String[] { this.getName() });
    }
    
    public bZ(final int n, final String s) {
        super(n, s);
    }
}
