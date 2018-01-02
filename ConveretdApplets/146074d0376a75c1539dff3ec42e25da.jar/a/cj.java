// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;

public final class cj extends bZ
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
    
    public cj(final int n, final String s) {
        super(n, s);
    }
    
    public final int q(final cj cj) {
        final int q;
        if ((q = super.q(cj)) != 0) {
            return q;
        }
        if (this.q != cj.q) {
            return 1;
        }
        if (this.q.compareTo(cj.q) != 0) {
            return this.q.compareTo(cj.q);
        }
        return 0;
    }
    
    public cj(final cj cj) {
        super(cj.r, cj.getName());
        this.q(cj.q());
        this.q = cj.q;
        this.q = cj.q;
    }
}
