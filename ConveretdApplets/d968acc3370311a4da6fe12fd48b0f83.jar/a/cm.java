// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;

public class cm extends bZ
{
    public Image q;
    public int q;
    public String q;
    
    public cm(final int n, final String s) {
        super(n, s);
        this.q = -1;
    }
    
    public final int w() {
        return this.q;
    }
    
    public final void q(int q) {
        if (q == 0) {
            q = -1;
        }
        this.q = q;
    }
    
    public final int q(final cm cm) {
        final int q;
        if ((q = super.q(cm)) != 0) {
            return q;
        }
        if (this.q != null && this.q != cm.q) {
            return 1;
        }
        if (this.q != cm.q) {
            return this.q - cm.q;
        }
        if (this.q.compareTo(cm.q) != 0) {
            return this.q.compareTo(cm.q);
        }
        return 0;
    }
}
