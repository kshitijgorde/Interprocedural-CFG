// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class cI extends ar implements aY
{
    public boolean t;
    
    public final Object q(final String s) {
        return super.q(s);
    }
    
    public final String q() {
        if (this.t) {
            return a.s.q(ak.q("Double-click here to enter a private conversation with %1."), new String[] { super.o });
        }
        return a.s.q(ak.q("%1 is not online at this time."), new String[] { super.o });
    }
    
    public cI(final int n, final String s) {
        super(n, s);
    }
}
