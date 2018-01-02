// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class bU extends bT implements aF
{
    public String e;
    
    public final Object q(final String s) {
        if ("group".equals(s)) {
            return this.e;
        }
        return super.q(s);
    }
    
    public final String q() {
        return ec.q(eb.q("Click here to edit the account %1."), new String[] { this.toString() });
    }
    
    public bU(final bT bt) {
        this(bt.r, bt.getName());
        this.q(bt.q().clone());
        super.q = bt.q;
        super.q = bt.q;
        super.i = bt.i;
        super.t = bt.y();
        super.t(bt.u);
        super.q(bt.w);
        super.w(bt.e);
        super.q = bt.q;
        super.w = bt.w;
        super.q = bt.q;
        super.w = bt.w;
    }
    
    public bU(final int n, final String s) {
        super(n, s);
    }
}
