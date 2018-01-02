// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;

public class aO extends p implements bJ
{
    public aZ q;
    public cx q;
    public Image q;
    public Image w;
    
    public Object q(final String s) {
        if ("room".equals(s)) {
            return new Integer(super.r);
        }
        if (this.q != null && "icon".equals(s)) {
            return this.q.q;
        }
        if (this.q != null && "star".equals(s)) {
            return this.q.q;
        }
        if (this.w != null && "countryFlag".equals(s)) {
            return this.w;
        }
        return super.q(s);
    }
    
    public String q() {
        return B.q(be.w("Double-click here to enter a private conversation with %1."), new String[] { super.a });
    }
    
    public aO(final int n, final String s) {
        super(n, s);
    }
    
    public final int q(final aO ao) {
        final int q;
        if ((q = super.q((p)ao)) != 0) {
            return q;
        }
        if (this.q != null && this.q != ao.q) {
            return 1;
        }
        if (this.w != null && this.w != ao.w) {
            return 1;
        }
        if (this.q != null && this.q != ao.q) {
            return 1;
        }
        if (this.q != null && this.q != ao.q) {
            return 1;
        }
        return 0;
    }
}
