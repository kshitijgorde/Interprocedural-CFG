// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;

public final class bg extends ba
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
    
    public bg(final int n, final String s) {
        super(n, s);
    }
}
