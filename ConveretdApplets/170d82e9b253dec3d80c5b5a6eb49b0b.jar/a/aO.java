// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.MenuItem;

public final class aO extends aG
{
    public static MenuItem q;
    
    public static aI q(final cV cv) {
        Object o;
        if (q()) {
            o = new bj(cv);
        }
        else {
            o = new aU(cv);
        }
        return (aI)o;
    }
    
    public static void q(final boolean enabled) {
        if (q()) {
            aO.q.setEnabled(enabled);
            return;
        }
        aO.q.q.q(enabled);
    }
}
