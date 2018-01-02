// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class cs extends bZ implements aF
{
    public int q;
    public String q;
    public int w;
    public int e;
    
    public cs(final int n, final String s) {
        super(n, s);
        this.q = 20;
        this.w = 14;
        this.e = 0;
    }
    
    public final String q() {
        return ec.q(eb.q("Click here to edit the sms %1."), new String[] { this.toString() });
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
    
    public final boolean q() {
        return this.q(1);
    }
    
    public final boolean w() {
        return this.q(2);
    }
    
    public final boolean e() {
        return !this.q(1) && !this.q(2);
    }
}
