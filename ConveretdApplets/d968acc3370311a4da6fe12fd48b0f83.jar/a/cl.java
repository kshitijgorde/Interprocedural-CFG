// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;

public final class cl extends bZ
{
    public static final String q;
    public int q;
    public Image q;
    public String w;
    public int w;
    
    public cl(final int n, final String s) {
        super(n, s);
        this.q = null;
        this.w = "";
        this.w = 0;
    }
    
    public cl(final cl cl) {
        super(cl.r, cl.getName());
        this.q = null;
        this.w = "";
        this.w = 0;
        this.q(cl.q());
        this.q = cl.q;
        this.q = cl.q;
        this.w = cl.w;
        this.w = cl.w;
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
    
    public static cl q(final dW dw, final int n) {
        if (n > 0) {
            for (int i = 0; i < dw.q(); ++i) {
                final cl cl = (cl)dw.q(i);
                if (n == cl.q) {
                    return cl;
                }
            }
        }
        return null;
    }
    
    public static int q(final dW dw, final int n) {
        if (dw != null && n > 0) {
            for (int i = 0; i < dw.q(); ++i) {
                final cl cl = (cl)dw.q(i);
                if (n == cl.r) {
                    return cl.q;
                }
            }
        }
        return -1;
    }
    
    public final int q(final cl cl) {
        final int q;
        if ((q = super.q(cl)) != 0) {
            return q;
        }
        if (this.q != null && this.q != cl.q) {
            return 1;
        }
        if (this.q != cl.q) {
            return this.q - cl.q;
        }
        if (this.w != null && this.w.compareTo(cl.w) != 0) {
            return this.w.compareTo(cl.w);
        }
        if (this.w != cl.w) {
            return this.w - cl.w;
        }
        return 0;
    }
    
    static {
        q = eb.q("No group");
    }
}
