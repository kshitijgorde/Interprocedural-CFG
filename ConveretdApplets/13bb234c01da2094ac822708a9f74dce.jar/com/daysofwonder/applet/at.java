// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import com.daysofwonder.util.t;

public class at
{
    public static void a(final aC ac, final int n, final ap ap) {
        final z p3 = ap.P();
        final w r = ap.r();
        try {
            ac.a(p3, n);
            int i = 0;
            while (i < n) {
                ac.a(p3, i, 1);
                r.d();
                ac.a(p3, i, 0);
                if (!ac.b(p3, i++)) {
                    break;
                }
                if (ap.T()) {
                    break;
                }
            }
        }
        catch (Exception ex) {
            t.a(ex);
        }
        finally {
            ac.a(p3);
        }
    }
}
