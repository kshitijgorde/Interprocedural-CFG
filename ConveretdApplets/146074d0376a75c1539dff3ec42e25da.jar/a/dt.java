// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class dt extends dj implements aF
{
    public boolean y;
    
    public final Object q(final String s) {
        return super.q(s);
    }
    
    public final String q() {
        if (this.y) {
            return ec.q(eb.q("Double-click here to enter a private conversation with %1."), new String[] { this.getName() });
        }
        return ec.q(eb.q("%1 is not online at this time."), new String[] { this.getName() });
    }
    
    public dt(final int n, final String s) {
        super(n, s);
    }
}
