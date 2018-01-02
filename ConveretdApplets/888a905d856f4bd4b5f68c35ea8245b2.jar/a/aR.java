// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class aR extends bp implements bJ
{
    public int q;
    public String q;
    public int w;
    public int e;
    
    public aR(final int n, final String s) {
        super(n, s);
        this.q = 20;
        this.w = 14;
        this.e = 0;
    }
    
    public final String q() {
        return B.q(be.w("Click here to edit the sms %1."), new String[] { this.toString() });
    }
    
    public final boolean q(final String s, final Object o) {
        if ("turnon".equals(s)) {
            this.q(0, (boolean)o);
            return true;
        }
        return false;
    }
    
    public final Object q(final String s) {
        if ("turnon".equals(s)) {
            return new Boolean(this.q(0));
        }
        return super.q(s);
    }
}
