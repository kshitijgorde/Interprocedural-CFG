// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Component;

class dr implements dp
{
    final /* synthetic */ p a;
    
    dr(final p a) {
        this.a = a;
    }
    
    public void a(final double n) {
        final Component a = p.a(this.a);
        if (h.q == 0) {
            if (a == null) {
                return;
            }
            p.a(this.a);
        }
        final int n2 = a.getBounds().height - p.b(this.a).getBounds().height;
        if (n2 > 0) {
            p.a(this.a).setLocation(p.a(this.a).getBounds().x, -(int)(n2 * n / 100.0));
        }
    }
    
    public double a() {
        final int q = h.q;
        final Component a = p.a(this.a);
        if (q == 0) {
            if (a == null) {
                return 0.0;
            }
            p.a(this.a);
        }
        final int n2;
        final int n = n2 = a.getBounds().height - p.b(this.a).getBounds().height;
        if (q == 0) {
            if (n2 <= 0) {
                return 0.0;
            }
            final int height = p.b(this.a).getBounds().height;
        }
        return n2 / 2.0 / n * 100.0;
    }
}
