// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class dC extends bY
{
    public final Object q(final String s) {
        return super.q(s);
    }
    
    public final String q() {
        return ec.q(eb.q("Double-click here to edit %1."), new String[] { this.getName() });
    }
    
    public dC(final bY by) {
        this(by.r, by.getName());
        this.q(by.q());
        super.q = by.q;
        super.w = by.w;
    }
    
    public dC(final int n, final String s) {
        super(n, s);
    }
}
