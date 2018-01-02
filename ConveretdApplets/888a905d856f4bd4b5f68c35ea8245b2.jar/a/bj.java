// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.MenuItem;

public final class bj extends cU
{
    public static MenuItem q;
    
    public static az q(final ap ap) {
        Object o;
        if (q()) {
            o = new aW(ap);
        }
        else {
            o = new da(ap);
        }
        return (az)o;
    }
    
    public static void q(final boolean b) {
        if (q()) {
            bj.q.setEnabled(b);
            return;
        }
        bj.q.q.q = b;
    }
}
