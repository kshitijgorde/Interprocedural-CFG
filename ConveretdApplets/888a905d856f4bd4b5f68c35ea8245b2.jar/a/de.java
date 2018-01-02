// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;

public final class de extends bp
{
    public static final String q;
    public int q;
    public Image q;
    public String w;
    public int w;
    
    public de(final int n, final String s) {
        super(n, s);
        this.q = null;
        this.w = "";
        this.w = 0;
    }
    
    public de(final de de) {
        super(de.s, de.a);
        this.q = null;
        this.w = "";
        this.w = 0;
        this.e = de.e;
        this.q = de.q;
        this.q = de.q;
        this.w = de.w;
        this.w = de.w;
    }
    
    public final boolean q(final String s, final Object o) {
        if ("formasters".equals(s)) {
            this.q(0, (boolean)o);
            return true;
        }
        return false;
    }
    
    public final Object q(final String s) {
        if ("image".equals(s)) {
            return this.q;
        }
        if ("formasters".equals(s)) {
            return new Boolean(this.q(0));
        }
        if ("arrangenumber".equals(s)) {
            return "" + this.w;
        }
        if ("description".equals(s)) {
            return this.w;
        }
        return super.q(s);
    }
    
    public final boolean q() {
        return this.q(0);
    }
    
    public final void q(final boolean b) {
        this.q(0, b);
    }
    
    public static de q(final M m, final int n) {
        if (n > 0) {
            for (int i = 0; i < m.q; ++i) {
                final de de = (de)m.q(i);
                if (n == de.q) {
                    return de;
                }
            }
        }
        return null;
    }
    
    public static int q(final M m, final int n) {
        if (m != null && n > 0) {
            for (int i = 0; i < m.q; ++i) {
                final de de = (de)m.q(i);
                if (n == de.s) {
                    return de.q;
                }
            }
        }
        return -1;
    }
    
    public final int q(final de de) {
        final int q;
        if ((q = super.q(de)) != 0) {
            return q;
        }
        if (this.q != null && this.q != de.q) {
            return 1;
        }
        if (this.q != de.q) {
            return this.q - de.q;
        }
        if (this.w != null && this.w.compareTo(de.w) != 0) {
            return this.w.compareTo(de.w);
        }
        if (this.w != de.w) {
            return this.w - de.w;
        }
        return 0;
    }
    
    static {
        q = be.w("No group");
    }
}
