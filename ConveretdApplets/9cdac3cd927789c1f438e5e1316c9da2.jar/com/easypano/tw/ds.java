// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Component;

class ds implements dq
{
    final /* synthetic */ o a;
    
    ds(final o a) {
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
        final int n2 = a.getBounds().height - o.b(this.a).getBounds().height;
        if (n2 > 0) {
            o.a(this.a).setLocation(o.a(this.a).getBounds().x, -(int)(n2 * n / 100.0));
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
        final int n = n2 = a.getBounds().height - o.b(this.a).getBounds().height;
        if (!q) {
            if (n2 <= 0) {
                return 0.0;
            }
            final int height = o.b(this.a).getBounds().height;
        }
        return n2 / 2.0 / n * 100.0;
    }
}
