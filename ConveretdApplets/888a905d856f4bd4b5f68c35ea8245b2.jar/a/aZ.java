// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;

public class aZ extends bp
{
    public Image q;
    public int q;
    public String q;
    
    public aZ(final int n, final String s) {
        super(n, s);
        this.q = -1;
    }
    
    public final void q(int q) {
        if (q == 0) {
            q = -1;
        }
        this.q = q;
    }
    
    public final int q(final aZ az) {
        final int q;
        if ((q = super.q(az)) != 0) {
            return q;
        }
        if (this.q != null && this.q != az.q) {
            return 1;
        }
        if (this.q != az.q) {
            return this.q - az.q;
        }
        if (this.q.compareTo(az.q) != 0) {
            return this.q.compareTo(az.q);
        }
        return 0;
    }
}
