// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class cF extends as implements aX
{
    public boolean t;
    
    public final Object q(final String s) {
        return super.q(s);
    }
    
    public final String q() {
        if (this.t) {
            return a.t.q(al.q("Double-click here to enter a private conversation with %1."), new String[] { super.o });
        }
        return a.t.q(al.q("%1 is not online at this time."), new String[] { super.o });
    }
    
    public cF(final int n, final String s) {
        super(n, s);
    }
}
