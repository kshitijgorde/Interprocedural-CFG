// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;

public class bV extends bp implements aq
{
    public bj q;
    bn q;
    Image e;
    public Image r;
    
    public Object q(final String s) {
        if ("room".equals(s)) {
            return new Integer(super.i);
        }
        if (this.q != null && "icon".equals(s)) {
            return this.q.q;
        }
        if (this.q != null && "star".equals(s)) {
            return this.q.q;
        }
        if (this.r != null && "countryFlag".equals(s)) {
            return this.r;
        }
        return super.q(s);
    }
    
    public String q() {
        return cv.q(cv.q("Double-click here to enter a private conversation with %1."), new String[] { this.getName() });
    }
    
    public bV(final int n, final String s) {
        super(n, s);
    }
    
    public final Image q() {
        return this.e;
    }
    
    public final bn q() {
        return this.q;
    }
    
    public final void q(final bn q) {
        this.q = q;
    }
}
