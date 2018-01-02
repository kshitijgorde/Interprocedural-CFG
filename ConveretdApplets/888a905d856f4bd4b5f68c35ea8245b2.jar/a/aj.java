// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;

public final class aj extends bp
{
    public Image q;
    public String q;
    
    public final Object q(final String s) {
        if ("showtousers".equals(s)) {
            return new Boolean(this.q(36));
        }
        if ("image".equals(s)) {
            return this.q;
        }
        return super.q(s);
    }
    
    public final boolean q(final String s, final Object o) {
        if ("showtousers".equals(s)) {
            this.q(36, (boolean)o);
            return true;
        }
        return super.q(s, o);
    }
    
    public final String q() {
        return null;
    }
    
    public aj(final int n, final String s) {
        super(n, s);
    }
    
    public final int q(final aj aj) {
        final int q;
        if ((q = super.q(aj)) != 0) {
            return q;
        }
        if (this.q != aj.q) {
            return 1;
        }
        if (this.q.compareTo(aj.q) != 0) {
            return this.q.compareTo(aj.q);
        }
        return 0;
    }
    
    public aj(final aj aj) {
        super(aj.s, aj.a);
        this.q(aj.w());
        this.q = aj.q;
        this.q = aj.q;
    }
}
