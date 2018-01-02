// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;
import java.awt.Canvas;
import java.util.Hashtable;

public class bi
{
    Hashtable q;
    
    public bi() {
    }
    
    public static Canvas q(final String s, final String s2, final String s3, final dH dh, final int n, final int n2) {
        bf q;
        if (!bC.w.z() || s == null) {
            ((ad)(q = new ad(n, n2))).q(dh.q(s3, false));
        }
        else {
            final Image r = dh.r(s + s2 + "_button_up.gif", true);
            final Image r2 = dh.r(s + s2 + "_button_dn.gif", true);
            final Image r3 = dh.r(s + s2 + "_button_disabled.gif", true);
            if (r == null || r2 == null) {
                ((ad)(q = new ad(n, n2))).q(dh.q(s3, false));
            }
            else {
                q = H.q(r, r2, r3);
            }
        }
        return (ad)q;
    }
    
    public static Canvas q(final String s, final String s2, final String s3, final dH dh) {
        return q(s, s2, s3, dh, 25, 25);
    }
    
    public bi(final String s) {
        this.q = new Hashtable();
    }
    
    public void q(final String s, final Image image) {
        this.q.put(s, image);
    }
    
    public Image q(final String s) {
        return this.q.get(s);
    }
}
