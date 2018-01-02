// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;

public class ar extends l implements aY
{
    public av q;
    public bv q;
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
        return a.s.q(ak.q("Double-click here to enter a private conversation with %1."), new String[] { super.o });
    }
    
    public ar(final int n, final String s) {
        super(n, s);
    }
}
