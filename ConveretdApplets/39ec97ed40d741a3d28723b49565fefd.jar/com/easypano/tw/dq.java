// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Component;

class dq implements dp
{
    final /* synthetic */ o a;
    
    dq(final o a) {
        this.a = a;
    }
    
    public void a(final double n) {
        final Component a = o.a(this.a);
        if (!g.q) {
            if (a == null) {
                return;
            }
            o.a(this.a);
        }
        final int n2 = a.getBounds().width - o.b(this.a).getBounds().width;
        if (n2 > 0) {
            o.a(this.a).setLocation(-(int)(n2 * n / 100.0), o.a(this.a).getBounds().y);
        }
    }
    
    public double a() {
        final boolean q = g.q;
        final Component a = o.a(this.a);
        if (!q) {
            if (a == null) {
                return 0.0;
            }
            o.a(this.a);
        }
        final int n2;
        final int n = n2 = a.getBounds().width - o.b(this.a).getBounds().width;
        if (!q) {
            if (n2 <= 0) {
                return 0.0;
            }
            final int width = o.b(this.a).getBounds().width;
        }
        return n2 / 2.0 / n * 100.0;
    }
}
