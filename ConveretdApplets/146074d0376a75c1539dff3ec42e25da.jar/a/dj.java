// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;

public class dj extends cz implements aF
{
    public cm q;
    cx q;
    Image e;
    public Image r;
    
    public Object q(final String s) {
        if ("room".equals(s)) {
            return new Integer(super.o);
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
        return ec.q(eb.q("Double-click here to enter a private conversation with %1."), new String[] { this.getName() });
    }
    
    public dj(final int n, final String s) {
        super(n, s);
    }
    
    public final Image q() {
        return this.e;
    }
    
    public final cx q() {
        return this.q;
    }
    
    public final void q(final cx q) {
        this.q = q;
    }
    
    public final int q(final dj dj) {
        final int q;
        if ((q = super.q((cz)dj)) != 0) {
            return q;
        }
        if (this.e != null && this.e != dj.e) {
            return 1;
        }
        if (this.r != null && this.r != dj.r) {
            return 1;
        }
        if (this.q != null && this.q != dj.q) {
            return 1;
        }
        if (this.q != null && this.q != dj.q) {
            return 1;
        }
        return 0;
    }
}
