// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;

public final class bi extends ba
{
    public int t;
    public Image q;
    public String q;
    public int y;
    
    public bi(final int n, final String s) {
        super(n, s);
        this.q = null;
        this.q = "";
        this.y = 0;
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
            return "" + this.y;
        }
        if ("description".equals(s)) {
            return this.q;
        }
        return super.q(s);
    }
    
    public static bi q(final cq cq, final int n) {
        if (n > 0) {
            for (int i = 0; i < cq.q(); ++i) {
                final bi bi = (bi)cq.q(i);
                if (n == bi.t) {
                    return bi;
                }
            }
        }
        return null;
    }
    
    public static int q(final cq cq, final int n) {
        if (cq != null && n > 0) {
            for (int i = 0; i < cq.q(); ++i) {
                final bi bi = (bi)cq.q(i);
                if (n == bi.q) {
                    return bi.t;
                }
            }
        }
        return -1;
    }
    
    static {
        cv.q("No group");
    }
}
